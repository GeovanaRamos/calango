package br.ucb.calango.exceptions.erros;

public class VariavelNaoIndexadaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public VariavelNaoIndexadaException(String nomeVar) {
      super(VariavelNaoIndexadaException.class, nomeVar);
   }
}
