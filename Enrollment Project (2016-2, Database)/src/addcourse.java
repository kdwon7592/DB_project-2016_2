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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import register.courseInfo;
import register.userInfo;
/**
 * Servlet implementation class course
 */
@WebServlet("/addcourse")
public class addcourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public addcourse() {
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
		int inputcid = Integer.parseInt(request.getParameter("addcid"));
		String inputname = request.getParameter("addcname");
		String inputroom = request.getParameter("addroom");
		String inputstime = request.getParameter("addstime");
		String inputetime = request.getParameter("addetime");
		String inputday = request.getParameter("loginDay");
		int inputpid = Integer.parseInt(request.getParameter("addpid"));
		int inputcredit = Integer.parseInt(request.getParameter("addcredit"));
		courseInfo courseinfo = new courseInfo(inputcid, inputname, inputroom, inputstime, inputetime, inputpid, inputcredit, inputday);
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery("select pid from professor where pid = '"+courseinfo.getPid()+"'"); //������ �����ϳ�?
			if(!rs.next()){//������ �������� �ʴ´�.
				response.setContentType( "text/html;charset=euc-kr");
				System.out.println("���� ���� �ʴ� ������ȣ");
				response.sendRedirect("addcourse.jsp");
			}
			else{
				response.setContentType( "text/html;charset=euc-kr");
				String command;
				command = String.format("insert into course " + "(cid, cname, room, stime, etime, pid, credit, day)" + " values ('%d','%s','%s','%s','%s','%d','%d','%s')",courseinfo.getCid(),courseinfo.getName(),courseinfo.getRoom(),courseinfo.getStime(),courseinfo.getEtime(),courseinfo.getPid(),courseinfo.getCredit(), courseinfo.getDay());
				rs = stmt.executeQuery("select room,stime,etime,cid from course where room='"+courseinfo.getRoom()+"' and day='"+courseinfo.getDay()+"'");
						//"'and (stime < "+courseinfo.getStime()+"and etime >= "+courseinfo.getStime()+
						//") or (stime >= "+courseinfo.getEtime()+"and etime < "+courseinfo.getEtime()+
						//") or (stime >= "+courseinfo.getStime()+"and etime <="+courseinfo.getEtime()+")");
				String url = "admin.jsp";
				int confirm = 1; //���°� �� �� ������ 1 ������ 0
				while(rs.next()){ //���ǽǰ� ������ ���� ���
					if((Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo.getStime()) 
							&& Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo.getEtime()))
							|| (Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo.getStime()) 
							&& Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo.getEtime()))){
						/*int rowNum = stmt.executeUpdate(command);
						System.out.println("���� �߰� ����");
						url = "admin.jsp";*/
					}
					else{
						confirm = 0;
						System.out.println("���� ���ǽ��� ����� �ð��� ���°� �����մϴ�. : "+rs.getString("cid"));
						url = "addcourse.jsp";
					}
				}
				rs = stmt.executeQuery("select stime,etime,cid,pid from course where pid = '"+courseinfo.getPid()+"'");
				while(rs.next()){
					if((Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo.getStime()) 
							&& Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo.getEtime()))
							|| (Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo.getStime()) 
							&& Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo.getEtime()))){
						/*int rowNum = stmt.executeUpdate(command);
						System.out.println("���� �߰� ����");
						url = "admin.jsp";*/
					}
					else{
						confirm = 0;
						System.out.println("���� �������� ����� ���°� �����մϴ�. : "+rs.getString("cid"));
						url = "addcourse.jsp";
					}
				}
				if(confirm == 1){
					int rowNum = stmt.executeUpdate(command);
					System.out.println("���� �߰� ����");
				}
				response.sendRedirect(url);
				stmt.close();
				conn.close();
				System.out.println("disconnect!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
