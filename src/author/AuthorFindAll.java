package author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import author.Author;

public class AuthorFindAll {
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection connect = null;
		ResultSet resultSet = null;
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/sampledb?"
				              + "user=john&password=pass1234");
			
			
			String sql = "select * from author";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			resultSet = preparestatement.executeQuery(); 
			//executes query to retrieve table and defines an object that will hold the database results
			
			while(resultSet.next()){
				 Author author = new Author();
				 author.setEmail(resultSet.getString("email"));
				 author.setLastName(resultSet.getString("lastName"));
				 author.setFirstName(resultSet.getString("firstName"));
				 author.setAffiliation(resultSet.getString("affiliation"));
				
	    		list.add(author);
			 }
			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}finally{
			if(resultSet !=null){
				try{
					connect.close();
					System.out.println("Closed AuthorfindAll servlet connection");
				}catch(SQLException e){
					//ignore
				}
			}
			if(connect != null){
				try{
					connect.close();
					System.out.println("Closed AuthorfindAll servlet connection");
				}catch(SQLException e){
					//ignore
				}
			}
		}
		return list;
	

		
	}

}
