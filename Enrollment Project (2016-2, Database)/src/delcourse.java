import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import register.userInfo;
import register.courseInfo;
/**
 * Servlet implementation class delcourse
 */
@WebServlet("/delcourse")
public class delcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delcourse() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db부분
		Context context = null;
		DataSource dataSource = null; 
		Connection conn = null;
		Statement stmt = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	    try {
			conn = dataSource.getConnection();
			stmt = (Statement)conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connect!");
		//db부분
		HttpSession session = request.getSession();
		courseInfo[] courseinfo = (courseInfo[]) session.getAttribute("course");
		int val = 0;
		for(int i = 0 ;i<1000;i++){
			if(request.getParameter("button"+i)!=null){
				val = i;
			}
		}
		int cid = courseinfo[val].getCid();
		String command = String.format("DELETE FROM course WHERE cid='%d'",cid);
		try {
			int rowNum = stmt.executeUpdate(command);
			stmt.close();
			conn.close();
			response.sendRedirect("viewservlet");
			System.out.println("disconnect!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
