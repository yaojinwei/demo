package com.alihealth.nukes.util;

import com.alihealth.nukes.domain.Annotation;
import com.alihealth.nukes.swagger.SwaggerAnnotation;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.javadoc.PsiDocToken;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class PsiDocUtils {

    public static String findNameFromDescriptions(PsiMethod method) {
        if(method == null || method.getDocComment() == null){
            return null;
        }
        PsiElement[] descriptionElements = method.getDocComment().getDescriptionElements();
        StringBuilder desc = new StringBuilder();
        for (PsiElement descriptionElement : descriptionElements) {
            if (descriptionElement instanceof PsiDocToken) {
                PsiDocToken psiDocToken = (PsiDocToken) descriptionElement;
                desc.append(psiDocToken.getText().trim());
            }
        }
        return desc.toString();
    }

    public static String findNameFromSwagger(PsiMethod method) {
        Annotation.Value value = SwaggerAnnotation.OPERATION.getValue(method);
        return value == null ? null : value.getValue();
    }

    public static String findNameFromSwaggerOrDescriptions(PsiMethod method) {
        String name = findNameFromSwagger(method);
        if (StringUtil.isEmpty(name)) {
            return findNameFromDescriptions(method);
        }
        return name;
    }

}
