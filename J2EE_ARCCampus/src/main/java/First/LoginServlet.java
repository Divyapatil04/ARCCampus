package First;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String un1 =req.getParameter("username");
		String pass =req.getParameter("password");
		
		try {
			resp.setContentType("text/html");
			PrintWriter pw=resp.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/arccampus","root","9923388663");
			
			String query="Select * from arcstudent where username=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, un1);
			pstmt.setString(2, pass);
			
			ResultSet res=pstmt.executeQuery();
			if(res.next()==true) {
//				pw.println("<h3>Welcome "+res.getString(2)+"</h3>");
				req.getRequestDispatcher("/Drive").forward(req, resp);
				
			}else {
				pw.println("<h3>Invalid Username and Password</h3>");
				
//				RequestDispatcher rd=req.getRequestDispatcher("Drive.html");
//				rd.forward(req, resp);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
