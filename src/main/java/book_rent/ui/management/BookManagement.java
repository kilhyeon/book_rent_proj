package book_rent.ui.management;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;
import book_rent.service.BookCateService;
import book_rent.ui.content.AbstractContentPanel;
import book_rent.ui.exception.InvalidCheckException;

public class BookManagement extends AbstractContentPanel<BookInfo> implements ActionListener {
	private JTextField tfBookNo;
	private JTextField tfBookName;
	private JTextField tfBookCate;
	private JTextField tfRentState;
	private JComboBox cmbCate;
	private JComboBox cmbRentState;
	private BookCateService service;
	private JTextField tfBookCount;

	public BookManagement() {
		service = new BookCateService();
		initialize();
		selectCmb();
		tfBookCate.setEditable(false);
//		tfRentState.setEditable(false);
	}

	public void selectCmb() {
		List<BookCate> cate = service.showBookCateByAll();
		DefaultComboBoxModel<BookCate> dcbm = new DefaultComboBoxModel<BookCate>(new Vector<>(cate));
		cmbCate.setModel(dcbm);
		cmbCate.setSelectedIndex(-1);
		tfBookCate.setText("");
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

		JPanel pBookCount = new JPanel();
		add(pBookCount);

		JLabel lblBookCount = new JLabel("보유권수 : ");
		pBookCount.add(lblBookCount);
		pBookCount.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		tfBookCount = new JTextField();
		pBookCount.add(tfBookCount);
		tfBookCount.setColumns(10);

		JPanel pBookCate = new JPanel();
		add(pBookCate);
		pBookCate.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblBookCate = new JLabel("도서구분 : ");
		pBookCate.add(lblBookCate);

		tfBookCate = new JTextField();
		tfBookCate.setColumns(10);
		pBookCate.add(tfBookCate);
		tfBookCate.setVisible(false);

		cmbCate = new JComboBox();
		cmbCate.addActionListener(this);

		pBookCate.add(cmbCate);

		JPanel pRentState = new JPanel();
		add(pRentState);
		pRentState.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		pRentState.setVisible(false);

		JLabel lblRentState = new JLabel("대여상태 : ");
		pRentState.add(lblRentState);

		tfRentState = new JTextField();
		tfRentState.setColumns(10);
		pRentState.add(tfRentState);

		cmbRentState = new JComboBox();
		pRentState.add(cmbRentState);
	}

	@Override
	public void setItem(BookInfo item) {

		tfBookNo.setText(String.valueOf(item.getBookNo()));
		tfBookName.setText(item.getBookName());
		tfBookCount.setText(String.valueOf(item.getBookCount()));
//		tfBookCate.setText((String.valueOf(item.getBookCateNo())).replaceAll("[^0-9]", ""));
		tfRentState.setText(String.valueOf(item.getRentState()));

		int bookCate = Integer.parseInt((String.valueOf(item.getBookCateNo())).replaceAll("[^0-9]", ""));
		cmbCate.setSelectedIndex(bookCate - 1);

		tfBookNo.setEditable(false);
	}

	@Override
	public BookInfo getItem() {
		validCheck();
		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
		String bookName = tfBookName.getText().trim();
		int bookCount = Integer.parseInt(tfBookCount.getText().trim());
		BookCate bookCateNo = new BookCate(Integer.parseInt(tfBookCate.getText().trim()));
//		int rentState = Integer.parseInt(tfRentState.getText().trim());
		return new BookInfo(bookNo, bookName, bookCount, bookCateNo);
	}

//	public BookInfo getItemBookNo() {
//		validCheck();
//		int bookNo = Integer.parseInt(tfBookNo.getText().trim());
//		return new BookInfo(bookNo);
//	}

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
		cmbCate.setSelectedIndex(-1);

		if (!tfBookNo.isEditable()) {
			tfBookNo.setEditable(true);
		}
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

	public JTextField getTfBookCount() {
		return tfBookCount;
	}

	public void setTfBookCount(JTextField tfBookCount) {
		this.tfBookCount = tfBookCount;
	}

	public JTextField getTfBookCate() {
		return tfBookCate;
	}

	public void setTfBookCate(JTextField tfBookCate) {
		this.tfBookCate = tfBookCate;
	}

	public JTextField getTfRentState() {
		return tfRentState;
	}

	public void setTfRentState(JTextField tfRentState) {
		this.tfRentState = tfRentState;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cmbCate) {
			actionPerformedCmbCate(e);
		}
	}

	public JComboBox getCmbCate() {
		return cmbCate;
	}

	public void setCmbCate(JComboBox cmbCate) {
		this.cmbCate = cmbCate;
	}

	public JComboBox getCmbRentState() {
		return cmbRentState;
	}

	public void setCmbRentState(JComboBox cmbRentState) {
		this.cmbRentState = cmbRentState;
	}

	public void actionPerformedCmbCate(ActionEvent e) {
		tfBookCate.setText((cmbCate.getSelectedItem() + "").replaceAll("[^0-9]", ""));
	}
}
