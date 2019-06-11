package ticketseller;

import java.util.Scanner;

public class TicketMachine {
	private int price;
	private int paid;
	private Ticket myTicket;
	
	public Boolean createTicket(int ticketType, String originName, String destinationName){
		if (ticketType==1){
			NormalTicket normalTicket = new NormalTicket();
			normalTicket.setOrigin(originName);
			normalTicket.setDestination(destinationName);
			myTicket = normalTicket;
		}
		else if (ticketType==2) {
			myTicket = new DayTicket();
		}
		price = getTicketsPrice(myTicket);
		System.out.println(price);
		if (price==-1)	return false;
		else return true;		
	}
	
	public int getTicketsPrice(Ticket ticket) {
		return ticket.calculatePrice();
	}
	
	public int readPrice(){
		return price;
	}
	
	public Boolean collectMoney(){
		System.out.println("Your ticket price is "+price);
		System.out.println("Please put money:");
		paid = new Scanner(System.in).nextInt();
		System.out.println("You've paid "+paid);
		if (paid>=price) return true;
		else{
			System.out.println("Not enough!");
			return false;
		}
	}
	
	public double giveCharge(){
		double charge = paid-price;
		if (charge>0){
			System.out.println("Please take back your charge:"+charge);			
		}
		return charge;
	}
	
	public String printTicket(){
//		System.out.println("Printing your ticket...");
//		System.out.println(myTicket);
		return myTicket.toString();
	}
}
