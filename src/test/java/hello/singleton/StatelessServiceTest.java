package hello.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatelessServiceTest {

    @Test
    @DisplayName("싱글톤 무상태(stateless)일 경우 문제점")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService01 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService02 = ac.getBean("statelessService", StatelessService.class);

        int price01 = statelessService01.order("member01",10000);
        int price02 = statelessService02.order("member02",20000);

        System.out.println("price01 = " + price01);
        System.out.println("price02 = " + price02);

        Assertions.assertThat(price01).isNotEqualTo(price02);

    }


    @Configuration
    static class TestConfig {
        @Bean
        public StatelessService statelessService(){
            return new StatelessService();
        }
    }
}
