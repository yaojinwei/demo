package com.yaojinwei.study.tbhessian;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Getter
@Setter
public class Teacher {
    private Student student;
    private Student student1;
    private List<Student> studentList;
}
