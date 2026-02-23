package hello.beandefinition;

import hello.hellospring.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext gc = new GenericXmlApplicationContext("AppConfig.xml");
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName : " + beanDefinitionName + " beanDefinition : " + beanDefinition);
            }
        }

        String[] beanXmlDefinitionNames = gc.getBeanDefinitionNames();

        for (String beanXmlDefinitionName : beanXmlDefinitionNames) {
            BeanDefinition beanXmlDefinition = ac.getBeanDefinition(beanXmlDefinitionName);
            if(beanXmlDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanXmlDefinitionName : " + beanXmlDefinitionName + " beanXmlDefinition : " + beanXmlDefinition);
            }
        }

    }

}
