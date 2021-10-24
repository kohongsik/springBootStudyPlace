package com.hs.springboot.study.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 기능이 실행될 시점 .. 어드바이스 정의
    // execution : 포인트컷 표현식으로 적용할 메서드를 명시할때 사용.
    @Around("execution(* board..controller.*Controller.*(..)) || execution(* board..service.*Impl.*(..)) || execution(* board..mapper.*Mapper.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();
        if (name.indexOf("Controller") > -1) {
            type = "Controller \t: ";
        } else if (name.indexOf("Service") > -1) {
            type = "ServiceImpl \t: ";
        } else if (name.indexOf("Mapper") > -1) {
            type = "Mapper \t\t: ";
        }
        logger.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }
}
