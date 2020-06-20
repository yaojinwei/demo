package com.yaojinwei.demo.zipkin.server2;

//import com.github.kristofa.brave.Brave;
//import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
//import com.github.kristofa.brave.SpanCollector;
//import com.github.kristofa.brave.http.DefaultSpanNameProvider;
//import com.github.kristofa.brave.http.HttpSpanCollector;
//import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
//import com.github.kristofa.brave.servlet.BraveServletFilter;
//import okhttp3.OkHttpClient;
//import org.springframework.context.annotation.Bean;
import brave.Tracing;
import brave.http.HttpTracing;
import brave.okhttp3.TracingInterceptor;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 *  
 * TODO zipkin配置 
 * 
 * @author wangzhao (mailto:wangzhao@primeton.com) 
 */  
@Configuration
public class ZipkinConfig {  
      
//    //span（一次请求信息或者一次链路调用）信息收集器
//    @Bean
//    public SpanCollector spanCollector() {
//        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
//                .compressionEnabled(false)// 默认false，span在transport之前是否会被gzipped
//                .connectTimeout(5000)
//                .flushInterval(1)
//                .readTimeout(6000)
//                .build();
//        return HttpSpanCollector.create("http://11.164.62.162:9411", config, new EmptySpanCollectorMetricsHandler());
//    }
//
//    //作为各调用链路，只需要负责将指定格式的数据发送给zipkin
//    @Bean
//    public Brave brave(SpanCollector spanCollector){
//        Brave.Builder builder = new Brave.Builder("service2");//指定serviceName
//        builder.spanCollector(spanCollector);
//        builder.traceSampler(com.github.kristofa.brave.Sampler.create(1));//采集率
//        return builder.build();
//    }
//
//
//    //设置server的（服务端收到请求和服务端完成处理，并将结果发送给客户端）过滤器
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave) {
//        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
//                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
//        return filter;
//    }
//
//    //设置client的（发起请求和获取到服务端返回信息）拦截器
//    @Bean
//    public OkHttpClient okHttpClient(Brave brave){
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
//                        brave.clientRequestInterceptor(),
//                        brave.clientResponseInterceptor(),
//                        new DefaultSpanNameProvider())).build();
//        return httpClient;
//    }
//
//    @Bean
//    Sender sender() {
//        return OkHttpSender.create("http://11.164.62.162:9411/api/v2/spans");
//    }
//
//    @Bean
//    AsyncReporter<Span> spanReporter(Sender sender) {
//        return AsyncReporter.create(sender);
//    }
//
//    @Bean
//    Tracing tracing(AsyncReporter<Span> reporter) {
//        return Tracing.newBuilder()
//                .localServiceName("service2")
//                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
////                .currentTraceContext(ThreadContextCurrentTraceContext.create()) // puts trace IDs into logs
//                .spanReporter(reporter)
//                .build();
//    }
//
//    @Bean
//    HttpTracing httpTracing(Tracing tracing) {
//        return HttpTracing.create(tracing);
//    }
//
////    //设置client的（发起请求和获取到服务端返回信息）拦截器

    @Bean
    public OkHttpClient okHttpClient(HttpTracing httpTracing) {
        return new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(
                        httpTracing.tracing().currentTraceContext()
                                .executorService(new Dispatcher().executorService())
                ))
                .addNetworkInterceptor(TracingInterceptor.create(httpTracing))
                .build();
    }
} 