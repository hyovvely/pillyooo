package com.multi.book;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class BookController {
	@Autowired
	BookService service;
	
	
	@RequestMapping("/")
	public String viewIndex() {
		return "index";
	}
	
	// 전체 상품 조회
	@RequestMapping("/book/listAllBook")
	public String listAllBook(Model model) {
		ArrayList<BookVO> bookList = service.listAllBook();
		model.addAttribute("bookList", bookList);		
		return "book/bookListView";
	}
	
	// 도서 등록폼으로 이동 
	@RequestMapping("/book/newBookForm")
	public String newBookForm() {
		return "book/newBookForm";
	}
	
	// 도서 등록
	@RequestMapping("/book/insertBook")
	public String insertBook(BookVO book) {
		
		service.insertBook(book);
		return "redirect:./listAllBook";
	}
	
	// 도서 상세 정보 페이지로 이동
	@RequestMapping("/book/detailViewBook/{bookNo}")
	public String detailViewBook(@PathVariable String bookNo, Model model) {
		
		BookVO book = service.detailViewBook(bookNo);
		model.addAttribute("book",book);
		
		
		return "book/bookDetailView";
	}
	
	// 도서 정보 수정 화면 이동
	@RequestMapping("/book/updateBookForm/{bookNo}")
	public String updateBookForm(@PathVariable String bookNo, Model model) {
		
		BookVO book = service.detailViewBook(bookNo);
		model.addAttribute("book",book);
		return "book/updateBookForm";
	}
	
	// 상품 정보 수정
	@RequestMapping("/book/updateBook")
	public String detailViewBook(BookVO book) {
		
		service.updateBook(book);
	
		return "redirect:./listAllBook";
	}
	
	// 상품 정보 삭제 
	@RequestMapping("/book/deleteBook/{bookNo}")
	public String deleteBook(@PathVariable String bookNo) {
		
		service.deleteBook(bookNo);
	
		return "redirect:../listAllBook";
	}
	
	// 도서번호 중복 확인 
		@ResponseBody
		@RequestMapping("/book/bookNoCheck")
		public String bookNoCheck(@RequestParam("bookNo") String bookNo) {
			
			String bookNo_result = service.bookNoCheck(bookNo);
			
			String result = "use";
			if(bookNo_result != null) {
				result = "no_use";
			}
			return result;
		}
		
		// 도서 검색 폼으로 이동
		@RequestMapping("/book/bookSearchForm")
		public String bookSearchForm() {
			return "book/bookSearchForm";
		}
		
		// 도서 검색
		@ResponseBody
		@RequestMapping("/book/bookSearch")
		public ArrayList<BookVO> bookSearch(@RequestParam HashMap<String, Object> param,
													Model model){
		 System.out.println("a");
			ArrayList<BookVO> bookList = service.bookSearch(param);
			model.addAttribute("bookList", bookList);
			
			return bookList;
		}
		// 도서 검색 폼2으로 이동
			@RequestMapping("/book/bookSearchForm2")
			public String bookSearchForm2() {
				return "book/bookSearchForm2";
			}
			
			// 도서 검색 
			@RequestMapping("/book/bookSearch2")
			public String bookSearch2(@RequestParam HashMap<String, Object> param, 
																			Model model){
				ArrayList<BookVO> bookList = service.bookSearch(param);
				model.addAttribute("bookList", bookList);
			
				return "book/bookSearchResultView"; // 뷰 페이지 반환 

			}
		
}
