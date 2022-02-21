package com.alihealth.nukes.lombok;

import com.alihealth.nukes.domain.Annotation;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface LombokAnnotation {
    Annotation GETTER = new Annotation("@Getter", "lombok.Getter");
    Annotation SETTER = new Annotation("@Setter", "lombok.Setter");
    Annotation DATA = new Annotation("@Data", "lombok.Data");
}
