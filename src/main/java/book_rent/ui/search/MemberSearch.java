package book_rent.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book_rent.service.MemberInfoService;
import book_rent.ui.content.MemberInfo;
import book_rent.ui.list.MemberInfoTablePanel;

@SuppressWarnings("serial")
public class MemberSearch extends JPanel implements ActionListener {
	private JTextField tf;
	private JButton btn;
	private MemberInfoService memService;
	private JComboBox<String> cmb;
	private List<MemberInfo> memList;
	private MemberInfoTablePanel pMemInfoTableList;

	public MemberSearch() {
		pMemInfoTableList = new MemberInfoTablePanel();
		memList = new ArrayList<MemberInfo>();
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
			String selectItem = cmb.getSelectedItem().toString();
			memList = item(selectItem);
			if (memList != null) {
				pMemInfoTableList.setList(memList);
				pMemInfoTableList.setList();
			} else {
				pMemInfoTableList.setList(memList);
				pMemInfoTableList.loadData();
			}

		} catch (Exception e1) {
			e1.getStackTrace();
		}
	}

	public List<MemberInfo> item(String model) {
		try {

			if (model.equals("회원번호")) {
				memList = memService.showMemberListByNo(new MemberInfo(Integer.parseInt(tf.getText())));
			}
			if (model.equals("회원명")) {
				memList = memService.showMemberListByName(new MemberInfo(tf.getText()));
			}
			if (model.equals("휴대전화")) {
				memList = memService.showMemberListByCp(new MemberInfo(tf.getText()));
			}
			if (model.equals("회원등급")) {
				memList = memService.showMemberListByGrade(new MemberInfo(Integer.parseInt(tf.getText())));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return memList;
	}

	public void setService(MemberInfoService service) {
		this.memService = service;
	}

//	public List<MemberInfo> getList() {
//		return list;
//	}

	public MemberInfoTablePanel getMemberInfoList() {
		return pMemInfoTableList;
	}

	public void clearTf() {
		tf.setText("");
	}
}
