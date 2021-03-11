package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class Escopo extends JPanel implements Parent {
   private final Escopo parent;
   private final JPanel container;
   private final Map<String, Variavel> variaveis = new HashMap();

   public Escopo(String nome, Escopo parent) {
      super(new BorderLayout());
      this.setName(nome);
      this.parent = parent;
      this.setOpaque(false);
      this.container = new JPanel(new AnimatedLayoutManager());
      this.setBackground(new Color(229, 233, 238));
      JPanel header = DefaultVariavel.createHeader();
      HeaderEscopo title = new HeaderEscopo(nome);
      title.setPreferredSize(new Dimension(25, 30));
      JPanel top = new JPanel(new GridLayout(2, 1));
      top.add(title);
      top.add(header);
      this.add("North", top);
      this.add("Center", this.container);
      super.setBorder(new ShadowBorder(20));
   }

   void adicionaVariavel(String nome, TipoDado tipo, Object valor, String nomeVariavelReferencia) {
      Variavel referencia = null;
      if (nomeVariavelReferencia != null && !nomeVariavelReferencia.isEmpty()) {
         if (this.parent == null) {
            throw new RuntimeException("Variável " + nome + " não pode ser de referência. Não há escopo pai para o escopo atual.");
         }

         if (nomeVariavelReferencia.endsWith("]")) {
            referencia = (Variavel)this.parent.variaveis.get(nomeVariavelReferencia.replaceAll("\\[[0-9]+\\]", ""));
            String[] index = nomeVariavelReferencia.split("\\[|\\]");
            List<Integer> vs = new ArrayList();
            String[] arr$ = index;
            int len$ = index.length;

            for(int i$ = 0; i$ < len$; ++i$) {
               String v = arr$[i$];
               if (v.matches("[0-9]+")) {
                  vs.add(Integer.parseInt(v));
               }
            }

            if (vs.size() == 2) {
               referencia = ((MatrizVariavel)referencia).getVariavel((Integer)vs.get(0));
               vs.remove(0);
            }

            if (vs.size() == 1) {
               referencia = ((ArrayVariavel)referencia).getVariavel((Integer)vs.get(0));
            }
         } else {
            referencia = (Variavel)this.parent.variaveis.get(nomeVariavelReferencia);
         }
      }

      Variavel variavel = VariavelFactory.create(this, nome, tipo, valor, referencia);
      if (this.container.getComponentCount() % 2 == 0) {
         variavel.setBackground(new Color(230, 230, 230));
      } else {
         variavel.setBackground(Color.WHITE);
      }

      this.variaveis.put(nome, variavel);
      this.container.add((JComponent)variavel);
   }

   void atualizaVariavel(String nome, Object valor) {
      if (!this.variaveis.containsKey(nome)) {
         throw new RuntimeException("Nenhuma variável com nome " + nome);
      } else {
         ((Variavel)this.variaveis.get(nome)).setValor(valor);
      }
   }

   public String getNome() {
      return this.getName();
   }

   public ShadowBorder getBorder() {
      return (ShadowBorder)super.getBorder();
   }

   public void setShadowOn(boolean last) {
      this.getBorder().setShadowOn(last);
      super.setBorder(this.getBorder());
      this.updateUI();
   }

   /** @deprecated */
   @Deprecated
   public void setBorder(Border border) {
   }

   public void hover() {
   }

   public void blur() {
   }

   public Escopo getEscopo() {
      return this;
   }

   public class ShadowBorder extends AbstractBorder {
      private boolean shadowOn;
      private int size;

      public ShadowBorder(int size) {
         this.size = size;
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         if (this.shadowOn) {
            Color color = new Color(Integer.getInteger("a3b6c2", 16));

            for(int i = 0; i < this.size; ++i) {
               float count = (float)(Math.pow((double)i, 4.0D) / Math.pow((double)this.size * 1.2D, 4.0D));
               g.setColor(new Color((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, count));
               g.drawLine(x, y + height - i, x + width, y + height - i);
            }

            g.setColor(Color.GRAY);
            g.drawLine(x, y + height - this.size, x + width, y + height - this.size);
         } else {
            g.setColor(Color.GRAY);
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
         }

      }

      public Insets getBorderInsets(Component c) {
         return this.shadowOn ? new Insets(0, 0, this.size, 0) : new Insets(0, 0, 1, 0);
      }

      public boolean isBorderOpaque() {
         return true;
      }

      public void setShadowOn(boolean borderOpaque) {
         this.shadowOn = borderOpaque;
      }
   }
}
