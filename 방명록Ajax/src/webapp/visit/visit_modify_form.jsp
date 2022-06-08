<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
	#box{
		width: 550px;
		margin: auto;
		margin-top: 20px;
	}
	
	textarea{
		width: 100%;
		resize: none;
	}
</style>

<script>
	function send(f){ // f = this.form
		
		//입력갑 유효성 체크 trim()좌우 공백만 제거, 중간 공백은 제거 안 한다. 
		var name    = f.name.value.trim();
		var content = f.content.value.trim();
		var pwd     = f.pwd.value.trim();
		
		if(name==''){
			alert('이름을 입력하세요.');
			f.name.value='';
			f.name.focus();
			return;
		}
		
		if(content==''){
			alert('내용을 입력하세요.');
			f.content.value='';
			f.content.focus();
			return;
		}
		
		if(pwd==''){
			alert('비밀번호를 입력하세요.');
			f.pwd.value='';
			f.pwd.focus();
			return;
		}
		
		if(confirm("정말 수정하시겠습니까?")==false) 
			return;
		
		//전송대상
		f.action="modify.do";//VisitModifyAction
		
		//전송
		f.submit();
	}
</script>
</head>
<body>


<form>
	<!-- session tracking중 1가지 방법 -->
	<input type="hidden" name="idx" value="${vo.idx }"><!-- 폼 요소지만 안보이게 함. 직접 idx를 수정할 일은 없지만, update쿼리 연산에서 조건으로 사용하기 위해 필요-->
	
	<div id="box">
		<div class="panel panel-primary">
      		<div class="panel-heading"><h4>방명록 수정하기</h4></div>
      		<div class="panel-body">
				<table class="table">
					<tr>
						<th>이름</th>
						<td><input name="name" value="${vo.name }"></td>
					</tr>
					
					<tr>
						<th>내용</th>
						<td><textarea name="content" rows="7" cols="50">${vo.content }</textarea></td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td><input type="text" name="pwd" value="${vo.pwd }"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-primary" type="button" value="수정하기" onclick="send(this.form);">
							<input class="btn btn-danger" type="button" value="목록보기" onclick="location.href='list.do'">
						</td>
						
					</tr>
				</table>      			
      		</div>
   		</div>
	</div>
</form>


</body>
</html>