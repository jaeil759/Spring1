package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.BookService;

/*
 * @Controller 어노테이션이 있는 클래스는 스프링이 브라우저 요청(request)를 받아들이는 컨트롤러라고 인지해서
 * 자바 빈(Java Bean)으로 등록해서 관리한다.
 */
@Controller
@RequestMapping("/book")
// 맨 처음 /book 이라는 url요쳥이 오면 이 컨트롤러로 매핑 된다 !
public class BookInsertController {
	
	@Inject
	BookService bookService;
	
	/*
	 * @RequestMapping 
	 * - 요청 URL을 어떤 메소드가 처리할 지 여부를 결정
	 * 
	 * method 속성은 http 요청 메소드를 의미한다.
	 * 일반적인 웹 페이지 개발에서 GET 메소드는 데이터를 변경하지 않는 경우에,
	 * POST 메소드는 데이터가 변경될 경우 사용한다.
	 * 
	 * ModelAndView는 컨트롤러가 반환할 데이터를 담당하는 모델(Model)과 화면을 담당하는 뷰(View)의 경로를 합쳐놓은 객체다.
	 * ModelAndView의 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주한다.
	 * */
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	// 컨텍스트 루트 아래 /form.do로 오면  이 메소드로 요청이 옴
	public ModelAndView bookForm() {
		return new ModelAndView("book/form");
	}
	
	/*
	 * 데이터의 변경이 일어나므로 http 메소드는 POST방식으로 처리
	 * 어노테이션(@) RequestParam은 HTTP 파라미터를 map 변수에 자동으로 바인딩한다.
	 * Map타입의 경우는 어노테이션(@) RequestParam을 붙여야만 HTTP 파라미터의 값을 map 안에 바인딩해준다.
	 * */
	@RequestMapping(value = "/form.do", method= RequestMethod.POST)
	public ModelAndView insertBook(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// 서비스 메소드 insertBook을 호출한다.
		String bookId = bookService.insertBook(map);
		if(bookId == null) {
			// 데이터 입력이 실패할 경우 다시 데이터를 입력 받아야 하므로 생성 화면으로 redirect 한다.
			// ModelAndView 객체는 .setViewName() 메소드를 통해 뷰의 경로를 지정할 수 있다.
			mav.setViewName("redirect:/book/form.do");
		}else {
			// 뷰의 경로가 redirect: 로 시작하면 스프링은 뷰 파일을 찾아가는 것이 아니라 웹 페이지의 주소(/detail.do)를 변경한다.
			// form.do > detail.do
			// 데이터 입력이 성공하면 상세 페이지로 이동한다.
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		return mav;
	}
}
