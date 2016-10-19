import web.Request;
import web.Response;

/**
 * @author Yao.Jinwei
 * @date 2016/10/14 18:09
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class ApplicationFilterChain implements FilterChain {
    private int pos = 0;
    private int n = 0;
    private Filter[] filters = new Filter[0];
    public static final int INCREMENT = 10;

    @Override
    public FilterChain addFilter(Filter filter){
        if (n == filters.length) {
            Filter[] newFilters =
            new Filter[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
            }
        filters[n++] = filter;
        return this;
    }

    @Override
    public void doFilter(Request request, Response response) {
        if(pos < n){
            Filter filter = filters[pos++];
            filter.doFilter(request, response, this);
        }
    }
}
