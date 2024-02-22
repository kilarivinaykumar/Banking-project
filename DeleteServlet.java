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
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			String s1=request.getParameter("ah_name");
			PreparedStatement ps=conn.prepareStatement("delete from account1 where ah_name=?") ;
			ps.setString(1, s1);
			PrintWriter pw=response.getWriter();
			int count=ps.executeUpdate();
			pw.println("<html><head><style>body{"
					+ "background-color: bisque;"
					+ "color:blue;"
					+ "font-size:30px;"
					+ "text-align: center;"
					+ "}"
					+ "h1{"
					+ " text-align: center;}"
					+ "</style></head><body>");
			if(count==1) {
				pw.println("<h1>Delete your record is successfully...."+s1+" record..</h1>"+"<br><br>");
				pw.println("<a href=login.html>Check your account...</a>");
			}else {
				pw.println("<h2>Delete your record is not successfully....</h2>");
			}
			pw.println("</body></html>");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
