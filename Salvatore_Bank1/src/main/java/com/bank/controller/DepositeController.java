package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.bank.serviceimpl.BankSearchServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class DepositeController
 */
public class DepositeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankCrudService bankCrudService = new BankCrudServiceImpl();
	private BankSearchService bankSearchService = new BankSearchServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
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
			//out.print("<h1>Welcome "+session.getAttribute("userId1")+" ..... You have logged in successfully at "+new Date(session.getCreationTime())+"</h1>");
			Gson gson=new Gson();
			Transaction transaction =gson.fromJson(request.getReader(), Transaction.class);
			System.out.println("before deposit");
			System.out.println(transaction);
			long accountNumber=transaction.getCust_accno();		
			 try {
				 double amount=transaction.getAmount();
				 Account account = bankSearchService.getBalanceByAccountNumber(accountNumber);
				 double openingBalance = account.getOpen_bal();
				 System.out.println(openingBalance);
				 Transaction transaction1=new Transaction(accountNumber, amount, openingBalance,openingBalance+amount , "Deposit");
					transaction = bankCrudService.depositAmount(transaction1);
					session.setAttribute("accountNumbert", transaction1.getCust_accno());
					session.setAttribute("closingBalance", transaction1.getClose_bal());
					System.out.println("After Deposit");
					System.out.println(transaction1);
				} catch (BankException e) {
					out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
				}
			out.print("<a href='mainmenu.html'>click here to go to main menu</a> ");
				response.setContentType("application/json;charset=UTF-8");
		}
	}
}
