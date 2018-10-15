package createTables;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paper.Paper;
import paper.PaperService;
import pcmember.Pcmember;
import pcmember.PcmemberService;
import author.Author;
import author.AuthorService;
import review.Review;
import review.ReviewService;
import written.WrittenService;

public class findAll {
	
	
	
	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 HttpSession session = request.getSession();
	        
	        PaperService paperservice = new PaperService();
	        AuthorService authorservice = new AuthorService();
	        PcmemberService pcmemberservice = new PcmemberService();
	        ReviewService reviewservice = new ReviewService();
	        WrittenService writtenservice = new WrittenService();
	        try {
				session.setAttribute("PaperList", paperservice.findall());
				session.setAttribute("AuthorList", authorservice.findall());
				session.setAttribute("PcmemberList", pcmemberservice.findall());
				session.setAttribute("ReviewList", reviewservice.findall());
				session.setAttribute("WrittenList", writtenservice.findall());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	        try {
				List<Object> li = paperservice.findall();
				for(int i = 0; i < li.size();i++){
					System.out.println(li.get(i).toString());
					
				}
				List<Object> gi = authorservice.findall();
				for(int i = 0; i < gi.size();i++){
					System.out.println(gi.get(i).toString());
				
			}
				List<Object> hi = pcmemberservice.findall();
				for(int i = 0; i < hi.size();i++){
					System.out.println(hi.get(i).toString());
				
			}
				List<Object> bi = reviewservice.findall();
				for(int i = 0; i < bi.size();i++){
					System.out.println(bi.get(i).toString());
				
			}
				List<Object> di = writtenservice.findall();
				for(int i = 0; i < di.size();i++){
					System.out.println(di.get(i).toString());
				
			}
			
			
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.getRequestDispatcher("/Queryresult/list.jsp").forward(request, response);
		
		
		
		
	}
	
	   public static void someMethod() {
	        System.out.print("hell world"); //good!
	       
	        
	        
	        
	        
	    }
		

}
