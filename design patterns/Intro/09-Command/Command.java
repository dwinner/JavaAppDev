// Описание команды и конкретные реализации 
abstract class Command()
{
	public abstract void execute();
}

class MultCommand extends Command
{	
	private ArrayOperation ao;
	
	public MultCommand(ArrayOperation ao)
	{
		this.ao = ao;
	}
	
	public void execute()
	{
		this.ao.product();
	}
}

class AddCommand extends Command
{	
	private ArrayOperation ao;
	
	public AddCommand(ArrayOperation ao)
	{
		this.ao = ao;
	}
	
	public void execute()
	{
		this.ao.sum();
	}
}

// класс Receiver (получатель) - располагает информацией о способах выполнения операций
class ArrayOperation
{	
	private int[] mass;
	
	public ArrayOperation(int[] mass)
	{
		this.mass = mass;
	}
	
	public void sum()
	{
		int sum = 0;
		for (int i : mass)
			sum += i;
		System.out.println(sum);
	}
	
	public void product()
	{
		int mult = 1;
		for (int i : mass)
			mult *= i;
		System.out.println(mult);
	}
}

// Класс Invoker (инициатор)-обращается к команде для выполнения запроса
class ManagerCommands
{	
	private Command command;
	
	public ManagerCommands(Command command)
	{
		this.command = command;
	}
	
	public void setManager(Command command)
	{
		this.command = command;
	}
	
	public void exec()
	{
		command.execute();
	}
}

public class MainClass
{
	public static void main(String[] args)
	{
		int mass[] = {-1, 71, 45, -20, 48, 60, 19};
		// Класс получатель (Receiver)-располагает информацией о способах выполнения операций
		ArrayOperation receiver = new ArrayOperation(mass);
		// Инициализация команды
		Command operation1 = new MultCommand(receiver);
		Command operation2 = new AddCommand(receiver);
		// Класс инициатор (Invoker)-обращается к команде для выполнения запроса
		ManagerCommands manager = new ManagerCommands(operation1);
		manager.exec();
		manager.setManager(operation2);
		manager.exec();
	}
}
