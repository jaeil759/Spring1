package kr.or.ddit.Member.repository;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


import kr.or.ddit.vo.Member;
import kr.or.ddit.Member.mapper.MemberMapper;


@Repository
public class MemberRepositoryImpl implements MemberRepository {
	@Inject
	SqlSessionTemplate sqlSessionTemplate;	
	private MemberMapper memberMapper;


	@Override
	public List<Member> selectAll() {
		sqlSessionTemplate.selectList("Member.loginMember");
		return memberMapper.selectAll();
	}

	@Override
	public Member selectMemberById(String mem_id) {
		sqlSessionTemplate.selectOne("Member.selectMemberById",mem_id);
		return memberMapper.selectmemberById(mem_id);
	}

	@Override
	public boolean isExistedMember(String mem_id) {
		sqlSessionTemplate.selectOne("Member.isExistedMember",mem_id);
		boolean isExist = false;
		int memberCount = memberMapper.isExistedMember(mem_id);
		
		if (memberCount == 1) {
			isExist = true;
		}
		
		return isExist;
	}


	

}
