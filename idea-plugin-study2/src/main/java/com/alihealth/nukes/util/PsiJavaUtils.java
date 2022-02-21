
package com.alihealth.nukes.util;

import com.alihealth.nukes.domain.Annotation;
import com.alihealth.nukes.domain.LooksData;
import com.alihealth.nukes.lombok.LombokAnnotation;
import com.intellij.lang.jvm.JvmAnnotatedElement;
import com.intellij.lang.jvm.JvmAnnotation;
import com.intellij.lang.jvm.annotation.*;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.intellij.psi.CommonClassNames.JAVA_UTIL_LIST;

/**
 * JavaPsi工具
 *
 * @author : zhengrf
 * @date : 2019-01-01
 */
public final class PsiJavaUtils {
    public static final String ROOT_PATH = "root";
    // 注解相关

    /**
     * 判断是否有指定注解
     *
     * @param target     目标元素
     * @param annotation 目标注解
     * @return true 有指定注解，false 没有指定注解
     */
    public static boolean hasAnnotation(JvmAnnotatedElement target, Annotation annotation) {
        return target.hasAnnotation(annotation.getQualifiedName());
    }

    /**
     * 判断是否有指定注解
     *
     * @param target      目标元素
     * @param annotations 目标注解集合
     * @return true 有指定注解，false 没有指定注解
     */
    public static boolean hasAnnotations(JvmAnnotatedElement target, Annotation... annotations) {
        return Arrays.asList(annotations).stream().anyMatch(annotation -> hasAnnotation(target, annotation));
    }

    public static JvmAnnotation getAnnotation(JvmAnnotatedElement target, Annotation annotation) {
        return target.getAnnotation(annotation.getQualifiedName());
    }

    /**
     * 获取注解属性数据（字符串）
     * @param attr
     * @return
     */
    public static String getAnnotationAttrValueAsString(JvmAnnotationAttribute attr) {
        JvmAnnotationAttributeValue attributeValue = attr.getAttributeValue();

        Object obj = null;
        if(attributeValue instanceof JvmAnnotationConstantValue){
            obj = ((JvmAnnotationConstantValue) attributeValue).getConstantValue();
        }

        else if(attributeValue instanceof JvmAnnotationArrayValue){
            //obj =
        }
        else if(attributeValue instanceof JvmNestedAnnotationValue){
            //obj =
        }
        else if(attributeValue instanceof JvmAnnotationClassValue){
            //obj =
        }
        String txt = obj.toString();
        if (txt.contains("\"")) {
            txt = txt.substring(1, txt.length() - 1);
        }
        if (txt != null && txt.length() > 0) {
            txt = txt.trim();
        }
        return txt;
    }

    /**
     * 获取注解属性数据（枚举）
     * @param attr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T getAnnotationAttrValueAsEnum(JvmAnnotationAttribute attr, Class<T> clazz) {
        JvmAnnotationAttributeValue attributeValue = attr.getAttributeValue();
        if(!(attributeValue instanceof JvmAnnotationEnumFieldValue)){
            throw new UnsupportedOperationException();
        }
        return getAnnotationAttrValueAsEnum((JvmAnnotationEnumFieldValue) attributeValue, clazz);
    }

    /**
     * 获取注解属性数据（枚举）
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    protected static <T extends Enum<T>> T getAnnotationAttrValueAsEnum(JvmAnnotationEnumFieldValue value, Class<T> clazz) {
        String name = value.getField().getName();
        return Enum.valueOf(clazz, name);
    }

    /**
     * 获取注解属性数据（字符串数组）
     * @param attr
     * @return
     */
    public static String[] getAnnotationAttrValueAsStringArray(JvmAnnotationAttribute attr) {
        JvmAnnotationAttributeValue attributeValue = attr.getAttributeValue();
        if(attributeValue instanceof JvmAnnotationConstantValue){
            String[] values = new String[1];
            values[0] = getAnnotationAttrValueAsString(attr);
            return values;
        }
        else if (attributeValue instanceof JvmAnnotationArrayValue) {
            List<JvmAnnotationAttributeValue> values = ((JvmAnnotationArrayValue) attributeValue).getValues();
            String[] arrayValues = new String[values.size()];
            for (int i = 0; i < arrayValues.length; i++) {
                JvmAnnotationConstantValue constantValue = (JvmAnnotationConstantValue) values.get(i);
                arrayValues[i] = (String) constantValue.getConstantValue();
            }
            return arrayValues;
        } else {
            throw new UnsupportedOperationException("Not support attributeValue of type:" + attributeValue.getClass());
        }
    }

    /**
     * 获取注解属性数据（枚举数组）
     * @param attr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T[] getAnnotationAttrValueAsEnumArray(JvmAnnotationAttribute attr, Class<T> clazz) {
        JvmAnnotationAttributeValue attributeValue = attr.getAttributeValue();
        if(attributeValue instanceof JvmAnnotationEnumFieldValue){
            T[] values = (T[]) Array.newInstance(clazz,1);
            values[0] = getAnnotationAttrValueAsEnum((JvmAnnotationEnumFieldValue)attributeValue, clazz);
            return values;
        }
        else if (attributeValue instanceof JvmAnnotationArrayValue) {
            List<JvmAnnotationAttributeValue> values = ((JvmAnnotationArrayValue) attributeValue).getValues();
            T[] arrayValues = (T[]) Array.newInstance(clazz,values.size());
            for (int i = 0; i < arrayValues.length; i++) {
                JvmAnnotationEnumFieldValue constantValue = (JvmAnnotationEnumFieldValue) values.get(i);
                arrayValues[i] = getAnnotationAttrValueAsEnum(constantValue, clazz);
            }
            return arrayValues;
        } else {
            throw new UnsupportedOperationException("Not support attributeValue of type:" + attributeValue.getClass());
        }
    }

    // 包相关

    /**
     * 判断是否已经导入目标类
     *
     * @param file          Java类文件
     * @param qualifiedName 类全限定名
     * @return true 已导入，false 未导入
     */
    public static boolean hasImportClass(PsiJavaFile file, String qualifiedName) {
        PsiImportList importList = file.getImportList();
        if (importList == null) {
            return false;
        }
        return importList.findSingleClassImportStatement(qualifiedName) != null;
    }

    /**
     * 导入类到指定Java文件
     *
     * @param file     Java文件
     * @param psiClass 要导入的类
     */
    public static void importClass(PsiJavaFile file, PsiClass psiClass) {
        file.importClass(psiClass);
    }

    // 字段相关

    /**
     * 判断是否为目标字段
     *
     * @param psiField 字段
     */
    public static boolean notSerialField(PsiField psiField) {
        return !"serialVersionUID".equals(psiField.getName());
    }

    // 方法相关

    /**
     * 判断是否为 void 方法
     *
     * @param method 方法
     */
    public static boolean isVoidMethod(PsiMethod method) {
        return PsiTypeUtils.isVoid(method.getReturnType());
    }

    /**
     * 判断是否为 getXXX 函数
     *
     * @param method 方法
     */
    public static boolean isGetMethod(PsiMethod method) {
        return isPublicMethod(method)
                && !isVoidMethod(method)
                && method.getName().startsWith("get")
                && method.getName().length() > 3;
    }

    /**
     * 判断是否为 本地方法 函数
     *
     * @param method 方法
     */
    public static boolean isNativeMethod(PsiMethod method) {
        return hasModifierProperty(method, PsiModifier.NATIVE);
    }

    /**
     * 判断是否为 public 函数
     *
     * @param method 方法
     */
    public static boolean isPublicMethod(PsiMethod method) {
        return hasModifierProperty(method, PsiModifier.PUBLIC);
    }

    /**
     * 判断是否为 static 函数
     *
     * @param method 方法
     */
    public static boolean isStaticMethod(PsiMethod method) {
        return hasModifierProperty(method, PsiModifier.STATIC);
    }

    /**
     * 判断是否又指定标识符
     *
     * @param method   方法
     * @param modifier 标识符 {@link PsiModifier}
     */
    private static boolean hasModifierProperty(PsiMethod method, String modifier) {
        return method.getModifierList().hasModifierProperty(modifier);
    }

    public static boolean isPublicField(PsiField field) {
        return hasModifierProperty(field, PsiModifier.PUBLIC);
    }

    public static boolean isStaticField(PsiField field) {
        return hasModifierProperty(field, PsiModifier.STATIC);
    }

    private static boolean hasModifierProperty(PsiField field, String modifier) {
        return field.getModifierList().hasModifierProperty(modifier);
    }

    /**
     * 获取指定方法
     *
     * @param psiClass   类信息
     * @param methodName 方法名称
     * @return 方法信息
     */
    public static Optional<PsiMethod> findPsiMethod(PsiClass psiClass, String methodName) {
        PsiMethod[] methods = psiClass.findMethodsByName(methodName, true);
        if (methods.length == 0) {
            return Optional.empty();
        }
        return Optional.of(methods[0]);
    }


    /**
     * 处理 getAaaBbb 方法名称
     *
     * @param method java方法
     * @return getAaaBbb->aaaBbb,或者 ""
     */
    public static String replaceGetPrefix(PsiMethod method) {
        String methodName = method.getName();
        if (methodName.length() == 3) {
            return "";
        }
        char first = Character.toLowerCase(methodName.charAt(3));
        if (methodName.length() > 4) {
            return first + methodName.substring(4);
        }
        return String.valueOf(first);
    }

    // Java 相关Element

    /**
     * 判断 PsiElement 是否为接口
     *
     * @param psiElement 元素
     * @return 如果是接口则返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isInterface(PsiElement psiElement) {
        return psiElement instanceof PsiClass && ((PsiClass) psiElement).isInterface();
    }

    /**
     * 判断 PsiElement 是否为类方法
     *
     * @param psiElement 元素
     * @return 如果是接口则返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isInterfaceMethod(PsiElement psiElement) {
        return psiElement instanceof PsiMethod && isElementWithinInterface(psiElement);
    }

    private static boolean isElementWithinInterface(PsiElement element) {
        if (element instanceof PsiClass && ((PsiClass) element).isInterface()) {
            return true;
        }
        PsiClass parentOfType = PsiTreeUtil.getParentOfType(element, PsiClass.class);
        return parentOfType != null && parentOfType.isInterface();
    }

    /**
     * 转为 getXxx方法名
     */

    public static String toGetPrefix(final String text) {
        return "get" + Character.toUpperCase(text.charAt(0)) + (text.length() > 1 ? text.substring(1) : "");
    }

    public static void psiClassProcessor(PsiClassType psiClassType,
                                         Predicate<PsiField> fieldCondition, Consumer<PsiField> fieldConsumer,
                                         Predicate<PsiMethod> methodCondition, Consumer<PsiMethod> methodConsumer) {
        if (psiClassType == null) {
            return;
        }
        PsiClass psiClass = psiClassType.resolve();
        psiClassProcessor(psiClass, fieldCondition, fieldConsumer, methodCondition, methodConsumer);
    }

    public static void psiClassProcessor(PsiClass psiClass,
                                         Predicate<PsiField> fieldCondition, Consumer<PsiField> fieldConsumer,
                                         Predicate<PsiMethod> methodCondition, Consumer<PsiMethod> methodConsumer) {
        if (psiClass == null) {
            return;
        }
        for (PsiField field : psiClass.getAllFields()) {
            if (fieldCondition.test(field)) {
                fieldConsumer.accept(field);
            }
        }
        for (PsiMethod method : psiClass.getAllMethods()) {
            if (methodCondition.test(method)) {
                methodConsumer.accept(method);
            }
        }
    }

    public static LooksData getTypeData(PsiType type) {
        return getTypeData( ROOT_PATH, type);
    }

    public static LooksData getTypeData(String dataKey, PsiType type) {
        return getTypeData( dataKey, dataKey,  type, new HashMap<>());
    }

    public static LooksData getTypeData(String dataPath, String dataKey, PsiType type, Map<String, String> classNameMap) {
        if (type == null) {
            return null;
        }
        if (PsiTypeUtils.isVoid(type)) {
            return LooksData.empty();
        }

//        String canonicalText = type.getCanonicalText();
        //数组类型
        if (PsiTypeUtils.isArrayType(type)) {
            PsiType psiType = ((PsiArrayType) type).getComponentType();
            LooksData item = getTypeData(dataPath, dataKey, psiType, classNameMap);
            return LooksData.array(dataKey, item);
        }
        //list
        if (PsiTypeUtils.isCollectionType(type)) {
            PsiType psiType = PsiUtil.extractIterableTypeParameter(type, false);
            LooksData item = getTypeData(dataPath, dataKey, psiType, classNameMap);
            return LooksData.array(dataKey, item);
        }

        if (PsiTypeUtils.isFloatOrDouble(type)) {
            return LooksData.float0(dataKey);
        }
        if (PsiTypeUtils.isNumber(type)) {
            return LooksData.int0(dataKey);
        }

        if (PsiTypeUtils.isString(type)) {
            return LooksData.string0(dataKey);
        }
        if (PsiTypeUtils.isBoolean(type)) {
            return LooksData.boolean0(dataKey);
        }
        if (PsiTypeUtils.isDate(type)) {
            return LooksData.date0(dataKey);
        }


        //泛类型
//        if (genericTypeMap != null && genericTypeMap.get(canonicalText) != null) {
//            return getResponseType(name, genericTypeMap.get(canonicalText), genericTypeMap);
//        }
        PsiUtil.substituteTypeParameter(type, JAVA_UTIL_LIST, 0, true);
        //剩下的都是Object类型
        if (type instanceof PsiClassType) {
            PsiClassType clazz = (PsiClassType) type;
            //在解析类之前判断是否已经解析过，如果解析过，就存放路径，不再解析，防止递归导致堆栈溢出
            if(classNameMap.get(clazz.getClassName()) != null){
                return LooksData.reference(dataKey, classNameMap.get(clazz.getClassName()));
            }
            else{
                classNameMap.put(clazz.getClassName(), dataPath);
            }
//            ((PsiClassType) type).rawType();
//            ((PsiClassReferenceType)((PsiClassReferenceType) type).getParameters()[0]).getParameters()
// .getParameters()
            PsiSubstitutor substitutor = ((PsiClassType) type).resolveGenerics().getSubstitutor();

//            ((PsiClassReferenceType) type).resolveGenerics().getSubstitutor().substitute(((PsiClassReferenceType)((PsiClassReferenceType) type).resolveGenerics().getParameters()[0]))
//            TypeConversionUtil.erasure(((PsiClassReferenceType)type).resolve().getAllFields()[3].getType(), ((PsiClassReferenceType)type).resolveGenerics().getSubstitutor());
//            Map<String, PsiType> map = genericTypeMap(clazz, genericTypeMap);
            //泛类型处理

            PsiClass resolved = clazz.resolve();
            assert resolved != null;
            @NotNull PsiField[] allFields = resolved.getAllFields();
            List<LooksData> children = new ArrayList<>();
            LooksData response = LooksData.object0(dataKey, children);
            for (PsiField field : allFields) {
                if (!canSerialize(field)) {
                    continue;
                }
                LooksData fieldResponse = getTypeData(getPath(dataPath, field.getName()), field.getName(), substitutor.substitute(field.getType()), classNameMap);
                if (fieldResponse != null && !LooksData.isEmpty(fieldResponse)) {
                    children.add(fieldResponse);
                }
            }
            return response;
        }

        throw new UnsupportedOperationException("Not support type:" + type.getCanonicalText());
    }

    private static String getPath(String parentPath, String dataKey){
        return parentPath + "." + dataKey;
    }

    public static boolean canSerialize(PsiField field) {
        //静态字段不处理
        if (PsiJavaUtils.isStaticField(field)) {
            return false;
        }
        if (PsiJavaUtils.isPublicField(field)) {
            return true;
        }
        if (PsiJavaUtils.hasAnnotations(field.getContainingClass(), LombokAnnotation.DATA, LombokAnnotation.GETTER)) {
            return true;
        }
        if (PsiJavaUtils.hasAnnotations(field, LombokAnnotation.DATA, LombokAnnotation.GETTER)) {
            return true;
        }
        //如果不是public的，并且没有对应的get方法，则不处理
        String getMethodName = PsiJavaUtils.toGetPrefix(field.getName());
        Optional<PsiMethod> psiMethod = PsiJavaUtils.findPsiMethod(Objects.requireNonNull(field.getContainingClass()), getMethodName);
        return psiMethod.isPresent();
    }

    public static Map<String, PsiType> genericTypeMap(PsiClassType clazz, Map<String, PsiType> parentTypeMap) {
        @NotNull PsiTypeParameter[] typeParameters = clazz.resolve().getTypeParameters();
        @NotNull PsiType[] parameters = clazz.getParameters();
        if (parameters.length == 0) {
            return null;
        }
        Map<String, PsiType> map = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            PsiType type = parameters[i];
            String canonicalText = parameters[i].getCanonicalText();
            if (parentTypeMap != null && parentTypeMap.containsKey(canonicalText)) {
                type = parentTypeMap.get(canonicalText);
            } else {
                //type
//                type.get
            }
            map.put(typeParameters[i].getText(), type);
        }
        return map;
    }

    public static Map<String, PsiType> genericTypeMap(PsiSubstitutor psiSubstitutor) {
        Map<String, PsiType> map = new HashMap<>();
        for (Map.Entry<PsiTypeParameter, PsiType> entry : psiSubstitutor.getSubstitutionMap().entrySet()) {
            //((PsiCorrectedClassType)((AbstractMap.SimpleImmutableEntry)((com.intellij.util.containers.UnmodifiableHashMap)psiSubstitutor.getSubstitutionMap()).entrySet().toArray()[1]).getValue()).myDelegate
            map.put(entry.getKey().getText(), entry.getValue());
        }
        return map;
    }

    public static String getQualifiedName(PsiMethod psiMethod, PsiClass psiClass) {
        return psiClass.getQualifiedName() + "#" + psiMethod.getName();
    }
}
