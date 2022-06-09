<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		width: 805px;
		margin: auto;
		margin-top: 30px;
	}
	
	#title{
		text-align: center;
		color: green;
		
		font-weight: bold;
		text-shadow: 1px 1px 2px black;
	}
	
	#empty_msg{
		text-align: center;
		color: red;
		margin-top: 200px;
	}
	
	#photo_box{
		width: 100%;
		height: 479px;
		border: 2px solid blue;
		overflow-y: scroll;
		margin-top: 10px;
	}
	
	.photo{
		border: 1px solid #ccccff;
		width: 145px;
		height: 180px;
		margin: 25px;
		padding: 10px;
		float: left;
	}
	
	.photo:hover{
		border: 1px solid red;
	}
	
	.photo > img{
		width: 122px;
		height: 100px;
		border: 1px solid gray;
		outline: 1px solid black;
		
	}
	
	
	.photo_class{
		border: 1px solid gray;
		margin-top: 2px;
		margin-bottom: 2px;
		padding: 3px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
</style>

<script type="text/javascript">

function upload_photo(){
	
	//로그인 여부 체크
	if("${ empty user}" == "true"){//로그인하지 않았다면,,,
		
		if(confirm("로그인 후에 파일 업로드가 가능합니다.\n로그인 하시겠습니까?")==false) return;
		
		//로그인 폼으로 이동 
		location.href="${pageContext.request.contextPath}/member/login_form.do";
		
		return;
		
	}
	
	//로그인 된 경우
	location.href="insert_form.do"; // PhotoInsertFormAction
}

function download(p_filename) {
	//로그인 여부 체크
	if("${ empty user}" == "true"){//로그인하지 않았다면,,,
		
		if(confirm("로그인 후에 파일 다운로드가 가능합니다.\n로그인 하시겠습니까?")==false) return;
		
		//로그인 폼으로 이동 
		location.href="${pageContext.request.contextPath}/member/login_form.do";
		
		return;
		
	}
	
	//로그인 된 경우
	//파일 다운로드 서블릿 호출 -> 외부 서블릿이 필요로 하는 파라미터 정보를 쿼리 형식으로 전달한다. 하지만,, 지원되지 않는 웹 브라우저도 존재한다.
	//다운로드 받을 파일명이 한글(특수문자)면 인코딩 해서 전송한다. 
	location.href="../FileDownload.do?dir=/upload/&filename=" + encodeURIComponent(p_filename); // PhotoInsertFormAction
}

//전역변수
var global_p_idx;


function photo_view(p_idx){
	
	global_p_idx = p_idx;
	
	//화면 중앙 배치 
	center_photo_popup();
	
	//데이터 가져와서 셋팅하기
	$.ajax({
		url     :'photo_view.do', //PhotoViewAction
		data    :{'p_idx' : p_idx},
		dataType:'json',
		success :function(res_data){
			//팝업 윈도우 배치 작업
			//res_data={'p_idx':20, 'p_subject':제목, 'p_content':'내용', ..., 'm_idx':3};
			$("#img_photo").attr('src','../upload/' + res_data.p_filename);
			$("#photo_subject").html(res_data.p_subject);
			$("#photo_content").html(res_data.p_content);
			$("#photo_regdate").html(res_data.p_regdate.substring(0,16));
			
			//수정&삭제 버튼의 사용 유무
			//현재 로그인한 유저의 번호와 비동기로 읽어온 번호가 같으면 수정 삭제버튼 보이기
			
			//         게시글의 주인인 경우				관리자인 경우
			if( ('${user.m_idx}' == res_data.m_idx) || ("${user.m_grade eq '관리자'}" == "true" ) ){
				$("#photo_job").show();
			}else{
				$("#photo_job").hide();
				
			}
		},
		error   :function(err){
			alert(err.responseText);
		}
	});
	
}

function center_photo_popup(){
	
	//브라우저의 너비 높이 구하기 
	var w_width  = $(window).width();
	var w_height = $(window).height();
	
	//console.log(w_width, w_height);
	
	//popup크기 구하기
	var p_width  = $("#photo_popup").width();
	var p_height = $("#photo_popup").height();
	
	//console.log(p_width, p_height);
	
	var left = w_width/2  - p_width/2;
	var top  = w_height/2 - p_height/2;
	$("#photo_popup").css({'left':left, 'top':top});
	
	$("#photo_popup").show();
}

function hide_photo_popup(){
	$("#photo_popup").hide();
	
}


//사진 삭제 
function photo_delete(){
	if(confirm(global_p_idx + "번 파일을 정말 삭제하시겠습니까?")==false) return;
	
	//삭제하기 
	location.href="delete.do?p_idx=" + global_p_idx;	//PhotoDeleteAction
}

//수정하기
function photo_modify(){
	
	//수정폼 띄우기 
	location.href="modify_form.do?p_idx=" + global_p_idx;	//PhotoModifyFormAction
	
}
</script>

</head>
<body>

<!-- photo_popup추가 -->
<%@include file="photo_popup.jsp" %>

<div id="box">
	<h1 id="title">::::PhotoGallery::::</h1>
	<div style="text-align: right;">
		<!-- 로그인이 안 된 경우 -->
		<c:if test="${empty sessionScope.user }">
			<input class="btn btn-primary" type="button" value="로그인"   onclick="location.href='../member/login_form.do';">
			<input class="btn btn-primary" type="button" value="회원가입" onclick="location.href='../member/insert_form.do';">
		</c:if>
		
		<!-- 로그인이 된 경우 -->
		<c:if test="${not empty user }">
			<b>${user.m_name }님</b> 환영합니다.
			<input class="btn btn-primary" type="button" value="로그아웃" onclick="location.href='../member/logout.do';">
		</c:if>
	</div>
	
	
	<div>
		<input class="btn btn-danger" type="button" value="사진올리기" onclick="upload_photo();">
		<c:if test="${user.m_grade eq '관리자' }">
			<input class="btn btn-warning" type="button" value="회원목록보기" onclick="location.href='../member/list.do'">
		</c:if>
	</div>
	
	
	<div id="photo_box">
	
		<!-- 데이터가 없는 경우 -->
		<c:if test="${empty list }">
			<div id="empty_msg">사진 정보가 없습니다.</div>
		</c:if>
	
		<!-- 데이터가 있는 경우 -->
		<c:forEach	 var="vo" items="${list }">
			
			<div class="photo">
				<img src="${pageContext.request.contextPath }/upload/${vo.p_filename }" onclick="photo_view('${vo.p_idx}');">
				<div class="photo_class">${vo.p_subject }</div>
				<div><input style="width: 100%;" type="button" value="다운로드" onclick="download('${vo.p_filename}');"></div>
			</div>
			
			
		</c:forEach>
		
	</div>
	
</div>
</body>
</html>