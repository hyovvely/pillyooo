/**
 * bookSearch.js
 	도서 검색 
 */
 
 $(document).ready(function(){
	$('#bookSearchFrm').on('submit', function(){
		event.preventDefault();
		
		var formData = $(this).serialize();
		// serialize() : 폼에 입력한 값을 쿼리 스트링 방식의 데이터로 변환
		//type=bookName&keyword=노트북
		
		$.ajax({
			type:"post",
			url:"bookSearch",
			data:formData,  
			success:function(result){   // 컨트롤러에서 반환한 bookList를 result가 받음
				//alert(result.length);
				// 반환된 결과(ArrayList<BookVO>)를  searchResultbox에 테이블 형태로 출력
				$('#searchResultbox').empty();
				$('#searchResultbox').append('<table id="resultTable" border="1" width="600">' +
																	'<tr><th>도서번호</th><th>도서명</th><th>저자</th>' + 
																	'<th>발행연도</th><th>가격</th><th>출판사</th></tr>');
				if(result == ""){
					$('#resultTable').append('<tr align="center"><td colspan="6">찾는 도서가 없습니다 </td></tr>');
				}else{
					for(var i=0; i <result.length; i++){
						$('#resultTable').append('<tr><td>' + result[i].bookNo + '</td><td>' +
											               result[i].bookTitle + '</td><td>' +
											               result[i].bookAuthor+ '</td><td>' +
											               result[i].bookYear + '</td><td>' +
											               result[i].bookPrice + '</td><td>' +
											               result[i].bookPublisher + '</td><td>' +
					       '<img src="/mybatis/images/' + result[i].bookNo + '.jpg" width="30" height="20"></td></tr>');
					}
				}
				
				$('#searchResultbox').append('</table>');
			},
			error:function(data, textStatus){
				alert("전송 실패");
			}
		});
	});	
});
 