/**
 * @author Yao.Jinwei
 * @date 2016/10/12 17:53
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class MethodBenchMark extends Benchmark {
    @Override
    public void benchmark() {
        for(int i=0; i< 1000; i++){
            System.out.println("i=" + i);
        }
    }
}
