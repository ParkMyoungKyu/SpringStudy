package hello.hellospring.discount;

import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountRate/100;
        }else{
            return 0;
        }
    }
}
