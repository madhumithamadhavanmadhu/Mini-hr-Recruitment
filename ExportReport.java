package hr;

import java.awt.EventQueue;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;

public class ExportReport extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ExportReport frame = new ExportReport();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ExportReport() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1177, 821);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("5. Export Report");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(352, 45, 421, 61);
        contentPane.add(l1);

        JButton b1 = new JButton("Export To CSV");
        b1.addActionListener(e -> exportToCSV());
        b1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b1.setBounds(294, 150, 584, 90);
        contentPane.add(b1);

        JButton b2 = new JButton("Export To PDF");
        b2.addActionListener(e -> exportToPDF());
        b2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b2.setBounds(294, 260, 584, 90);
        contentPane.add(b2);

        JButton b3 = new JButton("Back");
        b3.addActionListener(e -> {
            new Dashboards().setVisible(true);
            dispose();
        });
        b3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b3.setBounds(429, 560, 344, 61);
        contentPane.add(b3);

        JButton b4 = new JButton("Print PDF");
        b4.addActionListener(e -> printPDF());
        b4.setFont(new Font("Times New Roman", Font.BOLD, 25));
        b4.setBounds(294, 370, 584, 90);
        contentPane.add(b4);
    }

    private void exportToCSV() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
            String query = "SELECT * FROM `add applicant form`";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save CSV File");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection != JFileChooser.APPROVE_OPTION) return;

            File fileToSave = new File(fileChooser.getSelectedFile() + ".csv");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8"));

            String[] headers = {"Name", "Email", "Phone", "Gender", "Position", "Resume", "Date", "Status"};
            bw.write(String.join(",", headers));
            bw.newLine();

            while (rs.next()) {
                String[] data = {
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("Phone"),
                    rs.getString("Gender"),
                    rs.getString("Position Applied"),
                    rs.getString("Resume Upload"),
                    rs.getString("Date of Application"),
                    rs.getString("Status")
                };
                bw.write(String.join(",", data));
                bw.newLine();
            }

            bw.flush();
            bw.close();
            con.close();
            JOptionPane.showMessageDialog(null, "CSV exported successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void exportToPDF() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/hr_recruitment_tracker", "root", "");
            String query = "SELECT * FROM `add applicant form`";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save PDF File");
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection != JFileChooser.APPROVE_OPTION) return;

            File fileToSave = new File(fileChooser.getSelectedFile() + ".pdf");
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(fileToSave));
            doc.open();

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 3, 2, 3, 4, 3, 2});

            String[] headers = {"Name", "Email", "Phone", "Gender", "Position", "Resume", "Date", "Status"};
            for (String h : headers) {
                table.addCell(new PdfPCell(new Phrase(h)));
            }

            while (rs.next()) {
                table.addCell(rs.getString("Name"));
                table.addCell(rs.getString("Email"));
                table.addCell(rs.getString("Phone"));
                table.addCell(rs.getString("Gender"));
                table.addCell(rs.getString("Position Applied"));
                table.addCell(rs.getString("Resume Upload"));
                table.addCell(rs.getString("Date of Application"));
                table.addCell(rs.getString("Status"));
            }

            doc.add(table);
            doc.close();
            con.close();
            JOptionPane.showMessageDialog(null, "PDF exported successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void printPDF() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select PDF to Print");

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File pdfFile = fileChooser.getSelectedFile();

                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().print(pdfFile);
                        JOptionPane.showMessageDialog(null, "Printing started...");
                    } else {
                        JOptionPane.showMessageDialog(null, "Printing not supported on this system.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selected file not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error printing file: " + e.getMessage());
        }
    }
}
