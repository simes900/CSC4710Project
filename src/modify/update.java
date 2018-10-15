package modify;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import createTables.findAll;


@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getAttributeName(String inputType, int attributeNum){ //ok working 
    	String variable = "";
    	if(Objects.equals(inputType, new String ("pcmember"))){
    		
    		switch(attributeNum){
    		case 0: variable = "email"; break;
    		case 1: variable = "name"; break;
    		}
    	}else if (Objects.equals(inputType, new String ("review"))){
    		switch(attributeNum){
    		case 0: variable = "sdate"; break;
    		case 1: variable =  "comment"; break;
    		case 2: variable = "recommendation"; break;
    		case 3: variable = "paperid"; break;
    		case 4: variable =  "email"; break;
    		}
    	}else{
    		switch(attributeNum){
    		case 0: variable= "title"; break;
    		case 1: variable = "abstract"; break;
    		case 2: variable = "pdf"; break;
    		}
    	}
    	
    	return variable;
		
		
    }
    
    public String getPrimaryKeyName(String inputType){ 
    	if(Objects.equals(inputType, new String("review"))){
    		return "reportid";
    	}else if (Objects.equals(inputType,  new String("paper"))){
    		return "paperid";
    	}else{
    		return "email";
    	}
    }
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	String query = null;
    	String primaryKey = (String) request.getAttribute("primaryKey");
    	String[] fieldInput = (String[]) request.getAttribute("fieldInput");
    	String inputType = (String) request.getAttribute("inputType");
		int numOfFields = fieldInput.length;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
			for(int i = 0; i < numOfFields; i++){
				if(!(Objects.equals(fieldInput[i], ""))){
					
					query = "update "+inputType+" set "+getAttributeName(inputType,i)
							+ " = ? where "+getPrimaryKeyName(inputType)+" = ?";
					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1,fieldInput[i]);
					preparedStmt.setString(2,primaryKey);
					preparedStmt.executeUpdate();
					
					/*if(Objects.equals(inputType,"pcmember") && i==0){
						query = "if exists (select * from pcmember where email = ?)"+
								"update review set email = ? where email= ?";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, primaryKey);
						preparedStmt.setString(2, fieldInput[i])
					}*/
				}
				
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
