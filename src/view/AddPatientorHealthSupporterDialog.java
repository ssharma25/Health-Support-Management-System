package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import controllers.PatientController;
import model.DataFactory;
import model.Patient;


@SuppressWarnings("serial")
public class AddPatientorHealthSupporterDialog extends JFrame  implements ActionListener 
{
	JTextField mUserNameData,mPatientNameData,mPhoneNoData;
	JTextField mDOBData;
	JFrame mMainFrame;
	JPasswordField mPasswordData;	
	JTextArea mAddressData;
	JButton mSaveButton,mResetButton,mBackButton;
	JLabel mUserName ,mPatientName,mAddress,mPassword,mMainLabel,mGender,mPhoneNo,mApplyFor, mDOB;
	JSeparator mSeparator;
	JComboBox mGenderData,mApplyForData;
	String mGenderArray[]={"Male","Female"};
	String mUserArray[] = {"Patient","Health Supporter"};
	PreparedStatement mPreparedStatement;
	
	PatientController mPatientController;
	
	
	/**
	 * 
	 */
	public AddPatientorHealthSupporterDialog(){	
	
	mMainFrame=new JFrame("New Registration Form");
	mMainFrame.setLayout(null);
	mMainFrame.setSize(850,700); 
	setFont(new Font("verdana",3,14));
    
	initializeComponents();  
    setBoundsForComponents(); 
	customizingComponents();  
	AddingComponentsToMainFrame();
	
	mMainFrame.setVisible(true);
	
	mBackButton.addActionListener(this);
	mSaveButton.addActionListener(this);
	mResetButton.addActionListener(this);
	
	mApplyForData.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			//String log=mApplyForData.getSelectedItem().toString();
			if(e.getItem().equals("Patient")){
				makeComponentsVisible("Patient");
			}else if(e.getItem().equals("Health Supporter")){
				makeComponentsVisible("Health Supporter");
			}
			
		}
	});


	}
	
	/**
	 * 
	 * @param string
	 */
	protected void makeComponentsVisible(String string) { 
		if(string.equals("Patient")){
			mPatientName.setText("Patient Name"); 
		}else{ 
			mPatientName.setText("Supporter Name");  
		}
		
	}


	/**
	 * 
	 */
	private void customizingComponents() {
		Font font = new Font("Verdana", Font.BOLD, 25);
		mMainLabel.setFont(font);
		mApplyForData.setForeground(Color.blue);
		mMainLabel.setForeground(Color.blue);
		mGender.setForeground(Color.blue);
		mGenderData.setForeground(Color.blue);
		mApplyFor.setForeground(Color.blue);
		mPhoneNo.setForeground(Color.blue);
		mUserName.setForeground(Color.blue);
		mPassword.setForeground(Color.blue);
		mAddress.setForeground(Color.blue);
		mDOB.setForeground(Color.blue);
		mDOBData.setForeground(Color.blue);
		mPatientName.setForeground(Color.blue);
		mResetButton.setForeground(Color.blue);
		mSaveButton.setForeground(Color.blue);
		mResetButton.setBackground(Color.gray);
		mSaveButton.setBackground(Color.gray);	
		mBackButton.setForeground(Color.blue);
		mBackButton.setBackground(Color.gray);
		
	}

	/**
	 * 
	 */
	private void AddingComponentsToMainFrame() {
		mMainFrame.add(mSaveButton );
		mMainFrame.add(mApplyForData);
		mMainFrame.add(mApplyFor);
		mMainFrame.add(mBackButton);
		mMainFrame.add(mPasswordData);
		mMainFrame.add(mAddressData);
		mMainFrame.add(mPatientNameData);
		mMainFrame.add(mUserNameData);
		mMainFrame.add(mPhoneNoData);
		mMainFrame.add(mPassword);
		mMainFrame.add(mAddress);
		mMainFrame.add(mPatientName);
		mMainFrame.add(mUserName);
		mMainFrame.add(mMainLabel);
		mMainFrame.add(mSeparator);
		mMainFrame.add(mGender);
		mMainFrame.add(mGenderData);
		mMainFrame.add(mPhoneNo);
		mMainFrame.add(mResetButton);
		mMainFrame.add(mDOB);
		mMainFrame.add(mDOBData);
	}


	/**
	 * 
	 */
	private void initializeComponents() {
		mDOB = new JLabel("DOB (MM/DD/YYYY)");
		mGender=new JLabel("Gender");
		mGenderData=new JComboBox(mGenderArray); 
		mUserName = new JLabel("UserName"); 
		mApplyFor= new JLabel("Apply For:"); 
		mSeparator=new JSeparator();
		mPatientName = new JLabel("Patient Name"); 
		mAddress = new JLabel("Address");
		mPassword = new JLabel("Password"); 
		mPhoneNo = new JLabel("Mobile No");
		mMainLabel=new JLabel("New Registration");
		mUserNameData = new JTextField(); 
		mApplyForData=new JComboBox(mUserArray);
		mDOBData = new JTextField();
		mPatientNameData = new JTextField(); 
		mPhoneNoData = new JTextField();
		mAddressData = new JTextArea(10,20); 
		mPasswordData = new JPasswordField();
		mSaveButton = new JButton("Register");
		mResetButton = new JButton("Reset");
		mBackButton=new JButton("Back");
		
	}



	/**
	 * 
	 */
	private void setBoundsForComponents() {
		mSeparator.setBounds(0,100,700,150);
		mGender.setBounds(430,125,150,150);
		mSeparator.setBounds(0,100,1300,150);
		mUserName.setBounds(80,125,670,150);
		mMainLabel.setBounds(300,20,500,50);
		mPatientName.setBounds(80,235,150,20);
		mAddress.setBounds(430,230,150,20);
		mApplyFor.setBounds(80,130,400,20);
		mApplyForData.setBounds(230,130,150,20);
		mGenderData.setBounds(570,190,150,20);
		mPhoneNoData.setBounds(230,320,150,20);
		mPassword.setBounds(80,280,150,20);
		mUserNameData.setBounds(230,190,152,20);
		mPatientNameData.setBounds(230,235,152,20);
		mPhoneNo.setBounds(80,320,152,20);
		mAddressData.setBounds(570,230,152,70);
		mDOBData.setBounds(570,320,152,20);
		mDOB.setBounds(430,320,152,20);
		mPasswordData.setBounds(230,280,152,20);
		mSaveButton.setBounds(200,490,90,30);
		mResetButton.setBounds(300,490,95,30);
		mBackButton.setBounds(400,490,95,30);
		
	}



	/**
	 * 
	 */
	public void actionPerformed(ActionEvent ae){
	
		if((ae.getActionCommand()).equals("Reset")){
			mMainFrame.setVisible(false);
			new AddPatientorHealthSupporterDialog();
		}
		if((ae.getActionCommand()).equals("Register")){
			try{
				getDataAndRegisterForNew(); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(mMainFrame, "Please enter valid Information");
			}

		}
		if((ae.getActionCommand()).equals("Back")){
			mMainFrame.setVisible(false);
			new LoginMainActivity();
		}



	}
	
	private void getDataAndRegisterForNew() {
		
		String mUserID =  mUserNameData.getText();
		String mUserName = mPatientNameData.getText();
		String mPassword = mPasswordData.getText();
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date mDate = null;
		try {
			if(!mDOBData.getText().isEmpty())
			mDate = format.parse(mDOBData.getText());
		} catch (ParseException e1) {
			e1.printStackTrace(); 
		}
		java.sql.Date sqlDate = null;
		if(mDate != null)
			sqlDate = new java.sql.Date(mDate.getTime());
		
		String mAddress = mAddressData.getText();
		String mCNo = mPhoneNoData.getText();
		String mGen = mGenderData.getSelectedItem().toString();

		if(mUserID.isEmpty() && mPassword.isEmpty()){
			JOptionPane.showMessageDialog(mMainFrame, "Username and Password must not be empty.");
			return;
		}
		
		try {
			mPatientController = new PatientController(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Patient mPatient = new Patient(mUserID,mPassword,mUserName, sqlDate, false, null, mGen, mAddress, mCNo);
		boolean mIsSucess = mPatientController.add(mPatient);
		if(mIsSucess){
			JOptionPane.showMessageDialog(mMainFrame, "Patient has been added.");
			resetValues(); 
		}else{
			JOptionPane.showMessageDialog(mMainFrame, "Please try another username.");
		}
		
		
	}

	/*
	 * 
	 * 
	 */
	private void resetValues() {
		mUserNameData.setText(""); 
		mPasswordData.setText("");
		mPatientNameData.setText("");
		mPhoneNoData.setText("");
		mAddressData.setText("");
		mPasswordData.setText("");
		mDOBData.setText("");
	}

	/**
	 * 
	 * @param ars
	 */
	public static void main(String ars[]){
		new AddPatientorHealthSupporterDialog();}

}