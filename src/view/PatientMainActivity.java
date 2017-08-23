package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.DataFactory;
import model.Patient;
import model.Supporter;
import controllers.PatientController;
import controllers.SupporterController;

public class PatientMainActivity {

	Scanner mScanner = new Scanner(System.in);
	int mPatientChoice = 0;
	PatientController mPatientController;
	private String mPatientID;
	private int mCount = 0;
	private String[] mUsersArray = new String[10];
	private String mSelectedSupporter;
	private boolean mIsPrimaryExisting;
	private boolean mIsSecondaryExisting;
	private String mPrimaryHealthSupporter;
	private String mSecondaryHealthSupporter;
	private PatientDiagnosesActivity mPatientDiagnosesActivity; 
	
	public PatientMainActivity(String mUsername) { 
		mPatientID = mUsername; 
	}
	public void displayPatientOptions() {
		
		try {
			mPatientController = new PatientController();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter Choice");
		System.out.println("1 Profile");
		System.out.println("2 Diagnoses");
		System.out.println("3 Health Indicators");
		System.out.println("4 Alerts");
		System.out.println("5 Health Supporters");
		System.out.println("6 Logout");
		mPatientChoice = mScanner.nextInt();

		switch (mPatientChoice) {
		case 1:
			patientProfileMethod();
			displayPatientOptions();
			break;
		case 2:
			patientDiagnosesMethod();
			displayPatientOptions();
			break;
		case 3:
			patientHIMethod();
			displayPatientOptions();
			break;
		case 4:
			patientAlertsMethod();
			displayPatientOptions();
			break;
		case 5:
			patientHSMethod();
			displayPatientOptions();
			break; 
		case 6:
			patientLogoutMethod();
			break;
		default:
			break;
		}
		
		
	}
	
	/**
	 * 
	 */
	private void patientLogoutMethod() {
		MainActivity.mainMethod();
		
	}
	
	
	
	/**
	 * Health Supporters
	 */
	private void patientHSMethod() {
		
		System.out.println("Select any of the following options");
		System.out.println("1 View the Supporters");
		System.out.println("2 Add the Supporters");
		System.out.println("3 Authorization");
		System.out.println("4 Deletion");
		
		int mValue = mScanner.nextInt();
		
		mCount = 0;
		fetchAllUsers();
		fetchAlreadyAddedSupporters();	
		if(mValue == 1){
			System.out.println("Primary Health Supporter: "+mPrimaryHealthSupporter);
			System.out.println("Secondary Health Supporter: "+mSecondaryHealthSupporter);
			System.out.println();
		}else if(mValue == 2){
			System.out.println("Which of the following do you wanna add as Health Supporter"); 
			int mCo = 0;
			for(int i = 0; i < mCount; i++){
				System.out.println(mCo+":"+mUsersArray[i]);
				mCo++;
			}
			mSelectedSupporter = selectSupporter(); 
			System.out.println("Is it primary supporter(y/n)?");
			String mIs = mScanner.next();
			
			if(mIs.equals("y") || mIs.equals("Y")){
				if(!mIsPrimaryExisting){
					addPrimaryMember();
				}else{
					System.out.println("Primary Health Supporter is already added.");
				}
			}else{
				if(!mIsSecondaryExisting){
				addSecondaryMember();
				}else{
					System.out.println("Secondary Health Supporter is already added.");
				}
			}
		}else if(mValue == 3){
			System.out.println("Which of the following do you wanna authorize as Health Supporter");
			
			int mCo = 0;
			if(mPrimaryHealthSupporter != null && !mPrimaryHealthSupporter.isEmpty()
					&& mSecondaryHealthSupporter != null && !mSecondaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mPrimaryHealthSupporter);
				System.out.println("1 For:"+mSecondaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mPrimaryHealthSupporter;
				}else{
					mSelectedSupporter = mSecondaryHealthSupporter;
				}
				authorize();
			}else if(mPrimaryHealthSupporter != null && !mPrimaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mPrimaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mPrimaryHealthSupporter;
				}
				authorize();
			}else if(mSecondaryHealthSupporter != null && !mSecondaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mSecondaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mSecondaryHealthSupporter;
				}
				authorize();
			}else{
				System.out.println("Patient does not have any supporters.");
			}
		}else if(mValue == 4){
			System.out.println("Which of the following do you wanna delete Health Supporter");
			
			int mCo = 0;
			if(mPrimaryHealthSupporter != null && !mPrimaryHealthSupporter.isEmpty()
					&& mSecondaryHealthSupporter != null && !mSecondaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mPrimaryHealthSupporter);
				System.out.println("1 For:"+mSecondaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mPrimaryHealthSupporter;
				}else{
					mSelectedSupporter = mSecondaryHealthSupporter;
				}
				delete();
			}else if(mPrimaryHealthSupporter != null && !mPrimaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mPrimaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mPrimaryHealthSupporter;
				}
				delete();
			}else if(mSecondaryHealthSupporter != null && !mSecondaryHealthSupporter.isEmpty()){
				System.out.println("0 For:"+mSecondaryHealthSupporter);
				int mIndex = mScanner.nextInt();
				if(mIndex == 0){
					mSelectedSupporter = mSecondaryHealthSupporter;
				}
				delete(); 
			}else{
				System.out.println("Patient does not have any supporters.");
			}
		}
		
		displayPatientOptions();
	}
	
	
	private void delete() {
		
		String mDelete = mSelectedSupporter;
		String mUsername = "'"+mPatientID+"'";
		String mSupporter = "'"+mDelete+"'";
		
		System.out.println(mUsername);
		System.out.println(mSupporter);
		
		if(mDelete.equals(mPrimaryHealthSupporter) || mDelete.equals(mSecondaryHealthSupporter)){
			String mQuery = "delete from SUPPORTSPATIENT where LOWER(pUsername) ="+mUsername
					+"AND LOWER(sUsername) = "+mSupporter;
			try {
				PreparedStatement mStatement = DataFactory.getConnection().prepareStatement(mQuery); 
				int mSuccess = mStatement.executeUpdate();
				if(mSuccess != -1){
					System.out.println("Health Supporter is deleted.");
				}				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			System.out.println("Select correct supporter.");
		}
		
		
	}
	public void authorize(){
		
		System.out.println("Please Enter Authorization Date."); 
		String mTemp = mScanner.next();
		
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
		
		
		Supporter mSupporter = new Supporter();
		if(mSelectedSupporter.equals(mPrimaryHealthSupporter)){
			mSupporter.setUsername(mPrimaryHealthSupporter); 
			mSupporter.setPatient(mPatientID);
			mSupporter.setAuthorizationDate(sqlDate);
			mSupporter.setPrimary(true);
		}else if(mSelectedSupporter.equals(mSecondaryHealthSupporter)){
			mSupporter.setUsername(mSecondaryHealthSupporter); 
			mSupporter.setPatient(mPatientID);
			mSupporter.setAuthorizationDate(sqlDate);
			mSupporter.setPrimary(false); 
		}
		
		
		SupporterController mSC = null;
		try {
			mSC = new SupporterController();
		} catch (ClassNotFoundException | SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean mBool = mSC.update(mSupporter);
		if(mBool){
			System.out.println("Authorized");
		}else{
			System.out.println("Error");
		}
	
	}
	
	/**
	 * 
	 */
	private void fetchAlreadyAddedSupporters() { 
		mIsPrimaryExisting = false;
		mIsSecondaryExisting = false;
		String mUsername = "'"+mPatientID+"'";
		String mTemp = "select sUsername, type from SUPPORTSPATIENT where pUsername = "+mUsername;
		
		ResultSet mRSet = null;
		try {
			Statement mStatement = DataFactory.getConnection().createStatement();
			mRSet=mStatement.executeQuery(mTemp); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			while(mRSet.next()){ 
				String mNam = mRSet.getString(1);
				String mtype = mRSet.getString(2);  
				if(mtype.equals("Y")){
					mIsPrimaryExisting = true; 
					mPrimaryHealthSupporter = mNam;
				}else{
					mIsSecondaryExisting = true;  
					mSecondaryHealthSupporter = mNam; 
				}
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 
	 */
	private void addPrimaryMember() {
			String mStr = mSelectedSupporter;
			PreparedStatement mPreparedStatement;
			try {
				mPreparedStatement = DataFactory.getConnection(). 
						prepareStatement("insert into SUPPORTSPATIENT values(?,?,?,?)");

				mPreparedStatement.setString(1, mPatientID);
				mPreparedStatement.setString(2, mStr);
				mPreparedStatement.setDate(3, null);
				mPreparedStatement.setString(4, "Y");
				int mSuccess = mPreparedStatement.executeUpdate();
				if(mSuccess != -1){
					System.out.println("Primary Health Supporter is added.");
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Supporter with same username is already existing.");
			}
	}
	
	
	/**
	 * 
	 */
	private void addSecondaryMember() {
			String mStr = mSelectedSupporter;
			PreparedStatement mPreparedStatement;
			try {
				mPreparedStatement = DataFactory.getConnection(). 
						prepareStatement("insert into SUPPORTSPATIENT values(?,?,?,?)");

				mPreparedStatement.setString(1, mPatientID);
				mPreparedStatement.setString(2, mStr);
				mPreparedStatement.setDate(3, null);
				mPreparedStatement.setString(4, "N");
				int mSuccess = mPreparedStatement.executeUpdate();
				
				if(mSuccess != -1){
					System.out.println("Secondary Health Supporter is added.");
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Supporter with same username is already existing.");	
			}

	}
	
	
	private String selectSupporter() {
		System.out.println("Select the index for particular supporter"); 
		int mIndex = mScanner.nextInt();
		return mUsersArray[mIndex]; 
 	}
	
	
	/**
	 * 
	 */
	private void fetchAllUsers() {
		String mUsername = "'"+mPatientID+"'";
		String mTemp = "select username from USERS where username <> "+mUsername;
		
		ResultSet mRSet = null;
		try {
			Statement mStatement = DataFactory.getConnection().createStatement();
			mRSet=mStatement.executeQuery(mTemp); 
		} catch (Exception e) {
			e.printStackTrace();
		} 						

		try {
			while(mRSet.next()){ 
				 mUsersArray[mCount++] = mRSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Profile
	 */
	private void patientProfileMethod() {
		System.out.println("Do you want to view it or edit it?");
		System.out.println("1 For View");
		System.out.println("2 For Edit");
		int choice = mScanner.nextInt();
		if(choice == 1){
			viewProfile();
		}else if (choice == 2){ 
			editProfile();
		}
		
	}
	private void viewProfile() {
		Patient mPatient = new Patient();
		mPatient.setUsername(mPatientID);
		List<Patient> mReturnValue = mPatientController.getByUser(mPatient); 
		
		System.out.println("Name: "+mReturnValue.get(0).getName());
		System.out.println("Gender: "+mReturnValue.get(0).getGender());
		System.out.println("Address: "+mReturnValue.get(0).getAddress());
		System.out.println("UserName: "+mReturnValue.get(0).getUsername());
		System.out.println("Contact No: "+mReturnValue.get(0).getPhoneNum());
		System.out.println("DOB: "+mReturnValue.get(0).getDob());
		System.out.println("Diagnoses: "+mReturnValue.get(0).getDiagnosis());
		
		
		
	}
	private void editProfile() {
		System.out.println("Enter Name:");
		String mName = mScanner.next();
		System.out.println("Enter Gender:");
		String mGen = mScanner.next();
		System.out.println("Enter DOB:");
		String mDateText = mScanner.next();
		System.out.println("Enter Address:");
		String mAdd = mScanner.next();
		System.out.println("Enter Phone No:");
		String mPNo = mScanner.next();
		
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date mDate = null;
		try {
			if(!mDateText.isEmpty())
			mDate = format.parse(mDateText);
		} catch (ParseException e1) {
			e1.printStackTrace(); 
		}
		java.sql.Date sqlDate = null;
		if(mDate != null)
			sqlDate = new java.sql.Date(mDate.getTime());
		
		
		Patient mPatient = new Patient(mPatientID,null,mName, sqlDate, false, null, mGen, mAdd, mPNo);
		boolean mIsSucess = mPatientController.update(mPatient);
		if(mIsSucess){
			System.out.println("Updated");
		}else{
			System.out.println("Error");
		}
		
	}
	
	private void patientAlertsMethod() {
		// TODO Auto-generated method stub
		
	}
	private void patientHIMethod() {
		// TODO Auto-generated method stub
		
	}
	private void patientDiagnosesMethod() { 
		mPatientDiagnosesActivity =	new PatientDiagnosesActivity(mPatientID);
		mPatientDiagnosesActivity.showOptionsForDiagnoses();
	}
}
