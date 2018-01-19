package io.zilker.jdbc;

import java.util.Date;
import java.util.logging.Logger;

import io.zilker.bean.PersonDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
	Logger log=Logger.getLogger(Validation.class.getName());
	public boolean checkDate(String date,PersonDetails person) {
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date formattedDate=format.parse(date);
			person.setBirthDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.info("Date is not  in the right Format");
			return false;
		}
		return true;
		
	}
	
	public boolean checkSalary(String salary,PersonDetails person) {
		
		int validSalary;
		try {
		 validSalary=Integer.parseInt(salary);
		}
		catch(NumberFormatException e ) {
			log.info("Salary must be a number");
			return false;
		}
		person.setSalary(validSalary);
		return true;
	}
	
	
}
