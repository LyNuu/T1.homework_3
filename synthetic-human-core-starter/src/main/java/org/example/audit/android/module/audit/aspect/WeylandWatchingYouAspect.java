package org.example.audit.android.module.audit.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.audit.android.module.WeylandWatchingYou;
import org.example.audit.android.module.audit.AuditLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class WeylandWatchingYouAspect {
    private final ApplicationContext applicationContext;

    @Around("@annotation(watchingYou)")
    public Object log(ProceedingJoinPoint joinPoint, WeylandWatchingYou watchingYou) throws Throwable {
        String loggerName = watchingYou.name();
        AuditLogger auditLogger = (AuditLogger) applicationContext.getBean(loggerName);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();
        auditLogger.log(methodName, args, result);

        return result;
    }
}
