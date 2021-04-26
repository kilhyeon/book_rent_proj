package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;

public class BookContentPanel extends AbstractContentPanel<BookInfo> {
	private JTextField tfBookNo;
	private JTextField tfBookName;
	private JTextField tfBookCate;
	private JTextField tfRentState;
	private JTextField tfBookCount;

	public BookContentPanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "도서정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pBookNo = new JPanel();
		add(pBookNo);
		pBookNo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookNo = new JLabel("도서번호 : ");
		pBookNo.add(lblBookNo);

		tfBookNo = new JTextField();
		pBookNo.add(tfBookNo);
		tfBookNo.setColumns(15);

		JPanel pBookName = new JPanel();
		add(pBookName);
		pBookName.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookName = new JLabel("도서제목 : ");
		pBookName.add(lblBookName);

		tfBookName = new JTextField();
		tfBookName.setColumns(25);
		pBookName.add(tfBookName);

		JPanel pBookState = new JPanel();
		add(pBookState);
		pBookState.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblRentState = new JLabel("대여상태 : ");
		pBookState.add(lblRentState);

		tfRentState = new JTextField();
		pBookState.add(tfRentState);
		tfRentState.setColumns(10);

		JLabel lblBookCount = new JLabel("도서보유권수 : ");
		pBookState.add(lblBookCount);

		tfBookCount = new JTextField();
		pBookState.add(tfBookCount);
		tfBookCount.setColumns(10);

		JPanel pRentCate = new JPanel();
		add(pRentCate);
		pRentCate.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookCate = new JLabel("도서구분 : ");
		pRentCate.add(lblBookCate);

		tfBookCate = new JTextField();
		pRentCate.add(tfBookCate);
		tfBookCate.setColumns(15);
	}

	@Override
	public void setItem(BookInfo item) {
		if (item == null) {
			throw new NotSelectedException();
		}
		tfBookNo.setText(String.valueOf(item.getBookNo()));
		tfBookName.setText(item.getBookName());
		tfBookCate.setText(String.valueOf(item.getBookCateNo()));
		tfBookCount.setText(String.valueOf(item.getBookCount()));
		tfRentState.setText(String.valueOf(item.getRentState()));
		tfBookNo.setEditable(false);
	}

	@Override
	public BookInfo getItem() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		String bookName = tfBookName.getText().trim();
		int bookCount = Integer.parseInt(tfBookCount.getText().trim());
		BookCate bookCateNo = new BookCate(Integer.parseInt(tfBookCate.getText().trim()));
		int rentState = Integer.parseInt(tfRentState.getText().trim());
		return new BookInfo(bookNo, bookName, bookCount, bookCateNo, rentState);
	}

	public BookInfo getItemBookNo() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		return new BookInfo(bookNo);
	}

	public BookInfo getItemBookName() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		String bookName = tfBookName.getText().trim();
		return new BookInfo(bookNo, bookName);
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
		tfBookCount.setText("");
		tfRentState.setText("");

//		if (!tfBookNo.isEditable()) {
//			tfBookNo.setEditable(true);
//		}
	}

	public JTextField getTfBookNo() {
		return tfBookNo;
	}

	public void setTfBookNo(JTextField tfBookNo) {
		this.tfBookNo = tfBookNo;
	}

	public JTextField getTfBookName() {
		return tfBookName;
	}

	public void setTfBookName(JTextField tfBookName) {
		this.tfBookName = tfBookName;
	}

	public JTextField getTfBookCate() {
		return tfBookCate;
	}

	public void setTfBookCate(JTextField tfBookCate) {
		this.tfBookCate = tfBookCate;
	}

	public JTextField getTfBookCount() {
		return tfBookCount;
	}

	public void setTfBookCount(JTextField tfBookCount) {
		this.tfBookCount = tfBookCount;
	}

	public JTextField getTfRentState() {
		return tfRentState;
	}

	public void setTfRentState(JTextField tfRentState) {
		this.tfRentState = tfRentState;
	}

}
