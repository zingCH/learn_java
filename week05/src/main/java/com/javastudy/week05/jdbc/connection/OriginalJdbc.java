/**
 * <b>工程名：</b>week05<br/>
 * <b>包  名：</b>com.javastudy.week05.jdbc.connection<br/>
 * <b>文件名：</b>OriginalJdbc.java<br/>
 * <b>日  期：</b>2021/06/06<br/>
 */
package com.javastudy.week05.jdbc.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.java.Log;

import java.sql.*;

/**
 * <b>类  名：</b>OriginalJdbc<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/06<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Log
public class OriginalJdbc {

    public static void main(String[] args) throws SQLException {
        //jdbcOperateInsert(2,"s2",2,"k2");
        //jdbcOperateQuery(1);
        //jdbcOperateDelete(2,2);
        //jdbcOperateInsertBatch(10);
        jdbcOperateUpdate(6,"s666",6,"k666");
    }

    private static final HikariDataSource ds;

    static {
        HikariConfig conf = new HikariConfig();
        conf.setUsername("root");
        conf.setPassword("123456");
        conf.setJdbcUrl("jdbc:mysql://localhost:3306/javastudy");
        conf.setDriverClassName("com.mysql.jdbc.Driver");
        ds = new HikariDataSource(conf);
    }

    private static Connection getConnection(boolean isHikari){
        try {
            if(isHikari){
                return ds.getConnection();
            }
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javastudy","root","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void jdbcOperateInsertBatch(int batchNum){
        Connection connection = null;
        PreparedStatement p1 = null;
        PreparedStatement p2 = null;
        try{
            String sql1 = "insert IGNORE into klass  values(?,?);";
            String sql2 = "insert ignore into student values(?,?,?);";
            connection = getConnection(true);
            connection.setAutoCommit(false);
            p1 = connection.prepareStatement(sql1);
            for (int i=1;i<=batchNum;i++){
                p1.setInt(1,i);
                p1.setString(2,"k"+i);
                p1.addBatch();
            }
            p2 = connection.prepareStatement(sql2);
            for (int i=1;i<=batchNum;i++){
                p2.setInt(1,i);
                p2.setString(2,"s"+i);
                p2.setInt(3,i);
                p2.addBatch();
            }
            p1.executeBatch();
            p2.executeBatch();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                p2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                p1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void jdbcOperateInsert(int id,String name,int klassId,String klassName){
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try{
            String sql1 = "insert IGNORE into klass  values("+klassId+",'"+klassName+"');";
            String sql2 = "insert ignore into student values(?,?,?);";
            connection = getConnection(true);
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,klassId);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void jdbcOperateUpdate(int id ,String name,int klassId,String klassName) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try{
            String sql1 = "update  klass  set name = '"+klassName+"' where klass_id = "+klassId+";";
            String sql2 = "update  student  set name = ? where id = ?;";
            connection = getConnection(true);
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(2,id);
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void jdbcOperateDelete(int id,int klassId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try{
            String sql1 = "delete from klass  where klass_id = "+klassId+";";
            String sql2 = "delete from  student where id = ?;";
            connection = getConnection(true);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sql1);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void jdbcOperateQuery(int id){
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        try{
            String sql1 = "SELECT * From klass WHERE klass_id = "+id+";";
            String sql2 = "SELECT * FROM student WHERE id  = ?;";
            connection = getConnection(true);
            resultSet = connection.createStatement().executeQuery(sql1);
            if(resultSet!=null){
                while (resultSet.next()){
                    log.info("klass_id : "+resultSet.getInt("klass_id"));
                    log.info("name : "+resultSet.getString("name"));
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,id);
            resultSet1 = preparedStatement.executeQuery();
            if(resultSet1!=null){
                while (resultSet1.next()){
                    log.info("id : "+resultSet1.getInt("id"));
                    log.info("name : "+resultSet1.getString("name"));
                    log.info("klass_id : "+resultSet1.getInt("klass_id"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                resultSet1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
