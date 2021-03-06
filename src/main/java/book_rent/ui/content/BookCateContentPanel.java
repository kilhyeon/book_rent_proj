package book_rent.ui.content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookCate;
import book_rent.service.BookCateService;
import book_rent.ui.exception.InvalidCheckException;
import javax.swing.SwingConstants;

public class BookCateContentPanel extends AbstractContentPanel<BookCate> {
	private JTextField tfCateNo;
	private JTextField tfCateName;
	private BookCateService bookCateService;

	public BookCateContentPanel() {
		bookCateService = new BookCateService();
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
		tfCateNo.setHorizontalAlignment(SwingConstants.CENTER);
		pCateNo.add(tfCateNo);
		tfCateNo.setColumns(10);

		JPanel pCateName = new JPanel();
		add(pCateName);

		JLabel lblCateName = new JLabel("도서구분이름 : ");
		pCateName.add(lblCateName);

		tfCateName = new JTextField();
		tfCateName.setHorizontalAlignment(SwingConstants.CENTER);
		pCateName.add(tfCateName);
		tfCateName.setColumns(10);

	}

	@Override
	public void setItem(BookCate item) {
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
