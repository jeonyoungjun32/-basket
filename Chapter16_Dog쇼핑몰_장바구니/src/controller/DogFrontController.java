/*
 * 1.controller
 */

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.DogListAction;
import action.DogViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardController
 */
//확장자가 dog이면 무조건 DogFrontController로 이동하여 doProcess()메서드 실행함
@WebServlet("*.dog")//마지막 url이 *.bo로 끝나는 요청을 맵핑
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	//이 서블릿으로 들어오는 post나 get방식의 모든 요청은 doProcess()를 호출하여 처리
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//반드시 첫줄
		request.setCharacterEncoding("UTF-8");
		
		//요청객체로부터 '프로젝트명+파일경로'까지 가져옴 예)"/project/boardWriteFrom.dog"
		String requestURI = request.getRequestURI();
		//요청 URL : http://localhost:8090/project/boardWriteFrom.dog
		//요청 URI : 도메인 네임 +/project/boardWriteFrom.dog
		
		//요청객체로부터 '프로젝트 path'만 가져옴(예)/project
		String contextPath =  request.getContextPath();
		
		/* URI에서 contextPath길이만큼 잘라낸 나머지 문자열
		 * /project/boardWriteFrom.dog - /project = /boardWriteForm.dog
		 */
		
		String command = requestURI.substring(contextPath.length());//index 8~끝까지 부분문자열 반환
		
		/* 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성으로 이용해서 동일한 타입으로 참조하기 위해
		 * 'Action 인터페이스' 타입의 변수 선언(교재 p574)
		 */
		
		Action action = null;
		ActionForward forward = null;
		
		/* 글쓰기 페이지를 열어주는 요청인 경우는 특별한 비지니스 로직을 실행할 필요없이
		 * 글쓰기 할 수 있는 뷰페이로 포워딩하면 됨
		 */
		
		/*글 작성하는 페이지 */
		if(command.equals("/dogList.dog")) {//'개 상품 목록보기' 요청 이면
			action = new DogListAction();
			
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/dogView.dog")) {//'특정 개 상품의 상세 정보 보기' 요청
			action = new DogViewAction();
			try {
				forward = action.exeute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * 포워딩
		 * 
		 */
		if(forward != null) { // 거짓이닌깐 else를 실행한다
			if(forward.isRedirect()) {//isRedirect=true : 주소변경(새요청), request
				response.sendRedirect(forward.getPath());//응답-리다이렉트 방식
			} else {//isRedirect=false : 디스패치 방식
//			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			//dispatcher.forward(request, response);//기존 요청, 기존 응답 그대로 보내므로 주소 그대로
			request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}
}


