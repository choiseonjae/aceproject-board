package dao;

import java.sql.ResultSet;

import dto.BoardBean;

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

	// 게시판 찾기
	public BoardBean getBoard(int boardId) {

		BoardBean board = null;

		try {
			connect();

			StringBuffer sql = new StringBuffer("SELECT * FROM board where board_id = ?;");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardId);

			ResultSet rs = pstmt.executeQuery();
			board = new BoardBean();

			if (rs.next()) {
				board.setUserId(rs.getString("user_id"));
				board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
				board.setBoardId(rs.getInt(("board_id")));
				board.setDate(rs.getDate("date_entered"));
				board.setView(rs.getInt("view"));
			}

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;

	}

	public void updateView(int boardId) {
		try {
			connect();
			StringBuffer sql = new StringBuffer("Update board Set view = view + 1 Where board_id = ?;");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardId);
			pstmt.executeUpdate();

			disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
