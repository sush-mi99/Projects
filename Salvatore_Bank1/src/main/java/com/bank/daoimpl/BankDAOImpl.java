package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bank.dao.BankDAO;
import com.bank.dbutil.PostgresConnection;
import com.bank.exception.BankException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;

public class BankDAOImpl implements BankDAO {
	private static Logger log = Logger.getLogger(BankDAOImpl.class);

	@Override
	public Customer registerAccount(Customer customer) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql1 = ("insert into bank_schema.login(cust_id,cust_pwd) values(?,?)");
			String sql = ("insert into bank_schema.customer(cust_name,cust_emailid,cust_phno,cust_id) values(?,?,?,?)");
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCust_name());
			preparedStatement.setString(2, customer.getCust_emailid());
			preparedStatement.setString(3, customer.getCust_phno());
			preparedStatement.setString(4, customer.getCust_id());
			preparedStatement1.setString(1, customer.getCust_id());
			preparedStatement1.setString(2, customer.getCust_pwd());
			int c1 = preparedStatement1.executeUpdate();
			int c = preparedStatement.executeUpdate();
			

			if (c == 1 && c1 == 1) {
				log.info("Registration Successful");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return customer;
	}

	@Override
	public Account createAccount(Account account) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.account(userId1,cust_name,aadhar,open_bal) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// preparedStatement.setDouble(1, account.getAccountNumber());
			preparedStatement.setDouble(4, account.getOpen_bal());
			preparedStatement.setString(3, account.getAadhar());
			preparedStatement.setString(1, account.getUserId1());
			preparedStatement.setString(2, account.getCust_name());
			int c = preparedStatement.executeUpdate();

			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					account.setCust_accno(resultSet.getLong(5));
					log.info("Account Opened");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return account;
	}

	@Override
	public List<Customer> getAllCustomers() throws BankException {
		List<Customer> listCustomer=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.customer ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Customer customer=new Customer();
				customer.setCust_id(resultSet.getString(1));
				customer.setCust_name(resultSet.getString(2));
				customer.setCust_emailid(resultSet.getString(3));
				customer.setCust_phno(resultSet.getString(4));
				listCustomer.add(customer);
			}
			if(listCustomer.size()==0) {
				throw new BankException("No customers Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured");
		}
		return listCustomer;
	}

	@Override
	public List<Account> getAllAccounts() throws BankException {
		List<Account> listAccount=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.account";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Account account=new Account();
				account.setCust_accno(resultSet.getLong(5));
				account.setAadhar(resultSet.getString(3));
				account.setOpen_bal(resultSet.getDouble(4));
				account.setCust_name(resultSet.getString(2));
				account.setUserId1(resultSet.getString(1));
				listAccount.add(account);
			}
			if(listAccount.size()==0) {
				throw new BankException("No accounts Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured");
		}
		return listAccount;
	}
	
	@Override
	public List<Transaction> getAllTransactions() throws BankException {
		List<Transaction> listTransaction=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.transaction";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction=new Transaction();
				transaction.setTrans_id(resultSet.getInt(1));
				transaction.setTrans_type(resultSet.getString(2));
				transaction.setCust_accno(resultSet.getLong(3));
				transaction.setAmount(resultSet.getDouble(4));
				transaction.setOpen_bal1(resultSet.getDouble(5));
				transaction.setClose_bal(resultSet.getDouble(6));
				transaction.setDate(resultSet.getString(7));
				listTransaction.add(transaction);
			}
			if(listTransaction.size()==0) {
				throw new BankException("No transactions Found in DataBase");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured");
		}
		return listTransaction;
	}

	@Override
	public Transaction depositAmount(Transaction transaction) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.transaction(trans_type,balance,tamount,updated_bal,cust_accno) values(?,?,?,?,?)";
			String sql1 = "update bank_schema.account set open_bal=? where cust_accno = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setString(1, transaction.getTrans_type());
			preparedStatement.setDouble(2, transaction.getOpen_bal1());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setDouble(4, transaction.getClose_bal());
			preparedStatement.setDouble(5, transaction.getCust_accno());
			preparedStatement1.setDouble(1, transaction.getClose_bal());
			preparedStatement1.setDouble(2, transaction.getCust_accno());

			int c = preparedStatement.executeUpdate();
			int c1 = preparedStatement1.executeUpdate();

			if (c == 1 && c1 == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTrans_id(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return transaction;
	}

	@Override
	public Transaction withdrawAmount(Transaction transaction) throws BankException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.transaction(trans_type,balance,tamount,updated_bal,cust_accno) values(?,?,?,?,?)";
			String sql1 = "update bank_schema.account set balance=? where cust_accno = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement.setString(1, transaction.getTrans_type());
			preparedStatement.setDouble(2, transaction.getOpen_bal1());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setDouble(4, transaction.getClose_bal());
			preparedStatement.setDouble(5, transaction.getCust_accno());
			preparedStatement1.setDouble(1, transaction.getClose_bal());
			preparedStatement1.setDouble(2, transaction.getCust_accno());

			int c = preparedStatement.executeUpdate();
			int c1 = preparedStatement1.executeUpdate();

			if (c == 1 && c1 == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTrans_id(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return transaction;
	}

	@Override
	public Transaction transferAmount(Transaction transaction) throws BankException {
		
		return transaction;
	}

	@Override
	public Customer getPasswordByUserId(String UserId) throws BankException {
		Customer customer = new Customer();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select cust_id,cust_pwd from bank_schema.login where cust_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, UserId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				customer.setCust_id(resultSet.getString("cust_id"));
				customer.setCust_pwd(resultSet.getString("cust_pwd"));
			} else {
				throw new BankException("Invalid UserId or Password ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return customer;
	}

	@Override
	public Account getBalanceByAccountNumber(long accountNumber) throws BankException {
		Account account = new Account();
		try (Connection connection = PostgresConnection.getConnection()) {

			String sql = "select cust_accno,open_bal from bank_schema.account where cust_accno =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				account.setCust_accno(resultSet.getLong("cust_accno"));
				account.setOpen_bal(resultSet.getDouble("open_bal"));
				
			} else {
				throw new BankException("Invalid Account Number ");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return account;

	}

	@Override
	public Employee getPasswordByempId(String empId) throws BankException {
		Employee employee = new Employee();
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "select emp_id,emp_pwd from bank_schema.employee where emp_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, empId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				employee.setEmp_id(resultSet.getString("emp_id"));
				employee.setEmp_pwd(resultSet.getString("emp_pwd"));
			} else {
				throw new BankException("Invalid EmpId or Password ");
			}

		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BankException("Internal error occured");
		}
		return employee;
	}

	@Override
	public List<Transaction> getAllTransactions(long accountNumber) throws BankException {
		List<Transaction> statement=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select * from bank_schema.transaction where cust_accno = ?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Transaction transaction=new Transaction();
				transaction.setTrans_id(resultSet.getInt("trans_id"));
				transaction.setTrans_type(resultSet.getString("trans_type"));
				transaction.setCust_accno(resultSet.getLong("cust_accno"));
				transaction.setAmount(resultSet.getDouble("tamount"));
				transaction.setOpen_bal1(resultSet.getDouble("balance"));
				transaction.setClose_bal(resultSet.getDouble("updated_bal"));
				transaction.setDate(resultSet.getString("date"));
				statement.add(transaction);
			}
			if(statement.size()==0) {
				throw new BankException("No records exists for AccountNumber  : "+accountNumber);
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);//logger
			throw new BankException("Internal error occured");
		}
		
		
		return statement;
	}

	@Override
	public boolean isValidLoginCredentials(Customer customer) throws BankException {
		boolean b=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select cust_id from bank_schema.login where cust_id=? and cust_pwd=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getCust_id());
			preparedStatement.setString(2, customer.getCust_pwd());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}else {
				throw new BankException("Invaid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);//logger
			throw new BankException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return b;
	}

	@Override
	public boolean isValidLoginCredentials(Employee employee) throws BankException {
		boolean a=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select emp_id from bank_schema.employee where emp_id=? and emp_pwd=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, employee.getEmp_id());
			preparedStatement.setString(2, employee.getEmp_pwd());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				a=true;
			}else {
				throw new BankException("Invaid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);//logger
			throw new BankException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return a;
	}

	
	
}
