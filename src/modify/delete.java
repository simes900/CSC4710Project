package modify;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import createTables.findAll;

import java.sql.PreparedStatement;

@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Variables stored to connect to database. 
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
   
    	
		//retrieve variables from processModify servlet. deleteKey[0] is the primary key, delete[1] is the table
		String[] deleteKey = (String[]) request.getAttribute("deleteKey");
		String primaryKey = deleteKey[0];
		String table = deleteKey[1];
		
		try{
			//open connection to database 
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    
			
			//delete from table 
			if(Objects.equals(table, new String("pcmember"))){
				//delete review existing tuple that contains the email of the a pcmember
				String query = "delete from review where email= '"+ primaryKey+"'";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				preparedStmt.close();
				
				//delete the associated pcmember from pcmember
				query = "delete from pcmember where email='"+ primaryKey+"'";
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				preparedStmt.close();
				System.out.print("Delete sdjhjksdhf because of pcmember");
				
			}else if(Objects.equals(table, new String("paper"))){
				// delete review existing tuple that contains the paperid of a paper
				String query = "delete from review where paperid="+ primaryKey;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				System.out.print("Delete review because of paper");
				preparedStmt.close();
				
				//delete from written
				query = "delete from written where paperid="+ primaryKey;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				preparedStmt.close();
				
				//delete paper the associated tuple
				query = "delete from paper where paperid="+ primaryKey;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				System.out.print("Delete sdjhjksdhf because of paper");
				
			}else if(Objects.equals(table, new String("review"))){
				//delete review tuple 
				String query = "delete from review where reportid="+ primaryKey;
				preparedStmt = conn.prepareStatement(query);
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
		
		findAll.doPost(request, response);
		
	}

}
