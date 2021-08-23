package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.exception.BankException;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankSearchServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class StatementController
 */
public class StatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BankSearchService bankSearchService = new BankSearchServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson=new Gson();
		Long q = Long.parseLong(request.getParameter("cust_accno"));
		//session.getAttribute("userId");
		PrintWriter out=response.getWriter();
		System.out.println(q);
		
		try {
			out.print(gson.toJson(bankSearchService.getAllTransactions(q)));
		} catch (BankException e) {
			
			out.print(e.getMessage());
		
		}

	}
}
