package com.hs.springboot.study.common.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("board");
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    public ModelAndView unauthorizedExceptionHandler(HttpServletRequest request, UnauthorizedException exception) {
        ModelAndView mv = new ModelAndView("/error/error_default");
        mv.addObject("exception", exception);
        // error log table < insert
        // error
        logger.debug("unauthorizedExceptionHandler", exception);
        logger.error("exception", exception);
        return mv;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        ModelAndView mv = new ModelAndView("/error/error_default");
        mv.addObject("exception", exception);
        // error log table < insert
        // error
        logger.error("exception", exception);
        return mv;
    }
}
