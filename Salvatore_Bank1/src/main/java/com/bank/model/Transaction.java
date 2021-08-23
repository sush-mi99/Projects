package com.bank.model;

public class Transaction {

	@Override
	public String toString() {
		return "Transaction [cust_accno=" + cust_accno + ", amount=" + amount + ", open_bal=" + open_bal1
				+ ", close_bal=" + close_bal + ", trans_id=" + trans_id + ", trans_type="
				+ trans_type + ", date=" + date + "]";
	}

	private long cust_accno;
	private double amount;
	private double open_bal1;
	private double close_bal;
	private String userId2;
	private int trans_id;
	private String trans_type;
	private String date;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	
	public Transaction(long cust_accno, double amount, double open_bal1, double close_bal, String trans_type) {
		super();
		this.cust_accno = cust_accno;
		this.amount = amount;
		this.open_bal1 = open_bal1;
		this.close_bal = close_bal;
		this.trans_type = trans_type;
	}


	public Transaction(double amount, double close_bal, String trans_type) {
		super();
		this.amount = amount;
		this.close_bal = close_bal;
		this.trans_type = trans_type;
	}


	public Transaction(long cust_accno, double open_bal1) {
		super();
		this.cust_accno = cust_accno;
		this.open_bal1 = open_bal1;
	}


	public long getCust_accno() {
		return cust_accno;
	}

	public void setCust_accno(long cust_accno) {
		this.cust_accno = cust_accno;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getOpen_bal1() {
		return open_bal1;
	}

	public void setOpen_bal1(double open_bal1) {
		this.open_bal1 = open_bal1;
	}

	public double getClose_bal() {
		return close_bal;
	}

	public void setClose_bal(double close_bal) {
		this.close_bal = close_bal;
	}

	public String getUserId2() {
		return userId2;
	}

	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(close_bal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (cust_accno ^ (cust_accno >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(open_bal1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + trans_id;
		result = prime * result + ((trans_type == null) ? 0 : trans_type.hashCode());
		result = prime * result + ((userId2 == null) ? 0 : userId2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(close_bal) != Double.doubleToLongBits(other.close_bal))
			return false;
		if (cust_accno != other.cust_accno)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(open_bal1) != Double.doubleToLongBits(other.open_bal1))
			return false;
		if (trans_id != other.trans_id)
			return false;
		if (trans_type == null) {
			if (other.trans_type != null)
				return false;
		} else if (!trans_type.equals(other.trans_type))
			return false;
		if (userId2 == null) {
			if (other.userId2 != null)
				return false;
		} else if (!userId2.equals(other.userId2))
			return false;
		return true;
	}
	
	
	
	
	
	
}
