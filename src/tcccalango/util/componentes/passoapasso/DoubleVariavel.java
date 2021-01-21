package tcccalango.util.componentes.passoapasso;

import java.text.DecimalFormat;

public class DoubleVariavel extends DefaultVariavel {
   private static final DecimalFormat df = new DecimalFormat("0.000");

   public final void setValor(Object valor) {
      if (valor != null && valor instanceof Number) {
         valor = df.format((Number)valor);
      }

      super.setValor(valor);
   }
}
