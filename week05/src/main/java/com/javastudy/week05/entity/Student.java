/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.entity<br/>
 * <b>文件名：</b>Student.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.entity;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <b>类  名：</b>Student<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("st2")//2.注解配置扫描装配bean
public class Student {

    private int id;

    private String name;
}
