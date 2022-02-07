<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring JSP</title>
	</head>
	<body>
		<h3>도서 상세 정보 조회</h3>
			<table border="1" width="300">
				<tr><td>도서번호</td><td>${book.bookNo }</td></tr>
				<tr><td>도서명</td><td>${book.bookTitle }</td></tr>
				<tr><td>저자</td><td>${book.bookAuthor }</td></tr>
				<tr><td>발행연도</td><td>${book.bookYear }</td></tr>
				<tr><td>가격</td><td>${book.bookPrice }</td></tr>
				<tr><td>출판사</td><td>${book.bookPublisher }</td></tr>
			</table><br>
			
			<!-- 도서 정보 수정 화면으로 이동  -->
			<%-- <a href="/book/book/updateBookForm/${book.bookNo}">도서 정보 수정</a><br><br>
			<!-- 또는 --> <br> --%>
			<a href="<c:url value='/book/updateBookForm/${book.bookNo}'/>">도서 정보 수정</a><br><br>

			<a href="/book/">메인 화면으로 이동</a>
			
			<!-- 도서 정보 삭제 : 삭제 여부 확인 처리 (자바스크립트로) -->
			<a href="javascript:deleteCheck();">도서 정보 삭제</a><br><br>
				<script type="text/javascript">
					function deleteCheck(){
						var answer = confirm("선택한 도서 삭제하시겠습니까?");
						if(answer == true){
							location.href="/book/deleteBook/${book.bookNo}";
						}
					}
				</script>
				
			<a href="<c:url value='/'/>">메인 화면으로 이동</a>
	</body>
</html>