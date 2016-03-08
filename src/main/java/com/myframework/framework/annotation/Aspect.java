package com.myframework.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by liujiayan on 2016/3/8.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
