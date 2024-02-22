package banking;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5433/"+"bank","postgres","Vinay@123");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String s1=request.getParameter("cuname");
			String s2=request.getParameter("pword");
			PreparedStatement ps=conn.prepareStatement("select * from customer where cu_name=? and password=?");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body>");
			if(s1.equals("Admin")) {
		     if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("output");
				rd.forward(request,response);
			}else {
				pw.println("Invalid uname&passowrd");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request,response);
			}
			}else {
				if(rs.next()) {
					RequestDispatcher rd=request.getRequestDispatcher("forward");
					rd.forward(request,response);
				}else {
					pw.println("Invalid uname&passowrd");
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					rd.include(request,response);
					}
			}
			
			pw.println("</body></html>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
