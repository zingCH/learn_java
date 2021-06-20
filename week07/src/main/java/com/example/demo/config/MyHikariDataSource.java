/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.config<br/>
 * <b>文件名：</b>MyHikariDataSource.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.config;

import com.example.demo.constant.DbType;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>类  名：</b>MyHikariDataSource<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */

public class MyHikariDataSource  extends AbstractDataSource {

    private static Map<String, HikariDataSource> dataSourceMap = new HashMap<>();

    @Override
    public Connection getConnection() throws SQLException {
        return dataSourceMap.get(DataSourceHolder.getDbType()).getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSourceMap.get(DataSourceHolder.getDbType()).getConnection(username, password);
    }

    public void registerDataSource(String key, HikariDataSource value) {
        dataSourceMap.put(key, value);
    }
}
