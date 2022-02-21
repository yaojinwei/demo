package com.yaojinwei.study.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.junit.Test;

/**
 *
 * https://baijiahao.baidu.com/s?id=1636557218432721275&wfr=spider&for=pc
 *
 * https://my.oschina.net/wangmengjun/blog/1588096
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class HessianTest {

    /**
     * transient 属性不会序列化
     * @throws IOException
     */
    @Test
    public void testTransient() throws IOException {
        Student stu = new Student();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(stu);
        output.close();

        Student.hobby = "drink";

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        Student student1 = (Student) input.readObject();

        System.out.println(student1.getAddress());
        System.out.println(student1.getName());
        //System.out.println(stu.);
    }


    /**
     * transient 属性不会序列化
     * @throws IOException
     */
    @Test
    public void testSerializable() throws IOException {
        Student1 stu = new Student1();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(stu);
        output.close();

        Student1.hobby = "drink";

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        Student1 student1 = (Student1) input.readObject();

        System.out.println(student1.getAddress());
        System.out.println(student1.getName());
        //System.out.println(stu.);
    }

    /**
     * serializable 方法不起作用
     * @throws IOException
     */
    @Test
    public void testExternalizable() throws IOException {
        Student2 stu = new Student2();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(stu);
        output.close();

        Student2.hobby = "drink";

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        Student2 student1 = (Student2) input.readObject();

        System.out.println(student1.getAddress());
        System.out.println(student1.getName());
        //System.out.println(stu.);
    }

    /**
     * 测试集成的属性，同名属性会被赋值为null
     * https://www.jianshu.com/p/6a36dd1fcca8
     * @throws IOException
     */
    @Test
    public void testExtends() throws IOException {
        Teacher stu = new Teacher();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(stu);
        output.close();

        Teacher.hobby = "drink";

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        Teacher student1 = (Teacher) input.readObject();

        System.out.println(student1.getAddress());
        System.out.println(student1.getName());
    }

}
