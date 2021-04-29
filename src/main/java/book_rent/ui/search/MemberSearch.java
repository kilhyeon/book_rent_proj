package book_rent.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.list.MemberInfoTablePanel;

@SuppressWarnings("serial")
public class MemberSearch extends JPanel implements ActionListener {
	private JTextField tf;
	private JButton btn;
	private MemberInfoService memService;
	private JComboBox<String> cmb;
	private List<MemberInfo> memSearchList;
	private MemberInfoTablePanel pMemInfoTableList;

	public MemberSearch() {
		pMemInfoTableList = new MemberInfoTablePanel();
		memSearchList = new ArrayList<MemberInfo>();
		initialize();
		cmb.setSelectedIndex(-1);
	}

	private void initialize() {

		JLabel lbl = new JLabel("회원검색 : ");
		add(lbl);

		cmb = new JComboBox<String>();
		cmb.setModel(new DefaultComboBoxModel<String>(new String[] { "회원번호", "회원명" }));
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
			String selectItem = (cmb.getSelectedItem()).toString();
			
			
			memSearchList = item(selectItem);
			if (memSearchList != null) {
				pMemInfoTableList.setList(memSearchList);
				pMemInfoTableList.setList();
			} else {
				JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.");
				pMemInfoTableList.loadData();
				clearTf();
			}

		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "검색 방법을 선택하세요.");
//			pMemInfoTableList.setList(memSearchList);
			pMemInfoTableList.loadData();
		}
	}

	public List<MemberInfo> item(String item) {
		try {

			if (item.equals("회원번호")) {
				memSearchList = memService.showMemberListByNo(new MemberInfo(Integer.parseInt(tf.getText())));
			}
			if (item.equals("회원명")) {
				memSearchList = memService.showMemberListByName(new MemberInfo(tf.getText()));
			}
//			if (item.equals("휴대전화")) {
//				memSearchList = memService.showMemberListByCp(new MemberInfo(tf.getText()));
//			}
//			if (item.equals("회원등급")) {
//				memSearchList = memService.showMemberListByGrade(new MemberInfo(Integer.parseInt(tf.getText())));
//			}

		} catch (NumberFormatException e) {
			e.getStackTrace();
			pMemInfoTableList.loadData();

		}

		return memSearchList;
	}

	public void setService(MemberInfoService service) {
		this.memService = service;
	}

	public List<MemberInfo> getMemSearchList() {
		return memSearchList;
	}

	public void setMemSearchList(List<MemberInfo> memSearchList) {
		this.memSearchList = memSearchList;
	}

	public MemberInfoTablePanel getMemberInfoList() {
		return pMemInfoTableList;
	}

	public void clearTf() {
		tf.setText("");
	}
}
