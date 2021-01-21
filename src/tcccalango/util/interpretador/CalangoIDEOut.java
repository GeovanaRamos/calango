package tcccalango.util.interpretador;

import br.ucb.calango.api.publica.CalangoOut;
import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import tcccalango.util.componentes.CalangoConsole;
import tcccalango.view.util.RSyntaxTextAreaUtil;

public class CalangoIDEOut implements CalangoOut {
   private static final String BARRA_N = "\n";
   private CalangoConsole jtConsole;
   private RSyntaxTextArea jtAlgoritmo;

   public CalangoIDEOut() {
   }

   public CalangoIDEOut(CalangoConsole jtConsole, RSyntaxTextArea jtAlgoritmo) {
      this.jtConsole = jtConsole;
      this.jtAlgoritmo = jtAlgoritmo;
   }

   public void print(Object o) {
      this.jtConsole.append(o.toString());
   }

   public void printErro(int line, Object o) {
      this.jtConsole.appendError("\n");
      if (line > 0) {
         RSyntaxTextAreaUtil.highlightAndScroll(this.jtAlgoritmo, line, new Color(255, 200, 200));
         this.jtConsole.appendError("Linha " + line + " >> ");
      }

      this.jtConsole.appendError(o.toString());
   }

   public void novaLinha() {
      this.jtConsole.append("\n");
   }

   public void limpatela() {
      this.jtConsole.clear();
   }
}
