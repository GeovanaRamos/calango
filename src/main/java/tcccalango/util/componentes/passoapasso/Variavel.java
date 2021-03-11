package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;
import java.awt.Color;
import java.awt.event.MouseListener;

public interface Variavel {
   void prepare(Parent var1, String var2, TipoDado var3, Object var4, Variavel var5);

   Parent getDebugParent();

   String getNome();

   void setBackground(Color var1);

   void setValor(Object var1);

   void hover();

   void blur();

   void setListener(MouseListener var1);

   Object getReferencia();
}
