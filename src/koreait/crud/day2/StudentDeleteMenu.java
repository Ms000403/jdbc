package koreait.crud.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		System.out.println("::::::::::::::::::::학생 정보 삭제 메뉴입니다.::::::::::::::::::::");
		System.out.println("<<<지정된 학번으로 정보를 삭제할 수 있습니다>>>");
		
		try(
			Connection conn = DriverManager.getConnection(url,user,password);
				){
			updateStudent(conn);
		}catch(Exception e) {
			System.out.println("오류메세지 : " + e);
		}
		
	}
	private static void updateStudent(Connection connection)throws Exception{ //모든 예외를 처리하는 메서드
		Scanner sc = new Scanner(System.in);
		String stuno;
		//2.sql문 생성(?는 매개변수)
		String sql = "delete from TBL_STUDENT where stuno = ?";//
		System.out.println("학생번호 0000 입력은 삭제 취소 입니다.");
		System.out.print("삭제할 학번을 입력하세요 >>> ");
		stuno = sc.nextLine();

		if (stuno.equals("0000")) {
			System.out.println("학생 정보 삭제를 종료합니다.");
			sc.close();
			return; //리턴에 값이 없을 때는 단순하게 메소드 종료로 실행합니다
		}
		try (//3.PreparedStatement 생성
			PreparedStatement ps = connection.prepareStatement(sql); //PreparedStatement 객체를 생성하면서 실행할 sql을 설정합니다
		         //PreparedStatement 객체는 Connection 객체 메소드로 만듭니다.Connection 구현객체는 dbms 종류에 따라
		         //생성되고 PreparedStatement 객체도 그에 따라 구현 객체가 결정된다.
			){	//4.매개변수 값 설정
				ps.setString(1, stuno);//매개변수의 순서와 형식을 확인하고 전달하는 setXXXX 메소드 실행
				//ps.execute();	insert(create),update,delete,select(read) 모두 실행
				// 5.sql문 실행
				int count = ps.executeUpdate();	//리턴값은 반영된 행의 개수 -> 새로운 메소드 써보기
				System.out.println("학생 정보 삭제 " + count + "건이 완료되었습니다");
				
		}catch (SQLException e) {
			System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요" + e.getMessage());
		
		}
		sc.close();
				
	}
}
