package book_rent;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import book_rent.service.MemberInfoService;
import book_rent.ui.list.MemberInfoTablePanel;

@SuppressWarnings("serial")
public class BookRentMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private MemberInfoTablePanel pMember;
	private JPanel pBook;
	private JPanel pList;
	private MemberInfoService service;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookRentMain frame = new BookRentMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookRentMain() {

		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_7);

		JButton btnNewButton = new JButton("대출하기");
		panel_7.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("반납하기");
		panel_7.add(btnNewButton_1);

		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("빠른회원검색");
		panel_9.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		panel_9.add(comboBox);

		textField = new JTextField();
		panel_9.add(textField);
		textField.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);

		JLabel label = new JLabel("빠른도서검색");
		panel_10.add(label);

		JComboBox comboBox_1 = new JComboBox();
		panel_10.add(comboBox_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_10.add(textField_1);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new GridLayout(1, 0, 0, 0));

		pMember = new MemberInfoTablePanel();		
		pList.add(pMember);
		pMember.setService(service);
		pMember.loadData();

		pBook = new JPanel();
		pList.add(pBook);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
	}

}
