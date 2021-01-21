package br.ucb.calango.exceptions.erros;

public class ChamaFuncaoInvalidaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ChamaFuncaoInvalidaException(String identifier) {
      super(ChamaFuncaoInvalidaException.class, identifier);
   }
}
