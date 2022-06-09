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

	var regular_id = /^[a-zA-Z0-9]{3,16}$/;

	//문서내 element배치가 완료가 되면,,, 
	$(document).ready(function(){
		
		//아이디 입력창에서 키가 입력되면,,,(키가 눌리면 시스템에 의해 자동호출되는 함수 -> 콜백함수이자 익명함수)  
		$("#m_id").keyup(function(event){
						//this는 자기 자신 $("m_id")이다.	
			var m_id = $(this).val();
			//console.log(m_id);
			
			if(regular_id.test(m_id)==false){
				$("#id_msg").html("영문자(대/소문자) 숫자 조합의 3~16자리만 가능합니다.").css("color", "red");
				$("#btn-register").attr("disabled", true);
				return;
			}
			
			//$("#id_msg").html("정규식 만족합니다").css("color", "blue");
			
			
			//서버에 사용가능 여부 확인(Ajax)
			$.ajax({
				url	 	:'check_id.do', 				//MemberCheckAction
				data	:{'m_id':m_id},					//parameter :check_id.do?m_id=one
				dataType:'json',
				success :function(res_data){
					 //res_data = {"result": true} 사용가능//
					 //res_data = {"result": false} 사용불가//
					 
					if(res_data.result){ //사용가능한 아이디
						$("#id_msg").html("사용가능한 아이디입니다.").css("color", "blue");
						
						//가입하기 버튼 활성화
						$("#btn-register").attr("disabled", false);
					 
					 		
					}else{//이미 사용중인 아이디
						$("#id_msg").html("이미 사용중인 아이디입니다.").css("color", "red");
						//가입버튼 비활성화
						$("#btn-register").attr("disabled", true);
					}
				},
				error   :function(err){
					alert(err.responseText);
				}
			});
		});
		
	});
	
	
	function find(){
		var width  = 500; //팝업의 너비
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
			alert('이름을 입력해주세요.');
			f.m_name.value='';
			f.m_name.focus();
			return;
		}
		
		if(m_pwd==''){
			alert('비밀번호를 입력해주세요.');
			f.m_pwd.value='';
			f.m_pwd.focus();
			return;
		}
		
		if(m_zipcode==''){
			alert('우편번호를 입력해주세요.');
			f.m_zipcode.value='';
			f.m_zipcode.focus();
			return;
		}
		
		if(m_addr==''){
			alert('주소를 입력해주세요.');
			f.m_addr.value='';
			f.m_addr.focus();
			return;
		}
		
		
		f.action="insert.do" //MemberInsertAction
		f.submit();
	}
</script>
</head>
<body>
<%-- 
현재 Root Context 경로 : ${pageContext.request.contextPath } --%>

<form>
	<div id="box">
		<div class="panel panel-primary">
        	<div class="panel-heading"><h4>회원가입</h4></div>
        	<div class="panel-body">
        		<table class="table table-striped">
        			<tr>
        				<th>이름</th>
        				<td><input name="m_name"></td>
        			</tr>
        			
        			<tr>
        				<th>아이디</th>
        				<td>
        					<input name="m_id" id="m_id">
        					<span id="id_msg"></span>
        				</td>
        			</tr>
        			
        			<tr>
        				<th>비밀번호</th>
        				<td><input type="password" name="m_pwd"></td>
        			</tr>
        			
        			<tr>
        				<th>우편번호</th>
        				<td>
        					<input id="m_zipcode" name="m_zipcode">
        					<input class="btn btn-warning" type="button" value="주소찾기" onclick="find();">
        				</td>
        			</tr>
        			
        			<tr>
        				<th>주소</th>
        				<td><input id="m_addr" name="m_addr" size="60"></td>
        			</tr>
        			
        			<tr>
        				<td colspan="2" align="center">
        					<input class="btn btn-primary" type="button" value="가입하기" id="btn-register" disabled="disabled" onclick="send(this.form);"> <!-- 실시간으로(id값이 입력될 때마다 사용가능한지 체크. onkeydown이벤트를 사용할 듯) 사용자가 입력한 아이디에 대한 사용 가능 여부(기본키 이므로 값이 겹치면 안됨)를 파악(ajax로 할 듯)해서 가능하면 btn풀기, 불가능하면 버튼 막기 -->
        					<input class="btn btn-success" type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath }/photo/list.do';">
        				</td>
        			</tr>
        			
        		</table>
        	</div>
    	</div>
	</div>
</form>
</body>
</html>