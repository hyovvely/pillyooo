package com.boot.projectEx.service;

import java.util.ArrayList;

import com.boot.projectEx.model.CartVO;
import com.boot.projectEx.model.MemberVO;
import com.boot.projectEx.model.OrderInfoVO;

public interface ICartService {
	void insertCart(CartVO vo);	// 장바구니에 추가
	int checkPrdInCart(String prdNo, String memId);  //동일 상품 존재 여부 확인
	void updateQtyInCart(CartVO vo);  //동일 상품이 존재하면 수량 변경
	ArrayList<CartVO> cartList(String memId);   // 장바구니 목록
	
	// 주문 처리 작업
	void updateCart(CartVO vo); // 장바구니 수정
	void deleteCart(String cartNo); //장바구니 삭제
	MemberVO getMemberInfo(String memId); //주문서에 사용할 회원정보 알아오기
	
	//주문 내역 저장
	void insertOrder(OrderInfoVO ordInfoDto);	
}
