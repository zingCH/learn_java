/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.entity<br/>
 * <b>文件名：</b>School.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <b>类  名：</b>School<br/>
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
@Component("sc2")//2.注解配置扫描装配bean
public class School {

    private String schoolName;

    private List<Klass> klasses;
}
