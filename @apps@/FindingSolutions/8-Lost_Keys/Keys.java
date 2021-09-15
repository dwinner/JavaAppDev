// ����� ���������� �����!
import java.util.*;
import java.io.*;

// ���������� � ��������.
class RoomInfo {
	
	String from;
	String to;
	boolean skip;

	RoomInfo(String f, String t) {
		from = f;
		to = t;
		skip = false;
	}
}

public class Keys {
  
	final int MAX = 100;

	// ���� ������ �������� ���������� � ��������.
	RoomInfo room[] = new RoomInfo[MAX];

	int numRooms = 0; // ����� ������.

	Stack btStack = new Stack(); // ���� ���������.

	public static void main(String args[]) {
		String to, from;
		Keys ob = new Keys();

		ob.setup();

		from = "front_door";
		to = "keys";

		ob.iskeys(from, to);

		if (ob.btStack.size() != 0)
			ob.route(to);
	}
  
	// ������������� ���� ������ ������.
	void setup() {
		addRoom("front_door", "lr");
		addRoom("lr", "bath");
		addRoom("lr", "hall");
		addRoom("hall", "bd1");
		addRoom("hall", "bd2");
		addRoom("hall", "mb");
		addRoom("lr", "kitchen");
		addRoom("kitchen", "keys");
	}
  
	// ���������� ������� � ���� ������.
	void addRoom(String from, String to) {
		if (numRooms < MAX) {
			room[numRooms] = new RoomInfo(from, to);
			numRooms++;
		}
		else
			System.out.println("Room database full.\n");
	}

	// �������� ���� � ������ ����������.
	void route(String to) {
		Stack rev = new Stack();
		RoomInfo r;
		int num = btStack.size();

		// ����������� ���� ��� ����������� ����.
		for (int i=0; i < num; i++)
			rev.push(btStack.pop());

		for (int i=0; i < num; i++) {
			r = (RoomInfo) rev.pop();
			System.out.print(r.from + " to ");
		}

		System.out.println(to);
	}

	// ���� ���� ������� ����� from � to, ���������� true, ����� false.
	boolean match(String from, String to) {
		for (int i=numRooms-1; i > -1; i--) {
			if (room[i].from.equals(from) && room[i].to.equals(to) && !room[i].skip) {
				room[i].skip = true; // ��������� ��������� �������������.
				return true;
			}
		}

		return false; // �� �������
	}
  
	// ����� ����� ����.
	RoomInfo find(String from) {
		for (int i=0; i < numRooms; i++) {
			if (room[i].from.equals(from) && !room[i].skip) {
				RoomInfo r = new RoomInfo(
					room[i].from,
                    room[i].to
                );
                room[i].skip = true; // ��������� ��������� �������������.

                return r;
            }
        }
        return null;
    }
  
    // ����������, ���� �� ���� ����� from � to. 
    void iskeys(String from, String to) {
    	int dist;
    	RoomInfo r;

    	// ���� �� ����� ��������.
    	if (match(from, to)) {
    		btStack.push(new RoomInfo(from, to));
    		return;
    	}

    	// ����������� ������ �������.
    	r = find(from);
    	if (r != null) {
    		btStack.push(new RoomInfo(from, to));
    		iskeys(r.to, to);
    	}
    	else if (btStack.size() > 0) {
    		// ��������� � ����������� ������ �������.
    		r = (RoomInfo) btStack.pop();
    		iskeys(r.from, r.to);
    	}
    }

}