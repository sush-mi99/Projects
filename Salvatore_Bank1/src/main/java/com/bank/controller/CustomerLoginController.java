package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.exception.BankException;
import com.bank.model.Customer;
import com.bank.service.BankCrudService;
import com.bank.serviceimpl.BankCrudServiceImpl;

/**
 * Servlet implementation class CustomerLoginController
 */
public class CustomerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankCrudService bankCrudService=new BankCrudServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Customer customer=new Customer();
		customer.setCust_id(request.getParameter("cust_id"));
		customer.setCust_pwd(request.getParameter("cust_pwd"));	
		RequestDispatcher requestDispatcher=null;
		try {
			if(bankCrudService.isValidLoginCredentials(customer)) {
				//success
				HttpSession session=request.getSession();
				session.setAttribute("userId1", customer.getCust_id());
				response.sendRedirect("CustomerMenu.html");
				//requestDispatcher=request.getRequestDispatcher("success");
				//requestDispatcher.forward(request, response);
			}
		} catch (BankException e) {
			//failure
			PrintWriter out=response.getWriter();
			System.out.println(e);
			requestDispatcher=request.getRequestDispatcher("CustomerLogin.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}
//		response.setContentType("text/html");
//		Customer customer=new Customer();
//		customer.setCust_id(request.getParameter("cust_id"));
//		customer.setCust_pwd(request.getParameter("cust_pwd"));
//		
//		
//		
//		RequestDispatcher requestDispatcher=null;
//		try {
//			if(bankCrudService.isValidLoginCredentials(customer)) {
//				//success
//				requestDispatcher=request.getRequestDispatcher("CustomerMenu.html");
//				requestDispatcher.forward(request, response);
//			}
//		} catch (BankException e) {
//			//failure
//			PrintWriter out=response.getWriter();
//			requestDispatcher=request.getRequestDispatcher("CustomerLogin.html");
//			requestDispatcher.include(request, response);
//			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
//			
//		}
//	}
}
}
