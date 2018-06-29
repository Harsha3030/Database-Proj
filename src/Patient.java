import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; 
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

/* @author Harsha Vardhan Reddy Nallagatla  SIU 853965332*/

public class Patient {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Patient window = new Patient();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection connection=null;

	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldPatient;
	private JTextField textFieldSSN;
	private JTextField textFieldBirthDate;
	private JTextField textFieldAddress;
	private JTextField textFieldPPSSN;
	private JTextField textFieldDSSN;
	private JTextField textFieldName;
	private JTextField textFieldSpeciality;
	private JTextField textFieldYearsofExp;
	private JTextField textFieldPreID;
	private JTextField textFieldStatus;
	private JTextField textFieldPick_Off_Time;
	private JTextField textFieldDrop_Off_Time;
	private JTextField textFieldPDSSN;
	private JTextField textFieldPreDate;
	private JTextField textFieldQuantity;
	private JTextField textFieldTradeName;
	private JTextField textFieldPharmCoName;
	private JTextField textFieldPaSSN;


	public void refreshPatient(){
		try
		{  
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(" select ssn as PatientSSN,name as PatientName,birth_date,address,phy_ssn as DoctorSSn from pri_phy_patient");  
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			rs.close();

		}
		catch(Exception e1)
		{ 
			JOptionPane.showMessageDialog(null,e1);
		}  

	}
	
	public void refreshDoctor(){
		try
		{  
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(" select ssn as DoctorSSN,name as DoctorName,specialty as speciality,yearsofexperience from doctor");  
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			rs.close();

		}
		catch(Exception e1)
		{ 
			JOptionPane.showMessageDialog(null,e1);
		}  

	}
	
	public void refreshPresc(){
		try
		{  
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(" select * from prescription");  
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			rs.close();

		}
		catch(Exception e1)
		{ 
			JOptionPane.showMessageDialog(null,e1);
		}  
	}
	
	
	public Patient() 
	{
		connection=connect.dbconnector();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(100, 100, 1452, 718);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(693, 134, 667, 450);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton Button2 = new JButton("Patients & Prescriptions");
		Button2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		Button2.setBounds(10, 160, 217, 30);
		frame.getContentPane().add(Button2);
		Button2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)  /* Creates a list with patients and their prescriptions*/
			{
				try
				{  

					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select ph.SSN,ph.name as PatientName,ph.birth_date,ph.address,ph.phy_ssn as DoctorSSN,p.pre_id,p.status,p.pick_up_time,p.drop_off_time,p.pre_date,p.quantity,p.trade_name as drug,p.pharm_co_name from pri_phy_patient ph left join prescription p on ph.ssn=p.ssn");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  

			}
		});

		JButton Button1 = new JButton("Distinct Patients");
		Button1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		Button1.setBounds(20, 119, 191, 30);
		frame.getContentPane().add(Button1);
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)    /* Creates a list of distinct patients*/
			{
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery(" select Distinct name as PatientName,birth_date,address,phy_ssn as DoctorSSN from pri_phy_patient");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  


			}
		});


		JLabel lblNewLabel = new JLabel(" Patient Centered Functions");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 27));
		lblNewLabel.setBounds(707, 30, 442, 43);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPatientName = new JLabel("Name");
		lblPatientName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPatientName.setBounds(34, 289, 86, 37);
		frame.getContentPane().add(lblPatientName);

		textFieldPatient = new JTextField();
		textFieldPatient.setBounds(130, 298, 86, 20);
		frame.getContentPane().add(textFieldPatient);
		textFieldPatient.setColumns(10);

		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSsn.setBounds(35, 267, 46, 14);
		frame.getContentPane().add(lblSsn);

		textFieldSSN = new JTextField();
		textFieldSSN.setBounds(130, 265, 86, 20);
		frame.getContentPane().add(textFieldSSN);
		textFieldSSN.setColumns(10);

		JLabel lblBirthdate = new JLabel("Birth_Date");
		lblBirthdate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblBirthdate.setBounds(35, 342, 72, 14);
		frame.getContentPane().add(lblBirthdate);

		textFieldBirthDate = new JTextField();
		textFieldBirthDate.setBounds(129, 340, 86, 20);
		frame.getContentPane().add(textFieldBirthDate);
		textFieldBirthDate.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblAddress.setBounds(34, 379, 59, 14);
		frame.getContentPane().add(lblAddress);

		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(129, 377, 86, 20);
		frame.getContentPane().add(textFieldAddress);
		textFieldAddress.setColumns(10);

		JLabel lblPhyssn = new JLabel("Phy_SSN");
		lblPhyssn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPhyssn.setBounds(34, 421, 67, 14);
		frame.getContentPane().add(lblPhyssn);

		textFieldPPSSN = new JTextField();
		textFieldPPSSN.setBounds(129, 419, 86, 20);
		frame.getContentPane().add(textFieldPPSSN);
		textFieldPPSSN.setColumns(10);

		JButton btnAdd = new JButton("Add Patient");
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) /* Method to create add function for patients*/
			{
				try
				{  
					String sql="insert into pri_phy_patient values(?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.setString(1, textFieldSSN.getText());
					pst.setString(2, textFieldPatient.getText());
					pst.setString(3, textFieldBirthDate.getText());
					pst.setString(4, textFieldAddress.getText());
					pst.setString(5, textFieldPPSSN.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data added");	
					pst.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		

				refreshPatient();

			}
		});
		btnAdd.setBounds(44, 507, 146, 30);
		frame.getContentPane().add(btnAdd);
		
		JButton btnDeletePatient = new JButton("Delete Patient");
		btnDeletePatient.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) /* Method to delete a patient*/
			{
				try
				{  
					String sql="delete from pri_phy_patient where ssn='"+textFieldSSN.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data deleted");
					pst.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		

				refreshPatient();
			}
		});
		btnDeletePatient.setBounds(44, 561, 146, 23);
		frame.getContentPane().add(btnDeletePatient);

		JLabel lblSsn_1 = new JLabel("SSN");
		lblSsn_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSsn_1.setBounds(243, 268, 46, 14);
		frame.getContentPane().add(lblSsn_1);

		textFieldDSSN = new JTextField();
		textFieldDSSN.setBounds(325, 261, 86, 20);
		frame.getContentPane().add(textFieldDSSN);
		textFieldDSSN.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(243, 301, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		textFieldName = new JTextField();
		textFieldName.setBounds(325, 298, 86, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Speciality");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_2.setBounds(243, 343, 72, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblYearsOfExp = new JLabel("Years Of Exp");
		lblYearsOfExp.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblYearsOfExp.setBounds(243, 380, 86, 14);
		frame.getContentPane().add(lblYearsOfExp);

		textFieldSpeciality = new JTextField();
		textFieldSpeciality.setBounds(325, 340, 86, 20);
		frame.getContentPane().add(textFieldSpeciality);
		textFieldSpeciality.setColumns(10);

		textFieldYearsofExp = new JTextField();
		textFieldYearsofExp.setBounds(325, 377, 86, 20);
		frame.getContentPane().add(textFieldYearsofExp);
		textFieldYearsofExp.setColumns(10);

		JButton btnNewButton = new JButton("Add Doctor");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{  
					String sql="insert into doctor values(?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.setString(1, textFieldDSSN.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldSpeciality.getText());
					pst.setString(4, textFieldYearsofExp.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data added");
					pst.close();
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refreshDoctor();
			}
		});
		btnNewButton.setBounds(266, 507, 130, 30);
		frame.getContentPane().add(btnNewButton);

		JButton btnDeleteDoctor = new JButton("Delete Doctor");
		btnDeleteDoctor.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDeleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)			 /* Method to delete a Doctor*/
			{
				try
				{  
					String sql="delete from doctor where ssn='"+textFieldDSSN.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data deleted");
					pst.close();
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refreshDoctor();
			}
		});
		btnDeleteDoctor.setBounds(266, 566, 130, 23);
		frame.getContentPane().add(btnDeleteDoctor);

		JButton btnListOfDoctors = new JButton("List Of Doctors");
		btnListOfDoctors.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnListOfDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)		/* Creates a list of Doctors*/
			{
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery(" select ssn as DoctorSSN,name as DoctorName,specialty as speciality,yearsofexperience from doctor");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  


			}
		});
		btnListOfDoctors.setBounds(250, 142, 146, 30);
		frame.getContentPane().add(btnListOfDoctors);

		JLabel lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setFont(new Font("Consolas", Font.BOLD, 19));
		lblPatientDetails.setBounds(41, 208, 150, 14);
		frame.getContentPane().add(lblPatientDetails);

		JLabel lblDoctorDetails = new JLabel("Doctor Details");
		lblDoctorDetails.setFont(new Font("Consolas", Font.BOLD, 19));
		lblDoctorDetails.setBounds(245, 208, 140, 14);
		frame.getContentPane().add(lblDoctorDetails);

		JLabel lblPrescriptionDetails = new JLabel("Prescription Details");
		lblPrescriptionDetails.setFont(new Font("Consolas", Font.BOLD, 19));
		lblPrescriptionDetails.setBounds(435, 208, 207, 14);
		frame.getContentPane().add(lblPrescriptionDetails);

		JLabel lblPreid = new JLabel("Pre_ID");
		lblPreid.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPreid.setBounds(435, 264, 46, 14);
		frame.getContentPane().add(lblPreid);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(435, 301, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Pick_Up_Time");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_4.setBounds(436, 343, 86, 14);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblDropofftime = new JLabel("Drop_Off_Time");
		lblDropofftime.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblDropofftime.setBounds(436, 380, 92, 14);
		frame.getContentPane().add(lblDropofftime);

		JLabel lblSsn_2 = new JLabel("SSN");
		lblSsn_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSsn_2.setBounds(435, 422, 46, 14);
		frame.getContentPane().add(lblSsn_2);

		JLabel lblPhySsn = new JLabel("Phy SSN");
		lblPhySsn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPhySsn.setBounds(435, 458, 87, 14);
		frame.getContentPane().add(lblPhySsn);

		JLabel lblPre = new JLabel("Pre_Date");
		lblPre.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPre.setBounds(435, 496, 59, 14);
		frame.getContentPane().add(lblPre);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblQuantity.setBounds(435, 530, 59, 14);
		frame.getContentPane().add(lblQuantity);

		JLabel lblTradeName = new JLabel("Trade Name");
		lblTradeName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTradeName.setBounds(435, 570, 87, 14);
		frame.getContentPane().add(lblTradeName);

		JLabel lblPharmCoName = new JLabel("Pharm Co Name");
		lblPharmCoName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPharmCoName.setBounds(436, 610, 105, 14);
		frame.getContentPane().add(lblPharmCoName);

		textFieldPreID = new JTextField();
		textFieldPreID.setBounds(568, 265, 86, 20);
		frame.getContentPane().add(textFieldPreID);
		textFieldPreID.setColumns(10);

		textFieldStatus = new JTextField();
		textFieldStatus.setText("");
		textFieldStatus.setBounds(568, 298, 86, 20);
		frame.getContentPane().add(textFieldStatus);
		textFieldStatus.setColumns(10);

		textFieldPick_Off_Time = new JTextField();
		textFieldPick_Off_Time.setBounds(568, 340, 86, 20);
		frame.getContentPane().add(textFieldPick_Off_Time);
		textFieldPick_Off_Time.setColumns(10);

		textFieldDrop_Off_Time = new JTextField();
		textFieldDrop_Off_Time.setBounds(568, 377, 86, 20);
		frame.getContentPane().add(textFieldDrop_Off_Time);
		textFieldDrop_Off_Time.setColumns(10);

		textFieldPaSSN = new JTextField();
		textFieldPaSSN.setBounds(568, 419, 86, 20);
		frame.getContentPane().add(textFieldPaSSN);
		textFieldPaSSN.setColumns(10);
		
		textFieldPDSSN = new JTextField();
		textFieldPDSSN.setBounds(568, 456, 86, 20);
		frame.getContentPane().add(textFieldPDSSN);
		textFieldPDSSN.setColumns(10);

		textFieldPreDate = new JTextField();
		textFieldPreDate.setText("");
		textFieldPreDate.setBounds(568, 490, 86, 20);
		frame.getContentPane().add(textFieldPreDate);
		textFieldPreDate.setColumns(10);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(568, 528, 86, 20);
		frame.getContentPane().add(textFieldQuantity);
		textFieldQuantity.setColumns(10);

		textFieldTradeName = new JTextField();
		textFieldTradeName.setBounds(568, 568, 86, 20);
		frame.getContentPane().add(textFieldTradeName);
		textFieldTradeName.setColumns(10);

		textFieldPharmCoName = new JTextField();
		textFieldPharmCoName.setBounds(568, 608, 86, 20);
		frame.getContentPane().add(textFieldPharmCoName);
		textFieldPharmCoName.setColumns(10);

		JButton btnAddPrescription = new JButton("Add Prescription");
		btnAddPrescription.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAddPrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 				/* Method to add a new prescription */
			{
				try
				{  
					String sql="insert into prescription values(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.setString(1, textFieldPreID.getText());
					pst.setString(2, textFieldStatus.getText());
					pst.setString(3,textFieldPick_Off_Time.getText());
					pst.setString(4, textFieldDrop_Off_Time.getText());
					pst.setString(5, textFieldPaSSN.getText());
					pst.setString(6, textFieldPDSSN.getText());
					pst.setString(7, textFieldPreDate.getText());
					pst.setString(8, textFieldQuantity.getText());
					pst.setString(9, textFieldTradeName.getText());
					pst.setString(10,textFieldPharmCoName.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data added");
					pst.close();
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refreshPresc();
			}
		});
		btnAddPrescription.setBounds(690, 606, 147, 23);
		frame.getContentPane().add(btnAddPrescription);

		JButton btnDeletePrescription = new JButton("Delete Prescription");
		btnDeletePrescription.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDeletePrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 					/* Method to delete a prescription*/
			{
				try
				{  
					String sql="delete from prescription where pre_id=?";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.setString(1, textFieldPreID.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data deleted");
					pst.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refreshPresc();
			}
		});
		btnDeletePrescription.setBounds(873, 606, 164, 23);
		frame.getContentPane().add(btnDeletePrescription);
		
		JButton btnPatientEnquiry = new JButton("Patient Enquiry");
		btnPatientEnquiry.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnPatientEnquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 				/* To enquire a patient's drop off time, pick up time and status*/
			{
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery(" select p.drop_off_time,p.pick_up_time,p.status"
							+ " from pri_phy_patient ph,prescription p"
							+ " where p.ssn=ph.ssn and ph.name='"+textFieldPatient.getText()+"' and ph.Birth_Date='"+textFieldBirthDate.getText()+"' ");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  
			}
		});
		btnPatientEnquiry.setBounds(44, 606, 146, 23);
		frame.getContentPane().add(btnPatientEnquiry);
		
		JLabel lblNewLabel_5 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/People-Doctor-Male-icon.png")).getImage();
		lblNewLabel_5.setIcon(new ImageIcon(img));
		lblNewLabel_5.setBounds(295, 25, 59, 106);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/Patients-icon.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(83, 54, 52, 52);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/prescription-icon.png")).getImage();
		label_1.setIcon(new ImageIcon(img2));
		label_1.setBounds(500, 43, 78, 65);
		frame.getContentPane().add(label_1);
		
		JButton btnPrescriptionInfo = new JButton("Prescription Info");
		btnPrescriptionInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 			  /* To get Prescription information*/
			{
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery(" select * from prescription");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
			}
		});
		btnPrescriptionInfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnPrescriptionInfo.setBounds(457, 146, 157, 23);
		frame.getContentPane().add(btnPrescriptionInfo);
		
		JButton btnRegionTableContents = new JButton("Region table contents");
		btnRegionTableContents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery(" select * from region");  
					table.setModel(DbUtils.resultSetToTableModel(rs));
					stmt.close();
					rs.close();

				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
			}
		});
		btnRegionTableContents.setBounds(34, 20, 140, 23);
		frame.getContentPane().add(btnRegionTableContents);
	}
}