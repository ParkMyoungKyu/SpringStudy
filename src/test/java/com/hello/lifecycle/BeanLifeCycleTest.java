package com.hello.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close(); // 스프링 컨테이너 종료
                    // > ApplicationContext 에서는 종료를 할 수 없다(close 함수 x)
                    // > ConfigurableApplicationContext 필요(상속)
    }

    @Configuration
    static class LifeCycleConfig{
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.com");
            return networkClient;
        }
    }
}