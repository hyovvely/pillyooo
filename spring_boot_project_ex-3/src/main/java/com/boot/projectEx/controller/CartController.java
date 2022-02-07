package com.boot.projectEx.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.projectEx.model.CartVO;
import com.boot.projectEx.model.MemberVO;
import com.boot.projectEx.model.OrderInfoVO;
import com.boot.projectEx.service.CartService;

@Controller
public class CartController {
	@Autowired
	CartService service;
	
	// 장바구니에 추가
	@RequestMapping("/product/insertCart")
	public String insertCart(CartVO vo, HttpSession session) {
		// 로그인 성공 시 설정한 세션 sid 값 가져와서 memId에 저장
		String memId = (String)session.getAttribute("sid");
		vo.setMemId(memId);

		// 동일 상품이 존재하는 지 확인
		int count = service.checkPrdInCart(vo.getPrdNo(), memId);
				
		if(count == 0) { // 동일 상품이 존재하지 않으면 장바구니에 추가
			service.insertCart(vo);
		} else { // 존재하면 주문수량 변경
			service.updateQtyInCart(vo);
		}		
		
		//장바구니 목록 요청
		return "redirect:/product/cartList";
	}
	
	//장바구니 목록 보기
	@RequestMapping("/product/cartList")
	public String cartList(Model model, HttpSession session) {
		String memId = (String) session.getAttribute("sid");
		ArrayList<CartVO> cartList = service.cartList(memId);
		model.addAttribute("cartList", cartList);
		
		return "product/cartListView";
	}
	
	// 장바구니 삭제
	@ResponseBody
	@RequestMapping("/product/deleteCart")
	public int deleteCart(@RequestParam("chbox[]") ArrayList<String> chkArr) {
		int result = 0;
		String cartNo = "";
		
		if(chkArr != null) {
			for(String i : chkArr) {
				cartNo = i;
				service.deleteCart(cartNo);
			}
			result = 1;
		}
		
		return result;
	}
	
	//주문서 작성
	@RequestMapping(value="/product/orderForm")
	public String viewOrderForm(@RequestParam String[] memId, 
														@RequestParam int[] cartNo, 
														@RequestParam int[] cartQty,
														Model model) {		
		
		//[주문하기] 누르면 변경된 주문수량을 적용하기 위해 먼저 update 수행
		for(int i=0; i<cartNo.length; i++) {
			CartVO dto = new CartVO();
			dto.setCartNo(cartNo[i]);
			dto.setCartQty(cartQty[i]);
			service.updateCart(dto);			
		}

		//주문서에 정보 출력 (주문자 정보 + 장바구니 목록)
		//주문자 정보 가져오기
		MemberVO memVo = service.getMemberInfo(memId[0]);
		//전화번호 split
		String[] hp = (memVo.getMemHP()).split("-");
		
		//주문자 정보 출력하기 위해 model에 저장
		model.addAttribute("memDto", memVo);
		model.addAttribute("hp1", hp[0]);
		model.addAttribute("hp2", hp[1]);
		model.addAttribute("hp3", hp[2]);
		
		//장바구니 목록 출력
		ArrayList<CartVO> cartList = service.cartList(memId[0]);			
		model.addAttribute("cartList", cartList);
		
		return "product/orderForm"; 
	}
	
	//주문완료
	@RequestMapping(value="/product/orderComplete")
	public String orderInsert(OrderInfoVO ordInfoDto,
												@RequestParam String hp1,
												@RequestParam String hp2,
												@RequestParam String hp3) {
		System.out.println("ss");
		
		//전화 번호 설정
		String hp = hp1 + "-" + hp2 + "-" + hp3;
		ordInfoDto.setOrdRcvPhone(hp);
		
		//주문번호 생성 및 설정
		//주문번호 생성 : 오늘날짜시분초_랜덤숫자4개
		long timeNum = System.currentTimeMillis();
		//날짜시간 포맷 : MM(월), mm(분), HH(시:24시간제) 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String  strTime = dayTime.format(new Date(timeNum));
		
		//랜덤 숫자 4개 생성
		String rNum = "";
		for(int i=1; i<=4; i++) {
			rNum += (int)(Math.random() * 10);
		}
		
		String ordNo = strTime + "_" + rNum;
		
		//주문번호 설정
		ordInfoDto.setOrdNo(ordNo);		
		
		//주문 정보 입력 (주문 상품 정보는 장바구니에서 바로 주문 테이블로 입력)
		service.insertOrder(ordInfoDto);		
		
		return "product/orderCompleteView";
	}

}







