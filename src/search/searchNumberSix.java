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
 * Servlet implementation class searchNumberSix
 */
@WebServlet("/searchNumberSix")
public class searchNumberSix extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchNumberSix() {
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
		
		String [] names = (String[]) request.getAttribute("names");
		String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	PreparedStatement preparedStmt = null;
    	ResultSet resultSet = null;
    	ArrayList<String> results = new ArrayList<String>();
    	String columnName1 = "paperid";
		String columnName2 = "title";
		

    	try{
    		Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(names.length == 1){
				
				String retrieveSql = "Select W.paperid, title "
									+ "From author A, written W, paper P  "+
									" where W.paperid = P.paperid and W.email=A.email and A.lastName = ?";
				results.add("Papers where last name,"+names[0]+", is an author");
				preparedStmt = conn.prepareStatement(retrieveSql);
				preparedStmt.setString(1, names[0]);
				resultSet = preparedStmt.executeQuery();
				results.add("Paper ID");
				results.add("Title");
				while(resultSet.next()){
					results.add(resultSet.getString(columnName1));
					results.add(resultSet.getString(columnName2));
				}
				
			}else if(names.length == 2){
				
				String retrieveSql = "select T1.paperid, T1.title "+
						"FROM(select P.paperid, P.title, A.lastName "+
								"From author A, written W, paper P "+
								"where W.paperid=P.paperid and W.email=A.email) as T1 "+
								"INNER JOIN "+
								"(select P.paperid, P.title, A.lastName "+
								"From author A, written W, paper P "+
								"where W.paperid=P.paperid and W.email=A.email) as T2 "+
								"WHERE T1.paperid = T2.paperid and T1.lastName <> T2.lastName AND T1.lastName = ? and T2.lastName =?";
				results.add("Papers where "+names[0]+" and "+names[1]+" are co authors");
				preparedStmt = conn.prepareStatement(retrieveSql);
				preparedStmt.setString(1, names[0]);
				preparedStmt.setString(2, names[1]);
				resultSet = preparedStmt.executeQuery();
				results.add("Paper ID");
				results.add("Title");
				while(resultSet.next()){
					results.add(resultSet.getString(columnName1));
					results.add(resultSet.getString(columnName2));
				}
			}else{
				results.add("Cannot handle more than 2 authors at this moment");
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
