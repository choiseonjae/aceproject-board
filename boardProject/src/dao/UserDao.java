package dao;

import java.sql.ResultSet;

public class UserDao extends Dao {

	public UserDao(String dbName, String dbId, String dbpw) {
		super(dbName, dbId, dbpw);
	}

	// 회원가입
	public void signUp(String id, String pw) {
		try {
			connect();

			String sql = "INSERT INTO user (id, pw) VALUES ( ?, ? );";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			pstmt.executeUpdate();
			System.out.println("회원가입 완료!");

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 아이디 중복 체크
	public boolean isOverlap(String id) {
		try {
			connect();

			String sql = "select EXISTS (select * from user where id=?) as success;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				return rs.getBoolean(1);

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 로그인 체크
	public boolean isLogin(String id, String pw) {
		try {
			connect();

			String sql = "select EXISTS (select * from user where id=? and pw=?) as success;";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				return rs.getBoolean(1);

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



}
