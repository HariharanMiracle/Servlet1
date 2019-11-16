

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.AssignVehicle;
import com.project.model.Employee;
import com.project.model.Vehicle;

/**
 * Servlet implementation class AssignVehicleServlet
 */
@WebServlet("/AssignVehicleServlet")
public class AssignVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignVehicleServlet() {
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
		PrintWriter out = response.getWriter();
		String vnum=request.getParameter("vnum");
		String did=request.getParameter("did");
		String pur=request.getParameter("pur");
		String sdate=request.getParameter("sdate");
		int shrs=Integer.parseInt(request.getParameter("shrs"));
		int smins=Integer.parseInt(request.getParameter("smins"));
		String edate=request.getParameter("edate");
		int ehrs=Integer.parseInt(request.getParameter("ehrs"));
		int emins=Integer.parseInt(request.getParameter("emins"));
		
		AssignVehicle assignVehicle = new AssignVehicle(vnum, did, pur, sdate, edate, shrs, smins, ehrs, emins);
		
		String msg = "Error in assigning";
		String link = "assign_response.jsp?msg="+msg;
		
		
		Employee employee = new Employee();
		Vehicle vehicle = new Vehicle();
		
		//Check whether employee is present
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
			PreparedStatement ps1=con1.prepareStatement("select * from employee where eId='"+assignVehicle.geteId()+"'");  
            ResultSet rs1=ps1.executeQuery();  
            
            if(rs1.next()){  
            	employee.seteId(rs1.getString("eId"));
    			employee.setFname(rs1.getString("fname"));
    			employee.setLname(rs1.getString("lname"));
    			employee.setAddress(rs1.getString("address"));
    			employee.setTelephone(rs1.getInt("telephone"));
    			employee.setDob(rs1.getString("dob"));
            }
            else {
            	employee = null;
            }
		}
		catch(Exception e) {
			employee = null;
		}
		
		//Check whether vehicle is present
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
			PreparedStatement ps2=con2.prepareStatement("select * from vehicle where vnum='"+assignVehicle.getVnum()+"'");  
            ResultSet rs2=ps2.executeQuery();  
            
            if(rs2.next()){  
            	vehicle.setVnum(rs2.getString("vnum"));
    			vehicle.setModel(rs2.getString("model"));
    			vehicle.setDescr(rs2.getString("descr"));
    			vehicle.setCat(rs2.getString("cat"));
    			vehicle.setvAddDate(rs2.getString("vAddDate"));
    			vehicle.setManYear(rs2.getInt("manYear"));
            }
            else {
            	employee = null;
            }
		}
		catch(Exception e) {
			employee = null;
		}
		
		//assign vehicle
		try {
            if(vehicle == null) {
    			msg = "Vehicle id is incorrect";
    		}
    		else if(employee == null) {
    			msg = "Employee id is incorrect";
    		}
    		else {
    			Class.forName("com.mysql.jdbc.Driver");
                Connection con3=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
                String sql="insert into assign (`vnum`, `eId`, `purpose`, `sdate`, `edate`, `shrs`, `smins`, `ehrs`, `emins`) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	           PreparedStatement pst=con3.prepareStatement(sql);
    	           pst.setString(1,assignVehicle.getVnum());
    	           pst.setString(2,assignVehicle.geteId());
    	           pst.setString(3,assignVehicle.getPurpose());
    	           pst.setString(4,assignVehicle.getSdate()	);
    	           pst.setString(5,assignVehicle.getEdate());
    	           pst.setInt(6,assignVehicle.getShrs());
    	           pst.setInt(7,assignVehicle.getSmins());
    	           pst.setInt(8,assignVehicle.getEhrs());
    	           pst.setInt(9,assignVehicle.getEmins());
    	         
    	           pst.executeUpdate();
    	           msg = "Successfully assigned";
    	           link = "assign_response.jsp?msg=" + msg;
    		}
            response.sendRedirect(link);
		} catch (ClassNotFoundException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        link = "http://localhost:8081/Servlet1/assign_response.jsp?msg=" + msg;
	        response.sendRedirect(link);
	    } catch (SQLException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        link = "http://localhost:8081/Servlet1/assign_response.jsp?msg=" + msg;
	        response.sendRedirect(link);
	    }
	}

}
