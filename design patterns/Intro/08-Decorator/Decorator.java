// Определение интерфейса для компонента
public abstract class Driver
{
	public abstract void do();
}

// Интерфейс-декоратор для класса Driver
public abstract class DriverDecorator extends Driver
{	
	protected Driver driver;
	
	public DriverDecorator(Driver driver)
	{
		this.driver = driver;
	}
	
	public void do()
	{
		driver.do();
	}
}

// Класс просто водителя
public class CarDriver extends Driver
{
	public void do()
	{	// базовая операция
		System.out.println("I am a driver");
	}
}

// Класс водителя автобуса
public class BusDriver extends DriverDecorator
{	
	public BusDriver(Driver driver)
	{
		super(driver);
	}
	
	private void addedBehaviorOne()
	{
		System.out.println("I am bus driver");
	}
	
	public void do()
	{
		driver.do();	// super.do()
		addedBehaviorOne();
	}
}

public class CarDriverAndForwardingAgent extends DriverDecorator
{	
	public CarDriverAndForwardingAgent(Driver driver)
	{
		super(driver);
	}
	
	private void addedBehaviorTwo()
	{
		System.out.println("I am a Forwarding Agent");
	}
	
	public void do()
	{
		driver.do();	// super.do()
		addedBehaviorTwo();
	}
}

public class MainClass
{
	public static void main(String args[])
	{
		Driver carDriver = new CarDriver();
		MainClass runner = new MainClass();
		runner.doDrive(carDriver);
		runner.doDrive(new BusDriver(carDriver));
		runner.doDrive(new CarDriverAndForwardingAgent(carDriver));
	}
	
	public void doDrive(Driver driver)
	{
		driver.do();
	}
}
