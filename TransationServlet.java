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
 * Servlet implementation class TransationServlet
 */
public class TransationServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransationServlet() {
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
			
			String s1=request.getParameter("tr_type");
			String s2=request.getParameter("amount");
			String s3=request.getParameter("ah_id");
			String s4=request.getParameter("created_date");
			String s5=request.getParameter("tr_date");

			PreparedStatement ps=conn.prepareStatement("insert into transation1(tr_type,tr_date,amount,ah_id,created_date) values(?,?,?,?,?)");
			ps.setString(1, s1);
			//ps.setDouble(2,Double.parseDouble(s2));
			ps.setDate(2,Date.valueOf(s5));
			ps.setDouble(3,Double.parseDouble(s2));
			ps.setInt(4,Integer.parseInt(s3));
			ps.setDate(5,Date.valueOf(s4));
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
					+ "}</style></head><body><h1>You have completeded your transation successfully,Please you will go and check your account..."+"<br><br>");
			pw.println("<a href=login.html>Login Form....</a>");
			pw.println("</h1></body></html>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
