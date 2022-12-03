<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h2 class="display-4">책 상세보기</h2>
		</div>
	</div>
	<!-- 
		컨트롤러에서 전달받은 데이터(ModelAndView의 addObject()를 이용해서 작성된 데이터)를 보여주는 방법은 EL표현 방식입니다
		
		fmt는 데이터 포맷터 태그 라이브러리로, 원본데이터의 형식을 바꿔주는 역할을 합니다
		fmt의 사용을 위해서는 포맷터를 선언합니다(위 선언된  fmt 태그라이브러리)
		fmt:formatNumber의maxFractionDigits는 숫자 3자리이다 ','를 표시하기위해서 설정
		fmt:formatData는 날짜의 형식을 변경하기 위해서 설정
	 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 mb-2">
				<div class="row">
					<div class="col-md-2">
						<label for="title" class="col-form-label">제목</label>
					</div>
					<div class="col-md-10">${book.TITLE }</div>
				</div>
			</div>
			<div class="col-md-12 mb-2">
				<div class="row">
					<div class="col-md-2">
						<label for="category" class="col-form-label">카테고리</label>
					</div>
					<div class="col-md-10">${book.CATEGORY }</div>
				</div>
			</div>
			<div class="col-md-12 mb-2">
				<div class="row">
					<div class="col-md-2">
						<label for="price" class="col-form-label">가격</label>
					</div>
					<div class="col-md-10">
						<fmt:formatNumber type = "number" maxFractionDigits="3" value = "${book.PRICE }"/>
					</div>
				</div>
			</div>
			<div class="col-md-12 mb-2">
				<div class="row">
					<div class="col-md-2">
						<label for="insert_date" class="col-form-label">입력일</label>
					</div>
					<div class="col-md-10">
					<fmt:formatDate value="${book.INSERT_DATE }" pattern="yyyy.MM.dd HH:mm:ss"/>
					</div>
				</div>
			</div>
			<!-- 
				수정페이지로 이동을 하기 위해서는 html에서 링크를 거는 태그인 a태그를 이용하였습니다.
				수정 페이지 화면은 단순히 화면을 보여주기만 하기 때문에 GET메소드를 사용하고,
				a태그를 이용해서 링크를 걸 경우 HTTP 기본 메소드인 GET으로 링크가 걸립니다.
				
			 -->
			<a href = "/book/update.do?bookId=${bookId }"class = "btn btn-info">수정</a>
			<a href = "/book/list.do" class = "btn btn-primary">목록</a>
			<!-- 
				POST방식으로 데이터를 수정하기 위해서 form태그를 사용합니다
				form 태그의 action uri가 생략되었을 경우 브라우저의 기본값은 현재 주소 입니다.
			 -->
			
			<form action = "/book/delete.do" method="post" id = "delForm">
			<!-- 
				type 'hidden'은 숨은 태그로, 사용자에게 보여지지는 않지만 서버로 전달되거나 숨겨놓고 값을 사용해야 할 때 사용합니다.
			 -->
				<input type = "hidden" name = "bookId" value = "${bookId }"/>
				<input type = "button" class = "btn btn-danger" value = "삭제" id = "delBtn"/>
			</form>
		</div>
	</div>
</body>
<script>
	$(function(){
		var delBtn = $("#delBtn");
		var delForm = $("#delForm");
		
		delBtn.on("click",function(){
			if(confirm("정말로 삭제하시겠습니까?")){
				delForm.submit();
			}else{
				delForm.reset();
			}
		});
	});
</script>
</html>