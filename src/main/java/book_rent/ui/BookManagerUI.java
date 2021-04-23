package book_rent.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.management.BookManagement;
import book_rent.ui.search.BookSearch;

public class BookManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private BookInfoService bookService;
	private BookInfoTablePanel pListBook;
	private BookManagement pBookInfo;
	private BookSearch pSearchBook;

	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pBtn;

	public BookManagerUI() {
		bookService = new BookInfoService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pListBook.setService(bookService);
		pListBook.loadData();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel pBook = new JPanel();
		tabbedPane.addTab("도서정보", null, pBook, null);
		pBook.setLayout(new BoxLayout(pBook, BoxLayout.Y_AXIS));

		pSearchBook = new BookSearch();
		pBook.add(pSearchBook);
		pSearchBook.setService(bookService);

		pListBook = pSearchBook.getBookInfoList();
		pBook.add(pListBook);
		pListBook.setService(bookService);
		pListBook.loadData();

//		pBookInfo = pListBook.getpBookInfo();
//		pBook.add(pBookInfo);

		JPanel pRentList = new JPanel();
		pBook.add(pRentList);

		JPanel pBookMangement = new JPanel();
		tabbedPane.addTab("도서관리", null, pBookMangement, null);
		pBookMangement.setLayout(new BoxLayout(pBookMangement, BoxLayout.Y_AXIS));

		pSearchBook = new BookSearch();
		pBookMangement.add(pSearchBook);
		pSearchBook.setService(bookService);

		pListBook = pSearchBook.getBookInfoList();
		pBookMangement.add(pListBook);
		pListBook.setService(bookService);
		pListBook.loadData();

		pBookInfo = new BookManagement();
		pBookMangement.add(pBookInfo);

		pBtn = new JPanel();
		pBookMangement.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		JPopupMenu popupMenu = createPopupMenu();
		pListBook.setPopupMenu(popupMenu);
	}

	public BookInfoTablePanel getpListBook() {
		return pListBook;
	}

	public void setpListBook(BookInfoTablePanel pListBook) {
		this.pListBook = pListBook;
	}

	public BookManagement getpBookInfo() {
		return pBookInfo;
	}

	public void setpBookInfo(BookManagement pBookInfo) {
		this.pBookInfo = pBookInfo;
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}

			} else {
				if (e.getSource() == btnCancel) {
					actionPerformedBtnCancel(e);
				}

				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		BookInfo updateBook = pBookInfo.getItem();
		bookService.modifyBook(updateBook);
		pListBook.loadData();
		pBookInfo.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateBook.getBookName() + "정보가 수정되었습니다.");

	}

	private void actionPerformedMenuUpdate() {
		BookInfo updateBook = pListBook.getItem();
		pBookInfo.setItem(updateBook);
		btnAdd.setText("수정");

	}

	private void actionPerformedMenuDelete() {
		BookInfo delBook = pListBook.getItem();
		bookService.removeBook(delBook);
		pListBook.loadData();
		JOptionPane.showMessageDialog(null, delBook + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookInfo addBook = pBookInfo.getItem();
		bookService.addBook(addBook);
		pListBook.loadData();
		pBookInfo.clearTf();
		JOptionPane.showMessageDialog(null, addBook + " 추가했습니다.");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pBookInfo.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
