package hello.hellospring;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.member.MemoryMemberRepository;
import hello.hellospring.order.OrderService;
import hello.hellospring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService"); // 1번 호출 될것
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService"); // 1번 호출 될것
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository(){
        // 1번?? 2번?? 3번??
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}
