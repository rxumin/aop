package com.xmr.aop;

import com.xmr.annotation.ServiceSwitch;
import com.xmr.constant.Constant;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ServiceSwitchAOP{

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 定义切点，使用了@ServiceSwitch注解的类或方法都拦截
     */
    @Pointcut("@annotation(com.xmr.annotation.ServiceSwitch)")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        // 获取被代理的方法的参数
        Object[] args = point.getArgs();
        // 获取被代理的对象
        Object target = point.getTarget();
        // 获取通知签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        try {

            // 获取被代理的方法
            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            // 获取方法上的注解
            ServiceSwitch annotation = method.getAnnotation(ServiceSwitch.class);

            // 核心业务逻辑
            if (annotation != null) {

                // 获取注解里面的元素
                String switchKey = annotation.switchKey();
                String switchVal = annotation.switchVal();
                String message = annotation.message();


                // 方法1：存在redis，下面的做法
                // 方法2：放在数据库
                String configVal = redisTemplate.opsForValue().get(Constant.ConfigCode.AI_Service_SWITCH);
                if (switchVal.equals(configVal)) {
                    // 开关打开，则返回提示。
                    // 表示开关关闭，为0
                    return message;
                }
            }

            // 放行
            return point.proceed(args);

        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
