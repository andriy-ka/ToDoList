package andriy.todolist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class AspectLog {

    final Logger logger = Logger.getLogger(AspectLog.class);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Before("execution(* andriy.todolist.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        logger.debug(" Invocation of method " + jp.getSignature());
//        System.out.println(dtf.format(now) + " Invocation of method " + jp.getSignature());
    }

}
