package tobyspring.helloboot;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 톰캣 기반의 서블릿 웹 서버 팩토리 생성
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// 웹 서버 생성 및 서블릿 컨텍스트 초기화 설정
		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				// 새로운 서블릿을 등록
				servletContext.addServlet("hello", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						// 응답 상태 코드 설정 (200 OK)
						resp.setStatus(200);
						// 응답 헤더 설정 (ContentType을 "text/plain"으로 설정)
						resp.setHeader("ContentType", "text/plain");
						// 응답 본문에 "Hello Servlet" 출력
						resp.getWriter().println("Hello Servlet");
					}
				}).addMapping("/hello"); // "/hello" 경로로 요청이 오면 해당 서블릿 실행
			}
		});

		// 웹 서버 시작
		webServer.start();
	}

}
