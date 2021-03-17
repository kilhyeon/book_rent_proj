package book_rent.ui.list;

import javax.swing.SwingConstants;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;

@SuppressWarnings("serial")
public class MemberInfoTablePanel extends AbstractCustomTablePanel<MemberInfo> {
	public MemberInfoTablePanel() {
	}
	private MemberInfoService service;

//	@Override
//	public MemberInfo getItem() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void initList() {
		list = service.showMemberList();
	}
	
	public void setService(MemberInfoService service) {
		this.service = service;
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200, 200, 400, 400, 200, 200);
	}

	@Override
	public Object[] toArray(MemberInfo t) {
		return new Object[] {
			t.getMemNo()
			, t.getMemName()
			, t.getMemBirth()
			, t.getMemTel()
			, t.getMemCp()
			, t.getMemAddr()
			, t.getMemGrade()
		};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "회원번호", "회원명", "생년월일", "전화번호", "휴대전화", "주소", "회원등급" };
	}

}
