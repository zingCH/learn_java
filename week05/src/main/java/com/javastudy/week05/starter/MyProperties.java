/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.starter<br/>
 * <b>文件名：</b>MyProperties.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <b>类  名：</b>MyProperties<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@ConfigurationProperties(prefix = "my-starter")
public class MyProperties {

    private String para1 = "111";

    private int para2 = 111;

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public int getPara2() {
        return para2;
    }

    public void setPara2(int para2) {
        this.para2 = para2;
    }
}
