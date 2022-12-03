package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public String insertBoard(Map<String, Object> map);
	public Map<String, Object> selectBoard(Map<String, Object> map);
	public boolean updateBoard(Map<String, Object> map);
	public boolean deleteBoard(Map<String, Object> map);
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map);
}
