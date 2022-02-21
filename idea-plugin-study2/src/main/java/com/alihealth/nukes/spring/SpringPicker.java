package com.alihealth.nukes.spring;

import com.alihealth.nukes.AbstractPicker;
import com.alihealth.nukes.PickApiContext;
import com.alihealth.nukes.domain.LooksApi;
import com.alihealth.nukes.domain.LooksRequestBody;
import com.alihealth.nukes.domain.LooksRequestParameters;
import com.alihealth.nukes.domain.LooksData;
import com.alihealth.nukes.util.PsiJavaUtils;
import com.intellij.lang.jvm.JvmClass;
import com.intellij.lang.jvm.JvmMethod;
import com.intellij.lang.jvm.annotation.JvmAnnotationAttribute;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringValueResolver;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.util.UrlPathHelper;



import static com.alihealth.nukes.spring.SpringAnnotation.REQUESTBODY;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class SpringPicker extends AbstractPicker {

    private static Logger logger = Logger.getInstance(AbstractPicker.class);
    private Map<String, RequestMappingInfo> methodRequestMapping = new HashMap<>();
    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    private PathMatcher pathMatcher = new AntPathMatcher();
    private boolean useSuffixPatternMatch = true;
    private boolean useRegisteredSuffixPatternMatch = false;
    private boolean useTrailingSlashMatch = true;
    private ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();
    private StringValueResolver embeddedValueResolver;


    public SpringPicker() {
        this.config = new RequestMappingInfo.BuilderConfiguration();
        this.config.setUrlPathHelper(this.urlPathHelper);
        this.config.setPathMatcher(this.pathMatcher);
        this.config.setSuffixPatternMatch(this.useSuffixPatternMatch);
        this.config.setTrailingSlashMatch(this.useTrailingSlashMatch);
        this.config.setRegisteredSuffixPatternMatch(this.useRegisteredSuffixPatternMatch);
        this.config.setContentNegotiationManager(this.contentNegotiationManager);
    }

    @Override
    public boolean match(PsiClass psiClass) {
        return PsiJavaUtils.hasAnnotations(psiClass, SpringAnnotation.CONTROLLER, SpringAnnotation.RESTCONTROLLER);
    }

    @Override
    public boolean match(PsiMethod method) {
        return PsiJavaUtils.hasAnnotations(method, SpringAnnotation.REQUESTMAPPING
                , SpringAnnotation.POSTMAPPING
                , SpringAnnotation.GETMAPPING
                , SpringAnnotation.DELETEMAPPING
                , SpringAnnotation.PATCHMAPPING
                , SpringAnnotation.PUTMAPPING
        );
    }

    @Override
    public LooksRequestParameters pickRequestParameters(PsiMethod method) {
        LooksRequestParameters params = new LooksRequestParameters();
        for (PsiParameter parameter : method.getParameterList().getParameters()) {
            if (!PsiJavaUtils.hasAnnotation(parameter, REQUESTBODY)) {
                LooksData typeData = PsiJavaUtils.getTypeData(parameter.getName(), parameter.getType());
                params.add(typeData);
            }
        }
        return params;
    }

    @Override
    public LooksRequestBody pickRequstBody(PsiMethod method) {
        LooksRequestBody body = LooksRequestBody.json();
        for (PsiParameter parameter : method.getParameterList().getParameters()) {
            if (PsiJavaUtils.hasAnnotation(parameter, REQUESTBODY)) {
                LooksData bodyData = PsiJavaUtils.getTypeData( parameter.getType());
                if (bodyData.getChildren().size() > 0) {
                    body.addAll(bodyData.getChildren());
                }
            }
        }
        return body;
    }

    @Override
    protected void beforePickApi(PickApiContext context) {
        super.beforePickApi(context);
        methodRequestMapping.remove(getMethodKey(context.getPsiMethod(), context.getPsiClass()));
    }

    @Override
    protected void afterPickApi(PickApiContext context, LooksApi api) {
        super.afterPickApi(context, api);
        methodRequestMapping.remove(getMethodKey(context.getPsiMethod(), context.getPsiClass()));
    }

    @Override
    protected String pickApiUrl(PickApiContext context) {
        RequestMappingInfo requestMappingInfo = getMappingForMethod(context.getPsiMethod(), context.getPsiClass());
        Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
        if (patterns.size() == 0) {
            throw new IllegalArgumentException("patterns size==0");
        }
        for (String pattern : patterns) {
            return pattern;
        }
        return null;
    }

    @Override
    public String pickApiKey(PickApiContext context) {
        RequestMappingInfo mappingForMethod = getMappingForMethod(context.getPsiMethod(), context.getPsiClass());
        if(mappingForMethod == null){
            throw new IllegalArgumentException("无法识别的接口信息");
        }
        return mappingForMethod.getName();
    }

    @Override
    public String pickApiMethod(PickApiContext context) {
        RequestMappingInfo requestMappingInfo = getMappingForMethod(context.getPsiMethod(), context.getPsiClass());
        Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
        if (methods.size() > 0) {
            for (RequestMethod method : methods) {
                return method.name();
            }
        }
        return null;
    }

    private String getMethodKey(PsiMethod method, PsiClass handlerType){
        return PsiJavaUtils.getQualifiedName(method, handlerType);
    }

    protected RequestMappingInfo getMappingForMethod(PsiMethod method, PsiClass handlerType) {
        if(methodRequestMapping.get(getMethodKey(method, handlerType)) != null){
            return methodRequestMapping.get(getMethodKey(method, handlerType));
        }
        RequestMappingInfo info = this.createRequestMappingInfo(method);
        if (info != null) {

            RequestMappingInfo typeInfo = this.createRequestMappingInfo(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }
        }
        return info;
    }


    //主要获得Getter方法或者是字段的JPA annotation信息
    private RequestMappingInfo createRequestMappingInfo(PsiElement element) {
        PsiAnnotation[] annotations = null;
        if (element instanceof PsiMethod) {
            annotations = ((PsiMethod) element).getAnnotations();
        } else if (element instanceof PsiClass) {
            annotations = ((PsiClass) element).getAnnotations();
        }

        //一开始先忽略字段
        for (PsiAnnotation annotation : annotations) {
            String name = null;
            String[] values = null;
            String[] paths = null;
            RequestMethod[] method = null;
            String[] params = null;
            String[] headers = null;
            String[] consumes = null;
            String[] produces = null;
            if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.PostMapping")) {
                method = new RequestMethod[1];
                method[0] = RequestMethod.POST;
            } else if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.GetMapping")) {
                method = new RequestMethod[1];
                method[0] = RequestMethod.GET;
            } else if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.PutMapping")) {
                method = new RequestMethod[1];
                method[0] = RequestMethod.PUT;
            } else if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.DeleteMapping")) {
                method = new RequestMethod[1];
                method[0] = RequestMethod.DELETE;
            } else if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.PatchMapping")) {
                method = new RequestMethod[1];
                method[0] = RequestMethod.PATCH;
            } else if (annotation.getQualifiedName().equals("org.springframework.web.bind.annotation.RequestMapping")) {
                //分析普通字段 获得字段名

            } else {
                continue;
            }
            //分析普通字段 获得字段名
            for (JvmAnnotationAttribute attr : annotation.getAttributes()) {
                if (attr.getAttributeName().equals("path")) {
                    paths = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                } else if (attr.getAttributeName().equals("value")) {
                    values = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                } else if (attr.getAttributeName().equals("name")) {
                    name = PsiJavaUtils.getAnnotationAttrValueAsString(attr);
                } else if (attr.getAttributeName().equals("method")) {
                    method = PsiJavaUtils.getAnnotationAttrValueAsEnumArray(attr, RequestMethod.class);
                } else if (attr.getAttributeName().equals("params")) {
                    params = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                } else if (attr.getAttributeName().equals("headers")) {
                    headers = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                } else if (attr.getAttributeName().equals("consumes")) {
                    consumes = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                } else if (attr.getAttributeName().equals("produces")) {
                    produces = PsiJavaUtils.getAnnotationAttrValueAsStringArray(attr);
                }
            }
            paths = paths == null ? values : paths;

            if (StringUtil.isEmpty(name)) {
                if (element instanceof JvmClass) {
                    name = ((JvmClass) element).getQualifiedName();
                } else if (element instanceof JvmMethod) {
                    name = ((JvmMethod) element).getName();
                }
            }
            RequestMappingInfo mappingInfo = RequestMappingInfo
                    .paths(paths)
                    .methods(method)
                    .params(params)
                    .headers(headers)
                    .consumes(consumes)
                    .produces(produces)
                    .mappingName(name)
                    .customCondition(null)
                    .options(config).build();
            return mappingInfo;
        }
        return null;
    }

}
