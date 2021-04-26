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
import book_rent.ui.content.MemberContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.MemRentTablePanel;
import book_rent.ui.list.MemberInfoTablePanel;
import book_rent.ui.list.RentTablePanel;
import book_rent.ui.management.MemManagement;
import book_rent.ui.search.MemberSearch;

public class MemManagerUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private MemberInfoService memService;
	private RentService rentService;
	private MemberInfoTablePanel pListMem;
	private MemberInfoTablePanel pListMem2;
	private MemberContentPanel pMemInfo;
	private MemManagement pMemManage;
	private MemberSearch pSearchMem;

	private JButton btnAdd;
	private JButton btnCancel;
	private JPanel pBtn;
	private JPanel pGrade;
	private MemGradeManagerUI pGradeManage;
	private JTabbedPane tabbedPane;
	private JLabel lblMent;
	private MemRentTablePanel pRentList;

	public MemManagerUI() {
		memService = new MemberInfoService();
		rentService = new RentService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pListMem.setService(memService);
		pListMem.loadData();
		pListMem2.setService(memService);
		pListMem2.loadData();
		pRentList.setService(rentService);
		pRentList.loadData();
	}

//	public void LoadData() {
//		pListMem.loadData();
//		pListMem2.loadData();
//		
//	}

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
		pMem.setVisible(false);
		tabbedPane.addTab("회원정보", null, pMem, null);
		pMem.setLayout(new BoxLayout(pMem, BoxLayout.Y_AXIS));

		pSearchMem = new MemberSearch();
		pMem.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem = pSearchMem.getMemberInfoList();
		pMem.add(pListMem);
		pListMem.setService(memService);
		pListMem.loadData();

		pMemInfo = pListMem.getpMemInfo();
		pMem.add(pMemInfo);

		pRentList = pListMem.getMemRentList();
		
		pMem.add(pRentList);
		pRentList.setService(rentService);
		pRentList.loadData();

		JPanel pMemMangement = new JPanel();
		tabbedPane.addTab("회원관리", null, pMemMangement, null);

		pMemMangement.setLayout(new BoxLayout(pMemMangement, BoxLayout.Y_AXIS));

		pSearchMem = new MemberSearch();
		pMemMangement.add(pSearchMem);
		pSearchMem.setService(memService);

		pListMem2 = pSearchMem.getMemberInfoList();
		pMemMangement.add(pListMem2);
		pListMem2.setService(memService);
		pListMem2.loadData();

		pMemManage = new MemManagement();
		pMemMangement.add(pMemManage);

		lblMent = new JLabel("마우스 우클릭으로 수정, 삭제 ");
		lblMent.setHorizontalAlignment(SwingConstants.LEFT);
		pListMem2.add(lblMent, BorderLayout.NORTH);

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

		pGradeManage = new MemGradeManagerUI();
		pGrade.add(pGradeManage);

		JPopupMenu popupMenu = createPopupMenu();
		pListMem2.setPopupMenu(popupMenu);
	}

	public MemberInfoTablePanel getpListMem() {
		return pListMem;
	}

	public void setpListMem(MemberInfoTablePanel pListMem) {
		this.pListMem = pListMem;
	}

	public MemberInfoTablePanel getpListMem2() {
		return pListMem2;
	}

	public void setpListMem2(MemberInfoTablePanel pListMem2) {
		this.pListMem2 = pListMem2;
	}

	public MemRentTablePanel getpRentList() {
		return pRentList;
	}

	public void setpRentList(MemRentTablePanel pRentList) {
		this.pRentList = pRentList;
	}

	public MemberContentPanel getpMemInfo() {
		return pMemInfo;
	}

	public void setpMemInfo(MemberContentPanel pMemInfo) {
		this.pMemInfo = pMemInfo;
	}

	public MemManagement getpMemInfo2() {
		return pMemManage;
	}

	public void setpMemInfo2(MemManagement pMemInfo2) {
		this.pMemManage = pMemInfo2;
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
		MemberInfo updateMember = pMemManage.getItem();
		memService.modifyMember(updateMember);
		pListMem.loadData();
		pListMem2.loadData();
		pMemManage.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateMember.getMemName() + "정보가 수정되었습니다.");

	}

	private void actionPerformedMenuUpdate() {
		MemberInfo updateMember = pListMem2.getItem();
		pMemManage.setItem(updateMember);
		btnAdd.setText("수정");

	}

	private void actionPerformedMenuDelete() {
		MemberInfo delMember = pListMem2.getItem();
		memService.removeMember(delMember);
		pListMem.loadData();
		pListMem2.loadData();
		JOptionPane.showMessageDialog(null, delMember + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		MemberInfo addMember = pMemManage.getItem();
		memService.addMember(addMember);
		pListMem.loadData();
		pListMem2.loadData();
		pMemManage.clearTf();
		tableLoadData();
		JOptionPane.showMessageDialog(null, addMember + " 추가했습니다.");
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemManage.clearTf();

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
		pMemManage.selectCmb();
		tableLoadData();
	}
}
