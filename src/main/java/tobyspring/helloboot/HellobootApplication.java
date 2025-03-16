package tobyspring.helloboot;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class HellobootApplication {
	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		// 스프링 컨테이너를 초기화 하는중에 부가적인 작업을 해야하면
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// 톰캣 기반의 서블릿 웹 서버 팩토리 생성
				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//				dispatcherServlet.setApplicationContext(this);

				// 웹 서버 생성 및 서블릿 컨텍스트 초기화 설정
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");
				});

				// 웹 서버 시작
				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();

	}
}
