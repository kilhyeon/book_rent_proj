package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookCate;
import book_rent.dto.BookCount;
import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;

public class BookContentPanel extends AbstractContentPanel<BookInfo> {
	private JTextField tfBookNo;
	private JTextField tfBookName;
	private JTextField tfBookCate;
	private JTextField tfRentState;
	private JTextField tfBookCount;
	private JTextField tfBookCountTotal;

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
		tfBookNo.setHorizontalAlignment(SwingConstants.CENTER);
		pBookNo.add(tfBookNo);
		tfBookNo.setColumns(25);

		JPanel pBookName = new JPanel();
		add(pBookName);
		pBookName.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookCate = new JLabel("도서구분 : ");
		pBookName.add(lblBookCate);

		tfBookCate = new JTextField();
		pBookName.add(tfBookCate);
		tfBookCate.setHorizontalAlignment(SwingConstants.CENTER);
		tfBookCate.setColumns(10);

		JPanel pBookState = new JPanel();
		add(pBookState);
		pBookState.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookName = new JLabel("도서제목 : ");
		pBookState.add(lblBookName);

		tfBookName = new JTextField();
		pBookState.add(tfBookName);
		tfBookName.setHorizontalAlignment(SwingConstants.CENTER);
		tfBookName.setColumns(25);

		JPanel pRentCate = new JPanel();
		add(pRentCate);
		pRentCate.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookCount = new JLabel("대여권수 : ");
		lblBookCount.setVisible(false);

		JLabel lblBookCountTotal = new JLabel("도서권수 : ");
		pRentCate.add(lblBookCountTotal);

		tfBookCountTotal = new JTextField();
		tfBookCountTotal.setHorizontalAlignment(SwingConstants.CENTER);
		pRentCate.add(tfBookCountTotal);
		tfBookCountTotal.setColumns(10);
		pRentCate.add(lblBookCount);

		tfBookCount = new JTextField();
		tfBookCount.setVisible(false);
		pRentCate.add(tfBookCount);
		tfBookCount.setHorizontalAlignment(SwingConstants.CENTER);
		tfBookCount.setColumns(10);

		JLabel lblRentState = new JLabel("대여상태 : ");
		pRentCate.add(lblRentState);

		tfRentState = new JTextField();
		pRentCate.add(tfRentState);
		tfRentState.setHorizontalAlignment(SwingConstants.CENTER);
		tfRentState.setVisible(false);
		tfRentState.setColumns(10);
		lblRentState.setVisible(false);
	}

	@Override
	public void setItem(BookInfo item) {
		if (item == null) {
			throw new NotSelectedException();
		}
		tfBookNo.setText(String.valueOf(item.getBookNo()));
		tfBookName.setText(item.getBookName());
		tfBookCate.setText(String.valueOf(item.getBookCateNo().getBookCateName()));
		tfBookCountTotal.setText(String.valueOf(item.getBookCountTotal()));
		tfBookCount.setText(String.valueOf(item.getBookCount()));
		tfRentState.setText(String.valueOf(item.getRentState()));
		tfBookNo.setEditable(false);
	}

	@Override
	public BookInfo getItem() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		String bookName = tfBookName.getText().trim();
		int bookCountTotal = Integer.parseInt(tfBookCountTotal.getText().trim());
		int bookCount = Integer.parseInt(tfBookCount.getText().trim());
		BookCate bookCateNo = new BookCate(Integer.parseInt(tfBookCate.getText().trim()));
		int rentState = Integer.parseInt(tfRentState.getText().trim());
		return new BookInfo(bookNo, bookName, bookCountTotal, bookCount, bookCateNo, rentState);
	}

	public BookInfo getItemBookNo() {
//		validCheck();
		try {
			int bookNo = Integer.parseInt(tfBookNo.getText().trim());
			return new BookInfo(bookNo);

		} catch (NumberFormatException | InvalidCheckException e) {
			JOptionPane.showMessageDialog(null, "도서정보를 선택하세요.", "메세지", JOptionPane.WARNING_MESSAGE);
			e.getStackTrace();
			return null;
		}

	}

	public BookInfo getItemBookName() {
//		validCheck();
		try {
			int bookNo = Integer.parseInt(tfBookNo.getText().trim());
			String bookName = tfBookName.getText().trim();
			return new BookInfo(bookNo, bookName);

		} catch (NumberFormatException | InvalidCheckException e) {
			e.getStackTrace();
			return null;
		}

	}

	public int getItemBookCount() {
//		validCheck();
		try {
			int bookCount = Integer.parseInt(tfBookCount.getText().trim());
			return bookCount;

		} catch (NumberFormatException | InvalidCheckException e) {
			e.getStackTrace();
			return -1;
		}

	}

	public int getItemBokCountTotal() {
//		validCheck();
		try {
			int bookCountTotal = Integer.parseInt(tfBookCountTotal.getText().trim());
			return bookCountTotal;

		} catch (NumberFormatException | InvalidCheckException e) {
			e.getStackTrace();
			return -1;
		}

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
		tfBookCountTotal.setText("");
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

	public JTextField getTfBookCountTotal() {
		return tfBookCountTotal;
	}

	public void setTfBookCountTotal(JTextField tfBookCountTotal) {
		this.tfBookCountTotal = tfBookCountTotal;
	}

}
