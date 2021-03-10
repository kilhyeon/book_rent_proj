package book_rent.table;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import book_rent.dao.DaoFactory;
import book_rent.dto.Member_info_dto;

public class MemTableModel extends AbstractTableModel {
	private String[] titles = { "회원번호", "회원명", "생년월일", "전화번호", "휴대전화", "주소" };
	// private Object[][] data ;
	private Vector<String[]> data;
	// List<Client> clients;

	public MemTableModel() {
		// clients = DAOFactory.getClientDAO().all();
		initData();
	}

	public void loadData(List<Member_info_dto> members) {
		data = new Vector<String[]>();
		for (Member_info_dto m : members) {
			String[] str = { m.getMemNo(), m.getMemName(), m.getMemBirth(), m.getMemTel(), m.getMemCp(),
					m.getMemAddr() };
			data.add(str);
		}
//		fireTableChanged(null);
	}

	public void initData() {
		this.loadData(DaoFactory.getMember_info_dao().all());
	}

	public void availables() {
		this.loadData(DaoFactory.getMember_info_dao().available());
	}

	public int getRowCount() {
		return this.data.size();
	}

	public int getColumnCount() {
		return this.titles.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data.get(rowIndex)[columnIndex];
	}

	public String getColumnName(int col) {
		return this.titles[col];
	}
	
	
	

//	public String[] getTitles() {
//		return titles;
//	}
//
//	public Vector<String[]> getData() {
//		return data;
//	}

}
