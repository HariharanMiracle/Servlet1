

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/addVehicle")
public class AddVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddVehicleServlet() {
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
		String vnum=request.getParameter("vnum");
		String model=request.getParameter("model");
		String desc=request.getParameter("desc");
		String cate=request.getParameter("cate");
		int manf=Integer.parseInt(request.getParameter("manf"));
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String cdate = df.format(date);
		
		Vehicle vehicle = new Vehicle(vnum, model, desc, cate, cdate, manf);
		
		String msg = "Error in adding a vehicle";
		String link = "add_vehicle_response.jsp?msg=";
		PrintWriter out = response.getWriter();
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
	                String sql="insert into vehicle (`vnum`, `model`, `descr`, `cat`, `vAddDate`, `manYear`) " + "values(?, ?, ?, ?, ?, ?)";
	           PreparedStatement pst=con.prepareStatement(sql);
	           pst.setString(1,vehicle.getVnum());
	           pst.setString(2,vehicle.getModel());
	           pst.setString(3,vehicle.getDescr());
	           pst.setString(4,vehicle.getCat());
	           pst.setString(5,vehicle.getvAddDate());
	           pst.setInt(6,vehicle.getManYear());
	         
	           pst.executeUpdate();
	           
	           msg = "You have successfully added a vehicle.";
	           link = "http://localhost:8081/Servlet1/add_vehicle_response.jsp?msg=" + msg;
	           response.sendRedirect(link);
	           
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	            link = "http://localhost:8081/Servlet1/add_vehicle_response.jsp?msg=" + msg;
		        response.sendRedirect(link);
	        } catch (SQLException ex) {
	            Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	            link = "http://localhost:8081/Servlet1/add_vehicle_response.jsp?msg=" + msg;
		        response.sendRedirect(link);
	        }
	}

}
