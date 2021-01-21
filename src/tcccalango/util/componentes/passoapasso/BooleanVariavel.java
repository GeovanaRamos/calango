package tcccalango.util.componentes.passoapasso;

public class BooleanVariavel extends DefaultVariavel {
   public final void setValor(Object valor) {
      if (valor != null && valor instanceof Boolean) {
         valor = new Boolean(valor.toString()) ? "verdadeiro" : "falso";
      }

      super.setValor(valor);
   }
}
