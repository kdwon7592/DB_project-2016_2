

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
 * Servlet implementation class addenroll
 */
@WebServlet("/addenroll")
public class addenroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addenroll() {
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
		userInfo userinfo = (userInfo) session.getAttribute("userInfo");
		int val = 0;
		for(int i = 0 ;i<1000;i++){
			if(request.getParameter("button"+i)!=null){
				val = i;
			}
		}
		ResultSet rs = null;
		try{
				response.setContentType( "text/html;charset=euc-kr");
				String command;
				command = String.format("insert into enrollment " + "(cid, sid)" + " values (%d,%d)",courseinfo[val].getCid(),userinfo.getId());
				rs = stmt.executeQuery("select credit, cid, stime, etime, day from course where cid in (select cid from enrollment where sid = "+userinfo.getId()+")");
				int confirm = 1; //강좌가 들어갈 수 있으면 1 없으면 0
				int cnum = 0;
				while(rs.next()){ //강좌가 들어갈 시간이 있을까?
					System.out.println(rs.getString("day").equals(courseinfo[val].getDay()));
					if(rs.getString("day").equals(courseinfo[val].getDay())){
						cnum += rs.getInt("credit");
						if((Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo[val].getStime()) 
							&& Integer.parseInt(rs.getString("stime")) > Integer.parseInt(courseinfo[val].getEtime()))
							|| (Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo[val].getStime()) 
							&& Integer.parseInt(rs.getString("etime")) < Integer.parseInt(courseinfo[val].getEtime()))){
						/*int rowNum = stmt.executeUpdate(command);
						System.out.println("강좌 추가 성공");
						url = "admin.jsp";*/
						}
						else{
							confirm = 0;
							System.out.println("겹치는 강좌가 존재합니다. : "+rs.getString("cid"));
						}
					}
				}
				if(confirm == 1){
					if(courseinfo[val].getCredit() + cnum > 19){
						System.out.println("학점 초과 신청");
					}
					else{
						int rowNum = stmt.executeUpdate(command);
						System.out.println("강좌 추가 성공");
					}
				}
				response.sendRedirect("viewcourse");
				System.out.println("disconnect!");
				stmt.close();
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
