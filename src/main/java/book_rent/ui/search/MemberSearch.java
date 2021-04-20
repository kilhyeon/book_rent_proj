package book_rent.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import book_rent.dto.MemCp;
import book_rent.dto.MemGradeNo;
import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.list.MemberInfoTablePanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MemberSearch extends JPanel implements ActionListener {
	private JTextField tf;
	private JButton btn;
	private MemberInfoService service;
	private JComboBox<String> cmb;
	private List<MemberInfo> list;
	private MemberInfoTablePanel pMember;

	public MemberSearch() {
		pMember = new MemberInfoTablePanel();
		initialize();
		cmb.setSelectedIndex(-1);
	}

	private void initialize() {

		JLabel lbl = new JLabel("회원검색 : ");
		add(lbl);

		cmb = new JComboBox<String>();
		cmb.setModel(new DefaultComboBoxModel<String>(new String[] { "회원번호", "회원명", "휴대전화", "회원등급" }));
		add(cmb);

		tf = new JTextField();
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		add(tf);
		tf.setColumns(15);

		btn = new JButton("검색");
		btn.addActionListener(this);
		add(btn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			actionPerformedBtn(e);
		}
	}

	protected void actionPerformedBtn(ActionEvent e) {
		try {
			String selectItem = (String) cmb.getSelectedItem();

			list = selectModel(selectItem);

			if (list != null) {
				pMember.setService(service);
				pMember.setList(list);
				pMember.setList();
			} else {
				System.out.println("다시입력");
			}

		} catch (Exception e1) {
			e1.getStackTrace();
		}
	}

	public List<MemberInfo> selectModel(String model) {
		try {

			if (model.equals("회원번호")) {
				list = service.showMemberListByNo(new MemberInfo(Integer.parseInt(tf.getText())));
			}
			if (model.equals("회원명")) {
				list = service.showMemberListByName(new MemberInfo(tf.getText()));
			}
			if (model.equals("휴대전화")) {
				list = service.showMemberListByCp(new MemCp(tf.getText()));
			}
			if (model.equals("회원등급")) {
				list = service.showMemberListByGrade(new MemGradeNo(Integer.parseInt(tf.getText())));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return list;
	}

	public void setService(MemberInfoService service) {
		this.service = service;
	}

//	public List<MemberInfo> getList() {
//		return list;
//	}

	public MemberInfoTablePanel getMemberInfoList() {
		return pMember;
	}

	public void clearTf() {
		tf.setText("");
	}
}
