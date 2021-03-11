package tcccalango.util.interpretador;

import br.ucb.calango.api.publica.CalangoDebugger;
import br.ucb.calango.api.publica.TipoDado;
import br.ucb.calango.exceptions.statements.CalangoInterruptionException;
import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import tcccalango.util.componentes.passoapasso.PassoAPassoViewer;
import tcccalango.view.util.RSyntaxTextAreaUtil;

public class CalangoIDEDebug implements CalangoDebugger {
   private PassoAPassoViewer viewer;
   private RSyntaxTextArea jtAlgoritmo;

   public CalangoIDEDebug(PassoAPassoViewer viewer, RSyntaxTextArea jtAlgoritmo) {
      this.viewer = viewer;
      this.jtAlgoritmo = jtAlgoritmo;
   }

   public void passo() {
      synchronized(this) {
         this.notify();
      }
   }

   public void passo(String string, Integer intgr) {
      RSyntaxTextAreaUtil.highlightAndScroll(this.jtAlgoritmo, intgr, new Color(200, 200, 255));
      this.viewer.requestFocusInWindow();
      this.pausa();
   }

   public void pushedEscopo(String nomeEscopo) {
      if ("_principal".equals(nomeEscopo)) {
         nomeEscopo = "Escopo Principal";
      }

      this.viewer.adicionaEscopo(nomeEscopo);
   }

   public void poppedEscopo() {
      this.viewer.removeEscopoAtual();
   }

   public void definedVariable(TipoDado tipo, String nome, Object valor, String nomeVariavelReferencia) {
      this.viewer.adicionaVariavel(nome, tipo, valor, nomeVariavelReferencia);
      this.pausa();
   }

   public void settedValor(String nome, Object valor) {
      this.viewer.atualizaVariavel(nome, valor);
   }

   private void pausa() {
      this.viewer.revalidate();
      this.viewer.repaint();
      synchronized(this) {
         try {
            this.wait();
         } catch (InterruptedException var4) {
            throw new CalangoInterruptionException();
         }

      }
   }
}
