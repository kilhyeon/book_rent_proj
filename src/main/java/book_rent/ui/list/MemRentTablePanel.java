package book_rent.ui.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.Rent;
import book_rent.service.RentService;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.content.MemberInfo;
import book_rent.ui.content.RentContentPanel;
import book_rent.ui.exception.NotSelectedException;

public class MemRentTablePanel extends AbstractCustomTablePanel<Rent> implements MouseListener {
	private RentService rentService;
	private MemberContentPanel pMemInfoContent;
	private RentContentPanel pRentInfoContent;

	public MemRentTablePanel() {
		initialize();
		table.addMouseListener(this);
		pMemInfoContent = new MemberContentPanel();
		pRentInfoContent = new RentContentPanel();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "현재 대여중인 도서목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setService(RentService service) {
		this.rentService = service;
	}

	public void setList(List<Rent> list) {
		this.list = list;
	}

	public RentContentPanel getpRentInfo() {
		return pRentInfoContent;
	}

	@Override
	public void initList() {
		list = new ArrayList<Rent>();
	}

	public void blankTable() {
		Object[][] data = new Object[0][];

	}

	public void showRentListByMemNo(MemberInfo memIfno) {
		list = rentService.showRentListByMemNo(memIfno);
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 200, 500, 200, 100);

	}

	@Override
	public Object[] toArray(Rent r) {
		return new Object[] { r.getRentNo(), r.getMemNo(), r.getBookNo(), r.getRentDate(), r.getLateDate() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "대출번호", "회원정보", "도서정보", "도서대여일", "연체일자" };
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int rentNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		try {
			Rent item = rentService.showRentByRentNo(rentNo);
			pRentInfoContent.setItem(item);
			pRentInfoContent.getTfRentNo().setEditable(false);
			pRentInfoContent.getTfRentMem().setEditable(false);
			pRentInfoContent.getTfRentBook().setEditable(false);
			pRentInfoContent.getTflRentDate().setEditable(false);
			pRentInfoContent.getTfLateDate().setEditable(false);
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
