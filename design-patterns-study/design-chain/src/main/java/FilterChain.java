import web.Request;
import web.Response;

import java.io.IOException;

/**
 * @author Yao.Jinwei
 * @date 2016/10/14 17:45
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public interface FilterChain {
    public FilterChain addFilter(Filter filter);
    public void doFilter ( Request request, Response response);
}
