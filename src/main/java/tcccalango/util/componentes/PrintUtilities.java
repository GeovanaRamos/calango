package tcccalango.util.componentes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.RepaintManager;

public class PrintUtilities implements Printable {
   private Component componentToBePrinted;

   public static void printComponent(Component c) {
      (new PrintUtilities(c)).print();
   }

   public PrintUtilities(Component componentToBePrinted) {
      this.componentToBePrinted = componentToBePrinted;
   }

   public void print() {
      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setPrintable(this);
      if (printJob.printDialog()) {
         try {
            printJob.print();
         } catch (PrinterException var3) {
            System.out.println("Error printing: " + var3);
         }
      }

   }

   public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
      if (pageIndex > 0) {
         return 1;
      } else {
         Graphics2D g2d = (Graphics2D)g;
         g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
         disableDoubleBuffering(this.componentToBePrinted);
         this.componentToBePrinted.paint(g2d);
         enableDoubleBuffering(this.componentToBePrinted);
         return 0;
      }
   }

   public static void disableDoubleBuffering(Component c) {
      RepaintManager currentManager = RepaintManager.currentManager(c);
      currentManager.setDoubleBufferingEnabled(false);
   }

   public static void enableDoubleBuffering(Component c) {
      RepaintManager currentManager = RepaintManager.currentManager(c);
      currentManager.setDoubleBufferingEnabled(true);
   }
}
