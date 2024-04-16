package com.xmr.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})       // 作用在方法上
@Retention(RetentionPolicy.RUNTIME)     // 运行时起作用
public @interface ServiceSwitch {

    // 业务开关key
    String switchKey();

    // 开关：0表示关，1表示开，放行
    String switchVal() default "0";

    // 提示信息
    String message() default "该接口关闭！";
}
