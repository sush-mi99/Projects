package com.bank.dao;

import java.util.List;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;

public interface BankDAO {

	
	public List<Customer> getAllCustomers() throws BankException;
	public List<Account> getAllAccounts() throws BankException;
	public List<Transaction> getAllTransactions(long accountNumber) throws BankException;
	public Customer registerAccount(Customer customer) throws BankException;
	public Customer getPasswordByUserId(String userId) throws BankException;
	public Employee getPasswordByempId(String empId) throws BankException;
	public Account createAccount(Account account) throws BankException;
	public Account getBalanceByAccountNumber(long accountNumber) throws BankException;
	public Transaction depositAmount(Transaction transaction) throws BankException;
	public Transaction withdrawAmount(Transaction transaction) throws BankException;
	public Transaction transferAmount(Transaction transaction) throws BankException;
	Employee getPasswordByEmpId(String EmployeeId) throws BankException;
	
	
	
	
}
