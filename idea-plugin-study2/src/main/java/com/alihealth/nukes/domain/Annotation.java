
package com.alihealth.nukes.domain;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiJvmModifiersOwner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 相关注解
 *
 * @author : zhengrf
 * @date : 2019-06-18
 */
public class Annotation implements Cloneable {

    private final String label;
    private final String qualifiedName;
    private Value value;

    public Annotation(@NotNull String label, @NotNull String qualifiedName) {
        this.label = label;
        this.qualifiedName = qualifiedName;
    }

    public Annotation withValue(@NotNull String value) {
        Annotation copy = this.clone();
        copy.value = new Value(value);
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(label).append("(");
        if (value != null) {
            builder.append(value.toString());
        }
        return builder.append(")").toString();
    }

    @NotNull
    public String getLabel() {
        return label;
    }

    @NotNull
    public String getQualifiedName() {
        return qualifiedName;
    }

    @Nullable
    public Value getValue(PsiJvmModifiersOwner psiParameter) {
        if (psiParameter == null) {
            return null;
        }
        PsiAnnotation annotation = psiParameter.getAnnotation(this.qualifiedName);
        if (annotation != null) {
            String value = AnnotationUtil.getStringAttributeValue(annotation, Value.getName());
            if (value != null) {
                return new Value(value);
            }
        }
        return null;
    }

    @NotNull
    public Value getValue(@NotNull PsiJvmModifiersOwner psiParameter, @NotNull Supplier<String> defaultValue) {
        Value value = getValue(psiParameter);
        return value == null ? new Value(defaultValue.get()) : value;
    }

    @Nullable
    public Value getAttribute(PsiJvmModifiersOwner psiParameter, String attributeName) {
        if (psiParameter == null) {
            return null;
        }
        PsiAnnotation annotation = psiParameter.getAnnotation(this.qualifiedName);
        if (annotation != null) {
            String value = AnnotationUtil.getStringAttributeValue(annotation, attributeName);
            if (value != null) {
                return new Value(value);
            }
        }
        return null;
    }

    @Override
    protected Annotation clone() {
        Annotation annotation;
        try {
            annotation = (Annotation) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return annotation;
    }

    public static class Value {

        private String value;

        private Value(@NotNull String value) {
            this.value = value;
        }

        public static String getName() {
            return "value";
        }

        @NotNull
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "\"" + value + "\"";
        }

    }
}
