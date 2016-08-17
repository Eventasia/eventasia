package com.github.eventasia.eventstore.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class MethodReflectionUtil {

    /**
     * If you have a method like:
     * <p>
     * public List<Event> eventList() {...}
     * <p>
     * And you need to, via Reflection, discover dinamically what's the type the List are holding.
     *
     * @param method Reflection API Method
     * @return Class representing the Parameterized return type
     */
    public static Class<?> getReturnParameterizedType(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        if (genericReturnType != null && genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();

            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                return (Class<?>) actualTypeArguments[0];
            }

        }
        return null;
    }


    /**
     * If you have a method like:
     * <p>
     * public Event[] eventArray() {...}
     * <p>
     * And you need to, via Reflection, discover dinamically what's the type the Array are holding.
     *
     * @param method Reflection API Method
     * @return Class representing the Array return type
     */
    public static Class<?> getReturnArrayType(Method method) {
        Class<?> returnType = method.getReturnType();
        return returnType.getComponentType();
    }

}
