package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter();   
        Connection conn=null;

    try{  
      String Fname = request.getParameter("fName");   
      String Lname = request.getParameter("lName");  
      String Uname = request.getParameter("uname");    
      String Password = request.getParameter("pass");  
      String Security = request.getParameter("sQuestion");  

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","alig3t@r"); 
      PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into user_reg(uname,fName,lName,upass,sQuestion)values(?,?,?,?,?)");
      
      pst.setString(1,Uname);
      pst.setString(2,Fname);         
      pst.setString(3,Lname);
      pst.setString(4,Password);
      pst.setString(5,Security);


      int i = pst.executeUpdate();
      if(i!=0){  
        pw.println("\n\nRecord has been inserted");  
        
      }  
      else{  
        pw.println("\n\nFailed to insert the data");
       }  
      pst.close();
    }  
    catch (Exception e){  
      pw.println(e);  
    }  

	}
}

