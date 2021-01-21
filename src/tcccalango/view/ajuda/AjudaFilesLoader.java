package tcccalango.view.ajuda;

import java.io.File;
import java.util.Collections;
import java.util.ResourceBundle;

public class AjudaFilesLoader {
   private static AjudaRoot root;
   private static boolean editable;
   private static ResourceBundle bundle = ResourceBundle.getBundle("tcccalango.view.ajuda.patterns");

   public static void save() {
      File[] arr$ = AjudaRoot.getRootDir().listFiles();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         File child = arr$[i$];
         if (child.isFile()) {
            child.delete();
         }
      }

      root.saveToFile();
   }

   public static AjudaRoot getRoot() {
      return root;
   }

   public static void load(File file) {
      root = (AjudaRoot)JAXBAjudaUtil.load(file);
      root.setFile(file);
      Collections.sort(root.getChilds());
      editable = true;
   }

   public static boolean isEditable() {
      return editable;
   }

   public static String getBundleProperty(String key) {
      return bundle.getString(key);
   }

   static {
      try {
         root = (AjudaRoot)JAXBAjudaUtil.load("root.xml");
         Collections.sort(root.getChilds());
      } catch (Exception var1) {
         root = new AjudaRoot();
      }

   }
}
