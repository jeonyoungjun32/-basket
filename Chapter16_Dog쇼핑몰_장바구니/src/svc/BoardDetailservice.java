
package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;

import dao.BoardDAO;
import vo.Dog;

public class BoardDetailservice {
	
	//글번호로 해당글 찾아서 '조회수 1증가' + '글번호'로 '글 하나의 정보'를 조회해서 BoardBean객체로 반환
	public Dog getArticle(int board_num) throws Exception {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		//'글번호'로 해당글 찾아서 '조회수 1증가'
		int updateCount = boardDAO.updateReadCount(board_num);
		if(updateCount > 0) commit(con);	
		else rollback(con);
		
		//'글번호'로 '글 하나의 정보'를 조회해서 'BoardBean객체로 반환'
		Dog article = boardDAO.selectArticle(board_num);
		
		close(con);
		
		return article;
	}
}
