package dao;

import java.util.List;

public interface BoardDAO {

	// BoardDAOImpl 자식에서 사용할 메서드 이름을 미리 지정
	int insert(int idx);
	List<String> selectList();
	int update(int idx);
	int delete(int idx);
	
}
