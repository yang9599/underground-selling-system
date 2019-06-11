package ticketseller;

public class User {
	private TicketMachine machine;
	public String buyTicket(int ticketType, String originName, String destinationName) {
		machine = new TicketMachine();
		boolean buySuc = machine.createTicket(ticketType, originName, destinationName);
		if (buySuc){			
			return machine.printTicket();
//			boolean enoughMoney = machine.collectMoney();
//			if (enoughMoney){
//				machine.giveCharge();
//				machine.printTicket();	
//			}
		}
		else{
			return "Error founded in your input station name. Please do it again!";
		}
	}
}
