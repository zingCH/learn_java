/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.config<br/>
 * <b>文件名：</b>TestBean.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.config;

import com.javastudy.week05.starter.MyStarterTest;
import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <b>类  名：</b>TestBean<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Component
@Log
public class TestBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void test(){
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            log.info(beanDefinitionName);
        }
        log.info("-----------------");
        MyStarterTest bean = applicationContext.getBean(MyStarterTest.class);
        if(bean!=null){
            log.info("para1 "+bean.getPara1());
            log.info("para2 "+bean.getPara2());
        }
    }
}
