package test;

import DAO.BoardDao;
import DTO.BoardBean;

public class Main {

	public static void main(String[] args) {
		BoardDao boardDao = new BoardDao("board", "root", "");
//		boardDao.insertBoard("choiseonjae", "Hello", "I'm seonjae");
		
		BoardBean board = boardDao.getBoard(2);
		System.out.println(board.getContents());
	}

}
