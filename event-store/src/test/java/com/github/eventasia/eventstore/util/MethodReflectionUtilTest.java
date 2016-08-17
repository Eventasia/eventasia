package com.github.eventasia.eventstore.util;

import com.github.eventasia.framework.Event;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class MethodReflectionUtilTest {

    @Test
    public void getReturnParameterizedClass() throws Exception {
        Class<?> actual = MethodReflectionUtil.getReturnParameterizedType(MethodFixture.class.getDeclaredMethod("eventList"));

        Assert.assertEquals(Event.class, actual);
    }

    @Test
    public void getReturnParameterizedClass_withoutType() throws Exception {
        Class<?> actual = MethodReflectionUtil.getReturnParameterizedType(MethodFixture.class.getDeclaredMethod("eventListWithoutType"));

        Assert.assertNull(actual);
    }

    @Test
    public void getReturnArrayType() throws Exception {
        Class<?> actual = MethodReflectionUtil.getReturnArrayType(MethodFixture.class.getDeclaredMethod("eventArray"));

        Assert.assertEquals(Event.class, actual);
    }

    public static class MethodFixture {
        public List<Event> eventList() {
            return null;
        }

        public List eventListWithoutType() {
            return null;
        }

        public Event[] eventArray() {
            return null;
        }
    }

}