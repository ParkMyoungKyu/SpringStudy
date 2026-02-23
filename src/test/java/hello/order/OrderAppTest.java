package hello.order;

import hello.hellospring.AppConfig;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberService;
import hello.hellospring.order.Order;
import hello.hellospring.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderAppTest {
    MemberService memberService;// = new MemberServiceImpl();
    OrderService orderService  ;// = new OrderServiceImpl();



    @BeforeEach
    void setAppConfig() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void CreateOrderTest() {
        Member member = new Member("memberA",1L, Grade.VIP);
        memberService.join(member); // 회원가입

        Order order = orderService.createOrder(member.getId(), "ITEM",5000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(500);
    }


}