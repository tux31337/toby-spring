package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
@RestController
public class HelloController {
    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext =applicationContext;

        System.out.println("sprong contaienr 자기 자신인 applicationContext가 자동으로 들어오나 확인" + applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println("applicationContext" + applicationContext);
//        this.applicationContext = applicationContext;
//    }
}
