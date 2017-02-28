package com.ktds.jgu.dao.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// VO에 쓸 예정.
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Types {

	public String alias() default "";
	public boolean requires() default false;
}
