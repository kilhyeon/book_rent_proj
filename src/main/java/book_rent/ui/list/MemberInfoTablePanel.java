package book_rent.ui.list;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.NotSelectedException;

@SuppressWarnings("serial")

public class MemberInfoTablePanel extends AbstractCustomTablePanel<MemberInfo> implements MouseListener {
	private MemberInfoService memService;
	private MemberContentPanel pMemInfoContent;
	private MemRentTablePanel memRentTableList;

	public MemberInfoTablePanel() {

		initialize();
		table.addMouseListener(this);
		pMemInfoContent = new MemberContentPanel();
		memRentTableList = new MemRentTablePanel();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "회원리스트", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public MemberContentPanel getpMemInfo() {
		return pMemInfoContent;

	}

	public void setService(MemberInfoService service) {
		this.memService = service;
	}

	public void setList(List<MemberInfo> list) {
		this.list = list;
	}

	public MemRentTablePanel getMemRentList() {
		return memRentTableList;
	}

	public void setMemRentList(MemRentTablePanel memRentList) {
		this.memRentTableList = memRentList;
	}

	@Override
	public void initList() {
		list = memService.showMemberList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200, 300, 400, 400, 200, 300);
	}

	@Override
	public Object[] toArray(MemberInfo m) {
		return new Object[] { m.getMemNo(), m.getMemName(), m.getMemBirth(), m.getMemTel(), m.getMemCp(),
				m.getMemAddr(), m.getMemGradeNo().getMemGradeName() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "회원번호", "회원명", "생년월일", "전화번호", "휴대전화", "주소", "회원등급" };
	}

	@Override
	public MemberInfo getItem() {
		int row = table.getSelectedRow();
		int memNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new MemberInfo(memNo)));
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int row = table.getSelectedRow();
		int memNo = (int) table.getValueAt(row, 0);
		if (row == -1) {
			throw new NotSelectedException();
		}
		try {
			MemberInfo item = memService.showMemberByMemNo(memNo);
			pMemInfoContent.setItem(item);
			pMemInfoContent.getTfMemNo().setEditable(false);
			pMemInfoContent.getTfMemName().setEditable(false);
			pMemInfoContent.getTfMemGrade().setEditable(false);
			pMemInfoContent.getTfMemBirth().setEditable(false);
			pMemInfoContent.getTfMemAddr().setEditable(false);
			pMemInfoContent.getTfMemCp().setEditable(false);
			pMemInfoContent.getTfMemTel().setEditable(false);

//			System.out.println(memNo);
			memRentTableList.showRentListByMemNo(new MemberInfo(memNo));
			memRentTableList.setList();
		} catch (NullPointerException e1) {
//			e1.printStackTrace();
			memRentTableList.loadData();
//			System.out.println("널포인트");
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
