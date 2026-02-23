package hello.order;

import hello.hellospring.discount.FixDiscountPolicy;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemoryMemberRepository;
import hello.hellospring.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member("name", 1L, Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        orderService.createOrder(1L, "item01", 10000);
    }
}