import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;

public class ProfitLoss extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnBackButton;
	private JTable table_1;
	private JLabel spLabel;
	private JLabel ppLabel;
	private JTextField sptextField;
	private JTextField pptextField;
	private JTextField pltextField;
	private JLabel lblNumbersOfItems;
	private JTextField nitextField;
	private JButton p_l_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfitLoss frame = new ProfitLoss();
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
	public ProfitLoss() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1250, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(1106, 21, 97, 24);
		contentPane.add(btnNewButton_1);

		table = new JTable();
		table.setBounds(67, 257, 318, -168);
		contentPane.add(table);

		//**********************************************Inventory & Revenue Table************************************************
		
		btnNewButton = new JButton("Inventory & Revenue");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					Statement stmt = (Statement) conn.createStatement();
					String sql = "select productid, stock_quantity, product_price, sales_price, shipped_unit from product";
					ResultSet rs = stmt.executeQuery(sql);
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				
			}
		});

		btnNewButton.setBounds(41, 66, 661, 35);
		contentPane.add(btnNewButton);

		btnBackButton = new JButton("Back");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu window = new MainMenu();
				window.frame.setVisible(true);
				dispose();

			}
		});
		btnBackButton.setBounds(31, 10, 88, 24);
		contentPane.add(btnBackButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 122, 659, 314);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		spLabel = new JLabel("Sales Price:");
		spLabel.setBounds(748, 122, 145, 26);
		contentPane.add(spLabel);
		
		ppLabel = new JLabel("Product Price:");
		ppLabel.setBounds(750, 193, 135, 26);
		contentPane.add(ppLabel);
		
		sptextField = new JTextField();
		sptextField.setBounds(961, 119, 186, 32);
		contentPane.add(sptextField);
		sptextField.setColumns(10);
		
		pptextField = new JTextField();
		pptextField.setText("");
		pptextField.setBounds(961, 190, 186, 32);
		contentPane.add(pptextField);
		pptextField.setColumns(10);
		
		pltextField = new JTextField();
		pltextField.setBounds(961, 379, 186, 32);
		contentPane.add(pltextField);
		pltextField.setColumns(10);
		
		lblNumbersOfItems = new JLabel("Numbers of items: ");
		lblNumbersOfItems.setBounds(748, 277, 191, 26);
		contentPane.add(lblNumbersOfItems);
		
		nitextField = new JTextField();
		nitextField.setBounds(961, 271, 97, 32);
		contentPane.add(nitextField);
		nitextField.setColumns(10);
		
		//*********************************************************p&l_calculation****************************************************
		p_l_Button = new JButton("Net Profit & Loss");
		p_l_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float num1=Float.parseFloat(sptextField.getText());
				float num2=Float.parseFloat(pptextField.getText());
				int num3=Integer.parseInt(nitextField.getText());
				
				String answer = String.valueOf((num1-num2)*num3);
				pltextField.setText(answer);
			}
		});
		p_l_Button.setBounds(752, 378, 202, 35);
		contentPane.add(p_l_Button);

	}
}
