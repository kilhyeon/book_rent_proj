package book_rent.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.list.BookInfoTablePanel;

@SuppressWarnings("serial")
public class BookSearch extends JPanel implements ActionListener {
	private JTextField tf;
	private JButton btn;
	private BookInfoService service;
	private JComboBox<String> cmb;
	private List<BookInfo> list;
	private BookInfoTablePanel pBook;

	public BookSearch() {
		pBook = new BookInfoTablePanel();
		initialize();
		cmb.setSelectedIndex(-1);
	}

	private void initialize() {

		JLabel lbl = new JLabel("도서검색 : ");
		add(lbl);

		cmb = new JComboBox<String>();
		cmb.setModel(new DefaultComboBoxModel(new String[] { "도서번호", "도서제목", "도서구분", "대출상태" }));
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
			String selectItem = (String) cmb.getSelectedItem();

			list = selectModel(selectItem);

			if (list != null) {
				pBook.setService(service);
				pBook.setList(list);
				pBook.setList();
			} else {
				System.out.println("다시입력");
			}

		} catch (Exception e1) {
			e1.getStackTrace();
		}
	}

	public List<BookInfo> selectModel(String model) {
		try {

			if (model.equals("도서번호")) {
				list = service.showBookListByNo(new BookInfo(Integer.parseInt(tf.getText())));
			}
			if (model.equals("도서제목")) {
				list = service.showBookListByName(new BookInfo(tf.getText()));
			}
			if (model.equals("도서구분")) {
				list = service.showBookListByCate(new BookInfo(Integer.parseInt(tf.getText())));
			}
			if (model.equals("대출상태")) {
				list = service.showBookListByRent(new BookInfo(tf.getText()));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return list;
	}

	public void setService(BookInfoService service) {
		this.service = service;
	}

//	public List<MemberInfo> getList() {
//		return list;
//	}

	public BookInfoTablePanel getBookInfoList() {
		return pBook;
	}

	public void clearTf() {
		tf.setText("");
	}
}
