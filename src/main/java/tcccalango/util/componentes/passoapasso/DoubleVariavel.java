package tcccalango.util.componentes.passoapasso;

import java.util.Locale;

public class DoubleVariavel extends DefaultVariavel {

   public final void setValor(Object valor) {
      if (valor instanceof Number) {
         valor = String.format(Locale.US, "%s", valor);
      }

      super.setValor(valor);
   }
}
