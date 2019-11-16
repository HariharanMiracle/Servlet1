

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.Vehicle;

/**
 * Servlet implementation class UpdateVehicleServlet
 */
@WebServlet("/UpdateVehicleServlet")
public class UpdateVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVehicleServlet() {
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
     		String model=request.getParameter("model");
     		String desc=request.getParameter("desc");
     		String cate=request.getParameter("cate");
     		int manf=Integer.parseInt(request.getParameter("manf"));
     		
     		String msg = "Error in updating";
    		String link = "";
    		
    		Date date = new Date();
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String cdate = df.format(date);
    		
    		Vehicle vehicle = new Vehicle(vnum, model, desc, cate, cdate, manf);
    		
    		PreparedStatement ps=con.prepareStatement(  
                    "UPDATE `vehicle` SET `model`='"+vehicle.getModel()+"',`descr`='"+vehicle.getDescr()+"',`cat`='"+vehicle.getCat()+"',`vAddDate`='"+vehicle.getvAddDate()+"',`manYear`='"+vehicle.getManYear()+"' WHERE `vnum`='"+vehicle.getVnum()+"'");   
         
	       int status=ps.executeUpdate();
	       link = "updVeh.jsp?msg=Updated Successful!!!";
	       response.sendRedirect(link);
		} catch (ClassNotFoundException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("Error1");
		} catch (SQLException ex) {
	        Logger.getLogger(AddVehicleServlet.class.getName()).log(Level.SEVERE, null, ex);
	        System.out.println("Error2");
		}
	}

}
