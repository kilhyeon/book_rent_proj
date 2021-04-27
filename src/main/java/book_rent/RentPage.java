package book_rent;

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
import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.BookSearch;
import book_rent.ui.search.MemberSearch;

public class RentPage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private RentService rentService;
	private MemberInfoTablePanel pMemInfoTableList;
	private BookInfoTablePanel pBookInfoTableList;
	private MemberContentPanel pMemInfoContent;
	private BookContentPanel pBookInfoContent;
	private JPanel pMem;
	private JPanel pBook;
	private MemberSearch pMemSearch;
	private BookSearch pBookSearch;
	private JPanel pBtn;
	private JButton btnRent;
	private JButton btnCancel;

	public RentPage() {
		setTitle("도서대여");
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		rentService = new RentService();
		initialize();
		tableLoadData();
	}
	
	private void tableLoadData() {
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();
		

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

		pMemSearch = new MemberSearch();
		pMem.add(pMemSearch);
		pMemSearch.setService(memService);

		pMemInfoTableList = pMemSearch.getMemberInfoList();
		pMem.add(pMemInfoTableList);
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();

		pMemInfoContent = pMemInfoTableList.getpMemInfo();
		pMem.add(pMemInfoContent);

		pBook = new JPanel();
		contentPane.add(pBook);
		pBook.setLayout(new BoxLayout(pBook, BoxLayout.Y_AXIS));

		pBookSearch = new BookSearch();
		pBook.add(pBookSearch);
		pBookSearch.setService(bookService);

		pBookInfoTableList = pBookSearch.getBookInfoList();
		pBook.add(pBookInfoTableList);
		pBookInfoTableList.setService(bookService);
		pBookInfoTableList.loadData();

		pBookInfoContent = pBookInfoTableList.getpBookInfo();
		pBook.add(pBookInfoContent);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnRent = new JButton("대여하기");
//		btnRent.setEnabled(false);
		
		btnRent.addActionListener(this);
		pBtn.add(btnRent);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
	}

	public MemberInfoTablePanel getpListMem() {
		return pMemInfoTableList;
	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pMemInfoTableList = pListMem;
	}

	public BookInfoTablePanel getpListBook() {
		return pBookInfoTableList;
	}

	public void setpListBook(BookInfoTablePanel pListBook) {
		this.pBookInfoTableList = pListBook;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnRent) {
			actionPerformedBtnRent(e);
		}
	}
	protected void actionPerformedBtnRent(ActionEvent e) {
		MemberInfo memNo = pMemInfoContent.getItemMemNo();
//		System.out.println(memNo);
		BookInfo bookNo = pBookInfoContent.getItemBookNo();		
		rentService.addRent(memNo, bookNo);
		JOptionPane.showMessageDialog(null, pMemInfoContent.getItemMemName() + " 회원의 " + pBookInfoContent.getItemBookName() + " 도서 대여를 완료하였습니다.");
//		System.out.println("대여성공");
		pMemInfoTableList.loadData();
		pBookInfoTableList.loadData();
		pMemInfoContent.clearTf();
		pBookInfoContent.clearTf();

	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemInfoContent.clearTf();
		pBookInfoContent.clearTf();
	}
}
