package com.iGame.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	
	public Member getMember(Integer memberId){
		return memberRepository.findOne(memberId);
	}
	
	public List<Member> getAllMembers(){
		List<Member> memberList = new ArrayList<>();
		memberRepository.findAll().forEach(memberList::add);
		return memberList;
	}
	
	public void addMember(Member member){
		memberRepository.save(member);
	}
	
	public void updateMember(Integer memberId, Member member){
		memberRepository.save(member);
	}
	
	public void deleteMember(Integer memberId){
		memberRepository.delete(memberId);
	}
	
	
}
