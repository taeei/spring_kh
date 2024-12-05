package dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CommentVO;

public class CommentDAO {
	
	SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//댓글 조회
	public List<CommentVO> selectList( Map<String, Integer> map ){
		List<CommentVO> list = sqlSession.selectList("c.comment_list_page", map);
		return list;
	}
	
	//댓글 갯수
	public int getRowTotal( int idx ) {
		int cnt = sqlSession.selectOne("c.comment_idx_count", idx);
		return cnt;
	}
	
	//댓글 추가
	public int insert(CommentVO vo)	{
		int res = sqlSession.insert("c.comment_insert", vo);
		return res;
	}
	
	//댓글 삭제
	public int delete(int c_idx) {
		int res = sqlSession.delete("c.comment_delete", c_idx);
		return res;
	}
	
	

}
