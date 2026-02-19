package com.hello.member;

import com.hello.AppConfig;
import com.hello.member.Grade;
import com.hello.member.Member;
import com.hello.member.MemberService;
import com.hello.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    MemberService memberService; // = new MemberServiceImpl();


    @BeforeEach
    void setAppConfig(){
        AppConfig appConfig = new AppConfig();
        memberService  = appConfig.memberService();
    }
    @Test
    void join() {
        Member member = new Member("member01",1L, Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
