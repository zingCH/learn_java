/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.dao<br/>
 * <b>文件名：</b>UserDao.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <b>类  名：</b>UserDao<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Repository
public interface UserDao {

    @Select("select username from shop_user where id = #{id}")
    String getUserNameByUserId(int id);
}
