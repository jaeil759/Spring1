package kr.or.ddit.Member.controller;


import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import kr.or.ddit.vo.Member;
import kr.or.ddit.Member.service.MemberService;

@Controller
@RequestMapping("/Member")
public class MemberController {

	@Inject
	private MemberService memberService;

	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	

	@RequestMapping("/error")
	public String error() {
		return "error";
	}


	@RequestMapping(value = "login_action", method = RequestMethod.GET)
	public String loginByGET() {
		return "redirect:/login";
	}

	@RequestMapping(value = "login_action", method = RequestMethod.POST)
	public String loginByPOST(@RequestParam String mem_id, @RequestParam String mem_pw, HttpSession session,
			@RequestParam(defaultValue = "false") boolean save_id_check, HttpServletResponse response) {
		String result = "";

		try {
			Member loginMember = memberService.login(mem_id, mem_pw);
			session.setAttribute("loginMember", loginMember);
			if (save_id_check) {
				response.addCookie(new Cookie("saved_id", mem_id));
			}
			result = "redirect:/login";
		} catch (Exception e) {
			session.setAttribute("msg", e.getMessage());
			session.setAttribute("Mem_id", mem_id);
			result = "redirect:/error";
			e.printStackTrace();
		}
		return result;
	}

	// ------------------------------------------------------
	protected static String get_member_id_from_session(HttpSession session) {
		if (session.getAttribute("loginMember") == null || session.getAttribute("loginMember").equals("")) {
			return "";
		}

		Member member = (Member) session.getAttribute("loginMember");
		return member.getMem_id();
	}


}
