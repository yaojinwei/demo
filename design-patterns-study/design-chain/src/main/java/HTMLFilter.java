import web.Request;
import web.Response;

/**
 * @author Yao.Jinwei
 * @date 2016/10/14 18:31
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class HTMLFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.setRequestStr(request.getRequestStr().replace('<','[').replace('>',']') + "----HTMLFilter");
        chain.doFilter(request, response);
        response.setResponseStr(response.getResponseStr() + "----HTMLFilter");
    }
}
