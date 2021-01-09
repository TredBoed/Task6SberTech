package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class Main {
        public static void main(String args[]) {
            Calculator calc = new CalculatorImpl();
            Class myObjectClass = CalculatorImpl.class;

            //ex2
            // printAllMethods(myObjectClass);

            //ex3
            // printGettersSetters(myObjectClass);

            //ex4
            //checkAllStrings(myObjectClass);

            //ex5 cacheProxy
            /**
            Calculator delegate=new CalculatorImpl();
            Calculator calculator= (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                    delegate.getClass().getInterfaces(),
                    new CachedInvocationhandler(delegate));
            run(calculator);
             **/
            //ex6 Metric Proxy
            /**
            Calculator delegate=new CalculatorImpl();
            Calculator calculator= (Calculator)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                    delegate.getClass().getInterfaces(),new PerformanceProxy(delegate));
            run(calculator);
             **/

            //ex7
            TestClass test1=new TestClass();
            test1.setAge(30);
            test1.setName("John");

            TestClass test2=new TestClass();

            BeanUtils bean= new BeanUtils();
            bean.assign(test1,test2);

            System.out.println(test1.getAge()+" "+test1.getName());
            System.out.println(test2.getAge()+" "+test2.getName());
        }

        private  static void run(Calculator calc)
        {
            System.out.println(calc.calcFactorial(1));
            System.out.println(calc.calcFactorial(2));
            System.out.println(calc.calcFactorial(3));
            System.out.println(calc.calcFactorial(3));
            System.out.println(calc.calcFactorial(5));
            System.out.println(calc.calcFactorial(4));
            System.out.println(calc.calcFactorial(5));
            System.out.println(calc.calcFactorial(5));
        }
        public static void checkAllStrings(Class aClass) {
            Field[] fields = aClass.getDeclaredFields();
            for (Field fld : fields) {
                fld.setAccessible(true);
                if (fld.getType().getTypeName() == "java.lang.String") {
                    try {
                        if (fld.getName().equals(fld.get(aClass))) {
                            System.out.println(fld.getName() + " - has a same names\\values");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static void printAllMethods(Class aClass) {
            Method[] methods = aClass.getMethods();
            Method[] methods1 = aClass.getDeclaredMethods();

            for (Method method : methods1) {
                if (!isGetter(method) && !isSetter(method)) System.out.println(method);
            }
            for (Method method : methods) {
                if (!isGetter(method) && !isSetter(method)) System.out.println(method);
            }

        }

        public static void printGettersSetters(Class aClass) {
            Method[] methods = aClass.getMethods();

            for (Method method : methods) {
                if (isGetter(method)) System.out.println("getter: " + method);
                if (isSetter(method)) System.out.println("setter: " + method);
            }
        }

        public static boolean isGetter(Method method) {
            if (!method.getName().startsWith("get")) return false;
            if (method.getParameterTypes().length != 0) return false;
            if (void.class.equals(method.getReturnType())) return false;
            return true;
        }

        public static boolean isSetter(Method method) {
            if (!method.getName().startsWith("set")) return false;
            if (method.getParameterTypes().length != 1) return false;
            return true;
        }
    }
