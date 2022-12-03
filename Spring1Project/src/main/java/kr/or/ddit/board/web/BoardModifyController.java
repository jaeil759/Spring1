package kr.or.ddit.board.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardModifyController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = boardService.selectBoard(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", detailMap);
		mav.setViewName("board/update");
		return mav;
	}
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public ModelAndView updateBoard(@RequestParam Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();
		boolean isUpdateSuccess = boardService.updateBoard(map);
		
		if(isUpdateSuccess) {
			String bo_no = map.get("bo_no").toString();
			mav.setViewName("redirect:/board/detail.do?bo_no=" + bo_no);
		}else {
			mav = update(map);
		}
		
		return mav;
	}
	@RequestMapping(value = "/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteBoard(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isDeleteSuccess = boardService.deleteBoard(map);
		if(isDeleteSuccess) {
			mav.setViewName("redirect:/board/list.do");
		}else {
			String bo_no = map.get("bo_no").toString();
			mav.setViewName("redirect:/board/detail.do?bo_no=" + bo_no	);
		}
		
		return mav;
	}
}
