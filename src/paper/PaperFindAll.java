package paper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import paper.Paper;

public class PaperFindAll {
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection connect = null;
		ResultSet resultSet = null;
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/sampledb?"
				              + "user=john&password=pass1234");
			
			String from = " from";
			String pape = " paper";
			String sql = "select *";
			sql = sql + from + pape;
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			resultSet = preparestatement.executeQuery();
			
			while(resultSet.next()){
				Paper paper = new Paper();
				paper.setPaperId(Integer.valueOf(resultSet.getString("paperid")));
				paper.setTitle(resultSet.getString("title"));
				paper.setAbstractText(resultSet.getString("abstract"));
				paper.setPdf(resultSet.getString("pdf"));
	    		list.add(paper);
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
					System.out.println("Closed PaperFindAll servlet connection");
				}catch(SQLException e){
					//ignore
				}
			}
		}
		return list;
		}

}
