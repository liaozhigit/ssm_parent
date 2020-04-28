package cn.liaozhi.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author liaozhi
 * @Date: 2020/4/17 23:22
 * @Version 1.0
 */
@Aspect
@Component
public class CheckAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Around("cn.liaozhi.aop.CheckAspect.pointcut3()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        //这里可以获取到get请求的参数和其他信息
        logger.info("请求开始 uri: {}, params: {}", uri,queryString);
        logger.info("请求开始, 各个参数, url:{}, method: {}",url, method);
        //重点 这里就是获取@RequestBody参数的关键  调试的情况下 可以看到o变量已经获取到了请求的参数
        Object[] o = pjp.getArgs();
        String method2 = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getName();
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        logger.info("func<doAround> method<" + className + "." + method2 + "> cost time <"
                + (System.currentTimeMillis() - begin) + ">ms");
        return result;
    }

    @Before(value="pointcut3()")
    public void before(JoinPoint joinPoint) {
        System.out.println("---------------------------joinPoint");

    }


    //@Pointcut("execution(* cn.liaozhi.controller..*.*(..))")
    //@Pointcut(" execution (* cn.liaozhi..impl.*.*(..))")
   @Pointcut(" execution (* org.codehaus.jackson.map.*.*(..))")
    private void pointcut3(){}
}
