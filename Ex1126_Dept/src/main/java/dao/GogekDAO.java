package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.GogekVO;

public class GogekDAO {
	
	SqlSession sqlSession;
	public GogekDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//고객 정보 조회
	public List<GogekVO> selectList(int sabun){
		List<GogekVO> list = sqlSession.selectList("g.gogek_list", sabun);
		return list;
	}

}
