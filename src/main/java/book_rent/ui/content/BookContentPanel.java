package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookInfo;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;

public class BookContentPanel extends AbstractContentPanel<BookInfo> {
	private JTextField tfBookNo;
	private JTextField tfBookName;
	private JTextField tfBookCate;
	private JTextField tfRentState;

	public BookContentPanel() {
		initialize();
	}

	private void initialize() {
		setBorder(
				new TitledBorder(null, "도서정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
				JLabel lblBookNo = new JLabel("도서번호 : ");
				panel_1.add(lblBookNo);
		
				tfBookNo = new JTextField();
				panel_1.add(tfBookNo);
				tfBookNo.setColumns(15);

		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookName = new JLabel("도서제목 : ");
		panel_2.add(lblBookName);

		tfBookName = new JTextField();
		tfBookName.setColumns(25);
		panel_2.add(tfBookName);

		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookCate = new JLabel("도서구분 : ");
		panel_3.add(lblBookCate);

		tfBookCate = new JTextField();
		tfBookCate.setColumns(15);
		panel_3.add(tfBookCate);

		JPanel panel_4 = new JPanel();
		add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblRentState = new JLabel("대여상태 : ");
		panel_4.add(lblRentState);

		tfRentState = new JTextField();
		tfRentState.setColumns(10);
		panel_4.add(tfRentState);
	}

	@Override
	public void setItem(BookInfo item) {
		if (item == null) {
			throw new NotSelectedException();
		}
		tfBookNo.setText(String.valueOf(item.getBookNo()));
		tfBookName.setText(item.getBookName());
		tfBookCate.setText(String.valueOf(item.getBookCateNo()));
		tfRentState.setText(item.getRentState());

		tfBookNo.setEditable(false);
	}

	@Override
	public BookInfo getItem() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		String bookName = tfBookName.getText().trim();
		int bookCateNo = Integer.parseInt(tfBookCate.getText().trim());
		String rentState = tfRentState.getText().trim();
		return new BookInfo(bookNo, bookName, bookCateNo, rentState);
	}

	@Override
	public void validCheck() {
		if (tfBookNo.getText().equals("")) {
			throw new InvalidCheckException();
		}

	}

	@Override
	public void clearTf() {
		tfBookNo.setText("");
		tfBookName.setText("");
		tfBookCate.setText("");
		tfRentState.setText("");

		if (!tfBookNo.isEditable()) {
			tfBookNo.setEditable(true);
		}
	}
}
