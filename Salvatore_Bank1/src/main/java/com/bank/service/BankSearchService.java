package com.bank.service;

import java.util.List;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;


public interface BankSearchService {
	public Employee getPasswordByEmpId(String EmployeeId) throws BankException ;
	public Customer getPasswordByUserId(String userId) throws BankException;
	public Account getBalanceByAccountNumber(long accountNumber) throws BankException;
	public Employee getPasswordByempId(String empId) throws BankException;
	public List<Transaction> getAllTransactions(long accountNumber) throws BankException;
	
}
