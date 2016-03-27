package apputils;

import org.junit.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test the date regular expression.
 *
 * @author Denis
 * @version demo 22-12-2012
 */
public class CheckDatesUtilityTest
{
   public CheckDatesUtilityTest()
   {
   }

   @BeforeClass
   public static void setUpClass()
   {
   }

   @AfterClass
   public static void tearDownClass()
   {
   }

   @Before
   public void setUp()
   {
   }

   @After
   public void tearDown()
   {
   }

   /**
    * Test of isDateString method, of class CheckDatesUtility.
    */
   @Test
   public void testIsDateString()
   {
      System.out.println("isDateString");
      String check1 = "2010-12-12 06:00:00"; // must true
      String check2 = "2010-12-12 06:00:00 2010-12-12 06:00:00";  // must false
      String check3 = "2010-12-12 06:00"; // must true
      String check4 = "2011-01-01    18:00:00"; // must true
      String check5 = "        2010-12-12 06:00       "; // must true
      String check6 = "2010-12- 06:00:00";   // must false
      String check7 = "        2010-12-12 06:00:       "; // must false
      assertTrue(CheckDatesUtility.isDateString(check1));
      assertFalse(CheckDatesUtility.isDateString(check2));
      assertTrue(CheckDatesUtility.isDateString(check3));
      assertTrue(CheckDatesUtility.isDateString(check4));
      assertTrue(CheckDatesUtility.isDateString(check5));
      assertFalse(CheckDatesUtility.isDateString(check6));
      assertFalse(CheckDatesUtility.isDateString(check7));
   }

}
