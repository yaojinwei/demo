import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 策略模式
 * 定义一系列的算法,把每一个算法封装起来, 并且使它们可相互替换。
 * 当存在以下情况时使用Strategy模式
 *   1）• 许多相关的类仅仅是行为有异。 “策略”提供了一种用多个行为中的一个行为来配置一个类的方法。即一个系统需要动态地在几种算法中选择一种。
 *   2）• 需要使用一个算法的不同变体。例如，你可能会定义一些反映不同的空间 /时间权衡的算法。当这些变体实现为一个算法的类层次时 ,可以使用策略模式。
 *   3）• 算法使用客户不应该知道的数据。可使用策略模式以避免暴露复杂的、与算法相关的数据结构。
 *   4）• 一个类定义了多种行为 , 并且这些行为在这个类的操作中以多个条件语句的形式出现。将相关的条件分支移入它们各自的Strategy类中以代替这些条件语句。
 * 缺点
 *   1)客户端必须知道所有的策略类，并自行决定使用哪一个策略类:  本模式有一个潜在的缺点，就是一个客户要选择一个合适的Strategy就必须知道这些Strategy到底有何不同。此时可能不得不向客户暴露具体的实现问题。因此仅当这些不同行为变体与客户相关的行为时 , 才需要使用Strategy模式。
 * @author Yao.Jinwei
 * @date 2016/10/12 18:29
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {
        TravelStrategy travelStrategy = new TrainStrategy();
        Person person = new Person(travelStrategy);
        person.travel();

        List list = new ArrayList();
        for(int i=0;i<100;i++){
            list.add(i);
        }

        //设置按从左到右顺序比较大小
       Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1 = o1.toString();
                String str2 = o2.toString();
                int len1 = str1.length();
                int len2 = str2.length();

                for(int i= 0 ;i< Math.max(len1, len2);i++){
                    int a = 0;
                    int b = 0;
                    if(i<len1 ){
                        a = Integer.parseInt(str1.substring(i, i + 1));
                    }
                    if(i<len2){
                        b = Integer.parseInt(str2.substring(i, i + 1));
                    }
                    if(a>b){
                        return 1;
                    }
                    else if(a<b){
                        return -1;
                    }
                }
                return 0;
            }
        });

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
