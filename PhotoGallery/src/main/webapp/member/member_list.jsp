<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
#box {
	width: 1100px;
	margin: auto;
	margin-top: 30px;
}

#title {
	text-align: center;
	font-size: 25px;
	font-weight: bold;
	color: white;
	text-shadow: 1px 1px 3px black;
}

th {
	text-align: center;
}

td {
	text-indent: 10px; /* 들여쓰기 */
}
</style>
<!-- SweetAlert2 Library -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script type="text/javascript">
	function del(m_idx){
		
		//if(confirm("정말 삭제하시겠습니까?")==false) return; //동기함수 -> 사용자가 응답하기 전까지 이후에 나올 후속코드를 진행하지 않고 기다린다.
		
		//sweet alert 비동기 함수이다.----------------------------------------
		Swal.fire({
			  title: '정말 삭제하시겠습니까?',
			  //html: "<h4>선택한 사용자가 삭제됩니다.</h4>", text 대신 html로 사용가능
			  text: "선택한 사용자가 삭제됩니다.",
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '삭제',
			  cancelButtonText: '취소',
			  
			}).then((result) => {
				
				//삭제버튼을 누르면,,
				if(result.isConfirmed){
					alert('삭제한다');
					location.href='delete.do?m_idx=' + m_idx;					
				}
				
			});
		//----------------------------------------------------------------------
	}
</script>
</head>
<body>
	<div id="box">
		<h1 id="title">:::::회원 목록:::::</h1>

		<!-- 회원가입 -->
		<div>
			<input class="btn btn-primary" type="button" value="회원가입"
				onclick="location.href='insert_form.do'">
				
			<input class="btn btn-info" type="button" value="사진보러가기"
				onclick="location.href='../photo/list.do'">
		</div>

		<!-- 데이터 출력 -->
		<div>
			<table class="table table-striped">
				<tr class="info">
					<th>번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>우편번호</th>
					<th>주소</th>
					<th>회원등급</th>
					<th>아이피</th>
					<th>가입일자</th>
					<th>편집</th>
				</tr>

				<!-- data 없는 경우-->
				<c:if test="${empty list }">
					<tr>
						<td colspan="10" align="center"><font color="red">등록된
								회원이 없습니다.</font></td>
					</tr>
				</c:if>

				<!-- data가 있는 경우 -->
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.m_idx }</td>
						<td>${vo.m_name }</td>
						<td>${vo.m_id }</td>
						<td>${vo.m_pwd }</td>
						<td>${vo.m_zipcode }</td>
						<td>${vo.m_addr }</td>
						<td>${vo.m_grade }</td>
						<td>${vo.m_ip }</td>
						<td>${fn:substring(vo.m_regdate, 0, 10) }</td>
						<td><input class="btn btn-info" type="button" value="수정" onclick="location.href='modify_form.do?m_idx=${vo.m_idx}';">
						<!-- 상태 유지 기술을 세션트래킹이라고 한다. 현재 페이지에 있는 정보를 페이지를 변경할때에도 유지한 상태로 페이지 이동 -->
							<input class="btn btn-danger" type="button" value="삭제"
							onclick="del('${vo.m_idx}');"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>