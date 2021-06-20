/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.config<br/>
 * <b>文件名：</b>DataSourceConfig.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.config;

import com.example.demo.constant.DbType;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b>类  名：</b>DataSourceConfig<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Profile("test")
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${otherDb.datasource.url}")
    private String urlOtherDb;

    @Value("${otherDb.datasource.username}")
    private String userNameOtherDb;

    @Value("${otherDb.datasource.password}")
    private String passwordOtherDb;

    @Bean("master")
    @Primary
    public MyHikariDataSource dataSource() {
        DataSourceInfo info = new DataSourceInfo();
        info.setUrl(url);
        info.setUsername(userName);
        info.setPassword(password);
        HikariDataSource dataSource = DataSourceProvider.create(info);
        MyHikariDataSource hikariCPDataSource = new MyHikariDataSource();
        hikariCPDataSource.registerDataSource(DbType.master.getDbType(), dataSource);
        return hikariCPDataSource;
    }

    @Bean("other")
    public MyHikariDataSource dataSource2() {
        DataSourceInfo info = new DataSourceInfo();
        info.setUrl(urlOtherDb);
        info.setUsername(userNameOtherDb);
        info.setPassword(passwordOtherDb);
        HikariDataSource dataSource = DataSourceProvider.create(info);
        MyHikariDataSource hikariCPDataSource = new MyHikariDataSource();
        hikariCPDataSource.registerDataSource(DbType.other.getDbType(), dataSource);
        return hikariCPDataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
