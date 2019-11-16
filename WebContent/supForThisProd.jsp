<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="NewFile.css" />
<script>
        function getCalendar(){


            var html ="";

            var currentFullDate = new Date();
            var currentMonth = currentFullDate.getMonth();
            var currentDate = currentFullDate.getDate();
            var currentYear = currentFullDate.getFullYear();
            var nextMonth = currentMonth+1;
            var monthBefore = currentMonth - 1;

            var daysInFeb ="";
            if (currentMonth == 1){
                if ( (currentYear % 100 != 0) && (currentYear % 4 == 0) || (currentYear % 400 == 0)){
                    daysInFeb  = 29;
                }
                else{
                    daysInFeb  = 28;
                }
            }

            var monthByNames = ["January","February","March","April","May","June","July","August","September","October","November", "December"];
            var datesByNames = ["Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday", "Saturday"];
            var lastDateOfMonth = ["31", ""+daysInFeb+"","31","30","31","30","31","31","30","31","30","31"]

            var commingDate = new Date(nextMonth +' 1 ,'+currentYear);
            var commingDateByName= commingDate.getDay();
            var weekdays = commingDateByName;
            var numberOfDays = lastDateOfMonth[currentMonth];

            while (commingDateByName>0){
                html += "<td class='monthPre'></td>";

                commingDateByName--;
            }
            var count = 1;
            while (count <= numberOfDays){

                if (weekdays > 6){
                    weekdays = 0;
                    html += "</tr><tr>";
                }

                if (count == currentDate){

                    html +="<td class='dayNow'  onMouseOver='this.style.background=\"#A0E8AD\"; this.style.color=\"#FFFFFF\"' "+
                        "onMouseOut='this.style.background=\"#FFFFFF\"; this.style.color=\"#00FF00\"'>"+count+"</td>";
                }else{
                    html +="<td class='monthNow' onMouseOver='this.style.background=\"#A0E8AD\"'"+
                        " onMouseOut='this.style.background=\"#FFFFFF\"'>"+count+"</td>";

                }



                weekdays++;
                count++;
            }



            // building the calendar html body.
            var calendarBody = "<table class='calendar'> <tr class='monthNow'><th colspan='7'>"
                +monthByNames[currentMonth]+" "+ currentYear +"</th></tr>";
            calendarBody +="<tr class='dayNames'>  <td>Sun</td>  <td>Mon</td> <td>Tues</td>"+
                "<td>Wed</td> <td>Thurs</td> <td>Fri</td> <td>Sat</td> </tr>";
            calendarBody += "<tr>";
            calendarBody += html;
            calendarBody += "</tr></table>";

            document.getElementById("calendar").innerHTML=calendarBody;

        }
	</script>
</head>
<body background="download2.jpg">
<header>
		<div>
			<img class="mainicon"src="logo.jpg">
		</div>
	<div>
	<ul>
		<input type="button" class="btn2" value="Login"><br/><br/><br/><br/>
		<input type="button" class="btn3" value="Register">
	</ul></div>
	
	<div class="tagline">
	<h3><center>Welcome to Warakagoda Tea Factory!!</center></h1>
	</div>	
			
			<div class="wrd">
			<h5  font-family:  font-family: "Times New Roman", Times, serif;><center><i>Feel the Wonder of Freashness</i></center></h5>
			
			</div>
			<center>
			<br/>
				<%
					String bla = request.getParameter("id");
				%>
			</center>
		
</header>
<div class="rss">
	<body onload="getCalendar()">

	<div id="calendar"></div>
	<a href="https ://www.facebook.com/ArogyaPharmecy" target="_blank" class="imgshine"><img  alt="picture" src="logo.png"></a>
	<a href="https://twitter.com/ArogyaPharmecy" target="_blank" class="imgshine"><img  alt="picture" src="ios_homescreen_icon.png"></a>
	<a href="https://instagram.com/unionchemist" target="_blank" class="imgshine"><img  alt="picture" src="instagram.jpg"></a>


</div>

<div class="tabs">
	<ol>
		<li>
		  <div align="center"><a class="shine" href="Home.html">
			Home
			</a></div>
		</li>             
		  <li>
			<div align="center"><a class="shine" href="My Profile.html">
			  My Profile
			  </a></div>
		</li>         
		<li>
		  <div align="center"><a class="shine" href="Our Products.html">
			Our Products
			</a></div>
		</li>
		<li>
		  <div align="center"><a class="shine" href="About us.html">
			About us
			</a></div>
		</li>        
		  <li>
			<div align="center"><a class="shine" href="Contact us.html">
			  Contact us
			  </a></div>
		</li>
		 <li>
		   <div align="center"><a class="shine" href="Our Team.html">
			 Our Team
			 </a></div>
		</li>
		<li>
		  <div align="center"><a class="shine" href="FAQ.html">
			FAQ
			</a></div>
		</li>
		
	</ol>
</div>


<div class="Slider">

  <img class="mySlides" src="deliver11.jpg">
  <img class="mySlides" src="diabatic.jpg">
  <img class="mySlides" src="pharmacy21.jpg">
  <img class="mySlides" src="pharmacy31.jpg">
</div>
<script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 5000); // Change image every 5 seconds
}
</script>

<div class="udr">
	
</div>
<footer>The website and its content is copyright of Warakagoda Estate -@ Warakagoda Estate 2019.All rights reserved.</footer>
</body>
</html>