package com.boot.projectEx.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.projectEx.model.MemberVO;
import com.boot.projectEx.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	// 로그인 폼으로 이동
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	// 로그인 처리 : id와 pwd 전달 받아서 로그인 체크
	@ResponseBody
	@RequestMapping("/login")
	public String loginCheck(@RequestParam HashMap<String, Object> param,
											   HttpSession session) {
		
		// 로그인 체크 결과
		MemberVO vo = service.loginCheck(param);
		String result = "fail";
		
		if(vo != null) {
			//로그인 성공하면 세션 변수 지정
			session.setAttribute("sid", vo.getMemId());
			result = "success";
		}
		
		return result;
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 폼으로 이동
	// 로그인 폼으로 이동
		@RequestMapping("/joinForm")
		public String joinForm() {
			return "/member/joinForm";
		}
		
		
		// 사용자 아이디 중복 확인
		@ResponseBody
		@RequestMapping("/memIdCheck")
		public String memIdCheck(@RequestParam("memId") String memId) {
			// 서비스 호출 -> DAO -> Mapper -> prdNo가 존재하면 prdNo 반환
			String memId_result = service.memIdCheck(memId);
			
			String result = "use";
			if(memId_result != null) // prdNo_result가 받은 값이 있으면 (널이 아니면)
				result = "no_use";
			
			return result;
		}
}









