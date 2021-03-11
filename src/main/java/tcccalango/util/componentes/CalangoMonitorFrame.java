package tcccalango.util.componentes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class CalangoMonitorFrame extends JDialog implements IMonitorFrame {
   private static final BufferedImage img;
   private JPanel imgPanel;
   private JButton button;
   private ButtonObserver observer;
   private JComponent console;

   public void setConsole(JComponent console) {
      console.setBounds(35, 155, 620, 310);
      this.imgPanel.add("Center", this.console = console);
   }

   public boolean isConsole(JComponent console) {
      return console.equals(this.console);
   }

   public CalangoMonitorFrame(ButtonObserver observer, JComponent console) throws HeadlessException {
      this(observer);
      this.setConsole(console);
   }

   public CalangoMonitorFrame(ButtonObserver observer) throws HeadlessException {
      this.observer = observer;
      this.setSize(700, 600);
      this.setLocationRelativeTo((Component)null);
      this.setModal(true);
      this.setDefaultCloseOperation(0);
      this.setUndecorated(true);
      this.imgPanel = new JPanel() {
         public void paint(Graphics g) {
            g.drawImage(CalangoMonitorFrame.img, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);
            this.paintComponents(g);
         }
      };
      CustomMouseListener cml = new CustomMouseListener(this);
      this.imgPanel.setFocusable(true);
      this.imgPanel.addMouseListener(cml.mouseListener);
      this.imgPanel.addMouseMotionListener(cml.mouseMotionListener);
      this.button = new JButton("");
      this.button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            CalangoMonitorFrame.this.observer.clicked();
         }
      });
      this.button.setBounds(605, 479, 30, 22);
      this.add(this.button);
      this.imgPanel.setLayout((LayoutManager)null);
      this.add(this.imgPanel);
      this.setOpacity(0);
   }

   public void removeConsole() {
      if (this.console != null) {
         this.imgPanel.remove(this.console);
         this.console = null;
      }

   }

   public JButton getButton() {
      return this.button;
   }

   static {
      BufferedImage i = null;

      try {
         i = ImageIO.read(CalangoMonitorFrame.class.getResourceAsStream("/monitor.png"));
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      img = i;
   }

   public interface ButtonObserver {
      void clicked();
   }
}
