package book_rent.ui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.content.AbstractContentPanel;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.list.AbstractCustomTablePanel;
import book_rent.ui.list.BookInfoTablePanel;

@SuppressWarnings("serial")
public class BookManagerUI extends AbstractManagerUI<BookInfo> {
	private BookInfoService service;

	public BookManagerUI() {
		setBounds(100, 100, 800, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	protected void setService() {
		service = new BookInfoService();
	}

	@Override
	protected void tableLoadData() {
		((BookInfoTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<BookInfo> createContentPanel() {
		return new BookContentPanel();
	}

	@Override
	protected AbstractCustomTablePanel<BookInfo> createTablePanel() {
		return new BookInfoTablePanel();
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		BookInfo updateBook = pList.getItem();
		pContent.setItem(updateBook);
		btnAdd.setText("수정");

	}

	@Override
	protected void actionPerformedMenuDelete() {
		BookInfo delBook = pList.getItem();
		service.removeBook(delBook);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delBook + "삭제 되었습니다.");

	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		BookInfo updateBook = pContent.getItem();
		service.modifyBook(updateBook);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateBook.getBookName() + "정보가 수정되었습니다.");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookInfo addBook = pContent.getItem();
		service.addBook(addBook);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addBook + " 추가했습니다.");

	}

}
