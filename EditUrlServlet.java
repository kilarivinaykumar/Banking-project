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
 * Servlet implementation class EditUrlServlet
 */
public class EditUrlServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try{
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
			String s1=request.getParameter("ah_id");
			String s2=request.getParameter("ahname");
		    //Float s3=Float.parseFloat(request.getParameter("balance"));
			String s3=request.getParameter("balance");
			String s4=request.getParameter("doj");
			String s5=request.getParameter("dob");
			String s6=request.getParameter("created_date");
			PreparedStatement ps=conn.prepareStatement("update account1 set ah_name=?,balance=?,doj=?,dob=?,created_date=? where ah_id=?");
			ps.setString(1,s2);
			//ps.setString(2, s2);
			ps.setDouble(2,Double.parseDouble(s3));
			//ps.setChar(3,Character.parseChar(s3));
			ps.setDate(3,Date.valueOf(s4));
			ps.setDate(4,Date.valueOf(s5));
			ps.setDate(5,Date.valueOf(s6));
			ps.setInt(6,Integer.parseInt(s1));
			
			int count=ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>body{"
					+ "text-align:center;"
					+ "color:blue;"
					+ "background-color:bisque;}"
					+ "a{"
					+ "font-size:50px;"
					+ "color:green;}</style></head><body>");
			if(count==1) {
			pw.println("<h1>Record has updated successfully....</h1>");
			pw.println("<a href=login.html>Login Page.....</a>");
			}else {
				pw.println("<h2>Record has not updated successfully....</h2>");
			}
			pw.println("</body></html>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
