/**
 * <b>工程名：</b>week08<br/>
 * <b>包  名：</b>com.example.demo.entity<br/>
 * <b>文件名：</b>Order.java<br/>
 * <b>日  期：</b>2021/06/27<br/>
 */
package com.example.demo.entity;

import lombok.Data;

/**
 * <b>类  名：</b>Order<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/27<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@Data
public class Order {

    private String id;

    private Long userId;

    private String desc;

}
