package forgot;

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

/**
 * Servlet implementation class forgot
 */
@WebServlet("/forgot")
public class forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
	    String uname = request.getParameter("uname");
	    String sQuestion = request.getParameter("sQuestion");
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","alig3t@r");
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery("select uname,upass from user_reg where uname='"+uname+"' and sQuestion='"+sQuestion+"'");
	      
	      if(rs.next()) {
	    	  String Password = request.getParameter("upass");
	    	  PreparedStatement pst =(PreparedStatement) con.prepareStatement("insert into user_reg(upass)values(?)");
	    	  
	    	  pst.setString(4,Password);
	    	  
	    	  int i = pst.executeUpdate();
	          if(i!=0){  
	            out.println("\n\nRecord has been inserted");  
	          } 
	          
	          else{  
	            out.println("\n\nFailed to insert the data");
	           }  
	          pst.close();
	        }else {
		    	  out.println("\n\nWrong id and/or Security Question");
		      }
	      
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
		
	}

}
