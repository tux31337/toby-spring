package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@RestController
public class HelloController  {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        System.out.println("여기는ㄷ ㅡㄹ어오나????");
        System.out.println(name);
        if (name == null || name.trim().length() == 0) throw  new IllegalArgumentException();

        return helloService.sayHello(name);
    }

}
