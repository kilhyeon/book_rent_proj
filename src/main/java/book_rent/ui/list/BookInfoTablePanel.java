package book_rent.ui.list;

import javax.swing.SwingConstants;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;

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
		setTableCellWidth(400, 400, 400, 400);

	}

	@Override
	public Object[] toArray(BookInfo b) {
		return new Object[] { b.getBookNo(), b.getBookName(), b.getCateNo(), b.getRentState() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "도서번호", "도서제목", "구분번호", "대출상태" };
	}

}
