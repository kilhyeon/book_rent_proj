package book_rent.ui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.content.AbstractContentPanel;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.list.AbstractCustomTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;

@SuppressWarnings("serial")
public class MemberManagerUI extends AbstractManagerUI<MemberInfo> {
	private MemberInfoService service;

	public MemberManagerUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
	}

	@Override
	protected void setService() {
		service = new MemberInfoService();
	}

	@Override
	protected void tableLoadData() {
		((MemberInfoTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<MemberInfo> createContentPanel() {
		return new MemberContentPanel();
	}

	@Override
	protected AbstractCustomTablePanel<MemberInfo> createTablePanel() {
		return new MemberInfoTablePanel();
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		MemberInfo updateMember = pList.getItem();
		pContent.setItem(updateMember);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		MemberInfo delMember = pList.getItem();
		service.removeMember(delMember);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delMember + "삭제 되었습니다.");
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		MemberInfo updateMember = pContent.getItem();
		service.modifyMember(updateMember);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateMember.getMemName() + "정보가 수정되었습니다.");

	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		MemberInfo addMember = pContent.getItem();
		service.addMember(addMember);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addMember + " 추가했습니다.");
	}
	
	
	
}
