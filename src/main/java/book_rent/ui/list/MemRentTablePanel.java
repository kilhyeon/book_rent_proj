package book_rent.ui.list;

import javax.swing.SwingConstants;

import book_rent.dto.BookCate;
import book_rent.dto.Rent;
import book_rent.service.RentService;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.NotSelectedException;

public class MemRentTablePanel extends AbstractCustomTablePanel<Rent> {
	private RentService service;
	private MemberContentPanel pMemInfo;
	
	
	public MemRentTablePanel() {
		// TODO Auto-generated constructor stub
	}


	public void setService(RentService service) {
		this.service = service;
	}

	@Override
	public void initList() {
		
		list = service.showRentListByMemNo(memNo);
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 200, 400, 200, 200, 100);

	}

	@Override
	public Object[] toArray(Rent r) {
		return new Object[] { r.getRentNo(), r.getMemNo(), r.getBookNo(), r.getRentDate(), r.getReturnDate(),
				r.getLateDate() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "대출번호", "회원번호", "도서번호", "도서대여일", "도서반납일", "연체일자" };
	}

	@Override
	public Rent getItem() {
		int row = table.getSelectedRow();
		int memNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}

		return list.get(list.indexOf(new Rent().getMemNo()));
	}

}
