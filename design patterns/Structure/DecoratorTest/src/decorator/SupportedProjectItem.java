package decorator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public class SupportedProjectItem extends ProjectDecorator
{
   public SupportedProjectItem() { }

   public SupportedProjectItem(File newSupportingDocument)
   {
      addSupportingDocument(newSupportingDocument);
   }

   private void addSupportingDocument(File document)
   {
      if (!supportingDocuments.contains(document))
         supportingDocuments.add(document);
   }

   public void removeSupportingDocument(File document)
   {
      supportingDocuments.remove(document);
   }

   public List<File> getSupportingDocuments()
   {
      return supportingDocuments;
   }

   @Override
   public String toString()
   {
      return getProjectItem().toString() + EOL_STRING + "\tSupporting Documents: " + supportingDocuments;
   }

   private List<File> supportingDocuments = new ArrayList<>();
}
