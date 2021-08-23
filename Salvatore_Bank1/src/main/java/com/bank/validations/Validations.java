package com.bank.validations;

import org.apache.log4j.Logger;

public class Validations {

	private static Logger log = Logger.getLogger(Validations.class);

	public static boolean IsValidFirstName(String fullName) {

		if (fullName != null && fullName.matches("[a-zA-Z]{1,20}")) {
			return true;
		} else {
			log.info("Please Enter Valid Name");
			return false;
		}

	}

	
	public static boolean IsValidEmailId(String emailId) {

		if (emailId != null && emailId.matches(
				"^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$")) {
			return true;
		} else {
			log.info("Please Enter Valid Email Id");
			return false;
		}

	}

	public static boolean IsValidMobileNumber(String mobileNumber) {

		if (mobileNumber != null && mobileNumber.matches("[0-9]{10}")) {
			return true;
		} else {
			log.info("Please Enter Valid MobileNumber");
			return false;
		}

	}

	public static boolean IsValidUserId(String userId) {

		if (userId != null && userId.matches("[a-zA-Z0-9]{1,8}[_]{0,1}")) {
			return true;
		} else {
			log.info("Please Enter Valid Customer Id");
			return false;
		}

	}

	public static boolean IsValidPassword(String password) {

		if (password != null && password.matches("[a-zA-Z0-9]{8,20}")) {
			return true;
		} else {
			log.info("Please Enter Valid Password");
			return false;
		}

	}

}
