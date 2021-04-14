package book_rent;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.list.RentTablePanel;

@SuppressWarnings("serial")
public class BookRentMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private MemberInfoTablePanel pMember;
	private BookInfoTablePanel pBook;
	private JPanel pList;
	private RentTablePanel pRent;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

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
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		rentService = new RentService();
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
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

		btnNewButton = new JButton("대출하기");
		btnNewButton.addActionListener(this);
		panel_7.add(btnNewButton);

		btnNewButton_1 = new JButton("반납하기");
		btnNewButton_1.addActionListener(this);
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
		pMember.setService(memService);
		pMember.loadData();

		pBook = new BookInfoTablePanel();
		pList.add(pBook);
		pBook.setService(bookService);
		pBook.loadData();

		pRent = new RentTablePanel();
		contentPane.add(pRent);
		pRent.setBorder(new TitledBorder(null, "현재 대여중인 도서목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pRent.setService(rentService);
		pRent.loadData();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {
		RentPage frame = new RentPage();
		frame.setVisible(true);
	}
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}
}
