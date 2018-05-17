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
 * Servlet implementation class viewcourse
 */
@WebServlet("/viewcourse")
public class viewcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewcourse() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		courseInfo[] course = new courseInfo[1000];
		String[] pname = new String[1000];
		int cnt = 0;
		ResultSet rs = null;
		try {
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery("select cid, cname, room, stime, etime, credit, day, pname, c.pid from course c, professor p where c.pid = p.pid");
			while (rs.next()) {
				response.setContentType( "text/html;charset=euc-kr");
				course[cnt] = new courseInfo(rs.getInt("cid"),rs.getString("cname"),rs.getString("room"),rs.getString("stime"),rs.getString("etime"),rs.getInt("pid"),rs.getInt("credit"),rs.getString("day"));
				pname[cnt] = rs.getString("pname");
				cnt++;
			}
			request.setAttribute("cnt", cnt);
			session.setAttribute("course", course);
			session.setAttribute("pname", pname);
            RequestDispatcher view = request.getRequestDispatcher("/registcourse.jsp");
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
