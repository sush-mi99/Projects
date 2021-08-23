package com.bank.model;

public class Customer {
	private String cust_id;
	private String cust_name;
	private String cust_emailid;
	private String cust_phno;
	private String cust_pwd;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String cust_name, String cust_emailid, String cust_phno, String cust_id, String cust_pwd) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_emailid = cust_emailid;
		this.cust_phno = cust_phno;
		this.cust_pwd = cust_pwd;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_emailid=" + cust_emailid
				+ ", cust_phno=" + cust_phno + ", cust_pwd=" + cust_pwd + "]";
	}

	public Customer(String string, String string2) {
		// TODO Auto-generated constructor stub
	}


	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_emailid() {
		return cust_emailid;
	}

	public void setCust_emailid(String cust_emailid) {
		this.cust_emailid = cust_emailid;
	}

	public String getCust_phno() {
		return cust_phno;
	}

	public void setCust_phno(String cust_phno) {
		this.cust_phno = cust_phno;
	}

	public String getCust_pwd() {
		return cust_pwd;
	}

	public void setCust_pwd(String cust_pwd) {
		this.cust_pwd = cust_pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cust_emailid == null) ? 0 : cust_emailid.hashCode());
		result = prime * result + ((cust_id == null) ? 0 : cust_id.hashCode());
		result = prime * result + ((cust_name == null) ? 0 : cust_name.hashCode());
		result = prime * result + ((cust_phno == null) ? 0 : cust_phno.hashCode());
		result = prime * result + ((cust_pwd == null) ? 0 : cust_pwd.hashCode());
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
		Customer other = (Customer) obj;
		if (cust_emailid == null) {
			if (other.cust_emailid != null)
				return false;
		} else if (!cust_emailid.equals(other.cust_emailid))
			return false;
		if (cust_id == null) {
			if (other.cust_id != null)
				return false;
		} else if (!cust_id.equals(other.cust_id))
			return false;
		if (cust_name == null) {
			if (other.cust_name != null)
				return false;
		} else if (!cust_name.equals(other.cust_name))
			return false;
		if (cust_phno == null) {
			if (other.cust_phno != null)
				return false;
		} else if (!cust_phno.equals(other.cust_phno))
			return false;
		if (cust_pwd == null) {
			if (other.cust_pwd != null)
				return false;
		} else if (!cust_pwd.equals(other.cust_pwd))
			return false;
		return true;
	}
	

	

}
