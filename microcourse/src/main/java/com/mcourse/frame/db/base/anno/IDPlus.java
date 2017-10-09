package com.mcourse.frame.db.base.anno;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.mcourse.frame.db.base.extend.type.MysqlTypeEnum;

/**
 * @Title 主键ID注解
 * @Description
 *
 * @Created Assassin
 * @DateTime 2017/06/18 23:23:41
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RUNTIME)
public @interface IDPlus {

	String name() default "";

	boolean autoIncrement() default true;

	MysqlTypeEnum type() default MysqlTypeEnum.BIGINT;

}
