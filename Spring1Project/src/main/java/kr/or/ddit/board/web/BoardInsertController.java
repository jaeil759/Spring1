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
public class BoardInsertController {
	
	@Inject
	BoardService boardService;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public ModelAndView boardForm() {
		return new ModelAndView("board/form");
	}
	
	@RequestMapping(value = "/form.do", method= RequestMethod.POST)
	public ModelAndView insertBoard(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		String bo_no = boardService.insertBoard(map);
		if(bo_no == null) {
			mav.setViewName("redirect:/board/form.do");
		}else {
			mav.setViewName("redirect:/board/detail.do?bo_no=" + bo_no);
		}
		return mav;
	}
}
