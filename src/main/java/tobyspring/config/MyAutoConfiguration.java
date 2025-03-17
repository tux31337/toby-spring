package tobyspring.config;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false) // MyAutoConfituration 이 붙어서 imports 파일을 통해 동적으로 로딩되는 파일들은 그냥 proxyBeanMethods를 false로 바꾼 confituration이 적용된다.
public @interface MyAutoConfiguration {
}
