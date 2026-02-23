package hello.hellospring.discount;

import hello.hellospring.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
