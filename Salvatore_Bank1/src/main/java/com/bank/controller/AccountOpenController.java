package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.service.BankCrudService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class AccountOpenController
 */
public class AccountOpenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AccountOpenController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private BankCrudService bankCrudService=new BankCrudServiceImpl();
 
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		PrintWriter out=response.getWriter();
		RequestDispatcher requestDispatcher=null;
		response.setContentType("text/html");
		if(session == null)
		{
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/Salvatore_Bank1'>Click here to Login </a> ");
		}else {
			//out.print("<h1>Welcome "+session.getAttribute("userId")+" ..... You have logged in successfully at "+new Date(session.getCreationTime())+"</h1>");
			Gson gson = new Gson();
			Account account = gson.fromJson(request.getReader(), Account.class);
			session.setAttribute("userId4", account.getUserId1());
			session.setAttribute("open_bal", account.getOpen_bal());
			try {
				account = bankCrudService.createAccount(account);
				System.out.println(account);
				session.setAttribute("cust_accno4", account.getCust_accno());
				response.sendRedirect("open");
			} catch (BankException e) {
				requestDispatcher=request.getRequestDispatcher("OpenAccount.html");
				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			}
			
			//response.sendRedirect("success");
		//out.print("<a href='account.html'>click here to create a new account</a> ");
		//response.sendRedirect("account.html");
	}
    }
//    	System.out.println("HIII");
//		HttpSession session = request.getSession(false);
//		System.out.println("Hiii1");
//		PrintWriter out=response.getWriter();
//		RequestDispatcher requestDispatcher=null;
//		response.setContentType("text/html");
//			System.out.println("Hii2");
//			Gson gson = new Gson();
//			Account account = gson.fromJson(request.getReader(), Account.class);
//			System.out.println(account);
//			session.setAttribute("userId2", account.getUserId1());
//			session.setAttribute("open_bal", account.getOpen_bal());
//			try {
//				account = bankCrudService.createAccount(account);
//				System.out.println(account);
//				session.setAttribute("cust_accno", account.getCust_accno());
//				response.sendRedirect("CustomerMenu.html");
//				
//			} catch (BankException e) {
//				requestDispatcher=request.getRequestDispatcher("OpenAccount.html");
//				requestDispatcher.include(request, response);
//				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
//			}
//			
//	}

}

