package com.qinglinJiang.proxy;

import com.qinglinJiang.controller.SysLogController;
import com.qinglinJiang.domain.SysLog;
import com.qinglinJiang.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAOP {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Around("execution(* com.qinglinJiang.controller.*.*(..))")
    public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
        //获取访问时间
        Date visitTime = new Date();
        /*获取方法的相关信息  begin*/
        //获取方法的参数
        Object[] args = pjp.getArgs();
        //获取当前运行方法（无参）
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }
        MethodSignature msg = (MethodSignature) signature;
        //获取被代理的类
        Object target = pjp.getTarget();
        //获取全类名
        Class<?> className = target.getClass();
        //获取当前完整方法
        Method currentMethod = className.getMethod(msg.getName(), msg.getParameterTypes());
        //获取当前运行方法的方法名
        String methodName = currentMethod.getName();
        String name = "[类名] " + className + "[方法名] " + methodName;
        /*获取方法的相关信息  end*/
        if (className != SysLogController.class) {

            /*获取方法执行时间 begin*/
            //执行方法
            Object proceed = null;
            try {
                proceed = pjp.proceed(args);
                //获取方法执行后时间
                long start = visitTime.getTime();
                long end = System.currentTimeMillis();

                long time = end - start;
                /*获取方法执行时间 end*/

                /*request获取uri和ip*/
                String ip = request.getRemoteAddr();
                String uri = request.getRequestURI();

                //获取当前执行方法的用户名
                SecurityContext context = SecurityContextHolder.getContext();
                String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
                SysLog sysLog = new SysLog();
                sysLog.setVisitTime(visitTime);
                sysLog.setExecutionTime(time);
                sysLog.setUsername(username);
                sysLog.setIp(ip);
                sysLog.setUrl(uri);
                sysLog.setMethod(name);

                sysLogService.saveLog(sysLog);

                return proceed;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
        return pjp.proceed(args);

    }

}
