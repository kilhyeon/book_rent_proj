package book_rent;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.content.BookContentPanel;

public class ReturnPage2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private MemberInfoTablePanel panel_2;
	private BookInfoTablePanel panel_5;

	public ReturnPage2() {
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("검색방법 : ");
		panel_1.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		panel_1.add(comboBox);

		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("검색");
		panel_1.add(btnNewButton);

		panel_2 = new MemberInfoTablePanel();
		contentPane.add(panel_2);
		panel_2.setService(memService);
		panel_2.loadData();

		MemberContentPanel panel_3 = new MemberContentPanel();
		panel_3.setBorder(new TitledBorder(null, "대여회원 상세정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);

		JLabel label = new JLabel("검색방법 : ");
		panel_4.add(label);

		JComboBox comboBox_1 = new JComboBox();
		panel_4.add(comboBox_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_4.add(textField_1);

		JButton button = new JButton("검색");
		panel_4.add(button);

		panel_5 = new BookInfoTablePanel();
		contentPane.add(panel_5);
		panel_5.setService(bookService);
		panel_5.loadData();

		BookContentPanel panel_6 = new BookContentPanel();
		panel_6.setBorder(new TitledBorder(null, "대여도서 상세정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		contentPane.add(panel_7);

		JButton btnNewButton_1 = new JButton("반납하기");
		panel_7.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("취소");
		panel_7.add(btnNewButton_2);
	}

}
