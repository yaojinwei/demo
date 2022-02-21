package com.alihealth.nukes.spring;

import com.alihealth.nukes.domain.Annotation;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public interface SpringAnnotation {
    Annotation CONTROLLER = new Annotation("@Controller", "org.springframework.stereotype.Controller");

    Annotation RESTCONTROLLER = new Annotation("@RestController", "org.springframework.web.bind.annotation.RestController");

    Annotation REQUESTMAPPING = new Annotation("@RequestMapping", "org.springframework.web.bind.annotation.RequestMapping");

    Annotation POSTMAPPING = new Annotation("@PostMapping", "org.springframework.web.bind.annotation.PostMapping");

    Annotation GETMAPPING = new Annotation("@GetMapping", "org.springframework.web.bind.annotation.GetMapping");

    Annotation DELETEMAPPING = new Annotation("@DeleteMapping", "org.springframework.web.bind.annotation.DeleteMapping");

    Annotation PATCHMAPPING = new Annotation("@PatchMapping", "org.springframework.web.bind.annotation.PatchMapping");

    Annotation PUTMAPPING = new Annotation("@PutMapping", "org.springframework.web.bind.annotation.PutMapping");

    Annotation REQUESTBODY = new Annotation("@RequestBody", "org.springframework.web.bind.annotation.RequestBody");

    Annotation REQUESTPARAM = new Annotation("@RequestParam", "org.springframework.web.bind.annotation.RequestParam");

    Annotation REQUESTHEADER = new Annotation("@RequestHeader", "org.springframework.web.bind.annotation.RequestHeader");

    Annotation PATHVARIABLE = new Annotation("@PathVariable", "org.springframework.web.bind.annotation.PathVariable");

    Annotation COOKIEVALUE = new Annotation("@CookieValue", "org.springframework.web.bind.annotation.CookieValue");


}
