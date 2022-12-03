package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	@Override
	public String insertBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.insert(map);
		if(affectRowCount ==1) {
			return map.get("bo_no").toString();
		}
		return null;
	}

	@Override
	public Map<String, Object> selectBoard(Map<String, Object> map) {
		return boardDao.selectBoard(map);
	}

	@Override
	public boolean updateBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.update(map);
		return affectRowCount ==1;
	}

	@Override
	public boolean deleteBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.delete(map);
		return affectRowCount == 1;
	}

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return boardDao.selectList(map);
	}

}
