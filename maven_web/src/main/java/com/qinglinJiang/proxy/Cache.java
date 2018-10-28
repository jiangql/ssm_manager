package com.qinglinJiang.proxy;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qinglinJiang.util.JedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*@Component*/
@Aspect
public class Cache {

    private JSONArray jsonArray;
    @Around("execution(* com.qinglinJiang.service.impl.*.*(..))")
    public Object addCache(ProceedingJoinPoint pjp) {
        try {
            //获取当前运行方法
            Jedis jedis = JedisUtil.getJedis();
            Object[] args = pjp.getArgs();

            //获取方法无参的
            Signature sig = pjp.getSignature();
            MethodSignature msig = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            msig = (MethodSignature) sig;
            Object target = pjp.getTarget();
            //获取方法名
            String name = pjp.getSignature().getName();
            System.out.println(name);
            //获取全限定类名
            Class<?> aClass = target.getClass();

            //获取当前完整方法
            Method currentMethod = aClass.getMethod(msig.getName(), msig.getParameterTypes());


            String key=aClass.getName()+"."+name+Arrays.toString(args);
            System.out.println(key);
            //尝试从缓存中获取json数据
            String jsonStr = jedis.get(key);

            //判断该方法是否带有Coched注解
            if (!currentMethod.isAnnotationPresent(Cached.class)) {
                System.out.println("非查询所有,清除缓存");
                Set<String> keys = jedis.keys(aClass.getName() + "*");
                for (String s : keys) {
                    jedis.del(s);
                }
                return pjp.proceed(args);
            } else {
                if (jsonStr == null || "".equals(jsonStr)) {
                    try {
                        List<Object> students = (List<Object>) pjp.proceed(args);
                        ObjectMapper mapper = new ObjectMapper();
                        jsonStr = mapper.writeValueAsString(students);
                        jedis.set(key, jsonStr);
                        System.out.println("从数据库查询,存入缓存");
                        return students;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                List<Object> list = jsonArray.parseArray(jsonStr, Object.class);
                System.out.println("从缓存中获取...");
                return list;
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
