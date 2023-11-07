public class Test {

	private int idTest;
	private int numberQuest;
	private String testName;
	private int currentNumberQuest;
	
}

public class LineRequestQuest {

	private int questID;
	
	public void answerQuest() {
		Vector q = new Vector();
		q.add(makeRequest());
	}
	
	public Quest makeRequest() {
		return new Quest();
	}
	
}

public class Quest {

	private int idQuest;
	private int testID;
	
	public Quest() {
	}
	
}
