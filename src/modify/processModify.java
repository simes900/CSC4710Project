package modify;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class processModify
 */
@WebServlet("/processmodify")
public class processModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
				String[] inputType = request.getParameterValues("inputtype");
				
				String[] fieldinput = request.getParameterValues("fieldinput");
				
				String[] primaryKey = request.getParameterValues("primarykey");
			
				
					System.out.println("inputType[0]: " + inputType[0]);
					System.out.println("inputType[1]: " + inputType[1]);
					
				if(primaryKey == null){
					System.out.println("no primary key");
				}
				else{
					System.out.println("primaryKey[0]: " + primaryKey[0]);
				}
					
				
				if(fieldinput == null){
					System.out.println("no field inputs");
				}
				
				else{
					
					
					for(int i = 0; i < fieldinput.length; i++){
						System.out.println("fieldinput[" + i + "]: " + fieldinput[i]);
						}
					
				}
				
				System.out.println();
				
				//forward variables to either delete.java, update.java, or insert.java depending on the input type selected. 
				
				
				if(Objects.equals(inputType[0], new String("delete"))){
					//forward the primary key and table to delete from
					String [] deleteKey = {primaryKey[0], inputType[1]};
					request.setAttribute("deleteKey", deleteKey);
					request.getRequestDispatcher("delete").forward(request, response);
				}else if (Objects.equals(inputType[0], new String("insert"))){
					request.setAttribute("fieldInput", fieldinput);
					request.getRequestDispatcher("insert").forward(request, response);
				}else if (Objects.equals(inputType[0], new String("update"))){
					request.setAttribute("fieldInput", fieldinput);
					request.setAttribute("primaryKey", primaryKey[0]);
					request.setAttribute("inputType", inputType[1]);
					request.getRequestDispatcher("update").forward(request, response);
				}
				
					
				//}
				
	}

}
