// Поиск вглубь.
import java.util.*;
import java.io.*;

public class Depth {
  
	private static final int MAX = 100;	// Максимальное количество соединений.
	private FlightInfo flights[] = new FlightInfo[MAX];	// Этот массив содержит информацию о полетах.
	private int numFlights = 0; // Количество элементов массива.
	private Stack btStack = new Stack(); // Стек возвратов.

	public static void main(String args[]) {

		String to, from;
		Depth ob = new Depth();
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
  
	// Инициализация базы данных маршрутов.
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

	// Поместить маршрут в базу данных.
	private void addFlight(String from, String to, int dist) {

		if (numFlights < MAX) {
			flights[numFlights] = new FlightInfo(from, to, dist);
			numFlights++;
		}
		else
			System.out.println("Flight database full.\n");
	}

	// Показать маршрут и дистанцию.
	private void route(String to) {
		Stack rev = new Stack();
		int dist = 0;
		FlightInfo f;
		int num = btStack.size();

		// Перевернем стек для отображения маршрута.
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

	/* Если связь между городами есть, то возвращается расстояние между
	двумя городами, в противном случае возвращается 0. */
	private int match(String from, String to) {
		for (int i = numFlights - 1; i > -1; i--) {
			if (flights[i].getFrom().equals(from) && flights[i].getTo().equals(to) && !flights[i].isSkip()) {
				flights[i].setSkip(true); // Предупреждение повторного использования.
				return flights[i].getDistance();
			}
		}
		return 0; // Нет связи
	}
  
	// Наличие маршрута из данного города.
	private FlightInfo find(String from) {

		for (int i=0; i < numFlights; i++) {
			if (flights[i].getFrom().equals(from) && !flights[i].isSkip()) {
				FlightInfo f = new FlightInfo(
					flights[i].getFrom(),
                    flights[i].getTo(),
                    flights[i].getDistance()
				);
				flights[i].setSkip(true); // Запретить повторное использование.
				return f;
			}
		}

		return null;
	}
  
	// Определение маршрута между городами.
	private void isflight(String from, String to) {
		int dist;
		FlightInfo f;

		// Проверить пункт назначения.
		dist = match(from, to);
		if (dist != 0) {
			btStack.push(new FlightInfo(from, to, dist));
			return;
		}

		// Попробовать другой маршрут.
		f = find(from);
		if (f != null) {
			btStack.push(new FlightInfo(from, to, f.getDistance()));
			isflight(f.getTo(), to);
		}
		else if (btStack.size() > 0) {
			// Вернуться и попробовать другой маршрут.
			f = (FlightInfo) btStack.pop();
			isflight(f.getFrom(), f.getTo());
		}
	}
}