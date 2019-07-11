package com.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.todo.dto.Todo;

public class TodoDAO {
	private static String DB_URL = "jdbc:mysql://10.113.116.52:13306/user10?useSSL=false";
	private static String DB_USER = "user10";
	private static String DB_PASSWORD = "user10";

	public TodoDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error Type: " + e.getClass().getName());
			System.out.println("Error Message: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public List<Todo> getTodos(String type) throws SQLException {
		List<Todo> todos = new ArrayList<>();
		String sql = "SELECT * FROM todo WHERE type = ?";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, type);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					todos.add(createTodo(rs));
				}
			}
		}
		return todos;
	}

	public int addTodo(Todo todo) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			result = ps.executeUpdate();
		}
		return result;
	}

	public int updateTodo(Todo todo) throws SQLException {
		int result = 0;
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());
			result = ps.executeUpdate();
		}
		return result;
	}

	private Todo createTodo(ResultSet rs) throws SQLException {
		Todo selectedTodo = new Todo();
		selectedTodo.setId(rs.getLong("id"));
		selectedTodo.setTitle(rs.getString("title"));
		selectedTodo.setName(rs.getString("name"));
		selectedTodo.setSequence(rs.getInt("sequence"));
		selectedTodo.setType(rs.getString("type"));
		selectedTodo.setRegdate(rs.getTimestamp("regdate"));

		return selectedTodo;
	}
}
