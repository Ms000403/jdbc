package koreait.jdbc.day3;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoTest {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
		System.out.println("1. insert 테스트");
		System.out.println("2023009 김땡구 17 강남구 - 데이터 입력");
		StudentDto dto = new StudentDto("2023009","김땡구",17,"강남구");
		try {
		int count = dao.insert(dto);
		System.out.println("학생 등록" + count + "건 입력 성공");
		}catch(SQLException e) {
			System.out.println("예외 - " + e.getMessage());
		}
		System.out.println("2. update 테스트");
		System.out.println("2023009 김땡구를 16 용산구 - 데이터 수정");
		dto = new StudentDto("2023009","김땡구",16,"용산구");
		try {
			int count = dao.update(dto);
			System.out.println("학생 정보 수정" + count + "건 입력 성공");
			}catch(SQLException e) {
				System.out.println("예외 - " + e.getMessage());
			}
		
		
		System.out.println("3. delete 테스트");
		System.out.println("2023009 - 데이터 삭제");
		try {
			int count = dao.delete("2023009");
			System.out.println("학생 삭제" + count + "건 입력 성공");
			System.out.println("삭제결과 조회 : " + dao.selectOne(dto.getStuno()));
			}catch(SQLException e) {
				System.out.println("예외 - " + e.getMessage());
			}
	
		System.out.println("4. select 테스트");
		System.out.println("2023009 - 데이터 조회");
		try {
			StudentDto result = dao.selectOne("2023009");
			System.out.println("학생 조회" + result + "건 입력 성공");
			System.out.println("입력결과 조회 : " + dao.selectOne(dto.getStuno()));
			}catch(SQLException e) {
				System.out.println("예외 - " + e.getMessage());
			}
	
		System.out.println("4. selectAll 테스트");
		System.out.println("학생테이블 - 모든 데이터 조회");
		try {
			List<StudentDto>list = dao.selectAll();
			for (StudentDto studentDto : list) {
				System.out.println(studentDto);
			} {
			System.out.println("학생 삭제" + list + "건 입력 성공");
			
			}
			}catch(SQLException e) {
				System.out.println("예외 - " + e.getMessage());
			}
	
	
	}

}
