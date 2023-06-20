package koreait.crud.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::학생을 학번으로 조회하는 메뉴:::::::");
		selectOneStudent(conn);	
		
		OracleUtility.close(conn);
		
	}

	private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select * from TBL_STUDENT where stuno = ?";
		System.out.println("조회할 학번 입력 >>>> ");
		String stuno = sc.nextLine();
		
		try(
				PreparedStatement ps = conn.prepareStatement(sql);
		){				
			ps.setString(1, stuno);
		
			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는 " + rs.getClass().getName());
			if(rs.next()) {//주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할수 있다.
				//getXXXX메소드는 rs가 가리키는 현재 행의 컬럼값을 가져오기 합니다.
			System.out.println("학번 : " + rs.getString(1));
			System.out.println("학번 : " + rs.getString("stuno"));	//인덱스대신에 컬럼명을 사용할 수 있다.
			System.out.println("이름 : " + rs.getString(2));
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("나이 : " + rs.getString(3));
			System.out.println("나이 : " + rs.getString("age"));
			System.out.println("나이 : " + rs.getString(4));
			System.out.println("주소 : " + rs.getString("address"));
			
			}else {
				System.out.println("조회 결과가 없습니다");
			}
			System.out.println("다음 조회 결과 행이 또 있을까요?" + rs.next());
			
			
			
		}catch(SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다 : " + e.getMessage());
		}
		sc.close();
	}

}





