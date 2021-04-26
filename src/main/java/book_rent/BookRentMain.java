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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.BookManagerUI;
import book_rent.ui.MemManagerUI;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemRentTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.BookSearch;
import book_rent.ui.search.MemberSearch;

@SuppressWarnings("serial")
public class BookRentMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;

	private MemberInfoTablePanel pMember;
	private BookInfoTablePanel pBook;
	private JPanel pList;
	private MemRentTablePanel pRent;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private JPanel pBtnRentReturn;
	private JButton btnRent;
	private JButton btnReturn;
	private JPanel pBtnManagement;
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
		mainLoadData();
	}
	
	public void mainLoadData() {
		pMember.setService(memService);
		pBook.setService(bookService);
		pRent.setService(rentService);
		pMember.revalidate();
		pBook.revalidate();
		pRent.revalidate();
		pMember.repaint();
		pBook.repaint();
		pRent.repaint();
		pMember.loadData();
		pBook.loadData();
		pRent.loadData();
//		System.out.println("메인로드데이터");
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel pBtnSearch = new JPanel();
		contentPane.add(pBtnSearch);
		pBtnSearch.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pBtn = new JPanel();
		pBtnSearch.add(pBtn);
		pBtn.setLayout(new GridLayout(0, 2, 0, 0));

		pBtnRentReturn = new JPanel();
		pBtn.add(pBtnRentReturn);

		btnRent = new JButton("대출하기");
		btnRent.addActionListener(this);
		pBtnRentReturn.add(btnRent);

		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtnRentReturn.add(btnReturn);

		pBtnManagement = new JPanel();
		pBtn.add(pBtnManagement);
		pBtnManagement.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				btnMember = new JButton("회원관리");
				pBtnManagement.add(btnMember);
				btnMember.addActionListener(this);
		
				btnBook = new JButton("도서관리");
				pBtnManagement.add(btnBook);
				btnBook.addActionListener(this);

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

		pRent = pMember.getMemRentList();
		contentPane.add(pRent);
//		pRent.setBorder(new TitledBorder(null, "현재 대여중인 도서목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pRent.setService(rentService);
		pRent.loadData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRent) {
			actionPerformedButton1(e);
		}
		if (e.getSource() == btnReturn) {
			actionPerformedButton2(e);
		}
		if (e.getSource() == btnMember) {
			actionPerformedButton3(e);
		}
		if (e.getSource() == btnBook) {
			actionPerformedButton5(e);
		}
	}

	protected void actionPerformedButton1(ActionEvent e) {
		RentPage frame = new RentPage();
		frame.setVisible(true);
	}

	protected void actionPerformedButton2(ActionEvent e) {
		ReturnPage frame = new ReturnPage();
		frame.setVisible(true);
	}

	protected void actionPerformedButton3(ActionEvent e) {
		MemManagerUI frame = new MemManagerUI();
		frame.setVisible(true);
	}

	protected void actionPerformedButton5(ActionEvent e) {
		BookManagerUI frame = new BookManagerUI();
		frame.setVisible(true);
	}

}
