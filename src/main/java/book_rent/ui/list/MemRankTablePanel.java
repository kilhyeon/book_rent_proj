package book_rent.ui.list;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemGrade;
import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.exception.NotSelectedException;

public class MemRankTablePanel extends AbstractCustomTablePanel<MemberInfo> {
	private MemberInfoService memInfoService;

	public MemRankTablePanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "회원대여순위", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setMemInfoService(MemberInfoService memInfoService) {
		this.memInfoService = memInfoService;
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
	public void initList() {
		list = memInfoService.showMemberRank();

	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200, 200, 200);

	}

	@Override
	public Object[] toArray(MemberInfo m) {
		return new Object[] { m.getMemNo(), m.getMemName(), m.getMemGradeNo().getMemGradeName(), m.getMemRecrod() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "회원번호", "회원이름", "회원등급", "대여횟수" };
	}

}
