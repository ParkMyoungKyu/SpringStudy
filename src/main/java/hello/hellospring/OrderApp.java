package hello.hellospring;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberService;
import hello.hellospring.order.Order;
import hello.hellospring.order.OrderService;
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
