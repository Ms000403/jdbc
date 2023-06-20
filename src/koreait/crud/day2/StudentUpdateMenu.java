package koreait.crud.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentUpdateMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		System.out.println("::::::::::::::::::::학생 정보 수정 메뉴입니다.::::::::::::::::::::");
		System.out.println("<<<지정된 학번으로 나이와 주소를 수정할 수 있습니다>>>");
		
		try(
			Connection conn = DriverManager.getConnection(url,user,password);
				){
			updateStudent(conn);
		}catch(Exception e) {
			System.out.println("오류메세지 : " + e);
		}
		
	}
	//반복없이 1개만 수정하세요.
	private static void updateStudent(Connection connection)throws Exception{
		Scanner sc = new Scanner(System.in);
		String stuno,address,age;
		String sql = "update TBL_STUDENT\r\n"
				+ "set age = ?, address = ?\r\n"
				+ "where stuno = ?";
		
		System.out.println("학생번호 0000 입력은 수정 취소 입니다.");
		System.out.print("학번을 입력하세요 >>> ");
		stuno = sc.nextLine();

		if (stuno.equals("0000")) {
			System.out.println("학생 수정을 종료합니다.");
			sc.close();
			return; //리턴에 값이 없을 때는 단순하게 메소드 종료로 실행합니다
		}

		System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
		age = sc.nextLine();
		
		System.out.print("주소를 입력하세요 >>> ");
		address = sc.nextLine();
		
		try (
			PreparedStatement ps = connection.prepareStatement(sql);
			){	//매개변수의 순서와 형식을 확인하고 전달하는 setXXXX 메소드 실행
				ps.setInt(1, Integer.parseInt(age));
				ps.setString(2, address);
				ps.setString(3, stuno);
				//ps.execute();	insert(create),update,delete,select(read) 모두 실행
				int count = ps.executeUpdate();	//리턴값은 반영된 행의 개수 -> 새로운 메소드 써보기
				System.out.println("학생 정보 수정 " + count + "건이 완료되었습니다");
				
		}catch (SQLException e) {
			System.out.println("잘못된 데이터 입력입니다. 다시 입력하세요" + e.getMessage());
		
		}
		
		sc.close();		
	}
	
	
}
