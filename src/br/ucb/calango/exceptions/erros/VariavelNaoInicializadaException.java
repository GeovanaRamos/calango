package br.ucb.calango.exceptions.erros;

public class VariavelNaoInicializadaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public VariavelNaoInicializadaException(String identificador) {
      super(VariavelNaoInicializadaException.class, identificador);
   }
}
