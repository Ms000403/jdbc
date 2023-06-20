package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.lang.model.element.ExecutableElement;
//학생 성적처리 프로그램 중에 새로운 학생을 등록(입력)하는 기능을 만들어 봅시다.(테이블에 insert 실행)
public class InsertDMLTest {

   public static void main(String[] args) {

      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      
      String driver = "oracle.jdbc.driver.OracleDriver";
      String user = "iclass";
      String password = "0419";

      try ( 
            Connection conn = DriverManager.getConnection(url, user, password);
      ) {
        
         System.out.println("연결 상태 = " + conn);
         if (conn != null)
            System.out.println("오라클 데이터베이스 연결 성공!!");
         else
            System.out.println("오라클 데이터베이스 연결 실패!!");
         //db연결 완료후에 sql 실행
         
         //insert sql 작성 : 제약조건(기본키 : stuno) 위반 되지 않는 값으로 입력하기					
         String sql = "insert into TBL_STUDENT values ('2023002','김땡이',16,'경기도')";
        
         //PreparedStatement 객체를 생성하면서 실행할 sql을 설정합니다
         //PreparedStatement 객체는 Connection 객체 메소드로 만듭니다.Connection 구현객체는 dbms 종류에 따라
         //생성되고 PreparedStatement 객체도 그에 따라 구현 객체가 결정된다.
         PreparedStatement pstmt = conn.prepareStatement(sql);
         //PreparedStatement() 메소드는 객체를 생성해 리턴
         
         System.out.println("pstmt 객체의 구현 클래스 : " + pstmt.getClass().getName());
         //oracle.jdbc.driver.OraclePreparedStatementWrapper 클래스로 객체가 생성
         
         pstmt.execute();	//PreparedStatement 객체로 excute 하면 SQL이 실행됩니다
         pstmt.close();
         

      } catch (Exception e) {	//모든 Exception 처리합니다.
         System.out.println("오류 메시지 = " + e);
         //e.printStackTrace(); 
      }
      //conn.close()를 명시적으로 실행할 필요없습니다
   } // main

}
/*
 * Statement 인터페이스는 sql 쿼리 처리와 관련된 방법을 정의합니다.
 * 객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해 만듭니다.
 * 
 * PreparedStatement는 Statement의 자식 인터페이스
 * 특징은 sql을 먼저 컴파일하고 sql실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식이다.
*/