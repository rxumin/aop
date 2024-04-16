功能：使用自定义注解，再用AOP切面，完成控制接口的开关。

链接：https://blog.csdn.net/Arrogant1234/article/details/137820042?spm=1001.2014.3001.5501

1、Redis存储了这个接口的key，1表示放开，0表示关闭。

下图所示表示放开接口。


![image](https://github.com/rxumin/aop/assets/100500270/700d0192-93be-4035-be3f-dde554d6d170)

2、使用Postman调用接口，成功。

![image](https://github.com/rxumin/aop/assets/100500270/9413d054-6f2a-4785-ac7e-8b528aaf445d)

3、将Redis对应的value改为0（实际开发中是后台管理），在调用一次。


![image](https://github.com/rxumin/aop/assets/100500270/ef60bc32-5033-4ac7-aa08-7a216d147dc2)

