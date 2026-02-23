package hello.beanfind;

import hello.hellospring.AppConfig;
import hello.hellospring.member.MemberService;
import hello.hellospring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름이 아닌 타입으로만 조회")
    void findBeanType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanImpl(){
        MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("없는 빈 조회")
    void findBeanX(){
//        ac.getBean("myBeanGet", MemberService.class);

        // 없는 빈을 조회하게되면 'NoSuchBeanDefinitionException' 가 발생한다.
        // >> 해당 오류가 나는지 확인 -> 오류발생시 정상
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("myBeanGet", MemberService.class));
    }

}
