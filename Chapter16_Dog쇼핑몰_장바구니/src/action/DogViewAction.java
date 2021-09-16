package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogViewService dogViewService = new DogViewService();

		// id값을 가져오는데 어디서 가져오는가?! request에 담겨서 오닌깐
		// 해당 개 상품의 조회수 1증가 -> 'id로 조회한 개상품정보를 Dog객체를 반환'
		int id = Integer.parseInt(request.getParameter("id"));
		
		//Dog객체로 반환 해야지!!
		Dog dog = dogViewService.getDogView(id);
		//request영역에 '개 하나의 정보가 담긴 Dog객체'를 속성으로 공유!!
		request.setAttribute("dog", dog);
	
		//개 하나의 정보를 먼저 한 이유가 DogListAction.jsp에 있다 
		//개 상품 목록 정보를 알기 위해서!!
		//1번 개 이미지를 클릭하면 "today1"과 1번째 개이름 푸들의 그림이 나온다 이미지이름은 "pu.jsp"
		Cookie todayImagecookie = new Cookie("today" + id, dog.getImage());//개 이미지를 클라이언트로 보낸다
		//오른 본 이미지 
		todayImagecookie.setMaxAge(60*60*24); //60*60*24 = 24시간 
		response.addCookie(todayImagecookie);//반드시, 응답에 쿠키 객체를 추가한다잉!
		
		ActionForward forward = new ActionForward("dogView.jsp",false);
		return forward;
	}

}