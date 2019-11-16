

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
 * Servlet implementation class TrackOrderServlet
 */
@WebServlet("/TrackOrderServlet")
public class TrackOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackOrderServlet() {
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
		String msg = "Error in assigning";
		String link = "assign_response.jsp?msg="+msg;
		String records = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
			PreparedStatement ps1=con1.prepareStatement("select * from order_to_suppliers");  
            ResultSet rs=ps1.executeQuery(); 
            int i = 0;
            while(rs.next()) {
            	String link1 = "<a href = changeStatus.jsp?id="+rs.getString("orderId")+">Change Status</a>";
				records = records + "Order Id: " + rs.getString("orderId") + "<br/>Supplier Id: " + rs.getString("supplierId") + "<br/>Total Price: " + rs.getFloat("totalPrice") + "<br/>Date Placed: " + rs.getString("datePlaced")  + "<br/>Date Delivered: " + rs.getString("dateDelivered")  + "<br/>Status: " + rs.getString("status") +"<br/>Discount: " + rs.getFloat("discount") + "<br/>"+link1+"<br/><br/>";
				i++;
            }
            if(i == 0) {
            	records = "There are no orders";
            }
            link = "trackOrder.jsp?msg="+records;
            response.sendRedirect(link);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
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
