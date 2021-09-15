// Демонстрация интерпретатора Small Basic.

class SBDemo {
	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: sbasic <filename>");
			return;
		}
		try {
			SBasic ob = new SBasic(args[0]);
			ob.run();
		} 
		catch (InterpreterException exc) {
			System.out.println(exc);
		}
	}
}