package book_rent.panel;

import javax.swing.SwingConstants;

import book_rent.dto.Member_info_dto;
import book_rent.table.AbstractCustomTablePanel;

public class MemberTablePanel extends AbstractCustomTablePanel<Member_info_dto> {

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(100, 250, 100, 200, 200, 200);

	}

	@Override
	public Object[] toArray(Member_info_dto t) {
		return new Object[] {
				t.getMemNo(),
				t.getMemName(),
				t.getMemBirth(),
				t.getMemTel(),
				t.getMemCp(),
				t.getMemAddr()
		};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"회원번호", "회원명", "생년월일", "전화번호", "휴대전화", "주소"};
	}

}
