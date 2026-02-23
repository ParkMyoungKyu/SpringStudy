package hello.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {
    @Test
    @DisplayName("싱글톤 상태 유지(stateful)일 경우 문제점")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService01 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService02 = ac.getBean("statefulService", StatefulService.class);

        statefulService01.order("member01",10000);
        statefulService02.order("member02",20000);

        // member01 사용자가 주문한 금액 조회
        int price = statefulService01.getPrice();
        // 이 값에서는 10000원을 기대했지만 price필드가 전역변수로 사용되어 마지막에 주문한 20000원이 출력됨
        System.out.println("price : " +price);

        Assertions.assertThat(statefulService01.getPrice()).isEqualTo(20000);
    }


    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}