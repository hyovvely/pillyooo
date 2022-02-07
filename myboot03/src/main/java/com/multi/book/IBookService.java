package com.multi.book;

import java.util.ArrayList;
import java.util.HashMap;



public interface IBookService {
	ArrayList<BookVO> listAllBook();
	void insertBook(BookVO bookVo);
	void updateBook(BookVO bookVo);
	void deleteBook(String bookVo);
	BookVO detailViewBook(String bookNo); // 하나의 상품만 보니까 !! // 상품 상세 조회
	String bookNoCheck(String bookNo);
	ArrayList<BookVO> bookSearch(HashMap<String, Object> map); // 도서 검색 

}