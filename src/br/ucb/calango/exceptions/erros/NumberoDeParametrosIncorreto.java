package br.ucb.calango.exceptions.erros;

public class NumberoDeParametrosIncorreto extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public NumberoDeParametrosIncorreto(Integer quantidadeReal, Integer quantidadePassada) {
      super(NumberoDeParametrosIncorreto.class, quantidadeReal, quantidadePassada);
   }
}
