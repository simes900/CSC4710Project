package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class searchPredefined
 */
@WebServlet("/searchpredefined")
public class searchPredefined extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchPredefined() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		
		
		
String fieldInput = (String) request.getAttribute("allAccepted");
		
		
		System.out.println("fieldInput: " + fieldInput);
		
		
		
			
			doSomething(request, response,fieldInput);
			
		
		
		
		
		
	}
	
	
	
	
	public static void doSomething(HttpServletRequest request, HttpServletResponse response, String thingToChange) throws ServletException, IOException {
		
		
		
		
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = "";
		
		ArrayList<String> results = new ArrayList<String>();
		int i = 0;
		
		String columnName1 = "";
		String columnName2 = "";
		
		if(thingToChange.equals("allAcceptedPapers")){
			sql = "SELECT P.paperid,P.title FROM paper P, review R WHERE R.recommendation='A' AND P.paperid=R.paperid GROUP BY paperid HAVING COUNT(*)>1";
			
			results.add("All Papers Which have been Accepted");
			results.add("Paper ID");
			results.add("Title");
			columnName1 = "paperid";
			columnName2 = "title";
			try {
				Statement statementView = conn.createStatement();
				String code = "CREATE OR REPLACE VIEW allAcceptedPapers AS SELECT P.paperid,P.title FROM paper P, review R WHERE R.recommendation='A' AND P.paperid=R.paperid GROUP BY paperid HAVING COUNT(*)>1"; 
				statementView.executeUpdate(code);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if(thingToChange.equals("pcMemberReviewedMostPapers")){
			sql = "SELECT email, COUNT(email) AS totalPapersReviewed FROM sampledb.review " +
					"GROUP BY email " +
					"HAVING COUNT(email) = ( SELECT MAX(totalPapersReviewed) AS highest_total " +
					"FROM (SELECT email, COUNT(email) AS totalPapersReviewed FROM sampledb.review " +
					"GROUP BY email) AS T);";

			results.add("PC Member(s) who reviewed the most papers");
			results.add("Email");
			results.add("Total Papers Reviewed");
			columnName1 = "email";
			columnName2 = "totalPapersReviewed";
		}
		else if(thingToChange.equals("pcMemberNoAssignedPapers")){
			sql = "SELECT P.name,P.email FROM pcmember P WHERE email NOT IN (SELECT email FROM review);";
			results.add("PC Member(s) who have no papers assigned to them");
			results.add("Name");
			results.add("Email");
			columnName1 = "name";
			columnName2 = "email";
		}
		else{
			int numberOfAssigned = Integer.valueOf(thingToChange.substring(0,1));
			System.out.println("number of assigned: " + numberOfAssigned);
			sql = "SELECT R.email,P.name FROM review R, pcmember P WHERE R.email = P.email GROUP BY R.email HAVING COUNT(R.email) > " + (numberOfAssigned - 1) + " ORDER BY R.email;";
			results.add("PC Member(s) who have " + numberOfAssigned + " paper(s) assigned to them");
			results.add("Email");
			results.add("Name");
			columnName1 = "email";
			columnName2 = "name";
		}
	
			
			
			try {
				statement=conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				resultSet = statement.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(resultSet.next()){
					
					results.add(resultSet.getString(columnName1));
					
					results.add(resultSet.getString(columnName2));
					
			//System.out.println(resultSet.getString("paperid") + " " + resultSet.getString("title"));
				
				}
				
				
				System.out.println("resultset length: " + results.size());
				
				request.setAttribute("message", results);
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/Queryresult/searchResults.jsp");
	    		dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
	
	
	
	

}

}