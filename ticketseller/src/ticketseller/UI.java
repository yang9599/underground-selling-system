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
		normalTicketButton = new JRadioButton("单程次票");
		normalTicketButton.setMnemonic(KeyEvent.VK_B);
		normalTicketButton.setActionCommand("单程次票");
		normalTicketButton.setSelected(true);

		dayTicketButton = new JRadioButton("天票");
		dayTicketButton.setMnemonic(KeyEvent.VK_C);
		dayTicketButton.setActionCommand("天票");

		// 将按键组成单选按钮
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

		// 起点站
		JPanel startJPanel = new JPanel();
		String[] startStations = { "---选择一个起点站---", "上海南", "虹口足球场", "江湾镇" };
		startStationList = new JComboBox(startStations);
		startStationList.setSelectedIndex(0);
		startStationList.addActionListener(this);

		JLabel startJLabel = new JLabel("起点站");
		startJPanel.add(startJLabel);
		startJPanel.add(startStationList);
		centre.add(startJPanel);

		// 终点站
		JPanel stopJPanel = new JPanel();
		String[] stopStations = { "---选择一个终点站---", "上海南", "虹口足球场", "江湾镇" };
		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		stopStationList = new JComboBox(stopStations);
		stopStationList.setSelectedIndex(0);
		stopStationList.addActionListener(this);

		JLabel stopJLable = new JLabel("终点站");
		stopJPanel.add(stopJLable);
		stopJPanel.add(stopStationList);
		centre.add(stopJPanel);

		JPanel confirmJPanel = new JPanel();
		confirmButton = new JButton("确定");
		clearButton = new JButton("清除");
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
			if (ticketType==1 & (originName=="" | originName=="---选择一个起点站---")){
				System.out.println(originName+1);
				outputTextString = "请输入起点站";
				}
			else if (ticketType==1 & (destinationName=="" | destinationName=="---选择一个终点站---")){
				System.out.println(2);
				outputTextString = "请输入终点站";
				}
			else if (ticketType==1 & (originName== destinationName)){
				System.out.println(2);
				outputTextString = "请输入不同的起点站和终点站";
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
