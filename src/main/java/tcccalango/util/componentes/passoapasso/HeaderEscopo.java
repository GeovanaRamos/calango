package tcccalango.util.componentes.passoapasso;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class HeaderEscopo extends JPanel {
   private static final Image bgimg;

   public HeaderEscopo(String title) {
      this.setPreferredSize(new Dimension(20, 23));
      super.setBorder(new HeaderBorder());
      JLabel ltitle;
      this.add(ltitle = new JLabel(title));
      ltitle.setFont(ltitle.getFont().deriveFont(1));
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

   public HeaderBorder getBorder() {
      return (HeaderBorder)super.getBorder();
   }

   static {
      BufferedImage img = null;

      try {
         img = ImageIO.read(HeaderEscopo.class.getResourceAsStream("bg-header-escopo.png"));
      } catch (IOException var2) {
         Logger.getLogger(HeaderEscopo.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      bgimg = img;
   }

   public class HeaderBorder extends AbstractBorder {
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         if (this.isBorderOpaque()) {
            g.setColor(Color.GRAY);
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
         }

      }

      public Insets getBorderInsets(Component c) {
         return new Insets(0, 0, 1, 0);
      }

      public boolean isBorderOpaque() {
         return true;
      }
   }
}
