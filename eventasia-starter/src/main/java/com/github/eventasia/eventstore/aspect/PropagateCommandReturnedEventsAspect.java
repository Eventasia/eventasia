package com.github.eventasia.eventstore.aspect;

import com.github.eventasia.eventstore.command.AggregateCommandHandler;
import com.github.eventasia.eventstore.event.EventPublisher;
import com.github.eventasia.eventstore.event.EventasiaMessage;
import com.github.eventasia.eventstore.util.MethodReflectionUtil;
import com.github.eventasia.framework.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PropagateCommandReturnedEventsAspect {

    private static final Class<?> ALLOWED_RETURN_TYPE = Event.class;

    private final EventPublisher publisher;

    private Log log = LogFactory.getLog(PropagateCommandReturnedEventsAspect.class);

    @Autowired
    public PropagateCommandReturnedEventsAspect(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @Around("execution(@com.github.eventasia.eventstore.command.AggregateCommandHandler * *.*(..)) && @annotation(ann)")
    public Object process(ProceedingJoinPoint joinPoint, AggregateCommandHandler ann) throws Throwable {
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        log.debug("m=process, returnType=" + returnType);

        if (!hasAssignableReturnType(joinPoint)) {
            throw new HandlerSignatureException("Invalid return Type", returnType, ALLOWED_RETURN_TYPE, joinPoint.getSignature());
        }

        Object result = joinPoint.proceed(joinPoint.getArgs());

        if (result == null) {
            return null;
        } else if (Event.class.isAssignableFrom(result.getClass())) {
            publish((Event) result);
        } else if (Iterable.class.isAssignableFrom(result.getClass())) {
            for (Event event : (Iterable<Event>) result) {
                publish(event);
            }
        } else if (result.getClass().isArray()) {
            for (Event event : (Event[]) result) {
                publish(event);
            }
        }

        return result;
    }

    private boolean hasAssignableReturnType(ProceedingJoinPoint joinPoint) {

        Class<?> type = ((MethodSignature) joinPoint.getSignature()).getReturnType();

        if (type.isAssignableFrom(void.class)) {
            return true;
        } else if (type.isAssignableFrom(ALLOWED_RETURN_TYPE)) {
            return true;
        } else if (Iterable.class.isAssignableFrom(type)) {
            return ALLOWED_RETURN_TYPE.isAssignableFrom(getParameterizedType(joinPoint));
        } else if (type.isArray()) {
            return ALLOWED_RETURN_TYPE.isAssignableFrom(getGenericArrayType(joinPoint));
        }

        log.debug("m=hasAssignableReturnType, assignable=false, type='" + type + "'");
        return false;
    }

    private void publish(Event event) {
        log.info("Propagate m=publish, event='" + event + "'");
        publisher.publishEvent(new EventasiaMessage(event));
    }

    private Class getParameterizedType(ProceedingJoinPoint joinPoint) {
        return MethodReflectionUtil.getReturnParameterizedType(getJoinPointMethod(joinPoint));
    }

    private Class getGenericArrayType(ProceedingJoinPoint joinPoint) {
        return MethodReflectionUtil.getReturnArrayType(getJoinPointMethod(joinPoint));
    }

    private Method getJoinPointMethod(ProceedingJoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }
}
