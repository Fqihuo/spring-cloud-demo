package com.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyFallBack implements FallbackProvider {
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else{
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status){
        return new ClientHttpResponse(){
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //返回一个HttpStatus对象 这个对象是个枚举对象，里面包含了一个statuscode和reasonPhrase信息
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //返回status的code 比如 404，500等
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                //返回一个HttpStatus对象的reasonPhrase信息
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
                //close的时候调用的方法， 讲白了就是当降级信息全部响应完了之后调用的方法
                System.out.println("MyFallBack-------done");
            }

            @Override
            public InputStream getBody() throws IOException {
                //把降级信息响应回前端
                return new ByteArrayInputStream("降级信息".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                //需要对响应报头设置的话可以在此设置
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

    @Override
    public String getRoute() {
        //配置回退的微服务
        return "*";
    }
}
