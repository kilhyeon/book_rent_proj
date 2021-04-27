package book_rent.ui.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookCate;
import book_rent.service.BookCateService;
import book_rent.ui.content.BookCateContentPanel;
import book_rent.ui.exception.NotSelectedException;
import java.awt.GridLayout;

public class BookCateTablePanel extends AbstractCustomTablePanel<BookCate> {
	private BookCateService bookCateService;
	private BookCateContentPanel pBookCateContent;

	public BookCateTablePanel() {
		initialize();
		pBookCateContent = new BookCateContentPanel();

	}

	private void initialize() {
		setBorder(new TitledBorder(null, "구분목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public BookCateContentPanel getpBookCate() {
		return pBookCateContent;
	}

	public void setList(List<BookCate> list) {
		this.list = list;
	}

	public void setService(BookCateService service) {
		this.bookCateService = service;
	}

	@Override
	public BookCate getItem() {
		int row = table.getSelectedRow();
		int bookCateNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}

		return list.get(list.indexOf(new BookCate(bookCateNo)));
	}

	@Override
	public void initList() {
		list = bookCateService.showBookCateByAll();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200);

	}

	@Override
	public Object[] toArray(BookCate c) {
		return new Object[] { c.getBookCateNo(), c.getBookCateName() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "구분번호", "구분이름" };
	}

}
