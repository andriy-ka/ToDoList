package andriy.todolist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Aspect
public class AspectLog {
    private final Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Before("execution(* andriy.todolist.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        logger.info("Invocation of method " + jp.getSignature());
    }

}
