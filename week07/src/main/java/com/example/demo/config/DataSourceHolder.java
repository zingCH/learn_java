/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.config<br/>
 * <b>文件名：</b>DataSourceHolder.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.config;

import com.example.demo.constant.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>类  名：</b>DataSourceHolder<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class DataSourceHolder {

    public static ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType){
        dataSourceHolder.set(dbType.getDbType());
    }

    public static String getDbType(){
        String dbType = dataSourceHolder.get();
        if(StringUtils.isNotEmpty(dbType))return dbType;
        return DbType.master.getDbType();
    }

    public static void removeDbType(){
        dataSourceHolder.remove();
    }
}
