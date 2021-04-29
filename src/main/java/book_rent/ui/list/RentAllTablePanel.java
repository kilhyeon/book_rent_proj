package book_rent.ui.list;

import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.Rent;
import book_rent.service.RentService;
import book_rent.ui.exception.NotSelectedException;

public class RentAllTablePanel extends AbstractCustomTablePanel<Rent> {
	private RentService rentService;

	public RentAllTablePanel() {
		initialize();

	}

	private void initialize() {
		setBorder(new TitledBorder(null, "전체대여목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setList(List<Rent> list) {
		this.list = list;
	}

	public void setService(RentService service) {
		this.rentService = service;
	}

	@Override
	public void initList() {
		list = rentService.showRentAllList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 100, 100, 100, 100, 600, 100, 200, 200, 100);

	}

	@Override
	public Object[] toArray(Rent r) {
		return new Object[] { r.getRentNo(), r.getMemNo().getMemNo(), r.getMemName().getMemName(),
				r.getMemGradeNo().getMemGradeName(), r.getBookNo().getBookNo(), r.getBookName(),
				r.getBookCateNo().getBookCateName(), r.getRentDate(), r.getReturnDate(), r.getLateDate() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "대출번호", "회원번호", "회원이름", "회원등급", "도서번호", "도서제목", "도서구분", "도서대여일", "도서반납일", "연체일" };
	}

	@Override
	public Rent getItem() {
		int row = table.getSelectedRow();
		int rentNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}

		return list.get(list.indexOf(new Rent(rentNo)));
	}
}
