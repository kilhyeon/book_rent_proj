package book_rent.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.search.MemberSearch;

public class MemMangerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private MemberInfoService memService;
	private MemberInfoTablePanel pListMem;
	private MemberContentPanel pMemInfo;
	private MemberSearch pSearchMem;
	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pBtn;

	public MemMangerUI() {
		memService = new MemberInfoService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pListMem.setService(memService);
		pListMem.loadData();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel pMemList = new JPanel();
		tabbedPane.addTab("회원정보", null, pMemList, null);
		pMemList.setLayout(new BoxLayout(pMemList, BoxLayout.Y_AXIS));

		pSearchMem = new MemberSearch();
		pMemList.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem = pSearchMem.getMemberInfoList();
		pMemList.add(pListMem);
		pListMem.setService(memService);
		pListMem.loadData();

		pMemInfo = pListMem.getpMemInfo();
		pMemList.add(pMemInfo);

		JPanel pRentList = new JPanel();
		pMemList.add(pRentList);

		JPanel pMemMangement = new JPanel();
		tabbedPane.addTab("회원관리", null, pMemMangement, null);
		pMemMangement.setLayout(new BoxLayout(pMemMangement, BoxLayout.Y_AXIS));

		pSearchMem = new MemberSearch();
		pMemMangement.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem = new MemberInfoTablePanel();
		pMemMangement.add(pListMem);
		pListMem.setService(memService);
		pListMem.loadData();

		pMemInfo = new MemberContentPanel();
		pMemMangement.add(pMemInfo);

		pBtn = new JPanel();
		pMemMangement.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		JPopupMenu popupMenu = createPopupMenu();
		pListMem.setPopupMenu(popupMenu);
	}

	public MemberInfoTablePanel getpListMem() {
		return pListMem;
	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pListMem = pListMem;
	}

	public MemberContentPanel getpMemInfo() {
		return pMemInfo;
	}

	public void setpMemInfo(MemberContentPanel pMemInfo) {
		this.pMemInfo = pMemInfo;
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}

			} else {
				if (e.getSource() == btnCancel) {
					actionPerformedBtnCancel(e);
				}

				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		MemberInfo updateMember = pMemInfo.getItem();
		memService.modifyMember(updateMember);
		pListMem.loadData();
		pMemInfo.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateMember.getMemName() + "정보가 수정되었습니다.");

	}

	private void actionPerformedMenuUpdate() {
		MemberInfo updateMember = pListMem.getItem();
		pMemInfo.setItem(updateMember);
		btnAdd.setText("수정");

	}

	private void actionPerformedMenuDelete() {
		MemberInfo delMember = pListMem.getItem();
		memService.removeMember(delMember);
		pListMem.loadData();
		JOptionPane.showMessageDialog(null, delMember + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		MemberInfo addMember = pMemInfo.getItem();
		memService.addMember(addMember);
		pListMem.loadData();
		pMemInfo.clearTf();
		JOptionPane.showMessageDialog(null, addMember + " 추가했습니다.");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemInfo.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
