package andriiK.ToDoList.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {
    @Before("execution(* andriiK.ToDoList.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp){
        System.out.println("Invocation of method " + jp.getSignature());
    }

}
