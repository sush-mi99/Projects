package com.bank.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankDAO;
import com.bank.daoimpl.BankDAOImpl;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.service.BankSearchService;

public class BankSearchServiceImpl implements BankSearchService{
	
	private BankDAO bankDAO=new BankDAOImpl();
	
	@Override
	public Customer getPasswordByUserId(String userId) throws BankException {
		Customer customer=null;
		if(userId==null) {
			throw new BankException("enter correct Userid");
		}else {
			customer=bankDAO.getPasswordByUserId(userId);
		}
		return customer;
	}

	@Override
	public Account getBalanceByAccountNumber(long accountNumber) throws BankException {
		Account account =null;
		if(accountNumber==0) {
			throw new BankException("enter correct Userid");
		}else {
			account=bankDAO.getBalanceByAccountNumber(accountNumber);
		}	
		return account;
		
	}

	@Override
	public Employee getPasswordByempId(String empId) throws BankException {
		Employee employee=null;
		if(empId==null) {
			throw new BankException("enter correct Userid");
		}else {
			employee=bankDAO.getPasswordByempId(empId);
		}
		return employee;
	}

	@Override
	public List<Transaction> getAllTransactions(long accountNumber) throws BankException {
		List<Transaction> statement=new ArrayList<>();
        if(accountNumber!=0 ) {
			statement=bankDAO.getAllTransactions(accountNumber);
			
		}else {
			throw new BankException("Invalid Account Number "+accountNumber);
		}
		
		return statement;
	}

	@Override
	public Employee getPasswordByEmpId(String EmployeeId) throws BankException {
		// TODO Auto-generated method stub
		return null;
	}

}
