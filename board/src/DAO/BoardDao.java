package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.BoardBean;

public class BoardDao {
	private Connection conn = null; // 데이터베이스를 접근하기 위한 객체
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 정보를 담을 수 있는 변수를 만들어준다.
	private String dbId;
	private String dbpw;
	private String jdbc_url;

	public BoardDao(String dbName, String dbId, String dbpw) {
		try {
			this.dbId = dbId;
			this.dbpw = dbpw;
			jdbc_url = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=utf8&serverTimezone=UTC";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Database 연결
	private void connect() {
		// 생성자를 만들어준다.
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbc_url, dbId, dbpw); // DB에 연결
			System.out.println("연결을 시작합니다...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Database 연결 해제
	private void disconnect() {
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

	// 게시글 insert
	public void insertBoard(String user_id, String title, String contents) {
		try {
			connect();

			System.out.println("게시글을 삽입합니다...");

			StringBuffer sql = new StringBuffer("insert into board (user_id, title, contents) value ( ?, ?, ? );");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, user_id);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);

			pstmt.executeUpdate();

			System.out.println("삽입 성공!");

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
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
			}

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;

	}

}
