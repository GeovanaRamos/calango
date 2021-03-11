package tcccalango.util.componentes;

import javax.swing.JButton;
import javax.swing.JComponent;

public interface IMonitorFrame {
   JButton getButton();

   void setConsole(JComponent var1);

   void setVisible(boolean var1);

   boolean requestFocusInWindow();

   void removeConsole();

   boolean isConsole(JComponent var1);

   void setModal(boolean var1);
}
