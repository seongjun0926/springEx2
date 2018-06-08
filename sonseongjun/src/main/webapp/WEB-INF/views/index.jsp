<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/3.3.4/dist/css/bootstrap.min.css">
<script src="/webjars/jquery/2.2.4/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.4/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	
	$('#up').click(function(){
		$("#test1").attr("action", "/file/insert").submit();

	});
	$('#down').click(function(){
		$("#test1").attr("action", "/file/down").submit();
	});

}); 
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<form id="test1" action="/file/insert" method="post" enctype="multipart/form-data"> <!-- 추가 -->
				<table class="table">
					<thead>
						<tr>
							<td>
								<div class="form-group">
								  	<input type="file" name="files"> <!-- 추가 -->
								</div>
							</td>
							<td>
								<div class="form-group">
									<button type="button" id="up" class="form-control btn btn-primary">업로드</button>
								</div>
							</td>
							<td>
								<div class="form-group">
									<button type="button" id ="down" class="form-control btn btn-primary">다운로드</button>
								</div>
							</td>
						</tr>
					</thead>
				</table>
			</form>
			<table id="userTable" class="table table-striped table-bordered table-hover" >
			    <thead>
			        <tr>
			        	<th></th>
			            <th>사번</th>
			            <th>이름</th>
			            <th>점수1</th>
			            <th>점수2</th>
			            <th>점수3</th>
			            <th>점수4</th>
			            <th>점수5</th>
			            <th>평균</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<c:forEach var="user" items="${userList}" varStatus="status">
				    	<tr>
				    		<td>
				    			<input type="checkbox" name="chk" value="">
				    		</td>
				    		<td>
				    			<c:out value="${user.id}" escapeXml="true"/>
				    		</td>
				    		<td>
				    			<c:out value="${user.stuNm}" escapeXml="true"/>
				    		</td>
				    		<td>
				    			<input type="text" class="form-control" id="score1" name="score1" value="${user.score1}">
				    		</td>
				    		<td>
				    			<input type="text" class="form-control" id="score2" name="score1" value="${user.score2}">
				    		</td>
				    		<td>
				    			<input type="text" class="form-control" id="score3" name="score1" value="${user.score3}">
				    		</td>
				    		<td>
				    			<input type="text" class="form-control" id="score4" name="score1" value="${user.score4}">
				    		</td>
				    		<td>
				    			<input type="text" class="form-control" id="score5" name="score1" value="${user.score5}">
				    		</td>
				    		<td>
				    			<c:out value="${user.scoAvg}" escapeXml="true"/>
				    		</td>
				    	</tr>
			    	</c:forEach>
			    </tbody>
			</table>
		</div>
	</div>
</body>
</html>