<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">
<title>책 등록 화면</title>
</head>
<body>
   <div class="jumbotron">
      <div class="container">
         <h2 class="display-4">책 생성하기</h2>
      </div>
   </div>   
   <div class="container">
      <form action="" method="post">
         <div class="row">
            <div class="col-md-12 mb-2">
               <div class="row">
                  <div class="col-md-2">
                     <label for="title" class="col-form-label">제목</label>
                  </div>
                  <div class="col-md-10">
                     <input type="text" class="form-control" name="bo_title" id="bo_title"/>
                  </div>
               </div>
            </div>
            <div class="col-md-12 mb-2">
               <div class="row">
                  <div class="col-md-2">
                     <label for="category" class="col-form-label">내용</label>
                  </div>
                  <div class="col-md-10">
                     <textarea cols="50" rows="10" class="form-control" name="bo_content" id="bo_content"></textarea>
                  </div>
               </div>
            </div>
            <div class="col-md-12 mb-2">
               <div class="row">
                  <div class="col-md-2">
                     <label for="price" class="col-form-label">작성자</label>
                  </div>
                  <div class="col-md-10">
                     <input type="text" class="form-control" name="bo_writer" id="bo_writer"/>
                  </div>
               </div>
            </div>
            <button type="submit" class="btn btn-warning">등록</button>
            <a href="/board/list.do" class="btn btn-primary">목록</a>
         </div>
      </form>
   </div>   
</body>
</html>