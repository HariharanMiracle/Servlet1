

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
 * Servlet implementation class WeeklyReportServelet
 */
@WebServlet("/WeeklyReportServelet")
public class WeeklyReportServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeeklyReportServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			PreparedStatement ps1=con1.prepareStatement("SELECT * FROM `assign` WHERE sdate = '"+d1+"' or sdate = '"+d2+"' or sdate = '"+d3+"' or sdate = '"+d4+"' or sdate = '"+d5+"' or sdate = '"+d6+"' or sdate = '"+d7+"' or sdate = '"+d8+"'");  
            ResultSet rs1=ps1.executeQuery();  
            int i = 0;
            
            while(rs1.next()){  
            	record = record + "Vehicle Number: " + rs1.getString("vnum") + "<br/>Employee Id: " + rs1.getString("eId") + "<br/>Purpose: " + rs1.getString("purpose") + "<br/>Starting date: " + rs1.getString("sdate")  + "<br/>Ending date: " + rs1.getString("edate")  + "<br/>Starting time: " + rs1.getString("shrs") + ":" + rs1.getString("smins")  + "<br/>Ending time: " + rs1.getString("ehrs") + ":" + rs1.getString("emins") + "<br/><br/>";
				i++;
            }
            if(i == 0) {
				record = "There are no records for this week";
			}
			
			link = "WeeklyReport.jsp?msg="+record;
			response.sendRedirect(link);
		}
		catch(Exception e) {
			System.out.println("Error: " + e);
			response.sendRedirect(link);
		}
	}

}
