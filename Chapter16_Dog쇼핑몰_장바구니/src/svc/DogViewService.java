
package svc;

import static db.Jdbcutil.close;
import static db.Jdbcutil.commit;
import static db.Jdbcutil.getConnection;
import static db.Jdbcutil.rollback;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	// 해당 개 상품의 조회수 1증가 -> 'id로 조회한 개상품정보를 Dog객체를 반환'

	// ****** id로 조회한 개상품정보를 Dog객체를 반환'을 하는 클래스임 ****
	public Dog getDogView(int id) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴 : DogDao객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		dogDAO.setConnection(con);

		/*----DogDAO의 해당 메서드를 호출하여 처리----*/
		// 해당 개 상품의 '조회수 1증가'
		int updateCount = dogDAO.updateReadCount(id);

		/*----(update,delete,insert)성공하면 commit 실패하면 rollback을 넣어줘아 하며  이 문장은 꼭 있어야한다----
		 * 		(selecet제외!) 
		 * */
		
		// 꼭 있어야 하는 것!!
		
		if (updateCount > 0) {

			commit(con);
		} else {
			rollback(con);
		}
		
		//'id로 조회한 개 상품정보를 Dog객체로 반환'
		Dog dog= dogDAO.selectDog(id); //Dog에서 id로 받는다
		/******************************************************************/
		
		
		
		

		// 4.해제
		close(con);

		return dog;
	}
}