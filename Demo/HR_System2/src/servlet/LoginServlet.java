package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginService loginService = new LoginService();
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("name");
		
		String psd = request.getParameter("psd");
		//System.out.println(loginName+"--"+psd);
		int flag = loginService.AdminLogin(loginName, psd);
		//System.out.println("flaga:"+flag);
		request.setAttribute("flag", flag);
		if(flag == 1){
			request.setAttribute("name", loginName);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			System.out.println(request.getAttribute("flag"));
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
}
	

}
