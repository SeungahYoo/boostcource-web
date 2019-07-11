package com.nts.todo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.todo.dao.TodoDAO;
import com.nts.todo.dto.Todo;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		long todoID = Long.parseLong(request.getParameter("id"));
		String status = request.getParameter("type");
		TodoDAO dao = new TodoDAO();
		String nextStatus = "";

		switch (status) {
			case "TODO":
				nextStatus = "DOING";
				break;
			case "DOING":
				nextStatus = "DONE";
				break;
			default: //Null인 경우
				throw new IllegalArgumentException("status: " + status);
		}

		Todo nextTodo = new Todo();
		nextTodo.setId(todoID);
		nextTodo.setType(nextStatus);

		try {
			dao.updateTodo(nextTodo);
		} catch (SQLException e) {
			System.out.println("Error Type: " + e.getClass().getName());
			System.out.println("Error Message: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
