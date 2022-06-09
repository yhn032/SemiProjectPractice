<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 다음 우편번호 검색 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- SweetAlert2 Library -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">
	#box{
		width: 800px;
		margin: auto;
		margin-top: 60px;
	}
	
	#id_msg{
		margin-left: 10px;
		display: inline-block;
		width: 350px;
	}
</style>


<script type="text/javascript">
	
	function find(){
		var width = 500; //팝업의 너비
		var height = 600; //팝업의 높이
		
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            // data = {'zonecode':12345, 'address':'서울시 마포구 노고산동', ...} 이런식으로 data가 json 형식으로 들어온다.
	            
	            //선택된 주소의 우편번호 넣기 
	            $("#m_zipcode").val(data.zonecode);
	            
	            //선택된 주소를 넣기 
	            $("#m_addr").val(data.address);
	        },
	        theme: {
	            searchBgColor: "#0B65C8", //검색창 배경색
	            queryTextColor: "#FFFFFF" //검색창 글자색
	        },
	        width: width,
	        height: height
	    }).open(
	    		//주소검색 팝업창을 중앙에 배치하겠다.
	    		{
	    		    left: (window.screen.width / 2) - (width / 2),
	    		    top: (window.screen.height / 2) - (height / 2)
	    		}		
	    );
		
	}
	
	
	function send(f){
		var m_name    = f.m_name.value.trim();
		var m_pwd     = f.m_pwd.value.trim();
		var m_zipcode = f.m_zipcode.value.trim();
		var m_addr    = f.m_addr.value.trim();
		
		if(m_name==''){
			//swal이 비동기 함수이므로 focus함수가 실행되지 않는다. 
			//focus함수가 실행은 되지만 swal의 확인 버튼을 누르면 포커스가 알림창으로 빼앗긴다.
			//해결 방법1
			Swal.fire({
				  icon: 'error',
				  title: '이름을 입력하세요...',
				  text: '수정할 이름이 비어있습니다.',
				  returnFocus: false
			}).then((result) => {
				
				if(result.isConfirmed){
					f.m_name.value='';
					f.m_name.focus();
				}
				
			});
			return;
		}
		
		if(m_pwd==''){
			Swal.fire({
				  icon: 'error',
				  title: '비밀번호를 입력하세요...',
				  text: '수정할 비밀번호가 비어있습니다.',
				  returnFocus: false
			}).then((result) => {
				
				if(result.isConfirmed){
					f.m_pwd.value='';
					f.m_pwd.focus();
				}
				
			});
			return;
		}
		
		//방법2-1. lambda
		if(m_zipcode==''){
			Swal.fire({
				  icon: 'error',
				  title: '우편번호를 입력하세요...',
				  text: '수정할 우편번호가 비어있습니다.',
				  didClose: () =>{
					  f.m_zipcode.value='';
				      f.m_zipcode.focus();
				  }
			});
			return;
		}
		
		if(m_addr==''){
			//방법2
			Swal.fire({
				  icon: 'error',
				  title: '주소를 입력하세요...',
				  text: '수정할 주소가 비어있습니다.',
				  returnFocus: false,
				  didClose: function(){
					  f.m_addr.value='';
				      f.m_addr.focus();
				  }
			});
			return;
		}
		
		
		Swal.fire({
			  title: '정말 수정하시겠습니까?',
			  //html: "<h4>선택한 사용자가 삭제됩니다.</h4>", text 대신 html로 사용가능
			  text: "선택한 사용자가 수정됩니다.",
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '수정',
			  cancelButtonText: '취소',
			  
			}).then((result) => {
				
				//수정버튼을 누르면,,
				if(result.isConfirmed){
					f.action="modify.do" //MemberModifyAction
					f.submit();					
				}
				
			});
		
	}
</script>


<script type="text/javascript">
	//현재 element가 배치가 완료되면 자동 호출
	$(document).ready(function (){
		
		//제이쿼리 $와 EL표현식의 $가 충돌할 가능성이 있기 때문에 EL표현식을 '' 싱글 쿼테이션으로 감싸야 한다.
		//수정폼에 원래 회원등급을 설정
		$("#m_grade").val('${vo.m_grade}')
		
	});
</script>
</head>
<body>
<form>
	<!-- 세션 트래킹 기법 중 하나이다. -->
	<input type="hidden" name="m_idx" value="${vo.m_idx }">
	<div id="box">
		<div class="panel panel-primary">
        	<div class="panel-heading"><h4>회원수정</h4></div>
        	<div class="panel-body">
        		<table class="table table-striped">
        			<tr>
        				<th>이름</th>
        				<td><input name="m_name" value="${vo.m_name }"></td>
        			</tr>
        			
        			<tr>
        				<th>아이디</th>
        				<td>
        					<input name="m_id"  value="${vo.m_id}" readonly="readonly">
        				</td>
        			</tr>
        			
        			<tr>
        				<th>비밀번호</th>
        				<td><input type="password" name="m_pwd" value="${vo.m_pwd}"></td>
        			</tr>
        			
        			<tr>
        				<th>우편번호</th>
        				<td>
        					<input id="m_zipcode" name="m_zipcode" value="${vo.m_zipcode}">
        					<input class="btn btn-warning" type="button" value="주소찾기" onclick="find();">
        				</td>
        			</tr>
        			
        			<tr>
        				<th>주소</th>
        				<td><input id="m_addr" name="m_addr" value="${vo.m_addr}" size="60"></td>
        			</tr>
        			
        			<!-- 등급 수정 폼 -->
        			<!-- id는 클라이언트쪽에서 핸들링 하기 위한 선택자 정보, name은 서버 쪽으로 넘기기 위한 파라미터 이름 정보 -->
        			<tr>
        				<th>회원등급</th>
        				<td>
        					<select id="m_grade" name="m_grade">
        						<option value="일반">일반</option>
        						<option value="관리자">관리자</option>
        					</select>
        				</td>
        			</tr>
        			
        			<tr>
        				<td colspan="2" align="center">
        					<input class="btn btn-primary" type="button" value="수정하기" onclick="send(this.form);"> 
        					<input class="btn btn-success" type="button" value="목록보기" onclick="location.href='list.do';">
        				</td>
        			</tr>
        			
        		</table>
        	</div>
    	</div>
	</div>
</form>
</body>
</html>