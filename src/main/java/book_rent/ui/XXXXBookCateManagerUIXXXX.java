package book_rent.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import book_rent.dto.BookCate;
import book_rent.service.BookCateService;
import book_rent.ui.content.BookCateContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.BookCateTablePanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XXXXBookCateManagerUIXXXX extends JFrame implements ActionListener {
	private BookCateService service;
	private JPanel contentPane;
	private BookCateContentPanel pContent;
	private BookCateTablePanel pList;
	private JButton btnAdd;
	private JButton btnCancel;

	public XXXXBookCateManagerUIXXXX() {
		service = new BookCateService();
		initialize();
		tableLoadData();
	}

	private void tableLoadData() {
		pList.setService(service);
		pList.loadData();

	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pList = new BookCateTablePanel();
		contentPane.add(pList);

		pContent = new BookCateContentPanel();
		contentPane.add(pContent);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
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

	public BookCateContentPanel getpContent() {
		return pContent;
	}

	public void setpContent(BookCateContentPanel pContent) {
		this.pContent = pContent;
	}

	public BookCateTablePanel getpList() {
		return pList;
	}

	public void setpList(BookCateTablePanel pList) {
		this.pList = pList;
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
		BookCate updateCate = pContent.getItem();
		service.modifyCate(updateCate);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateCate.getBookCateName() + "정보가 수정되었습니다.");

	}

	protected void actionPerformedMenuUpdate() {
		BookCate updateCate = pList.getItem();
		pContent.setItem(updateCate);
		btnAdd.setText("수정");

	}

	protected void actionPerformedMenuDelete() {
		BookCate delCate = pList.getItem();
		service.removeCate(delCate);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delCate + "삭제 되었습니다.");

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		BookCate addCate = pContent.getItem();
		service.addCate(addCate);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addCate + " 추가했습니다.");

	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
