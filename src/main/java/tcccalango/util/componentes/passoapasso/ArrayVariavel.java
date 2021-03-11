package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ArrayVariavel extends DefaultVariavel implements MouseListener, Parent {
   public static final JDialog detalhes = new JDialog();
   private TipoDado tipo;
   private JPanel childs;

   public Variavel getVariavel(int index) {
      return (Variavel)this.childs.getComponent(index + 1);
   }

   public ArrayVariavel() {
      this.listener = new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
               ArrayVariavel.detalhes.setTitle(ArrayVariavel.this.getNome());
               Point location = ArrayVariavel.this.getLocationOnScreen();
               ArrayVariavel.detalhes.setLocation(new Point(location.x + 20, location.y + 20));
               ArrayVariavel.detalhes.setContentPane(new JScrollPane(ArrayVariavel.this.childs));
               ArrayVariavel.detalhes.addWindowListener(new WindowAdapter() {
                  public void windowLostFocus(WindowEvent e) {
                     ArrayVariavel.detalhes.removeWindowListener(this);
                     ArrayVariavel.super.blur();
                  }

                  public void windowClosing(WindowEvent e) {
                     ArrayVariavel.detalhes.removeWindowListener(this);
                     ArrayVariavel.super.blur();
                  }
               });
               ArrayVariavel.detalhes.setVisible(true);
            }

         }

         public void mouseEntered(MouseEvent e) {
            if (ArrayVariavel.this.getReferencia() != null) {
               ArrayVariavel.this.hover();
            }

         }

         public void mouseExited(MouseEvent e) {
            ArrayVariavel.this.blur();
         }
      };
      this.childs = new JPanel(new AnimatedLayoutManager());
   }

   public void prepare(Parent parent, String nome, TipoDado tipo, Object valor, Variavel referencia) {
      super.prepare(parent, nome, tipo, "", referencia);
      this.tipo = tipo;
      String var6;
      String tooltip;
      switch(tipo) {
      case VETOR_CARACTER:
         var6 = "CARACTER[]";
         tooltip = "CARACTER[]";
         break;
      case VETOR_INTEIRO:
         var6 = "INTEIRO[]";
         tooltip = "INTEIRO[]";
         break;
      case VETOR_LOGICO:
         var6 = "LOGICO[]";
         tooltip = "LOGICO[]";
         break;
      case VETOR_REAL:
         var6 = "REAL[]";
         tooltip = "REAL[]";
         break;
      case VETOR_TEXTO:
         var6 = "TEXTO[]";
         tooltip = "TEXTO[]";
         break;
      default:
         throw new RuntimeException("Wrong type of array: " + tipo);
      }

      this.getValor().removeMouseListener(this);
      this.getValor().addMouseListener(this);
      this.getValor().setForeground(Color.BLUE);
      this.getValor().setCursor(Cursor.getPredefinedCursor(12));
      this.ltipo.setToolTipText(tooltip);
      this.setValor(valor);
   }

   public void setValor(Object valor) {
      if (valor != null) {
         if (valor instanceof Object[]) {
            Object[] os = (Object[])((Object[])valor);
            TipoDado td;
            String str;
            switch(this.tipo) {
            case VETOR_CARACTER:
               str = "CARACTER";
               td = TipoDado.CARACTER;
               break;
            case VETOR_INTEIRO:
               str = "INTEIRO";
               td = TipoDado.INTEIRO;
               break;
            case VETOR_LOGICO:
               str = "LOGICO";
               td = TipoDado.LOGICO;
               break;
            case VETOR_REAL:
               str = "REAL";
               td = TipoDado.REAL;
               break;
            case VETOR_TEXTO:
               str = "TEXTO";
               td = TipoDado.TEXTO;
               break;
            default:
               throw new RuntimeException("Wrong type of array: " + this.tipo);
            }

            super.setValor("Vetor[" + os.length + "]");
            this.getValor().setToolTipText("Clique para visualizar o conteúdo.");
            if (this.getReferencia() != null) {
               this.getReferencia().setValor(valor);
            }

            int i;
            if (os.length + 1 != this.childs.getComponentCount()) {
               this.childs.removeAll();
               this.childs.add(carregaHeader());

               for(i = 0; i < os.length; ++i) {
                  Variavel ref = this.getReferencia() != null ? ((ArrayVariavel)this.getReferencia()).getVariavel(i) : null;
                  Variavel variavel = VariavelFactory.create(this, this.getNome() + "[" + i + "]", td, os[i], ref);
                  this.childs.add((JComponent)variavel);
                  if (this.childs.getComponentCount() % 2 == 0) {
                     variavel.setBackground(new Color(230, 230, 230));
                  } else {
                     variavel.setBackground(Color.WHITE);
                  }
               }

               detalhes.setSize(350, 60 + Math.min(this.childs.getMinimumSize().height, 500));
            }

            for(i = 0; i < os.length; ++i) {
               ((Variavel)this.childs.getComponent(i + 1)).setValor(os[i]);
            }

         }
      }
   }

   public void hover() {
      if (!detalhes.isVisible()) {
         super.hover();
      }

   }

   public void blur() {
      if (!detalhes.isVisible()) {
         super.blur();
      }

   }

   private static JPanel carregaHeader() {
      return DefaultVariavel.createHeader();
   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   }

   public void mouseEntered(MouseEvent e) {
      Map<TextAttribute, Integer> fontAttributes = new HashMap();
      fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      this.getValor().setFont(this.getValor().getFont().deriveFont(fontAttributes));
   }

   public void mouseExited(MouseEvent e) {
      this.getValor().setFont(new Font(this.getValor().getFont().getFontName(), this.getValor().getFont().getStyle(), this.getValor().getFont().getSize()));
   }

   public Escopo getEscopo() {
      return this.getDebugParent().getEscopo();
   }

   static {
      detalhes.setSize(350, 250);
      detalhes.addWindowFocusListener(new WindowAdapter() {
         boolean focused;

         public void windowGainedFocus(WindowEvent e) {
            this.focused = true;
         }

         public void windowLostFocus(WindowEvent e) {
            if (this.focused) {
               ArrayVariavel.detalhes.setVisible(false);
               this.focused = false;
            }

         }
      });
   }
}
