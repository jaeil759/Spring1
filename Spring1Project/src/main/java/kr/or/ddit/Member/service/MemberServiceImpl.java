package kr.or.ddit.Member.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.exception.PasswordMismatchException;

import kr.or.ddit.Member.repository.MemberRepository;
import kr.or.ddit.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberRepository memberRepository;

	
	@Override
	public Member login(String mem_id, String mem_pw) throws Exception {
		Member loginMember = memberRepository.selectMemberById(mem_id);
		
		if (loginMember == null) {
			throw new UserNotFoundException("존재하지 않는 아이디 입니다.");
		}
		
		if (!loginMember.isMatchPassword(mem_pw)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		
		return loginMember;
	}
	
	@Override
	public List<Member> selectAll() {
		return memberRepository.selectAll();
	}
	
	@Override
	public Member selectMemberById(String mem_id) {
		return memberRepository.selectMemberById(mem_id);
	}
	
	
}

