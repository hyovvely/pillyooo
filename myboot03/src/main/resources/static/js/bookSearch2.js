/**
 * bookSearch.js
 	도서 검색 
 */
 
 $(document).ready(function(){
	$('#bookSearchFrm2').on('submit', function(){
		event.preventDefault();
		
		var formData = $(this).serialize();
		// serialize() : 폼에 입력한 값을 쿼리 스트링 방식의 데이터로 변환
		//type=bookName&keyword=노트북
		
		$.ajax({
			type:"post",
			url:"bookSearch2",
			data:formData,  
			uccess:function(result){   // 컨트롤러에서 반환한 prdList를 result가 받음
				$('#searchResultbox').html(result);
			},
			error:function(data, textStatus){
				alert("전송 실패");
			}
		});
	});	
});
 