package book_rent.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import book_rent.dto.MemGrade;
import book_rent.service.MemGradeService;
import book_rent.ui.content.MemGradeContnetPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.MemGradeTablePanel;
import book_rent.ui.management.MemManagement;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class MemGradeManagerUI extends JPanel implements ActionListener {
	private MemGradeTablePanel pMemGradeTableList;
	private MemGradeContnetPanel pMemGradeContent;
	private JButton btnAdd;
	private JButton btnCancel;
	private MemGradeService memGradeService;
//	private MemManagement memManagement;
	private JLabel lblMent;

	public MemGradeManagerUI() {
		memGradeService = new MemGradeService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pMemGradeTableList.setService(memGradeService);
		pMemGradeTableList.loadData();

	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pMemGradeTableList = new MemGradeTablePanel();
		add(pMemGradeTableList);

		pMemGradeContent = new MemGradeContnetPanel();
		add(pMemGradeContent);

		JPanel pBtn = new JPanel();
		add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		JPopupMenu popupMenu = createPopupMenu();
		pMemGradeTableList.setPopupMenu(popupMenu);

		lblMent = new JLabel("마우스 우클릭으로 수정, 삭제 ");
		lblMent.setHorizontalAlignment(SwingConstants.LEFT);
		pMemGradeTableList.add(lblMent, BorderLayout.NORTH);

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

	public MemGradeTablePanel getpList() {
		return pMemGradeTableList;
	}

	public void setpList(MemGradeTablePanel pList) {
		this.pMemGradeTableList = pList;
	}

	public MemGradeContnetPanel getpContent() {
		return pMemGradeContent;
	}

	public void setpContent(MemGradeContnetPanel pContent) {
		this.pMemGradeContent = pContent;
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
		MemGrade updateGrade = pMemGradeContent.getItem();
		memGradeService.modifyGrade(updateGrade);
		pMemGradeTableList.loadData();
		pMemGradeContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateGrade.getMemGradeName() + "정보가 수정되었습니다.");

	}

	protected void actionPerformedMenuUpdate() {
		MemGrade updateGrade = pMemGradeTableList.getItem();
		pMemGradeContent.setItem(updateGrade);
		btnAdd.setText("수정");

	}

	protected void actionPerformedMenuDelete() {
		MemGrade delGrade = pMemGradeTableList.getItem();
		memGradeService.removeGrade(delGrade);
		pMemGradeTableList.loadData();
		JOptionPane.showMessageDialog(null, delGrade + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		MemGrade addGrade = pMemGradeContent.getItem();
		memGradeService.addGrade(addGrade);
		pMemGradeTableList.loadData();
		pMemGradeContent.clearTf();
		JOptionPane.showMessageDialog(null, addGrade + " 추가했습니다.");

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pMemGradeContent.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

}
