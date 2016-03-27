package app;

import abstractfactory.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] args)
   {
      System.out.println("Example for the AbstractFactory pattern");
      System.out.println();
      System.out.println(" (take a look in the app.RunPattern code. Notice that you can)");
      System.out.println(" use the abstractfactory.Address and abstractfactory.PhoneNumber classes when writing");
      System.out.println(" almost all of the code. This allows you to write a very");
      System.out.println(" generic framework, and plug in Concrete Factories");
      System.out.println(" and Products to specialize the behavior of your code)");
      System.out.println();

      System.out.println("Creating U.S. abstractfactory.Address and Phone Number");

      AddressFactory usAddressFactory = new USAddressFactory();
      Address usAddress = usAddressFactory.createAddress();
      PhoneNumber usPhone = usAddressFactory.createPhoneNumber();

      usAddress.setStreet("142 Lois Lane");
      usAddress.setCity("Metropolis");
      usAddress.setRegion("WY");
      usAddress.setPostalCode("54321");

      usPhone.setPhoneNumber("7039214722");

      System.out.println("U.S. address");
      System.out.println(usAddress.getFullAddress());
      System.out.println("U.S. phone number:");
      System.out.println(usPhone.getPhoneNumber());
      System.out.println();
      System.out.println();

      System.out.println("Creating French abstractfactory.Address and Phone Number");

      AddressFactory frenchAddressFactory = new FrenchAddressFactory();
      Address frenchAddress = frenchAddressFactory.createAddress();
      PhoneNumber frenchPhone = frenchAddressFactory.createPhoneNumber();

      frenchAddress.setStreet("21 Rue Victor Hugo");
      frenchAddress.setCity("Courbevoie");
      frenchAddress.setPostalCode("40792");
      frenchPhone.setPhoneNumber("011324290");

      System.out.println("French address:");
      System.out.println(frenchAddress.getFullAddress());
      System.out.println("French phone number:");
      System.out.println(frenchPhone.getPhoneNumber());
   }
}
