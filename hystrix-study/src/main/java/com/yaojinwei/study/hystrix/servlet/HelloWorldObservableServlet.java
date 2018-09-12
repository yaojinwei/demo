package com.yaojinwei.study.hystrix.servlet;

import com.yaojinwei.study.hystrix.HelloWorldHystrixCommand;
import com.yaojinwei.study.hystrix.HelloWorldHystrixObservableCommand;
import rx.functions.Action1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yao.Jinwei
 * @date 2017/12/20 17:19
 */
public class HelloWorldObservableServlet extends HttpServlet {
    private static volatile boolean isDestroyed = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isDestroyed) {
            response.sendError(503, "Service has been shut down.");
        } else {
            this.handleRequest(request, response);
        }

    }

    private void handleRequest(HttpServletRequest request,  final HttpServletResponse response) throws ServletException, IOException {
        List<Integer> numbers = new LinkedList<>() ;
        numbers.add(1);
        numbers.add(3);
        numbers.add(5);

        new HelloWorldHystrixObservableCommand(numbers).observe().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                try {
                    response.getWriter().write(integer + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        response.getWriter().flush();
    }

    public void init() throws ServletException {
        isDestroyed = false;
    }

    public void destroy() {
        isDestroyed = true;
        super.destroy();
    }
}
