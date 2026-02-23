package hello.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototypeBean.class);

        prototypeBean prototypeBean1 = ac.getBean(prototypeBean.class);
        System.out.println("find prototypeBean1");
        prototypeBean prototypeBean2 = ac.getBean(prototypeBean.class);
        System.out.println("find prototypeBean2");

        System.out.println("find prototypeBean1 : " +  prototypeBean1 );
        System.out.println("find prototypeBean2 : " +  prototypeBean2 );

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // 종료 호출하지 않는다

        // 직접 종료해야된다
        prototypeBean1.destroy();
        prototypeBean2.destroy();


    }

    @Scope("prototype")
    static class prototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean Init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean Destroy");
        }
    }
}
