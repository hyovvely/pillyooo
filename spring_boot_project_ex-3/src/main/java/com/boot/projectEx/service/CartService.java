package com.boot.projectEx.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boot.projectEx.dao.ICartDAO;
import com.boot.projectEx.model.CartVO;
import com.boot.projectEx.model.MemberVO;
import com.boot.projectEx.model.OrderInfoVO;

@Service
public class CartService implements ICartService {
	@Autowired
	@Qualifier("ICartDAO")
	ICartDAO dao;

	@Override
	public void insertCart(CartVO vo) {
		dao.insertCart(vo);
	}

	@Override
	public int checkPrdInCart(String prdNo, String memId) {
		// 전달받은  prdNo와 memId를 map에 넣어 Mapper에게 전달
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("prdNo", prdNo);
		map.put("memId", memId);
		
		return dao.checkPrdInCart(map);
	}

	@Override
	public void updateQtyInCart(CartVO vo) {
		dao.updateQtyInCart(vo);
	}

	@Override
	public ArrayList<CartVO> cartList(String memId) {		
		return dao.cartList(memId);
	}
	
	@Override
	public 	void updateCart(CartVO vo) { // 장바구니 수정
		dao.updateCart(vo);
	}
	
	@Override
	public void deleteCart(String cartNo) { //장바구니 삭제
		dao.deleteCart(cartNo);
	}
	
	@Override
	public MemberVO getMemberInfo(String memId) { //주문서에 사용할 회원정보 알아오기
		return dao.getMemberInfo(memId);
	}
	
	
	@Override	
	public  void insertOrder(OrderInfoVO ordInfoDto) {		
	
		//1. 주문 정보 저장  (order_info 테이블)
		dao.insertOrderInfo(ordInfoDto);
		
		//2. 주문 상품 내용 저장 (order_product 테이블)
		//주문 상품 저장에 필요한 데이터를 hashmap에 넣어서 전달
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ordNo", ordInfoDto.getOrdNo());
		map.put("memId", ordInfoDto.getMemId());
		//주문 상품 저장
		dao.insertOrderProduct(map);
		
		//3. 주문 완료 후 장바구니 삭제
		dao.deletCartAfterOrder(ordInfoDto.getMemId()); 
	}	
	

}
