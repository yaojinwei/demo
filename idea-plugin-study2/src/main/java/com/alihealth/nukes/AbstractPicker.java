package com.alihealth.nukes;

import com.alihealth.nukes.domain.LooksApi;
import com.alihealth.nukes.domain.LooksData;
import com.alihealth.nukes.domain.LooksRequestBody;
import com.alihealth.nukes.domain.LooksRequestParameters;
import com.alihealth.nukes.util.PsiDocUtils;
import com.alihealth.nukes.util.PsiJavaUtils;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiSubstitutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public abstract class AbstractPicker implements Picker{

    private static Logger logger = Logger.getInstance(AbstractPicker.class);


    @Override
    public List<LooksApi> pickApi(PsiClass psiClass) {
        List<LooksApi> looksApiList = new ArrayList<>();
        List<Pair<PsiMethod, PsiSubstitutor>> allMethodsAndTheirSubstitutors = psiClass.getAllMethodsAndTheirSubstitutors();//.getAllMethods();
        for (Pair<PsiMethod, PsiSubstitutor> methodsAndTheirSubstitutors : allMethodsAndTheirSubstitutors) {
            if(match(methodsAndTheirSubstitutors.getFirst())){
//                PsiUtil.resolveClassInClassTypeOnly(type);methodsAndTheirSubstitutors.getFirst().getParameters()[0].getType() methodsAndTheirSubstitutors.getSecond().substitute()
                //methodsAndTheirSubstitutors.getSecond().substitute((PsiType)methodsAndTheirSubstitutors.getFirst().getParameters()[0].getType())
                LooksApi looksApi = pickApi(methodsAndTheirSubstitutors.getFirst(), psiClass, methodsAndTheirSubstitutors.getSecond());
                looksApiList.add(looksApi);
            }
        }
        return looksApiList;
    }

    @Override
    public LooksApi pickApi(PsiMethod method) {
        return pickApi(method, method.getContainingClass(), PsiSubstitutor.EMPTY);
    }

    protected LooksApi pickApi(PsiMethod psiMethod, PsiClass psiClass, PsiSubstitutor substitutor){
//        RequestMappingInfo requestMappingInfo = getMappingForMethod(psiMethod, psiClass);
//        if(requestMappingInfo == null){
//            logger.error("Not found api config! method=" + psiMethod.getName());
//            return null;
//        }
        PickApiContext context = new PickApiContext();
        context.setPsiClass(psiClass);
        context.setPsiMethod(psiMethod);
        context.setSubstitutor(substitutor);
        beforePickApi(context);
        LooksApi api = new LooksApi();
        api.setProject(pickApiProjectName(context));
        api.setName(pickApiName(context));
        api.setDesc(pickApiDesc(context));
        api.setKey(pickApiKey(context));
        api.setUrl(pickApiUrl(context));
        api.setMethod(pickApiMethod(context));

        LooksData response = pickResponseBody(psiMethod, substitutor);
        api.setResponse(response);

        LooksRequestParameters parameters = pickRequestParameters(psiMethod);
        api.setParameters(parameters);

        LooksRequestBody body = pickRequstBody(psiMethod);
        api.setBody(body);

        afterPickApi(context, api);
        return api;
    }

    public String pickApiName(PickApiContext context){
        return PsiDocUtils.findNameFromSwaggerOrDescriptions(context.getPsiMethod());
    }

    public String pickApiDesc(PickApiContext context){
        return PsiDocUtils.findNameFromDescriptions(context.getPsiMethod());
    }

    public String pickApiProjectName(PickApiContext context){
        return context.getPsiClass().getProject().getName();
    }

    protected abstract String pickApiUrl(PickApiContext context);

    public abstract String pickApiKey(PickApiContext context);

    public abstract String pickApiMethod(PickApiContext context);

    protected void beforePickApi(PickApiContext context){

    }

    protected void afterPickApi(PickApiContext context, LooksApi api){

    }

    /**
     * 提取响应参数
     * @param method
     * @return
     */
    public LooksData pickResponseBody(PsiMethod method, PsiSubstitutor substitutor){
        return PsiJavaUtils.getTypeData(substitutor.substitute(method.getReturnType()));
    }

    /**
     * 提取输入参数
     * @param method
     * @return
     */
    protected abstract LooksRequestParameters pickRequestParameters(PsiMethod method);

    protected abstract LooksRequestBody pickRequstBody(PsiMethod method);

}
