package view;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Patient;
import model.Supporter;
import controllers.SupporterController;
import controllers.PatientController;
public class HealthSupporterMainActivity {

	SupporterController mSupporterController;
	String mSupporterID = "";
	private List<Patient> mList;
	Scanner mScanner = new Scanner(System.in);
	private int mSupporterChoice;
	private boolean mIsAuthorizedForCurrent;
	String mSelectedPatient;
<<<<<<< HEAD
	PatientController mPatientController;
=======
	private SupporterDiagnosesActivity mSupporterDiagnosesActivity;
>>>>>>> branch 'master' of https://github.ncsu.edu/wpmoore2/CSC540-DBMS-Project.git
	
	public HealthSupporterMainActivity(String mUsername) {
		mSupporterID = mUsername;
	}

	public void displaySupporterOptions() { 
		fetchAllPatients();
	}

	private void fetchAllPatients() {
		mSupporterController = null;
		try {
			mPatientController = new PatientController();
			mSupporterController = new SupporterController();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Supporter mSupporter = new Supporter();
		mSupporter.setUsername(mSupporterID);
		mList = mSupporterController.getAllPatients(mSupporter); 
		int mCount = 0;
		if(mList.isEmpty()){
			System.out.println("You are not supporting any patient!");
		}else{
			System.out.println("You are supporting the following patients:"); 
			for(Patient mP: mList){
				System.out.println(mCount+":"+mP.getUsername());
				mCount++;
			}  
			
			mSelectedPatient = selectPatient(); 
			
			
			Supporter mY = new Supporter();
			mY.setPatient(mSelectedPatient);  
			
			List<Supporter> mTList = mSupporterController.getByUser(mY);
			for(Supporter mS: mTList){
				if(mSupporterID.equals(mS.getUsername())){
	 				if(mS.getAuthorizationDate() != null){
						mIsAuthorizedForCurrent = true; 
					}
				}
			}
			
			displayAllOptions(); 
		}


	}

	private String selectPatient() {
		System.out.println("Select the index for particular patient"); 
		int mIndex = mScanner.nextInt();
		return mList.get(mIndex).getUsername();
 	}

	private void displayAllOptions() {
		System.out.println("Enter Choice");
		System.out.println("1 Profile");
		System.out.println("2 Diagnoses");
		System.out.println("3 Health Indicators");
		System.out.println("4 Alerts");
		System.out.println("5 Health Supporters");
		System.out.println("6 Logout");
		mSupporterChoice = mScanner.nextInt(); 

		switch (mSupporterChoice) {
		case 1:
			if(!mIsAuthorizedForCurrent){
				System.out.println("Not Authorized");
				return;
			}
			supporterProfileMethod();
			break;
		case 2:
			if(!mIsAuthorizedForCurrent){
				System.out.println("Not Authorized");
				return;
			}
			supporterDiagnosesMethod();
			break;
		case 3:
			if(!mIsAuthorizedForCurrent){
				System.out.println("Not Authorized"); 
				return;
			}
			supporterHIMethod();
			break;
		case 4:
			if(!mIsAuthorizedForCurrent){
				System.out.println("Not Authorized");
				return;
			}
			supporterAlertsMethod();
			break;
		case 5:
			if(!mIsAuthorizedForCurrent){
				System.out.println("Not Authorized");
				return;
			}
			supporterHSMethod();
			break; 
		case 6:
			supporterLogoutMethod(); 
			break;
		default:
			break;
	}


}

	/**
	 * 
	 */
	private void supporterLogoutMethod() {
		MainActivity.mainMethod();
		
	}

	private void supporterHSMethod() {
		// TODO Auto-generated method stub
		
	}

	private void supporterAlertsMethod() {
		// TODO Auto-generated method stub
		
	}

	private void supporterHIMethod() {
		// TODO Auto-generated method stub
		
	}

	private void supporterDiagnosesMethod() {
		mSupporterDiagnosesActivity = new SupporterDiagnosesActivity(mSupporterID);   
		System.out.println("Do you want to add or delete the diagnosis?");
		System.out.println("1 For add");
		System.out.println("2 For delete");
		int choice = mScanner.nextInt();
		if(choice == 1){
			mSupporterDiagnosesActivity.addDisease();
		}else if (choice == 2){ 
			mSupporterDiagnosesActivity.removeDisease();
		}
		displayAllOptions();
		
	}

	private void supporterProfileMethod() {
		System.out.println("Do you want to view or edit the patient profile or own profile?");
		System.out.println("1 For Own Profile");
		System.out.println("2 For Patient Profile");
		int choice = mScanner.nextInt();
		if(choice == 1){
			viewOrEditOwnProfile();
		}else if (choice == 2){ 
			viewOrEditPatientProfile(); 
		}
		
	}

	private void viewOrEditPatientProfile() {
		// TODO Auto-generated method stub
		System.out.println("Do you want to view it or edit it?");
		System.out.println("1 For View");
		System.out.println("2 For Edit");
		int choice = mScanner.nextInt();
		if(choice == 1){
			viewPatientProfile();
		}else if (choice == 2){ 
			editPatientProfile();
		}
				
	}

	public void viewPatientProfile()
	{
		Patient sPatient = new Patient();
		sPatient.setUsername(mSelectedPatient);
		List<Patient> mReturnValue = mPatientController.getByUser(sPatient); 
		System.out.println(sPatient.getUsername());
		System.out.println("Name: "+mReturnValue.get(0).getName());
		System.out.println("Gender: "+mReturnValue.get(0).getGender());
		System.out.println("Address: "+mReturnValue.get(0).getAddress());
		System.out.println("UserName: "+mReturnValue.get(0).getUsername());
		System.out.println("Contact No: "+mReturnValue.get(0).getPhoneNum());
		System.out.println("DOB: "+mReturnValue.get(0).getDob());
		System.out.println("Diagnoses: "+mReturnValue.get(0).getDiagnosis());
		
	}
	public void editPatientProfile()
	{
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
		
		
		Patient sPatient = new Patient(mSelectedPatient,null,mName, sqlDate, false, null, mGen, mAdd, mPNo);
		boolean sIsSucess = mPatientController.update(sPatient);
		if(sIsSucess){
			System.out.println("Updated");
		}else{
			System.out.println("Error");
		}
	}
	
	private void viewOrEditOwnProfile() {
		// TODO Auto-generated method stub
		System.out.println("Do you want to view it or edit it?");
		System.out.println("1 For View");
		System.out.println("2 For Edit");
		int choice = mScanner.nextInt();
		if(choice == 1){
			viewOwnProfile();
		}else if (choice == 2){ 
			editOwnProfile();
		}
	}
	public void viewOwnProfile()
	{
		Patient sSupporter = new Patient();
		sSupporter.setUsername(mSupporterID);
		List<Patient> mReturnValue = mPatientController.getByUser(sSupporter); 
		
		System.out.println("Name: "+mReturnValue.get(0).getName());
		System.out.println("UserName: "+mReturnValue.get(0).getUsername());
		System.out.println("DOB: "+mReturnValue.get(0).getDob());
		
	}
	public void editOwnProfile()
	{
		System.out.println("Enter Name:");
		String mName = mScanner.next();
		System.out.println("Enter DOB:");
		String mDateText = mScanner.next();
		
		
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
		
		
		Patient sSupporter = new Patient(mSupporterID,null,mName, sqlDate, false, null, null, null, null);
		boolean sIsSucess = mPatientController.update(sSupporter);
		if(sIsSucess){
			System.out.println("Updated");
		}else{
			System.out.println("Error");
		}
	}
	
}
