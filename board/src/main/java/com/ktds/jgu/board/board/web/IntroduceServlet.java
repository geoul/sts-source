package com.ktds.jgu.board.board.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.jgu.board.board.vo.IntroduceVO;

public class IntroduceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IntroduceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/introduce.jsp");
		
		IntroduceVO introduce = new IntroduceVO();
		introduce.setName("장거울");
		introduce.setAge(26);
		introduce.setHome("안성");
		introduce.setHobby("드라마 보기");
		introduce.setLike("초밥");
		
		request.setAttribute("introduce", introduce);
		// key 하나로 여러 개의 값을 전달할 수 있다
		
		List<IntroduceVO> introduceList = new ArrayList<IntroduceVO>();
		introduceList.add(introduce);
		introduceList.add(introduce);
		introduceList.add(introduce);
		introduceList.add(introduce);
		introduceList.add(introduce);
		introduceList.add(introduce);
		// 추가한 것만큼 브라우저에 보인다
		
		request.setAttribute("introduceList", introduceList);
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
