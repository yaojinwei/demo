import web.Request;
import web.Response;

/**
 * 责任链模式
 *  本例是一个双向责任链
 * 使用场景
 *   1、有多个对象可以处理一个请求，哪个对象处理该请求运行时刻自动确定
 *   2、想在不明确接收者的情况下，想多个对象提交一个请求
 *   3、可以动态指定一组对象处理请求
 * 优点：
 *   1、降低耦合度
 *   2、职责链可简化对象的相互连接
 *   3、增强了给对象指派职责( Responsbility)的灵活性
 *   4、增加新的请求处理类很方便
 * @author Yao.Jinwei
 * @date 2016/10/13 10:26
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Client {
    public static void main(String[] args) {
        String msg = "大家好:)，<script>，敏感，被就业，java共有23种设计模式";
        Request request = new Request();
        request.setRequestStr(msg);
        Response response = new Response();
        response.setResponseStr("response");

        FilterChain fc = new ApplicationFilterChain();
        fc.addFilter(new HTMLFilter()).addFilter(new SesitiveFilter());

        fc.doFilter(request, response);

        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }
}
