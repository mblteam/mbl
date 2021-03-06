package com.mbl.common.framework.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author HuangSiwei
 * @create 2014年9月20日 上午11:23:20
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FormObj {
	/**
	 * 参数别名
	 * 
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	String value() default "";

	/**
	 * 是否展示, 默认展示
	 * 
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	boolean show() default true;
}