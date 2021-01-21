package tcccalango.util.componentes;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class SimpleMonitorFrame extends JDialog implements IMonitorFrame {
   private CloseListener listener;
   private JComponent console;

   public SimpleMonitorFrame(CloseListener listener) {
      this.setTitle("Console");
      this.setSize(600, 400);
      this.setLocationRelativeTo((Component)null);
      this.listener = listener;
      this.setDefaultCloseOperation(0);
      this.addWindowListener(new WindowListener());
      this.setLayout(new GridLayout(1, 1));
      ((JComponent)this.getContentPane()).getInputMap(2).put(KeyStroke.getKeyStroke(27, 0), "esc");
      ((JComponent)this.getContentPane()).getActionMap().put("esc", new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
            SimpleMonitorFrame.this.listener.esc();
         }
      });
   }

   public JButton getButton() {
      return new JButton();
   }

   public void setConsole(JComponent console) {
      this.add(this.console = console);
   }

   public boolean isConsole(JComponent console) {
      return console.equals(this.console);
   }

   public void removeConsole() {
      if (this.console != null) {
         this.remove(this.console);
         this.console = null;
      }

   }

   class WindowListener extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
         SimpleMonitorFrame.this.listener.close();
      }
   }

   public interface CloseListener {
      void esc();

      void close();
   }
}
