package book_rent.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.exception.NotSelectedException;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class BookInfoTablePanel extends AbstractCustomTablePanel<BookInfo> {
	private BookInfoService service;

	public BookInfoTablePanel() {
		initialize();
	}
	private void initialize() {
		setBorder(new TitledBorder(null, "도서리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setList(List<BookInfo> list) {
		this.list = list;
	}

	public void setService(BookInfoService service) {
		this.service = service;
	}

	@Override
	public void initList() {
		list = service.showBookList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 800, 200, 400);

	}

	@Override
	public Object[] toArray(BookInfo b) {
		return new Object[] { b.getBookNo(), b.getBookName(), b.getBookCateNo(), b.getRentState() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "도서번호", "도서제목", "도서구분", "대여상태" };
	}

	@Override
	public BookInfo getItem() {
		int row = table.getSelectedRow();
		int bookNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new BookInfo(bookNo)));
	}

}
