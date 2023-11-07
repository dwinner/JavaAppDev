public class User
{
	
	private String login = "Guest";
	private String password = "Kc";
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
}

public abstract class BaseBuilder
{
	
	protected User user = new User();
	
	public User getUser()
	{
		return user;
	}
	
	public abstract void buildLogin();
	public abstract void buildPassword();
}

public class XMLBuilder extends BaseBuilder
{
	
	public void buildLogin()
	{
		user.setLogin("Admin");
	}
	
	public void buildPassword()
	{
		user.setPassword("Qu");
	}
}

public class DBBuilder extends BaseBuilder
{
	
	public void buildLogin()
	{
		user.setLogin("Moderator");
	}
	
	public void buildPassword()
	{
		user.setPassword("Ku");
	}
}

public class MainClass
{
	
	private static User buildUser(BaseBuilder builder)
	{
		builder.buildLogin();
		builder.buildPassword();
		return builder.getUser();
	}
	
	public static void main(String args[])
	{
		User e1 = buildUser(new XMLBuilder());
		User e2 = buildUser(new DBBuilder());
		
		System.out.println(e1.getLogin());
		System.out.println(e1.getPassword());
		System.out.println(e2.getLogin());
		System.out.println(e2.getPassword());
	}
}
