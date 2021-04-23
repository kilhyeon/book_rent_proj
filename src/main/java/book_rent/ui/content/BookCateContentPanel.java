package book_rent.ui.content;

import book_rent.dto.BookCate;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BookCateContentPanel extends AbstractContentPanel<BookCate> {
	private JTextField tfCateNo;
	private JTextField tfCateName;

	public BookCateContentPanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "도서구분정보", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pCateNo = new JPanel();
		add(pCateNo);

		JLabel lblCateNo = new JLabel("도서구분번호 : ");
		pCateNo.add(lblCateNo);

		tfCateNo = new JTextField();
		pCateNo.add(tfCateNo);
		tfCateNo.setColumns(10);

		JPanel pCateName = new JPanel();
		add(pCateName);

		JLabel lblCateName = new JLabel("도서구분이름 : ");
		pCateName.add(lblCateName);

		tfCateName = new JTextField();
		pCateName.add(tfCateName);
		tfCateName.setColumns(10);

	}

	@Override
	public void setItem(BookCate item) {
		if (item == null) {
			throw new NotSelectedException();
		}
		tfCateNo.setText(String.valueOf(item.getBookCateNo()));
		tfCateName.setText(item.getBookCateName());
		tfCateNo.setEditable(false);
	}

	@Override
	public BookCate getItem() {
		validCheck();
		int bookCateNo = Integer.parseInt(tfCateNo.getText().trim());
		String bookCateName = tfCateName.getText().trim();
		return new BookCate(bookCateNo, bookCateName);
	}

	@Override
	public void validCheck() {
		if (tfCateNo.getText().equals("")) {
			throw new InvalidCheckException();
		}

	}

	@Override
	public void clearTf() {
		tfCateNo.setText("");
		tfCateName.setText("");

		if (!tfCateNo.isEditable()) {
			tfCateNo.setEditable(true);
		}

	}

	public JTextField getTfCateNo() {
		return tfCateNo;
	}

	public void setTfCateNo(JTextField tfCateNo) {
		this.tfCateNo = tfCateNo;
	}

	public JTextField getTfCateName() {
		return tfCateName;
	}

	public void setTfCateName(JTextField tfCateName) {
		this.tfCateName = tfCateName;
	}

}
