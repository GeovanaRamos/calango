package br.ucb.calango.exceptions.erros;

public class ValoresForaDoLimiteException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ValoresForaDoLimiteException(int input1, int input2, int lim1, int lim2) {
      super(ValoresForaDoLimiteException.class, input1, input2, lim1, lim2);
   }
}
