package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemberInfo;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class MemberContentPanel extends AbstractContentPanel<MemberInfo> {
	private JTextField tfMemNo;
	private JTextField tfMemName;
	private JTextField tfMemBirth;
	private JTextField tfMemTel;
	private JTextField tfMemCp;
	private JTextField tfMemGrade;
	private JTextField tfMemAddr;

	public MemberContentPanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "회원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblMemNo = new JLabel("회원번호 : ");
		panel_1.add(lblMemNo);

		tfMemNo = new JTextField();
		tfMemNo.setColumns(10);
		panel_1.add(tfMemNo);

		JLabel lblMemName = new JLabel("회  원  명 : ");
		panel_1.add(lblMemName);

		tfMemName = new JTextField();
		tfMemName.setColumns(10);
		panel_1.add(tfMemName);

		JPanel panel_2 = new JPanel();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setAlignment(FlowLayout.LEFT);
		add(panel_2);

		JLabel lblMemBirth = new JLabel("생년월일 : ");
		panel_2.add(lblMemBirth);

		tfMemBirth = new JTextField();
		panel_2.add(tfMemBirth);
		tfMemBirth.setColumns(10);

		JLabel lblMemTel = new JLabel("전화번호 : ");
		panel_2.add(lblMemTel);

		tfMemTel = new JTextField();
		panel_2.add(tfMemTel);
		tfMemTel.setColumns(10);

		JPanel panel_3 = new JPanel();
		FlowLayout fl_panel_3 = (FlowLayout) panel_3.getLayout();
		fl_panel_3.setAlignment(FlowLayout.LEFT);
		add(panel_3);

		JLabel lblMemCp = new JLabel("휴대전화 : ");
		panel_3.add(lblMemCp);

		tfMemCp = new JTextField();
		panel_3.add(tfMemCp);
		tfMemCp.setColumns(10);

		JLabel lblMemAddr = new JLabel("주        소 : ");
		panel_3.add(lblMemAddr);

		tfMemAddr = new JTextField();
		panel_3.add(tfMemAddr);
		tfMemAddr.setColumns(10);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_4);

		JLabel lblMemGrade = new JLabel("회원등급 : ");
		panel_4.add(lblMemGrade);

		tfMemGrade = new JTextField();
		panel_4.add(tfMemGrade);
		tfMemGrade.setColumns(10);
	}

	@Override
	public void setItem(MemberInfo item) {
//		if (item == null) {
//			throw new NotSelectedException();
//		}
		tfMemNo.setText(String.valueOf(item.getMemNo()));
		tfMemName.setText(item.getMemName());
		tfMemGrade.setText(String.valueOf(item.getMemGradeNo()));
		tfMemBirth.setText(item.getMemBirth());
		tfMemAddr.setText(item.getMemAddr());
		tfMemCp.setText(item.getMemCp());
		tfMemTel.setText(item.getMemTel());

		tfMemNo.setEditable(false);

	}

	@Override
	public MemberInfo getItem() {
		validCheck();
		int memNo = Integer.parseInt(tfMemNo.getText().trim());
		String memName = tfMemName.getText().trim();
		String memBirth = tfMemBirth.getText().trim();
		String memTel = tfMemTel.getText().trim();
		String memCp = tfMemCp.getText().trim();
		String memAddr = tfMemAddr.getText().trim();
		int memGradeNo = Integer.parseInt(tfMemGrade.getText().trim());
		return new MemberInfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo);
	}

	@Override
	public void validCheck() {
		if (tfMemNo.getText().equals("")) {
			throw new InvalidCheckException();
		}
	}

	@Override
	public void clearTf() {
		tfMemNo.setText("");
		tfMemName.setText("");
		tfMemGrade.setText("");
		tfMemBirth.setText("");
		tfMemAddr.setText("");
		tfMemCp.setText("");
		tfMemTel.setText("");

		if (!tfMemNo.isEditable()) {
			tfMemNo.setEditable(true);
		}

	}

	public JTextField getTfMemNo() {
		return tfMemNo;
	}

	public void setTfMemNo(JTextField tfMemNo) {
		this.tfMemNo = tfMemNo;
	}

	public JTextField getTfMemName() {
		return tfMemName;
	}

	public void setTfMemName(JTextField tfMemName) {
		this.tfMemName = tfMemName;
	}

	public JTextField getTfMemBirth() {
		return tfMemBirth;
	}

	public void setTfMemBirth(JTextField tfMemBirth) {
		this.tfMemBirth = tfMemBirth;
	}

	public JTextField getTfMemTel() {
		return tfMemTel;
	}

	public void setTfMemTel(JTextField tfMemTel) {
		this.tfMemTel = tfMemTel;
	}

	public JTextField getTfMemCp() {
		return tfMemCp;
	}

	public void setTfMemCp(JTextField tfMemCp) {
		this.tfMemCp = tfMemCp;
	}

	public JTextField getTfMemGrade() {
		return tfMemGrade;
	}

	public void setTfMemGrade(JTextField tfMemGrade) {
		this.tfMemGrade = tfMemGrade;
	}

	public JTextField getTfMemAddr() {
		return tfMemAddr;
	}

	public void setTfMemAddr(JTextField tfMemAddr) {
		this.tfMemAddr = tfMemAddr;
	}

}
