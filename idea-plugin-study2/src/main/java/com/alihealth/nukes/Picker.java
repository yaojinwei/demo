package com.alihealth.nukes;

import com.alihealth.nukes.domain.LooksApi;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;

import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface Picker {

    /**
     * 判断类是否匹配接口格式
     * @param psiClass
     * @return
     */
    boolean match(PsiClass psiClass);
    /**
     * 判断方法是否匹配接口格式
     * @param method
     * @return
     */
    boolean match(PsiMethod method);

    LooksApi pickApi(PsiMethod method);

    List<LooksApi> pickApi(PsiClass psiClass) ;
}
