package service;

import java.util.List;

import dao.BoardDAO;

public class BoardServiceImpl implements BoardService{

	//일반적으로 Service클래스는
	//DAO들을 묶어서 관리하는 용도로 생성한다
	BoardDAO dao;
	
	public BoardServiceImpl() {
		
	}
	
	//세터
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	// 생성자 
	public BoardServiceImpl( BoardDAO dao ) {
		//외부에서 들어온 dao의 주소를 받을 예정
		this.dao = dao;
	}
	
	@Override
	public List<String> selectList() {
		//과일목록을 가져오는 dao의 메서드를 호출한다
		return dao.selectList();
	}

	
	
}
