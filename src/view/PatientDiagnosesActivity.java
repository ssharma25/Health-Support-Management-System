package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.DiagnosesController;
import controllers.PatientController;
import model.Diagnoses;

public class PatientDiagnosesActivity {

	String mPatientID;
	Scanner mScanner = new Scanner(System.in);
	List<Diagnoses> mDiagnoses = new ArrayList();
	private DiagnosesController mDiagnosisController;
	private PatientController mPatientController;
	
	public PatientDiagnosesActivity(String mPatientID) { 
		this.mPatientID = mPatientID;
		try {
			mDiagnosisController = new DiagnosesController();
			mPatientController = new PatientController(); 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * 
	 */
	public void showOptionsForDiagnoses() { 
		System.out.println("Select any of the following options");
		System.out.println("1: Add the Diagnoses");
		System.out.println("2: Delete the Diagnoses");
		System.out.println("3: View the Diagnoses");
		
		int mChoice = mScanner.nextInt();
		
		switch (mChoice) {
		case 1:
			addDiagnoses(); 
			break;
		case 2:
			deleteDiagnosis();
			break;
		case 3:
			viewDiagnosis(); 
			break;
		default:
			break;
		}
	}

	private void viewDiagnosis() {
		
	}

	/**
	 * 
	 */
	private void deleteDiagnosis() {
				
	}

	/**
	 * 
	 */
	private void addDiagnoses() {
		mDiagnoses = mDiagnosisController.getByUser(new Diagnoses());
		if(mDiagnoses.isEmpty()){
			System.out.println("No disease in record!!!!");
		}
		else{
			System.out.println("Select index for particular disease:"); 
			int mCo = 0;
			for(Diagnoses mD : mDiagnoses){
				System.out.println(mCo+":"+mD.getName()); 
				mCo++;
			}

			int mIndex = mScanner.nextInt();
			System.out.println("Enter date for Diagnosis");

			String mDa = mScanner.next();
			java.sql.Date date = MainActivity.getSQLDate(mDa);		

			boolean mBool = mPatientController.
					addDiagnoses(Integer.valueOf(mDiagnoses.get(mIndex).getDiagnosisID()), mPatientID, date);
			if(mBool){
				System.out.println("Diagnosis Added");
			}else{
				System.out.println("Already There...");
			}
		}
	}
	
}
