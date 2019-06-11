package ticketseller;

import java.awt.print.Printable;
import java.util.HashMap;
import java.util.Scanner;

abstract public class Ticket{
	abstract public int calculatePrice();
}

class NormalTicket extends Ticket{
	
	private Station originStation; 
	private Station destStation;
	private static HashMap<String, Integer> ticketsPriceMap = new HashMap<String, Integer>();
	
	static{
		ticketsPriceMap.put("上海南->江湾镇",5);
		ticketsPriceMap.put("上海南->虹口足球场",4);
		ticketsPriceMap.put("江湾镇->虹口足球场",3);
		ticketsPriceMap.put("江湾镇->上海南",5);
		ticketsPriceMap.put("虹口足球场->江湾镇",3);
		ticketsPriceMap.put("虹口足球场->上海南",4);
	}
	
	public void setOrigin(String originName){
		this.originStation = new Station(originName);
	}
	
	public void setDestination(String destinationName){
		this.destStation = new Station(destinationName);
	}

	public int calculatePrice() {
		String oriDest = originStation.originToDest(destStation);
		try {
			System.out.println(oriDest);
			return ticketsPriceMap.get(oriDest);
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	public String toString(){
		String str = "\n";
		str += "****************************\n";
		str += "    Subway Normal Ticket    \n";
		str += "                           \n";
		str += "  "+originStation.originToDest(destStation)+"\n";
		str += "        Price:$" + calculatePrice() + "\n";
		str += "                           \n";
		str += "****************************";
		return str;
	}
}

class DayTicket extends Ticket{
	private static final int dayTicketPrice = 20;
	public int calculatePrice() {
		return dayTicketPrice;
	}
	
	public String toString(){
		String str = "**************************";
		str += "                         \n";
		str += "   Subway Day Ticket   \n";
		str += "                         \n";
		str += "       Price:$20.0       \n";
		str += "                         \n";
		str += "***************************\n";
		return str;
	}
}

