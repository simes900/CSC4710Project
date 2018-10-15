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
 * Servlet implementation class searchNumberNine
 */
@WebServlet("/searchNumberNine")
public class searchNumberNine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchNumberNine() {
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
		
		String [] name = (String[]) request.getAttribute("names");
		String rejectedOrAccepted = (String) request.getAttribute("rejectedOrAccepted");
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
    		if(name.length == 1){ //papers accepted or rejected by one person
    			
    			String retrieveSql = "SELECT R.paperid, title FROM review R, paper A, pcmember P WHERE R.recommendation= ? and P.name = ? and R.paperid=A.paperid and R.email = P.email";
    			
    			results.add("Papers "+recWord(rejectedOrAccepted)+"  by "+name[0]+"");
				preparedStmt = conn.prepareStatement(retrieveSql);
				preparedStmt.setString(1, recLetter(rejectedOrAccepted));
				preparedStmt.setString(2, name[0]);
    		}else{
    			String retrieveSql = "select T1.paperid, T1.title "+
    					"From(Select A.paperid, title, P.name, R.recommendation "+
						"FROM review R, paper A, pcmember P "+
						"WHERE R.recommendation = ? and R.paperid =A.paperid And R.email = P.email) as T1 "+
						"INNER JOIN "+
						"(Select A.paperid, title, P.name, R.recommendation "+
						"FROM review R, paper A, pcmember P "+
						"WHERE R.recommendation = ? and R.paperid =A.paperid And R.email = P.email) as T2 "+
						"WHERE T1.name = ? AND T2.name = ? and T1.paperid = T2.paperid";
    			
    			results.add("Papers "+recWord(rejectedOrAccepted)+"  by "+name[0]+" and "+name[1]);
				preparedStmt = conn.prepareStatement(retrieveSql);
				preparedStmt.setString(1,recLetter(rejectedOrAccepted));
				preparedStmt.setString(2, recLetter(rejectedOrAccepted));
				preparedStmt.setString(3, name[0]);
				preparedStmt.setString(4, name[1]);
    		}
    		resultSet = preparedStmt.executeQuery();
			results.add("Paper ID");
			results.add("Title");
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
	public String recLetter(String rejectedOrAccepted){
		if(rejectedOrAccepted.equals("rejectedby")){
			return "R";
		}else{
			return "A";
		}
	}
	public String recWord(String rejectedOrAccepted){
		if(rejectedOrAccepted.equals("rejectedby")){
			return "rejected";
		}else{
			return "accepted";
		}
	}

}
