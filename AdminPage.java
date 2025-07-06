package hr;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JPasswordField p1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("Login Page");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(247, 70, 305, 68);
		contentPane.add(l1);
		
		t1 = new JTextField();
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setBounds(412, 199, 255, 68);
		contentPane.add(t1);
		t1.setColumns(10);
		
		p1 = new JPasswordField();
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		p1.setBounds(412, 377, 255, 76);
		contentPane.add(p1);
		
		JLabel l2 = new JLabel("Username");
		l2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(82, 191, 197, 76);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Password");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		l3.setBounds(86, 377, 174, 68);
		contentPane.add(l3);
		
		JButton b1 = new JButton("Login");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = t1.getText();
				String pass = new String(p1.getPassword());
				try
				{
			    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
	            
	            
				String query = "SELECT * FROM admin WHERE username=? AND password=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, user);
				ps.setString(2, pass);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
				    JOptionPane.showMessageDialog(null, "Login Successful!");
				    Dashboards dashboard = new Dashboards();
				    dashboard.setVisible(true);
				    dispose(); 
				} else
				{
				    JOptionPane.showMessageDialog(null, "Invalid ");
				}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}

			        } 
		});
		b1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b1.setBounds(326, 565, 174, 68);
		contentPane.add(b1);
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
}
