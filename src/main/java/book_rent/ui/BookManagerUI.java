package book_rent.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.BookInfoTablePanel;
import book_rent.ui.management.BookManagement;
import book_rent.ui.search.BookSearch;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class BookManagerUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private BookInfoService bookService;
	private BookInfoTablePanel pListBook;
	private BookInfoTablePanel pListBook2;
	private BookContentPanel pBookInfo;
	private BookManagement pBookMange;
	private BookSearch pSearchBook;

	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pBtn;
	private JPanel pCate;
	private BookCateManagerUI pCateManage;
	private JTabbedPane tabbedPane;
	private JLabel lblMent;

	public BookManagerUI() {
		bookService = new BookInfoService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pListBook.setService(bookService);
		pListBook.loadData();
		pListBook2.loadData();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(this);
		contentPane.add(tabbedPane);

		JPanel pBook = new JPanel();
//		tabbedPane.addTab("도서정보", null, pBook, null);
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

		JPanel pRentList = new JPanel();
		pBook.add(pRentList);

		JPanel pBookMangement = new JPanel();
		tabbedPane.addTab("도서관리", null, pBookMangement, null);
		pBookMangement.setLayout(new BoxLayout(pBookMangement, BoxLayout.Y_AXIS));

		pSearchBook = new BookSearch();
		pBookMangement.add(pSearchBook);
		pSearchBook.setService(bookService);

		pListBook2 = pSearchBook.getBookInfoList();
		pBookMangement.add(pListBook2);
		pListBook2.setService(bookService);
		pListBook2.loadData();

		pBookMange = new BookManagement();
		pBookMangement.add(pBookMange);

		lblMent = new JLabel("마우스 우클릭으로 수정, 삭제 ");
		lblMent.setHorizontalAlignment(SwingConstants.LEFT);
		pListBook2.add(lblMent, BorderLayout.NORTH);
		
		pBtn = new JPanel();
		pBookMangement.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pCate = new JPanel();
		tabbedPane.addTab("구분관리", null, pCate, null);
		pCate.setLayout(new BoxLayout(pCate, BoxLayout.X_AXIS));

		pCateManage = new BookCateManagerUI();
		pCate.add(pCateManage);

		JPopupMenu popupMenu = createPopupMenu();
		pListBook2.setPopupMenu(popupMenu);
	}

	public BookInfoTablePanel getpListBook() {
		return pListBook;
	}

	public void setpListBook(BookInfoTablePanel pListBook) {
		this.pListBook = pListBook;
	}

	public BookInfoTablePanel getpListBook2() {
		return pListBook2;
	}

	public void setpListBook2(BookInfoTablePanel pListBook2) {
		this.pListBook2 = pListBook2;
	}

	public void setpBookInfo(BookContentPanel pBookInfo) {
		this.pBookInfo = pBookInfo;
	}

	public BookManagement getpBookInfo() {
		return pBookMange;
	}

	public void setpBookInfo(BookManagement pBookInfo) {
		this.pBookMange = pBookInfo;
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
		BookInfo updateBook = pBookMange.getItem();
		bookService.modifyBook(updateBook);
		pListBook.loadData();
		pListBook2.loadData();
		pBookMange.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateBook.getBookName() + " 도서의 정보를 수정하였습니다.");

	}

	private void actionPerformedMenuUpdate() {
		BookInfo updateBook = pListBook2.getItem();
		pBookMange.setItem(updateBook);
		btnAdd.setText("수정");

	}

	private void actionPerformedMenuDelete() {
		BookInfo delBook = pListBook2.getItem();
		bookService.removeBook(delBook);
		pListBook.loadData();
		pListBook2.loadData();
		JOptionPane.showMessageDialog(null, delBook + " 도서의 정보를 삭제하였습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookInfo addBook = pBookMange.getItem();
//		System.out.println(addBook);
		bookService.addBook(addBook);
		pListBook.loadData();
		pListBook2.loadData();
		pBookMange.clearTf();
		JOptionPane.showMessageDialog(null, addBook + " 도서를 추가하였습니다.");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pBookMange.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tabbedPane) {
			mouseClickedTabbedPane(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void mouseClickedTabbedPane(MouseEvent e) {
		pBookMange.selectCmb();
		tableLoadData();
	}
}
