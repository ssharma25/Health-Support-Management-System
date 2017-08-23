/**
 * 
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JSeparator;

import model.DataFactory;

/**
 * @author sukhdev
 *
 */
public class MainActivity {

	public static Scanner mScanner;
	
	static Connection mConnection;	
	static ResultSet mResultSet;
	static PreparedStatement mPreparedStatement;
	static JSeparator mSeparator;
	static Statement mStatement;
	public static int mCountForDisease;
	public static int mInput;
	
	public static PatientMainActivity mPatientMainActivity;
	public static HealthSupporterMainActivity mHealthSupporterActivity; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		mScanner = new Scanner(System.in);		
		mainMethod(); 
	}

	public static void mainMethod() {
		System.out.println("Enter Choice");
		System.out.println("1 For Patient Login");
		System.out.println("2 For Health Supporters Login");
		
		mInput = mScanner.nextInt();
		
		if(mInput == 1){
			patientLogin();
		}else{
			supporterLogin(); 
		}
		
	}

	/**
	 * 
	 */
	private static void supporterLogin() {
		
		System.out.println("Enter UserName");
		String mUsername = mScanner.next();
		System.out.println("Enter Password");
		String mPassword = mScanner.next();
		
		isMatchingSupporter(mUsername, mPassword);
		
	}

	/**
	 * 
	 */
	private static void patientLogin() {
		System.out.println("Enter UserName");
		String mUsername = mScanner.next();
		System.out.println("Enter Password");
		String mPassword = mScanner.next();
		
		isMatchingPatient(mUsername, mPassword); 
	}

	private static void isMatchingPatient(String mUsername, String mPassword) {
		mPatientMainActivity=new PatientMainActivity(mUsername);
		mUsername = "'"+mUsername+"'";
		String mPass = "";
		String mTemp = "select password from USERS "
				+ "where username ="
				+ ""+mUsername;

		ResultSet mResultSet = executeSQLQuery(mTemp);  

		try {
			while(mResultSet.next()){ 
				mPass=mResultSet.getString("PASSWORD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(mPassword.equals(mPass)){
			mTemp = "select MAX(diagnosesID) from DIAGNOSES";
			mResultSet = executeSQLQuery(mTemp);
			try {
				while(mResultSet.next()){
					mCountForDisease = mResultSet.getInt(1); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mPatientMainActivity.displayPatientOptions();
			
		}else{
			System.out.println("Incorrect username or password");
			patientLogin();

		}
	}
	
	private static void isMatchingSupporter(String mUsername, String mPassword) {
		mHealthSupporterActivity = new HealthSupporterMainActivity(mUsername);
		mUsername = "'"+mUsername+"'";
		String mPass = "";
		String mTemp = "select password from USERS "
				+ "where username ="
				+ ""+mUsername;

		ResultSet mResultSet = executeSQLQuery(mTemp);  

		try {
			while(mResultSet.next()){ 
				mPass=mResultSet.getString("PASSWORD");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(mPassword.equals(mPass)){
			mTemp = "select MAX(diagnosesID) from DIAGNOSES";
			mResultSet = executeSQLQuery(mTemp);
			try {
				while(mResultSet.next()){
					mCountForDisease = mResultSet.getInt(1); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mHealthSupporterActivity.displaySupporterOptions();
			
		}else{
			System.out.println("Incorrect username or password");
			supporterLogin();

		}
	}

	/**
	 * 
	 * @param mTemp
	 * @return
	 */ 
	private static ResultSet executeSQLQuery(String mTemp) {
		// TODO Auto-generated method stub
		ResultSet mRSet = null;
		try {
			mStatement=DataFactory.getConnection().createStatement();
			mRSet=mStatement.executeQuery(mTemp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return mRSet;
	}
	
	/**
	 * 
	 * @param mTemp
	 * @return
	 */
	public static java.sql.Date getSQLDate(String mTemp){
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date mDate = null;
		try {
			if(!mTemp.isEmpty())
			mDate = format.parse(mTemp);
		} catch (ParseException e1) {
			e1.printStackTrace(); 
		}
		java.sql.Date sqlDate = null;
		if(mDate != null)
			sqlDate = new java.sql.Date(mDate.getTime());
		return sqlDate;
	}
	
}
