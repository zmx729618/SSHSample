package org.nercita.core.test.groups;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现TestNG Groups分组执行用例功能的annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.TYPE })
@Documented
public @interface Groups {
	/**
	 * 执行所有组别的测试.
	 */
	public static final String ALL = "all";

	/**
	 * 组别定义,默认为ALL.
	 */
	public String value() default ALL;
}
