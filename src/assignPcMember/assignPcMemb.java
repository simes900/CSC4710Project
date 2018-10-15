package assignPcMember;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class assignPcMemb
 */
@WebServlet("/assignpcmemb")
public class assignPcMemb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int resultsLength = 0;
	public int paperLength = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public assignPcMemb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("servlet is connected");
		String[] emails = request.getParameterValues("pcmember");
		String[] emails2 = request.getParameterValues("pcmember"); //will be used near the end to output all pc members who have been assigned 3 papers already
		String[] paperId = request.getParameterValues("paper");
		System.out.println("emails length: " + emails.length);
		for(int i = 0; i < emails.length; i++){
			System.out.println("iteration: " + i + " emails:  " + emails[i]);
			
		}
		System.out.println("paperId: " + paperId[0]);
		
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	try{
	    		//Step1: Register JDBC driver
	    		try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		//Step 2: Open conection
	    		try {
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		try {
					stmt = conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		// get parameters and assign to java variables
	    		System.out.println("connected!");
	    		
	    		String sql ="SELECT email FROM review";
	    		String sql2 = "SELECT paperid FROM review WHERE paperid=" + paperId[0];

	    		//String [RESULTS_LENGTH] results;
	    		
	    		 String[] results = new String[100];
	    		
	    	                rs = null;
							try {
								rs = stmt.executeQuery(sql);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    	            
							System.out.println("connected!");
	    	               // loop through the result set
	    	               try {
							while (rs.next()) {
							       //System.out.println(rs.getString("email"));
								   results[resultsLength] = rs.getString("email");
								   resultsLength++;
							   }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    	               
	    	               paperLength = 0;
	    	               
	    	              	ResultSet rsPaper = null;
							try {
								System.out.println("connected2!");
								rsPaper = stmt.executeQuery(sql2);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
		    	               
	    	               	try {
								while (rsPaper.next()){
									//rsPaper.getString("paperid");
									paperLength++;
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    	               	
	    	               	System.out.println("paperLength: " + paperLength);
	    	               	
	    	               	
	    	        
	    	        if(paperLength >= 3){
	    	        	String message = "paper already assigned 3 pc member! No PC members were assigned.";
	    	    		System.out.println(message);
	    	    		request.setAttribute("message", message);
	    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/Queryresult/review.jsp");
	    	    		dispatcher.forward(request, response);
	    	        	//request.getRequestDispatcher("/Queryresult/review.jsp").forward(request, response);
	    	        }
	    	        else{
	    	        
	    	        for(int i = 0; i < resultsLength; i++){
	    	        	System.out.println("array: " + results[i]);
	    	        }
	    	        
	    	        int a = 0;
	    	        int b = 0; 
	    	        int c = 0;
	    	        
	    	        int[] abc = {0,0,0};  // integer array which will hold the total number of reviews each pc member has been assigned for up to 3 
	    	        // pc members selected from the wform
	    	        
	    	        for(int i = 0; i < emails.length; i++){
	    	        	System.out.println("emails[i]: " + emails[i]);
	    	        	for(int j = 0; j < resultsLength; j++){
	    	        		System.out.println("results[j] " + results[j]);
	    	        		if(emails[i].equals(results[j])){
	    	        			System.out.println(emails[i] + " matches " + results[j]);
	    	        			if(i == 0){
	    	        				abc[0]++;
	    	        			}
	    	        			else if(i == 1){
	    	        				abc[1]++;
	    	        			}
	    	        			else if(i == 2){
	    	        				abc[2]++;
	    	        			}
	    	        		}
	    	        	}
	    	        }
	    	        
	    	        System.out.println("int a: " + abc[0]);
	    	        System.out.println("int b: " + abc[1]);
	    	        System.out.println("int c: " + abc[2]);
	    	        
	    	        
	    	        for(int i = 0; i < emails.length; i++){
	    	        	System.out.println("emails[i] " + emails[i]);
	    	        	if(abc[i] >= 5){
	    	        		emails[i] = null;
	    	        	}
	    	        	else{
	    	        		emails2[i] = null;
	    	        	}
	    	        	
	    	        }
	    	        
	    	        
	    	        
	    	
	    	        
	    	        for(int i = 0; i < emails.length; i++){
	    	        	System.out.println("emails[i] " + emails[i]);
	    	        }
	    	        
	    	        // string which will inject sql statement
	    	    	String sqltuplereview; 
	    	    	Boolean wasAdded = false; // boolean value representing whether anyone was added to the database. starts as false;
	    	    	// string which will appear on the next page telling what happened
	    	    	String outputMessage = "The paper was previously assigned " + paperLength + " pc member(s). "; 
	    	    	ResultSet exists = null;
					for(int i = 0;i< emails.length; i++){
						if(emails[i] != null && paperLength != 3){
							
							paperLength++;
						
						sqltuplereview = null;
						try {
							exists = stmt.executeQuery("SELECT * FROM sampledb.review WHERE email='"+emails[i]+"' AND paperid='"+paperId[0]+"'");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							if(exists.next() != false){
								System.out.println("it's already in there!");
								outputMessage = outputMessage + "'" + emails[i] + "'" + " was already assigned to that paper! ";
							}
							
							else{
								wasAdded = true;
							sqltuplereview = "INSERT INTO review(paperid, email,recommendation,sdate,comment) VALUES ('"+paperId[0]+"','"+emails[i]+"', NULL, NULL, NULL);";
							outputMessage = outputMessage + "'" + emails[i] + "'" + ", ";
							try {
								stmt.executeUpdate(sqltuplereview);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
					
					
					
					}
					
					
					// do if no one was added to the database
					if(!wasAdded){
						outputMessage = outputMessage + " NO ONE ";
					}
					
					
					outputMessage = outputMessage + "was/were added to the database. ";
					
					Boolean is2many = false; //boolean value representing whether any pc members selected had already been assigned 3 papers;
					
					for(int i = 0; i < emails2.length; i++){
						
						if(emails2[i] != null){
							outputMessage = outputMessage + " " + emails2[i] + ", ";
							is2many = true;
						}
						
					}
					
					if(is2many == true){
					outputMessage = outputMessage + "had already been assigned 3 reviews and could not be assigned to any more reviews.";
					}
							
    	    		System.out.println(outputMessage);
    	    		request.setAttribute("message", outputMessage);
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/Queryresult/review.jsp");
    	    		dispatcher.forward(request, response);
	    	        
	    	        } //end of else statement
	    	        
    	}finally{
    		if(rs != null){
    			try{
    				rs.close();
    				System.out.println("ResultSet closed in assignPcmem.java");
    			}catch(SQLException e){
    				//ignore
    			}
    		}
    		if(stmt != null){
    			try{
    				stmt.close();
    				System.out.println("Statement closed assignPcmem.java");
    			}catch(SQLException e){
    				//ignore
    			}
    		}
    		if(conn != null){
    			try{
    				conn.close();
    				System.out.println("Connection closed assignPcmem.java");
    			}catch(SQLException e){
    				//ignore
    			}
    		}
    		
    		
    	}
		
	}

}

