package hr;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddApplicantForm extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddApplicantForm frame = new AddApplicantForm();
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
	public AddApplicantForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 816);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("2.Add Applicant Form");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(247, 35, 376, 48);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Name");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l2.setBounds(49, 107, 376, 48);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Email");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l3.setBounds(49, 165, 376, 48);
		contentPane.add(l3);
		
		JLabel l4 = new JLabel("Phone");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l4.setBounds(49, 227, 376, 48);
		contentPane.add(l4);
		
		JLabel l5 = new JLabel("Gender");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l5.setBounds(49, 285, 376, 48);
		contentPane.add(l5);
		
		JLabel l6 = new JLabel("Position Applied");
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		l6.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l6.setBounds(49, 343, 376, 48);
		contentPane.add(l6);
		
		JLabel l7 = new JLabel("Resume Upload");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		l7.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l7.setBounds(49, 401, 376, 48);
		contentPane.add(l7);
		
		JLabel l8 = new JLabel("Date of Application");
		l8.setHorizontalAlignment(SwingConstants.CENTER);
		l8.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l8.setBounds(49, 474, 376, 48);
		contentPane.add(l8);
		
		JLabel l9 = new JLabel("Status");
		l9.setHorizontalAlignment(SwingConstants.CENTER);
		l9.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l9.setBounds(49, 532, 376, 48);
		contentPane.add(l9);
		
		JRadioButton rb1 = new JRadioButton("Male");
		buttonGroup.add(rb1);
		rb1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rb1.setHorizontalAlignment(SwingConstants.CENTER);
		rb1.setBounds(475, 287, 148, 48);
		contentPane.add(rb1);
		
		JRadioButton rb2 = new JRadioButton("Female");
		buttonGroup.add(rb2);
		rb2.setHorizontalAlignment(SwingConstants.CENTER);
		rb2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rb2.setBounds(614, 287, 148, 48);
		contentPane.add(rb2);
		
		JTextArea t1 = new JTextArea();
		t1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		t1.setBounds(469, 107, 293, 48);
		contentPane.add(t1);
		
		JTextArea t2 = new JTextArea();
		t2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		t2.setBounds(469, 165, 293, 48);
		contentPane.add(t2);
		
		JTextArea t3 = new JTextArea();
		t3.setFont(new Font("Times New Roman", Font.BOLD, 23));
		t3.setBounds(469, 227, 293, 48);
		contentPane.add(t3);
		
		JDateChooser db1 = new JDateChooser();
		db1.setBounds(475, 464, 293, 55);
		contentPane.add(db1);
		
		JComboBox<String> cb = new JComboBox<String>();
		cb.setBounds(475, 341, 293, 50);
		contentPane.add(cb);
		
		
		try {
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
		    PreparedStatement pst = con.prepareStatement("SELECT `Job Title` FROM `new job posting`");
		
		    ResultSet rs = pst.executeQuery();

		    while (rs.next()) {
		   
		        cb.addItem(rs.getString("Job Title")); 
		        cb.setSelectedItem(null);
		    }

		    con.close();
		} 
		catch (Exception ex) {
		    JOptionPane.showMessageDialog(null,ex);
		}
		
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	
		
		JTextArea t4 = new JTextArea();
		t4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				        JFileChooser fileChooser = new JFileChooser();
				        int filepath = fileChooser.showOpenDialog(null);
				        if (filepath == JFileChooser.APPROVE_OPTION) {
				            File selectedFile = fileChooser.getSelectedFile();
				            String path = selectedFile.getAbsolutePath();
				            t4.setText(path); 
				        }
				    }
				});
		t4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		t4.setBounds(475, 401, 293, 48);
		contentPane.add(t4);
		
		
		JComboBox<String> cb1 = new JComboBox<String>();
		cb1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cb1.setBounds(475, 533, 293, 52);
		cb1.addItem("Applied");
		cb1.addItem("Shortlisted");
		cb1.addItem("Selected");
		cb1.addItem("Rejected");
		cb1.setSelectedItem(null);

		contentPane.add(cb1);
		cb1.setBounds(475, 535, 293, 50);
		contentPane.add(cb1);
		
		JButton b1 = new JButton("Back");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboards().setVisible(true);
	            dispose();
			}
		});
		b1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		b1.setBounds(142, 617, 220, 62);
		contentPane.add(b1);
		
		JButton b2 = new JButton("Submit");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String name = t1.getText();
							String email = t2.getText();
							String phone = t3.getText();
							String gender = rb1.isSelected() ? "Male" : (rb2.isSelected() ? "Female" : "");
							String position = (cb.getSelectedItem() != null) ? cb.getSelectedItem().toString() : "";
							String resumePath = t4.getText();
							String status = (String) cb1.getSelectedItem();
							java.util.Date date1 = db1.getDate();
							if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty() ||
								position.isEmpty() || resumePath.isEmpty() || date1 == null || status.isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please fill all fields.");
								return;
							}
							java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
							String query = "INSERT INTO `add applicant form`(`Name`, `Email`, `Phone`, `Gender`, `Position Applied`, `Resume Upload`, `Date of Application`, `Status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
							PreparedStatement pst = con.prepareStatement(query);
							pst.setString(1, name);
							pst.setString(2, email);
							pst.setString(3, phone);
							pst.setString(4, gender);
							pst.setString(5, position);
							pst.setString(6, resumePath);
							pst.setDate(7, sqlDate);
							pst.setString(8, status);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Applicant added successfully!");
							con.close();
						} 
						catch (Exception ex) {
							JOptionPane.showMessageDialog(null,ex);
						}
					}
				});

			    
			}
		});
		b2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		b2.setBounds(542, 617, 220, 62);
		contentPane.add(b2);
		
		
		
		
	}
	
}
