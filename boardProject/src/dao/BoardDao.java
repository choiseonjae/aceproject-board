package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BoardBean;

public class BoardDao extends Dao {

	public BoardDao(String dbName, String dbId, String dbpw) {
		super(dbName, dbId, dbpw);
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

	// 게시판 목록 가져오기
	public List<BoardBean> getPage(int pageNo) {
		List<BoardBean> boards = new ArrayList<>();

		try {
			connect();

			int startLimit = (pageNo - 1) * 10;
			int endLimit = startLimit + 10;

			StringBuffer sql = new StringBuffer("SELECT * FROM board ORDER BY board_id DESC LIMIT ?, ?;");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startLimit);
			pstmt.setInt(2, endLimit);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setUserId(rs.getString("user_id"));
				board.setTitle(rs.getString("title"));
				board.setContents(rs.getString("contents"));
				board.setBoardId(rs.getInt(("board_id")));
				board.setDate(rs.getDate("date_entered"));
				board.setView(rs.getInt("view"));

				boards.add(board);

			}

			disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return boards;
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

	// 조회수 증
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

	// 게시글 삭
	public void deleteBoard(int boardId) {
		try {
			connect();

			String sql = "DELETE FROM board WHERE (board_id = ?);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardId);
			pstmt.executeUpdate();

			disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시글 수정
	public void updateBoard(int boardId, String title, String contents) {
		try {
			connect();
			String sql = "UPDATE board SET title = ?, contents = ? WHERE (board_id = ?);";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, boardId);
			pstmt.executeUpdate();

			disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
