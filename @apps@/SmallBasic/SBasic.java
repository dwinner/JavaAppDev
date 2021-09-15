// ������������� ����� Small Basic.
import java.io.*;
import java.util.*;

// ����� ���������� ��� ������ ��������������.

// ����� �������������� ����� Small Basic.
class InterpreterException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errStr; // �������� ������.

    public InterpreterException(String str) {
		errStr = str;
    }

    @Override
    public String toString() {
		return errStr;
    }
}

// ����� �������������� ����� Small Basic.
class SBasic {

    public static final int PROG_SIZE = 10000; // ������������ ������ ���������.

    // ���� ������.
    public static final int NONE = 0;
    public static final int DELIMITER = 1;
    public static final int VARIABLE = 2;
    public static final int NUMBER = 3;
    public static final int COMMAND = 4;
    public static final int QUOTEDSTR = 5;

    // ���� ������.
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

    // ���������� ������������� �������� ���� Small Basic.
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

    // ������� ����� ���������.
    public static final String EOP = "\0";

    // ���� ��� ������� ����������, ����� ��� <=.
    public static final char LE = 1;
    public static final char GE = 2;
    public static final char NE = 3;

    // ������ ��� ����������.
    private double vars[];

    // � ���� ������ ����������� �������� ����� � �� ���������.
    private class Keyword {

        private String keyword; // ������.
        private int keywordTok; // ���������� �������������.

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

    /* ������� �������� ���� � �� ���������� ��������������
    ��� ����� ������ ��������� � ������ ��������. */
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

    private char[] prog; // ������ �� ������ � ����������.
    private int progIdx; // ������� ������ � ���������.

    private String token; // ��������� ������� �������.
    private int tokType;  // ��������� ��� �������.

    private int kwToken; // ���������� ������������� ��������� �����.

    // ��������� ����� FOR.
    private class ForInfo {
        int var; // �������.
        double target; // ��������
        int loc; // ������ � �������� ���� ��� �����.
    }

    // ���� ��� ������ FOR.
    private Stack fStack;

    // ���������� ������� ��������� �����.
    private class Label {

        public String name; // �����.
        public int loc; // ������ ��������� ����� � �������� �����.

        public Label(String name, int loc) {
            this.name = name;
            this.loc = loc;
        }
    }

    // ������������� �����.
    private TreeMap labelTable;

    // ���� ��� gosub.
    private Stack gStack;

    // ��������� ���������.
    private char rops[] = { GE, NE, LE, '<', '>', '=', 0 };

    /* ������� ������, ���������� ��������� ���������, ��� ����
    ����� ������� �� �������� ����� �������. */
    private String relops = new String(rops);

    // ����������� ��� Small Basic.
    public SBasic(String progName) throws InterpreterException {
        char tempbuf[] = new char[PROG_SIZE];
        int size;
        // ��������� ��������� ��� ����������.
        size = loadProgram(tempbuf, progName);
        if (size != -1) {
            // ������� ��������������� ������ ��� �������� ���������.
            prog = new char[size];
            // ���������� ��������� � ������.
            System.arraycopy(tempbuf, 0, prog, 0, size);
        }
    }

    // ��������� ���������.
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
        // ���� ���� ������������ �������� EOF, ������� �������.
        if (p[size-1] == (char) 26) {
            size--;
		}
        return size; // ���������� ������ ���������.
    }

    // ��������� ���������.
    public void run() throws InterpreterException {
        // ���������������� ��� ������� ����� ���������.
        vars = new double[26];
        fStack = new Stack();
        labelTable = new TreeMap();
        gStack = new Stack();
        progIdx = 0;
        scanLabels(); // ����� ����� � ���������.
        sbInterp(); // ���������.
    }

    // ����� ����� ��� ������������� Small Basic.
    private void sbInterp() throws InterpreterException {
        // �������� ���� ��������������.
        do {
            getToken();
            // �������� �� ������� ��������� ������������.
            if (tokType == VARIABLE) {
                putBack(); // ���������� ���������� �� ������� �����.
                assignment(); // ���������� �������� ������������.
            }
            else { // �������� �����
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

    // ����� ��� �����.
    @SuppressWarnings({"unchecked", "unchecked", "unchecked"})
    private void scanLabels() throws InterpreterException {
        int i;
        Object result;
        // �����������, �������� �� ������ ������� � ����� ������.
        getToken();
        if (tokType == NUMBER) {
            labelTable.put(token, new Integer(progIdx));
        }
        findEOL();
        do {
            getToken();
            if (tokType == NUMBER) { // ������ ���� ����� ������.
                result = labelTable.put(token, new Integer(progIdx));
                if (result != null) {
                    handleErr(DUPLABEL);
                }
            }
            // ����� ��������� ������.
            if (kwToken != EOL) {
                findEOL();
            }
        }
        while (!token.equals(EOP));
        progIdx = 0; // �������������� ������ � ������ ���������.
    }

    // ����� ������ ��������� ������.
    private void findEOL() {
        while (progIdx < prog.length && prog[progIdx] != '\n') {
            ++progIdx;
        }
        if (progIdx < prog.length) {
            progIdx++;
        }
    }

    // ��������� ���������� ��������.
    private void assignment() throws InterpreterException {
        int var;
        double value;
        char vname;
        // �������� ��� ����������.
        getToken();
        vname = token.charAt(0);
        if (!Character.isLetter(vname)) {
            handleErr(NOTVAR);
            return;
        }
        // ������������� ������ �� ������� ����������.
        var = (int) Character.toUpperCase(vname) - 'A';
        // �������� ���� ���������.
        getToken();
        if (!token.equals("=")) {
            handleErr(EQUALEXPECTED);
            return;
        }
        // �������� �������� ��� ������������.
        value = evaluate();
        // ��������� ��������.
        vars[var] = value;
    }

    // ��������� ������� ������ ����������� PRINT.
    private void print() throws InterpreterException {
        double result;
        int len = 0, spaces;
        String lastDelim = "";
        do {
            getToken(); // �������� ��������� ������� ������.
            if (kwToken == EOL || token.equals(EOP)) {
                break;
            }
            if (tokType == QUOTEDSTR) { // ������.
                System.out.print(token);
                len += token.length();
                getToken();
            }
            else { // ���������.
                putBack();
                result = evaluate();
                getToken();
                System.out.print(result);
                // �������� ����� ������ ��� ������� ����������.
                Double t = new Double(result);
                len += t.toString().length(); // ��������� �����.
            }
            lastDelim = token;
            // ���� �������, ������������� � ��������� �����.
            if (lastDelim.equals(",")) {
                // ���������� ����� �������� ��� ���������.
                spaces = 8 - (len % 8);
                len += spaces; // �������� ������� ���������.
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

    // ��������� ����������� GOTO.
    private void execGoto() throws InterpreterException {
        Integer loc;
        getToken(); // �������� �����.
        // ����� ��������� �����.
        loc = (Integer) labelTable.get(token);
        if (loc == null) {
            handleErr(UNDEFLABEL);
        }
        else { // ����� �� ����������.
            progIdx = loc.intValue();
        }
    }

    // ��������� ����������� if.
    private void execIf() throws InterpreterException {
        double result;
        result = evaluate(); // �������� �������� ���������.
        /* ���� ��������� true (��-����), ���������� if. � ��������� ������
        ������� � ��������� ������ ���������. */
        if (result != 0.0) {
            getToken();
            if (kwToken != THEN) {
                handleErr(THENEXPECTED);
                return;
            } // ����� ����������� ����� ���������.
        }
        else { // ����� ������ ��������� ������.
            findEOL();
        }
    }

    // ��������� ����������� for.
    @SuppressWarnings("unchecked")
    private void execFor() throws InterpreterException {
        ForInfo stckvar = new ForInfo();
        double value;
        char vname;
        getToken(); // ������� ����������� ����������.
        vname = token.charAt(0);
        if (!Character.isLetter(vname)) {
            handleErr(NOTVAR);
            return;
        }
        // ��������� ������ ����������� ����������.
        stckvar.var = Character.toUpperCase(vname) - 'A';
        getToken(); // ������� ������ ���������.
        if (token.charAt(0) != '=') {
            handleErr(EQUALEXPECTED);
            return;
        }
        value = evaluate(); // ����������������.
        vars[stckvar.var] = value;
        getToken(); // ������� � ��������� TO
        if (kwToken != TO) {
            handleErr(TOEXPECTED);
        }
        stckvar.target = evaluate(); // �������� ��������.
        /* ���� ���� ����� ����������� �� ������� ���� ���� ���,
        �� ��������� � ����. */
        if (value >= vars[stckvar.var]) {
            stckvar.loc = progIdx;
            fStack.push(stckvar);
        }
        else { // � ��������� ������ ���������� ���� ���������.
            while (kwToken != NEXT) {
                getToken();
            }
        }
    }

    // ��������� ����������� NEXT.
    @SuppressWarnings({"unchecked", "unchecked"})
    private void next() throws InterpreterException {
        ForInfo stckvar;
        try {
            // ������� ���������� ��� ����� for.
            stckvar = (ForInfo) fStack.pop();
            vars[stckvar.var]++; // ���������������� ����������� ����������.
            // ���� �������, ���������.
            if (vars[stckvar.var] > stckvar.target) {
                return;
            }
            // � ��������� ������, ������� ����������
            fStack.push(stckvar);
            progIdx = stckvar.loc;  // ����.
        }
        catch (EmptyStackException exc) {
            handleErr(NEXTWITHOUTFOR);
        }
    }

    // ��������� ������� ����� INPUT.
    private void input() throws InterpreterException {
        int var;
        double val = 0.0;
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getToken(); // ���������� �� ����������� �����������.
        if (tokType == QUOTEDSTR) {
            // ���� ���, ���������� � ��������� �������.
            System.out.print(token);
            getToken();
            if (!token.equals(",")) {
                handleErr(SYNTAX);
            }
            getToken();
        }
        else { // ����� ������� ����������� � "?".
            System.out.print("? ");
        }
        // �������� ������� ����������.
        var =  Character.toUpperCase(token.charAt(0)) - 'A';
        try {
            str = br.readLine();
            val = Double.parseDouble(str); // ������� ��������.
        }
        catch (IOException exc) {
            handleErr(INPUTIOERROR);
        }
        catch (NumberFormatException exc) {
            /* ��������, �� �������� ���������� ��� ������
            �� �������, ��� ������ ������ ��������������. */
            System.out.println("Invalid input.");
        }
        vars[var] = val; // ���������.
    }

    // �������� ����������� GOSUB.
    @SuppressWarnings("unchecked")
    private void gosub() throws InterpreterException {
        Integer loc;
        getToken();
        // ����� ����� ��� ������
        loc = (Integer) labelTable.get(token);
        if (loc == null) { // ����� �� ����������.
            handleErr(UNDEFLABEL);
        }
        else {
            // ��������� ����� ��� ��������.
            gStack.push(new Integer(progIdx));
            // ������ ��������� � ����� �����.
            progIdx = loc.intValue();
        }
    }

    // ������� �� GOSUB.
    private void greturn() throws InterpreterException {
        Integer t;
        try {
            // ������������ ������ ���������.
            t = (Integer) gStack.pop();
            progIdx = t.intValue();
        }
        catch (EmptyStackException exc) {
            handleErr(RETURNWITHOUTGOSUB);
        }
    }

    // **************** �������������� ���������� ****************
    // ������� ����� �����������.
    private double evaluate() throws InterpreterException {
        double result = 0.0;
        getToken();
        if (token.equals(EOP)) { // ��������� ���.
            handleErr(NOEXP);
        }
        // ���������������� � ���������� ���������.
        result = evalExp1();
        putBack();
        return result;
    }

    // ���������� ��������� ���������.
    private double evalExp1() throws InterpreterException {
        double l_temp, r_temp, result;
        char op;
        result = evalExp2();
        // ���� ����� ���������, �� �������.
        if (token.equals(EOP)) {
            return result;
        }
        op = token.charAt(0);
        if (isRelop(op)) {
            l_temp = result;
            getToken();
            r_temp = evalExp1();
            switch (op) { // ��������� �������� ���������.
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

    // ������� ��� ������� ��� �����.
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

    // ����������� ��� ��������� ��� �������.
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

    // �������� ���������� � �������.
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

    // ������� ������� + ��� -.
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

    // ���������� ��������� � ������� �������.
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

    // �������� �������� ����� ��� ����������.
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

    // ���������� �������� ����������.
    private double findVar(String vname) throws InterpreterException {
        if (!Character.isLetter(vname.charAt(0))) {
            handleErr(SYNTAX);
            return 0.0;	// ?!
        }
        return vars[Character.toUpperCase(vname.charAt(0))-'A'];
    }

    // ���������� ������� �� ������� �����.
    private void putBack() {
        if (token == null ? EOP == null : token.equals(EOP)) {
            return;
        }
        for (int i=0; i < token.length(); i++) {
            progIdx--;
        }
    }

    // ���������� ������.
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

    // �������� ��������� �������.
    private void getToken() throws InterpreterException {
        char ch;
        tokType = NONE;
        token = "";
        kwToken = UNKNCOM;
        // ��������� ����� ���������.
        if (progIdx == prog.length) {
            token = EOP;
            return;
        }
        // ���������� �������.
        while (progIdx < prog.length && isSpaceOrTab(prog[progIdx])) {
            progIdx++;
        }
        // �������������� ������� ���������.
        if (progIdx == prog.length) {
            token = EOP;
            tokType = DELIMITER;
            return;
        }
        if (prog[progIdx] == '\r') { // ���������� �������
            progIdx += 2;
            kwToken = EOL;
            token = "\r\n";
            return;
        }
        // ��������� ���������� ���������.
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
            // ��������
            token += prog[progIdx];
            progIdx++;
            tokType = DELIMITER;
        }
        else if (Character.isLetter(prog[progIdx])) {
            // ���������� ��� �������� �����.
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
            // �����
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
            // ������ � ��������.
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
        else { // ����������� ������, �������� ����������.
            token = EOP;
            return;
        }
    }

    // ���������� true, ���� � �������� ������������.
    private boolean isDelim(char c) {
        return ((" \r,;<>+-/*%^=()".indexOf(c) != -1)) ? true : false;
    }

    // ���������� true, ���� c �������� �������� ��� �������� ���������.
    boolean isSpaceOrTab(char c) {
        return (c == ' ' || c =='\t') ? true : false;
    }

    // ���������� true, ���� � �������� ���������� ���������.
    boolean isRelop(char c) {
        return (relops.indexOf(c) != -1) ? true : false;
    }

    // ����� ���������� ������������� ������� � ������� ������.
    private int lookUp(String s) {
        int i;
        // ��������� ������� � �������
        for (i=0; i < kwTable.length; i++) {
            if (kwTable[i].getKeyword().equals(s.toLowerCase())) {
                return kwTable[i].getKeywordTok();
            }
        }
        return UNKNCOM; // ����������� �������� �����.
    }
}