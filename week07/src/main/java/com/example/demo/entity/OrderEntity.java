/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.entity<br/>
 * <b>文件名：</b>Order.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.entity;

import lombok.Data;

import java.util.Random;
import java.util.UUID;

/**
 * <b>类  名：</b>Order<br/>
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
public class OrderEntity {

    private String oid;
    private int number;
    private int pid;
    private double pprice;
    private int uid;
    private String pname;
    private String username;

    static Random r = new Random();

    public static OrderEntity create(){
        OrderEntity order = new OrderEntity();
        order.setOid(UUID.randomUUID().toString());
        order.setNumber((int) (1+Math.random()*(4)));
        order.setPid((int) (1+Math.random()*(10)));
        order.setPname("pname"+order.getPid());
        order.setPprice(r.nextDouble()*3+20);
        order.setUid((int) (1+Math.random()*(10)));
        order.setUsername("uname"+order.getUid());
        return order;
    }
}
