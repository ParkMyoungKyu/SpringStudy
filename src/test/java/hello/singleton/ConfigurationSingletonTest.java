package hello.singleton;

import hello.hellospring.AppConfig;
import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemberServiceImpl;
import hello.hellospring.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("Configuration 어노테이션의 싱글톤 적용여부 확인")
    void configurationTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService   = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 모두 같은 인스턴스를 참조하는지 확인
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
        // 모두 같은 인스턴스를 참조하고있는걸 확인할 수 있다

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    @DisplayName("Configuration 바이트코드 확인")
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 스프링 빈에 등록이 된다
        AppConfig bean = ac.getBean("appConfig",AppConfig.class);

        System.out.println("bean -> " + bean.getClass());

        /**
         * AppConfig.java 에 @Configuration을 제거하면 각기 다른 인스턴스를 생성한다
         * !!! 스프링 컨테이너 싱글톤 패턴 X !!!
         */
    }
}
