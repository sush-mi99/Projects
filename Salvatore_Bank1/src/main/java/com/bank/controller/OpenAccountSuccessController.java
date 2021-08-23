package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OpenAccountSuccessController
 */
public class OpenAccountSuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenAccountSuccessController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if(session == null)
		{
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/Salvatore_Bank1'>Click here to Login </a> ");
		}else {
			out.print("<center><h1>Welcome "+session.getAttribute("userId4")+" ..... You have opened account successfully at "+new Date()+"</h1></center>");
			out.print("<center><h2>Your Account Number is:"+session.getAttribute("cust_accno4")+"</h2></center>");
			out.print("<p><a href='/Salvatore_Bank1/CustomerMenu.html'>Click Here to go to Menu</a></p>");
		}
		
	}

//		PrintWriter out=response.getWriter();
//		out.print("<h1>Welcome "+request.getAttribute("userId4")+" ..... You have opened account successfully at "+new Date()+"</h1>");
//		out.print("<h1>your account number  "+request.getAttribute("cust_accno4"));
//		out.print("<a href='/Salvatore_Bank1/'>Click Here to LOGIN</a>");
//	}

}

