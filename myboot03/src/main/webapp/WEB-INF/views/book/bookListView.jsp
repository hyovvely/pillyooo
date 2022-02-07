<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>전체 도서 조회</title>
	</head>
	<body>
			<h3>전체 도서 조회</h3>
			<table border="1" width="600">
				<tr><th>도서번호</th><th>도서명</th><th>저자</th>
						<th>발행연도</th><th>가격</th><th>출판사</th></tr>
			
			   <c:forEach items="${bookList }" var="book">
			   	<tr><td><a href="<c:url value='/book/detailViewBook/${book.bookNo}'/>">${book.bookNo }</a></td>
			   			<td>${book.bookTitle }</td>
			   			<td>${book.bookAuthor }</td>
			   			<td>${book.bookYear }</td>
			   			<td>${book.bookPrice }</td>
			   			<td>${book.bookPublisher }</td>
			   			</tr>
			   </c:forEach>
			</table><br>
			
			<a href="<c:url value='/'/>">메인 화면으로 이동</a>
			
	</body>
</html>