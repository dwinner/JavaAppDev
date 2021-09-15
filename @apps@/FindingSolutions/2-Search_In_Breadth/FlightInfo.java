/**
 * Информация о полетах
 * @author dwinner@inbox.ru
 */
public class FlightInfo {
	
	private String from;
	private String to;
	private int distance;
	private boolean skip; // Используется для указания возврата.

	public FlightInfo(String f, String t, int d) {
		from = f;
		to = t;
		distance = d;
		skip = false;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public boolean isSkip() {
		return skip;
	}
	
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
}