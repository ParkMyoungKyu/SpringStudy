package com.hello;

import com.hello.member.Grade;
import com.hello.member.Member;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import com.hello.order.Order;
import com.hello.order.OrderService;
import com.hello.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // CASE 01.
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService   = new OrderServiceImpl();

        // CASE 02.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // CASE 03.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean(MemberService.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);

        Member member = new Member("memberA",1L, Grade.VIP);
        memberService.join(member); // 회원가입

        Order order = orderService.createOrder(member.getId(), "ITEM",5000);

        System.out.println(order.toString());

    }
}
