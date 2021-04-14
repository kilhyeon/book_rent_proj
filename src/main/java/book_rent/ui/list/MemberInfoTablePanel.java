package book_rent.ui.list;

import javax.swing.SwingConstants;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.exception.NotSelectedException;

@SuppressWarnings("serial")
public class MemberInfoTablePanel extends AbstractCustomTablePanel<MemberInfo> {

	public MemberInfoTablePanel() {
	}

	private MemberInfoService service;

	public void setService(MemberInfoService service) {
		this.service = service;
	}

	@Override
	public void initList() {
		list = service.showMemberList();
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200, 200, 400, 400, 200, 200);
	}

	@Override
	public Object[] toArray(MemberInfo m) {
		return new Object[] { m.getMemNo(), m.getMemName(), m.getMemBirth(), m.getMemTel(), m.getMemCp(),
				m.getMemAddr(), m.getMemGrade() };
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

}
