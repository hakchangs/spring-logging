package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@Slf4j
@Component
public class PayloadLoggingPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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
        String reqBody = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));

        log.info("request payload={}", reqBody);

        return null;
    }
}
