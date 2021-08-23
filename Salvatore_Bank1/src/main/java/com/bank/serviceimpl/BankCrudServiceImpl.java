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
import com.bank.service.BankCrudService;

public class BankCrudServiceImpl implements BankCrudService{
	private BankDAO bankDAO=new BankDAOImpl();
	@Override
	public Customer registerAccount(Customer customer) throws BankException {
		
			
			customer =bankDAO.registerAccount(customer);
			
		return customer;
	}
	
	@Override
	public Account createAccount(Account account) throws BankException {
			account=bankDAO.createAccount(account);
		return account;
	}
	
	@Override
	public List<Customer> getAllCustomers() throws BankException {
		List<Customer> listCustomer=new ArrayList<>();
       try {
			listCustomer=bankDAO.getAllCustomers();	
		}
       catch(BankException b) {
			throw new BankException("No Data");
		}
		
		return listCustomer;
	}
	
	@Override
	public List<Account> getAllAccounts() throws BankException {
		List<Account> listAccount=new ArrayList<>();
		try {
			listAccount=bankDAO.getAllAccounts();	
		}
       catch(BankException b) {
			throw new BankException("No Data");
		}
		return listAccount;
	}
	
	@Override
	public List<Transaction> getAllTransactions() throws BankException {
		List<Transaction> listTransaction=new ArrayList<>();
	       try {
				listTransaction=bankDAO.getAllTransactions();	
			}
	       catch(BankException b) {
				throw new BankException("No Data");
			}
			
			return listTransaction;
	}
	@Override
	public Transaction depositAmount(Transaction transaction) throws BankException {
		try {
			transaction=bankDAO.depositAmount(transaction);
		}
       catch(BankException b) {
			throw new BankException("NO TRANSACTION PERFORMED");
		}
		
		return transaction;
	}
	@Override
	public Transaction withdrawAmount(Transaction transaction) throws BankException {
		try {
			transaction=bankDAO.withdrawAmount(transaction);	
		}
       catch(BankException b) {
			throw new BankException("NO TRANSACTION PERFORMED");
		}
		return transaction;
	}
	@Override
	public Transaction transferAmount(Transaction transaction) throws BankException {
		
		return transaction;
	}
	@Override
	public boolean isValidLoginCredentials(Customer customer) throws BankException {
		boolean b=false;
		if(customer!=null ) {
			
			//code here for DAO
			b=bankDAO.isValidLoginCredentials(customer);
		}else {
			throw new BankException("Invalid customername or Password");
		}
		
		return b;
	}
	@Override
	public boolean isValidLoginCredentials(Employee employee) throws BankException {
		boolean b=false;
		if(employee!=null ) {
			
			//code here for DAO
			b=bankDAO.isValidLoginCredentials(employee);
		}else {
			throw new BankException("Invalid employeename or Password");
		}
		
		return b;
	}

	

}
