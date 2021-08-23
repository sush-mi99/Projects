package com.bank.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.service.BankCrudService;
import com.bank.service.BankSearchService;
import com.bank.serviceimpl.BankCrudServiceImpl;
import com.bank.serviceimpl.BankSearchServiceImpl;
import com.bank.validations.Validations;

public class Salvatore_Bank {

	private static Logger log = Logger.getLogger(Salvatore_Bank.class);

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			log.info("========Welcome to Salvatore Bank========");
			log.info("====Main Menu====");
			log.info("1)Register");
			log.info("2)Login");
			log.info("3)EXIT");
			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				log.info(e.getMessage());
			}
			switch (ch) {
			case 1:
				BankCrudService bankCrudService = new BankCrudServiceImpl();
				try {
					boolean valid =false;
					String cust_name,cust_emailid,cust_phno,cust_id,cust_pwd;
					do {
					log.info("Enter your Full Name");
					 cust_name = sc.nextLine();
					valid= Validations.IsValidFirstName(cust_name);
					}while(!valid);
					do {
					log.info("Enter your Email Id");
					cust_emailid = sc.nextLine();
					valid=Validations.IsValidEmailId(cust_emailid);
					}while(!valid);
					do {
					log.info("Enter your Mobile Number");
					cust_phno = sc.nextLine();
					valid=Validations.IsValidMobileNumber(cust_phno);
					}while(!valid);
					do {
					log.info("Enter your customer id");
					cust_id = sc.nextLine();
					valid=Validations.IsValidUserId(cust_id);
					}while(!valid);
					do {
					log.info("Enter your Password");
					cust_pwd = sc.nextLine();
					valid=Validations.IsValidPassword(cust_pwd);
					}while(!valid);
					Customer customer = new Customer(cust_name, cust_emailid, cust_phno,cust_id, cust_pwd);
					customer = bankCrudService.registerAccount(customer);
					if (customer != null) {
						log.info("Successfully registered!");
						log.info("\n");
						log.info(customer);
						log.info("\n");
					}
				} catch (BankException e) {
					log.warn(e.getMessage());
				}
				break;
			case 2:
				int menuChoice = 0;
				do {
					log.info("======Login Portal======");
					log.info("1)Login as Customer");
					log.info("2)Login as Employee");
					log.info("3)Main Menu");
					log.info("4)Exit");
					try {
						menuChoice = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
						log.warn(e.getMessage());
					}
					switch (menuChoice) {
					case 1:
						BankSearchService bankSearchService = new BankSearchServiceImpl();

						String cust_id = null;
						Customer customer = null;

						try {
							log.info("Enter cust_id");
							cust_id = sc.nextLine();
							customer = bankSearchService.getPasswordByUserId(cust_id);

							if (customer.getCust_pwd() != null) {
								log.info("Enter Password");
								String password = sc.nextLine();
								if (customer.getCust_pwd().equals(password)) {
									log.info("\n");
									log.info("======Welcome to Salvatore Bank======");
									log.info("\n");
									do {
										int ch1 = 0;
										log.info("======Customer Menu======");
										log.info("1)New Account");
										log.info("2)Transfer");
										log.info("3)Withdraw");
										log.info("4)Deposit");
										log.info("5)view statements");
										log.info("6)Logout");
										try {
											ch1 = Integer.parseInt(sc.nextLine());
										} catch (NumberFormatException e) {
											log.info(e.getMessage());
										}
										switch (ch1) {
										case 1:
											BankCrudService bankCrudService1 = new BankCrudServiceImpl();
											Account account = null;
											log.info("Enter your Name");
											String name = sc.nextLine();
											log.info("Enter your AadharCard Number");
											String aadhar = sc.nextLine();
											log.info("Enter your Initial Amount to open account");
											double open_bal10 = Double.parseDouble(sc.nextLine());
											String UserId1 = cust_id;
											if (aadhar.matches("[0-9]{12}")) {
												account = new Account(UserId1, name, aadhar, open_bal10);
												System.out.println(account);
											} else {
												log.warn("Invalid AadharCard Number");
											}
											try {
												account = bankCrudService1.createAccount(account);
												if (account != null) {
													log.info("Successfully registered!");
													log.info("\n");
													log.info("Thank You");
													log.info("Your Account Number");
													log.info(account.getCust_accno());
													log.info("\n");
												}
											} catch (BankException e) {
												log.info(e);
											}
											break;
										case 2:
											Transaction transaction2 = null;
											Transaction transaction3 = null;
											double close_bal =0;
											BankCrudService bankCrudService5 = new BankCrudServiceImpl();
											BankSearchService bankSearchService5 = new BankSearchServiceImpl();
											Account account4 = new Account();
											log.info("Enter Your Account Number");
											long cust_accno3 = Long.parseLong(sc.nextLine());
											log.info("Enter Amount to be transfered");
											double amount3 = Double.parseDouble(sc.nextLine());
											try {
												account4 = bankSearchService5.getBalanceByAccountNumber(cust_accno3);
												double open_bal1 = account4.getOpen_bal();
												String trans_type = "Withdraw";
												if(amount3<open_bal1) {
												 close_bal = open_bal1 - amount3;
												}else {
													log.warn("NO sufficient amount to transfer");
													break;
												}
												transaction2 = new Transaction(cust_accno3, amount3, open_bal1,
														close_bal, trans_type);
												transaction2 = bankCrudService5.depositAmount(transaction2);
												log.info("Amount " + amount3);
												log.info("Transferring from...." +cust_accno3);
												
											} catch (BankException b) {
												log.warn(b.getMessage());
											}
											BankCrudService bankCrudService6 = new BankCrudServiceImpl();
											BankSearchService bankSearchService6 = new BankSearchServiceImpl();
											Account account5 = new Account();
											log.info("Enter Account Number to be Transferred to   ");
											long accountNumber4 = Long.parseLong(sc.nextLine());
											double amount5 = amount3;
											try {
												account5 = bankSearchService6.getBalanceByAccountNumber(accountNumber4);
												double balance = account5.getOpen_bal();
												String transType = "Deposit";
												double closingBalance = balance + amount5;

												transaction3 = new Transaction(accountNumber4, amount5, balance,
														closingBalance, transType);
												transaction3 = bankCrudService6.depositAmount(transaction3);
												
											} catch (BankException d) {
												log.warn(d.getMessage());
											}

											log.info("Amount " + amount5
													+ "   Transferred to   "
													+ accountNumber4);
											log.info("Thankyou");
											log.info("\n");
											
											break;
										case 3:
											Transaction transaction1 = null;
											double close_bal1=0;
											BankCrudService bankCrudService3 = new BankCrudServiceImpl();
											BankSearchService bankSearchService2 = new BankSearchServiceImpl();
											Account account2 = new Account();
											log.info("Enter Your Account Number");
											long accountNumber1 = Long.parseLong(sc.nextLine());
											log.info("Enter Amount to be withdrawn");
											double amount1 = Double.parseDouble(sc.nextLine());
											try {
												account2 = bankSearchService2.getBalanceByAccountNumber(accountNumber1);
												double open_bal1 = account2.getOpen_bal();
												String trans_type1 = "Withdraw";
												if(amount1<open_bal1) {
												 close_bal1 = open_bal1 - amount1;
			
												transaction1 = new Transaction(accountNumber1, amount1, open_bal1,
														close_bal1, trans_type1);
												transaction1 = bankCrudService3.depositAmount(transaction1);
												log.info("Amount " + amount1
														+ "Successfully Withdrawn!"
														+ accountNumber1);
												log.info(transaction1);
												}else {
													log.warn("No sufficient Amount for Withdrawl");
												}
											} catch (BankException b) {
												log.warn(b.getMessage());
											}
											log.info("Thankyou");
											log.info("\n");

											break;
										case 4:
											Transaction transaction = null;
											BankCrudService bankCrudService2 = new BankCrudServiceImpl();
											BankSearchService bankSearchService1 = new BankSearchServiceImpl();
											Account account1 = new Account();
											log.info("Enter Your Account Number");
											long cust_accno = Long.parseLong(sc.nextLine());
											log.info("enter Amount to be deposited");
											double amount = Double.parseDouble(sc.nextLine());
											try {
												account1 = bankSearchService1.getBalanceByAccountNumber(cust_accno);
												double balance = account1.getOpen_bal();
												String transType = "Deposit";
												double closingBalance = balance + Math.abs(amount);

												transaction = new Transaction(cust_accno, amount, balance,
														closingBalance, transType);
												transaction = bankCrudService2.depositAmount(transaction);
											} catch (BankException d) {
												log.warn(d.getMessage());
											}

											log.info("Amount " + amount
													+ "Amount Credited"
													+ cust_accno);
											log.info(transaction);
											log.info("Thankyou");
											log.info("\n");

											break;
										case 5:
											log.info("Enter AccountNumber to get the Statement ");
											long accountNumber5 = Long.parseLong(sc.nextLine());
											try {
												List<Transaction> statement = bankSearchService
														.getAllTransactions(accountNumber5);
												if (statement != null && statement.size() > 0) {
													log.info("Statement for the Account Number  " + accountNumber5);
													for (Transaction t : statement) {
														log.info(t);
													}
												}
											} catch (BankException e) {
												log.error(e.getMessage());
											}
											log.info("Thankyou");

											break;
										case 6:
											log.info("Logged out");
											System.exit(0);
											break;
										default:
											log.info("Invalid Option");
											break;
										}

									} while (ch != 6);
									break;

								} else {
									log.warn("Invalid id or password");
									break;
								}
							}
						} catch (BankException | NullPointerException e1) {
							log.warn("UserId not Exists");
						}

						break;
					case 2:
						BankSearchService bankSearchService3 = new BankSearchServiceImpl();

						String empId = null;
						Employee employee = null;
						log.info("Enter EmployeeId");
						empId = sc.nextLine();
						try {
							employee = bankSearchService3.getPasswordByempId(empId);

							if (employee.getEmp_pwd() != null) {
								log.info("Enter Password");
								String password = sc.nextLine();
								if (employee.getEmp_pwd().equals(password)) {
									log.info("\n");
									log.info("======Welcome to Salvatore Bank======");
									log.info("====Employee Menu====");
								
							
							int ch2 = 0;
							do {
								log.info("1)View All the Accounts");
								log.info("2)View All Customers");
								log.info("3)View Customer Transactions");
								log.info("4)LogOut");
								try {
									ch2 = Integer.parseInt(sc.nextLine());
								} catch (NumberFormatException e) {
									log.info(e.getMessage());
								}
								switch (ch2) {
								case 1:
									BankCrudService bankCrudService4 = new BankCrudServiceImpl();
									try {
										List<Account> listAccount = bankCrudService4.getAllAccounts();
										if (listAccount != null && listAccount.size() > 0) {
											for (Account a : listAccount) {
												log.info(a);
											}
										}
									} catch (BankException e) {
										log.error(e.getMessage());
									}
									log.info("\n");
									log.info("Thankyou");
									log.info("\n");
									break;
							
								case 2:
									BankCrudService bankCrudService5 = new BankCrudServiceImpl();
									try {
										List<Customer> listCustomer = bankCrudService5.getAllCustomers();
										if (listCustomer != null && listCustomer.size() > 0) {
											for (Customer c : listCustomer) {
												log.info(c);
										
										}}
									} catch (BankException e) {
										log.error(e.getMessage());
									}
									log.info("\n");
									log.info("Thankyou");
									log.info("\n");
									break;
								case 3:
									BankSearchService bankSearchService1=new BankSearchServiceImpl();
									log.info("Enter AccountNumber to get the Statement ");
									long accountNumber5 = Long.parseLong(sc.nextLine());
									try {
										List<Transaction> statement = bankSearchService1.getAllTransactions(accountNumber5);
										if (statement != null && statement.size() > 0) {
											log.info("Statement for the Account Number  " + accountNumber5);
											for (Transaction t : statement) {
												log.info(t);
											}
										}
									} catch (BankException e) {
										log.error(e.getMessage());
									}
									log.info("Thankyou");
									break;
								case 4:
									log.info("Logged out");
									System.exit(0);
									break;
								default:
									log.info("Invalid Option");
									break;
								}
							} while (ch2 != 4);
								}else {
									log.warn("invalid Empid or password entered");
									break;
								}
							}else {
									log.warn("invalid Empid or password entered");
									break;
								}
						} catch (BankException e) {

							log.warn(e.getMessage());
							;
						}
						break;
					case 3:
						log.info("MainMenu.........");
						break;
					case 4:
						System.exit(0);
					default:
						log.info("Invalid Option");
						break;
					}
				} while (menuChoice != 3);
				break;
			case 3:
				break;
			default:
				log.warn("Invalid Option... Enter between 1-3");
				break;
			}
		} while (ch != 3);
		sc.close();
	}
}
