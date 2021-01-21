package tcccalango;

import java.awt.Window;
import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import tcccalango.view.TCCCalangoView;

public class TCCCalangoApp extends SingleFrameApplication {
   private TCCCalangoView view;

   protected void startup() {
      this.show(this.view);
   }

   protected void initialize(String[] args) {
      this.view = new TCCCalangoView(this);
      if (args.length > 0 && args[0].endsWith(".clg")) {
         this.view.carregaArquivo(new File(args[0]));
      } else {
         this.view.novoArquivo();
      }

   }

   protected void configureWindow(Window root) {
   }

   public static TCCCalangoApp getApplication() {
      return (TCCCalangoApp)Application.getInstance(TCCCalangoApp.class);
   }

   public static void main(String[] args) {
      try {
         System.setProperty("apple.laf.useScreenMenuBar", "true");
         System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Calango");
         if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
         } else {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
      } catch (ClassNotFoundException var2) {
         System.out.println("ClassNotFoundException: " + var2.getMessage());
      } catch (InstantiationException var3) {
         System.out.println("InstantiationException: " + var3.getMessage());
      } catch (IllegalAccessException var4) {
         System.out.println("IllegalAccessException: " + var4.getMessage());
      } catch (UnsupportedLookAndFeelException var5) {
         System.out.println("UnsupportedLookAndFeelException: " + var5.getMessage());
      }

      launch(TCCCalangoApp.class, args);
   }
}
