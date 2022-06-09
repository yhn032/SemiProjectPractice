<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>

<script type="text/javascript">

function send(f){
	
	var p_subject = f.p_subject.value.trim();
	var p_content = f.p_content.value.trim();
	var p_photo   = f.p_photo.value;
	
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
	
	if(p_photo==''){
		alert('사진을 선택하세요!!!');
		return;
	}
	
	f.action="insert.do" //PhotoInsertAction
	f.submit();
}
</script>

</head>
<body>
	<form method="POST" enctype="multipart/form-data">
		<div id="box">
			<div class="panel panel-success">
        		<div class="panel-heading">사진등록</div>
        		<div class="panel-body">
        			<table class="table table-striped">
        				<tr>
        					<th>제목</th>
        					<td><input name="p_subject" style="width: 100%;"></td>
        				</tr>
        				
        				
        				<tr>
        					<th>내용</th>
        					<td><textarea name="p_content"></textarea></td>
        				</tr>
        				
        				
        				<tr>
        					<th>사진</th>
        					<td><input type="file" name="p_photo"></td>
        				</tr>
        				
        				<tr>
        					<td colspan="2" align="center">
        						<input class="btn btn-primary" type="button" value="등록하기" onclick="send(this.form);">
        						<input class="btn btn-info" type="button" value="목록보기" onclick="location.href='list.do';">
        					</td>
        				</tr>
        			</table>
        		</div>
    		</div>	
		</div>	
	</form>
</body>
</html>