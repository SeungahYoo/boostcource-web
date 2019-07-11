package com.nts.todo;

import java.sql.SQLException;
import java.util.List;

import com.nts.todo.dao.TodoDAO;
import com.nts.todo.dto.Todo;

public class test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		TodoDAO dao = new TodoDAO();
		List<Todo> ret = dao.getTodos("TODO");
		System.out.println(ret);
	}
}
