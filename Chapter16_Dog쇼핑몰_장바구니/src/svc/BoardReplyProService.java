//'답변정보 등록 요청' 처리하는 Service클래스
package svc;
import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Dog;

public class BoardReplyProService {
	
	//답변글 정보 BoardBean객체
	public Dog replyArticle(Dog article) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		boardDAO.insertReplyArticle(article);
		
		return article;
	}
}
