/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.service<br/>
 * <b>文件名：</b>OrderService.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.service;

import com.example.demo.annotation.DataSource;
import com.example.demo.constant.DbType;
import com.example.demo.dao.OrderDao;
import com.example.demo.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>类  名：</b>OrderService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @DataSource(DbType.master)
    public void inset10w1(){
        orderDao.delete();
        int i = 100_0000;
        StopWatch stopWatch = new StopWatch("insertOrder");
        stopWatch.start("insertOrder1");
        for (int i1 = 0; i1 < i; i1++) {
            orderDao.insert(OrderEntity.create());
        }
        stopWatch.stop();
        log.info("\r\n{}",stopWatch.prettyPrint());
    }

    @DataSource(DbType.master)
    public void inset10w2(){
        orderDao.delete();
        int i = 100_0000;
        StopWatch stopWatch = new StopWatch("insertOrder");
        stopWatch.start("insertOrder2");
        List<OrderEntity> list = new ArrayList<>(i);
        for (int i1 = 0; i1 < i; i1++) {
            list.add(OrderEntity.create());
        }
        batchInsert(list);
        stopWatch.stop();
        log.info("\r\n{}",stopWatch.prettyPrint());
    }

    @DataSource(DbType.master)
    public void inset10w3(){
        orderDao.delete();
        int ii = 100_0000;
        StopWatch stopWatch = new StopWatch("insertOrder");
        stopWatch.start("insertOrder3");
        List<OrderEntity> list = new ArrayList<>(ii);
        for (int i1 = 0; i1 < ii; i1++) {
            list.add(OrderEntity.create());
        }
        int batchCount = 100000;
        //每批最后一个的下标
        int batchLastIndex = batchCount;
        for (int i = 0; i < list.size(); ) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                orderDao.insertBatch(new ArrayList<>(list.subList(i, batchLastIndex)));//避免直接使用sublist
                break;
            } else {
                orderDao.insertBatch(new ArrayList<>(list.subList(i, batchLastIndex)));//避免直接使用sublist
                // 设置下一批下标
                i = batchLastIndex;
                batchLastIndex = i + (batchCount - 1);
            }
        }
        stopWatch.stop();
        log.info("\r\n{}",stopWatch.prettyPrint());
    }

    private void batchInsert(List<OrderEntity> list){
        SqlSession batchSqlSession = null;
        try {
            // 获取批量方式的sqlsession
            batchSqlSession = this.sqlSessionTemplate
                    .getSqlSessionFactory()
                    .openSession(ExecutorType.BATCH, false);
            //通过新的session获取mapper
            OrderDao orderDao2 = batchSqlSession.getMapper(OrderDao.class);
            //每批commit的个数
            int batchCount = 10000;
            //每批最后一个的下标
            int batchLastIndex = batchCount;
            for (int i = 0; i < list.size(); ) {
                if (batchLastIndex >= list.size()) {
                    batchLastIndex = list.size();
                    orderDao2.insertBatch(new ArrayList<>(list.subList(i, batchLastIndex)));//避免直接使用sublist
                    batchSqlSession.commit();
                    batchSqlSession.clearCache();
                    break;
                } else {
                    orderDao2.insertBatch(new ArrayList<>(list.subList(i, batchLastIndex)));//避免直接使用sublist
                    //提交
                    batchSqlSession.commit();
                    //清缓存防溢出
                    batchSqlSession.clearCache();
                    // 设置下一批下标
                    i = batchLastIndex;
                    batchLastIndex = i + (batchCount - 1);
                }
            }
            //提交
            //batchSqlSession.commit();
        } catch (Exception e) {
            log.error("批量写入出现异常 {}", e);
        } finally {
            batchSqlSession.close();
        }
    }

    @DataSource(DbType.other)
    public void select(){
        OrderEntity orderEntity = orderDao.selectByOid("1");
        log.info(orderEntity.toString());
    }
}
