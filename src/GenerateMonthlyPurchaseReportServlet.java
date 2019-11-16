

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

/**
 * Servlet implementation class GenerateMonthlyPurchaseReportServlet
 */
@WebServlet("/GenerateMonthlyPurchaseReportServlet")
public class GenerateMonthlyPurchaseReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateMonthlyPurchaseReportServlet() {
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
		String link = "genMonthlyPurRep.jsp?msg="+msg;
		String records = "";
		float total = 0;
		String morderId = "", mdatePlaced = "", mdateDelivered = "", mstatus = "", msupplierId = "";
		float mdiscount = 0;
		float mtotalPrice = 0;
		String stotalPrice = "";
		String sdiscount = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
            Date date = new Date();
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String cdate = df.format(date);
    		String xcdate = cdate.substring(0, 8);
            PreparedStatement ps1=con1.prepareStatement("select * from order_to_suppliers where dateDelivered LIKE '"+xcdate+"%'");  
            ResultSet rs=ps1.executeQuery(); 
            int i = 0;
            while(rs.next()) {
            	String orderId, datePlaced, dateDelivered, status, supplierId;
				float totalPrice, discount;
            	orderId = rs.getString("orderId");
				datePlaced = rs.getString("datePlaced");
				dateDelivered = rs.getString("dateDelivered");
				status = rs.getString("status");
				supplierId = rs.getString("supplierId");
				totalPrice = rs.getFloat("totalPrice");
				discount = rs.getFloat("discount");
				stotalPrice = Float.toString(totalPrice);
				sdiscount = Float.toString(discount);
				
				if(mtotalPrice < totalPrice) {
					mtotalPrice = totalPrice;
					morderId = orderId;
					mdatePlaced = datePlaced;
					mdateDelivered = dateDelivered;
					mstatus = status;
					msupplierId = supplierId;
					mdiscount = discount;
				}
				
            	records = records + "Order Id: " + orderId + "<br/>Supplier Id: " + supplierId + "<br/>Total Price: " + stotalPrice + "<br/>Date Placed: " + datePlaced  + "<br/>Date Delivered: " + dateDelivered  + "<br/>Status: " + status +"<br/>Discount: " + sdiscount + "<br/><br/>";
				i++;
				total = total + totalPrice;
            }
            if(i == 0) {
				records = "There are no purchase for this month";
			}
			String smtotalPrice = Float.toString(mtotalPrice);
			String smdiscount = Float.toString(mdiscount);
			String sTotal =  Float.toString(total);
			String si = Integer.toString(i);
			String hello = ""
					+ "<h1>MonthlyPurchaseReport</h1>"
					+ "<br/><br/>"
					+ records
					+ "<h4>Total purchase cost: "+sTotal+"</h4>"
					+ "<h4>Number of purchase: "+si+"</h4>"
					+ "<br/>"
					+ "<h4><u>Highest paid purchase</u></h4>"
					+ "<h4>Order ID: "+morderId+"</h4>"
					+ "<h4>SupplierId: "+msupplierId+"</h4>"
					+ "<h4>Total Price: "+smtotalPrice+"</h4>"
					+ "<h4>Date Placed: "+mdatePlaced+"</h4>"
					+ "<h4>Date Delivered: "+mdateDelivered+"</h4>"
					+ "<h4>Discount: "+smdiscount+"</h4>";
				link = "genMonthlyPurRep.jsp?msg="+hello;
				 response.sendRedirect(link);
		}
		catch(Exception e){
			
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
