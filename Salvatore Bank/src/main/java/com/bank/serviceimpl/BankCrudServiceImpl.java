package com.bank.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankDAO;
import com.bank.daoimpl.BankDAOImpl;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;

public class BankCrudServiceImpl implements BankCrudService{
	private BankDAO bankDAO=new BankDAOImpl();
	@Override
	public Customer registerAccount(Customer customer) throws BankException {
		
			if(customer!=null) {
			customer =bankDAO.registerAccount(customer);
			}else {
				throw new BankException("Not registered");
			}
		return customer;
	}
	@Override
	public Account createAccount(Account account) throws BankException {
			
		if(account!=null) {
			account=bankDAO.createAccount(account);
		}else {
			throw new BankException("No Opened");
		}
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
	

}
