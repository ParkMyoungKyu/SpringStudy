package hello.singleton;

import hello.hellospring.AppConfig;
import hello.hellospring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContainerTest {


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤 테스트")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1.조회: 호출 할 때마다 같은 객체를 반환한다
        MemberService memberService01 = ac.getBean(MemberService.class);

        // 2.조회: 호출 할 때마다 같은 객체를 반환한다
        MemberService memberService02 = ac.getBean(MemberService.class);

        System.out.println("memberService01 : " + memberService01);
        System.out.println("memberService02 : " + memberService02);

        Assertions.assertThat(memberService01).isSameAs(memberService02);
    }

}
