package kr.or.ddit.Member.service;

import java.util.List;


import kr.or.ddit.vo.Member;

public interface MemberService {


	public Member login(String mem_id, String mem_pw) throws Exception;

	public List<Member> selectAll();

	Member selectMemberById(String mem_id);

}