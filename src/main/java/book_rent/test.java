package book_rent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book_rent.service.MemberInfoService;
import book_rent.ui.list.MemberInfoTablePanel;

public class test extends JFrame {

	private JPanel contentPane;
	private MemberInfoTablePanel panel;
	private MemberInfoService service;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public test() {
		service = new MemberInfoService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new MemberInfoTablePanel();
		panel.setService(service);
		panel.loadData();
		
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
