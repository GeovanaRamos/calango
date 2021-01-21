package br.ucb.calango.exceptions.erros;

public class VariavelIndefinidaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public VariavelIndefinidaException(String identificador) {
      super(VariavelIndefinidaException.class, identificador);
   }
}
