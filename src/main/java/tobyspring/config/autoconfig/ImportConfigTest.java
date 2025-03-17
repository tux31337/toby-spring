package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;

public class ImportConfigTest {
    
    @Bean
    Test1 test1() {
        return new Test1(test2());
    }

    @Bean
    Test2 test2() {
        return new Test2();
    }
    
    
    
    
    static class Test1 {
        Test2 test;

        public Test1(Test2 test) {
            this.test = test;
        }
    }
    
    static class Test2 {
        public Test2() {
            System.out.println("test2 싦행");
        }
    }
}
