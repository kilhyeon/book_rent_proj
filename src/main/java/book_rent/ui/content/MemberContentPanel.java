package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.MemGrade;
import book_rent.dto.MemGradeRentCount;
import book_rent.dto.MemberInfo;
import book_rent.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class MemberContentPanel extends AbstractContentPanel<MemberInfo> {
	private JTextField tfMemNo;
	private JTextField tfMemName;
	private JTextField tfMemBirth;
	private JTextField tfMemTel;
	private JTextField tfMemCp;
	private JTextField tfMemGrade;
	private JTextField tfMemAddr;
	private JTextField tfMemRentCount;
	private JTextField tflMemGradeRentCount;

	public MemberContentPanel() {
		initialize();

	}

	private void initialize() {
		setBorder(new TitledBorder(null, "회원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pMem1 = new JPanel();
		add(pMem1);
		pMem1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblMemNo = new JLabel("회원번호 : ");
		pMem1.add(lblMemNo);

		tfMemNo = new JTextField();
		tfMemNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfMemNo.setColumns(10);
		pMem1.add(tfMemNo);

		JLabel lblMemName = new JLabel("회  원  명 : ");
		pMem1.add(lblMemName);

		tfMemName = new JTextField();
		tfMemName.setHorizontalAlignment(SwingConstants.CENTER);
		tfMemName.setColumns(10);
		pMem1.add(tfMemName);

		JPanel pMem2 = new JPanel();
		FlowLayout fl_pMem2 = (FlowLayout) pMem2.getLayout();
		fl_pMem2.setAlignment(FlowLayout.LEFT);
		add(pMem2);

		JLabel lblMemBirth = new JLabel("생년월일 : ");
		pMem2.add(lblMemBirth);

		tfMemBirth = new JTextField();
		tfMemBirth.setHorizontalAlignment(SwingConstants.CENTER);
		pMem2.add(tfMemBirth);
		tfMemBirth.setColumns(10);

		JLabel lblMemTel = new JLabel("전화번호 : ");
		pMem2.add(lblMemTel);

		tfMemTel = new JTextField();
		tfMemTel.setHorizontalAlignment(SwingConstants.CENTER);
		pMem2.add(tfMemTel);
		tfMemTel.setColumns(10);

		JPanel pMem3 = new JPanel();
		FlowLayout fl_pMem3 = (FlowLayout) pMem3.getLayout();
		fl_pMem3.setAlignment(FlowLayout.LEFT);
		add(pMem3);

		JLabel lblMemCp = new JLabel("휴대전화 : ");
		pMem3.add(lblMemCp);

		tfMemCp = new JTextField();
		tfMemCp.setHorizontalAlignment(SwingConstants.CENTER);
		pMem3.add(tfMemCp);
		tfMemCp.setColumns(10);

		JLabel lblMemAddr = new JLabel("주        소 : ");
		pMem3.add(lblMemAddr);

		tfMemAddr = new JTextField();
		tfMemAddr.setHorizontalAlignment(SwingConstants.CENTER);
		pMem3.add(tfMemAddr);
		tfMemAddr.setColumns(10);

		JPanel pMem4 = new JPanel();
		FlowLayout fl_pMem4 = (FlowLayout) pMem4.getLayout();
		fl_pMem4.setAlignment(FlowLayout.LEFT);
		add(pMem4);

		JLabel lblMemGrade = new JLabel("회원등급 : ");
		pMem4.add(lblMemGrade);

		tfMemGrade = new JTextField();
		tfMemGrade.setHorizontalAlignment(SwingConstants.CENTER);
		pMem4.add(tfMemGrade);
		tfMemGrade.setColumns(10);

		JPanel pMem5 = new JPanel();
		pMem5.setVisible(false);
		add(pMem5);

		JLabel lblMemRentCount = new JLabel("대여권수 : ");
		pMem5.add(lblMemRentCount);

		tfMemRentCount = new JTextField();
		tfMemRentCount.setHorizontalAlignment(SwingConstants.CENTER);
		tfMemRentCount.setColumns(10);
		pMem5.add(tfMemRentCount);

		JPanel pMem6 = new JPanel();
		pMem6.setVisible(false);
		add(pMem6);

		JLabel lblMemGradeRentCount = new JLabel("회원등급대여가능권수 :");
		pMem6.add(lblMemGradeRentCount);

		tflMemGradeRentCount = new JTextField();
		pMem6.add(tflMemGradeRentCount);
		tflMemGradeRentCount.setColumns(10);
	}

	@Override
	public void setItem(MemberInfo item) {
//		if (item == null) {
//			throw new NotSelectedException();
//		}
		tfMemNo.setText(String.valueOf(item.getMemNo()));
		tfMemName.setText(item.getMemName());
		tfMemGrade.setText(String.valueOf(item.getMemGradeNo().getMemGradeName()));
		tfMemBirth.setText(item.getMemBirth());
		tfMemAddr.setText(item.getMemAddr());
		tfMemCp.setText(item.getMemCp());
		tfMemTel.setText(item.getMemTel());
		tfMemRentCount.setText(String.valueOf(item.getMemRentCount()));
		tflMemGradeRentCount.setText(String.valueOf(item.getMemGradeRentCount().getMemGradeRentCount()));
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
		MemGrade memGradeNo = new MemGrade(Integer.parseInt(tfMemGrade.getText().trim()));
		MemGradeRentCount memGradeRentCount = new MemGradeRentCount(
				Integer.parseInt(tflMemGradeRentCount.getText().trim()));
		int memRentCount = Integer.parseInt(tfMemRentCount.getText().trim());
		return new MemberInfo(memNo, memName, memBirth, memTel, memCp, memAddr, memGradeNo, memGradeRentCount,
				memRentCount);
	}

	public MemberInfo getItemMemNo() {
		try {
			int memNo = Integer.parseInt(tfMemNo.getText().trim());
			return new MemberInfo(memNo);

		} catch (NumberFormatException | InvalidCheckException e) {
			JOptionPane.showMessageDialog(null, "회원정보를 선택하세요.", "메세지", JOptionPane.WARNING_MESSAGE);
			e.getStackTrace();
			return null;
		}
	}

	public int getItemMemRentCount() {
		try {
			int memRentCount = Integer.parseInt(tfMemRentCount.getText().trim());
			return memRentCount;

		} catch (NumberFormatException | InvalidCheckException e) {
			e.getStackTrace();
			return -1;
		}

	}

	public int getItemMemGradeRentCount() {
		try {
			int memGradeRentCount = Integer.parseInt(tflMemGradeRentCount.getText().trim());
			return memGradeRentCount;

		} catch (NumberFormatException | InvalidCheckException e) {
			e.getStackTrace();
			return -1;
		}

	}

//	public MemberInfo getItemMemName() {
//		int memNo = Integer.parseInt(tfMemNo.getText().trim());
//		String memName = tfMemName.getText().trim();
//		return new MemberInfo(memNo, memName);
//	}

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

//		if (!tfMemNo.isEditable()) {
//			tfMemNo.setEditable(true);
//		}

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

	public JTextField getTfMemRentCount() {
		return tfMemRentCount;
	}

	public void setTfMemRentCount(JTextField tfMemRentCount) {
		this.tfMemRentCount = tfMemRentCount;
	}

	public JTextField getTflMemGradeRentCount() {
		return tflMemGradeRentCount;
	}

	public void setTflMemGradeRentCount(JTextField tflMemGradeRentCount) {
		this.tflMemGradeRentCount = tflMemGradeRentCount;
	}

}
