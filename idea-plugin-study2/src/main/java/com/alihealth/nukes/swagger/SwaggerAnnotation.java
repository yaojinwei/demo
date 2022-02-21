package com.alihealth.nukes.swagger;

import com.alihealth.nukes.domain.Annotation;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface SwaggerAnnotation {
    Annotation OPERATION = new Annotation("@ApiOperation", "io.swagger.annotations.ApiOperation");

}
