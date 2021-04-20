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
	private JPanel pBtn1;
	private JButton btnRent;
	private JButton btnReturn;
	private JPanel pBtn2;
	private JButton btnMember;
	private JButton btnBook;

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
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel pBtnSearch = new JPanel();
		contentPane.add(pBtnSearch);
		pBtnSearch.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pBtn3 = new JPanel();
		pBtnSearch.add(pBtn3);
		pBtn3.setLayout(new GridLayout(0, 2, 0, 0));

		pBtn1 = new JPanel();
		FlowLayout fl_pBtn1 = (FlowLayout) pBtn1.getLayout();
		pBtn3.add(pBtn1);

		btnRent = new JButton("대출하기");
		btnRent.addActionListener(this);
		pBtn1.add(btnRent);

		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn1.add(btnReturn);

		pBtn2 = new JPanel();
		pBtn3.add(pBtn2);

		btnMember = new JButton("회원관리");
		btnMember.addActionListener(this);
		pBtn2.add(btnMember);

		btnBook = new JButton("도서관리");
		btnBook.addActionListener(this);
		btnBook.setHorizontalAlignment(SwingConstants.RIGHT);
		pBtn2.add(btnBook);

		JPanel pSearch = new JPanel();
		pBtnSearch.add(pSearch);
		pSearch.setLayout(new GridLayout(0, 2, 0, 0));

		MemberSearch pSerachMember = new MemberSearch();
		pSearch.add(pSerachMember);
		pSerachMember.setService(memService);
		pSerachMember.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		BookSearch pSerachBook = new BookSearch();
		pSearch.add(pSerachBook);
		pSerachBook.setService(bookService);

		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new GridLayout(1, 0, 5, 0));

		pMember = pSerachMember.getMemberInfoList();

		pList.add(pMember);
//		pMember.setBorder(new TitledBorder(null, "회원리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMember.setService(memService);
		pMember.loadData();

		pBook = pSerachBook.getBookInfoList();

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
		if (e.getSource() == btnBook) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnReturn) {
			actionPerformedButton_1(e);
		}
		if (e.getSource() == btnRent) {
			actionPerformedButton(e);
		}
		if (e.getSource() == btnMember) {
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
