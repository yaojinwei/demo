/**
 * 建造者模式
 * 应用场景
 * 1、多构造函数、多可选参数
 * 优点：
 * 1、Person类作为不可变类，所有属性都是final类型的，而不需要区分可选参数
 * 2、builder使用流式接口风格，让客户端代码更易读
 * 参考：
 * http://www.importnew.com/11506.html
 * 总结：
 * 建造者模式在多于几个参数（虽然不是很科学准确，但是我通常把四个参数作为使用建造者模式的一个很好的指示器），
 * 特别是当大部分参数都是可选的时候。你可以让客户端代码在阅读，写和维护方面更容易。另外，你的类可以保持不可变特性，让你的代码更安全。
 * Created by yaojinwei on 2016/9/24.
 */
public class Person {
    private final String id;
    private final String name;
    private final String sex;
    private final String country;
    private final String address;

    private Person(PersonBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.sex = builder.sex;
        this.country = builder.country;
        this.address = builder.address;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class PersonBuilder{
        private final String id;
        private final String name;
        private String sex;
        private String country;
        private String address;
        public PersonBuilder(String id, String name){
            this.id = id;
            this.name = name;
        }

        public PersonBuilder sex(String sex){
            this.sex = sex;
            return this;
        }

        public PersonBuilder country(String country){
            this.country = country;
            return this;
        }

        public PersonBuilder address(String address){
            this.address = address;
            return this;
        }

        public Person build(){
            //return new Person(this);
            //线程安全的检查方式
            Person person = new Person(this);
            if(person.country == null){ //原文是age > 120
                throw new IllegalStateException("country is null");
            }
            return person;
//            //线程不安全的检查方式
//            if(country == null){
//                throw new IllegalStateException("country is null");
//            }
//            return new Person(this);
        }
    }
}
