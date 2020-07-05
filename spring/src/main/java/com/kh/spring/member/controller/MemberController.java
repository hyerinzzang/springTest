package com.kh.spring.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcry;
	
	// 로그인
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String memberLogin(Member m, Model model) {
	
		Member loginUser = mService.loginMember(m);
		
//		System.out.println("con loginUser: " + loginUser);
		
		if(bcry.matches(m.getPwd(), loginUser.getPwd())) {
			model.addAttribute("loginUser",loginUser);
			
			return "home";
		} else {
			System.out.println("로그인 실패");
			return "home";
		}
		
	}
	
	// 로그아웃
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();
		return "home";
	}
	
	// 회원가입 view 이동
	@RequestMapping(value="enrollview", method=RequestMethod.GET)
	public String enrollView() {
		return "member/memberJoin";
	}
	
	
	// 회원가입
	@RequestMapping(value="minsert", method=RequestMethod.POST)
	public String memberInsert(Member m, Model model,
							@RequestParam("post") String post,
							@RequestParam("address1") String address1,
							@RequestParam("address2") String address2 ) {
//		System.out.println("con insert m : " + m);
		
		String encPwd = bcry.encode(m.getPwd());
		m.setPwd(encPwd);
		m.setAddress(post+","+address1+","+address2);
		
		int result = mService.insertMember(m);
//		System.out.println("con result : " + result);
		
		if(result <= 0) {
			System.out.println("회원가입 실패");
		}
		
		return "home";
	}
	
	// 아이디 유효성검사
	@RequestMapping("dupid")
	public void idDuplicateCheck(String id, HttpServletResponse response) throws IOException {
		boolean isUsable = mService.checkIdDup(id) == 0 ? true : false;
		System.out.println("con isUsable : " + isUsable);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(isUsable);
		pw.flush();
		pw.close();
	}
	
	// 정보수정 view
	@RequestMapping("myinfo")
	public String myInfoView() {
		return "member/mypage";
	}
	
	// 정보수정
	@RequestMapping("mupdate")
	public String memberUpdate(Member m, Model model,
								@RequestParam("post") String post,
								@RequestParam("address1") String address1,
								@RequestParam("address2") String address2) {
		
		m.setAddress(post+","+address1+","+address2);
		
		int result = mService.updateMember(m);
//		System.out.println("con update : " + result);
		if(result <= 0 ) {
			System.out.println("회원 정보 수정 실패");
		} else {
			model.addAttribute("loginUser", m);
		}
		return "home";
				
	}
	
	@RequestMapping("mdelete")
	public String memberDelete(String id) {
		int result = mService.deleteMember(id);
		
		if(result >0) {
			return "redirect:logout";
		} else {
			System.out.println("탈퇴 실패");
			return "redirect:home";
		}
	}
}
