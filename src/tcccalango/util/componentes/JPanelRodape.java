/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

/**
 *
 * @author Gabriel
 */
public class JPanelRodape extends JPanel {
    private static final Image bgimg;
    
    static{
        Image img = null;
        try {
            img = ImageIO.read(JPanelRodape.class.getResourceAsStream("bg-rodape.png"));
        } catch (IOException ex) {
            Logger.getLogger(JPanelRodape.class.getName()).log(Level.SEVERE, null, ex);
        }
        bgimg = img;
    }

    public JPanelRodape() {
        setMinimumSize(new Dimension(20, 23));
        super.setBorder(new RodapeBorder());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bgimg, 0, 0, getWidth(), getHeight(), this);
        paintBorder(g);
        paintComponents(g);
    }

    @Override
    @Deprecated
    public void setBorder(Border border) {}
    
    

    @Override
    public RodapeBorder getBorder() {
        return (RodapeBorder) super.getBorder();
    }
    
    public class RodapeBorder extends AbstractBorder {
        private boolean borderOpaque;
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            if (isBorderOpaque()){
                g.setColor(Color.GRAY);
                g.drawLine(x, y, x+width, y);
            }
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 10, 0, 10);
        }

        @Override
        public boolean isBorderOpaque() {
            return borderOpaque;
        }

        public void setBorderOpaque(boolean borderOpaque) {
            this.borderOpaque = borderOpaque;
        }
        
    }
}

