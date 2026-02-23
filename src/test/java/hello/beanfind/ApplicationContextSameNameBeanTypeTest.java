package hello.beanfind;

import hello.hellospring.member.MemberRepository;
import hello.hellospring.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameNameBeanTypeTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 2개 이상이면 오류 발생")
    void findByTypeDuplicate(){
//        MemberRepository bean = ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입으로 등록된 빈이 2개 이상이면 빈 이름으로 조회")
    void findByTypeNameDuplicate(){
        MemberRepository memberRepository = ac.getBean("memberRepository2", MemberRepository.class);

        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void findByTypeAllName(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }

    }
}
