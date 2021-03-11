package tcccalango.util.componentes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class JPanelRodape extends JPanel {
   private static final Image bgimg;

   public JPanelRodape() {
      this.setMinimumSize(new Dimension(20, 23));
      super.setBorder(new RodapeBorder());
   }

   public void paint(Graphics g) {
      g.drawImage(bgimg, 0, 0, this.getWidth(), this.getHeight(), this);
      this.paintBorder(g);
      this.paintComponents(g);
   }

   /** @deprecated */
   @Deprecated
   public void setBorder(Border border) {
   }

   public RodapeBorder getBorder() {
      return (RodapeBorder)super.getBorder();
   }

   static {
      BufferedImage img = null;

      try {
         img = ImageIO.read(JPanelRodape.class.getResourceAsStream("bg-rodape.png"));
      } catch (IOException var2) {
         Logger.getLogger(JPanelRodape.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      bgimg = img;
   }

   public class RodapeBorder extends AbstractBorder {
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      }

      public Insets getBorderInsets(Component c) {
         return new Insets(0, 10, 0, 10);
      }

      public boolean isBorderOpaque() {
         return false;
      }
   }
}
