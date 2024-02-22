package banking;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
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
			String s3=request.getParameter("email");
			String s4=request.getParameter("doj");
			String s5=request.getParameter("dob");
			String s6=request.getParameter("created_date");
			
			PreparedStatement ps=conn.prepareStatement("insert into customer(cu_name,password,email,doj,dob,created_date) values(?,?,?,?,?,?)");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setString(3, s3);
			ps.setDate(4,Date.valueOf(s4));
			ps.setDate(5,Date.valueOf(s5));
			ps.setDate(6,Date.valueOf(s6));
			ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>body{"
					+ "background-color: bisque;"
					+ "text-align:center;"
					+ "color:blue;}"
					+ "a{"
					+ "font-size:50px;"
					+ " text-decoration: none;}</style></head><body>");
			pw.println("<h1>");
			pw.println("You have created your customer page successfully....Please go and check....");
			pw.println("</h1>");
			pw.println("<br><br>");
			pw.println("<a href=reg.html>Account Holder Page....</a><br><br>");
			pw.println("<a href=login.html>Transation Page....</a><br><br>");
			pw.println("<a href=tr.html>Login Page....</a>");
			pw.println();
			pw.println("</body></html>");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
