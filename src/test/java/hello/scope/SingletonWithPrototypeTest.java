package hello.scope;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {


    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
        // prototype은 빈 생성 후 의존 주입 및 초기화까지만 관여한다
        // 그리하여 스프링 컨테이너에 요청시 빈을 다시 생성한다

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init " + this);
        }

        public void destroy(){
            System.out.println("PrototypeBean destroy");
        }
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        // PrototypeBean이 스프링 컨테이너 생성 시점에 의존관계 자동 주입되어 같은 참조값을 사용한다
        // 따라서 스프링 컨테이너가 요청할때마다 생성하지 않는다
        // ※ 여기서 의문...? 그럼 Scope prototype을 쓰는 의미가...? 없는데???
    }


    static class ClientBean{
//        private final PrototypeBean prototypeBean;
//
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        // private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            // PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
}
