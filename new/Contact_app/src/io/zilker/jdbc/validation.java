package io.zilker.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.zilker.bean.contact_details;
import io.zilker.bean.phone_details;
import io.zilker.utility.getconnection;

public class validation {

	Connection con = null;
	static Logger log = Logger.getLogger(operator.class.getName());

	static public boolean valid_email(String email) {

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+([.][a-zA-Z0-9]+)?@[a-zA-Z0-9]+([.][a-zA-Z]{2,3}){1,2}$");
		Matcher match = pattern.matcher(email);

		while (match.find()) {
			return true;
		}

		return false;
	}

	public void insert(contact_details cd, phone_details pd) {

		operator op = new operator();
		getconnection obj = new getconnection();
		obj.createconnection();
		con = obj.con();

		try {

			if (!valid_email(cd.getEmail_id())) {
				log.warning("Email-id is not in the right format");
				return;

			}

			op.insertion(cd, pd, con);
			log.info("Successfully Added your contact");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void search(String f_name, String l_name) {
		operator op = new operator();
		getconnection obj = new getconnection();
		obj.createconnection();
		con = obj.con();

		try {
			op.search(f_name, l_name, con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
