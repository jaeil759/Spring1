package kr.or.ddit.Member.mapper;

import java.util.List;

import kr.or.ddit.vo.Member;


public interface MemberMapper {
	public List<Member> selectAll();
	
	public Member selectmemberById(String mem_id);
	
	public int isExistedMember(String mem_id);

}
