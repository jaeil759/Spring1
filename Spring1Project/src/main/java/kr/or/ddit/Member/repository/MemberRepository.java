package kr.or.ddit.Member.repository;

import java.util.List;

import kr.or.ddit.vo.Member;


public interface MemberRepository {
	
	public List<Member> selectAll();
	public Member selectMemberById(String mem_id);
	public boolean isExistedMember(String mem_id);
	
}
	
