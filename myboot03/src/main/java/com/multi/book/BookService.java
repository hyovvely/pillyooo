package com.multi.book;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service // Service 컴포넌트 되있어야 다른 패키지의 컨트롤러에서 객체 생성없이 사용할 수 있다 
public class BookService implements IBookService {

	@Autowired
	@Qualifier("IBookDAO")
	IBookDAO dao;
	
	@Override
	public ArrayList<BookVO> listAllBook() {
		// TODO Auto-generated method stub
		return dao.listAllBook();
	}

	@Override
	public void insertBook(BookVO bookVo) {
		dao.insertBook(bookVo);
	}

	@Override
	public void updateBook(BookVO bookVo) {
		dao.updateBook(bookVo);
	}

	@Override
	public void deleteBook(String bookVo) {
		dao.deleteBook(bookVo);
	}

	@Override
	public BookVO detailViewBook(String bookNo) {
		// TODO Auto-generated method stub
		return dao.detailViewBook(bookNo);
	}
	
	@Override
	public String bookNoCheck(String bookNo) {
		return dao.bookNoCheck(bookNo);
	}
	
	@Override
	public ArrayList<BookVO> bookSearch(HashMap<String, Object> map){
		return dao.bookSearch(map);
	} 
	// 기능 구현할 때 /bookSearch 메소드 BookVO 리턴 타입 /dao.bookSearch의 결과를 Arraylist로 

}
