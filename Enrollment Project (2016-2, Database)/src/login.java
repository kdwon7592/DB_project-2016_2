import register.userInfo;
import java.io.IOException;
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

/**
 * Servlet implementation class login
 */
@WebServlet("/login")

 
/*public class DBCP {
    //�� �ڵ带 �Ʒ��� ���� �ٿ��� �ۼ������ϴ�.
    Context initContext = new InitialContext();
    Context envContext  = (Context) initContext.lookup("java:/comp/env");
    DataSource dataSource = (DataSource) envContext.lookup("jdbc/oracle");
    Connection conn = dataSource.getConnection();
    
     
    Context context = new InitialContext();
    DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
    Connection con = dataSource.getConnection();        
}*/

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db�κ�
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
		//db�κ�
		request.setCharacterEncoding("euc-kr");
		int inputid = Integer.parseInt(request.getParameter("loginId"));
		String inputpw = request.getParameter("loginPw");
		int inputposition = Integer.parseInt(request.getParameter("loginPosition"));
		String table;
		String id;
		if(inputposition == 1){
			table = "student";
			id = "sid";
		}
		else if(inputposition == 2){
			table = "professor";
			id = "pid";
		}
		else{
			table = "admin";
			id = "aid";
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from "+table+" where "+id+" = '"+inputid+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(!rs.next()){//�ش� id�� ���� ��
				response.setContentType( "text/html;charset=euc-kr");
				System.out.println("���̵� �������� �ʽ��ϴ�.");
				response.sendRedirect("login.jsp");
			}
			else{
				userInfo userInfo = new userInfo();
				userInfo.setId(inputid);
				userInfo.setPw(inputpw);
				if(inputpw.equals(userInfo.getPw())){//�α��� ����
					HttpSession session = request.getSession();
					session.setAttribute("userInfo", userInfo);
					response.sendRedirect("home.jsp");
				}
				else{ //��й�ȣ�� �ٸ� ��
					response.setContentType( "text/html;charset=euc-kr");
					System.out.println("��й�ȣ�� �ٸ��ϴ�.");
					response.sendRedirect("login.jsp");
				}
			}
			stmt.close();
			conn.close();
			System.out.println("disconnect!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
