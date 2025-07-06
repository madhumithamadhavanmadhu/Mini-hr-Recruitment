package hr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboards extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboards frame = new Dashboards();
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
	public Dashboards() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 812);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("DashBoards");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(213, 76, 390, 54);
		contentPane.add(l1);
		
		JButton b1 = new JButton("1.New Job Posting");
		b1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				NewJobPostingForm jobForm = new NewJobPostingForm();
		        jobForm.setVisible(true);
		        dispose(); 
			}
		});
		b1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b1.setBounds(173, 202, 507, 54);
		contentPane.add(b1);
		
		JButton b2 = new JButton("2.Add Applicants");
		b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AddApplicantForm appForm = new AddApplicantForm();
		        appForm.setVisible(true);
		        dispose();
			}
		});
		b2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b2.setBounds(173, 266, 507, 54);
		contentPane.add(b2);
		
		JButton b3 = new JButton("3.View Applications");
		b3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				  ViewApplications viewApps = new ViewApplications();
			      viewApps.setVisible(true);
			      dispose();
			}
		});
		b3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b3.setBounds(173, 330, 507, 54);
		contentPane.add(b3);
		
		JButton b4 = new JButton("4.Track Status");
		b4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 TrackStatus statusPage = new TrackStatus();
			        statusPage.setVisible(true);
			        dispose();
			}
		});
		b4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b4.setBounds(173, 394, 507, 54);
		contentPane.add(b4);
		
		JButton b5 = new JButton("5.Export Report");
		b5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				  ExportReport export = new ExportReport();
			        export.setVisible(true);
			        dispose();
			}
		});
		b5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b5.setBounds(173, 458, 507, 54);
		contentPane.add(b5);
		
		JButton b6 = new JButton("6.Logout");
		b6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				  WelcomePage welcome = new WelcomePage();
			        welcome.setVisible(true);
			        dispose();
			}
		});
		b6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		b6.setBounds(173, 522, 507, 54);
		contentPane.add(b6);
	}

}
