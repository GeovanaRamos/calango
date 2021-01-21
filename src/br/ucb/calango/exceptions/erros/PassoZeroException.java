package br.ucb.calango.exceptions.erros;

public class PassoZeroException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public PassoZeroException() {
      super(PassoZeroException.class);
   }
}
