package tobyspring.helloboot;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너를 초기화 하는중에 부가적인 작업을 해야하면
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
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
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();


	}

}
