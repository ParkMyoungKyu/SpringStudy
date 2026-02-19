package com.hello.xml;

import com.hello.member.MemberService;
import com.hello.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    @Test
    @DisplayName("xml 기반의 설정 정보 조회")
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("AppConfig.xml");
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
    }
}
