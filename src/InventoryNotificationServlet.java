

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.AssignVehicle;

/**
 * Servlet implementation class InventoryNotificationServlet
 */
@WebServlet("/InventoryNotificationServlet")
public class InventoryNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryNotificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		PrintWriter out = response.getWriter();
		String msg = "Error in assigning";
		String link = "assign_response.jsp?msg="+msg;
		String record = "";
		AssignVehicle assignVehicle = new AssignVehicle();
		
		String partDate = cdate.substring(0, 8);
		int day = Integer.parseInt(cdate.substring(8,10));
		
		String d1, d2, d3, d4, d5, d6, d7, d8;
		
		if(day >= 1 && day < 9) {
			d1 = partDate + "01";
			d2 = partDate + "02";
			d3 = partDate + "03";
			d4 = partDate + "04";
			d5 = partDate + "05";
			d6 = partDate + "06";
			d7 = partDate + "07";
			d8 = partDate + "08";
		}
		else if(day >= 9 && day < 17) {
			d1 = partDate + "09";
			d2 = partDate + "10";
			d3 = partDate + "11";
			d4 = partDate + "12";
			d5 = partDate + "13";
			d6 = partDate + "14";
			d7 = partDate + "15";
			d8 = partDate + "16";
		}
		else if(day >= 17 && day < 24) {
			d1 = partDate + "17";
			d2 = partDate + "18";
			d3 = partDate + "19";
			d4 = partDate + "20";
			d5 = partDate + "21";
			d6 = partDate + "22";
			d7 = partDate + "23";
			d8 = partDate + "24";
		}
		else {
			d1 = partDate + "25";
			d2 = partDate + "26";
			d3 = partDate + "27";
			d4 = partDate + "28";
			d5 = partDate + "29";
			d6 = partDate + "30";
			d7 = partDate + "31";
			d8 = partDate + "32";
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
			PreparedStatement ps1=con1.prepareStatement("select * from products where quantity < 10 ORDER BY quantity ASC");  
            ResultSet rs1=ps1.executeQuery();  
            int i = 0;
            
            while(rs1.next()){  
            	String link1 = "<a href = http://localhost:8081/Servlet1/SupForThisProdServlet?proId="+rs1.getString("productId")+">Suppliers</a>";
            	record = record + "Product Id: " + rs1.getString("productId") + "<br/>Product Name: " + rs1.getString("productName") + "<br/>Quantity: " + rs1.getInt("quantity") + "<br/>Unit Price: " + rs1.getDouble("unitPrice")  + "<br/>Description: " + rs1.getString("description") + "<br/>" + link1 + "<br/><br/>";
				i++;
            }
            if(i == 0) {
				record = "There are no records for this week";
			}
			
            String body = ""
					+ "<h1>Inventory level notification</h1>"
					+ record
					+ "<br/><br/>";
            
			link = "inventNotifi.jsp?msg="+body;
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
