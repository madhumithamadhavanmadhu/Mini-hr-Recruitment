package hr;
import java.sql.*;
import java.sql.Date;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import hr_db.DB_Connection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class NewJobPostingForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5781019049742163991L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewJobPostingForm frame = new NewJobPostingForm();
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
	public NewJobPostingForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("1.New Job Posting");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(120, 36, 616, 62);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Job Title");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l2.setBounds(104, 118, 180, 62);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Department");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l3.setBounds(104, 190, 180, 62);
		contentPane.add(l3);
		
		JLabel l4 = new JLabel("Location");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l4.setBounds(104, 262, 180, 62);
		contentPane.add(l4);
		
		JLabel l5 = new JLabel("Experience Required");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l5.setBounds(104, 334, 202, 62);
		contentPane.add(l5);
		
		JLabel l6 = new JLabel("Skills Required");
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		l6.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l6.setBounds(104, 406, 180, 62);
		contentPane.add(l6);
		
		JLabel l7 = new JLabel("Salary Range");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		l7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l7.setBounds(104, 478, 180, 62);
		contentPane.add(l7);
		
		JLabel l8 = new JLabel("Job Description");
		l8.setHorizontalAlignment(SwingConstants.CENTER);
		l8.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l8.setBounds(104, 550, 180, 62);
		contentPane.add(l8);
		
		JLabel l9 = new JLabel("Post Date");
		l9.setHorizontalAlignment(SwingConstants.CENTER);
		l9.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l9.setBounds(120, 622, 180, 62);
		contentPane.add(l9);
		
		JTextArea t1 = new JTextArea();
		t1.setBounds(380, 128, 246, 52);
		contentPane.add(t1);
		
		JTextArea t2 = new JTextArea();
		t2.setBounds(380, 200, 246, 52);
		contentPane.add(t2);
		
		JTextArea t3 = new JTextArea();
		t3.setBounds(380, 272, 246, 52);
		contentPane.add(t3);
		
		JTextArea t4 = new JTextArea();
		t4.setBounds(380, 344, 246, 52);
		contentPane.add(t4);
		
		JTextArea t5 = new JTextArea();
		t5.setBounds(380, 416, 246, 52);
		contentPane.add(t5);
		
		JTextArea t6 = new JTextArea();
		t6.setBounds(380, 488, 246, 52);
		contentPane.add(t6);
		
		JTextArea t7 = new JTextArea();
		t7.setBounds(380, 560, 246, 52);
		contentPane.add(t7);
		
		JDateChooser dc = new JDateChooser();
		dc.setBounds(380, 644, 246, 52);
		contentPane.add(dc);
		
		JButton b1 = new JButton("Go Back");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 new Dashboards().setVisible(true);
			     dispose();
			}
		});
		b1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		b1.setBounds(57, 728, 120, 34);
		contentPane.add(b1);
		
		JButton b2 = new JButton("Add Job");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String JobTitle=t1.getText();
				String Department=t2.getText();
				String Location=t3.getText();
				String ExperienceRequired = t4.getText();
				String SkillsRequired= t5.getText();
				String SalaryRange = t6.getText();
				String JobDescription = t7.getText();
				java.util.Date JobDate=dc.getDate();
				if (JobTitle.isEmpty() || Department.isEmpty() || Location.isEmpty() ||  ExperienceRequired.isEmpty() || SkillsRequired.isEmpty() || SalaryRange.isEmpty() || JobDescription.isEmpty()||JobDate==null)
				{
				    JOptionPane.showMessageDialog(null, "Please fill all fields!");
				    return;
				}
				java.sql.Date JobDate1 = new java.sql.Date(JobDate.getTime());
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
				String query = "INSERT INTO `new job posting`(`Job Title`, `Department`, `Location`, `Experience Required`, `Skills Required`, `Salary Range`, `Job Description`, `Post Date`) VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, JobTitle);
				pst.setString(2, Department);
				pst.setString(3, Location);
				pst.setString(4, ExperienceRequired);
				pst.setString(5, SkillsRequired);
				pst.setString(6, SalaryRange);
				pst.setString(7, JobDescription);
				pst.setDate(8,  JobDate1);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Job Posted Successfully!");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Oops!!Job Posted UnSuccessfully!"+e1);
				}

			}
		});
		b2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		b2.setBounds(643, 728, 120, 34);
		contentPane.add(b2);
	}
}
