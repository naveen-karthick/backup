package io.zilker.interfac;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.bean.contact_details;
import io.zilker.bean.phone_details;
import io.zilker.jdbc.validation;
public class user_interface {

	static Logger log=Logger.getLogger(user_interface.class.getName());
	static Scanner input=new Scanner(System.in);
	public static void main(String[] args)
	{
		log.info("DO you want to \n1.)Search for a contact\n2.)Insert new contact");
		int value=input.nextInt();
		validation val=new validation();
	
		
		switch(value)
		{
		
		case 1:
		{
			log.info("Please Enter the first and the last name of the contact you want to search");
			String f_name=input.next();
			String l_name=input.next();
			val.search(f_name, l_name);
		}
		break;
		case 2:
		{
			
			String f_name,l_name,email_id;
			long ph;
			int type;
			String e_code,c_code,a_code;
			phone_details pd=new phone_details();
			contact_details cd=new contact_details();
			
			log.info("Enter first name");
			f_name=input.next();
			log.info("Enter Last name");
			l_name=input.next();
			log.info("Enter your email_id");
			email_id=input.next();
			log.info("Enter your phone number");
			ph=input.nextLong();
			log.info("Enter area code");
			a_code=input.next();
			log.info("Enter country code");
			c_code=input.next();
			log.info("Enter ext_code");
			e_code=input.next();
			log.info("Enter type of number");
			type=input.nextInt();
			
			cd.setF_name(f_name);
			cd.setL_name(l_name);
			cd.setEmail_id(email_id);
			pd.setA_code(a_code);
			pd.setC_code(c_code);
			pd.setExt_code(e_code);
			pd.setPh_number(ph);
			pd.setType(type);
			val.insert(cd,pd);
		}
		}
		
		
	}
}
