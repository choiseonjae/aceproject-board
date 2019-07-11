package dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardBean {

	private String title;
	private String contents;
	private Date date;
	private String userId;
	private int boardId;
	private int view;

}
