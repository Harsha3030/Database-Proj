import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;


/* @author Harsha Vardhan Reddy Nallagatla  SIU 853965332*/

public class Contract {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					Contract window = new Contract();
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
	private JTextField textFieldPharm_id;
	private JTextField textFieldStart_date;
	private JTextField textFieldEnd_date;
	private JTextField textFieldText;
	private JTextField textFieldSupervisor;
	private JTextField textFieldPharm_co_name;
	
	public void refresh(){
		try
		{  
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("select c.*, pharmacy.name, pharmacy.address, pharmacy.phone from contract C, pharmacy where c.pharm_id=pharmacy.pharm_id");  
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			rs.close();
			
		}
		catch(Exception e1)
		{ 
			JOptionPane.showMessageDialog(null,e1);
		}  
		
	}
	public Contract() {
		connection=connect.dbconnector();
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 27));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(100, 100, 1082, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnContractList = new JButton("Contract List");
		btnContractList.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnContractList.setBackground(UIManager.getColor("Button.background"));
		btnContractList.setBounds(65, 124, 114, 33);
		frame.getContentPane().add(btnContractList);
		
		btnContractList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					Statement stmt=connection.createStatement();
					ResultSet rs=stmt.executeQuery("select c.*, pharmacy.name, pharmacy.address, pharmacy.phone from contract C, pharmacy where c.pharm_id=pharmacy.pharm_id");  
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
		
		JScrollPane tabledemo = new JScrollPane();
		tabledemo.setBounds(278, 124, 755, 348);
		frame.getContentPane().add(tabledemo);
		
		table = new JTable();
		tabledemo.setViewportView(table);
		
		JButton btnChangeupdate = new JButton("Change/Update");
		btnChangeupdate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnChangeupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					String sql="update contract set start_date='"+textFieldStart_date.getText()+"',end_date='"+textFieldEnd_date.getText()+"',text='"+textFieldText.getText()+"',supervisor='"+textFieldSupervisor.getText()+"' where pharm_id='"+textFieldPharm_id.getText()+"' and pharm_co_name='"+textFieldPharm_co_name.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Updated");
					pst.close();
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refresh();
			}
		});
		btnChangeupdate.setBounds(27, 520, 146, 23);
		frame.getContentPane().add(btnChangeupdate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{  
					String sql="insert into contract values(?,?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.setString(1, textFieldPharm_id.getText());
					pst.setString(2, textFieldStart_date.getText());
					pst.setString(3, textFieldEnd_date.getText());
					pst.setString(4, textFieldText.getText());
					pst.setString(5, textFieldSupervisor.getText());
					pst.setString(6, textFieldPharm_co_name.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data added");
					pst.close();
				
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refresh();
			}
		});
		btnAdd.setBounds(247, 520, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{  
					String sql="delete from contract where pharm_id='"+textFieldPharm_id.getText()+"' and pharm_co_name='"+textFieldPharm_co_name.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data removed");
					pst.close();
				}
				catch(Exception e1)
				{ 
					JOptionPane.showMessageDialog(null,e1);
				}  		
				
				refresh();
				
			}
		});
		btnRemove.setBounds(448, 520, 89, 23);
		frame.getContentPane().add(btnRemove);
		
		JLabel lblNewLabel_1 = new JLabel("Start Date");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(27, 242, 63, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("End Date");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_2.setBounds(27, 297, 63, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Text");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(27, 347, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Supervisor");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_4.setBounds(27, 401, 74, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pharm Co Name");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_5.setBounds(21, 455, 100, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Pharm ID");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(27, 194, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldPharm_id = new JTextField();
		textFieldPharm_id.setBounds(158, 191, 86, 20);
		frame.getContentPane().add(textFieldPharm_id);
		textFieldPharm_id.setColumns(10);
		
		textFieldStart_date = new JTextField();
		textFieldStart_date.setBounds(158, 239, 86, 20);
		frame.getContentPane().add(textFieldStart_date);
		textFieldStart_date.setColumns(10);
		
		textFieldEnd_date = new JTextField();
		textFieldEnd_date.setBounds(158, 294, 86, 20);
		frame.getContentPane().add(textFieldEnd_date);
		textFieldEnd_date.setColumns(10);
		
		textFieldText = new JTextField();
		textFieldText.setBounds(158, 344, 86, 20);
		frame.getContentPane().add(textFieldText);
		textFieldText.setColumns(10);
		
		textFieldSupervisor = new JTextField();
		textFieldSupervisor.setBounds(158, 398, 86, 20);
		frame.getContentPane().add(textFieldSupervisor);
		textFieldSupervisor.setColumns(10);
		
		textFieldPharm_co_name = new JTextField();
		textFieldPharm_co_name.setBounds(158, 452, 86, 20);
		frame.getContentPane().add(textFieldPharm_co_name);
		textFieldPharm_co_name.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Contract Management Functions");
		lblNewLabel_6.setFont(new Font("Consolas", Font.BOLD, 27));
		lblNewLabel_6.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_6.setBounds(384, 34, 456, 33);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/signature-icon.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(48, 11, 171, 90);
		frame.getContentPane().add(label);
	}
}