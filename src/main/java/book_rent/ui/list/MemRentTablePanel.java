package book_rent.ui.list;

import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;
import book_rent.service.RentService;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.NotSelectedException;

public class MemRentTablePanel extends AbstractCustomTablePanel<Rent> {
	private RentService service;
	private MemberContentPanel pMemInfo;
	
	
	public MemRentTablePanel() {
		initialize();
	}


	public void setService(RentService service) {
		this.service = service;
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "현재 대여중인 도서목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}
	
	@Override
	public void initList() {		
		list = new ArrayList<Rent>();
	}
	
	public void blankTable() {
		Object[][] data = new Object[0][];
		
	}

	public void showRentListByMemNo(MemberInfo memIfno) {
		list = service.showRentListByMemNo(memIfno);
	}
	
	
	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 150, 550, 150, 150, 100);

	}

	@Override
	public Object[] toArray(Rent r) {
		return new Object[] { r.getRentNo(), r.getMemNo(), r.getBookNo(), r.getRentDate(), r.getReturnDate(),
				r.getLateDate() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "대출번호", "회원정보", "도서정보", "도서대여일", "도서반납일", "연체일자" };
	}

	@Override
	public Rent getItem() {
		return null;
		
	}

}
