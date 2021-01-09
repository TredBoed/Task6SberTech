package com.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PerformanceProxy implements InvocationHandler {

    private final Map<Object, Object> resultByArg = new HashMap<>();
    private final Object delegate;
    private long startTime =  0;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Metric.class)) {

            return invoke(method, args);
        }
        return null;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            startTime =  System.nanoTime();

            return  method.invoke(delegate,args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible",e);
        }
        catch (InvocationTargetException e) {
            throw e.getCause();
        }
        finally {
            startTime = System.nanoTime() - startTime;
            System.out.println("Время работы метода: "+ startTime+ " наносек");
        }
    }



}
