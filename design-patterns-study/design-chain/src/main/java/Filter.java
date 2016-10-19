import web.Request;
import web.Response;

import java.io.IOException;

/**
 * @author Yao.Jinwei
 * @date 2016/10/14 13:56
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public interface Filter {
    public void doFilter ( Request request, Response response, FilterChain chain );
}
