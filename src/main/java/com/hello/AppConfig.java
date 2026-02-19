package com.hello;

import com.hello.discount.DiscountPolicy;
import com.hello.discount.FixDiscountPolicy;
import com.hello.discount.RateDiscountPolicy;
import com.hello.member.MemberRepository;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import com.hello.member.MemoryMemberRepository;
import com.hello.order.OrderService;
import com.hello.order.OrderServiceImpl;
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
