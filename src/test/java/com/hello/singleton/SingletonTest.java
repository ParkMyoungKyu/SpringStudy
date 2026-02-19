package com.hello.singleton;

import com.hello.AppConfig;
import com.hello.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 할때마다 객체를 생성한다..
        MemberService memberService01 = appConfig.memberService();
        // 2. 조회 할때마다 객체를 생성한다..
        MemberService memberService02 = appConfig.memberService();

        // 로그를 확인해보면 참조값이 다르다
        System.out.println("memberService01 = " + memberService01);
        System.out.println("memberService02 = " + memberService02);

        Assertions.assertThat(memberService01).isNotSameAs(memberService02);
    }
}
