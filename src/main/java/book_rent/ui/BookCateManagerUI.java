package book_rent.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import book_rent.dto.BookCate;
import book_rent.service.BookCateService;
import book_rent.ui.content.BookCateContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.BookCateTablePanel;

public class BookCateManagerUI extends JPanel implements ActionListener {
	private BookCateTablePanel pBookCateTableList;
	private BookCateContentPanel pBookCateContent;
	private JButton btnAdd;
	private JButton btnCancel;
	private BookCateService bookCateService;
	private JLabel lblMent;

	public BookCateManagerUI() {
		bookCateService = new BookCateService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pBookCateTableList.setService(bookCateService);
		pBookCateTableList.loadData();

	}

	private void initialize() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pBookCateTableList = new BookCateTablePanel();
		add(pBookCateTableList);

		pBookCateContent = new BookCateContentPanel();
		add(pBookCateContent);

		JPanel pBtn = new JPanel();
		add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		JPopupMenu popupMenu = createPopupMenu();
		pBookCateTableList.setPopupMenu(popupMenu);
		
		lblMent = new JLabel("마우스 우클릭으로 수정, 삭제 ");
		lblMent.setHorizontalAlignment(SwingConstants.LEFT);
		pBookCateTableList.add(lblMent, BorderLayout.NORTH);
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

	public BookCateTablePanel getpList() {
		return pBookCateTableList;
	}

	public void setpList(BookCateTablePanel pList) {
		this.pBookCateTableList = pList;
	}

	public BookCateContentPanel getpContent() {
		return pBookCateContent;
	}

	public void setpContent(BookCateContentPanel pContent) {
		this.pBookCateContent = pContent;
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
		BookCate updateCate = pBookCateContent.getItem();
		bookCateService.modifyCate(updateCate);
		pBookCateTableList.loadData();
		pBookCateContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateCate.getBookCateName() + "정보가 수정되었습니다.");

	}

	protected void actionPerformedMenuUpdate() {
		BookCate updateCate = pBookCateTableList.getItem();
		pBookCateContent.setItem(updateCate);
		btnAdd.setText("수정");

	}

	protected void actionPerformedMenuDelete() {
		BookCate delCate = pBookCateTableList.getItem();
		bookCateService.removeCate(delCate);
		pBookCateTableList.loadData();
		JOptionPane.showMessageDialog(null, delCate + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookCate addCate = pBookCateContent.getItem();
		bookCateService.addCate(addCate);
		pBookCateTableList.loadData();
		pBookCateContent.clearTf();
		JOptionPane.showMessageDialog(null, addCate + " 추가했습니다.");

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pBookCateContent.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

}
