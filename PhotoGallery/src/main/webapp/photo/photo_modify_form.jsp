<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- BootStrap 3.x -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
	#box{
		width: 500px;
		margin: auto;
		margin-top: 50px;
	}
	
	textarea{
		width: 100%;
		height: 150px;
		resize: none;
	}
	
	#modify_photo{
		width: 100%;
	}
</style>

<script type="text/javascript">

function send(f){
	
	if(confirm("정말 수정하시겠습니까?")==false) return;
	
	
	
	var p_subject = f.p_subject.value.trim();
	var p_content = f.p_content.value.trim();
	
	
	if(p_subject==''){
		alert('제목을 입력하세요!!!');
		f.p_subject.value='';
		f.p_subject.focus();
		return;
	}
	
	if(p_content==''){
		alert('내용을 입력하세요!!!');
		f.p_content.value='';
		f.p_content.focus();
		return;
	}
	
	f.action="modify.do" //PhotoModifyAction
	f.submit();
}
</script>

</head>
<body>
	<form>
		<input type="hidden" name="p_idx" value="${list.p_idx }">
		<div id="box">
			<div class="panel panel-success">
        		<div class="panel-heading">내용수정(최근 수정시간: ${fn:substring(list.p_regdate,0,16) })</div>
        		<div class="panel-body">
        			<table class="table table-striped">
        				<tr>
        					<td colspan="2"><img id="modify_photo" src="../upload/${list.p_filename }"></td>
        				</tr>
        				<tr>
        					<th>제목</th>
        					<td><input name="p_subject" style="width: 100%;" value="${list.p_subject }"></td>
        				</tr>
        				
        				
        				<tr>
        					<th>내용</th>
        					<td><textarea name="p_content">${list.p_content }</textarea></td>
        				</tr>
        				
        				<tr>
        					<td colspan="2" align="center">
        						<input class="btn btn-primary" type="button" value="수정하기" onclick="send(this.form);">
        						<input class="btn btn-info"    type="button" value="목록보기" onclick="location.href='list.do';">
        					</td>
        				</tr>
        			</table>
        		</div>
    		</div>	
		</div>	
	</form>
</body>
</html>