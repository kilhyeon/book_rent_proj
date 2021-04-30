package book_rent.ui.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import book_rent.dto.BookInfo;
import book_rent.service.BookInfoService;
import book_rent.ui.list.BookInfoTablePanel;

@SuppressWarnings("serial")
public class BookSearchPanel extends JPanel implements ActionListener {
	private JTextField tf;
	private JButton btn;
	private BookInfoService bookService;
	private JComboBox<String> cmb;
	private List<BookInfo> bookList;
	private BookInfoTablePanel pBookInfoTableList;

	public BookSearchPanel() {
		pBookInfoTableList = new BookInfoTablePanel();
		bookList = new ArrayList<BookInfo>();
		initialize();
		cmb.setSelectedIndex(-1);
	}

	private void initialize() {

		JLabel lbl = new JLabel("도서검색 : ");
		add(lbl);

		cmb = new JComboBox<String>();
		cmb.setModel(new DefaultComboBoxModel(new String[] { "도서번호", "도서제목" }));
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
			bookList = item(selectItem);
			if (bookList != null) {
				pBookInfoTableList.setList(bookList);
				pBookInfoTableList.setList();
			} else {
				JOptionPane.showMessageDialog(null, "일치하는 도서 정보가 없습니다.");
				pBookInfoTableList.loadData();
				clearTf();
			}

		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "검색 방법을 선택하세요.");
//			pMemInfoTableList.setList(memSearchList);
			pBookInfoTableList.loadData();
		}
	}

	public List<BookInfo> item(String model) {
		try {

			if (model.equals("도서번호")) {
				bookList = bookService.showBookListByNo(new BookInfo(Integer.parseInt(tf.getText())));
			}
			if (model.equals("도서제목")) {
				bookList = bookService.showBookListByName(new BookInfo(tf.getText()));
			}
//			if (model.equals("도서구분")) {
//				bookList = bookService.showBookListByCate(new BookInfo(Integer.parseInt(tf.getText())));
//			}
//			if (model.equals("대출상태")) {
//				bookList = bookService.showBookListByRent(new BookInfo(tf.getText()));
//			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return bookList;
	}

	public void setService(BookInfoService service) {
		this.bookService = service;
	}

//	public List<MemberInfo> getList() {
//		return list;
//	}

	public BookInfoTablePanel getBookInfoList() {
		return pBookInfoTableList;
	}

	public void clearTf() {
		tf.setText("");
	}
}
