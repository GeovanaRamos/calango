package br.ucb.calango.exceptions.erros;

public class VariavelTravadaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public VariavelTravadaException(String identificador) {
      super(VariavelTravadaException.class, identificador);
   }
}
