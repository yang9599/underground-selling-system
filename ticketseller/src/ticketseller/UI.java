package ticketseller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import ticketseller.TicketMachine;

public class UI extends JFrame implements ActionListener {
	JRadioButton normalTicketButton;
	JRadioButton dayTicketButton;
	JButton confirmButton;
	JButton clearButton;
	JComboBox startStationList;
	JComboBox stopStationList;
	JTextArea displayArea;

	int ticketType = 1;
	String originName ="";
	String destinationName ="";
	String outputTextString="";

	public UI(String UIname) {
		super(UIname);
		normalTicketButton = new JRadioButton("���̴�Ʊ");
		normalTicketButton.setMnemonic(KeyEvent.VK_B);
		normalTicketButton.setActionCommand("���̴�Ʊ");
		normalTicketButton.setSelected(true);

		dayTicketButton = new JRadioButton("��Ʊ");
		dayTicketButton.setMnemonic(KeyEvent.VK_C);
		dayTicketButton.setActionCommand("��Ʊ");

		// ��������ɵ�ѡ��ť
		ButtonGroup group = new ButtonGroup();
		group.add(normalTicketButton);
		group.add(dayTicketButton);
		normalTicketButton.addActionListener(this);
		dayTicketButton.addActionListener(this);

		JPanel top = new JPanel();
		top.add(normalTicketButton);
		top.add(dayTicketButton);
		add(top, BorderLayout.NORTH);

		JPanel centre = new JPanel();
		centre.setLayout(new GridLayout(3, 1));

		// ���վ
		JPanel startJPanel = new JPanel();
		String[] startStations = { "---ѡ��һ�����վ---", "�Ϻ���", "�������", "������" };
		startStationList = new JComboBox(startStations);
		startStationList.setSelectedIndex(0);
		startStationList.addActionListener(this);

		JLabel startJLabel = new JLabel("���վ");
		startJPanel.add(startJLabel);
		startJPanel.add(startStationList);
		centre.add(startJPanel);

		// �յ�վ
		JPanel stopJPanel = new JPanel();
		String[] stopStations = { "---ѡ��һ���յ�վ---", "�Ϻ���", "�������", "������" };
		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		stopStationList = new JComboBox(stopStations);
		stopStationList.setSelectedIndex(0);
		stopStationList.addActionListener(this);

		JLabel stopJLable = new JLabel("�յ�վ");
		stopJPanel.add(stopJLable);
		stopJPanel.add(stopStationList);
		centre.add(stopJPanel);

		JPanel confirmJPanel = new JPanel();
		confirmButton = new JButton("ȷ��");
		clearButton = new JButton("���");
		confirmJPanel.add(confirmButton);
		confirmJPanel.add(clearButton);
		confirmButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		centre.add(confirmJPanel);

		add(centre, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		displayArea = new JTextArea(8, 16);
		displayArea.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
		displayArea.setOpaque(true);
		bottom.add(displayArea);
		add(bottom, BorderLayout.SOUTH);

		setSize(300, 400);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		UI ticketSeller = new UI("Ticket Seller");
		ticketSeller.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if (src == normalTicketButton) {
			ticketType = 1;
			startStationList.setEnabled(true);
			stopStationList.setEnabled(true);
		}
		else if (src == startStationList)	originName = (String) ((JComboBox) src).getSelectedItem();
		else if (src == stopStationList) destinationName = (String) ((JComboBox) src).getSelectedItem();
		else if (src == dayTicketButton) {	
			ticketType = 2;
			startStationList.setEnabled(false);
			stopStationList.setEnabled(false);
		}
		else if (src == confirmButton){	
			if (ticketType==1 & (originName=="" | originName=="---ѡ��һ�����վ---")){
				System.out.println(originName+1);
				outputTextString = "���������վ";
				}
			else if (ticketType==1 & (destinationName=="" | destinationName=="---ѡ��һ���յ�վ---")){
				System.out.println(2);
				outputTextString = "�������յ�վ";
				}
			else if (ticketType==1 & (originName== destinationName)){
				System.out.println(2);
				outputTextString = "�����벻ͬ�����վ���յ�վ";
				}
			else{
				System.out.println(ticketType+originName+destinationName);	
				User user = new User();
				outputTextString = user.buyTicket(ticketType, originName, destinationName);
			}
			displayArea.setText(outputTextString);
		}
		else if (src == clearButton){
			displayArea.setText("");
		}
	}
}
