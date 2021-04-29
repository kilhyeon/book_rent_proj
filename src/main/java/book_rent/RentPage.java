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
import book_rent.dto.MemGradeRentCount;
import book_rent.dto.MemberInfo;
import book_rent.service.BookInfoService;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.InvalidCheckException;
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
		BookInfo bookNo = pBookInfoContent.getItemBookNo();
		int bookCount = pBookInfoContent.getItemBookCount();
		int bookCountTotal = pBookInfoContent.getItemBokCountTotal();

		int memRentCount = pMemInfoContent.getItemMemRentCount();
		int memGradeRentCount = pMemInfoContent.getItemMemGradeRentCount();

//		try {
		if (memNo != null && bookNo != null) {

			if (bookCount < bookCountTotal) {
				if (memRentCount < memGradeRentCount) {
					rentService.bookRent(memNo, bookNo);
					pMemInfoTableList.loadData();
					pBookInfoTableList.loadData();
					pMemInfoContent.clearTf();
					pBookInfoContent.clearTf();
					JOptionPane.showMessageDialog(null, "도서 대여가 완료되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "대여가능한 도서권수를 초과하였습니다.", "메세지", JOptionPane.ERROR_MESSAGE);
					pMemInfoTableList.loadData();
					pBookInfoTableList.loadData();
					pMemInfoContent.clearTf();
					pBookInfoContent.clearTf();
				}

			} else {
				JOptionPane.showMessageDialog(null, "모두 대여중인 도서입니다.", "메세지", JOptionPane.ERROR_MESSAGE);
				pMemInfoTableList.loadData();
				pBookInfoTableList.loadData();
//				pMemInfoContent.clearTf();
//				pBookInfoContent.clearTf();
			}
//		} catch (NumberFormatException | InvalidCheckException e1) {
//			JOptionPane.showMessageDialog(null, "회원, 도서정보를 선택하세요.", "메세지", JOptionPane.WARNING_MESSAGE);
//		}
		}
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemInfoContent.clearTf();
		pBookInfoContent.clearTf();
	}
}
