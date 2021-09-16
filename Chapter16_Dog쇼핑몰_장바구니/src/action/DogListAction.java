//개 상품 목록보기 요청을 처리하는
package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		DogListService dogListService = new DogListService();

		ArrayList<Dog> dogList = dogListService.getDogList(); // 메서드 만들고
		// request영역에 dogList이름으로 "개 상품 목록 정보"를 속성값으로 공유
		request.setAttribute("dogList", dogList);

		//★★★★★★★★★★★★★★★★
		// 교재 p753 두번째 그림(dogList.jsp) : 오늘본 개 상품 목록 정보를 알기 위해서는 '개 하나의 상세정보 보기'를 한 후		★★★★
		//★★★★★★★★★★★★★
		/*----(update,delete,insert)성공하면 commit 실패하면 rollback을 넣어줘아 하며  이 문장은 꼭 있어야한다----
		 * 		(selecet제외!) 
		 * */
		Cookie[] cookieArray =  request.getCookies();
		ArrayList<String> todayImageList = new ArrayList<String>(); //이미지를 Array에 담았다 
		
		if(cookieArray != null) {
			for(int i=0;i<cookieArray.length;i++) {
				//0인덱스부터 이름을 가지고와 start에서today를 찾는다
				//getValue = today1은 푸들.jpg를 얻어와서 ArrayList에 넣겠다		jin.jpg를 얻어와서 ArrayList에 넣겠다
				if(cookieArray[i].getName().startsWith("today")) { //getName = 1번째 이미지를 클릭하면 today1이 나온다 . 3번째 이미지를 누르면 today3 
					todayImageList.add(cookieArray[i].getValue()); //getValue = today1은 푸들.jpg를 얻어와서 todayImageList에 넣겠다		jin.jpg를 얻어와서 todayImageList에 넣겠다
					//최종적으로 View에 넣기 위해 이 지랄 하고 있다잉!!
					
				}
			}
		}
		//request영엑에 담아 dogList에 뿌린다잉!!
		request.setAttribute("todayImageList", todayImageList);
		
		// dogList.jsp로 ★ 디스패치 방식 ★으로 포워딩
		ActionForward forward =new ActionForward("dogList.jsp", false);

		return forward;
	}

}















