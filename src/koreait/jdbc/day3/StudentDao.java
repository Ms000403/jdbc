package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.crud.day2.OracleUtility;

//DAO : Data Access(접근-읽기와 쓰기)Object
//		ㄴ SQL 실행 메소드를 모아 놓은 클래스

/*
 * StudentDao의 내용 요약
 * insert,update 는 sql 파라미터에 전달한 데이터의 타입을 dto
 * delete는 			"							원시형 또는 String
 * delete sql의 조건절 컬럼이 여러개 일때는 dto가 될 수 있습니다.	 map도 종종 사용한다.
 * 
 * insert,update,delete는 정수 리턴값으로 반영된 행의 개수를 전달.
 * 
 * selectOne : sql 파라미터에 전달할 데이터를 메소드 인자로 한다.
 * selectAll : 파라미터 없으며 여러개의 행을 리턴하기위해 저장할 자바의 객체는 List 타입이다.
 */

public class StudentDao {
	//나중에 db를 쉽게 코딩하기 위해 프레임워크를 사용하면 Exception 처리 안해도 가능
	public int insert(StudentDto student) throws SQLException{
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT values (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}

public int update(StudentDto student) throws SQLException{
	Connection connection = OracleUtility.getConnection();
	String sql = "update TBL_STUDENT\r\n"
			+ "set age = ?, address = ?\r\n"
			+ "where stuno = ?";
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setInt(1, student.getAge());
	ps.setString(2, student.getAddress());
	ps.setString(3, student.getStuno());
	int count = ps.executeUpdate();
	ps.close();
	connection.close();
	return count;
}

public int delete(String stu) throws SQLException{
	
	Connection connection = OracleUtility.getConnection();
	
	String sql = "delete from TBL_STUDENT where stuno = ?";
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setString(1, stu);	//메소드 인자값(매개변수 값)을 sql 쿼리에 전달
	int result = ps.executeUpdate(); 
	ps.close();
	connection.close();
	
	return result;


}
	//select 모두 조회 - 조회 결과 값을 list 객체로 리턴
//메소드에서 조회된 결과만을 리턴 출력과 가타기능들은 이 메소드를 사용하는 프로그램에서 구현할 수 있다.
	public List<StudentDto> selectAll() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<StudentDto>results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentDto dto = new StudentDto(rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4));
			results.add(dto);
		}
		return results;
	}

	
	//select * from TBL_STUDENT where stuno = '2023002';
	// 실행을 위한 selectOne 메소드 정의
public StudentDto selectOne(String stu) throws SQLException{
	Connection connection = OracleUtility.getConnection();
	String sql = "select * from TBL_STUDENT where stuno = ?";
	PreparedStatement ps = connection.prepareStatement(sql);
	ps.setString(1, stu);	//메소드 인자값(매개변수 값)을 sql 쿼리에 전달
	ResultSet rs = ps.executeQuery();
	StudentDto result = null;
	if(rs.next()) {
		//String stu1 = rs.getString(1);
		String name = rs.getString(2);
		int age = rs.getInt(3);
		String address = rs.getString(4);
		result = new StudentDto(stu,name,age,address);
	}
	ps.close();
	connection.close();
	return result;
}
}	






