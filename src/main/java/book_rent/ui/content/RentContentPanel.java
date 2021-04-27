package book_rent.ui.content;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mysql.fabric.xmlrpc.base.Data;

import book_rent.dto.BookCate;
import book_rent.dto.BookInfo;
import book_rent.dto.MemberInfo;
import book_rent.dto.Rent;
import book_rent.ui.exception.InvalidCheckException;
import book_rent.ui.exception.NotSelectedException;
import javax.swing.SwingConstants;

public class RentContentPanel extends AbstractContentPanel<Rent> {
	private JTextField tfRentNo;
	private JTextField tfRentMem;
	private JTextField tfRentBook;
	private JTextField tflRentDate;
	private JTextField tfLateDate;

	public RentContentPanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "대여정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 3, 0, 0));

		JPanel pRent1 = new JPanel();
		add(pRent1);
		pRent1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblRentNo = new JLabel("대 여  번 호 : ");
		pRent1.add(lblRentNo);

		tfRentNo = new JTextField();
		tfRentNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfRentNo.setColumns(10);
		pRent1.add(tfRentNo);

		JPanel pRent2 = new JPanel();
		FlowLayout fl_pRent2 = (FlowLayout) pRent2.getLayout();
		fl_pRent2.setAlignment(FlowLayout.LEFT);
		add(pRent2);

		JLabel lblRentMem = new JLabel("회원번호 : ");
		pRent2.add(lblRentMem);

		tfRentMem = new JTextField();
		tfRentMem.setHorizontalAlignment(SwingConstants.CENTER);
		pRent2.add(tfRentMem);
		tfRentMem.setColumns(10);

		JPanel pRent3 = new JPanel();
		FlowLayout fl_pRent3 = (FlowLayout) pRent3.getLayout();
		fl_pRent3.setAlignment(FlowLayout.LEFT);
		add(pRent3);

		JLabel lblRentBook = new JLabel("도서번호 : ");
		pRent3.add(lblRentBook);

		tfRentBook = new JTextField();
		tfRentBook.setHorizontalAlignment(SwingConstants.CENTER);
		pRent3.add(tfRentBook);
		tfRentBook.setColumns(10);

		JPanel pRent4 = new JPanel();
		FlowLayout fl_pRent4 = (FlowLayout) pRent4.getLayout();
		fl_pRent4.setAlignment(FlowLayout.LEFT);
		add(pRent4);

		JLabel lblRentDate = new JLabel("도서대여일 : ");
		pRent4.add(lblRentDate);

		tflRentDate = new JTextField();
		tflRentDate.setHorizontalAlignment(SwingConstants.CENTER);
		pRent4.add(tflRentDate);
		tflRentDate.setColumns(10);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel);

		JLabel lblLateDate = new JLabel("연체일자 : ");
		panel.add(lblLateDate);

		tfLateDate = new JTextField();
		tfLateDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(tfLateDate);
		tfLateDate.setColumns(10);
	}

	@Override
	public void setItem(Rent item) {
		if (item == null) {
			throw new NotSelectedException();
		}
		tfRentNo.setText(String.valueOf(item.getRentNo()));
		tfRentMem.setText(String.valueOf(item.getMemNo().getMemNo()));
		tfRentBook.setText(String.valueOf(item.getBookNo().getBookNo()));
		tflRentDate.setText(String.valueOf(item.getRentDate()));
		tfLateDate.setText(String.valueOf(item.getLateDate()));

	}

	@Override
	public Rent getItem() {
		validCheck();

		int rentNo = Integer.parseInt(tfRentNo.getText().trim());
		MemberInfo memNo = new MemberInfo(Integer.parseInt(tfRentMem.getText().trim()));
		BookInfo bookNo = new BookInfo(Integer.parseInt(tfRentBook.getText().trim()));
//		Date rentDate = new Data(Integer.parseInt(tflRentDate.getText().trim()));
		int lateDate = Integer.parseInt(tfLateDate.getText().trim());
		return new Rent(rentNo, memNo, bookNo, lateDate);
	}

	public Rent getItemRentNo() {
		validCheck();
		int rentNo = Integer.parseInt(tfRentNo.getText().trim());
		return new Rent(rentNo);
	}

	
	public Rent getItemBookNo() {
		validCheck();
		BookInfo bookNo = new BookInfo(Integer.parseInt(tfRentBook.getText().trim()));
		return new Rent(bookNo);
	}

	@Override
	public void validCheck() {
		if (tfRentNo.getText().equals("")) {
			throw new InvalidCheckException();
		}

	}

	@Override
	public void clearTf() {
		tfRentNo.setText("");
		tfRentMem.setText("");
		tfRentBook.setText("");
		tflRentDate.setText("");
		tfLateDate.setText("");

	}

	public JTextField getTfRentNo() {
		return tfRentNo;
	}

	public void setTfRentNo(JTextField tfRentNo) {
		this.tfRentNo = tfRentNo;
	}

	public JTextField getTfRentMem() {
		return tfRentMem;
	}

	public void setTfRentMem(JTextField tfRentMem) {
		this.tfRentMem = tfRentMem;
	}

	public JTextField getTfRentBook() {
		return tfRentBook;
	}

	public void setTfRentBook(JTextField tfRentBook) {
		this.tfRentBook = tfRentBook;
	}

	public JTextField getTflRentDate() {
		return tflRentDate;
	}

	public void setTflRentDate(JTextField tflRentDate) {
		this.tflRentDate = tflRentDate;
	}

	public JTextField getTfLateDate() {
		return tfLateDate;
	}

	public void setTfLateDate(JTextField tfLateDate) {
		this.tfLateDate = tfLateDate;
	}

}
