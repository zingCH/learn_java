/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.annotation<br/>
 * <b>文件名：</b>DataSource.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.annotation;

import com.example.demo.constant.DbType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>类  名：</b>DataSource<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    DbType value() default DbType.master;
}
