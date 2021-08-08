package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import org.example.tomcat.filter.PayloadCachingRequestWrapper;
import org.springframework.core.Ordered;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

//@Component
public class PayloadWrappingPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        PayloadCachingRequestWrapper requestWrapper = new PayloadCachingRequestWrapper(request);

        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));

        context.setRequest(requestWrapper);

        return null;
    }
}
