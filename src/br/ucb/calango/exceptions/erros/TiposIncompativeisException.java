package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class TiposIncompativeisException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public TiposIncompativeisException(Class<?> tipo1, Class<?> tipo2) {
      super(TiposIncompativeisException.class, AcoesUtil.getTypeName(tipo1), AcoesUtil.getTypeName(tipo2));
   }
}
