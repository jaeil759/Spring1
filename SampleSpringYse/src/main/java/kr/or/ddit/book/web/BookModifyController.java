package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.BookService;

@Controller
@RequestMapping("/book")
public class BookModifyController {
	
	@Inject
	private BookService bookService;
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = bookService.selectBook(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("book", detailMap);
		mav.setViewName("book/update");
		// update.jsp를 보여준다
		return mav;
	}
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public ModelAndView updateBook(@RequestParam Map<String, Object> map) {
		/*
		 * {
		 * 	"bookId" : 1,
		 *  "title"  : "제목 수정",
		 *  "category" : "IT",
		 *  "price" : "1000" 
		 * }
		 * 
		 * */
		ModelAndView mav = new ModelAndView();
		boolean isUpdateSuccess = bookService.updateBook(map);
		
		if(isUpdateSuccess) {
			// 업데이트가 정상적으로 데이터 갱신되었을 때 확인을 위해 상세 페이지로 이동한다.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}else {
			// 갱신이 되지 않앗을 경우, GET 메소드로 redirect 하는 방법도 잇지만
			// 상세보기 화면을 바로 보여줄 수도 있다. (update()함수 이용해서 (GET방식의 메소드))
			mav = update(map);
		}
		
		return mav;
	}
	@RequestMapping(value = "/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// 삭제가 성공했는지 확인한다.
		boolean isDeleteSuccess = bookService.deleteBook(map);
		if(isDeleteSuccess) {
			// 삭제가 성공했으면 상세 페이지가 없으므로 목록으로 redirect 한다.
			mav.setViewName("redirect:/book/list.do");
		}else {
			// 삭제가 실패했으면 다시 상세 페이지로 이동한다.
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		
		return mav;
	}
}
