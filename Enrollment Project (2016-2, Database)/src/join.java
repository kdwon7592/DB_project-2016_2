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
 * Servlet implementation class join
 */
@WebServlet("/join")
public class join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public join() {
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
		//db부분
		request.setCharacterEncoding("euc-kr");
		int inputid = Integer.parseInt(request.getParameter("joinId"));
		String inputpw = request.getParameter("joinPw");
		String inputname = request.getParameter("joinName");
		String inputmajor = request.getParameter("inputMajor");
		int inputposition = Integer.parseInt(request.getParameter("inputPosition"));
		userInfo userInfo = new userInfo(inputid, inputpw, inputname, inputmajor);
		ResultSet rs = null;
		String id;
		String table;
		if(inputposition == 1){
			table = "student";
			id = "s";
		}
		else{
			table = "professor";
			id = "p";
		}
		try {
			rs = stmt.executeQuery("select * from "+table+" where "+id+"id = '"+userInfo.getId()+"'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(rs.next()){//해당 id가 있을 때
				response.setContentType( "text/html;charset=euc-kr");
				System.out.println("이미 존재하는 아이디");
				response.sendRedirect("join.jsp");
			}
			else{
				response.setContentType( "text/html;charset=euc-kr");
				System.out.println("계정 생성 완료");
				String command;
				command = String.format("insert into "+table+" "+ "("+id+"id, "+id+"name, "+id+"major, "+id+"pwd)" + " values ('%d','%s', '%s', '%s')",userInfo.getId(),userInfo.getName(),userInfo.getMajor(),userInfo.getPw());
				int rowNum = stmt.executeUpdate(command);
				response.sendRedirect("login.jsp");
				conn.close();
				stmt.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
