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
	private MemberInfoTablePanel pListMem;
	private BookInfoTablePanel pListBook;
	private MemberContentPanel pMemInfo;
	private BookContentPanel pBookInfo;
	private JPanel pMem;
	private JPanel pBook;
	private MemberSearch pSearchMem;
	private BookSearch pSearchBook;
	private JPanel pBtn;
	private JButton btnRent;
	private JButton btnCancel;

	public RentPage() {
		memService = new MemberInfoService();
		bookService = new BookInfoService();
		rentService = new RentService();
		initialize();
		tableLoadData();
	}
	
	private void tableLoadData() {
		pListMem.setService(memService);
		pListMem.loadData();
		

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

		pSearchMem = new MemberSearch();
		pMem.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem = pSearchMem.getMemberInfoList();
		pMem.add(pListMem);
		pListMem.setService(memService);
		pListMem.loadData();

		pMemInfo = pListMem.getpMemInfo();
		pMem.add(pMemInfo);

		pBook = new JPanel();
		contentPane.add(pBook);
		pBook.setLayout(new BoxLayout(pBook, BoxLayout.Y_AXIS));

		pSearchBook = new BookSearch();
		pBook.add(pSearchBook);
		pSearchBook.setService(bookService);

		pListBook = pSearchBook.getBookInfoList();
		pBook.add(pListBook);
		pListBook.setService(bookService);
		pListBook.loadData();

		pBookInfo = pListBook.getpBookInfo();
		pBook.add(pBookInfo);

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
		return pListMem;
	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pListMem = pListMem;
	}

	public BookInfoTablePanel getpListBook() {
		return pListBook;
	}

	public void setpListBook(BookInfoTablePanel pListBook) {
		this.pListBook = pListBook;
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
		MemberInfo insertMem = pMemInfo.getItemMemNo();
		BookInfo insertBook = pBookInfo.getItemBookNo();		
		rentService.addRent(insertMem, insertBook);
		JOptionPane.showMessageDialog(null, pMemInfo.getItemMemName() + " 회원의 " + pBookInfo.getItemBookName() + " 도서 대여를 완료하였습니다.");
//		System.out.println("대여성공");
		pListMem.loadData();
		pListBook.loadData();
		pMemInfo.clearTf();
		pBookInfo.clearTf();

	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemInfo.clearTf();
		pBookInfo.clearTf();
	}
}
