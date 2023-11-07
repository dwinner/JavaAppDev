import interpretertest.*;

public class InterpreterTest
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Interpreter pattern");
      System.out.println("In this demonstration, the syntax defined");
      System.out.println(" by the Interpreter can be used to search");
      System.out.println(" among a collection of Contacts, returning");
      System.out.println(" the subset that match the given search criteria.");

      ContactList candidates = makeContactList();
      Context ctx = new Context();

      System.out.println("Contents of the ContactList:");
      System.out.println(candidates);
      System.out.println();

      Contact testContact = new ContactImpl();
      VariableExpression varLName = new VariableExpression(testContact, "getLastName");
      ConstantExpression constLName = new ConstantExpression("u");
      ContainsExpression eqLName = new ContainsExpression(varLName, constLName);

      System.out.println("Contents of the search on ContactList:");
      System.out.println("(search was contains 'u' in Lase Name)");
      Object result = candidates.getContactsMatchingExpression(eqLName, ctx, testContact);
      System.out.println(result);

      VariableExpression varTitle = new VariableExpression(testContact, "getTitle");
      ConstantExpression constTitle = new ConstantExpression("LT");
      EqualsExpression eqTitle = new EqualsExpression(varTitle, constTitle);

      System.out.println("Contents of the search on ContactList:");
      System.out.println("(search was all LT personnel)");
      result = candidates.getContactsMatchingExpression(eqTitle, ctx, testContact);
      System.out.println(result);
      System.out.println();

      VariableExpression varLastName = new VariableExpression(testContact, "getLastName");
      ConstantExpression constLastName = new ConstantExpression("S");
      ContainsExpression cLName = new ContainsExpression(varLastName, constLastName);

      AndExpression andExpr = new AndExpression(eqTitle, cLName);

      System.out.println("Contents of the search on ContactList:");
      System.out.println("(search was all LT personnel with 'S' in Last Name)");
      result = candidates.getContactsMatchingExpression(andExpr, ctx, testContact);
      System.out.println(result);
   }

   private static ContactList makeContactList()
   {
      ContactList returnList = new ContactList();
      returnList.addContact(new ContactImpl("James", "Kirk", "Captain", "USS Enterprise"));
      returnList.addContact(new ContactImpl("Mr.", "Spock", "Science Officer", "USS Enterprise"));
      returnList.addContact(new ContactImpl("LT", "Uhura", "LT", "USS Enterprise"));
      returnList.addContact(new ContactImpl("LT", "Sulu", "LT", "USS Enterprise"));
      returnList.addContact(new ContactImpl("Ensign", "Checkov", "Ensign", "USS Enterprise"));
      returnList.addContact(new ContactImpl("Dr.", "McCoy", "Ship's Doctor", "USS Enterprise"));
      returnList.addContact(new ContactImpl("Montgomery", "Scott", "LT", "USS Enterprise"));
      return returnList;
   }
}
