/**
 * Created by yaojinwei on 2016/9/24.
 */
public class Client {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder("1","Jhon").build();
        System.out.println(person);
    }
}
