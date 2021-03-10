package book_rent.panel;

import javax.swing.SwingConstants;

import book_rent.dto.Member_info;
import book_rent.table.AbstractCustomTablePanel;

public class MemTablePanel extends AbstractCustomTablePanel<Member_info> {

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3);

		// 컬럼별 너비 조정
		setTableCellWidth(300, 300, 300, 300);

	}

	@Override
	public Object[] toArray(Member_info t) {
		return new Object[] { t.getMemNo(), t.getMemName(), t.getMemTel(), t.getMemCp() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "회원번호", "회원명", "전화번호", "휴대전화" };
	}

}
