/* Этот модуль содержит рекурсивно-последовательный анализатор,
который не использует переменных. */
   
// Класс исключений для ошибок анализатора.  
class ParserException extends Exception {

	private String errStr; // Описание ошибки.

	public ParserException(String str) {
		errStr = str;
	}

	public String toString() {
		return errStr;
	}
}

class Parser {
	// Объявление лексем. 
	private static final int NONE = 0;
	private static final int DELIMITER = 1; 
	private static final int VARIABLE = 2;
	private static final int NUMBER = 3;

	// Объявление констант синтаксических ошибок. 
	private static final int SYNTAX = 0; 
    private static final int UNBALPARENS = 1; 
    private static final int NOEXP = 2; 
    private static final int DIVBYZERO = 3; 

    // Эта лексема определяет конец выражения. 
    private static final String EOE = "\0"; 

    private String exp;   // Ссылка на строку с выражением.
    private int expIdx;   // Текущий индекс в выражении.
    private String token; // Сохранение текущей лексемы.
    private int tokType;  // Содержит тип текущей лексемы.

    // Точка входа анализатора.  
    public double evaluate(String expstr) throws ParserException {
    	double result;
    	exp = expstr;
    	expIdx = 0;
    	getToken();  
    	if (token.equals(EOE))
    		handleErr(NOEXP); // Нет выражения.
    	// Анализ и вычисление выражения.
    	result = evalExp2();
    	if (!token.equals(EOE)) // Последняя лексема должна быть EOE.
    		handleErr(SYNTAX);
    	return result;
    }

    // Сложить или вычесть два терма.  
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
    
    // Умножить или разделить два фактора.  
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
    
    // Выполнить возведение в степень
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
    
    // Определить унарные + или -.
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
    
    // Обработать выражение в скобках.  
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
    
    // Получить значение числа.  
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
    		default:
    			handleErr(SYNTAX);
    			break;
    	}
    	return result;
    }

    // Обработка ошибки.  
    private void handleErr(int error) throws ParserException {
    	String[] err = {
    		"Syntax Error",
    		"Unbalanced Parentheses",
    		"No Expression Present",
    		"Division by Zero"
    	};
    	throw new ParserException(err[error]);
    }

  	// Получить следующую лексему.
  	private void getToken() {
  		tokType = NONE;
  		token = "";

  		// Проверить окончание выражения.
  		if (expIdx == exp.length()) {
  			token = EOE; 
  			return;
  		}

  		// Пропустить пробелы.
  		while (expIdx < exp.length() && Character.isWhitespace(exp.charAt(expIdx)))
  			++expIdx;

  		// Проверить окончание выражения.
  		if (expIdx == exp.length()) {
  			token = EOE;
  			return;
  		}

  		if (isDelim(exp.charAt(expIdx))) { // Оператор.
  			token += exp.charAt(expIdx);
  			expIdx++;
  			tokType = DELIMITER;
  		}
  		else if (Character.isLetter(exp.charAt(expIdx))) { // Переменная.
  			while (!isDelim(exp.charAt(expIdx))) {
  				token += exp.charAt(expIdx);
  				expIdx++;
  				if (expIdx >= exp.length())
  					break;
  			}
  			tokType = VARIABLE;
  		}
  		else if (Character.isDigit(exp.charAt(expIdx))) { // Число.
  			while (!isDelim(exp.charAt(expIdx))) {
  				token += exp.charAt(expIdx);
  				expIdx++;
  				if (expIdx >= exp.length())
  					break;
  			}
  			tokType = NUMBER;
  		}
  		else { // Неизвестный символ.
  			token = EOE;
  			return;
  		}
  	}
    
  	// Возвращает true, если с является разделителем.   
  	private boolean isDelim(char c) {
  		return ((" +-/*%^=()".indexOf(c) != -1)) ? true : false;
  	}

}