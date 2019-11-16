

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SupForThisProdServlet
 */
@WebServlet("/SupForThisProdServlet")
public class SupForThisProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupForThisProdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String msg = "Error";
		String link = "supForThisPro.jsp?msg="+msg;
		String proId = request.getParameter("proId");
		String records = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
            PreparedStatement ps1=con1.prepareStatement("SELECT s.* FROM supplier s WHERE s.supId IN (SELECT p.supId from prodsupby p WHERE p.prodId = '"+proId+"')");  
	           
			ResultSet rs=ps1.executeQuery();  
            int i = 0;
            
            while(rs.next()){ 
            	PreparedStatement ps2=con2.prepareStatement("SELECT cost FROM prodsupby WHERE supId='"+rs.getString("supId")+"' AND prodId='"+proId+"'"); 
            	ResultSet rs1=ps2.executeQuery();
            	String price = "";
            	if(rs1.next()) {
            		price = rs1.getString("cost");
            	}
            	else {
            		price = "null";
            	}
            	records = records + "Supplier Id: " + rs.getString("supId") + "<br/>Supplier Name: " + rs.getString("supName") + "<br/>Contact Number: " + rs.getInt("contactNum") + "<br/>Email: " + rs.getString("email")  + "<br/>Price: " + price + "<br/><br/>";
				i++;
            }
            if(i == 0) {
				records = "There are no records for this week";
			}
			
            String body = ""
					+ "<html><head></head>"
					+ "<body>"
					+ "<h1>Suppliers</h1>"
					+ records
					+ "<br/><br/>";
            
			link =  "supForThisPro.jsp?msg="+body;
			response.sendRedirect(link);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
			response.sendRedirect(link);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
