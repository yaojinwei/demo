import web.Request;
import web.Response;

/**
 * 关键词过滤
 */
public class SesitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.setRequestStr(request.getRequestStr().replace("被就业","就业").replace("敏感", "") + "----SesitiveFilter");
        chain.doFilter(request, response);
        response.setResponseStr(response.getResponseStr() + "----SesitiveFilter");
    }
}