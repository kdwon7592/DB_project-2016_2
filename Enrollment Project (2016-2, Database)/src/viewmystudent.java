

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import register.courseInfo;
import register.userInfo;
/**
 * Servlet implementation class viewmystudent
 */
@WebServlet("/viewmystudent")
public class viewmystudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewmystudent() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db부분
		Context context = null;
		DataSource dataSource = null; 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
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
		userInfo[] userinfo = new userInfo[100];
		int val = 0;
		for(int i = 0 ;i<100;i++){
			if(request.getParameter("button"+i)!=null){
				val = i;
			}
		}
		try {
		rs = stmt.executeQuery("select sid, sname, smajor from student where sid in (select sid FROM enrollment WHERE cid="+courseinfo[val].getCid()+")");
		int cnt = 0;
		while(rs.next()){
			userinfo[cnt] = new userInfo(rs.getInt(1),rs.getString(2),rs.getString(3));
			cnt++;
		}
		request.setAttribute("cnt", cnt);
		session.setAttribute("userinfo", userinfo);
		RequestDispatcher view = request.getRequestDispatcher("/viewmystudent.jsp");
        view.forward(request, response);
		stmt.close();
		conn.close();
		System.out.println("disconnect!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
