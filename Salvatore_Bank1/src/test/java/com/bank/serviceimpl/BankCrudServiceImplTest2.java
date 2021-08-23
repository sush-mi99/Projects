package com.bank.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.BankCrudService;

class BankCrudServiceImplTest2 {
private static BankCrudService service;
	
	@BeforeAll
	public static void setUp() {
		service=new BankCrudServiceImpl();
	}
	@Test
	void testRegisterAccount() {
		Customer customer=new Customer("susmitha", "susmitha@gmail.com", "9441477537", "1220", "sush");
		try {
			assertEquals(customer,service.registerAccount(customer));
		}catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCreateAccount() {
		Account account=new Account("1060", "susmitha", "112323349887", 12000);
		try {
			assertEquals(account,service.createAccount(account));
		}catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	}
