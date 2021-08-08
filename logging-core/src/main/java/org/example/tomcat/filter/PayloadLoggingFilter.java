package org.example.tomcat.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class PayloadLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //
        //Before
        //
        PayloadCachingRequestWrapper wrapper = new PayloadCachingRequestWrapper(req);
        String payload = wrapper.getPayload();

        //
        //Action
        //
        chain.doFilter(wrapper, res);

        //
        //After
        //
        log.info("request payload={}", payload);
    }

}
