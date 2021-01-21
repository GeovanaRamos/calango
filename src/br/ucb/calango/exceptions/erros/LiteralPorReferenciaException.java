package br.ucb.calango.exceptions.erros;

public class LiteralPorReferenciaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public LiteralPorReferenciaException() {
      super(LiteralPorReferenciaException.class);
   }
}
