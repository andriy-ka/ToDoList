package andriy.todolist.aspect;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {

    private static final Logger LOG = LogManager.getLogger(AspectLog.class);

    @Before("execution(* andriy.todolist.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        LOG.info("Invocation of method " + jp.getSignature());
    }

}
