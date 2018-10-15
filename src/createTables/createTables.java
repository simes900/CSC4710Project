package createTables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import createTables.findAll;

/**
 * Servlet implementation class createTables
 */
@WebServlet("/createtables")
public class createTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createTables() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public String recGenerator(){ //recommendation generator
		String recommendation;
		
		if(Math.random() < 0.5){
			recommendation ="A";
		}else{
			recommendation ="R";
		}
		return recommendation;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Tables creating..");
    
    	String DB_URL="jdbc:mysql://localhost:3306/sampledb?";
    	String NO_DB_URL = "jdbc:mysql://localhost:3306";
    	
    	String USER = "john";
    	String PASS = "pass1234";
    	Connection conn = null;
    	Connection conn_nodb = null;
    	Statement stmt = null;
    	
    	try{
    		//Step1: Register JDBC driver
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		//Step 2: Open connection for user and create database if it doesn't exist
    		conn_nodb = DriverManager.getConnection(NO_DB_URL, USER, PASS);
    		System.out.println("Creating database if not exists");
    		stmt = conn_nodb.createStatement();
    		stmt.executeUpdate("DROP DATABASE IF EXISTS sampledb");
    		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS sampledb");
    		
    		
    		
    		//Step 2: Open connection to the database
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		System.out.println("Connected to database.");
    		
    		//Step 3: Execute a query to create tables and tuples
    		System.out.println("Creating table in given database");
    		stmt = conn.createStatement();
    		
    		String sqlpaper = "CREATE TABLE IF NOT EXISTS paper"+
    						"(paperid integer AUTO_INCREMENT, " +
    						"title VARCHAR(50), "+
    						"abstract VARCHAR(250), "+
    						"pdf VARCHAR(100), "+
    						"primary key (paperid));";
    		String sqlauthor = "CREATE TABLE IF NOT EXISTS author"+
    						"(email VARCHAR(100), "+
    						"lastName VARCHAR(50), "+
    						"firstName VARCHAR(50), "+
    						"affiliation VARCHAR(100), "+
    						"primary key (email));";
    		
    		String sqlpcmember = "CREATE TABLE IF NOT EXISTS pcmember"+
    							"(email VARCHAR(50), "+
    							"name VARCHAR(50), "+
    							"primary key (email));";
    		String sqlreview = "CREATE TABLE IF NOT EXISTS review"+
    							"(reportid integer AUTO_INCREMENT, "+
    							"sdate VARCHAR(250), "+
    							"comment VARCHAR(250), "+
    							"recommendation CHAR(1), "+
    							"paperid integer, "+
    							"email VARCHAR(100), "+
    							"unique(paperid, email), "+
    							"primary key (reportid), "+
    							"CONSTRAINT FK_reviewpaperid foreign key (paperid) references paper(paperid), "+
    							"CONSTRAINT FK_reviewemail foreign key (email) references pcmember(email) ON UPDATE CASCADE);";
    		
    		String sqlwritten = "CREATE TABLE IF NOT EXISTS written"+
					"(paperid integer, "+
					"email VARCHAR(100), "+
					"orderOf integer, "+
					"primary key (paperid,email), "+
					"CONSTRAINT FK_writepaperid foreign key (paperid) references paper (paperid) ON DELETE CASCADE, "+
					"CONSTRAINT FK_writeemail foreign key (email) references author (email));";
    		
    		/*String sqlwritten = "CREATE TABLE IF NOT EXISTS written"+
    							"(paperid integer, "+
    							"email VARCHAR(100), "+
    							"orderOf integer, "+
    							"primary key (paperid,email), "+
    							"CONSTRAINT FK_writepaperid foreign key (paperid) references paper (paperid), "+
    							"CONSTRAINT FK_writeemail foreign key (email) references author (email));";*/
    		
    		stmt.executeUpdate(sqlpaper);
    		System.out.println("paper table created");
    		stmt.executeUpdate(sqlauthor);
    		System.out.println("author table created");
    		stmt.executeUpdate(sqlpcmember);
    		System.out.println("pcmember table made");
    		stmt.executeUpdate(sqlreview);
    		System.out.println("review table made");
    		stmt.executeUpdate(sqlwritten);
    		System.out.println("written table made");
    		// tuples creating
    		//-----------------------------------------------------------------------------------------
    		//add tuple for pcmember
    		//INCORRECT VARIABLE NAMES suppose to be pcmember
    		String sqltupleauthor1 = "INSERT INTO pcmember(name,email) VALUES('Matt', 'matt@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor1);
    		String sqltupleauthor2 = "INSERT INTO pcmember(name,email) VALUES('Simon', 'simon@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor2);
    		String sqltupleauthor3 = "INSERT INTO pcmember(name,email) VALUES('Scott', 'scott@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor3);
    		String sqltupleauthor4 = "INSERT INTO pcmember(name,email) VALUES('Kevin', 'kevin@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor4);
    		String sqltupleauthor5 = "INSERT INTO pcmember(name,email) VALUES('Gunnett', 'gunnett@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor5);
    		String sqltupleauthor6 = "INSERT INTO pcmember(name,email) VALUES('John', 'john@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor6);
    		String sqltupleauthor7 = "INSERT INTO pcmember(name,email) VALUES('Dim', 'dim@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor7);
    		String sqltupleauthor8 = "INSERT INTO pcmember(name,email) VALUES('Goku', 'goku@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor8);
    		String sqltupleauthor9 = "INSERT INTO pcmember(name,email) VALUES('Seymour', 'seymour@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor9);
    		String sqltupleauthor10 = "INSERT INTO pcmember(name,email) VALUES('Wang', 'wang@zmail.com');";
    		stmt.executeUpdate(sqltupleauthor10);
    		System.out.println("Added author tuples");
    		
    		//----------------------------------------------------------------------------------------------------------
    		//paper tuples----------------------------------------------------------------------------------
    		//HORRIBLE WAY DON"T DO THIS // INCORRECT VARIABLE NAME, should've been sqltuplepaper
    		String sqltuplepcmember1 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('shish', 'dffsfd', 'brain.pdf');";
    		stmt.executeUpdate(sqltuplepcmember1);
    		String sqltuplepcmember2 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Body', 'JIBBERESH IS MY NAME', 'body.pdf');";
    		stmt.executeUpdate(sqltuplepcmember2);
    		String sqltuplepcmember3 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Candy Land', 'We are exploring deep stuff about candy', 'candy.pdf');";
    		stmt.executeUpdate(sqltuplepcmember3);
    		String sqltuplepcmember4 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Flat Earth', 'I believe the earth is fla', 'flat.pdf');";
    		stmt.executeUpdate(sqltuplepcmember4);
    		String sqltuplepcmember5 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Fire Danger', 'I have battle scars', 'fire.pdf');";
    		stmt.executeUpdate(sqltuplepcmember5);
    		String sqltuplepcmember6 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Electricity is electric', 'Electric Electric Electric', 'electric.pd');";
    		stmt.executeUpdate(sqltuplepcmember6);
    		String sqltuplepcmember7 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('not hot', 'Humans should wear winter coats during the summer', 'mansnothot.pdf');";
    		stmt.executeUpdate(sqltuplepcmember7);
    		String sqltuplepcmember8 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Too much CO2', 'Earth rise in temperature is caused by farts', 'greenhouse.pdf');";
    		stmt.executeUpdate(sqltuplepcmember8);
    		String sqltuplepcmember9 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Small children can learn faster', 'Kids learn very fast', 'kids.pdf');";
    		stmt.executeUpdate(sqltuplepcmember9);
    		String sqltuplepcmember10 = "INSERT INTO paper(title, abstract, pdf)" + 
    				"VALUES ('Moon is dark', 'The moon is dark', 'moon.pdf');";
    		stmt.executeUpdate(sqltuplepcmember10);
    		System.out.println("Added paper tuples");
    		
    		//add tuples for author
    		String sqltupleauthors1 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('lu@zmail.com', 'Lu', 'George', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors1);
    		String sqltupleauthors2 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('Zhang@zmail.com', 'Zhang', 'Hue', 'Google Corp');";
    		stmt.executeUpdate(sqltupleauthors2);
    		String sqltupleauthors3 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('mitch@zmail.com', 'Jones', 'Mitch', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors3);
    		String sqltupleauthors4 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('jesus@zmail.com', 'Jesus', 'Sam', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors4);
    		String sqltupleauthors5 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('georgey@zmail.com', 'Wang', 'Sum', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors5);
    		String sqltupleauthors6 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('dat@zmail.com', 'Dat', 'Walter', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors6);
    		String sqltupleauthors7 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('ju@zmail.com', 'Ju', 'Mem', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors7);
    		String sqltupleauthors8 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('cya@zmail.com', 'Cya', 'Sensu', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors8);
    		String sqltupleauthors9 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('Moo@zmail.com', 'Moo', 'Minks', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors9);
    		String sqltupleauthors10 = "INSERT INTO author(email, lastName, firstName, affiliation) VALUES ('kevin@zmail.com', 'Kevin', 'Garnett', 'Comcast Corp');";
    		stmt.executeUpdate(sqltupleauthors10);
    		//---------------------------------------------------------------------------------------------------------
    		//tuples for review
    		// foreign keys are paperid which refers to a paper from paper table
    		//foreign key is also email which references to a pcmember from pcmember table
    		String sqltuplereview = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '1', 'john@zmail.com');";
    		stmt.executeUpdate(sqltuplereview);
    		String sqltuplereview2 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '1', 'matt@zmail.com');";
    		stmt.executeUpdate(sqltuplereview2);
    		String sqltuplereview3 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '1', 'seymour@zmail.com');";
    		stmt.executeUpdate(sqltuplereview3);
    		String sqltuplereview4 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '2', 'john@zmail.com');";
    		stmt.executeUpdate(sqltuplereview4);
    		String sqltuplereview5 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '2', 'matt@zmail.com');";
    		stmt.executeUpdate(sqltuplereview5);
    		String sqltuplereview6 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '2', 'seymour@zmail.com');";
    		stmt.executeUpdate(sqltuplereview6);
    		String sqltuplereview7 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder','"+ recGenerator() + "', '3', 'john@zmail.com');";
    		stmt.executeUpdate(sqltuplereview7);
    		String sqltuplereview8 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder', '"+ recGenerator() + "', '3', 'matt@zmail.com');";
    		stmt.executeUpdate(sqltuplereview8);
    		String sqltuplereview9 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder', '"+ recGenerator() + "', '3', 'wang@zmail.com');";
    		stmt.executeUpdate(sqltuplereview9);
    		String sqltuplereview10 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder', '"+ recGenerator() + "', '4', 'john@zmail.com');";
    		stmt.executeUpdate(sqltuplereview10);
    		String sqltuplereview11 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder', '"+ recGenerator() + "', '4', 'matt@zmail.com');";
    		stmt.executeUpdate(sqltuplereview11);
    		String sqltuplereview12 = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES('2/2/17', 'comment place holder', '"+ recGenerator() + "', '4', 'scott@zmail.com');";
    		stmt.executeUpdate(sqltuplereview12);
    		
    		//-----------------------------------------------------------------
    		//tuples for written
    		//Foreign key is paperid which refers to a paper from paper table
    		//FOREIGN KEY is also email which refers to an email of an author from author table
    		String sqltuplewritten = "INSERT INTO written(paperid, email, orderOf) VALUES('1', 'lu@zmail.com', '2');";
    		stmt.executeUpdate(sqltuplewritten);
    		String sqltuplewritten2 = "INSERT INTO written(paperid, email, orderOf) VALUES('1', 'Zhang@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten2);
    		String sqltuplewritten3 = "INSERT INTO written(paperid, email, orderOf) VALUES('3', 'kevin@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten3);
    		String sqltuplewritten4 = "INSERT INTO written(paperid, email, orderOf) VALUES('4', 'Moo@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten4);
    		String sqltuplewritten5 = "INSERT INTO written(paperid, email, orderOf) VALUES('5', 'georgey@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten5);
    		String sqltuplewritten6 = "INSERT INTO written(paperid, email, orderOf) VALUES('6', 'dat@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten6);
    		String sqltuplewritten7 = "INSERT INTO written(paperid, email, orderOf) VALUES('7', 'ju@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten7);
    		String sqltuplewritten8 = "INSERT INTO written(paperid, email, orderOf) VALUES('9', 'lu@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten8);
    		String sqltuplewritten9 = "INSERT INTO written(paperid, email, orderOf) VALUES('9', 'Zhang@zmail.com', '2');";
    		stmt.executeUpdate(sqltuplewritten9);
    		String sqltuplewritten10 = "INSERT INTO written(paperid, email, orderOf) VALUES('10', 'lu@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten10);
    		String sqltuplewritten11 = "INSERT INTO written(paperid, email, orderOf) VALUES('2', 'lu@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten11);
    		String sqltuplewritten12 = "INSERT INTO written(paperid, email, orderOf) VALUES('8', 'Zhang@zmail.com', '1');";
    		stmt.executeUpdate(sqltuplewritten12);
    		
    		
    		
    		
    		//=------------------------------------------------------------------------------------------
    	}catch(SQLException se){
    		//handle errors for JDBC
    		se.printStackTrace();
    	}catch(Exception e){
    		//handle errors for Class.forname
    		e.printStackTrace();
    	}finally{
    		//finally block used to close resources
    		try{
    			if(stmt!=null){
    				stmt.close();
    				System.out.println("Closed createTable statement connection");
    			}
    		}catch(SQLException se){
    			//do nothing
    		}
    		try{
    			if(conn!=null){
    				conn.close();
    				conn_nodb.close();
    				System.out.println("Closed createTable connection connection");
    			}
    				
    			
    		}catch(SQLException se){
    				se.printStackTrace();
    			}//end 
    		}
    	//response.sendRedirect("findAll");
    	findAll.doPost(request, response);
	}

	

}
