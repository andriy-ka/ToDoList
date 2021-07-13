package andriy.todolist.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {

    final Logger logger = Logger.getLogger(AspectLog.class);

    @Before("execution(* andriy.todolist.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        logger.debug(" Invocation of method " + jp.getSignature());
    }

}
