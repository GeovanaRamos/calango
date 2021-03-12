package tcccalango.view.ajuda;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.w3c.dom.Node;

public class JAXBAjudaUtil {
   private static JAXBContext context;

   public static <T> T load(String path) {
      try {
         return (T) context.createUnmarshaller().unmarshal(JAXBAjudaUtil.class.getClassLoader().getResourceAsStream("ajuda/" + path));
      } catch (Exception var2) {
         System.out.println("ajuda/" + path);
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var2);
         return null;
      }
   }

   public static <T> T load(File file) {
      try {
         return (T) context.createUnmarshaller().unmarshal(file);
      } catch (JAXBException var2) {
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var2);
         return null;
      }
   }

   public static <T> T load(Node node) {
      try {
         return (T) context.createUnmarshaller().unmarshal(node);
      } catch (JAXBException var2) {
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var2);
         return null;
      }
   }

   public static void save(File file, Object o) {
      try {
         Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(o, file);
      } catch (JAXBException var3) {
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

   }

   public static void save(Node node, Object o) {
      try {
         context.createMarshaller().marshal(o, node);
      } catch (JAXBException var3) {
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

   }

   static {
      JAXBContext context = null;

      try {
         context = JAXBContext.newInstance(AjudaRoot.class, FuncionalidadeImpl.class, FuncaoEmbutida.class, IncludeFuncionalidade.class);
      } catch (JAXBException var2) {
         Logger.getLogger(JAXBAjudaUtil.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      JAXBAjudaUtil.context = context;
   }
}
