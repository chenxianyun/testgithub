package com.cxy.demoaop.comment;

import com.cxy.demoaop.annotation.BatchRedisKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 15:12
 */
@Aspect
@Component
public class TestAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

    private final LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();


    @Pointcut("execution(* com.cxy.demoaop.service..*.*(..))")
    public void point() {
    }

    @Around("point()&&@annotation(batchRedisKey)")
    public Object haha(ProceedingJoinPoint proceedingJoinPoint, BatchRedisKey batchRedisKey) {
        try {
            // 获取方法参数值
            Object[] arguments = proceedingJoinPoint.getArgs();
            // 获取方法
            Method method = getMethod(proceedingJoinPoint);
            // 从注解中获取spel字符串
            String spel = batchRedisKey.value();
            String name = batchRedisKey.fileName().name();
            // 解析spel表达式
            Object result = parseSpel(method, arguments, spel, Object.class, null);
            if (result instanceof Collection) {
                System.out.println("是集合->" + result.toString());
            }
            System.out.println("开始进入了切面方法");
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

    /**
     * 解析 spel 表达式
     *
     * @param method        方法
     * @param arguments     参数
     * @param spel          表达式
     * @param clazz         返回结果的类型
     * @param defaultResult 默认结果
     * @return 执行spel表达式后的结果
     */
    private <T> T parseSpel(Method method, Object[] arguments, String spel, Class<T> clazz, T defaultResult) {
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], arguments[len]);
        }
        try {
            Expression expression = parser.parseExpression(spel);
            return expression.getValue(context, clazz);
        } catch (Exception e) {
            return defaultResult;
        }
    }
}
