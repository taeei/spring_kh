package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.RequestMapping;

import vo.SawonVO;

public class SawonDAO {
	
	SqlSession sqlSession;
	public SawonDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//사원 목록 가져오기
	public List<SawonVO> selectList(){
		List<SawonVO> list = sqlSession.selectList("s.sawon_list");
		return list;
	}
	
	//사원추가
	public int insert(SawonVO vo) {
		int res = sqlSession.insert("s.sawon_insert", vo);
		return res;
	}
	
	

}
