// Интерпретатор языка Small Basic.
import java.io.*;
import java.util.*;

// Класс исключений для ошибок интерпретатора.

// Класс интерпретатора языка Small Basic.
class InterpreterException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errStr; // Описание ошибки.

    public InterpreterException(String str) {
		errStr = str;
    }

    @Override
    public String toString() {
		return errStr;
    }
}

// Класс интерпретатора языка Small Basic.
class SBasic {

    public static final int PROG_SIZE = 10000; // Максимальный размер программы.

    // Типы лексем.
    public static final int NONE = 0;
    public static final int DELIMITER = 1;
    public static final int VARIABLE = 2;
    public static final int NUMBER = 3;
    public static final int COMMAND = 4;
    public static final int QUOTEDSTR = 5;

    // Типы ошибок.
    public static final int SYNTAX = 0;
    public static final int UNBALPARENS = 1;
    public static final int NOEXP = 2;
    public static final int DIVBYZERO = 3;
    public static final int EQUALEXPECTED = 4;
    public static final int NOTVAR = 5;
    public static final int LABELTABLEFULL = 6;
    public static final int DUPLABEL = 7;
    public static final int UNDEFLABEL = 8;
    public static final int THENEXPECTED = 9;
    public static final int TOEXPECTED = 10;
    public static final int NEXTWITHOUTFOR = 11;
    public static final int RETURNWITHOUTGOSUB = 12;
    public static final int MISSINGQUOTE = 13;
    public static final int FILENOTFOUND = 14;
    public static final int FILEIOERROR = 15;
    public static final int INPUTIOERROR = 16;

    // Внутреннее представление ключевых слов Small Basic.
    public static final int UNKNCOM = 0;
    public static final int PRINT = 1;
    public static final int INPUT = 2;
    public static final int IF = 3;
    public static final int THEN = 4;
    public static final int FOR = 5;
    public static final int NEXT = 6;
    public static final int TO = 7;
    public static final int GOTO = 8;
    public static final int GOSUB = 9;
    public static final int RETURN = 10;
    public static final int END = 11;
    public static final int EOL = 12;

    // Лексема конца программы.
    public static final String EOP = "\0";

    // Коды для двойных операторов, таких как <=.
    public static final char LE = 1;
    public static final char GE = 2;
    public static final char NE = 3;

    // Массив для переменных.
    private double vars[];

    // В этом классе связываются ключевые слова с их лексемами.
    private class Keyword {

        private String keyword; // Строка.
        private int keywordTok; // Внутреннее представление.

        public Keyword(String keyword, int keywordTok) {
            this.keyword = keyword;
            this.keywordTok = keywordTok;
        }
		
		public String getKeyword() {
			return keyword;
		}
		
		public int getKeywordTok() {
			return keywordTok;
		}
		
    }

    /* Таблица ключевых слов с их внутренним представлением
    Все слова должны вводиться в нижнем регистре. */
    private Keyword kwTable[] = {
        new Keyword("print", PRINT),
        new Keyword("input", INPUT),
        new Keyword("if", IF),
        new Keyword("then", THEN),
        new Keyword("goto", GOTO),
        new Keyword("for", FOR),
        new Keyword("next", NEXT),
        new Keyword("to", TO),
        new Keyword("gosub", GOSUB),
        new Keyword("return", RETURN),
        new Keyword("end", END)
    };

    private char[] prog; // Ссылка на массив с программой.
    private int progIdx; // Текущий индекс в программе.

    private String token; // Сохраняет текущую лексему.
    private int tokType;  // Сохраняет тип лексемы.

    private int kwToken; // Внутреннее представление ключевого слова.

    // Поддержка цикла FOR.
    private class ForInfo {
        int var; // Счетчик.
        double target; // Значение
        int loc; // Индекс в исходном коде для цикла.
    }

    // Стек для циклов FOR.
    private Stack fStack;

    // Определяем таблицу вхождения меток.
    private class Label {

        public String name; // Метка.
        public int loc; // Индекс положения метки в исходном файле.

        public Label(String name, int loc) {
            this.name = name;
            this.loc = loc;
        }
    }

    // Распределение меток.
    private TreeMap labelTable;

    // Стек для gosub.
    private Stack gStack;

    // Операторы отношения.
    private char rops[] = { GE, NE, LE, '<', '>', '=', 0 };

    /* Создать строку, содержащую операторы отношения, для того
    чтобы сделать их проверку более удобной. */
    private String relops = new String(rops);

    // Конструктор для Small Basic.
    public SBasic(String progName) throws InterpreterException {
        char tempbuf[] = new char[PROG_SIZE];
        int size;
        // Загрузить программу для выполнения.
        size = loadProgram(tempbuf, progName);
        if (size != -1) {
            // Создать соответствующий массив для хранения программы.
            prog = new char[size];
            // Копировать программу в массив.
            System.arraycopy(tempbuf, 0, prog, 0, size);
        }
    }

    // Загрузить программу.
    private int loadProgram(char[] p, String fname) throws InterpreterException {
        int size = 0;
        try {
            FileReader fr = new FileReader(fname);
            BufferedReader br = new BufferedReader(fr);
            size = br.read(p, 0, PROG_SIZE);
            fr.close();
        }
        catch (FileNotFoundException exc) {
            handleErr(FILENOTFOUND);
        }
        catch (IOException exc) {
            handleErr(FILEIOERROR);
        }
        // Если файл оканчивается маркером EOF, сделать возврат.
        if (p[size-1] == (char) 26) {
            size--;
		}
        return size; // Возвратить размер программы.
    }

    // Выполнить программу.
    public void run() throws InterpreterException {
        // Инициализировать для запуска новой программы.
        vars = new double[26];
        fStack = new Stack();
        labelTable = new TreeMap();
        gStack = new Stack();
        progIdx = 0;
        scanLabels(); // Найти метки в программе.
        sbInterp(); // Выполнить.
    }

    // Точка входа для интепретатора Small Basic.
    private void sbInterp() throws InterpreterException {
        // Основной цикл интерпретатора.
        do {
            getToken();
            // Проверка на наличие оператора присваивания.
            if (tokType == VARIABLE) {
                putBack(); // Возвратить переменную во входной поток.
                assignment(); // Обработать оператор присваивания.
            }
            else { // Ключевое слово
                switch (kwToken) {
                    case PRINT: print(); break;
                    case GOTO: execGoto(); break;
                    case IF: execIf(); break;
                    case FOR: execFor(); break;
                    case NEXT: next(); break;
                    case INPUT: input(); break;
                    case GOSUB: gosub(); break;
                    case RETURN: greturn(); break;
                    case END: return;
                }
            }
        }
        while (!token.equals(EOP));
    }

    // Найти все метки.
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    private void scanLabels() throws InterpreterException {
        int i;
        Object result;
        // Просмотреть, является ли первая лексема в файле меткой.
        getToken();
        if (tokType == NUMBER) {
            labelTable.put(token, new Integer(progIdx));
        }
        findEOL();
        do {
            getToken();
            if (tokType == NUMBER) { // Должен быть номер строки.
                result = labelTable.put(token, new Integer(progIdx));
                if (result != null) {
                    handleErr(DUPLABEL);
                }
            }
            // Найти следующую строку.
            if (kwToken != EOL) {
                findEOL();
            }
        }
        while (!token.equals(EOP));
        progIdx = 0; // Переустановить индекс в начало программы.
    }

    // Найти начало следующей строки.
    private void findEOL() {
        while (progIdx < prog.length && prog[progIdx] != '\n') {
            ++progIdx;
        }
        if (progIdx < prog.length) {
            progIdx++;
        }
    }

    // Присвоить переменной значение.
    private void assignment() throws InterpreterException {
        int var;
        double value;
        char vname;
        // Получить имя переменной.
        getToken();
        vname = token.charAt(0);
        if (!Character.isLetter(vname)) {
            handleErr(NOTVAR);
            return;
        }
        // Преобразовать индекс по таблице переменных.
        var = (int) Character.toUpperCase(vname) - 'A';
        // Получить знак равенства.
        getToken();
        if (!token.equals("=")) {
            handleErr(EQUALEXPECTED);
            return;
        }
        // Получить значение для присваивания.
        value = evaluate();
        // Присвоить значение.
        vars[var] = value;
    }

    // Выполнить простую версию утверждения PRINT.
    private void print() throws InterpreterException {
        double result;
        int len = 0, spaces;
        String lastDelim = "";
        do {
            getToken(); // Получить следующий элемент списка.
            if (kwToken == EOL || token.equals(EOP)) {
                break;
            }
            if (tokType == QUOTEDSTR) { // Строка.
                System.out.print(token);
                len += token.length();
                getToken();
            }
            else { // Выражение.
                putBack();
                result = evaluate();
                getToken();
                System.out.print(result);
                // Добавить длину выхода для полного выполнения.
                Double t = new Double(result);
                len += t.toString().length(); // Сохранить длину.
            }
            lastDelim = token;
            // Если запятая, переместиться к следующей точке.
            if (lastDelim.equals(",")) {
                // Подсчитать число пробелов для табуляции.
                spaces = 8 - (len % 8);
                len += spaces; // Добавить позицию табуляции.
                while (spaces != 0) {
                    System.out.print(" ");
                    spaces--;
                }
            }
            else if (token.equals(";")) {
                System.out.print(" ");
                len++;
            }
            else if (kwToken != EOL && !token.equals(EOP)) {
                handleErr(SYNTAX);
            }
        }
        while (lastDelim.equals(";") || lastDelim.equals(","));
            if (kwToken == EOL || token.equals(EOP)) {
                if (!lastDelim.equals(";") && !lastDelim.equals(",")) {
                    System.out.println();
                }
            }
            else {
                handleErr(SYNTAX);
            }
    }

    // Выполнить утверждение GOTO.
    private void execGoto() throws InterpreterException {
        Integer loc;
        getToken(); // Получить метку.
        // Найти положение метки.
        loc = (Integer) labelTable.get(token);
        if (loc == null) {
            handleErr(UNDEFLABEL);
        }
        else { // Метка не определена.
            progIdx = loc.intValue();
        }
    }

    // Выполнить утверждение if.
    private void execIf() throws InterpreterException {
        double result;
        result = evaluate(); // Получить значение выражения.
        /* Если результат true (не-нуль), обработать if. В противном случае
        перейти к следующей строке программы. */
        if (result != 0.0) {
            getToken();
            if (kwToken != THEN) {
                handleErr(THENEXPECTED);
                return;
            } // Иначе утверждение будет выполнено.
        }
        else { // Найти начало следующей строки.
            findEOL();
        }
    }

    // Выполнить утверждение for.
    @SuppressWarnings("unchecked")
    private void execFor() throws InterpreterException {
        ForInfo stckvar = new ForInfo();
        double value;
        char vname;
        getToken(); // Считать контрольную переменную.
        vname = token.charAt(0);
        if (!Character.isLetter(vname)) {
            handleErr(NOTVAR);
            return;
        }
        // Сохранить индекс контрольной переменной.
        stckvar.var = Character.toUpperCase(vname) - 'A';
        getToken(); // Считать символ равенства.
        if (token.charAt(0) != '=') {
            handleErr(EQUALEXPECTED);
            return;
        }
        value = evaluate(); // Инициализировать.
        vars[stckvar.var] = value;
        getToken(); // Считать и отбросить TO
        if (kwToken != TO) {
            handleErr(TOEXPECTED);
        }
        stckvar.target = evaluate(); // Получить значение.
        /* Если цикл может выполниться по крайней мере один раз,
        то поместить в стек. */
        if (value >= vars[stckvar.var]) {
            stckvar.loc = progIdx;
            fStack.push(stckvar);
        }
        else { // В противном случае пропустить цикл полностью.
            while (kwToken != NEXT) {
                getToken();
            }
        }
    }

    // Выполнить утверждение NEXT.
    @SuppressWarnings({"unchecked", "unchecked"})
    private void next() throws InterpreterException {
        ForInfo stckvar;
        try {
            // Извлечь информацию для цикла for.
            stckvar = (ForInfo) fStack.pop();
            vars[stckvar.var]++; // Инкрементировать управляющую переменную.
            // Если сделано, вернуться.
            if (vars[stckvar.var] > stckvar.target) {
                return;
            }
            // В противном случае, извлечь информацию
            fStack.push(stckvar);
            progIdx = stckvar.loc;  // Цикл.
        }
        catch (EmptyStackException exc) {
            handleErr(NEXTWITHOUTFOR);
        }
    }

    // Выполнить простую форму INPUT.
    private void input() throws InterpreterException {
        int var;
        double val = 0.0;
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getToken(); // Посмотреть на присутствие приглашения.
        if (tokType == QUOTEDSTR) {
            // Если так, напечатать и проверить запятую.
            System.out.print(token);
            getToken();
            if (!token.equals(",")) {
                handleErr(SYNTAX);
            }
            getToken();
        }
        else { // Иначе сделать приглашение с "?".
            System.out.print("? ");
        }
        // Получить входную переменную.
        var =  Character.toUpperCase(token.charAt(0)) - 'A';
        try {
            str = br.readLine();
            val = Double.parseDouble(str); // Считать значение.
        }
        catch (IOException exc) {
            handleErr(INPUTIOERROR);
        }
        catch (NumberFormatException exc) {
            /* Возможно, вы захотите обработать эту ошибку
            по другому, чем другие ошибки интерпретатора. */
            System.out.println("Invalid input.");
        }
        vars[var] = val; // Сохранить.
    }

    // Выпонить утверждение GOSUB.
    @SuppressWarnings("unchecked")
    private void gosub() throws InterpreterException {
        Integer loc;
        getToken();
        // Найти метку для вызова
        loc = (Integer) labelTable.get(token);
        if (loc == null) { // Метка не определена.
            handleErr(UNDEFLABEL);
        }
        else {
            // Сохранить место для возврата.
            gStack.push(new Integer(progIdx));
            // Начать выполение с этого места.
            progIdx = loc.intValue();
        }
    }

    // Возврат из GOSUB.
    private void greturn() throws InterpreterException {
        Integer t;
        try {
            // Восстановить индекс программы.
            t = (Integer) gStack.pop();
            progIdx = t.intValue();
        }
        catch (EmptyStackException exc) {
            handleErr(RETURNWITHOUTGOSUB);
        }
    }

    // **************** Синтаксический анализатор ****************
    // Входная точка анализатора.
    private double evaluate() throws InterpreterException {
        double result = 0.0;
        getToken();
        if (token.equals(EOP)) { // Выражения нет.
            handleErr(NOEXP);
        }
        // Проанализировать и подсчитать выражение.
        result = evalExp1();
        putBack();
        return result;
    }

    // Обработать операторы отношения.
    private double evalExp1() throws InterpreterException {
        double l_temp, r_temp, result;
        char op;
        result = evalExp2();
        // Если конец программы, то возврат.
        if (token.equals(EOP)) {
            return result;
        }
        op = token.charAt(0);
        if (isRelop(op)) {
            l_temp = result;
            getToken();
            r_temp = evalExp1();
            switch (op) { // Выполнить оператор отношения.
                case '<':
                    result = (l_temp < r_temp) ? 1.0 : 0.0;
                    break;
                case LE:
                    result = (l_temp <= r_temp) ? 1.0 : 0.0;
                    break;
                case '>':
                    result = (l_temp > r_temp) ? 1.0 : 0.0;
                    break;
                case GE:
                    result = (l_temp >= r_temp) ? 1.0 : 0.0;
                    break;
                case '=':
                    result = (l_temp == r_temp) ? 1.0 : 0.0;
                    break;
                case NE:
                    result = (l_temp != r_temp) ? 1.0 : 0.0;
                    break;
            }
        }
        return result;
    }

    // Сложить или вычесть два терма.
    @SuppressWarnings("NestedAssignment")
    private double evalExp2() throws InterpreterException {
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

    // Перемножить или разделить два фактора.
    @SuppressWarnings("NestedAssignment")
    private double evalExp3() throws InterpreterException {
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
                    if (partialResult == 0.0) {
                        handleErr(DIVBYZERO);
                    }
                    result /= partialResult;
                    break;
                case '%':
                    if (partialResult == 0.0) {
                        handleErr(DIVBYZERO);
                    }
                    result %= partialResult;
                    break;
            }
        }
        return result;
    }

    // Выпонить возведение в степень.
    private double evalExp4() throws InterpreterException {
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
                for (t = (int) partialResult - 1; t > 0; t--) {
                    result *= ex;
                }
            }
        }
        return result;
    }

    // Оценить унарные + или -.
    private double evalExp5() throws InterpreterException {
        double result;
        String  op;
        op = "";
        if ((tokType == DELIMITER) && token.equals("+") || token.equals("-")) {
            op = token;
            getToken();
        }
        result = evalExp6();
        if (op.equals("-")) {
            result = -result;
        }
        return result;
    }

    // Обработать выражение в круглых скобках.
    private double evalExp6() throws InterpreterException {
        double result;
        if (token.equals("(")) {
            getToken();
            result = evalExp2();
            if (!token.equals(")")) {
                handleErr(UNBALPARENS);
            }
            getToken();
        }
        else {
            result = atom();
        }
        return result;
    }

    // Получить значение числа или переменной.
    private double atom() throws InterpreterException {
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

    // Возвратить значение переменной.
    private double findVar(String vname) throws InterpreterException {
        if (!Character.isLetter(vname.charAt(0))) {
            handleErr(SYNTAX);
            return 0.0;	// ?!
        }
        return vars[Character.toUpperCase(vname.charAt(0))-'A'];
    }

    // Возвратить лексему во входной поток.
    private void putBack() {
        if (token == null ? EOP == null : token.equals(EOP)) {
            return;
        }
        for (int i=0; i < token.length(); i++) {
            progIdx--;
        }
    }

    // Обработать ошибку.
    private void handleErr(int error) throws InterpreterException {
        String[] err = {
            "Syntax Error",
            "Unbalanced Parentheses",
            "No Expression Present",
            "Division by Zero",
            "Equal sign expected",
            "Not a variable",
            "Label table full",
            "Duplicate label",
            "Undefined label",
            "THEN expected",
            "TO expected",
            "NEXT without FOR",
            "RETURN without GOSUB",
            "Closing quotes needed",
            "File not found",
            "I/O error while loading file",
            "I/O error on INPUT statement"
        };
        throw new InterpreterException(err[error]);
    }

    // Получить следующую лексему.
    private void getToken() throws InterpreterException {
        char ch;
        tokType = NONE;
        token = "";
        kwToken = UNKNCOM;
        // Проверить конец программы.
        if (progIdx == prog.length) {
            token = EOP;
            return;
        }
        // Пропустить пробелы.
        while (progIdx < prog.length && isSpaceOrTab(prog[progIdx])) {
            progIdx++;
        }
        // Заключительные пробелы программы.
        if (progIdx == prog.length) {
            token = EOP;
            tokType = DELIMITER;
            return;
        }
        if (prog[progIdx] == '\r') { // Обработать каретку
            progIdx += 2;
            kwToken = EOL;
            token = "\r\n";
            return;
        }
        // Обработка операторов отношения.
        ch = prog[progIdx];
        if (ch == '<' || ch == '>') {
            if (progIdx+1 == prog.length) {
                handleErr(SYNTAX);
            }
            switch (ch) {
                case '<':
                    if (prog[progIdx+1] == '>') {
                        progIdx += 2;
                        token = String.valueOf(NE);
                    }
                    else if (prog[progIdx+1] == '=') {
                        progIdx += 2;
                        token = String.valueOf(LE);
                    }
                    else {
                        progIdx++;
                        token = "<";
                    }
                    break;
                case '>':
                    if (prog[progIdx+1] == '=') {
                        progIdx += 2;
                        token = String.valueOf(GE);
                    }
                    else {
                        progIdx++;
                        token = ">";
                    }
                    break;
            }
            tokType = DELIMITER;
            return;
        }
        if (isDelim(prog[progIdx])) {
            // Оператор
            token += prog[progIdx];
            progIdx++;
            tokType = DELIMITER;
        }
        else if (Character.isLetter(prog[progIdx])) {
            // Переменная или ключевое слово.
            while (!isDelim(prog[progIdx])) {
                token += prog[progIdx];
                progIdx++;
                if (progIdx >= prog.length) {
                    break;
                }
            }
            kwToken = lookUp(token);
            tokType = (kwToken == UNKNCOM) ? VARIABLE : COMMAND;
        }
        else if (Character.isDigit(prog[progIdx])) {
            // Число
            while (!isDelim(prog[progIdx])) {
                token += prog[progIdx];
                progIdx++;
                if (progIdx >= prog.length) {
                    break;
                }
            }
            tokType = NUMBER;
        }
        else if (prog[progIdx] == '"') {
            // Строка в кавычках.
            progIdx++;
            ch = prog[progIdx];
            while (ch != '"' && ch != '\r') {
                token += ch;
                progIdx++;
                ch = prog[progIdx];
            }
            if (ch == '\r') {
                handleErr(MISSINGQUOTE);
            }
            progIdx++;
            tokType = QUOTEDSTR;
        }
        else { // Неизвестный символ, прервать выполнение.
            token = EOP;
            return;
        }
    }

    // Возвратить true, если с является разделителем.
    private boolean isDelim(char c) {
        return ((" \r,;<>+-/*%^=()".indexOf(c) != -1)) ? true : false;
    }

    // Возвратить true, если c является пробелом или символом табуляции.
    boolean isSpaceOrTab(char c) {
        return (c == ' ' || c =='\t') ? true : false;
    }

    // Возвратить true, если с является оператором отношения.
    boolean isRelop(char c) {
        return (relops.indexOf(c) != -1) ? true : false;
    }

    // Найти внутреннее представление лексемы в таблице лексем.
    private int lookUp(String s) {
        int i;
        // Проверить лексему в таблице
        for (i=0; i < kwTable.length; i++) {
            if (kwTable[i].getKeyword().equals(s.toLowerCase())) {
                return kwTable[i].getKeywordTok();
            }
        }
        return UNKNCOM; // Неизвестное ключевое слово.
    }
}