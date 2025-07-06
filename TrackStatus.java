package hr;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TrackStatus extends JFrame {
    private JPanel contentPane;
    private JTextField searchField;
    private JTextArea resultArea;
    private JLabel appliedLabel, shortlistedLabel, selectedLabel, rejectedLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TrackStatus frame = new TrackStatus();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TrackStatus() {
        setTitle("Track Recruitment Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1082, 828);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("5. Track Recruitment Status");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        titleLabel.setBounds(180, 30, 450, 40);
        contentPane.add(titleLabel);

        JLabel searchLabel = new JLabel("Enter Email or Name:");
        searchLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        searchLabel.setBounds(85, 100, 262, 30);
        contentPane.add(searchLabel);

        searchField = new JTextField();
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        searchField.setBounds(373, 100, 412, 30);
        contentPane.add(searchField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        searchBtn.setBounds(843, 100, 120, 30);
        contentPane.add(searchBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(85, 160, 878, 346);
        contentPane.add(scrollPane);

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        backBtn.setBounds(450, 729, 180, 40);
        contentPane.add(backBtn);

        resultArea = new JTextArea();
        resultArea.setBounds(85, 161, 878, 298);
        contentPane.add(resultArea);
        resultArea.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        resultArea.setEditable(false);

        appliedLabel = new JLabel("Total Applied: ");
        appliedLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        appliedLabel.setBounds(85, 551, 235, 57);
        contentPane.add(appliedLabel);

        shortlistedLabel = new JLabel("Total Shortlisted: ");
        shortlistedLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        shortlistedLabel.setBounds(302, 551, 235, 57);
        contentPane.add(shortlistedLabel);

        selectedLabel = new JLabel("Total Selected: ");
        selectedLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        selectedLabel.setBounds(550, 551, 235, 57);
        contentPane.add(selectedLabel);

        rejectedLabel = new JLabel("Total Rejected: ");
        rejectedLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        rejectedLabel.setBounds(775, 551, 235, 57);
        contentPane.add(rejectedLabel);

        searchBtn.addActionListener(e -> searchApplicant());
        backBtn.addActionListener(e -> {
            new Dashboards().setVisible(true);
            dispose();
        });

        loadCountsFromDatabase();
    }

    private void searchApplicant() {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter email or name.");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");

            String query = "SELECT * FROM `add applicant form` WHERE `Email` = ? OR `Name` = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                StringBuilder result = new StringBuilder();
                result.append("Name: ").append(rs.getString("Name")).append("\n");
                result.append("Email: ").append(rs.getString("Email")).append("\n");
                result.append("Phone: ").append(rs.getString("Phone")).append("\n");
                result.append("Gender: ").append(rs.getString("Gender")).append("\n");
                result.append("Position Applied: ").append(rs.getString("Position Applied")).append("\n");
                result.append("Resume Path: ").append(rs.getString("Resume Upload")).append("\n");
                result.append("Date of Application: ").append(rs.getString("Date of Application")).append("\n");
                result.append("Status: ").append(rs.getString("Status")).append("\n");

                resultArea.setText(result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No applicant found with that name or email.");
                resultArea.setText("");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void loadCountsFromDatabase() {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");

            String query = "SELECT `Status`, COUNT(*) AS total FROM `add applicant form` GROUP BY `Status`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            int applied = 0, shortlisted = 0, selected = 0, rejected = 0;

            while (rs.next()) {
                String status = rs.getString("Status");
                int count = rs.getInt("total");

                switch (status) {
                    case "Applied":
                        applied = count;
                        break;
                    case "Shortlisted":
                        shortlisted = count;
                        break;
                    case "Selected":
                        selected = count;
                        break;
                    case "Rejected":
                        rejected = count;
                        break;
                }
            }

            appliedLabel.setText("Total Applied: " + applied);
            shortlistedLabel.setText("Total Shortlisted: " + shortlisted);
            selectedLabel.setText("Total Selected: " + selected);
            rejectedLabel.setText("Total Rejected: " + rejected);

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
