package book_rent;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.BookManagerUI;
import book_rent.ui.MemManagerUI;
import book_rent.ui.RentPageUI;
import book_rent.ui.ReturnPageUI;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.BookSearchPanel;
import book_rent.ui.search.MemberSearchPanel;
import javax.swing.JTabbedPane;
import book_rent.ui.list.MemRankTablePanel;
import book_rent.ui.list.BookRankTablePanel;
import book_rent.ui.list.RentAllTablePanel;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class BookRentMain extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;

	private MemberInfoTablePanel pMemInfoTableList;
	private BookInfoTablePanel pBookInfoTableList;
	private JPanel pTableList;
	private JPanel pRentInfoTableList;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private JPanel pBtnRentReturn;
	private JButton btnRent;
	private JButton btnReturn;
	private JPanel pBtnMemberBook;
	private JButton btnMember;
	private JButton btnBook;
	private JTabbedPane tabbedPane;
	private RentAllTablePanel RentRecord;
	private JPanel RentRank;
	private MemRankTablePanel pMemRank;
	private BookRankTablePanel pBookRank;
	private JPanel pBtnNew;
	private JButton btnNewButton;

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
		setTitle("도서관리 프로그램");
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		rentService = new RentService();
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.addMouseListener(this);
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

		btnRent = new JButton("대여하기");
		btnRent.addActionListener(this);
		pBtnRentReturn.add(btnRent);

		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtnRentReturn.add(btnReturn);

		pBtnMemberBook = new JPanel();
		pBtn.add(pBtnMemberBook);
		pBtnMemberBook.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnMember = new JButton("회원관리");
		pBtnMemberBook.add(btnMember);
		btnMember.addActionListener(this);

		btnBook = new JButton("도서관리");
		pBtnMemberBook.add(btnBook);
		btnBook.addActionListener(this);

		JPanel pSearch = new JPanel();
		pBtnSearch.add(pSearch);
		pSearch.setLayout(new GridLayout(0, 2, 0, 0));

		MemberSearchPanel pSerachMember = new MemberSearchPanel();
		pSearch.add(pSerachMember);
		pSerachMember.setService(memService);
		pSerachMember.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		BookSearchPanel pSerachBook = new BookSearchPanel();
		pSearch.add(pSerachBook);
		pSerachBook.setService(bookService);

		pTableList = new JPanel();
		contentPane.add(pTableList);
		pTableList.setLayout(new GridLayout(0, 2, 5, 0));

		pMemInfoTableList = pSerachMember.getMemberInfoList();

		pTableList.add(pMemInfoTableList);
//		pMember.setBorder(new TitledBorder(null, "회원리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();

		pBookInfoTableList = pSerachBook.getBookInfoList();

		pTableList.add(pBookInfoTableList);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		RentRank = new JPanel();
		tabbedPane.addTab("대여순위", null, RentRank, null);
		RentRank.setLayout(new GridLayout(0, 2, 5, 0));

		pMemRank = new MemRankTablePanel();
		pMemRank.setMemInfoService(memService);
		pMemRank.loadData();
		RentRank.add(pMemRank);

		pBookRank = new BookRankTablePanel();
		pBookRank.setBookInfoService(bookService);
		pBookRank.loadData();
		RentRank.add(pBookRank);

		RentRecord = new RentAllTablePanel();
		tabbedPane.addTab("대여기록", null, RentRecord, null);
		RentRecord.setLayout(new BoxLayout(RentRecord, BoxLayout.X_AXIS));
		RentRecord.setService(rentService);
		RentRecord.loadData();
		
		
		

		pBtnNew = new JPanel();
		contentPane.add(pBtnNew);

		btnNewButton = new JButton("목록 새로고침");
		btnNewButton.addActionListener(this);
		pBtnNew.add(btnNewButton);
//		pBook.setBorder(new TitledBorder(null, "도서리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pBookInfoTableList.setService(bookService);
		pBookInfoTableList.loadData();

		pRentInfoTableList = new JPanel();
//		contentPane.add(pRentInfoTableList);
//		pRentInfoTableList.setRentService(rentService);
//		pRentInfoTableList.loadData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
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
		RentPageUI frame = new RentPageUI();
		frame.setVisible(true);
	}

	protected void actionPerformedButton2(ActionEvent e) {
		ReturnPageUI frame = new ReturnPageUI();
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

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == contentPane) {
			mouseClickedContentPane(e);
		}
	}

	protected void mouseClickedContentPane(MouseEvent e) {
//		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();

//		pBookInfoTableList.setService(bookService);
		pBookInfoTableList.loadData();

//		pMemRank.setMemInfoService(memService);
		pMemRank.loadData();
		pBookRank.loadData();
		RentRecord.loadData();
//		pRentInfoTableList.setRentService(rentService);
//		pRentInfoTableList.loadData();

	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {
		pMemInfoTableList.loadData();
		pBookInfoTableList.loadData();
		pMemRank.loadData();
		pBookRank.loadData();
		RentRecord.loadData();
	}
}
