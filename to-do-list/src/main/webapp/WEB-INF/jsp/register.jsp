<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 등록</title>
<link rel="stylesheet" type="text/css" href="css/register.css" />
</head>
<body>
	<section>
		<center>
			<h1>할일 등록</h1>
		</center>
		<form action="InsertTodoServlet" method="post">
			<div class="question">
				어떤일인가요?<br> <input name="title" maxlength="24" size="50"
					placeholder="swift공부하기(24자까지)" />
			</div>
			<div class="question">
				누가 할 일인가요?<br> <input name="name" size="50" placeholder="홍길동" />
			</div>
			<div class="question">
				우선 순위를 선택하세요<br> 
				<label><input type="radio" name="sequence" value="1"/>1순위</label>
				<label><input type="radio" name="sequence" value="2"/>2순위</label>
				<label><input type="radio" name="sequence" value="3"/>3순위</label>
			</div>
			<div class="buttons">
				<div class="btn back">
					<a href="MainServlet "> &lt; 이전</a>
				</div>
				<button class="btn blue" type="reset">내용지우기</button>
				<button class="btn blue" type="submit">제출</button>
			</div>
		</form>
	</section>
</body>
</html>