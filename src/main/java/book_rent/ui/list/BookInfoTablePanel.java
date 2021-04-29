package book_rent.ui.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.content.BookContentPanel;
import book_rent.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class BookInfoTablePanel extends AbstractCustomTablePanel<BookInfo> implements MouseListener {
	private BookInfoService bookService;
	private BookContentPanel pBookInfoContent;

	public BookInfoTablePanel() {
		initialize();
		table.addMouseListener(this);
		pBookInfoContent = new BookContentPanel();
		pBookInfoContent.getTfBookNo().setEditable(false);
		pBookInfoContent.getTfBookName().setEditable(false);
		pBookInfoContent.getTfBookCount().setEditable(false);
		pBookInfoContent.getTfBookCate().setEditable(false);
		pBookInfoContent.getTfRentState().setEditable(false);
		
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "도서리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public BookContentPanel getpBookInfo() {
		return pBookInfoContent;
	}

	public void setList(List<BookInfo> list) {
		this.list = list;
	}

	public void setService(BookInfoService service) {
		this.bookService = service;
	}

	@Override
	public void initList() {
		list = bookService.showBookList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 850, 150, 200, 200);

	}

	@Override
	public Object[] toArray(BookInfo b) {
		return new Object[] { b.getBookNo(), b.getBookName(), b.getBookCount(), b.getBookCateNo().getBookCateName(), b.getRentState() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "도서번호", "도서제목", "보유권수", "도서구분", "대여상태" };
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
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int bookNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		try {
			BookInfo item = bookService.showBookByBookNo(bookNo);
			pBookInfoContent.setItem(item);
		
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
