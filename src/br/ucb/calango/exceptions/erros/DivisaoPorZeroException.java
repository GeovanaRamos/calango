package br.ucb.calango.exceptions.erros;

public class DivisaoPorZeroException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public DivisaoPorZeroException() {
      super(DivisaoPorZeroException.class);
   }
}
