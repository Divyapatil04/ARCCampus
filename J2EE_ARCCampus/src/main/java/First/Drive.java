package First;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Drive")
public class Drive extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		String un1 =req.getParameter("username");
		String pass =req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arccampus","root","9923388663");
//			PrintWriter pw=resp.getWriter();
//			req.getRequestDispatcher("/Eligible").forward(req, resp);

				String query2="select * from drives ";
				Statement stmt =con.createStatement();
				ResultSet res2=stmt.executeQuery(query2);
				pw.println("<h3>Availabe for drives:</h3>");
				pw.println("<table border=1\r\n>"
						+ "\r\n"
						+ "<tr>\r\n"
						+ "<th>Id</th>\r\n"
						+ "<th>Name</th>\r\n"
						+ "<th>10th</th>\r\n"
						+ "<th>12th</th>\r\n"
						+ "<th>Graduation</th>\r\n"
						+ "<th>Profile</th>\r\n"
						+ "<th>Package</th>\r\n"
						+ "</tr>");
			while(res2.next()==true) {
				int id=res2.getInt(1);
				String name=res2.getString(2);
				String ten=res2.getString(3);
				String twe=res2.getString(4);
				String grad=res2.getString(5);
				String profile=res2.getString(6);
				String pac=res2.getString(7);
				
				pw.println("<tr>\r\n"
						+ "<td>"+id+"</td>\r\n"
						+ "<td>"+name+"</td>\r\n"
						+ "<td>"+ten+"</td>\r\n"
						+ "<td>"+twe+"</td>\r\n"
						+ "<td>"+grad+"</td>\r\n"
						+ "<td>"+profile+"</td>\r\n"
						+ "<td>"+pac+"</td>\r\n"
						+ "</tr>");	
			}
			pw.println("</table>");
			
//			req.getRequestDispatcher("/Eligible").forward(req, resp);
			req.getRequestDispatcher("/Eligible").include(req, resp);
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
