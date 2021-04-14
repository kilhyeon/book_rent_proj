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

public class ReturnPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private MemberInfoTablePanel panel_2;
	private BookInfoTablePanel panel_5;

	public ReturnPage() {
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

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "대여회원 상세정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("이름 : ");
		panel.add(lblNewLabel_1);

		textField_2 = new JTextField();
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("회원번호 : ");
		panel.add(lblNewLabel_2);

		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_8);

		JLabel lblNewLabel_3 = new JLabel("생년월일 : ");
		panel_8.add(lblNewLabel_3);

		textField_4 = new JTextField();
		panel_8.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("년 ");
		panel_8.add(lblNewLabel_4);

		textField_5 = new JTextField();
		panel_8.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("월 ");
		panel_8.add(lblNewLabel_5);

		textField_6 = new JTextField();
		panel_8.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("일 ");
		panel_8.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("전화번호 : ");
		panel_8.add(lblNewLabel_7);

		textField_7 = new JTextField();
		panel_8.add(textField_7);
		textField_7.setColumns(10);

		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_9);

		JLabel lblNewLabel_8 = new JLabel("주소 : ");
		panel_9.add(lblNewLabel_8);

		textField_8 = new JTextField();
		panel_9.add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("휴대전화 : ");
		panel_9.add(lblNewLabel_9);

		textField_9 = new JTextField();
		panel_9.add(textField_9);
		textField_9.setColumns(10);

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

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "대여도서 상세정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_10.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_10);

		JLabel lblNewLabel_10 = new JLabel("도서제목 : ");
		panel_10.add(lblNewLabel_10);

		textField_10 = new JTextField();
		panel_10.add(textField_10);
		textField_10.setColumns(10);

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_11.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_11);

		JLabel label_1 = new JLabel("도서번호 : ");
		panel_11.add(label_1);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		panel_11.add(textField_11);

		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_12.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_12);

		JLabel label_2 = new JLabel("도서구분 : ");
		panel_12.add(label_2);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		panel_12.add(textField_12);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		contentPane.add(panel_7);

		JButton btnNewButton_1 = new JButton("반납하기");
		panel_7.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("취소");
		panel_7.add(btnNewButton_2);
	}

}
