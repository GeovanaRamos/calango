package tcccalango.util.componentes.passoapasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DoubleVariavel extends DefaultVariavel {
   private final static NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
   private static final DecimalFormat df = (DecimalFormat)nf;

   public final void setValor(Object valor) {
      df.setGroupingUsed(false);
      if (valor != null && valor instanceof Number) {
         valor = df.format((Number)valor);
      }

      super.setValor(valor);
   }
}
