package book_rent;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.BookManagerUI;
import book_rent.ui.MemMangerUI;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.list.RentTablePanel;
import book_rent.ui.search.BookSearch;
import book_rent.ui.search.MemberSearch;

@SuppressWarnings("serial")
public class BookRentMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;

	private MemberInfoTablePanel pMember;
	private BookInfoTablePanel pBook;
	private JPanel pList;
	private RentTablePanel pRent;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private JPanel panel_1;
	private JButton button;
	private JButton button_1;
	private JPanel panel_2;
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
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));

		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(10);
		panel_7.add(panel_1);

		button = new JButton("대출하기");
		button.addActionListener(this);
		panel_1.add(button);

		button_1 = new JButton("반납하기");
		button_1.addActionListener(this);
		panel_1.add(button_1);

		panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(10);
		panel_7.add(panel_2);

		btnNewButton = new JButton("회원관리");
		btnNewButton.addActionListener(this);
		panel_2.add(btnNewButton);

		btnNewButton_1 = new JButton("도서관리");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnNewButton_1);

		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 0, 0));

		MemberSearch panel_9 = new MemberSearch();
		panel_8.add(panel_9);
		panel_9.setService(memService);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		BookSearch panel_10 = new BookSearch();
		panel_8.add(panel_10);
		panel_10.setService(bookService);
		

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new GridLayout(1, 0, 5, 0));

		pMember = panel_9.getMemberInfoList();
		pList.add(pMember);
//		pMember.setBorder(new TitledBorder(null, "회원리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMember.setService(memService);
		pMember.loadData();

		pBook = panel_10.getBookInfoList();
		pList.add(pBook);
//		pBook.setBorder(new TitledBorder(null, "도서리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		if (e.getSource() == button_1) {
			actionPerformedButton_1(e);
		}
		if (e.getSource() == button) {
			actionPerformedButton(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}

	protected void actionPerformedButton(ActionEvent e) {
		RentPage frame = new RentPage();
		frame.setVisible(true);
	}

	protected void actionPerformedButton_1(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {
		MemMangerUI frame = new MemMangerUI();
		frame.setVisible(true);
	}

	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		BookManagerUI frame = new BookManagerUI();
		frame.setVisible(true);
	}
}
