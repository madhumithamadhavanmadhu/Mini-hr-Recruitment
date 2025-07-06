package hr;

import java.awt.BorderLayout;
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

public class WelcomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
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
	public WelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("MINI HR RECRUITMENT TRACKER");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(197, 71, 491, 78);
		contentPane.add(l1);
		
		JButton b1 = new JButton("Click Me");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage loginPage = new AdminPage();
				loginPage.setVisible(true);
				loginPage.close();

				
			}
		});
		b1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		b1.setBounds(379, 322, 144, 59);
		contentPane.add(b1);
	}
}
