package hello.member;

import hello.hellospring.AppConfig;
import hello.hellospring.member.Grade;
import hello.hellospring.member.Member;
import hello.hellospring.member.MemberService;
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
