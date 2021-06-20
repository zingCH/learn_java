/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.aop<br/>
 * <b>文件名：</b>DataSourceAspect.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.aop;

import com.example.demo.annotation.DataSource;
import com.example.demo.config.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <b>类  名：</b>DataSourceAspect<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceAspect {

    @Pointcut("@annotation(com.example.demo.annotation.DataSource)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataSource annotation = signature.getMethod().getAnnotation(DataSource.class);
        DataSourceHolder.setDbType(annotation.value());
        log.info("切换数据源："+annotation.value().getDbType());
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceHolder.removeDbType();
        }
    }
}
