package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

	protected Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	protected String dbId;
	protected String dbpw;
	protected String jdbc_url;

	protected Dao(String dbName, String dbId, String dbpw) {
//		try {
			this.dbId = dbId;
			this.dbpw = dbpw;
			jdbc_url = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=utf8&serverTimezone=UTC";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	// Database 연결
	protected void connect() {
		// 생성자를 만들어준다.
//		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(jdbc_url, dbId, dbpw); // DB에 연결
				
				System.out.println("연결을 시작합니다...");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	// Database 연결 해제
	protected void disconnect() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			System.out.println("연결을 끊습니다...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
