<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script>
function login() {
		if (document.f.userId.value == "") {
			alert("사용자 아이디를 입력하십시요.");
			document.f.userId.focus();
			return false;
		}
		if (document.f.password.value == "") {
			alert("비밀번호를 입력하십시요.");
			document.f.password.focus();
			return false;
		}

		document.f.action = "member_login_action.do";
		document.f.method='POST';
		document.f.submit();
	}
</script>
</head>
<body>
	<div class="customer_login">
		<div class="container">
			<div class="row">
				<!--login area start-->
				<div class="offset-md-3 col-md-6 offset-md-3">
					<div class="account_form">
						<h2>로그인</h2>
						<form id="login_action_form" action="login_action" method="POST">
							<p>
								<label>아이디<span>*</span></label>
								<c:choose>
									<c:when test="${empty mem_id }">
										<input id="mem_id" name="mem_id" type="text"
											value="${cookie['saved_id'].value}">
									</c:when>
									<c:otherwise>
										<input id="mem_id" name="mem_id" type="text" value="${mem_id}">
									</c:otherwise>
								</c:choose>
							</p>
							<p>
								<label>비밀번호 <span>*</span></label> <input id="mem_pw"
									name="mem_pw" type="text">
								<c:if test="${empty loginMember}">
									<span id="login_error_msg">${msg }</span>
								</c:if>
							</p>
							<div class="login_submit">
								<a href="#">비밀번호 찾기</a> <label for="remember"> <c:choose>
										<c:when test="${empty cookie['saved_id'].value}">
											<input id="remember" name="save_id_check" type="checkbox">
	                                    	아이디 저장
	                                </c:when>
										<c:otherwise>
											<input id="remember" checked name="save_id_check"
												type="checkbox">
	                                    	아이디 저장
	                                </c:otherwise>
									</c:choose>
								</label>
								<button type="submit">로그인</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>