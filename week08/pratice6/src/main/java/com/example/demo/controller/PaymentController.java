package com.example.demo.controller;


import com.example.demo.dto.OrderDTO;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("payment")
    public void payment(@RequestBody OrderDTO orderDTO) {
        paymentService.payOrder(orderDTO);
    }

    @PostMapping("paymentWithBuyException")
    public void paymentWithBuyException(@RequestBody OrderDTO orderDTO) {
        paymentService.paymentWithBuyException(orderDTO);
    }

    @PostMapping("paymentWithException")
    public void paymentWithException(@RequestBody OrderDTO orderDTO) {
        paymentService.paymentWithException(orderDTO);
    }

}
