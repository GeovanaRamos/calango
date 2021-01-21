package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class TiposIncompativeisEmOperacaoException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public TiposIncompativeisEmOperacaoException(Class<?> tipo1, Class<?> tipo2) {
      super(TiposIncompativeisEmOperacaoException.class, AcoesUtil.getTypeName(tipo1), AcoesUtil.getTypeName(tipo2));
   }
}
