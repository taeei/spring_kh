package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.VisitVO;

public class VisitDAO {
	
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//방문자 조회
	public List<VisitVO> selectList(Map<String, Integer> map){
		List<VisitVO> list = sqlSession.selectList("v.visit_list", map);
		return list;
	}
	
	//전체 게시글 수
	public int getRowTotal() {
		int cnt = sqlSession.selectOne("v.visit_count");
		return cnt;
	}
	
	//방명록 추가
	public int insert(VisitVO vo) {
		int res = sqlSession.insert("v.visit_insert", vo);
		return res;
	}
	
	//방명록 삭제
	public int delete(int idx) {
		int res = sqlSession.delete("v.visit_delete", idx);
		return res;
	}
	
	//수정할 방명록 하나만 조회
	public VisitVO selectOne(int idx) {
		VisitVO vo = sqlSession.selectOne("v.select_one", idx);
		return vo;
	}
	
	//방명록 수정
	public int update(VisitVO vo) {
		int res = sqlSession.update("v.visit_update", vo);
		return res;
	}

}
