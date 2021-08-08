package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.AbstractBorder;

public class PassoAPassoViewer extends JScrollPane implements AdjustmentListener {
   private static final Image bgimg;
   private final List<Escopo> pilhaEscopos = new ArrayList();
   private final JPanel container = new JPanel(new AnimatedLayoutManager()) {
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         int width = this.getWidth();
         int height = this.getHeight();
         int iwidth = PassoAPassoViewer.bgimg.getWidth(this);
         int iheight = PassoAPassoViewer.bgimg.getHeight(this);

         for(int y = 0; y < height; y += iheight) {
            for(int x = 0; x < width; x += iwidth) {
               g.drawImage(PassoAPassoViewer.bgimg, x, y, Math.min(x + iwidth, width), Math.min(y + iheight, height), 0, 0, Math.min(x + iwidth, width) - x, Math.min(y + iheight, height) - y, this);
            }
         }

      }
   };
   private int lastMaximum = 0;

   public PassoAPassoViewer() {
      this.setViewportView(this.container);
      this.container.setBackground(new Color(229, 233, 238));
      this.getVerticalScrollBar().addAdjustmentListener(this);
      this.setBorder(new AbstractBorder() {
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            if (this.isBorderOpaque()) {
               g.setColor(Color.GRAY);
               g.drawLine(x, y, x + width, y);
               g.drawLine(x, y, x, y + height);
               g.drawLine(x, y + height - 1, x + width, y + height - 1);
            }

         }

         public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 1, 0);
         }

         public boolean isBorderOpaque() {
            return true;
         }
      });
   }

   public void adicionaEscopo(String nome) {
      this.push(new Escopo(nome, this.top()));
   }

   public void removeEscopoAtual() {
      if (this.top() == null) {
         throw new RuntimeException("Nenhum escopo para remover.");
      } else {
         this.pop();
      }
   }

   public void adicionaVariavel(String nome, TipoDado tipo, Object valor, String nomeVariavelReferencia) {
      if (this.top() == null) {
         throw new RuntimeException("Nenhum escopo para adicionar variável");
      } else {
         this.top().adicionaVariavel(nome, tipo, valor, nomeVariavelReferencia);
         this.top().revalidate();
         this.top().repaint();
      }
   }

   public void atualizaVariavel(String nome, Object valor) {
      if (this.top() == null) {
         throw new RuntimeException("Nenhum escopo para atualizar variável.");
      } else {
         this.top().atualizaVariavel(nome, valor);
         this.top().revalidate();
         this.top().repaint();
      }
   }

   private void push(Escopo escopo) {
      if (this.top() != null) {
         this.top().getBorder().setShadowOn(false);

         this.top().revalidate();
         this.top().repaint();
      }

      this.pilhaEscopos.add(escopo);
      this.container.add(escopo);
      this.top().getBorder().setShadowOn(true);

      this.revalidate();
      this.repaint();

   }

   public JPanel getContainer() {
      return this.container;
   }

   private Escopo pop() {
      Escopo escopo = this.top();
      if (escopo != null) {
         this.pilhaEscopos.remove(escopo);
         this.container.remove(escopo);
         if (this.top() != null) {
            this.top().setShadowOn(true);

            this.top().revalidate();
            this.top().repaint();
         }
      }

      this.revalidate();
      this.repaint();

      return escopo;
   }

   private Escopo top() {
      return this.pilhaEscopos.isEmpty() ? null : (Escopo)this.pilhaEscopos.get(this.pilhaEscopos.size() - 1);
   }

   public void limpaEscopos() {
      while(this.top() != null) {
         this.pop();
      }

   }

   public void adjustmentValueChanged(AdjustmentEvent e) {
      if (!e.getValueIsAdjusting()) {
         if (e.getValue() + e.getAdjustable().getVisibleAmount() >= this.lastMaximum) {
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
         }

         if (this.lastMaximum != e.getAdjustable().getMaximum()) {
            this.lastMaximum = e.getAdjustable().getMaximum();
         }
      }

   }

   static {
      BufferedImage img = null;

      try {
         img = ImageIO.read(HeaderEscopo.class.getResourceAsStream("/bg-escopos.png"));
      } catch (IOException var2) {
         Logger.getLogger(HeaderEscopo.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      bgimg = img;
   }
}
