package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.crud.day2.OracleUtility;
import koreait.jdbc.day3.StudentDto;

public class JCustomerDao {		//구매와 관련된 CRUD 실행 SQL. DAO : JCustomerDao , JProductDao
	//메소드 이름은 insert , update , delete , select , selectByPname 등등으로 이름을 작성하세요.

		//1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
		public JCustomer selectById(String custom_id) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "select * from j_custom"
					+ " where custom_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, custom_id);
			
			ResultSet rs = ps.executeQuery();
			JCustomer temp = null;
			if(rs.next()) {
				temp = new JCustomer(rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getDate(5));
			}
			ps.close();
			conn.close();
			
			return temp;
		}
		
		
	}


