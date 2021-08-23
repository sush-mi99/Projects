package com.bank.service;

import java.util.List;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public interface BankCrudService {

	public Customer registerAccount(Customer customer) throws BankException;
	public Account createAccount(Account account) throws BankException;
	public List<Customer> getAllCustomers() throws BankException;
	public List<Account> getAllAccounts() throws BankException;
	public Transaction depositAmount(Transaction transaction) throws BankException;
	public Transaction withdrawAmount(Transaction transaction) throws BankException;
	public Transaction transferAmount(Transaction transaction) throws BankException;
}
