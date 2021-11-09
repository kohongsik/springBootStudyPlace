package com.hs.springboot.study.interceptor;

import com.hs.springboot.study.common.exceptionHandler.UnauthorizedException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class TestInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger("board");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("Test http request handler start ... ");
        String headerUserId = request.getHeader("X-USER-ID");
        // check if he is user in this service.
        final List<String> adminIdList = Arrays.asList("ADMIN", "SPRING_BOOT", "STUDY");
        boolean isAdmin = adminIdList.stream().filter(adminId -> adminId.equals(headerUserId)).count() > 0;
        logger.debug("is admin", isAdmin);
        if (!isAdmin) {
            // 403 error.
            throw new UnauthorizedException("he or she is not admin");
        }
        return super.preHandle(request, response, handler);
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler, ModelAndView modelAndView) throws Exception {
        logger.debug("=========================END===================");
    }
}
