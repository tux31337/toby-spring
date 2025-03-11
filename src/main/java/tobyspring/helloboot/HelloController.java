package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello") // / http://127.0.0.1:8080/hello?name=spring=
    public String hello(String name) {
        return "Hello " +name;
    }
}
