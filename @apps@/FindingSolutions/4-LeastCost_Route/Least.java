// Поиск по критерию наименьшей стоимости.
import java.util.*;
import java.io.*;

public class Least {

	private static final int MAX = 100;
    private FlightInfo flights[] = new FlightInfo[MAX];
	private int numFlights = 0;
	private Stack btStack = new Stack();

	public static void main(String args[]) {
		String to, from;
		Least ob = new Least();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ob.setup();

		try {
			System.out.print("From? ");
			from = br.readLine();
			System.out.print("To? ");
			to = br.readLine();

			ob.isflight(from, to);

			if (ob.btStack.size() != 0)
				ob.route(to);
		}
		catch (IOException exc) {
			System.out.println("Error on input.");
		}
	}
  
	private void setup() {
		addFlight("New York", "Chicago", 900);
		addFlight("Chicago", "Denver", 1000);
		addFlight("New York", "Toronto", 500);
		addFlight("New York", "Denver", 1800);
		addFlight("Toronto", "Calgary", 1700);
		addFlight("Toronto", "Los Angeles", 2500);
		addFlight("Toronto", "Chicago", 500);
		addFlight("Denver", "Urbana", 1000);
		addFlight("Denver", "Houston", 1000);
		addFlight("Houston", "Los Angeles", 1500);
		addFlight("Denver", "Los Angeles", 1000);
	}
  
	private void addFlight(String from, String to, int dist) {
		if (numFlights < MAX) {
			flights[numFlights] = new FlightInfo(from, to, dist);
			numFlights++;
		}
		else
			System.out.println("Flight database full.\n");
	}

	private void route(String to) {
		Stack rev = new Stack();
		int dist = 0;
		FlightInfo f;
		int num = btStack.size();

		for (int i=0; i < num; i++)
			rev.push(btStack.pop());

		for (int i=0; i < num; i++) {
			f = (FlightInfo) rev.pop();
			System.out.print(f.getFrom() + " to ");
			dist += f.getDistance();
		}

		System.out.println(to);
		System.out.println("Distance is " + dist);
	}

	private int match(String from, String to) {
		int t;
  
		for (t = numFlights - 1; t > -1; t--) {
			if (flights[t].getFrom().equals(from) && flights[t].getTo().equals(to))
				return flights[t].getDistance();
		}
  
		return 0;
	}

	// Поиск кратчайшего маршрута.
	private FlightInfo find(String from) {
		int pos = -1;
		int dist = 10000; // Длиннее, чем самый длинный маршрут.

		for (int i=0; i < numFlights; i++) {
			if (flights[i].getFrom().equals(from) && !flights[i].isSkip()) {
				if (flights[i].getDistance() < dist) {
					pos = i;
					dist = flights[i].getDistance();
				}
			}
		}

		if (pos != -1) {
			flights[pos].setSkip(true); // Запрет повторного использования.
			FlightInfo f = new FlightInfo(
				flights[pos].getFrom(),
                flights[pos].getTo(),
                flights[pos].getDistance()
			);
			return f;
		}

		return null;
	}
  
	private void isflight(String from, String to) {
		int dist;
		FlightInfo f;

		dist = match(from, to);
		if (dist != 0) {
			btStack.push(new FlightInfo(from, to, dist));
			return;
		}

		f = find(from);
		if (f != null) {
			btStack.push(new FlightInfo(from, to, f.getDistance()));
			isflight(f.getTo(), to);
		}
		else if (btStack.size() > 0) {
			f = (FlightInfo) btStack.pop();
			isflight(f.getFrom(), f.getTo());
		}
	}

}