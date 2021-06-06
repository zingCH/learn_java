/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.starter<br/>
 * <b>文件名：</b>MyAutoConfiguration.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <b>类  名：</b>MyAutoConfiguration<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Configuration
@ConditionalOnWebApplication
@Import(MyConfiguration.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {

    @Autowired
    private MyProperties myProperties;

    @Autowired
    private MyConfiguration myConfiguration;


    @Bean
    public MyStarterTest getMyStarterTest(){
        return new MyStarterTest(myProperties.getPara1(),myProperties.getPara2());
    }
}
