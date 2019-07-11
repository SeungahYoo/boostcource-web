<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 해야할 일들</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>

<body>
	<div id="container">
		<header>
			<div id="title">나의 해야할 일들</div>
			<div class="new-btn" style="float: right;">
				<a href="RegisterForm">새로운 TODO 등록</a>
			</div>
		</header>
		<section>
			<div class="list" id="todo-list">
				<div class="status">TODO</div>
				<c:forEach var="todo" items="${todos }">
					<div class="card">
						<div class="content">${todo.title }</div>
						<div class="detail">등록날짜 ${todo.regdateForView },
							${todo.name }, 우선순위 ${todo.sequence }</div>
						<button class="next-btn" data-id="${todo.id }"
							data-type="${todo.type }">→</button>
					</div>
				</c:forEach>

			</div>

			<div class="list" id="doing-list">
				<div class="status">DOING</div>
				<c:forEach var="doing" items="${doings }">
					<div class="card">
						<div class="content">${doing.title }</div>
						<div class="detail">등록날짜 ${doing.regdateForView  },
							${doing.name }, 우선순위 ${doing.sequence }</div>
						<button class="next-btn" data-id="${doing.id }"
							data-type="${doing.type }">→</button>
					</div>
				</c:forEach>
			</div>

			<div class="list" id="done-list">
				<div class="status">DONE</div>
				<c:forEach var="done" items="${dones }">
					<div class="card">
						<div class="content">${done.title }</div>
						<div class="detail">등록날짜 ${done.regdateForView  },
							${done.name }, 우선순위 ${done.sequence }</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</div>

<script type="text/javascript">
	var Buttons = document.querySelectorAll("button");

	Buttons.forEach((event)=>{
	    event.addEventListener("click",changeStatus)
	});
	
	function changeStatus() {
		const button = this;
		const request = new XMLHttpRequest();
		const information = 'id='+button.dataset.id+'&type='+button.dataset.type;
		
		request.open("POST", "UpdateStatusServlet");//parameter를 붙여서 보낼수있음. 
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(information);
		
		const clickedCard = button.parentNode;
		let doList;
		
		request.onreadystatechange = function() {
			if(request.readyState !== 4 || request.status !== 200) return;
		
			if(button.dataset.type === "TODO"){//todo->doing
				doList = document.querySelector("#doing-list");
				button.dataset.type = "DOING";
			}else if(button.dataset.type === "DOING"){//doing->done
				button.remove();
				doList = document.querySelector("#done-list");
			}
			doList.appendChild(clickedCard);
		};
	}

</script>
</body>
</html>