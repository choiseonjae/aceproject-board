package dto;

import java.sql.Date;

public class BoardBean {

	private String title;
	private String contents;
	private Date date;
	private String userId;
	private int boardId;
	private int view;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}
	

}
