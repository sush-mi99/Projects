package com.bank.model;

public class Account {
	private String cust_name;
	private long cust_accno;
	private String aadhar;
	private double amount;
	private double open_bal;
	private double close_bal;
	private String userId1;

	
	public Account() {
		// TODO Auto-generated constructor stubString userId1,
	}


	public Account(String userId1,String cust_name, String aadhar, double open_bal) {
		super();
		this.userId1 = userId1;
		this.cust_name = cust_name;
		this.aadhar = aadhar;
		this.open_bal = open_bal;
		
	}
	
	

	public Account(String cust_name, long cust_accno, String aadhar, double open_bal, String userId1) {
		super();
		this.cust_name = cust_name;
		this.cust_accno = cust_accno;
		this.aadhar = aadhar;
		this.open_bal = open_bal;
		this.userId1 = userId1;
	}


	
	
	
	public Account(long cust_accno, double open_bal) {
		super();
		this.cust_accno = cust_accno;
		this.open_bal = open_bal;
	}


	public String getCust_name() {
		return cust_name;
	}


	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}


	public long getCust_accno() {
		return cust_accno;
	}


	public void setCust_accno(long cust_accno) {
		this.cust_accno = cust_accno;
	}


	public String getAadhar() {
		return aadhar;
	}


	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getOpen_bal() {
		return open_bal;
	}


	public void setOpen_bal(double open_bal) {
		this.open_bal = open_bal;
	}


	public double getClose_bal() {
		return close_bal;
	}


	public void setClose_bal(double close_bal) {
		this.close_bal = close_bal;
	}


	public String getUserId1() {
		return userId1;
	}


	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}


	@Override
	public String toString() {
		return "Account [cust_name=" + cust_name + ", cust_accno=" + cust_accno + ", aadhar=" + aadhar + ", open_bal="
				+ open_bal + ", userId1=" + userId1 + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aadhar == null) ? 0 : aadhar.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(close_bal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (cust_accno ^ (cust_accno >>> 32));
		result = prime * result + ((cust_name == null) ? 0 : cust_name.hashCode());
		temp = Double.doubleToLongBits(open_bal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userId1 == null) ? 0 : userId1.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (aadhar == null) {
			if (other.aadhar != null)
				return false;
		} else if (!aadhar.equals(other.aadhar))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(close_bal) != Double.doubleToLongBits(other.close_bal))
			return false;
		if (cust_accno != other.cust_accno)
			return false;
		if (cust_name == null) {
			if (other.cust_name != null)
				return false;
		} else if (!cust_name.equals(other.cust_name))
			return false;
		if (Double.doubleToLongBits(open_bal) != Double.doubleToLongBits(other.open_bal))
			return false;
		if (userId1 == null) {
			if (other.userId1 != null)
				return false;
		} else if (!userId1.equals(other.userId1))
			return false;
		return true;
	}


	
}
