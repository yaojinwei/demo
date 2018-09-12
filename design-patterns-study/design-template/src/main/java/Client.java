/**
 * @author Yao.Jinwei
 * @date 2016/10/12 17:49
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {
        Benchmark benchmark = new MethodBenchMark();
        String  str1 = new String("123");
        System.out.println(str1);
        String str2 = new String("123");
        System.out.println(str2);
        benchmark.benchmark();
    }
}
