package search;

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
 * Servlet implementation class Search
 */
@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		
		
		String[] pcMemberSearchQuery = request.getParameterValues("pcmembername");
		String[] pcMembersAssigned = request.getParameterValues("pcmembersassigned");
		
		
		String[] paperOrPcMember = request.getParameterValues("paperOrPcMember"); 
		String[] whereOrRejected = request.getParameterValues("whereOrRejectedBy"); //condition WHERE,REJECTED,ACCEPTED
		String[] names = request.getParameterValues("names"); //names
		String[] orAnd = request.getParameterValues("orAnd"); //ignore 
		String[] numberOfAuthors = request.getParameterValues("numberOfAuthors");  //
		String[] authorRank = request.getParameterValues("authorRank");
		
		//System.out.println(pcMembersAssigned[0]);
		

		if(paperOrPcMember != null){
			for(int i = 0; i < paperOrPcMember.length;i++){
				System.out.println("paper or pc member: " + paperOrPcMember[i]);
			}
		}
		
		if(whereOrRejected != null){
		for(int i = 0; i < whereOrRejected.length;i++){
				System.out.println("where or rejected by: " + whereOrRejected[i]);
			}
		}
		
		if(names != null){
			for(int i = 0; i < names.length;i++){
				System.out.println("Names: " + names[i]);
			}
		}
		
		if(orAnd != null){
			for(int i = 0; i < orAnd.length;i++){
				System.out.println("andOrBoth: " + orAnd[i]);
			}
			
		}
		
		// numberOfAuthors is null if field is disabled upon submit. Field is disabled when text input is gray instead of white
		if(numberOfAuthors != null){
			for(int i = 0; i < numberOfAuthors.length;i++){
				System.out.println("Number of Authors: " + numberOfAuthors[i]);
			}
		}
		
		// authorRank is null if field is disabled upon submit. Field is disabled when text input is gray instead of white
		if(authorRank != null){
			for(int i = 0; i < authorRank.length;i++){
				System.out.println("Author Rank: " + authorRank[i]);
			}
		}
		
		
			
			
		
		
	
		
		// The conditions below handle the buttons for the predetermined searches. These already work
		// The last two condition still need to be handled. 
		if(pcMemberSearchQuery != null){
		if(pcMemberSearchQuery[0].equals("allAcceptedPapers")){
			System.out.println("the redirect has been reached");
			 
			request.setAttribute("allAccepted", pcMemberSearchQuery[0]);
			request.getRequestDispatcher("searchpredefined").forward(request, response);
		}
		else if(pcMemberSearchQuery[0].equals("pcMemberReviewedMostPapers")){
			request.setAttribute("allAccepted", pcMemberSearchQuery[0]);
			request.getRequestDispatcher("searchpredefined").forward(request, response);
			
		}
		else if(pcMemberSearchQuery[0].equals("pcMemberNoAssignedPapers")){
			request.setAttribute("allAccepted", pcMemberSearchQuery[0]);
			request.getRequestDispatcher("searchpredefined").forward(request, response);
		}
		else if(pcMemberSearchQuery[0].equals("pcMemberNumberAssignedPapers")){
			String temp = "";
			temp = pcMembersAssigned[0] + pcMemberSearchQuery[0];
			request.setAttribute("allAccepted", temp);
			request.getRequestDispatcher("searchpredefined").forward(request, response);
		}
		}
		
		else{
			
			
			
		//if condition if you are searching for a paper by author name
		if(paperOrPcMember[0].equals("paper")){
			if(whereOrRejected[0].equals("where")){
			System.out.println("Type is paper!");
				 if (authorRank!= null && numberOfAuthors !=null ){
					 cannotPerform(request,response);
				 }else if( authorRank== null && numberOfAuthors ==null){
					 System.out.println("Inside the null space");
					 request.setAttribute("names", names);
					request.getRequestDispatcher("searchNumberSix").forward(request, response);
				 }
				 else if (numberOfAuthors == null){
					// 5.	List all the papers in which “Lu” is the first author.
					 if(names.length == 1){
						 request.setAttribute("name", names[0]);
							request.setAttribute("authorRank", authorRank[0]);
							request.getRequestDispatcher("searchNumberFive").forward(request, response);
					 }else{
						 //invalid choice
						 cannotPerform(request,response);
					 }
					
				 }else if(authorRank == null){
					//4.	List all the papers by “Lu” (last name) as the single author.
					if(numberOfAuthors[0].equals("1")){
						request.setAttribute("name", names[0]);
						request.setAttribute("numberOfAuthors", numberOfAuthors[0]);
						request.getRequestDispatcher("searchNumberFour").forward(request, response);
					}else{
						//6.	Find all the papers coauthored by “Zhang” and “Lu”.
						cannotPerform(request,response);
					}
				}
				
			}else if(whereOrRejected[0].equals("rejectedby") || whereOrRejected[0].equals("acceptedby")){
				//9.	List all papers rejected by both PC members “Matt” and “John”.
				   if(names.length < 3 && 0 < names.length){
					   request.setAttribute("names", names);
					   request.setAttribute("rejectedOrAccepted", whereOrRejected[0]);
					   request.getRequestDispatcher("searchNumberNine").forward(request, response);
				   }else {
					   cannotPerform(request,response);
				   }
			}
		}
		//else if condition used if you are searching for a paper by pc member name
		else if(paperOrPcMember[0].equals("pcmember")){
			System.out.println("Type is pcmember!");
			//unreachable 
		}
		}
		
		
	}
	
	public void cannotPerform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 ArrayList<String> results = new ArrayList<String>();
		 results.add("Invalid or function not implemented");
		 request.setAttribute("message", results);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/Queryresult/searchResults.jsp");
		 dispatcher.forward(request, response);
	}
	
	

}
