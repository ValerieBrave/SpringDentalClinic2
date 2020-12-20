package by.smelova.dentalclinic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RESTlogger {
    @Pointcut("@annotation(NeedToLog)")
    public void LogREST() {}

    @After("LogREST()")
    public void LogRESTMethod(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("Call method " + methodName + " with args " + methodArgs);

        log.info("Method " + methodName + " returns " + methodArgs.toString());

    }
}
