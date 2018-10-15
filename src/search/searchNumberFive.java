package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchNumberFive
 */
@WebServlet("/searchNumberFive")
public class searchNumberFive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchNumberFive() {
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
		//5.	List all the papers in which “Lu” is the first author.

		String name = (String) request.getAttribute("name");
		String authorRank = (String) request.getAttribute("authorRank");
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet resultSet = null;
    	ArrayList<String> results = new ArrayList<String>();
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			String retrieveSql = "SELECT P.paperid, P.title FROM  author A, written W, paper P WHERE W.paperid = P.paperid and W.email=A.email and A.lastName = ? and W.orderOf = ?";
			
			results.add("Papers where "+name+" is the #"+authorRank+" author");
			results.add("Paper ID");
			results.add("Title");
			preparedStmt = conn.prepareStatement(retrieveSql);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, authorRank);
			resultSet = preparedStmt.executeQuery();
			String columnName1 = "paperid";
			String columnName2 = "title";
			
			while(resultSet.next()){
				results.add(resultSet.getString(columnName1));
				results.add(resultSet.getString(columnName2));
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
    	request.setAttribute("message", results);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Queryresult/searchResults.jsp");
		dispatcher.forward(request, response);
	}

}
