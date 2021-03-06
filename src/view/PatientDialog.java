package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author sukhdev
 *
 */

public class PatientDialog implements ActionListener
{
	JButton mProfile,mDiagnoses ,mHealthIndicators,mAlerts,mHealthSupporters,mLogout;
	JFrame mMainFrame;
	JLabel mMainLabel;
	JSeparator mSeparator;
	public PatientDialog()
	{
		mMainFrame=new JFrame("Patient Form");
		mMainFrame.setSize(500,500);

		initializeComponents(); 
		addBoundsToComponents(); 
		addComponentsToMainFrame(); 
		customizeComponents(); 

		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMainFrame.setVisible(true);
		mLogout.addActionListener(this);
		mProfile.addActionListener(this);
		mDiagnoses.addActionListener(this);
		mHealthSupporters.addActionListener(this);
		mHealthIndicators.addActionListener(this);
		mAlerts.addActionListener(this);
	}

	private void customizeComponents() {
		mProfile.setBackground(Color.gray);
		mDiagnoses .setBackground(Color.gray);
		mHealthIndicators.setBackground(Color.gray);
		mAlerts.setBackground(Color.gray);
		mHealthSupporters.setBackground(Color.gray);
		mLogout.setBackground(Color.gray);
		mProfile.setForeground(Color.blue);
		mDiagnoses .setForeground(Color.blue);
		mHealthIndicators.setForeground(Color.blue);
		mAlerts.setForeground(Color.blue);
		mHealthSupporters.setForeground(Color.blue);
		mLogout.setForeground(Color.blue);
	}

	private void addComponentsToMainFrame() {
		mMainFrame.setLayout(null);
		mMainFrame.add(mProfile);
		mMainFrame.add(mDiagnoses );
		mMainFrame.add(mHealthIndicators);
		mMainFrame.add(mMainLabel);
		mMainFrame.add(mAlerts);
		mMainFrame.add(mHealthSupporters);
		mMainFrame.add(mSeparator);
		mMainFrame.add(mLogout);

	}

	private void addBoundsToComponents() {
		mMainLabel.setBounds(100,30,300,30);
		mSeparator.setBounds(0,70,500,30);
		mProfile.setBounds(150,100,150,30);
		mDiagnoses .setBounds(150,150,150,30);
		mHealthIndicators.setBounds(150,200,150,30);
		mAlerts.setBounds(150,250,150,30);
		mHealthSupporters.setBounds(150,300,150,30);
		mLogout.setBounds(150,350,150,30);
	}

	/**
	 * 
	 */

	private void initializeComponents() {
		Font font = new Font("Verdana", Font.BOLD, 25);
		mMainLabel=new JLabel("Patient Section");
		mMainLabel.setFont(font);
		mMainLabel.setForeground(Color.blue);
		mProfile=new JButton("Profile");
		mDiagnoses =new JButton("Diagnoses");
		mHealthIndicators=new JButton("Health Indicators");
		mAlerts=new JButton("Alerts");
		mHealthSupporters=new JButton("Health Supporters");
		mSeparator=new JSeparator();
		mLogout=new JButton("Logout");

	}
	public void actionPerformed(ActionEvent ae){
		if((ae.getActionCommand()).equals("Profile")){
			mMainFrame.setVisible(false);
		}
		if((ae.getActionCommand()).equals("Diagnoses")){
			mMainFrame.setVisible(false);
		}
		if((ae.getActionCommand()).equals("Health Indicators")){
			mMainFrame.setVisible(false);
		}
		if((ae.getActionCommand()).equals("Alerts")){
			mMainFrame.setVisible(false);
		}
		if((ae.getActionCommand()).equals("Health Supporters")){
			new AddHealthSupporters();
			
		}
		if((ae.getActionCommand()).equals("Logout")){
			mMainFrame.setVisible(false);
			new LoginMainActivity();
		}

	}

	/**
	 * 
	 * @param d
	 */
	public static void main(String d[]){
		new PatientDialog();
	}


}