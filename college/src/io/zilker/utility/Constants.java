package io.zilker.utility;

public class Constants {
	
public static final String LOGIN="select staff_id from login where email_id=? and pass=?";
public static final String STAFF="select  staff_details.staff_name,staff_details.designation,staff_details.dept_id,departments.dept_name,designations.designation from staff_details join departments join designations on staff_details.dept_id=departments.dept_id and staff_details.designation=designations.designation_id where staff_details.staff_id=?";
public static final String SUBMITREPORT="INSERT INTO `college_logbook`.`staff_report` (`date_on`, `hour_on`, `staff_id`, `work_done`, `updated_on`, `year_1`, `dept_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";
public static final String FETCHREPORT="select staff_report.staff_id,staff_details.staff_name,staff_report.work_done,staff_report.updated_on from staff_details join staff_report on staff_details.staff_id=staff_report.staff_id where staff_report.date_on=? and staff_report.hour_on=? and staff_report.year_1=? and staff_report.dept_id=?";
public static final String HODREPORT="INSERT INTO `college_logbook`.`hod_report` (`date_on`, `dept_id`, `Hod_id`, `status_report`, `updated_on`) VALUES (?, ?, ?, ?, ?)";
public static final String FETCHHODREPORT="SELECT  departments.dept_name,hod_report.status_report,hod_report.updated_on FROM hod_report join departments on hod_report.dept_id=departments.dept_id where hod_report.dept_id=? and hod_report.date_on=?";


	
	
}
