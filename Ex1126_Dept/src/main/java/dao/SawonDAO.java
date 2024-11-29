package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SawonVO;

public class SawonDAO {
	
	SqlSession sqlSession;
	public SawonDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//사원목록 조회
	public List<SawonVO> selectList(int deptno){
		List<SawonVO> list = sqlSession.selectList("s.sawon_list", deptno);
		
		return list;
	}
	
	//사원 삭제
	public int delete(int sabun) {
		int res = sqlSession.delete("s.sawon_del", sabun);
		return res;
	}
	
	//사원 추가
	public int insert(SawonVO vo) {
		int res = sqlSession.update("s.sawon_insert", vo);
		return res;
	}
	
	//사원 한 명 조회
	public SawonVO selectOne(int sabun) {
		SawonVO vo = sqlSession.selectOne("s.sawon_one", sabun);
		return vo;
	}
	
	//사원 수정
	public int update(SawonVO vo) {
		int res = sqlSession.update("s.sawon_update", vo);
		return res;
	}

}
