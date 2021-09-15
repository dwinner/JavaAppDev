// ���������� ���������� ������� � ������� �������� �����.
import java.util.*;
import java.io.*;

public class NodeR {
  
	private static final int MAX = 100;
	
	// ���� ������ �������� ���������� � �������.
	private FlightInfo flights[] = new FlightInfo[MAX];

	private int numFlights = 0;	// ���������� ��������� �������.

	private Stack btStack = new Stack(); // ���� ���������.

	public static void main(String args[]) {
		String to, from;
		NodeR ob = new NodeR();
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
					// ��������� ������� � �����.
					f = (FlightInfo) ob.btStack.peek();

					ob.route(to); // ���������� ������� ����.

					ob.resetAllSkip(); // �������������� ��� ���� skip.

					/* ������� ��������� ������� ����������� ����
					�� ���� ������ ���������. */
					ob.remove(f);

					// �������������� ���� ���������.
					ob.btStack = new Stack();
				}
			}
			while (!done);
		}
		catch (IOException exc) { 
			System.out.println("Error on input.");
		}
	}
  
	// ���������������� ���� ������ �������.
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
  
	// ���������� �������� � ���� ������.
	private void addFlight(String from, String to, int dist) {
		if (numFlights < MAX) {
			flights[numFlights] = new FlightInfo(from, to, dist);

			numFlights++;
		}
		else
			System.out.println("Flight database full.\n");
	}

	// �������� ���� � ����� ����������.
	private void route(String to) {
		Stack rev = new Stack();
		int dist = 0;
		FlightInfo f;
		int num = btStack.size();

		// ���������� ���� ��� ����������� ����.
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

	/* ���� ���������� ������� ����� from � to, ������� ����������,
	� ��������� ������ ������� 0. */
	private int match(String from, String to) {
		for (int i=numFlights-1; i > -1; i--) {
			if (flights[i].getFrom().equals(from) && flights[i].getTo().equals(to) && !flights[i].isSkip()) {
				flights[i].setSkip(true);
				return flights[i].getDistance();
			}
		}
		return 0; 
	}
  
	// ����� ����� �������.
	private FlightInfo find(String from) {
		for (int i=0; i < numFlights; i++) {
			if (flights[i].getFrom().equals(from) && !flights[i].isSkip()) {
				FlightInfo f = new FlightInfo(
					flights[i].getFrom(),
                    flights[i].getTo(),
                    flights[i].getDistance()
                );
                flights[i].setSkip(true); // ��������� ��������� �������������.

                return f;
            }
        }
        return null;
    }
  
    // ���� �� ������� ����� from � to? 
    private void isflight(String from, String to) {
    	int dist;
    	FlightInfo f;

    	// ���� �� ����� ��������?.
    	dist = match(from, to);
    	if (dist != 0) {
    		btStack.push(new FlightInfo(from, to, dist));
    		return;
    	}

    	// ����������� ������ �������.
    	f = find(from);
    	if (f != null) {
    		btStack.push(new FlightInfo(from, to, f.getDistance()));
    		isflight(f.getTo(), to);
    	}
    	else if (btStack.size() > 0) {
    		// ��������� � ����������� ������ �������.
    		f = (FlightInfo) btStack.pop();
    		isflight(f.getFrom(), f.getTo());
    	}
    }

    // �������������� ��� ���� skip.
    private void resetAllSkip() {
    	for (int i=0; i< numFlights; i++)
    		flights[i].setSkip(false);
    }

    // ������� �������.
    private void remove(FlightInfo f) {
    	for (int i=0; i< numFlights; i++)
    		if (flights[i].getFrom().equals(f.getFrom()) && flights[i].getTo().equals(f.getTo()))
    			flights[i].setFrom("");
    }

}