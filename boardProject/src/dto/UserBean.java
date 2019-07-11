package dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UserBean {

	private String id;
	private String pw;
	private Date createDate;

}
