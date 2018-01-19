package io.zilker.contact_app;

import java.sql.*;
import java.util.logging.*;
import java.util.*;
public class application {
	static Logger log=Logger.getLogger(application.class.getName());
	static Scanner input=new Scanner(System.in);
	static Connection con=null;
	static public void insert_contact () throws Exception 
	{
		log.info("Enter the first_name "
				+ ",\n"
				+ "last_name,"
				+ "\ntype,\nemail_id,"
				+ "\nphone_number,\ncountry_code,"
				+ "\narea_code,\next_code");
		String first_name=input.next();
		String second_name=input.next();
		int type=input.nextInt();
		String email_id=input.next();
		long ph=input.nextLong();
		int c_code=input.nextInt();
		int a_code=input.nextInt();
		int ex_code =input.nextInt();
		connection con1=new connection();
		con=con1.con();
		Statement st=con.createStatement();
		
		st.executeUpdate("\r\n" + 
				"insert into contact_list \r\n" + 
				"(first_name,second_name,email_id)\r\n" + 
				"values\r\n" + 
				"('"+first_name+"','"+second_name+"','"+email_id+"')");	
ResultSet rs=		st.executeQuery("select last_insert_id()");
	rs.next();
	System.out.println(rs.getInt(1));
		st.executeUpdate("INSERT INTO `contact_app`.`phone_list` (`contact_id`, `phone_number`, `type_1`, `ext_code`, `Area_code`, `Country_code`) VALUES ("+rs.getInt(1)+","+ph+","+type+", "+ex_code+", "+a_code+", "+c_code+");\r\n" + 
				"");
		}
		
	
	
	static public void search_contact(String first_name,String second_name,Connection con) throws Exception
	{
		
		
		Statement st=con.createStatement();
		
		ResultSet rs2=st.executeQuery("select phone_list.phone_number,contact_list.first_name"
				+ ",contact_list.second_name,contact_list.email_id from phone_list join contact_list on"
				+ " phone_list.contact_id=contact_list.contact_id where"
				+ " contact_list.first_name='"+first_name+"' and contact_list.second_name='"+second_name+"'");	
	
		System.out.println("Phone Number		First_name	Last_name		email-id		");
		while(rs2.next()) 
		{
			for(int i=1;i<=4;i++)
			{
				System.out.print(rs2.getString(i)+"		");
			}
			System.out.println();  
		  
		}
		}
		
		
		
	
	
	
	
	
	public static void main(String[] args)
	{
	
			log.info("DO you want to \n1.)Search for a contact\n2.)Insert new contact");
			int value=input.nextInt();
	
			try
			{
			switch(value)
			{
			case 1 :;
			break;
			
			case 2 : insert_contact();
			default:
				
			
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				
			}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
