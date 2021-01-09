package com.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {
    // @param to   Object which properties will be set.
    // @param from Object which properties will be used to get value

    public void assign(Object from, Object to) {
        Class toClass = to.getClass();
        Class fromClass = from.getClass();

        Map<String, ArrayList<Method>> TOgetAset = printGettersSetters(toClass);
        Map<String, ArrayList<Method>> FROMgetAset = printGettersSetters(fromClass);
        ArrayList<ArrayList<Method>> groupedBy =  GroupByTypes(FROMgetAset, TOgetAset);
        MakeAssign(groupedBy, from, to);

    }

    public void MakeAssign(ArrayList groupedBy,Object from,Object to) {
        Boolean flag =true;
        Object copyVal = null;
        Method meth;
        for (Object a: groupedBy) {
            ArrayList<Method> aa=  (ArrayList<Method>)a;
           try {
               Object returnValue = aa.get(0).invoke(from);
               aa.get(1).invoke(to,returnValue);
           }
           catch (IndexOutOfBoundsException e)
           {

           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }
        }
    }

    public ArrayList GroupByTypes(Map to, Map from) {

        ArrayList<Method> getFROMgetters = ((ArrayList<Method>) from.get("Getter"));
        ArrayList<Method> getTOsetters = ((ArrayList<Method>) to.get("Setter"));

        ArrayList<ArrayList<Method>> groupedBy = new ArrayList<>();
        for (Method frm : getFROMgetters) {
            ArrayList<Method> pare = new ArrayList<>();
            for (Method tom : getTOsetters) {
                if (frm.getReturnType().equals(tom.getParameterTypes()[0])) {
                    pare.add(frm);
                    pare.add(tom);
                }
            }
            groupedBy.add(pare);
        }

        return groupedBy;
    }

    public Map printGettersSetters(Class aClass) {
        Method[] methods = aClass.getMethods();
        Map<String, ArrayList<Method>> getAset = new HashMap<>();

        for (Method method : methods) {
            if (isGetter(method)) getAset.computeIfAbsent("Getter", k -> new ArrayList<>()).add(method);
            if (isSetter(method)) getAset.computeIfAbsent("Setter", k -> new ArrayList<>()).add(method);
        }
        return getAset;
    }

    public boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }
}
