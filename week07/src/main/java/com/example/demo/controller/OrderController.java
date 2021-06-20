/**
 * <b>工程名：</b>week07<br/>
 * <b>包  名：</b>com.example.demo.controller<br/>
 * <b>文件名：</b>OrderController.java<br/>
 * <b>日  期：</b>2021/06/20<br/>
 */
package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>类  名：</b>OrderController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>qchang<br/>
 * <b>创建时间：</b>2021/06/20<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0 <br/>
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/test1.action")
    public void test1(){
        orderService.inset10w1();
    }

    @RequestMapping("/test2.action")
    public void test2(){
        orderService.inset10w2();
    }

    @RequestMapping("/test3.action")
    public void test3(){
        orderService.inset10w3();
    }

    @RequestMapping("/test0.action")
    public void test0(){
        orderService.select();
    }
}
