package com.ktds.jgu.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		 하고 싶은 일을 여기에 적어준다. (아래를 하게 되면 Servlet 에서 일일이 인코딩을 바꿔주지 않아도 된다.)
//		Servlet 보다 Filter가 먼저 실행 된다. (Servlet과 톰캣의 중간 다리)
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);  // Servlet 에 요청하기 / 응답하기
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
