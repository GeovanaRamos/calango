package br.ucb.calango.exceptions.erros;

public class ComparacaoTiposIncompativeisException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public ComparacaoTiposIncompativeisException(Object... params) {
      super(ComparacaoTiposIncompativeisException.class, params);
   }
}
