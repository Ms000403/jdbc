package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionWithResources {

   public static void main(String[] args) {

      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      
      String driver = "oracle.jdbc.driver.OracleDriver";
      String user = "iclass";
      String password = "0419";

      // try with resources 형식 : try에 자원객체 선언하면 별도로 자원해제 처리를 해주지 않아도 됨.
      try ( // 자원해제 close가 필요한 변수 선언하기.
            Connection conn = DriverManager.getConnection(url, user, password);
            // 2개 이상의 구문을 작성할 수 있습니다.
      ) {
         // 현재 JAVA 버전에서는 DriverManager가 알아서 실행시키므로 생략 가능하다.
         // Class.forName(driver);

         System.out.println("연결 상태 = " + conn);
         if (conn != null)
            System.out.println("오라클 데이터베이스 연결 성공!!");
         else
            System.out.println("오라클 데이터베이스 연결 실패!!");

      } catch (Exception e) {
         System.out.println("ClassNotFoundException = 드라이버 경로가 잘못됐습니다.");
         System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
         System.out.println("오류 메시지 = " + e);
         e.printStackTrace(); // Exception 발생의 모든 원인을 cascade 형식으로 출력
      }
      // conn.close()를 명시적으로 실행할 필요가 없습니다.   
      
      
   } // main

}

/*
 * API : Application Programming interface : 서로 다른 software 시스템 간의 연결을 위한 방식
 * (JAVA 와 ORACLE) 인터페이스는 소통
 * 
 * 라이브러리 : 자바 라이브러리와 같이 특정 기능을 제공하는 클래스드르이 집합. 확장자는 압축형태 .jar
 * 
 * jdbc : 자바와 dbms를 연결하는 api (오라클 jdbc는 ojdbcX.jar 이고 'X'는 오라클 버전 )
 * 
 */