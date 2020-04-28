package cn.liaozhi.aop;

import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author liaozhi
 * @Date: 2020/4/25 23:58
 * @Version 1.0
 */
@Aspect
@Component
public class JSONAspect {

    public static JSONAspect ajc$perSingletonInstance;

    private static Throwable ajc$initFailureCause;

    static {
        try {
            ajc$postClinit();
        } catch (Throwable localThrowable) {
            ajc$initFailureCause = localThrowable;
        }
    }

    private static void ajc$postClinit() {
        ajc$perSingletonInstance = new JSONAspect();
    }

    public static JSONAspect aspectOf() {
        if (ajc$perSingletonInstance == null)
            throw new NoAspectBoundException("cn.liaozhi.aop.JSONAspect", ajc$initFailureCause);
        return ajc$perSingletonInstance;
    }

    /**
     * attention:
     * Details：切入到第三方jar包对应的具体方法上，同时配置环绕通知
     * @author chhliu
     * 创建时间：2016-7-26 下午5:25:06
     * @param join
     * @param obj
     * @return
     * String
     */
    //@Around("execution(* com.alibaba.fastjson.JSON.toJSONString(java.lang.Object)) && args(obj)")
    @Around("execution(* org.codehaus.jackson.map.ObjectMapper.writeValueAsString(java.lang.Object)) && args(obj)")
    public String parse2String(ProceedingJoinPoint join, Object obj){
        long begin = System.currentTimeMillis();
        System.out.println("parse to String before");
        String str = "";
        try {
            String method2 = join.getSignature().getName();
            str = (String) join.proceed(new Object[]{obj});
            System.out.println("func<JSONAspect> method<" + "." + method2 + "> cost time <"
                    + (System.currentTimeMillis() - begin) + ">ms");
            System.out.println("result:"+str);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("parse to String after");
        return str;
    }
}
