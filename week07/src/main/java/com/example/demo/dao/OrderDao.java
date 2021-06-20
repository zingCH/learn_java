/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.dao<br/>
 * <b>文件名：</b>OrderDao.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.dao;

import com.example.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>类  名：</b>OrderDao<br/>
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
public interface OrderDao {

    void delete();

    void insert(OrderEntity orderEntity);

    void insertBatch(@Param("list") List<OrderEntity> list);

    OrderEntity selectByOid(String oid);
}
