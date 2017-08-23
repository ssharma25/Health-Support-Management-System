package view;
import javax.swing.*;

import model.DataFactory;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author sukhdev
 *
 */

public class AddHealthSupporters implements ActionListener
{
	JButton mProfile,mAdd, mLogout, mDelete;
	JFrame mMainFrame;
	JLabel mMainLabel;
	JSeparator mSeparator;
	JCheckBox mIsPrimary;
	
	JComboBox<String> mUsersData;
	JLabel mPrimary, mPrimaryData;
	JLabel mSecondary, mSecondaryData;
	
	String [] mUsersArray = new String[100];
	int mCount = 0;
	
	
	public AddHealthSupporters()
	{
		mMainFrame=new JFrame("Health Supporters");
		mMainFrame.setSize(500,500);

		fetchAllUsers();
		initializeComponents(); 
		addBoundsToComponents(); 
		addComponentsToMainFrame(); 
		customizeComponents();
		fetchAlreadyAddedSupporters(); 
	
		

		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMainFrame.setVisible(true);
		mLogout.addActionListener(this);
		mAdd.addActionListener(this);
		mDelete.addActionListener(this);
	}

	/**
	 * 
	 */
	private void fetchAlreadyAddedSupporters() {
		//String mUsername=""; 
		String mUsername = "'"+LoginMainActivity.sUser.getUsername()+"'";
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
				System.out.println(mNam); 
				if(mtype.equals("Y")){
					mPrimaryData.setText(mNam);
				}else{
					mSecondaryData.setText(mNam); 
				}
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * 
	 */
	private void fetchAllUsers() {
		//String mUsername = "";
		String mUsername = "'"+LoginMainActivity.sUser.getUsername()+"'";
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

	private void customizeComponents() {
		
		mUsersData.setBackground(Color.gray);
		mUsersData.setForeground(Color.blue);
		
		mPrimary.setBackground(Color.gray);
		mPrimary.setForeground(Color.blue);
		
		mSecondary.setBackground(Color.gray);
		mSecondary.setForeground(Color.blue);
		
		mProfile.setBackground(Color.gray);
		mProfile.setForeground(Color.blue);
		
		mAdd.setBackground(Color.gray);
		mAdd.setForeground(Color.blue);
		
		mDelete.setBackground(Color.gray);
		mDelete.setForeground(Color.blue);
		
		mLogout.setForeground(Color.blue);
		mLogout.setBackground(Color.gray);
	}

	private void addComponentsToMainFrame() {
		mMainFrame.setLayout(null);
		mMainFrame.add(mUsersData);
		mMainFrame.add(mPrimary);
		mMainFrame.add(mSecondary);
		mMainFrame.add(mAdd);
		mMainFrame.add(mDelete);
		mMainFrame.add(mLogout);
		mMainFrame.add(mIsPrimary);
		mMainFrame.add(mPrimaryData);
		mMainFrame.add(mSecondaryData);

	}

	private void addBoundsToComponents() {

		mUsersData.setBounds(50,100,150,30);
		
		mPrimary.setBounds(50,150,300,30);
		mPrimaryData.setBounds(400,150,300,30);
		mSecondary.setBounds(50,200 ,300,30);
		mSecondaryData.setBounds(400,200 ,300,30);
		
		mIsPrimary.setBounds(50,250 ,300,30);
		mMainLabel.setBounds(100,30,300,30);
		mDelete.setBounds(340,100,100,30);
		mAdd.setBounds(220,100,100,30);
		mLogout.setBounds(100,400,300,30);

	}

	/**
	 * 
	 */

	private void initializeComponents() {
		Font font = new Font("Verdana", Font.BOLD, 25);
		mMainLabel=new JLabel("HealthSupporters");
		mMainLabel.setFont(font);
		mMainLabel.setForeground(Color.blue);
		
		mIsPrimary = new JCheckBox("Is Primary");
		mPrimary = new JLabel("Primary Health Supporter");
		mSecondary = new JLabel("Secondary Health Supporter");
		mUsersData = new JComboBox<String>(mUsersArray);
		mPrimaryData = new JLabel();
		mSecondaryData = new JLabel();
		
		mProfile=new JButton("Profile");
		mAdd =new JButton("Add");
		mDelete =new JButton("Delete");
		mLogout=new JButton("Dismiss");

	}
	public void actionPerformed(ActionEvent ae){
		
		if((ae.getActionCommand()).equals("Add")){
			if(mIsPrimary.isSelected()){
				addPrimaryMember();
			}else{
				addSecondaryMember(); 
			}
			
		}else if((ae.getActionCommand()).equals("Dismiss")){
			mMainFrame.setVisible(false); 
		}else if((ae.getActionCommand()).equals("Delete")){
			deleteSupporter(); 
		}

	}

	
	private void deleteSupporter() {
		
		String mDelete = mUsersData.getSelectedItem().toString();
		String mUsername = "'"+LoginMainActivity.sUser.getUsername()+"'";
		String mSupporter = "'"+mDelete+"'";
		
		System.out.println(mUsername);
		System.out.println(mSupporter);
		
		if(mDelete.equals(mPrimaryData.getText()) || mDelete.equals(mSecondaryData.getText())){
			String mQuery = "delete from SUPPORTSPATIENT where LOWER(pUsername) ="+mUsername
					+"AND LOWER(sUsername) = "+mSupporter;
			try {
				PreparedStatement mStatement = DataFactory.getConnection().prepareStatement(mQuery); 
				int mSuccess = mStatement.executeUpdate();
				if(mSuccess != -1){
					JOptionPane.showMessageDialog(mMainFrame, "Health Supporter is deleted.");
					mMainFrame.setVisible(false);
					new AddHealthSupporters();
				}				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			JOptionPane.showMessageDialog(mMainFrame, "Select correct supporter.");
		}
		
	}

	/**
	 * 
	 */
	private void addSecondaryMember() {

		if(mSecondaryData.getText().isEmpty()){
			String mStr = mUsersData.getSelectedItem().toString();
			PreparedStatement mPreparedStatement;
			try {
				mPreparedStatement = DataFactory.getConnection(). 
						prepareStatement("insert into SUPPORTSPATIENT values(?,?,?,?)");

				mPreparedStatement.setString(1, LoginMainActivity.sUser.getUsername());
				mPreparedStatement.setString(2, mStr);
				mPreparedStatement.setDate(3, null);
				mPreparedStatement.setString(4, "N");
				int mSuccess = mPreparedStatement.executeUpdate();
				
				if(mSuccess != -1){
					JOptionPane.showMessageDialog(mMainFrame, "Secondary Health Supporter is added.");
					mMainFrame.setVisible(false);
					new AddHealthSupporters();
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(mMainFrame, "Supporter with same username is already added.");	
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(mMainFrame, "Secondary Supporter is already added.");	
		}

	}
	
	/**
	 * 
	 */
	private void addPrimaryMember() {
		if(mPrimaryData.getText().isEmpty()){
			String mStr = mUsersData.getSelectedItem().toString();
			PreparedStatement mPreparedStatement;
			try {
				mPreparedStatement = DataFactory.getConnection(). 
						prepareStatement("insert into SUPPORTSPATIENT values(?,?,?,?)");

				mPreparedStatement.setString(1, LoginMainActivity.sUser.getUsername());
				mPreparedStatement.setString(2, mStr);
				mPreparedStatement.setDate(3, null);
				mPreparedStatement.setString(4, "Y");
				int mSuccess = mPreparedStatement.executeUpdate();
				if(mSuccess != -1){
					JOptionPane.showMessageDialog(mMainFrame, "Primary Health Supporter is added.");
					mMainFrame.setVisible(false);
					new AddHealthSupporters();
				}

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(mMainFrame, "Supporter with same username is already added.");
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(mMainFrame, "Primary Supporter is already added.");	
		}
	}

	/**
	 * 
	 * @param d
	 */
	public static void main(String d[]){
		new AddHealthSupporters();
	}


}