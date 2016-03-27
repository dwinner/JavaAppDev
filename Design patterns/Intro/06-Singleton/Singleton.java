public class Singleton
{	
	private static Singleton instance = null;
	
	private SingletonTrust() {}
	
	public static Singleton getInstance()
	{
		if (instance == null)
		{
			instance = new Singleton();
		}
		return instance;
	}
}
