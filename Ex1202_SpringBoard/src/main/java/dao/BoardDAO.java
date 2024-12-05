package dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {
	
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체 게시글 조회
	public List<BoardVO> selectList( Map<String, Object> map ){
		List<BoardVO> list = sqlSession.selectList("b.board_list", map);
		return list;
	}
	
	//새 글 등록
	public int insert( BoardVO vo ) {
		int res = sqlSession.insert("b.board_insert", vo);
		return res;
	}
	
	//상세보기를 위한 게시글 조회
	public BoardVO selectOne( int idx ) {
		BoardVO vo = sqlSession.selectOne("b.board_one", idx);
		return vo;
		
	}
	
	//조회수 증가
	public int update_readhit( int idx ) {
		int res = sqlSession.update("b.board_update_readhit", idx);
		return res;
	}
	
	//댓글 처리를 위한 step + 1
	public int update_step( BoardVO baseVO ) {
		int res = sqlSession.update("b.board_update_step", baseVO);
		return res;
	}
	
	//댓글등록
	public int reply(BoardVO vo) {
		int res = sqlSession.insert("b.board_reply", vo);
		return res;
	}
	
	//삭제(된 것 처럼 업데이트)
	public int del_update( int idx ) {
		int res = sqlSession.update("b.board_del_update", idx);
		return res;
	}
	
	
	//게시판의 전체 게시글 수
	public int getRowTotal( Map<String, Object> map ) {
		int cnt = sqlSession.selectOne("b.board_count", map);
		return cnt;
	}
	
	//게시글 수정
	public int modify(BoardVO vo) {
		int res = sqlSession.update("b.modify", vo);
		return res;
	}
}


















































