package book_rent.ui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import book_rent.dto.BookCate;
import book_rent.service.BookCateService;
import book_rent.ui.content.AbstractContentPanel;
import book_rent.ui.content.BookCateContentPanel;
import book_rent.ui.list.AbstractCustomTablePanel;
import book_rent.ui.list.BookCateTablePanel;

public class BookCateManagerUI extends AbstractManagerUI<BookCate> {
	private BookCateService service;

	public BookCateManagerUI() {
		setBounds(100, 100, 600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	protected void setService() {
		service = new BookCateService();

	}

	@Override
	protected void tableLoadData() {
		((BookCateTablePanel) pContent).setService(service);
		pContent.loadData();

	}

	@Override
	protected AbstractContentPanel<BookCate> createContentPanel() {
		return new BookCateContentPanel();
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		BookCate updateCate = pContent.getItem();
		pList.setItem(updateCate);
		btnAdd.setText("수정");

	}

	@Override
	protected AbstractCustomTablePanel<BookCate> createTablePanel() {
		return new BookCateTablePanel();
	}

	@Override
	protected void actionPerformedMenuDelete() {
		BookCate delCate = pContent.getItem();
		service.removeCate(delCate);
		pContent.loadData();
		JOptionPane.showMessageDialog(null, delCate + "삭제 되었습니다.");

	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		BookCate updateCate = pList.getItem();
		service.modifyCate(updateCate);
		pContent.loadData();
		pList.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateCate.getBookCateName() + "정보가 수정되었습니다.");

	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookCate addCate = pContent.getItem();
		service.addCate(addCate);
		pContent.loadData();
		pList.clearTf();
		JOptionPane.showMessageDialog(null, addCate + " 추가했습니다.");

	}

}
