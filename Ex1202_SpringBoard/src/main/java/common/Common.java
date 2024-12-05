package common;

public class Common {

	//일반게시판
	public static class Board{
		
		public final static String VIEW_PATH = "/WEB-INF/views/board/";
		
		//한 페이지에 보여줄 게시글 수
		public final static int BLOCKLIST = 5;
		
		//하단 페이지 메뉴의 수
		//<- 1 2 3 ->
		public final static int BLOCKPAGE = 3;
		
	}
	
	//댓글 페이징
	public static class Comment{
		
		public final static String VIEW_PATH = "/WEB-INF/views/comment/";
		
		// 한 페이지에 보여줄 댓글 수
		public final static int BLOCKLIST = 3;

		// 하단 페이지 메뉴의 수
		// <- 1 2 3 ->
		public final static int BLOCKPAGE = 2;
		
	}
	
}
