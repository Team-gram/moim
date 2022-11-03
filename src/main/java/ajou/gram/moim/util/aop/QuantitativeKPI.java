package ajou.gram.moim.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class QuantitativeKPI {

    @Around("execution(* ajou.gram.moim.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long elapsed = finish - start;
            System.out.println("ELAPSED TIME : " +joinPoint.toString() + " : " + elapsed + "ms");
        }
    }
}
