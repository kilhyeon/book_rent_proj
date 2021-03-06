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
import javax.swing.border.EmptyBorder;

import book_rent.ui.content.AbstractContentPanel;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.SqlConstraintException;
import book_rent.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnCancel;

	protected AbstractContentPanel<T> pList;
	protected AbstractCustomTablePanel<T> pContent;

	public AbstractManagerUI() {
		setService(); // service 연결
		initialize();
		tableLoadData(); // component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createTablePanel();
		contentPane.add(pContent);
//		pContent.setLayout(new GridLayout(1, 0, 0, 0));

//		JPanel pBtn = new JPanel();
//		contentPane.add(pBtn);
//
//		btnAdd = new JButton("추가");
//		btnAdd.addActionListener(this);
//		pBtn.add(btnAdd);
//
//		btnCancel = new JButton("취소");
//		btnCancel.addActionListener(this);
//		pBtn.add(btnCancel);
//
//		pList = createTablePanel();
//		contentPane.add(pList);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		pList = createContentPanel();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pContent.setPopupMenu(popupMenu);
		
	}

	protected abstract void setService();

	protected abstract void tableLoadData();

	protected abstract AbstractContentPanel<T> createContentPanel();

	protected abstract AbstractCustomTablePanel<T> createTablePanel();

	protected abstract void actionPerformedMenuUpdate();

	protected abstract void actionPerformedMenuDelete();

	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	protected abstract void actionPerformedBtnAdd(ActionEvent e);

	private void actionPerformedBtnCancel(ActionEvent e) {
		pList.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
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

}
