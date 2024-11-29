package dao;

import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public int insert(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectList() {
		
		//DB에서 정보를 가져왔다고 가정
		List<String> list = new ArrayList<String>();
		list.add("사과");
		list.add("orange");
		list.add("복숭아");
		list.add("pear");
		
		return list;
	}

	@Override
	public int update(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
