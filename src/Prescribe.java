import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

/* @author Harsha Vardhan Reddy Nallagatla  SIU 853965332*/

public class Prescribe {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescribe window = new Prescribe();
					window.frame.setVisible(true);
				} catch (Exception e) {
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
	public Prescribe() {
		connection=connect.dbconnector();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(100, 100, 1047, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 159, 723, 376);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Pending Prescriptions");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select * from prescription where status='pending'");  
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
		btnNewButton.setBounds(24, 159, 215, 23);
		frame.getContentPane().add(btnNewButton);
	
		
		JButton btnCompletedPrescriptions = new JButton("Completed Prescriptions");
		btnCompletedPrescriptions.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCompletedPrescriptions.setBounds(24, 212, 215, 23);
		frame.getContentPane().add(btnCompletedPrescriptions);
		
		btnCompletedPrescriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select * from prescription where status='completed'");  
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
		
		JButton btnReadyPrescriptions = new JButton("Ready Prescriptions");
		btnReadyPrescriptions.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnReadyPrescriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select * from prescription where status='ready'");
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
		btnReadyPrescriptions.setBounds(24, 267, 215, 23);
		frame.getContentPane().add(btnReadyPrescriptions);
		
		JButton btnDrugList = new JButton("Drug List");
		btnDrugList.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDrugList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select trade_name as drug, pharm_co_name, formula from make_drug");  
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
		btnDrugList.setBounds(24, 318, 215, 23);
		frame.getContentPane().add(btnDrugList);
		
		JButton btnDrugsSoldBy = new JButton("Drugs sold by all pharmacies");
		btnDrugsSoldBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select S.trade_name as drug,p.pharm_id,p.name as PharmacyName,p.address,p.phone from sell S,pharmacy p where s.pharm_id=p.pharm_id");  
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
		btnDrugsSoldBy.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDrugsSoldBy.setBounds(24, 374, 215, 23);
		frame.getContentPane().add(btnDrugsSoldBy);
		
		JButton btnNewButton_1 = new JButton("Pharma Co selling \r\nexpensive drugs");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select S.trade_name as drug ,S.price,ph.name as PharmCoName,ph.phone from sell S,pharm_co ph where s.pharm_co_name=ph.name ORDER BY price DESC");  
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
		btnNewButton_1.setBounds(8, 436, 259, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnCommonList = new JButton("Pharmacies selling same drug");
		btnCommonList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select s1.pharm_id,s1.trade_name as drug,s2.pharm_id,phone as pharm2_phone from sell s1, sell s2, pharmacy where s1.trade_name=s2.trade_name and s1.pharm_id<>s2.pharm_id and s2.pharm_id=pharmacy.pharm_id and (s1.pharm_id<s2.pharm_id)");  
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
		btnCommonList.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCommonList.setBounds(14, 495, 243, 23);
		frame.getContentPane().add(btnCommonList);

		
		JLabel lblNewLabel = new JLabel("Prescription & Drug Info");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 27));
		lblNewLabel.setBounds(365, 67, 380, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/medical-suitecase-icon.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(61, 26, 196, 99);
		frame.getContentPane().add(label);
	}
}
