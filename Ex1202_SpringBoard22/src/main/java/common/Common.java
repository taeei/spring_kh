package common;

public class Common {
	//일반게시판
	public static class Board{
		
		public final static String VIEW_PATH = "/WEB-INF/views/board/";
		
		//한 페이지에 보여줄 게시글의 수
		public final static int BLOCKLIST = 5;
		//--> Common.Board.BLOCKLIST 로 호출
		
		//하단 페이지 메뉴의 수
		public final static int BLOCKPAGE = 3;
	}
	
	//댓글페이지
	public static class Comment{

		public final static String VIEW_PATH = "/WEB-INF/views/comment/";
		
		//한 페이지에 보여줄 게시글의 수
		public final static int BLOCKLIST = 3;
		
		//하단 페이지 메뉴의 수
		public final static int BLOCKPAGE = 2;
	}
}