package modify;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paper.Paper;
import createTables.findAll;

//*********** STILL NEED TO IMPLEMENT CONSTRAINTS. Randomly assign pc members to review, cannot be more than 5 papers etc

@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet resultSet = null;
    	
		String[] fieldInput = (String[]) request.getAttribute("fieldInput");
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			if(fieldInput.length == 2){
				//insert pcmember
				String query = "insert into pcmember (email, name)values(?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.execute();
			}else if(fieldInput.length == 5){ 
				// insert review
				String query = "insert into review (sdate, comment, recommendation, paperid, email) values(?,?,?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.setString(3, fieldInput[2]);
				preparedStmt.setString(4, fieldInput[3]);
				preparedStmt.setString(5, fieldInput[4]);
				preparedStmt.execute();
			}else{ 
				
				//insert paper
				String query = "insert into paper(title,abstract,pdf) values(?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.setString(3, fieldInput[2]);
				preparedStmt.execute();
				
				//insert authors
				insertAuthorAndWritten(fieldInput);
			}
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(resultSet!=null){
					resultSet.close();
				}
			}catch(SQLException se){
				//do nothing
			}
			try{
    			if(preparedStmt!=null){
    				preparedStmt.close();
    			}
    		}catch(SQLException se){
    			//do nothing
    		}
    		try{
    			if(conn!=null){
    				conn.close();
    			
    			}
    		}catch(SQLException se){
    				se.printStackTrace();
    		}//end 
		}
		findAll.doPost(request,response);
	}
	
	public String findPaperId(String [] fieldInput){
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet resultSet = null;
    	String retrieveSql;
    	String paperid = "";
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			retrieveSql = "select paperid from paper where title = ? and abstract =? and pdf =?";
			preparedStmt = conn.prepareStatement(retrieveSql);
			preparedStmt.setString(1, fieldInput[0]);
			preparedStmt.setString(2, fieldInput[1]);
			preparedStmt.setString(3, fieldInput[2]);
			resultSet = preparedStmt.executeQuery();
			if(resultSet.next()){
				paperid = resultSet.getString(1);
			}
			
    	}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(resultSet!=null){resultSet.close();}}catch(SQLException se){
				//do nothing
			}try{
    			if(preparedStmt!=null){preparedStmt.close();}}catch(SQLException se){
    			//do nothing
    		}
    		try{if(conn!=null){conn.close();
    			}
    		}catch(SQLException se){
    				se.printStackTrace();
    		}//end 
		}
    	System.out.println("Paperid inside is: " +paperid);
		return paperid;
	}
	public void insertAuthorAndWritten(String [] fieldInput){
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet resultSet = null;
    	String query;
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			
			int numberOfAuthors = (fieldInput.length -  3)/ 5; // subtract out the fields pertaining to paper. Left with 5. 
			//each author takes up 5 fields. So divide by 5 and you get the number of authors
			int authorFieldBegin;
			for(int i = 0; i < numberOfAuthors; i++){
				authorFieldBegin = (5*(i))+3; //first 3 are the fields belong to paper. 3 is the off set. 5 belongs to author. 
				query = "insert into author(email,firstName,lastName,affiliation) values(?,?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[authorFieldBegin]);
				preparedStmt.setString(2, fieldInput[authorFieldBegin+1]);
				preparedStmt.setString(3, fieldInput[authorFieldBegin+2]);
				preparedStmt.setString(4, fieldInput[authorFieldBegin+3]);
				preparedStmt.execute();
				preparedStmt.close();
				
				//insert written by
				query = "insert into written(paperid, email, orderOf) values(?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, findPaperId(fieldInput));
				preparedStmt.setString(2, fieldInput[authorFieldBegin]);
				preparedStmt.setString(3, fieldInput[authorFieldBegin+4]);
				preparedStmt.execute();
				preparedStmt.close();
				
				
			}
			
			
    	}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(resultSet!=null){resultSet.close();}}catch(SQLException se){
				//do nothing
			}try{
    			if(preparedStmt!=null){preparedStmt.close();}}catch(SQLException se){
    			//do nothing
    		}
    		try{if(conn!=null){conn.close();
    			}
    		}catch(SQLException se){
    				se.printStackTrace();
    		}//end 
		}
    	
	}

}

/*package modify;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import createTables.findAll;

//*********** STILL NEED TO IMPLEMENT CONSTRAINTS. Randomly assign pc members to review, cannot be more than 5 papers etc

@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public insert() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	
		String[] fieldInput = (String[]) request.getAttribute("fieldInput");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			if(fieldInput.length == 2){
				//insert pcmember
				String query = "insert into pcmember (email, name)values(?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.execute();
			}else if(fieldInput.length == 3){ 
				//insert paper
				String query = "insert into paper(title,abstract,pdf) values(?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.setString(3, fieldInput[2]);
				preparedStmt.execute();
			}else if(fieldInput.length == 5){ // insert review
				String query = "insert into review (sdate, comment, recommendation, paperid, email) values(?,?,?,?,?)";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, fieldInput[0]);
				preparedStmt.setString(2, fieldInput[1]);
				preparedStmt.setString(3, fieldInput[2]);
				preparedStmt.setString(4, fieldInput[3]);
				preparedStmt.setString(5, fieldInput[4]);
				preparedStmt.execute();
			}
			
			
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
    			if(preparedStmt!=null){
    				preparedStmt.close();
    			}
    		}catch(SQLException se){
    			//do nothing
    		}
    		try{
    			if(conn!=null){
    				conn.close();
    			
    			}
    		}catch(SQLException se){
    				se.printStackTrace();
    		}//end 
		}
		findAll.doPost(request,response);
	}

}

*/
