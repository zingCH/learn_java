/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.config<br/>
 * <b>文件名：</b>EntityConfiguration.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.config;

import com.javastudy.week05.entity.Klass;
import com.javastudy.week05.entity.School;
import com.javastudy.week05.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>类  名：</b>EntityConfiguration<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Configuration//3.java配置类方式装配bean
public class EntityConfiguration {

    @Bean("st3")
    public Student getStudent(){
        return new Student();
    }

    @Bean("kl3")
    public Klass getKlass(){
        return new Klass();
    }

    @Bean("sc3")
    public School getSchool(){
        return new School();
    }
}
