package book_rent.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import book_rent.dto.MemberInfo;
import book_rent.service.MemberInfoService;
import book_rent.service.RentService;
import book_rent.ui.content.MemContentManagement;
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.MemRentTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.list.RentTablePanel;
import book_rent.ui.search.MemberSearch;

public class MemManagerUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private MemberInfoService memService;
	private RentService rentService;
	private MemberInfoTablePanel pMemInfoTableList;
	private MemberInfoTablePanel pMemInfoTableList2;
	private MemberContentPanel pMemInfoContent;
	private MemContentManagement pMemManageContent;
	private MemberSearch pMemSearch;

	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pBtn;
	private JPanel pGrade;
	private MemGradeManagerUI pMemGradeManagerUI;
	private JTabbedPane tabbedPane;
	private JLabel lblMent;
	private MemRentTablePanel pMemRentTableList;

	public MemManagerUI() {
		setResizable(false);
		setTitle("회원관리");
		memService = new MemberInfoService();
		rentService = new RentService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();
		pMemInfoTableList2.setService(memService);
		pMemInfoTableList2.loadData();
		pMemRentTableList.setRentService(rentService);
		pMemRentTableList.loadData();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(this);
		contentPane.add(tabbedPane);

		JPanel pMem = new JPanel();
//		tabbedPane.addTab("회원정보", null, pMem, null);
		pMem.setLayout(new BoxLayout(pMem, BoxLayout.Y_AXIS));

		pMemSearch = new MemberSearch();
		pMem.add(pMemSearch);
		pMemSearch.setService(memService);

		pMemInfoTableList = pMemSearch.getMemberInfoList();
		pMem.add(pMemInfoTableList);
		pMemInfoTableList.setService(memService);
		pMemInfoTableList.loadData();

		pMemInfoContent = pMemInfoTableList.getpMemInfo();
		pMem.add(pMemInfoContent);

		pMemRentTableList = pMemInfoTableList.getMemRentList();

		pMem.add(pMemRentTableList);
		pMemRentTableList.setRentService(rentService);
		pMemRentTableList.loadData();

		JPanel pMemMangement = new JPanel();
		tabbedPane.addTab("회원관리", null, pMemMangement, null);

		pMemMangement.setLayout(new BoxLayout(pMemMangement, BoxLayout.Y_AXIS));

		pMemSearch = new MemberSearch();
		pMemMangement.add(pMemSearch);
		pMemSearch.setService(memService);

		pMemInfoTableList2 = pMemSearch.getMemberInfoList();
		pMemMangement.add(pMemInfoTableList2);
		pMemInfoTableList2.setService(memService);
		pMemInfoTableList2.loadData();

		pMemManageContent = new MemContentManagement();
		pMemMangement.add(pMemManageContent);

		lblMent = new JLabel("마우스 우클릭으로 수정, 삭제 ");
		lblMent.setHorizontalAlignment(SwingConstants.LEFT);
		pMemInfoTableList2.add(lblMent, BorderLayout.NORTH);

		pBtn = new JPanel();
		pMemMangement.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pGrade = new JPanel();
		tabbedPane.addTab("등급관리", null, pGrade, null);
		pGrade.setLayout(new BoxLayout(pGrade, BoxLayout.X_AXIS));

		pMemGradeManagerUI = new MemGradeManagerUI();
		pGrade.add(pMemGradeManagerUI);

		JPopupMenu popupMenu = createPopupMenu();
		pMemInfoTableList2.setPopupMenu(popupMenu);
	}

	public MemberInfoTablePanel getpListMem() {
		return pMemInfoTableList;
	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pMemInfoTableList = pListMem;
	}

	public MemberInfoTablePanel getpListMem2() {
		return pMemInfoTableList2;
	}

	public void setpListMem2(MemberInfoTablePanel pListMem2) {
		this.pMemInfoTableList2 = pListMem2;
	}

	public MemRentTablePanel getpRentList() {
		return pMemRentTableList;
	}

	public void setpRentList(MemRentTablePanel pRentList) {
		this.pMemRentTableList = pRentList;
	}

	public MemberContentPanel getpMemInfo() {
		return pMemInfoContent;
	}

	public void setpMemInfo(MemberContentPanel pMemInfo) {
		this.pMemInfoContent = pMemInfo;
	}

	public MemContentManagement getpMemInfo2() {
		return pMemManageContent;
	}

	public void setpMemInfo2(MemContentManagement pMemInfo2) {
		this.pMemManageContent = pMemInfo2;
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
		MemberInfo updateMember = pMemManageContent.getItem();
		memService.modifyMember(updateMember);
		pMemInfoTableList.loadData();
		pMemInfoTableList2.loadData();
		pMemManageContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateMember.getMemName() + " 회원의 정보를 수정하였습니다.");

	}

	private void actionPerformedMenuUpdate() {
		try {

			MemberInfo updateMember = pMemInfoTableList2.getItem();
			pMemManageContent.setItem(updateMember);
			btnAdd.setText("수정");

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "회원정보를 선택하세요.");
		}

	}

	private void actionPerformedMenuDelete() {
		try {

			MemberInfo delMember = pMemInfoTableList2.getItem();
			memService.removeMember(delMember);
			pMemInfoTableList.loadData();
			pMemInfoTableList2.loadData();
			JOptionPane.showMessageDialog(null, delMember.getMemName() + " 회원의 정보를 삭제하였습니다.");

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "회원정보를 선택하세요.");
		}

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		try {

			MemberInfo addMember = pMemManageContent.getItem();
			memService.addMember(addMember);
			pMemInfoTableList.loadData();
			pMemInfoTableList2.loadData();
			pMemManageContent.clearTf();
			tableLoadData();
			JOptionPane.showMessageDialog(null, addMember.getMemName() + " 회원을 추가하였습니다.");
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
		}

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemManageContent.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tabbedPane) {
			mouseClickedTabbedPane(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void mouseClickedTabbedPane(MouseEvent e) {
		pMemManageContent.selectCmb();
		tableLoadData();
	}
}
