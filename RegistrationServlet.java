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
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
			
			String s1=request.getParameter("ahname");
			String s2=request.getParameter("email");
			String s3=request.getParameter("created_date");
			String s4=request.getParameter("bal");
			String s5=request.getParameter("doj");
			String s6=request.getParameter("dob");
			PreparedStatement ps=conn.prepareStatement("insert into account1(ah_name,email,doj,dob,created_date,balance) values(?,?,?,?,?,?)");
			ps.setString(1, s1);
			ps.setString(2, s2);
			ps.setDate(3,Date.valueOf(s3) );
			ps.setDate(4,Date.valueOf(s5) );
			ps.setDate(5,Date.valueOf(s6) );
			ps.setDouble(6,Double.parseDouble(s4));
			ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>body{"
					+ "    background-color: bisque;"
					+ "    text-align: center;"
					+ "    color: blueviolet;"
					+ "}"
					+ "a{"
					+ "    color: blue;"
					+ "    text-decoration: none;"
					+ "}</style></head><body><h1>You have created account successfully,now you will go and created one password for your bank transation....</h1><h2>"+"<br><br>");
			pw.println("<a href=customer.html>Customer Form....</a>"+"<br><br>");
			pw.println("<a href=tr.html>Transation Form....</a>"+"<br><br>");
			pw.println("<a href=login.html>Login Form....</a>");

			pw.println("</h2></body></html>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
