
//DB로 SQL구문을 전송하는 클래스
package dao;

//static:모든 메서드들 미리 메모리에 올림
import static db.Jdbcutil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Dog;

public class DogDAO {
	Connection con = null;// 멤버변수(전역변수) : 전체 메서드에서 사용 가능
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	/*
	 * 싱글톤 패턴 : BoardDAO객체 단 1개만 생성 이유? 외부 클래스에서
	 * "처음 생성된 BoardDAO객체를 공유해서 사용하도록 하기 위해"
	 */

	// 싱글톤-1.1
	private DogDAO() {
	}

	// 싱클톤-1.2
	private static DogDAO dogDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 만들도록 하기
	// 위해
	public static DogDAO getInstance() {
		if (dogDAO == null) {// 객체가 없으면
			dogDAO = new DogDAO();// 객체 생성
		}

		return dogDAO;// 기존 객체의 주소 리턴
	}

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	// 9. 글 삭제
	public int deleteArticle(int board_num) {
		String sql = "delete from board where board_num=" + board_num;
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(sql);

			deleteCount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDelete 에러 : " + e);// e:예외종류+예외메세지
			e.printStackTrace();
		} finally {
			close(pstmt);
//         close(rs);
		}
		return deleteCount;
	}

	// 1. 모든 개 상품 정보를 조회하여 ArrayList<Dog>객체 반환
	public ArrayList<Dog> selectDogList() {
		ArrayList<Dog> dogList = null;
		try {
			pstmt = con.prepareStatement("select * from dog");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dogList = new ArrayList<Dog>();

				do {
					Dog dog = /* dogList.add ( */new Dog(rs.getInt("id"), rs.getString("kind"), rs.getInt("number"),
							rs.getString("image"), rs.getString("country"), rs.getInt("height"), rs.getInt("weight"),
							rs.getString("content"), rs.getInt("readcount"))/* ) */;

					dogList.add(dog);

				} while (rs.next());
			} // if문

		} catch (Exception e) {
			System.out.println("selectDogList 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return dogList;
	}

	// id로 조회수 1증가
	public int updateReadCount(int id) {
//      sql = "update dog set readcount= readcount+1 where id=?";
		sql = "update dog set readcount= readcount+1 where id=" + id;
		int updateCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
//         pstmt.setInt(1, id);

			// 성공이나 실패 하는걸 확인한다
			updateCount = pstmt.executeUpdate();// 업데이트가 성공하면 1리턴받음

		} catch (Exception e) {
			System.out.println("updateReadCount 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return updateCount;
	}

	// id 개 정보를 조회하여 Dog객체를 반환
	public Dog selectDog(int id) {
		Dog dog = null;
		sql = "select * from dog where id=" + id;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dog = new Dog(rs.getInt("id"), rs.getString("kind"), rs.getInt("number"),
						rs.getString("image"), rs.getString("country"), rs.getInt("height"), rs.getInt("weight"),
						rs.getString("content"), rs.getInt("readcount"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(rs);
			close(pstmt);
		}

		return dog;
	}

}