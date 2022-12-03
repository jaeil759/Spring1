<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="jumbotron">
      <div class="container">
         <h2 class="display-4">책 상세보기</h2>
      </div>
   </div>   
   <div class="container">
      <div class="row">
         <div class="col-md-12 mb-2">
            <div class="row">
               <div class="col-md-7">
               </div>
               <div class="col-md-5">
                  <form>
                     <div class="row">
                        <div class="col-md-10">
                           <input type="text" class="form-control" name="keyword" value="${keyword }"/>
                        </div>
                        <div class="col-md-2">
                           <input type="submit" class="btn bnt-secondary" value="검색"/>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
         	<div class="col-md-12 mb-2">
         		<div class="row">
				<table class = "table">
					<thead class = "table-dark">
						<tr>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성시간</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${empty boardList }">
							<tr>
								<td colspan="3">조회하실 게시글이 존재하지 않습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items = "${boardList }" var = "board">
								<tr>
									<td><a href = "/views/detail.do?bookId=${board.bo_no }">${board.bo_title }</a></td>
									<td>${board.bo_writer }</td>
									<td>
										<fmt:formatDate value="${board.bo_date}" pattern="yyyy-MM-dd"/>							
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>         	
         	</div>
         </div>
         <a href = "/book/form.do" class = "btn btn-primary">등록</a>
      </div>   
   </div>
</body>
</html>