package book_rent.ui.list;

import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookInfo;
import book_rent.service.BookCateService;
import book_rent.service.BookInfoService;
import book_rent.ui.exception.NotSelectedException;

public class BookRankTablePanel extends AbstractCustomTablePanel<BookInfo> {
	private BookInfoService bookInfoService;

	public BookRankTablePanel() {
		initialize();

	}

	private void initialize() {
		setBorder(new TitledBorder(null, "도서대여순위", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setList(List<BookInfo> list) {
		this.list = list;
	}

	public void setBookInfoService(BookInfoService bookInoService) {
		this.bookInfoService = bookInoService;
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

	@Override
	public void initList() {
		list = bookInfoService.showBookRank();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 600, 200, 200);

	}

	@Override
	public Object[] toArray(BookInfo b) {
		return new Object[] { b.getBookNo(), b.getBookName(), b.getBookCateNo().getBookCateName(), b.getBookRecord() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "도서번호", "도서제목", "도서구분", "대여횟수" };
	}

}
