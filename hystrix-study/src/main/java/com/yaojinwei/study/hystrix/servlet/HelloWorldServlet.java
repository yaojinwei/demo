package com.yaojinwei.study.hystrix.servlet;

import com.yaojinwei.study.hystrix.HelloWorldHystrixCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yao.Jinwei
 * @date 2017/12/20 17:19
 */
public class HelloWorldServlet extends HttpServlet {
    private static volatile boolean isDestroyed = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isDestroyed) {
            response.sendError(503, "Service has been shut down.");
        } else {
            this.handleRequest(request, response);
        }

    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = new HelloWorldHystrixCommand("HLX").execute();
        response.getWriter().write(result);
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
