package com.yaojinwei.study.tbhessian;

import com.taobao.hsf.serialize.hessian2.Hessian2Serializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class TbhessianTest {

    @Test
    public void testSerialize() throws Exception {
        Student stu = new Student();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        Hessian2Serializer serializer = new Hessian2Serializer();
        byte[] serialized = serializer.serialize(stu, null);

        Student deserialize = (Student)serializer.deserialize(serialized, null);
        System.out.println(deserialize.getName());
    }

    @Test
    public void testRefernce() throws Exception {
        Teacher teacher = new Teacher();

        Student stu = new Student();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        teacher.setStudent(stu);
        teacher.setStudent1(stu);

        List<Student> studentList = new ArrayList<Student>();
        studentList.add(stu);
        teacher.setStudentList(studentList);

        Hessian2Serializer serializer = new Hessian2Serializer();
        byte[] serialized = serializer.serialize(teacher, null);

        Teacher deserialize = (Teacher)serializer.deserialize(serialized, null);
        System.out.println(deserialize.getStudent().getName());

        System.out.println(deserialize.getStudent1().getName());

        System.out.println(deserialize.getStudentList().get(0).getName());
    }
}
