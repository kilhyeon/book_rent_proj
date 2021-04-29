package book_rent.ui.list;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemGrade;
import book_rent.service.MemGradeService;
import book_rent.ui.content.MemGradeContnetPanel;
import book_rent.ui.exception.NotSelectedException;

public class MemGradeTablePanel extends AbstractCustomTablePanel<MemGrade> {
	private MemGradeService memGradeService;
	private MemGradeContnetPanel pMemGradeContent;

	public MemGradeTablePanel() {
		initialize();
		pMemGradeContent = new MemGradeContnetPanel();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "등급목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public MemGradeContnetPanel getpMemGrade() {
		return pMemGradeContent;
	}

	public void setService(MemGradeService service) {
		this.memGradeService = service;
	}

	@Override
	public MemGrade getItem() {
		int row = table.getSelectedRow();
		int memGradeNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}

		return list.get(list.indexOf(new MemGrade(memGradeNo)));
	}

	@Override
	public void initList() {
		list = memGradeService.showMemGradeByAll();

	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);

		// 컬럼별 너비 조정
		setTableCellWidth(200, 200, 200);

	}

	@Override
	public Object[] toArray(MemGrade g) {
		return new Object[] { g.getMemGradeNo(), g.getMemGradeName(), g.getMemGradeRentCount() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "등급번호", "등급이름", "대여가능권수" };
	}

}
