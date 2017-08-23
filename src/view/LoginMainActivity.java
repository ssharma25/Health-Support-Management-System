package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.DataFactory;
import model.Patient;
import model.Supporter;
import model.User;

import java.sql.*;


@SuppressWarnings("serial")
public class LoginMainActivity  extends JFrame implements ActionListener 
{
	
	public static User sUser;
	public static Patient sPatient;
	public static Supporter sSupporter;
	
	JTextField mUserNameData;
	JPasswordField mPasswordData;
	JFrame mMainFrame;
	JButton mLoginButton,mNewButton;
	JLabel mUserName,mPassword,mLoginAs,mMainLabel,mNotaMember;
	@SuppressWarnings("rawtypes")
	JComboBox mUserTypesData;
	Connection mConnection;	
	ResultSet mResultSet;
	PreparedStatement mPreparedStatement;
	JSeparator mSeparator;
	Statement mStatement;;
	String mUserTypes[]={"Patient","Health Supporter"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginMainActivity(){             

		mConnection  = createConnectionToJavaDB();
		if(mConnection == null){
				return;
		}
		mMainFrame=new JFrame("Login Form");
		mMainFrame.setLayout(null);
		mMainFrame.setSize(700,700);
		
		initializeComponents(); 
		customizeComponents();  	
		setBoundsforComponents(); 
		addingComponentstoMainFrame();
		
		mMainFrame.setVisible(true);
		mUserTypesData.addActionListener(this);
		mLoginButton.addActionListener(this);
		mNewButton.addActionListener(this);			

	}

	/**
	 * 
	 */
	
	private void customizeComponents() {
		Font font = new Font("Verdana", Font.BOLD, 25);
		mMainLabel.setFont(font);
		mMainLabel.setForeground(Color.blue);
		mNotaMember.setForeground(Color.blue);
		mLoginAs.setForeground(Color.blue);
		mPassword.setForeground(Color.blue);
		mUserName.setForeground(Color.blue);
		mUserTypesData.setForeground(Color.blue);
		mMainLabel.setForeground(Color.blue);
		mNewButton.setForeground(Color.blue);
		mLoginButton.setForeground(Color.blue);
		mNewButton.setBackground(Color.gray);
		mLoginButton.setBackground(Color.gray);
	}

	/**
	 * 
	 */
	private void initializeComponents() {
		mUserTypesData=new JComboBox(mUserTypes); 		
		mUserName = new JLabel("UserID"); 
		mSeparator=new JSeparator();
		mPassword = new JLabel("Pasword:"); 
		mLoginAs=new JLabel("Login As:");        
		mMainLabel=new JLabel("Health Management System");
		mNotaMember =new JLabel("Not a Member:");
		mUserNameData = new JTextField(); 
		mPasswordData = new JPasswordField(); 
		mLoginButton = new JButton("Login");
		mNewButton = new JButton("New Member");
	}


	/**
	 * 
	 */
	private void addingComponentstoMainFrame() {
		mMainFrame.add(mNotaMember);
		mMainFrame.add(mMainLabel);
		mMainFrame.add(mSeparator);		
		mMainFrame.add(mNewButton);
		mMainFrame.add(mLoginButton );
		mMainFrame.add(mPasswordData);
		mMainFrame.add(mUserNameData);
		mMainFrame.add(mLoginAs);
		mMainFrame.add(mUserTypesData);	
		mMainFrame.add(mPassword);
		mMainFrame.add(mUserName);

	}

	/**
	 * 
	 */
	private void setBoundsforComponents() {
		mMainLabel.setBounds(120,20,500,50);
		mSeparator.setBounds(0,100,700,150);
		mUserTypesData.setBounds(220,195,150,20);
		mLoginAs.setBounds(80,195,150,20);
		mUserName.setBounds(80,235,150,20);
		mPassword.setBounds(80,280,150,20);
		mUserNameData.setBounds(220,235,152,26);
		mPasswordData.setBounds(220,275,152,26);
		mLoginButton.setBounds(250,320,90,30);
		mNewButton.setBounds(500,120,150,20);
		mNotaMember.setBounds(400,120,150,20);
	}

	/**
	 * 
	 */

	public void actionPerformed(final ActionEvent ae)
	{
		String mUsertype=mUserTypesData.getSelectedItem().toString();
		String mUsername = "";
		String mTemp = "";
		if(ae.getActionCommand()=="Login"){ 
			String mPass = "";
			mUsername = mUserNameData.getText(); 
			mUsername = "'"+mUsername+"'";
			mTemp = "select password from USERS "
					+ "where username ="
					+ ""+mUsername;

			mResultSet = executeSQLQuery(mTemp);  

			try {
				while(mResultSet.next()){ 
					mPass=mResultSet.getString("PASSWORD");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String pas=mPasswordData.getText();
			if(pas.equals(mPass))
			{
				sUser = new User(mUserNameData.getText(),null,null, null){}; 
				mMainFrame.setVisible(false);
				if(mUsertype.equals("Patient")){
					
					new PatientDialog();	
				}else if(mUsertype.equals("Health Supporter")){
					new HealthSupporterDialog();
				}
			}else{
				JOptionPane.showMessageDialog(mMainFrame, "Incorrect username or password");

			}
		}
		if(ae.getActionCommand()=="New Member"){ 
			mMainFrame.setVisible(false);
			new AddPatientorHealthSupporterDialog();
		}
	}


	/**
	 * 
	 * @param mTemp
	 * @return
	 */
	private ResultSet executeSQLQuery(String mTemp) {
		// TODO Auto-generated method stub
		ResultSet mRSet = null;
		try {
			mStatement=mConnection.createStatement();
			mRSet=mStatement.executeQuery(mTemp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return mRSet;
	}


	/**
	 * @return Connection
	 * 
	 */
	private Connection createConnectionToJavaDB() { 

		Connection conn = null;
		try {
			try {
				conn = DataFactory.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	/**
	 * 
	 * @param ars
	 */
	public static void main(String ars[])
	{
		new LoginMainActivity();
	}

}