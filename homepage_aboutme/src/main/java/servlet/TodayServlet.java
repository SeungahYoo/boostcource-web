package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
//현재 시간을 나타내는 클래스
public class TodayServlet extends HttpServlet {
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String currentTime = LocalDateTime.now().format(TIME_FORMAT);
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<a href='http://localhost:8080/2019_2nd_intern/html/index.html'>메인화면</a>" 
				+ "<br><br>"
				+ "<center><h1>현재시간 : " + currentTime + "</h1></center>");
		out.print("</html>");

	}

}
