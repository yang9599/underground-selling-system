package ticketseller;

public class Station {	
	public String stationName;
	private double latitude;
	private double longitude;
	
	public Station(String stationName){
		this.stationName = stationName;
	}
		
	public String originToDest(Station station){
		return this.stationName+"->"+station.stationName;
	}
}