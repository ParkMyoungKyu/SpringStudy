package com.hello.singleton;

import com.hello.AppConfig;
import com.hello.SingletonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonServiceTest {


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest(){
        // 01. private로 선언되어있어 생성자 생성이 안된다
        // new SingletonService();

        // 1. 조회: 호출할때마다 객체 반환
        SingletonService singletonService01 = SingletonService.getInstance();
        // 2. 조회: 호출할때마다 객체 반환
        SingletonService singletonService02 = SingletonService.getInstance();

        System.out.println("singletonService01 = " + singletonService01);
        System.out.println("singletonService02 = " + singletonService02);

        Assertions.assertThat(singletonService01).isSameAs(singletonService02);

        singletonService01.singletonLogic();
    }
}
