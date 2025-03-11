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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 톰캣 기반의 서블릿 웹 서버 팩토리 생성
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		// 웹 서버 생성 및 서블릿 컨텍스트 초기화 설정
		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				HelloController helloController = new HelloController();

				// 새로운 서블릿을 등록
				servletContext.addServlet("frontController", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						// 인증, 보안, 다국어, 공통 기능
						if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
							String name = req.getParameter("name");

							String ret = helloController.hello(name);

							// 응답 상태 코드 설정 (200 OK)
							resp.setStatus(HttpStatus.OK.value());
							// 응답 헤더 설정 (ContentType을 "text/plain"으로 설정)
							resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
							// 응답 본문에 "Hello Servlet" 출력
							resp.getWriter().println(ret);
						}
						else if (req.getRequestURI().equals("/user")) {

						}
						else {
							resp.setStatus(HttpStatus.NOT_FOUND.value());
						}
					}
				}).addMapping("/*"); // "/hello" 경로로 요청이 오면 해당 서블릿 실행
			}
		});

		// 웹 서버 시작
		webServer.start();
	}

}
