package io.zilker.contact_app;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
public class getinput {

	static Logger log=Logger.getLogger(application.class.getName());
	static Scanner input=new Scanner(System.in);
	public static void main(String[] args)
	{
		log.info("DO you want to \n1.)Search for a contact\n2.)Insert new contact");
		int value=input.nextInt();
		operations op=new operations();
		Connection con=null;
		connection obj=new connection();
		con=obj.con();
		
		switch(value)
		{
		
		case 1:
		{
			log.info("Please Enter the first and the last name of the contact you want to search");
			String f_name=input.next();
			String l_name=input.next();
			op.search(f_name,l_name,con);
		}
		case 2:
		{
			String f_name,l_name,email_id;
			long ph;
			int type,e_code,c_code,a_code;
			log.info("Enter first name");
			f_name=input.next();
			log.info("Enter Last name");
			l_name=input.next();
			log.info("Enter your email_id");
			email_id=input.next();
			log.info("Enter your phone number");
			ph=input.nextLong();
			log.info("Enter area code");
			a_code=input.nextInt();
			log.info("Enter country code");
			c_code=input.nextInt();
			log.info("Enter ext_code");
			e_code=input.nextInt();
			log.info("Enter type of number");
			type=input.nextInt();
		}
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
