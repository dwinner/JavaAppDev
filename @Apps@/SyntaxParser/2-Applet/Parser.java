/* ���� ������ �������� ����������-����������������
����������, ������� ���������� ����������. */

// ����� ���������� ��� ������ �����������.
class ParserException extends Exception {

	private String errStr; // �������� ������.

	public ParserException(String str) {
		errStr = str;
	}

	public String toString() {
		return errStr;
	}
}

class Parser {
	// ����� ������������� ���� ������.
	private static final int NONE = 0;
	private static final int DELIMITER = 1;
	private static final int VARIABLE = 2;
	private static final int NUMBER = 3;
	// ���� �������������� ������.
	private static final int SYNTAX = 0;
	private static final int UNBALPARENS = 1;
	private static final int NOEXP = 2;
	private static final int DIVBYZERO = 3;
	// �������, ���������� ����� ���������.
	private static final String EOE = "\0";
	private String exp;   // ������ �� ������ � ����������.
	private int expIdx;   // ������� ������.
	private String token; // �������� ������� �������.
	private int tokType;  // �������� ���� ������� �������.
	// ������ ��� ����������
	private double vars[] = new double[26];

	// ������� ����� �����������.
	public double evaluate(String expstr) throws ParserException {
		double result;
		exp = expstr;
		expIdx = 0;
		getToken();
		if (token.equals(EOE))
			handleErr(NOEXP); // ��������� �����������.
		// ������ � ���������� ���������.
		result = evalExp1();
		if (!token.equals(EOE)) // ��������� ������� ������ ���� EOE
			handleErr(SYNTAX);
		return result;
	}
    
	// ������������.
	private double evalExp1() throws ParserException {
		double result;
		int varIdx;
		int ttokType;
		String temptoken;
		if (tokType == VARIABLE) {
			// ���������� ���������� �������.
			temptoken = new String(token);
			ttokType = tokType;
			// ������ �������.
			varIdx = Character.toUpperCase(token.charAt(0)) - 'A';
			getToken();
			if (!token.equals("=")) {
				putBack(); // ���������� ������� �������
				// ������������ �������������� �������. ��� ������������.
				token = new String(temptoken);
				tokType = ttokType;
			}
			else {
				getToken(); // �������� ��������� ����� ���������.
				result = evalExp2();
				vars[varIdx] = result;
				return result;
			}
		}
		return evalExp2();  
	}

	// �������� ��� ��������� ���� ������.
	private double evalExp2() throws ParserException {
		char op;
		double result;
		double partialResult;
		result = evalExp3();
		while ((op = token.charAt(0)) == '+' || op == '-') {
			getToken();
			partialResult = evalExp3();
			switch (op) {
				case '-':
					result -= partialResult;
					break;
				case '+':
					result += partialResult;
					break;
			}
		}
		return result;
	}
    
	// ������������ ��� ������� ���� ��������.
	private double evalExp3() throws ParserException {
		char op;
		double result;
		double partialResult;
		result = evalExp4();
		while ((op = token.charAt(0)) == '*' || op == '/' || op == '%') {
			getToken();
			partialResult = evalExp4();
			switch (op) {
				case '*':
					result *= partialResult;
					break;
				case '/':
					if (partialResult == 0.0)
						handleErr(DIVBYZERO);
					result /= partialResult;
					break;
				case '%':
					if (partialResult == 0.0)
						handleErr(DIVBYZERO);
					result %= partialResult;
					break;
			}
		}
		return result;
	}

	// ��������� ���������� � �������.
	private double evalExp4() throws ParserException {
		double result;
		double partialResult;
		double ex;
		int t;
		result = evalExp5();
		if (token.equals("^")) {
			getToken();
			partialResult = evalExp4();
			ex = result;
			if (partialResult == 0.0) {
				result = 1.0;
			}
			else {
				for (t = (int) partialResult - 1; t > 0; t--)
					result *= ex;
			}
		}
		return result;
	}

	// ���������� ������� + ��� -.
	private double evalExp5() throws ParserException {
		double result;
		String  op;
		op = "";
		if ((tokType == DELIMITER) && token.equals("+") || token.equals("-")) {
			op = token;
			getToken();
		}
		result = evalExp6();
		if (op.equals("-"))
			result = -result;
		return result;
	}

	// ���������� ��������� � �������.
	private double evalExp6() throws ParserException {
		double result;
		if (token.equals("(")) {
			getToken();
			result = evalExp2();
			if (!token.equals(")"))
				handleErr(UNBALPARENS);
			getToken();
		}
		else {
			result = atom();
		}
		return result;
	}
  
	// �������� �������� ����� ��� ����������.
	private double atom() throws ParserException {
		double result = 0.0;
		switch (tokType) {
			case NUMBER:
				try {
					result = Double.parseDouble(token);
				}
				catch (NumberFormatException exc) {
					handleErr(SYNTAX);
				}
				getToken();
				break;
			case VARIABLE:
				result = findVar(token);
				getToken();
				break;
			default:
				handleErr(SYNTAX);
				break;
		}
		return result;
	}

	// ���������� �������� ����������.
	private double findVar(String vname) throws ParserException {
		if (!Character.isLetter(vname.charAt(0))) {
			handleErr(SYNTAX);
			return 0.0;
		}
		return vars[Character.toUpperCase(vname.charAt(0))-'A'];
	}

	// ���������� ������� �� ������� �����.
	private void putBack() {
		if (token == EOE)
			return;
		for (int i=0; i < token.length(); i++)
			expIdx--;
	}

	// ���������� ������.
	private void handleErr(int error) throws ParserException {
		String[] err = {
			"Syntax Error",
			"Unbalanced Parentheses",
			"No Expression Present",
			"Division by Zero"
		};
		throw new ParserException(err[error]);
	}
    
	// ��������� ��������� �������.
	private void getToken() {
		tokType = NONE;
		token = "";
		// �������� �� ��������� ���������.
		if (expIdx == exp.length()) {
			token = EOE;
			return;
		}
		// ���������� �������.
		while (expIdx < exp.length() && Character.isWhitespace(exp.charAt(expIdx)))
			++expIdx;
		// �������� ��������� ���������
		if (expIdx == exp.length()) {
			token = EOE;
			return;
		}
		if (isDelim(exp.charAt(expIdx))) { // ��������
			token += exp.charAt(expIdx);
			expIdx++;
			tokType = DELIMITER;
		}
		else if (Character.isLetter(exp.charAt(expIdx))) { // ����������
			while (!isDelim(exp.charAt(expIdx))) {
				token += exp.charAt(expIdx);
				expIdx++;
				if (expIdx >= exp.length()) 
					break;
			}
			tokType = VARIABLE;
		}
		else if (Character.isDigit(exp.charAt(expIdx))) { // �����
			while (!isDelim(exp.charAt(expIdx))) {
				token += exp.charAt(expIdx);
				expIdx++;
				if (expIdx >= exp.length())
					break;
			}
			tokType = NUMBER;
		}
		else { // ����������� ������. �����.
			token = EOE;
			return;
		}
	}
    
	// ���������� true, ���� � �������� �������������.
	private boolean isDelim(char c) {
		return ((" +-/*%^=()".indexOf(c) != -1)) ? true : false;
	}
}