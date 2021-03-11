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

public class MatrizVariavel extends DefaultVariavel implements MouseListener, Parent {
   public static final JDialog detalhes = new JDialog();
   private TipoDado tipo;
   private JPanel childs;

   public Variavel getVariavel(int index) {
      return (Variavel)this.childs.getComponent(index + 1);
   }

   public MatrizVariavel() {
      this.listener = new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
               MatrizVariavel.detalhes.setTitle(MatrizVariavel.this.getNome());
               Point location = MatrizVariavel.this.getLocationOnScreen();
               MatrizVariavel.detalhes.setLocation(new Point(location.x + 20, location.y + 20));
               MatrizVariavel.detalhes.setContentPane(new JScrollPane(MatrizVariavel.this.childs));
               MatrizVariavel.detalhes.addWindowListener(new WindowAdapter() {
                  public void windowLostFocus(WindowEvent e) {
                     MatrizVariavel.detalhes.removeWindowListener(this);
                     MatrizVariavel.super.blur();
                  }

                  public void windowClosing(WindowEvent e) {
                     MatrizVariavel.detalhes.removeWindowListener(this);
                     MatrizVariavel.super.blur();
                  }
               });
               MatrizVariavel.detalhes.setVisible(true);
            }

         }

         public void mouseEntered(MouseEvent e) {
            if (MatrizVariavel.this.getReferencia() != null) {
               MatrizVariavel.this.hover();
            }

         }

         public void mouseExited(MouseEvent e) {
            MatrizVariavel.this.blur();
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
      case MATRIZ_CARACTER:
         var6 = "CARACTER[][]";
         tooltip = "CARACTER[][]";
         break;
      case MATRIZ_INTEIRO:
         var6 = "INTEIRO[][]";
         tooltip = "INTEIRO[][]";
         break;
      case MATRIZ_LOGICO:
         var6 = "LOGICO[][]";
         tooltip = "LOGICO[][]";
         break;
      case MATRIZ_REAL:
         var6 = "REAL[][]";
         tooltip = "REAL[][]";
         break;
      case MATRIZ_TEXTO:
         var6 = "TEXTO[][]";
         tooltip = "TEXTO[][]";
         break;
      default:
         throw new RuntimeException("Wrong type of array: " + tipo);
      }

      this.ltipo.setToolTipText(tooltip);
      this.getValor().removeMouseListener(this);
      this.getValor().addMouseListener(this);
      this.getValor().setForeground(Color.BLUE);
      this.getValor().setCursor(Cursor.getPredefinedCursor(12));
      this.setValor(valor);
   }

   public void setValor(Object valor) {
      if (valor != null) {
         if (valor instanceof Object[][]) {
            Object[][] os = (Object[][])((Object[][])valor);
            TipoDado td;
            String str;
            switch(this.tipo) {
            case MATRIZ_CARACTER:
               str = "CARACTER";
               td = TipoDado.VETOR_CARACTER;
               break;
            case MATRIZ_INTEIRO:
               str = "INTEIRO";
               td = TipoDado.VETOR_INTEIRO;
               break;
            case MATRIZ_LOGICO:
               str = "LOGICO";
               td = TipoDado.VETOR_LOGICO;
               break;
            case MATRIZ_REAL:
               str = "REAL";
               td = TipoDado.VETOR_REAL;
               break;
            case MATRIZ_TEXTO:
               str = "TEXTO";
               td = TipoDado.VETOR_TEXTO;
               break;
            default:
               throw new RuntimeException("Wrong type of array: " + this.tipo);
            }

            super.setValor("Matriz[" + os.length + "][" + (os.length > 0 ? os[0].length : 0) + "]");
            this.getValor().setToolTipText("Clique para visualizar o conteúdo.");
            if (this.getReferencia() != null) {
               this.getReferencia().setValor(valor);
            }

            int i;
            if (os.length + 1 != this.childs.getComponentCount()) {
               this.childs.removeAll();
               this.childs.add(carregaHeader());

               for(i = 0; i < os.length; ++i) {
                  Variavel ref = this.getReferencia() != null ? ((MatrizVariavel)this.getReferencia()).getVariavel(i) : null;
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
            if (this.focused && !ArrayVariavel.detalhes.isVisible()) {
               MatrizVariavel.detalhes.setVisible(false);
               this.focused = false;
            }

         }
      });
   }
}
