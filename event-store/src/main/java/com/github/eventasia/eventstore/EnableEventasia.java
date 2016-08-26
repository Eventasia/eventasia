package com.github.eventasia.eventstore;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(value=EventasiaConfig.class)
public @interface EnableEventasia {
}
