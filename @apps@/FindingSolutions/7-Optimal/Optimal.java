// Нахождение "оптимального" решения при использовании критерия наименьшей стоимости.
import java.util.*;
import java.io.*;

public class Optimal {
  
	private static final int MAX = 100;

	// Этот массив содержит информацию о полетах.
	private FlightInfo flights[] = new FlightInfo[MAX];

	private int numFlights = 0; // Число элементов массива.

	private Stack btStack = new Stack(); // Стек возвратов.

	private Stack optimal; // Содержит оптимальные решения.

	private int minDist = 10000;

	public static void main(String args[]) {
		String to, from;
		Optimal ob = new Optimal();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean done = false;
		FlightInfo f;

		ob.setup();  

		try {
			System.out.print("From? ");
			from = br.readLine();
			System.out.print("To? ");
			to = br.readLine();
			do {
				ob.isflight(from, to);

				if (ob.btStack.size() == 0)
					done = true;
				else {
					ob.route(to);
					ob.btStack = new Stack();
				}
			}
			while (!done);

			// Отобразить оптимальное решение.
			if (ob.optimal != null) {
				System.out.println("Optimal solution is: ");

				int num = ob.optimal.size();
				for (int i=0; i < num; i++) {
					f = (FlightInfo) ob.optimal.pop();
					System.out.print(f.getFrom() + " to ");
				}

				System.out.println(to);
				System.out.println("Distance is " + ob.minDist);
			}
		}
		catch (IOException exc) {
			System.out.println("Error on input.");
		}
	}
  
	// Инициализировать базу данных полетов.
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
  
	// Расположить маршруты в базе данных.
	private void addFlight(String from, String to, int dist) {
		if (numFlights < MAX) {
			flights[numFlights] = new FlightInfo(from, to, dist);
			numFlights++;
		}
		else
			System.out.println("Flight database full.\n");
	}

	// Сохранить самый короткий путь.
	private void route(String to) {
		int dist = 0;
		FlightInfo f;
		int num = btStack.size();
		Stack optTemp = new Stack();

		for (int i=0; i < num; i++) {
			f = (FlightInfo) btStack.pop();
			optTemp.push(f); // Сохранить маршрут
			dist += f.getDistance();
		}

		// Если кратчайший путь, то сохранить.
		if (minDist > dist) {
			optimal = optTemp;
			minDist = dist;
		}
	}

	private int match(String from, String to) {
		for (int i=numFlights-1; i > -1; i--) {
			if (flights[i].getFrom().equals(from) && flights[i].getTo().equals(to) && !flights[i].isSkip()) {
				flights[i].setSkip(true);
				return flights[i].getDistance();
			}
		}

		return 0; 
	}
  
	private FlightInfo find(String from) {
		int pos = -1;
		int dist = 10000;

		for (int i=0; i < numFlights; i++) {
			if (flights[i].getFrom().equals(from) && !flights[i].isSkip()) {
				if (flights[i].getDistance() < dist) {
					pos = i;
					dist = flights[i].getDistance();
				}
			}
		}

		if (pos != -1) {
			flights[pos].setSkip(true);
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