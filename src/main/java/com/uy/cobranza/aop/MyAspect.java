package com.uy.cobranza.aop;


import com.uy.cobranza.model.Statistics;
import com.uy.cobranza.service.statistics.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@Aspect
public class MyAspect {

    @Autowired
    private StatisticsService statisticsService;

    @Around("execution(* com.uy.cobranza.service.*.*(..))")
    public Object executionTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("executing aspect, intercep method with @Execution {}", methodName);
        long init = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - init;
        log.info("executing log time ===> method {}, time {}", methodName, time);
        Statistics stats = new Statistics();
        stats.setMethodName(methodName);
        stats.setRunTime((int) time);
        stats.setCreationDate(new Date());
        statisticsService.addStatistics(stats);
        return result;
    }


}
