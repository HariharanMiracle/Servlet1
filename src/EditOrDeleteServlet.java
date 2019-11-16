

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;  
import java.sql.*;  

import com.project.model.Vehicle;

/**
 * Servlet implementation class EditOrDelete
 */
@WebServlet("/editORdelete")
public class EditOrDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrDeleteServlet() {
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/vehi","root","");
             
            String vnum=request.getParameter("vnum");
     		String submit=request.getParameter("submit");
     		String msg = "Error in updating or deleting";
    		String link = "";
     		
     		PreparedStatement ps=con.prepareStatement("select * from vehicle where vnum=?");  
            ps.setString(1,vnum);  
            ResultSet rs=ps.executeQuery();  
            Vehicle veh = new Vehicle();
            if(rs.next()){  
            	veh.setVnum(rs.getString("vnum"));
    			veh.setModel(rs.getString("model"));
    			veh.setDescr(rs.getString("descr"));
    			veh.setCat(rs.getString("cat"));
    			veh.setvAddDate(rs.getString("vAddDate"));
    			veh.setManYear(rs.getInt("manYear"));  
            }
            else {
            	veh = null;
            }
     		
     		
     		if(submit.equals("Edit")) {
     			if(veh == null) {
     				msg = "There is no such vehicle";
     				link = "updateV_response.jsp?msg=" + msg;
     				response.sendRedirect(link);
     			}
     			else {
     				String xvnum = veh.getVnum();
     				String model = veh.getModel();
     				String desc = veh.getDescr();
     				String cate = veh.getCat();
     				int manf = veh.getManYear();	
     				link = "update_vehicle.jsp?vnum=" +xvnum+ "&model=" +model+ "&desc=" +desc+ "&cate=" +cate+ "&manf=" +manf;
     				response.sendRedirect(link);
     			}
     		}
     		else {
     			if(veh == null) {
     				msg = "There is no such vehicle";
     				link = "deleteV_response.jsp?msg=" + msg;
     			}
     			else {
     				PreparedStatement ps2=con.prepareStatement("delete from vehicle where vnum=?");  
     	            ps2.setString(1,vnum);  
     	            int status=ps2.executeUpdate();
     	            msg = "Deleted vehicle successfully";
     	            link = "deleteV_response.jsp?msg=" + msg;
     			}
     			response.sendRedirect(link);
     		}
		} catch (ClassNotFoundException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("Error1");
		} catch (SQLException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("Error2");
		}
	}
}
