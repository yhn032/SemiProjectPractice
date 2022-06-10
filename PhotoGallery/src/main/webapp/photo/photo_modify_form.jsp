<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- BootStrap 3.x -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
#box {
	width: 500px;
	margin: auto;
	margin-top: 50px;
}

textarea {
	width: 100%;
	height: 150px;
	resize: none;
}

#modify_photo {
	width: 80%;
	border: 1px solid gray;
	outline: 1px solid black;
}
</style>

<script type="text/javascript">
	function send(f) {

		if (confirm("정말 수정하시겠습니까?") == false)
			return;

		var p_subject = f.p_subject.value.trim();
		var p_content = f.p_content.value.trim();

		if (p_subject == '') {
			alert('제목을 입력하세요!!!');
			f.p_subject.value = '';
			f.p_subject.focus();
			return;
		}

		if (p_content == '') {
			alert('내용을 입력하세요!!!');
			f.p_content.value = '';
			f.p_content.focus();
			return;
		}

		f.action = "modify.do" //PhotoModifyAction
		f.submit();
	}

	//Ajax파일 업로드 코드... 
	function ajaxFileUpload() {
		// 업로드 버튼이 클릭되면 파일 찾기 창을 띄운다.
		// == 사진 수정 버튼을 클릭하면 -> 숨겨놓은 버튼이 클릭되도록 설정한다.
		$("#ajaxFile").click();
	}

	function ajaxFileChange() {
		// 파일이 선택되면 업로드를 진행한다.
		photo_upload();
	}

	function photo_upload() {

		//파일선택->취소시
		if ($("#ajaxFile")[0].files[0] == undefined)
			return;

		var form = $("#ajaxForm")[0];
		var formData = new FormData(form);
		formData.append("p_idx", '${ list.p_idx }');
		formData.append("photo", $("#ajaxFile")[0].files[0]);	//photo_upload.do?p_idx=55&photo=a.jpg

		
		$.ajax({
			url : "photo_upload.do", //PhotoUploadAction
			type : "POST",
			data : formData,
			processData : false,
			contentType : false,
			dataType : 'json',
			success : function(result_data) {
				//result_data = {"p_filename":"aaa.jpb"}

				//location.href=''; //자신의 페이지를 리로드(refresh)

				$("#modify_photo").attr("src",
						"../upload/" + result_data.p_filename);

			},
			error : function(err) {
				alert(err.responseText);
			}
		});

	}
</script>

</head>
<body>
	<!--화일업로드용 폼  -->
	<!-- 실제 파일 업로드는 이 form이 담당하게 되고, display를 none으로 한다. 
	 우리가 만들어 놓은 버튼을 누르면 이 버튼이 눌리는 것처럼 동작되도록 구현한다. -->
	<form enctype="multipart/form-data" id="ajaxForm" method="post">
		<input type="file" id="ajaxFile" style="display: none;"
			onChange="ajaxFileChange();">
	</form>

	<form>
		<input type="hidden" name="p_idx" value="${list.p_idx }">
		<div id="box">
			<div class="panel panel-success">
				<div class="panel-heading">사진수정(최근 수정시간:
					${fn:substring(list.p_regdate,0,16) })</div>
				<div class="panel-body">
					<table class="table table-striped">
						<tr>
							<td colspan="2" align="center">
								<img id="modify_photo" src="../upload/${list.p_filename }"> 
								<br><br> 
								<input type="button" value="사진수정" onclick="ajaxFileUpload();">
							</td>
						</tr>



						<tr>
							<th>제목</th>
							<td><input name="p_subject" style="width: 100%;"
								value="${list.p_subject }"></td>
						</tr>


						<tr>
							<th>내용</th>
							<td><textarea name="p_content">${list.p_content }</textarea></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input
								class="btn btn-primary" type="button" value="수정하기"
								onclick="send(this.form);"> <input class="btn btn-info"
								type="button" value="목록보기" onclick="location.href='list.do';">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>