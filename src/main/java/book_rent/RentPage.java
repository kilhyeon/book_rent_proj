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
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.MemberSearch;
import book_rent.ui.search.BookSearch;

public class RentPage extends JFrame {

	private JPanel contentPane;
	private MemberInfoService memService;
	private BookInfoService bookService;
	private MemberInfoTablePanel pListMem;
	private BookInfoTablePanel pListBook;
	private MemberSearch pSearchMem;
	private BookSearch pSearchBook;

	public RentPage() {
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

		pSearchMem = new MemberSearch();
		contentPane.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem = pSearchMem.getMemberInfoList();
		contentPane.add(pListMem);
		pListMem.setService(memService);
		pListMem.loadData();

		pSearchBook = new BookSearch();
		pSearchBook.setBorder(new EmptyBorder(10, 0, 0, 0));
		contentPane.add(pSearchBook);
		pSearchBook.setService(bookService);

		pListBook = pSearchBook.getBookInfoList();
		contentPane.add(pListBook);
		pListBook.setService(bookService);
		pListBook.loadData();

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		JButton btnRent = new JButton("대여하기");
		pBtn.add(btnRent);

		pSearchBook = new BookSearch();
		contentPane.add(pSearchBook);
		pSearchBook.setService(bookService);

		JButton btnCancel = new JButton("취소");
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

}
