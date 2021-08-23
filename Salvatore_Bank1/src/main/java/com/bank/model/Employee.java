package com.bank.model;

public class Employee {
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_pwd=" + emp_pwd + "]";
	}

	private String emp_id;
	private String emp_pwd;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String emp_id, String emp_pwd) {
		super();
		this.emp_id = emp_id;
		this.emp_pwd = emp_pwd;
	}

	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_pwd() {
		return emp_pwd;
	}
	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((emp_pwd == null) ? 0 : emp_pwd.hashCode());
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
		Employee other = (Employee) obj;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (emp_pwd == null) {
			if (other.emp_pwd != null)
				return false;
		} else if (!emp_pwd.equals(other.emp_pwd))
			return false;
		return true;
	}
		
	
}
