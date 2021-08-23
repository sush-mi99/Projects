package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WithdrawSuccessController
 */
public class WithdrawSuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawSuccessController() {
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
			
			out.print("<center><h2>Your Account Number is:"+session.getAttribute("accountNumberw")+"</h2></center>");
			out.print("<center><h2>Your Updated Balance is:"+session.getAttribute("closingBalancew")+"</h2></center>");
			out.print("<p><a href='/Salvatore_Bank1/CustomerMenu.html'>Click Here to go to Menu</a></p>");
		}
	}
}
