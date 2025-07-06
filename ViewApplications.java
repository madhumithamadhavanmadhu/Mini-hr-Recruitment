package hr;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewApplications extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewApplications frame = new ViewApplications();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewApplications() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		JLabel l1 = new JLabel("3. View All Applications");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(200, 20, 500, 40);
		contentPane.add(l1);

		String[] columns = {"Name", "Email", "Phone", "Gender", "Position Applied", "Resume Upload", "Date of Application", "Status"};
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 80, 820, 600);
		contentPane.add(scrollPane);

		
		loadTableData();

		
		JButton b1 = new JButton("Back");
		b1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b1.setBounds(210, 713, 160, 40);
		contentPane.add(b1);
		b1.addActionListener(e -> {
			new Dashboards().setVisible(true);
			dispose();
		});

		
		JButton b2 = new JButton("Search");
		b2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b2.setBounds(555, 713, 160, 40);
		contentPane.add(b2);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameToSearch = JOptionPane.showInputDialog("Enter name to search:");
				if (nameToSearch != null && !nameToSearch.trim().isEmpty()) {
					boolean found = false;
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.getValueAt(i, 0).toString().equalsIgnoreCase(nameToSearch)) {
							table.setRowSelectionInterval(i, i);
							table.scrollRectToVisible(table.getCellRect(i, 0, true));
							found = true;
							break;
						}
					}
					if (!found) {
						JOptionPane.showMessageDialog(null, "Applicant not found.");
					}
				}
			}
		});
	}

	private void loadTableData() {
		try {
			model.setRowCount(0); 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `add applicant form`");

			while (rs.next()) {
				Object[] row = {
					rs.getString("Name"),
					rs.getString("Email"),
					rs.getString("Phone"),
					rs.getString("Gender"),
					rs.getString("Position Applied"),
					rs.getString("Resume Upload"),
					rs.getDate("Date of Application"),
					rs.getString("Status")
				};
				model.addRow(row);
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
