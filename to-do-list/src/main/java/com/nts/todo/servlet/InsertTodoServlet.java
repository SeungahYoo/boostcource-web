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

@WebServlet("/InsertTodoServlet")
public class InsertTodoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			TodoDAO dao = new TodoDAO();
			dao.addTodo(createTodo(request));
			response.sendRedirect("MainServlet");
		} catch (SQLException e) {
			System.out.println("Error Type: " + e.getClass().getName());
			System.out.println("Error Message: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	private Todo createTodo(HttpServletRequest request) {
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setName(name);
		todo.setSequence(sequence);
		return todo;
	}
}
