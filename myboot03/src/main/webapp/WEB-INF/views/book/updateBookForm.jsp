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
		<h3>도서 등록</h3>
		
		<form method="post" action="<c:url value='/book/updateBook'/>">
			<table>
				<tr><td>도서번호</td><td><input type="text" name="bookNo" value="${book.bookNo}" readonly>></td></tr>
				<tr><td>도서명</td><td><input type="text" name="bookTitle" value="${book.bookTitle}"></td></tr>
				<tr><td>저자 </td><td> <input type="text" name="bookAuthor" value="${book.bookAuthor}"></td></tr>
				<tr><td>발행연도</td><td><input type="text" name="bookYear" value="${book.bookYear}"></td></tr>
				<tr><td>가격 </td> <td><input type="text" name="bookPrice" value="${book.bookPrice}"></td></tr>
				<tr><td>출판사 </td> <td><input type="text" name="bookPublisher" value="${book.bookPublisher}"></td></tr>
				<tr><td colspan="2"><input type="submit" value="수정"><input type="reset" value="취소"></td></tr>
			</table>
		</form>
	</body>
</html>