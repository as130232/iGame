package com.iGame.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/member/{memberId}")
	public Member getMember(@PathVariable Integer memberId){
		return memberService.getMember(memberId);
	}
	
	@RequestMapping("/members")
	public List<Member> getAllMembers() {
		System.out.println("**************");
		Long memberCount = memberService.getAllMembers().stream().count();
		System.out.println("memberCount:" + memberCount);
		return memberService.getAllMembers();
	}
	
	@RequestMapping(value = "/member", method=RequestMethod.POST)
	public void addMember(@RequestBody Member member){
		memberService.addMember(member);
	}
	
	@RequestMapping(value = "/member/{memberId}", method=RequestMethod.PUT)
	public void updateMember(@RequestBody Member member, @PathVariable Integer memberId){
		memberService.updateMember(memberId, member);
	}
	
	@RequestMapping(value = "/member/{id}", method=RequestMethod.DELETE)
	public void deleteMember(@PathVariable Integer memberId){
		memberService.deleteMember(memberId);
	}
	
	
}
