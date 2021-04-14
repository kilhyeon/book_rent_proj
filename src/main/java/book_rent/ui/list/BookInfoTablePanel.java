package book_rent.ui.list;

import javax.swing.SwingConstants;

import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class BookInfoTablePanel extends AbstractCustomTablePanel<BookInfo> {

	public BookInfoTablePanel() {
	}

	private BookInfoService service;

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
		return new Object[] { b.getBookNo(), b.getBookName(), b.getBookCate(), b.getRentState() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "도서번호", "도서제목", "구분번호", "대출상태" };
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
