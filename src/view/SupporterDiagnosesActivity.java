package view;

import java.util.Scanner;

public class SupporterDiagnosesActivity {

	private String mSupporterID;
	private Scanner mScanner = new Scanner(System.in);
	public SupporterDiagnosesActivity(String mSupporterID) { 
		this.mSupporterID = mSupporterID;
	}

	public void addDisease() { 
		
		System.out.println("Do you wanna add disease for Patient or for System");
		System.out.println("Select 1 for Patient");
		System.out.println("Select 2 for System"); 
		int mChoice  = mScanner.nextInt();
		switch(mChoice){
		case 1:
			System.out.println("Do you wanna add or delete for Patient?");
			System.out.println("Select 1 for Add");
			System.out.println("Select 2 for Deletion");
			int mValue = mScanner.nextInt();
			patientMethod(mValue); 
			break;
		case 2:
			System.out.println("Do you wanna add or delete for System?");
			System.out.println("Select 1 for Add");
			System.out.println("Select 2 for Deletion");
			int mValue1 = mScanner.nextInt();
			systemMethod(mValue1);
			break;
		default:
			break;

		}
	}

	private void patientMethod(int mValue) { 
		if(mValue == 1){
			patientDiseaseAddition();
		}else if(mValue == 2){
			patientDiseaseDeletion(); 
		}
	}
	
	
	private void systemMethod(int mValue) { 
		if(mValue == 1){
			systemDiseaseAddition();
		}else if(mValue == 2){
			systemDiseaseDeletion(); 
		}
	}

	private void patientDiseaseDeletion() {
		
	}

	private void patientDiseaseAddition() {
		
	}
	
	private void systemDiseaseDeletion() {
		
	}

	private void systemDiseaseAddition() {
		
	}

	public void removeDisease() { 
		// TODO Auto-generated method stub
		
	}

}
