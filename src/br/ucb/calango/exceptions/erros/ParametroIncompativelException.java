package br.ucb.calango.exceptions.erros;

public class ParametroIncompativelException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ParametroIncompativelException() {
      super(ParametroIncompativelException.class);
   }
}
