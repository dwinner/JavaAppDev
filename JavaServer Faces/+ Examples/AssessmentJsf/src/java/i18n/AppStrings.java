package i18n;

import java.util.ResourceBundle;

/**
 * Application strings (for i18n) to avoid hard coding.
 *
 * @author Denis
 * @version demo 22-12-2012
 */
public final class AppStrings
{
   public static final class PdfStrings
   {
      public static final String PDF_TITLE = messages.getString("pdf_title");
      public static final String START_DATE_LABEL = messages.getString("date_from");
      public static final String END_DATE_LABEL = messages.getString("date_to");
      public static final String GENERATED_TIME_LABEL = messages.getString("generated_time");

      private PdfStrings()
      {
      }

   }

   private AppStrings()
   {
   }

   private static final ResourceBundle messages = ResourceBundle.getBundle("i18n/messages");
}