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
 * Servlet implementation class ForwardServlet
 */
public class ForwardServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
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
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String s1=request.getParameter("cuname");
			PreparedStatement ps=conn.prepareStatement("select * from account1 where ah_name=?");
			ps.setString(1, s1);
			ResultSet rs=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html><head><style>table,th,td{"
					+ "border:2px solid cadetblue;"
					+ " border-collapse: collapse;"
					+ "text-align:center;"
					+ "}"
					+ "th{"
					+ "color:blue;}"
					+ "td{"
					+ "color:rgb(173, 13, 185);}"
					+ "a{"
					+ "text-decoration: none;"
					+ "}"
					+ "body{"
					+ "background-color:bisque;"
					+ "text-align: center;"
					+ "}"
					+ "#k{"
					+ "font-size:35px;"
					+ "color:blue;"
					+ "}"
					+ "h1{"
					+ " color: blueviolet;}"
					+ "</style></head><body>");
			pw.println("<h1 style=text-align:center;>Account History of "+s1+"....</h1>");
			pw.println("<table style=width:100%>");
			pw.println("<tr>");
			pw.println("<th>Ah_Id</th>");
			pw.println("<th>Ah_Name</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Balance</th>");
			pw.println("<th>DOJ</th>");
			pw.println("<th>DOB</th>");
			pw.println("<th>Cretaed_Date</th>");
			pw.println("<th>Modified_Date</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getString("ah_id")+"</td>");
				pw.println("<td>"+rs.getString("ah_name")+"</td>");
				pw.println("<td>"+rs.getString("email")+"</td>");
				pw.println("<td>"+rs.getString("balance")+"</td>");
				pw.println("<td>"+rs.getString("doj")+"</td>");
				pw.println("<td>"+rs.getString("dob")+"</td>");
				pw.println("<td>"+rs.getString("created_date")+"</td>");
				pw.println("<td>"+rs.getString("modified_date")+"</td>");
				pw.println("<td><a href=edit?ah_id="+rs.getString("ah_id")+">Edit</a></td>");
				pw.println("<td><a href=delete?ah_name="+rs.getString("ah_name")+">Delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			pw.println("<a href=homepage.html id='k'>Click and Go Out</a>");
			pw.println("</body></html>");
			
			pw.println();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
