/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.config<br/>
 * <b>文件名：</b>DataSourceInfo.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.config;

import lombok.Data;

/**
 * <b>类  名：</b>DataSourceInfo<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Data
public class DataSourceInfo {
    private String url;
    private String username;
    private String password;
}
