package com.alihealth.nukes;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiSubstitutor;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class PickApiContext {
    private PsiMethod psiMethod;
    private PsiClass psiClass;
    private PsiSubstitutor substitutor;

    public PsiMethod getPsiMethod() {
        return psiMethod;
    }

    public void setPsiMethod(PsiMethod psiMethod) {
        this.psiMethod = psiMethod;
    }

    public PsiClass getPsiClass() {
        return psiClass;
    }

    public void setPsiClass(PsiClass psiClass) {
        this.psiClass = psiClass;
    }

    public PsiSubstitutor getSubstitutor() {
        return substitutor;
    }

    public void setSubstitutor(PsiSubstitutor substitutor) {
        this.substitutor = substitutor;
    }
}
