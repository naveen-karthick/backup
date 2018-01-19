package io.zilker.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.bean.LoginDetails;
import io.zilker.bean.ReportDetails;
import io.zilker.bean.StaffDetails;
import io.zilker.jdbc.FetchDetails;
import io.zilker.jdbc.UpdateDetails;
import io.zilker.jdbc.ValidationOfCredentials;

public class UserInterface {
	static Logger log = Logger.getLogger(UserInterface.class.getName());
	static Scanner input = new Scanner(System.in);

	static public StaffDetails printStaffDetails(int staffId, FetchDetails details) {

		// Fetch Staff details and store it in the staff bean class
		StaffDetails staff = details.getStaffDetails(staffId);

		// Printing the welcome message
		log.info("Welcome " + staff.getStaffName() + "," + staff.getDesignation() + " of the " + staff.getDepartment()
				+ " Department");
		return staff;
	}

	static public void fetchReport(FetchDetails fetchDetails) {

		// Fetching class report
		ReportDetails report = new ReportDetails();
		log.info("please Enter the date on which you want the report on in the format yyyy-mm-dd");
		report.setDateOn(input.next());
		log.info("Enter the department of which you want the report of"
				+ "\n1-ECE\n2-CSE\n3-IT\n4-MECH\n5-CIVIL\n6-EEE");
		report.setDepartment(input.nextInt());
		log.info("Enter the year of the department");
		report.setYearOfDepartment(input.nextInt());

		// Mapping every hour status to a report_details object
		Map<Integer, ReportDetails> sample_report = fetchDetails.fetchReport(report);

		for (int hour = 1; hour <= 7; hour++) {
			if (sample_report.get(hour) == null) {
				FileWriter writer;
				try {
					writer = new FileWriter("C:\\Users\\Naveen Karthick\\Desktop\\report_" + report.getDepartment()
							+ "_" + report.getDateOn() + ".txt", true);

					BufferedWriter bw = new BufferedWriter(writer);
					bw.newLine();
					bw.write("Hour : " + hour + " -- no one has reported");
					bw.flush();
					bw.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				log.info("Hour : " + hour + " -- no one has reported");
			} else {
				FileWriter writer;
				try {
					writer = new FileWriter("C:\\Users\\Naveen Karthick\\Desktop\\report_" + report.getDepartment()
							+ "_" + report.getDateOn() + ".txt", true);

					BufferedWriter bw = new BufferedWriter(writer);
					bw.newLine();
					bw.write("Hour : " + hour + "\n" + "		Reported by : " + sample_report.get(hour).getStaffName()
							+ "\n		Lecture on : " + sample_report.get(hour).getWorkDone() + "\n	Updated on : "
							+ sample_report.get(hour).getUpdatedOn() + "\n	Staff_id : "
							+ sample_report.get(hour).getStaffId());
					bw.flush();
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.info("Hour : " + hour + "\n" + "Reported by : " + sample_report.get(hour).getStaffName()
						+ "\nLecture on : " + sample_report.get(hour).getWorkDone() + "\nUpdated on : "
						+ sample_report.get(hour).getUpdatedOn() + "\nStaff_id : "
						+ sample_report.get(hour).getStaffId());
			}

		}

	}

	static public boolean submitReport(StaffDetails staff, UpdateDetails updateDetails) {
		ReportDetails report = new ReportDetails();
		report.setStaffId(staff.getStaffId());
		log.info("please Enter the department you took a lecture in" + "\n1-ECE\n2-CSE\n3-IT\n4-MECH\n5-CIVIL\n6-EEE");
		report.setDepartment(input.nextInt());
		log.info("Enter the year of the department");
		report.setYearOfDepartment(input.nextInt());
		log.info("Enter the hour of class");
		report.setHourOfClass(input.nextInt());
		input.nextLine();
		log.info("Please Enter sufficient info about the lecture");
		report.setWorkDone(input.nextLine());
		SimpleDateFormat Date_of_work = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat updated_on = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current_date = new Date();

		report.setDateOn(Date_of_work.format(current_date));
		report.setUpdatedOn(updated_on.format(current_date));

		boolean updation_successful = updateDetails.updateReport(report);
		if (updation_successful) {
			log.info("Succesfully submmitted your report to the database");

		} else {
			log.warning("There was some error while submitting your report please bare with us");

		}
		return updation_successful;
	}

	public static void main(String[] args) {	
		
		while (true) {
			String emailId;
			String password;

			// Get the user_id and password to log in

			if (args.length == 0) {
				log.info("Enter the email_id and Password to login to your portal");
				emailId = input.next();
				password = input.next();
			} else {
				emailId = args[0];
				password = args[1];
			}
			// creating object for validation for validation of email and user-id with
			// password is authentic
			ValidationOfCredentials validation = new ValidationOfCredentials();
			if (!validation.verifyEmail(emailId)) {
				log.warning("The email-id is not in the right format");
				continue;
			}
			// creating object for storing login credentials
			LoginDetails user = new LoginDetails();
			user.setEmailId(emailId);
			user.setPasword(password);

			// checking for valid login id and password
			int staffId = validation.verifyLoginCredentials(user);
			if (staffId < 1) {
				log.warning("Invalid email_id and password combination please try again");
				continue;
			}

			UpdateDetails updateDetails = new UpdateDetails();
			FetchDetails fetchDetails = new FetchDetails();
			// printing Welcome message
			// staff_details staff=print_staff_details(staffId,Fetch_details);
			StaffDetails staff = printStaffDetails(staffId, fetchDetails);

			switch (staff.getDesigId()) {

			// Assisstant Professor Functionality
			case 3: {
				while (true) {
					log.info("Press \n0 - To  Log out \n1 - To submit a report");
					if (input.nextInt() == 0) {
						break;
					}
					submitReport(staff, updateDetails);

				}
				break;
			}

			// HOD functionality
			case 2: {
				boolean logIn = true;
				while (true) {
					log.info(
							"Do want to \n0-To Log out \n1-view the log report of a class for the day?\n2-update your report\n3-update status");
					switch (input.nextInt()) {
					case 0: {
						logIn = false;
					}
					case 1: {
						fetchReport(fetchDetails);
						break;
					}
					case 2: {
						submitReport(staff, updateDetails);
						break;
					}
					case 3: {
						// Update Hod status

						ReportDetails report = new ReportDetails();

						log.info("Type\n1 - If you have viewed every class under your department's log"
								+ "\n0 - if the work is bending ");
						int status = input.nextInt();
						report.setDepartment(staff.getDeptId());
						report.setStaffId(staff.getStaffId());
						report.setStatus(status);
						log.info("Enter the date of your report in yyyy-mm-dd format");
						report.setDateOn(input.next());

						// Fetch the updated on date with time
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						report.setUpdatedOn(format.format(date));

						staff.setStatus(status);
						if (updateDetails.updateHodReport(report)) {
							log.info("Report successfully submitted to the database");
						} else {
							log.info("There was an error in the updation Check whether you have entered all"
									+ " the data in the desired format");

						}
						break;
					}
					}
					if (!logIn) {
						break;
					}
				}

			}

			// DEEN functionality
			case 1: {
				boolean logIn = true;
				while (true) {
					log.info("Welcome " + staff.getStaffName() + " Deen of the college");

					log.info(
							"Do want to \n0-To log out \n1-view the log report of a class for the day?\n2-view Hod Report");
					int n = input.nextInt();
					switch (n) {
					case 0: {
						logIn = false;
						break;
					}
					case 1: {
						fetchReport(fetchDetails);
						break;
					}
					case 2: {
						// Fetch Hod report
						log.info("please Enter the date in the format yyyy-mm-dd on which you want the hod report");
						ReportDetails report = new ReportDetails();
						report.setDateOn(input.next());
						Map<Integer, ReportDetails> reports = fetchDetails.fetchHodReport(report);
						for (int dept = 1; dept <= 6; dept++) {
							if (!(reports.get(dept) == null)) {
								String status = reports.get(dept).getStatus() == 1 ? "Validated" : "Pending";
								log.info("Department : " + reports.get(dept).getDepartmentName() + ""
										+ "\n Status report : " + status + "" + "\nUpdated on : "
										+ reports.get(dept).getUpdatedOn());
							}
						}
						break;
					}
					}
					if (!logIn) {
						break;
					}
				}

			}
			}
		}
	}

}
