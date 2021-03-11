package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DefaultVariavel extends JPanel implements Variavel {
   private static final int DEFAULT_HEIGHT = 30;
   private static final Color DEFAULT_HOVER_COLOR = new Color(200, 255, 200);
   private static final Image REFERENCE_IMAGE;
   private static final Map<TipoDado, Image> icons = new HashMap();
   protected static final int DEFAULT_WIDTH = 80;
   private Parent parent;
   private Variavel referencia;
   private JLabel valor;
   private TipoDado tipo;
   private Color defaultBackground;
   protected MouseListener listener;
   protected JLabel lnome;
   protected JLabel ltipo;

   public static JPanel createHeader() {
      JPanel header = new JPanel(new ColumnsLayoutManager());
      header.setMinimumSize(new Dimension(50, 30));
      header.setBackground(Color.WHITE);
      JLabel hnome = new JLabel("Nome");
      hnome.setFont(hnome.getFont().deriveFont(1));
      header.add("35", hnome);
      JLabel htipo = new JLabel("Tipo");
      htipo.setHorizontalAlignment(0);
      htipo.setFont(htipo.getFont().deriveFont(1));
      header.add("20", htipo);
      JLabel hvalor = new JLabel("Valor");
      hvalor.setFont(hvalor.getFont().deriveFont(1));
      header.add("35", hvalor);
      JLabel hreferencia = new JLabel("ref");
      hreferencia.setHorizontalAlignment(0);
      hreferencia.setFont(hreferencia.getFont().deriveFont(1));
      header.add("10", hreferencia);
      return header;
   }

   public DefaultVariavel() {
      this.setBorder(new Border() {
         public Insets getBorderInsets(Component c) {
            return new Insets(0, 10, 0, 10);
         }

         public void paintBorder(Component cmpnt, Graphics grphcs, int i, int i1, int i2, int i3) {
         }

         public boolean isBorderOpaque() {
            return true;
         }
      });
      this.listener = new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            if (DefaultVariavel.this.referencia != null) {
               DefaultVariavel.this.hover();
            }

         }

         public void mouseExited(MouseEvent e) {
            DefaultVariavel.this.blur();
         }
      };
   }

   public void prepare(Parent parent, String nome, TipoDado tipo, Object valor, Variavel referencia) {
      this.setLayout(new ColumnsLayoutManager());
      this.setName(nome);
      this.lnome = new JLabel(nome);
      this.prepare(this.lnome, "35");
      this.tipo = tipo;
      this.ltipo = new JLabel(new ImageIcon((Image)icons.get(tipo)));
      this.ltipo.setText(" ");
      this.ltipo.setHorizontalAlignment(0);
      this.prepare(this.ltipo, "20");
      this.valor = new JLabel();
      this.setValor(valor);
      this.prepare(this.valor, "35");
      JLabel lreferencia = new JLabel();
      lreferencia.setHorizontalAlignment(0);
      if (referencia != null) {
         lreferencia.setIcon(new ImageIcon(REFERENCE_IMAGE));
         lreferencia.setText(" ");
         this.referencia = referencia;
      } else {
         this.referencia = null;
      }

      this.prepare(lreferencia, "10");
      this.parent = parent;
      this.defaultBackground = this.getBackground();
      this.ltipo.setToolTipText(tipo.toString());
      if (referencia != null) {
         lreferencia.setToolTipText(referencia.getDebugParent().getEscopo().getNome() + " : " + referencia.getNome());
      }

   }

   public void hover() {
      super.setBackground(DEFAULT_HOVER_COLOR);
      if (this.referencia != null) {
         this.referencia.hover();
      }

      this.getDebugParent().hover();
   }

   public void blur() {
      super.setBackground(this.defaultBackground);
      if (this.referencia != null) {
         this.referencia.blur();
      }

      this.getDebugParent().blur();
   }

   public void setBackground(Color bg) {
      super.setBackground(this.defaultBackground = bg);
   }

   private void prepare(JLabel label, String percent) {
      label.setMinimumSize(new Dimension(80, 30));
      label.setToolTipText(label.getText());
      label.addMouseListener(this.listener);
      label.setFont(new Font("SansSerif", 0, 13));
      this.add(percent, label);
   }

   public Parent getDebugParent() {
      return this.parent;
   }

   public String getNome() {
      return this.getName();
   }

   public void setValor(Object valor) {
      String svalor = String.valueOf(valor != null ? valor : "");
      this.valor.setText(svalor);
      this.valor.setToolTipText(svalor);
      if (this.referencia != null) {
         this.referencia.setValor(valor);
      }

      this.updateUI();
   }

   public JLabel getValor() {
      return this.valor;
   }

   public Variavel getReferencia() {
      return this.referencia;
   }

   public void setListener(MouseListener listener) {
      this.listener = listener;
   }

   static {
      BufferedImage refImg = null;

      try {
         refImg = ImageIO.read(DefaultVariavel.class.getResource("icons/accept.png"));
         icons.put(TipoDado.CARACTER, ImageIO.read(DefaultVariavel.class.getResource("icons/caracter-icon.png")));
         icons.put(TipoDado.INTEIRO, ImageIO.read(DefaultVariavel.class.getResource("icons/inteiro-icon.png")));
         icons.put(TipoDado.LOGICO, ImageIO.read(DefaultVariavel.class.getResource("icons/logico-icon.png")));
         icons.put(TipoDado.MATRIZ_CARACTER, ImageIO.read(DefaultVariavel.class.getResource("icons/caracter-icon.png")));
         icons.put(TipoDado.MATRIZ_INTEIRO, ImageIO.read(DefaultVariavel.class.getResource("icons/inteiro-icon.png")));
         icons.put(TipoDado.MATRIZ_LOGICO, ImageIO.read(DefaultVariavel.class.getResource("icons/logico-icon.png")));
         icons.put(TipoDado.MATRIZ_REAL, ImageIO.read(DefaultVariavel.class.getResource("icons/real-icon.png")));
         icons.put(TipoDado.MATRIZ_TEXTO, ImageIO.read(DefaultVariavel.class.getResource("icons/texto-icon.png")));
         icons.put(TipoDado.REAL, ImageIO.read(DefaultVariavel.class.getResource("icons/real-icon.png")));
         icons.put(TipoDado.TEXTO, ImageIO.read(DefaultVariavel.class.getResource("icons/texto-icon.png")));
         icons.put(TipoDado.VETOR_CARACTER, ImageIO.read(DefaultVariavel.class.getResource("icons/caracter-icon.png")));
         icons.put(TipoDado.VETOR_INTEIRO, ImageIO.read(DefaultVariavel.class.getResource("icons/inteiro-icon.png")));
         icons.put(TipoDado.VETOR_LOGICO, ImageIO.read(DefaultVariavel.class.getResource("icons/logico-icon.png")));
         icons.put(TipoDado.VETOR_REAL, ImageIO.read(DefaultVariavel.class.getResource("icons/real-icon.png")));
         icons.put(TipoDado.VETOR_TEXTO, ImageIO.read(DefaultVariavel.class.getResource("icons/texto-icon.png")));
      } catch (Exception var2) {
         Logger.getLogger(DefaultVariavel.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

      REFERENCE_IMAGE = refImg;
   }
}
