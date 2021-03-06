package book_rent.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;
import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.content.RentContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemRentTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.BookSearchPanel;
import book_rent.ui.search.MemberSearchPanel;

public class ReturnPageUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private MemberInfoTablePanel pMemInfoTableList;
	private BookInfoTablePanel pBookInfoTableList;
	private MemRentTablePanel pMemRentInfoTableList;
	private MemberContentPanel pMemInfoContent;
	private BookContentPanel pBookInfoContent;
	private RentContentPanel pRentInfoContent;
	private JPanel pMem;
	private JPanel pRent;
	private JPanel pBook;

	private MemberSearchPanel pMemSearch;
	private BookSearchPanel pBookSearch;

	private JPanel pBtn;
	private JButton btnReturn;
	private JButton btnCancel;

	public ReturnPageUI() {
		setResizable(false);
		setTitle("도서반납");
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		rentService = new RentService();
		initialize();
//		tableLoadData();
	}

	private void tableLoadData() {
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();
		pBookInfoTableList.setService(bookService);
		pBookInfoTableList.loadData();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pMem = new JPanel();
		contentPane.add(pMem);
		pMem.setLayout(new BoxLayout(pMem, BoxLayout.Y_AXIS));

		pMemSearch = new MemberSearchPanel();
		pMem.add(pMemSearch);
		pMemSearch.setService(memService);

		pMemInfoTableList = pMemSearch.getMemberInfoList();
		pMem.add(pMemInfoTableList);
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();

		pMemInfoContent = pMemInfoTableList.getpMemInfo();
		pMem.add(pMemInfoContent);

		pRent = new JPanel();
		contentPane.add(pRent);
		pRent.setLayout(new BoxLayout(pRent, BoxLayout.Y_AXIS));

//		pSearchBook = new BookSearch();
//		pBook.add(pSearchBook);
//		pSearchBook.setService(bookService);

		pMemRentInfoTableList = pMemInfoTableList.getMemRentList();
		pRent.add(pMemRentInfoTableList);
		pMemRentInfoTableList.setRentService(rentService);
		pMemRentInfoTableList.loadData();

		pRentInfoContent = pMemRentInfoTableList.getpRentInfoContent();
		pRent.add(pRentInfoContent);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnReturn = new JButton("반납하기");
		btnReturn.addActionListener(this);
		pBtn.add(btnReturn);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

//		pBook = new JPanel();
//		contentPane.add(pBook);
//		pBook.setLayout(new BoxLayout(pBook, BoxLayout.Y_AXIS));
//		pBook.setVisible(true);
//
//		pBookSearch = new BookSearch();
//		pBook.add(pBookSearch);
//		pBookSearch.setService(bookService);
//
//		pBookInfoTableList = pBookSearch.getBookInfoList();
//		pBook.add(pBookInfoTableList);
//		pBookInfoTableList.setService(bookService);
//		pBookInfoTableList.loadData();
//
//		pBookInfoContent = pBookInfoTableList.getpBookInfo();
//		pBook.add(pBookInfoContent);

	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pMemInfoTableList = pListMem;
	}

	public MemberInfoTablePanel getpListMem() {
		return pMemInfoTableList;
	}

	public void setpListBook(BookInfoTablePanel pListBook) {
		this.pBookInfoTableList = pListBook;
	}

	public MemRentTablePanel getpListBook() {
		return pMemRentInfoTableList;
	}

	public void setpListBook(MemRentTablePanel pListBook) {
		this.pMemRentInfoTableList = pListBook;
	}

	public RentContentPanel getpRentInfo() {
		return pRentInfoContent;
	}

	public void setpRentInfo(RentContentPanel pRentInfo) {
		this.pRentInfoContent = pRentInfo;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnReturn) {
			actionPerformedBtnRent(e);
		}
	}

	protected void actionPerformedBtnRent(ActionEvent e) {
		Rent rentNo = pMemRentInfoTableList.getRentNo();
		MemberInfo memNo = pMemInfoContent.getItemMemNo();

		try {
			rentService.bookReturn(rentNo, memNo);
		} catch (NumberFormatException | InvalidCheckException e1) {
			JOptionPane.showMessageDialog(null, "회원, 도서정보를 선택하세요.", "메세지", JOptionPane.WARNING_MESSAGE);
		}
		
		pMemInfoTableList.loadData();
		pMemRentInfoTableList.loadData();
//		pBookInfoTableList.loadData();
		
		pMemInfoContent.clearTf();
		pRentInfoContent.clearTf();

		JOptionPane.showMessageDialog(null, "도서 반납이 완료되었습니다.");


	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemInfoContent.clearTf();
		pRentInfoContent.clearTf();
	}
}
