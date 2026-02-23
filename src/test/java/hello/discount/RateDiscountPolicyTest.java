package hello.discount;

import hello.hellospring.discount.DiscountPolicy;
import hello.hellospring.discount.RateDiscountPolicy;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인정책이 적용됩니다.")
    void vip_o() {
        Member member = new Member("VIP_MEMBER", 1L, Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC은 10% 할인정책이 적용되지않습니다.")
    void vip_x() {
        Member member = new Member("BASIC_MEMBER", 1L, Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(0);
    }

}