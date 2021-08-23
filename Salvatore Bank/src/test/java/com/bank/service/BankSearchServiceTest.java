package com.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.serviceimpl.BankSearchServiceImpl;

class BankSearchServiceTest {

	private static BankSearchService service;
	
	@BeforeAll
	public static void setUp() {
		service=new BankSearchServiceImpl();
	}
	
	@Test
	void testGetPasswordByUserId() {
		Customer c=new Customer(null, null, null, "1011", "sush");
		try {
			assertEquals(c,service.getPasswordByUserId("1011"));
		}catch(BankException e) {
			fail("Test Case failed");
		}
	}
	
	@Test
	void testGetPasswordByUserIdFalse() {
		try {
			service.getPasswordByUserId("1011");
		} catch (BankException e) {
			assertEquals("Invalid UserId or Password ", e.getMessage());
		}
	}

	@Test
	void testGetBalanceByAccountNumber() {
		Account a=new Account(null,10011, null, 33700.0, null);
		long accountNumber=10011;
		try {
			assertEquals(a,service.getBalanceByAccountNumber(accountNumber));
		}catch(BankException e) {
			fail("Test Case failed");
		}

	}
	
	@Test
	void testGetBalanceByAccountNumberFalse() {
		long accountNumber=10018;
		try {
			service.getBalanceByAccountNumber(accountNumber);
		}catch(BankException e) {
			assertEquals("Invalid Account Number ",e.getMessage());
		}

	}

	@Test
	void testGetPasswordByempId() {
		Employee emp=new Employee("admin0","admin");
		emp.setEmp_id("admin0");
		try {
			assertEquals(emp,service.getPasswordByempId("admin0"));
		}catch(BankException e) {
			fail("Test Case failed");
		}
		
	}
	
	@Test
	void testGetPasswordByempIdFalse() {
		try {
			service.getPasswordByempId("admin0");
		} catch (BankException e) {
			assertEquals("Invalid UserId or Password ", e.getMessage());
		}
		
	}

	@Test
	void testGetAllTransactionsFalse() {
		
	}

}
