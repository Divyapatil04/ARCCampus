package First;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Eligible")
public class Eligible extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
//		RequestDispatcher rd=req.getRequestDispatcher(null);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arccampus","root","9923388663");
			
			
			String un=req.getParameter("username");
			String pass1=req.getParameter("password");
			
            String query="select * from arcstudent where username=? and password=?";
            PreparedStatement pstmt1=con.prepareStatement(query);
            pstmt1.setString(1,un);
            pstmt1.setString(2,pass1);
			ResultSet res1=pstmt1.executeQuery();
			
			res1.next();
			String ten =res1.getString(3);
			String twe =res1.getString(4);
			String grad=res1.getString(5);
			
			
			String query1="select* from drives where 10th<=? and 12th<=? and graduation<=?";
			PreparedStatement pstmt=con.prepareStatement(query1);
			pstmt.setString(1, ten);
			pstmt.setString(2, twe);
			pstmt.setString(3, grad);
			ResultSet res2=pstmt.executeQuery();
			pw.println("<h3>Drives you are eligible for:</h3>");
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
			String te=res2.getString(3);
			String tw=res2.getString(4);
			String gra=res2.getString(5);
			String profile=res2.getString(6);
			String pac=res2.getString(7);
			
			pw.println("<tr>\r\n"
					+ "<td>"+id+"</td>\r\n"
					+ "<td>"+name+"</td>\r\n"
					+ "<td>"+te+"</td>\r\n"
					+ "<td>"+tw+"</td>\r\n"
					+ "<td>"+gra+"</td>\r\n"
					+ "<td>"+profile+"</td>\r\n"
					+ "<td>"+pac+"</td>\r\n"
					+ "</tr>");	
		}
		pw.println("</table>");
	
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
