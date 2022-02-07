/**
 * 도서번호 중복 확인
 */
 
 $(document).ready(function(){
	$('#bookNoCheckBtn').on('click', function(){
		event.preventDefault();
		alert('bookNoCheckBtn Click!');
		
		$.ajax({
			type:"post",
			url:"bookNoCheck",
			data:{"bookNo":$('#bookNo').val()},  /* 컨트롤러에서 받을 때 : prdNo로 받음*/
			dataType:'text',
			success:function(result){
				if(result == "no_use"){
					alert("사용할 수 없는 번호입니다.");
				}else{
				    alert("사용 가능한 번호입니다.");
			 }
			},
			error:function(data, textStatus){
				alert("전송 실패");
			}
		});
	});
});