package book_rent.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemGrade;
import book_rent.service.MemGradeService;
import book_rent.ui.exception.InvalidCheckException;

public class MemGradeContnetPanel extends AbstractContentPanel<MemGrade> {
	private JTextField tfGradeNo;
	private JTextField tfGradeName;
	private MemGradeService service;

	public MemGradeContnetPanel() {
		service = new MemGradeService();
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "회원등급정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pGradeNo = new JPanel();
		add(pGradeNo);

		JLabel lblGradeNo = new JLabel("회원등급번호 : ");
		pGradeNo.add(lblGradeNo);

		tfGradeNo = new JTextField();
		pGradeNo.add(tfGradeNo);
		tfGradeNo.setColumns(10);

		JPanel pGradeName = new JPanel();
		add(pGradeName);

		JLabel lblGradeName = new JLabel("회원등급이름 : ");
		pGradeName.add(lblGradeName);

		tfGradeName = new JTextField();
		pGradeName.add(tfGradeName);
		tfGradeName.setColumns(10);
	}

	@Override
	public void setItem(MemGrade item) {
		tfGradeNo.setText(String.valueOf(item.getMemGradeNo()));
		tfGradeName.setText(item.getMemGradeName());
		tfGradeNo.setEditable(false);
	}

	@Override
	public MemGrade getItem() {
		validCheck();
		int memGradeNo = Integer.parseInt(tfGradeNo.getText().trim());
		String memGradeName = tfGradeName.getText().trim();
		return new MemGrade(memGradeNo, memGradeName);
	}

	@Override
	public void validCheck() {
		if (tfGradeNo.getText().equals("")) {
			throw new InvalidCheckException();
		}

	}

	@Override
	public void clearTf() {
		tfGradeNo.setText("");
		tfGradeName.setText("");

		if (!tfGradeNo.isEditable()) {
			tfGradeNo.setEditable(true);
		}
	}

	public JTextField getTfGradeNo() {
		return tfGradeNo;
	}

	public void setTfGradeNo(JTextField tfGradeNo) {
		this.tfGradeNo = tfGradeNo;
	}

	public JTextField getTfGradeName() {
		return tfGradeName;
	}

	public void setTfGradeName(JTextField tfGradeName) {
		this.tfGradeName = tfGradeName;
	}

}
