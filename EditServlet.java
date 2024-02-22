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
import java.sql.ResultSet;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
			String s1=request.getParameter("ah_id");
			PreparedStatement ps=conn.prepareStatement("select * from account1 where ah_id=?");
			ps.setInt(1,Integer.parseInt(s1));
			ResultSet rs=ps.executeQuery();
			//while(rs.next())
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>table,td,th{"
					+ "border-collapse:collapse;"
					+ "border:2px solid green;"
					+ "}"
					+ "#k{"
					+ "  color: blue;"
					+ " margin-top: 40px;"
					+ "}"
					+ "body{"
					+ "    text-align: center;"
					+ "background-color: bisque;"
					+ "}"
					+ "table{"
					+ "    margin-left: 140px;"
					+ "margin-top: 60px;"
					+ "}"
					+ "th{"
					+ "color:blue;}"
					+ "tr{"
					+ "color:darkred;</style></head><body>");
			rs.next();
			pw.println("<form action='editurl?ah_id="+s1+"' method='post'>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>Ah_Name</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Balance</th>");
			pw.println("<th>DOJ</th>");
			pw.println("<th>DOB</th>");
			pw.println("<th>Created_Date</th>");
			pw.println("<th>Modified_Date</th>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='text' name='ahname' value="+rs.getString("ah_name")+"></td>");
			pw.println("<td><input type='email' name='email' value="+rs.getString("email")+"></td>");
			pw.println("<td><input type='number' name='balance' value="+rs.getString("balance")+"></td>");
			pw.println("<td><input type='date' name='doj' value="+rs.getString("doj")+"></td>");
			pw.println("<td><input type='date' name='dob' value="+rs.getString("dob")+"></td>");
			pw.println("<td><input type='date' name='created_date' value="+rs.getString("created_date")+"></td>");
			pw.println("<td><input type='date' name='modified_date' value="+rs.getString("modified_date")+"></td>");

			pw.println("</tr>");
			pw.println("</table>");
			pw.println("<input type=Submit value='Save' id='k'>");
			pw.println("<input type=Reset value='Cancel' id='k'>");

			
			//pw.println("<td><input type='text' name='modified_date' value="+rs.getString("modified_date")+"></td>");


			pw.println("</form></body></html>");
			pw.println();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
