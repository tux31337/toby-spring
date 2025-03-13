package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        // ResponseEntity : 웹 응답에 관한걸 다 담고 있는 object <string> body 부분이 string
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header (content-type) text/plain
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body Hello Spring
        assertThat(res.getBody()).isEqualTo("Hello Spring");
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        // ResponseEntity : 웹 응답에 관한걸 다 담고 있는 object <string> body 부분이 string
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name=", String.class);
        System.out.println(res);
        // status 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
