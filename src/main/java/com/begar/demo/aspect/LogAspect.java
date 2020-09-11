package com.begar.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("within(com.begar.demo.controller.*)")
    public void callAllControllerMethods() {
    }

    @Before("callAllControllerMethods()")
    public void logMappingMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("{} {}, from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
    }

    @Around("callAllControllerMethods()")
    public Object logMappingMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        final long finish = System.currentTimeMillis();

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        Object[] args = proceedingJoinPoint.getArgs();
        log.info("{}.{} - {} :: {} ms", className, methodName, readMethodParams(args), (finish - start));
        return result;
    }

    private List<String> readMethodParams(Object[] params) {
        return Stream.of(params)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
