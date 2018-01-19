package io.zilker.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import io.zilker.bean.contact_details;
import io.zilker.bean.phone_details;

public class operator {
	
	static Logger log=Logger.getLogger(operator.class.getName());
	public void insertion(contact_details cd,phone_details pd,Connection con) throws Exception
	{
		
		String first_name=cd.getF_name();
		String second_name=cd.getL_name();
		int type=pd.getType();
		String email_id=cd.getEmail_id();
		long ph=pd.getPh_number();
		String c_code=pd.getC_code();
		String a_code=pd.getA_code();
		String ex_code =pd.getExt_code();
		Statement st=con.createStatement();
		
		st.executeUpdate("\r\n" + 
				"insert into contact_list \r\n" + 
				"(first_name,second_name,email_id)\r\n" + 
				"values\r\n" + 
				"('"+first_name+"','"+second_name+"','"+email_id+"')");	
		ResultSet rs=st.executeQuery("select last_insert_id()");
	rs.next();
	int n=rs.getInt(1);
	try
	{
		st.executeUpdate("INSERT INTO `contact_app`.`phone_list` (`contact_id`, `phone_number`, `type_1`, `ext_code`, `Area_code`, `Country_code`) VALUES ("+rs.getInt(1)+","+ph+","+type+", "+ex_code+", "+a_code+", "+c_code+");\r\n" + 
				"");
	}
	catch(Exception e)
	{
		st.executeUpdate("delete from contact_list where contact_id="+n);
		log.warning("THere was an error adding your contact either you are adding the same number or the values are not in the right format");
	}
	
	
	}
	

	public void search(String f_name,String l_name,Connection con) throws Exception
	{
		
	Statement st=con.createStatement();
		
		ResultSet rs2=st.executeQuery("select phone_list.phone_number,contact_list.first_name"
				+ ",contact_list.second_name,contact_list.email_id,phone_list.ext_code,phone_list.Area_code,phone_list.Country_code,phone_list.type_1 from phone_list join contact_list on"
				+ " phone_list.contact_id=contact_list.contact_id where"
				+ " contact_list.first_name='"+f_name+"' and contact_list.second_name='"+l_name+"'");	
	
		
		while(rs2.next()) 
		{
			
			log.info("First_name - "+rs2.getString(2));
			log.info("Last_name - "+rs2.getString(3));
			log.info("Email_id - "+rs2.getString(4));
			
			switch(rs2.getString(8))
			{
			case "1" : log.info("Home Number");
						break;
			case "2" : log.info("office Number");
			break;
			case "3" : log.info("Mobile Number");
			break;
			default:
			
			}
			
			if(!rs2.getString(5).contains("*"))
			{
			log.info("Ext_code - "+rs2.getString(5));
			}
			if(!rs2.getString(6).contains("*"))
			{
			log.info("Area_code - "+rs2.getString(6));
			}
			if(!rs2.getString(7).contains("*"))
			{
			log.info("Country_code - "+rs2.getString(7));
			}
			
			log.info("Phone_number - "+rs2.getString(1));		
		
		
		
	}
	
}
}