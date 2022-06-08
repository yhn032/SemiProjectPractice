<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<style>
	#box{
		width: 500px;
		margin: auto;
		margin-top: 10px;
	}
	
	
	#title{
		text-shadow: 1px 1px 1px black;
		font-weight: bold;
		font-size: 30px;
		font-family: 휴먼옛체, 굴림체, 궁서체;
		text-align: center;
		color: #3366ff;
	}
	
	.content_type{
		padding: 5px;
		min-height: 60px;
		
		border: 1px solid gray;
		box-shadow: -1px -1px 1px #333333;
	}
</style>

<script type="text/javascript">

	function modify_form(f){ //f = this.form
		var idx = f.idx.value;
		var c_pwd = f.c_pwd.value.trim();
		
		if(c_pwd==''){
			alert('수정할 게시물의 비밀번호를 입력하세요.');
			f.c_pwd.value='';
			f.c_pwd.focus();
			return;
		}
		
		$.ajax({
			url:'check_pwd.do',
			data:{'idx':idx, 'c_pwd':c_pwd},
			dataType:'json',
			success:function(res_data){
				//res_data={"result":true};
				if(res_data.result==false){
					alert('비밀번호가 틀립니다.');
					f.c_pwd.value='';
					f.c_pwd.focus();
					return;
				}
				
				location.href="modify_form.do?idx="+idx;
			},
			error: function(err){
				alert(err.responseText);
			}
		});
	}

	function del(f){
		var idx = f.idx.value;//게시물 번호
		var c_pwd = f.c_pwd.value.trim();//사용자 입력 비번
		
		if(c_pwd==''){
			alert('삭제할 게시물의 비밀번호를 입력하세요.');
			f.c_pwd.value='';
			f.c_pwd.focus();
			return;
		}
		
		$.ajax({
			url     :'check_pwd.do',	//VisitCheckPasswordAction
			data    :{'idx':idx, 'c_pwd':c_pwd},
			dataType:'json',
			success : function(res_data){
				//res_data = {"result":true}
				if(res_data.result==false){
					alert('비밀번호가 틀립니다.');
					return;
				}
				
				if(confirm("정말 삭제하시겠습니까?")==false) return;
				
				//삭제 수행
				location.href='delete.do?idx='+idx;
				
			},
			error   :function(err){
				alert(err.responseText);
			}
		});
		
	}
	
</script>
</head>
<body>
	<div id="box">
		<h1 id="title">::::방명록::::</h1>
		<div style="margin-top: 10px; margin-bottom: 10px; text-align: right;">
			<input class="btn btn-primary" type="button" value="글쓰기" onclick="location.href='insert_form.do'">
		</div>
		
		<!-- 게시물이 없다면? -->
		<c:if test="${empty list }">
			<div id="empty_message">게시물이 없습니다.</div>
		</c:if>
		
		<!-- 게시물이 있다면? -->
		<c:forEach var="vo" items="${list }">
			<form>
			<input type="hidden" name="idx" value="${vo.idx }">
			
			
				<div class="panel panel-primary">
	      			<div class="panel-heading"><h4>${vo.name }님의 글</h4></div>
	      			<div class="panel-body">
	      				<div class="content_type">${vo.content }</div>
	      				<div>작성일자 : ${fn:substring(vo.regdate,0,16) }(${vo.ip })</div>
	      				<div>
	      					비밀번호(${vo.pwd }) : <input type="password" name="c_pwd">
	      					           <input class="btn btn-primary" type="button" value="수정" onclick="modify_form(this.form)">
	      					           <input class="btn btn-danger" type="button" value="삭제" onclick="del(this.form)">
	      				</div>
	      			</div>
	   			</div>
   			</form>
		</c:forEach>
	</div>
</body>
</body>
</html>