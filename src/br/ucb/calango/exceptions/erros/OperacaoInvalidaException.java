package br.ucb.calango.exceptions.erros;

public class OperacaoInvalidaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public OperacaoInvalidaException(String descricaoOperaca, String valor1, String valor2) {
      super(OperacaoInvalidaException.class, descricaoOperaca, valor1, valor2);
   }
}
