<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>전체 도서 조회 JSP</title>
	</head>
	<body>
		<h3>전체 도서 조회</h3>
			<table border="1" width="600">
				<tr><th>도서번호</th><th>도서명</th><th>저자</th>
						<th>발행연도</th><th>가격</th><th>출판사</th><th>사진</th></tr>
		   <c:choose>
			<c:when test="${empty bookList}">
				<tr align="center"><td colspan="7">찾는 도서가 없습니다.</td></tr>
			</c:when>
			<c:otherwise>
			   <c:forEach items="${bookList }" var="book">
			   	<tr><td><a href="<c:url value='/book/detailViewBook/${book.bookNo}'/>">${book.bookNo }</a></td>
			   			<td>${book.bookTitle }</td>
			   			<td>${book.bookAuthor }</td>
			   			<td>${book.bookYear }</td>
			   			<td>${book.bookPrice }</td>
			   			<td>${book.bookPublisher }</td>
			   			<td><img src="<c:url value='/images/${book.bookNo}.jpg'/>" width="30" height="20">
			   					<!-- 또는  -->
			   			        <img src="/mybatis/images/${book.bookNo}.jpg" width="30" height="20"></td>
			   	</tr>
			   </c:forEach>
			   </c:otherwise>
		   </c:choose>
		  </table><br>
			<a href="/mybatis/">메인 화면으로 이동</a><br>
			<a href="<c:url value='/' />">메인 화면으로 이동</a> <!-- 같은 표현 -->
			
	</body>
</html>