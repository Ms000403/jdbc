package koreait.crud.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSelectAllMenu {

	public static void main(String[] args) {
		/*
		 * 	모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행을 출력하세요
		 * 	과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스 
		 */
		Connection conn = OracleUtility.getConnection();
		System.out.println(":::::::학생의 점수를 과목으로 조회하는 메뉴:::::::");
		selectManyStudent(conn);	
		OracleUtility.close(conn);
		
	}

	private static void selectCount(Connection conn,String subject) {
		Scanner sc = new Scanner(System.in);
		String sql = "select count(*) \r\n "
				+ "from TBL_SCORE \r\n"
				+ "where subject = ? ";
		//*1. count 와 같은 함수 결과는 항상 행1개, 컬럼1개
	
		try (
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			if(rs.next()) {	//*1. 다른 조회문과 다르게 if문 안써도 됩니다. rs.next()만 단독으로
				count = rs.getInt(1); //count 와 같은 함수 결과는 행1개 컬럼도 1개이다.
		}
			//sql = "select count(*)from tbl_score where subject=?";
			//입력한 과목의 건(행)수를 조회할수 있다.
			System.out.println("과목 << " + subject + ">>" + count + "건이 조회 되었습니다");
		
		}catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 있습니다" + e.getMessage());
		}
		sc.close();
	}

	private static void selectManyStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select stuno,jumsu,teacher,term \r\n "
				+ "from TBL_SCORE \r\n"
				+ "where subject = ? ";
		//조건절에 사용하는 컬럼이 기본키와 유니크 일 때는 0또는 1개 행이 조회되고  -> rs.next()를 if에 사용
				//					기본키가 아닐때는 0~n개 행이 조회됩니다. -> 			while 에 사용
		System.out.println("점수를 조회할 과목을 고르시오 >> ");
		String subject = sc.nextLine();
		try (
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {//주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할수 있다.
				System.out.println(String.format("%10s %10d %10s %20s",
						rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
			}
			//sql = "select count(*)from tbl_score where subject=?";
			//입력한 과목의 건(행)수를 조회할수 있다.
			selectCount(conn, subject);
		
		}catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 있습니다" + e.getMessage());
		}
		sc.close();
	}

}
