package tobyspring.helloboot;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class HellobootApplication {

	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}

	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}

	public static void main(String[] args) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// 톰캣 기반의 서블릿 웹 서버 팩토리 생성
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

				// 웹 서버 생성 및 서블릿 컨텍스트 초기화 설정
				WebServer webServer = serverFactory.getWebServer(servletContext -> {

					servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)).addMapping("/*");
				});

				// 웹 서버 시작
				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication.class); // 자바 구성코드로 된 클래스를 등록해서 bean Object를 만들게함.
		applicationContext.refresh();
	}

}
