package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class TipoParametroIncompativelException extends CalangoRuntimeException {
   private static final long serialVersionUID = 2388480423582038877L;

   public TipoParametroIncompativelException(Class<?> tipo) {
      super(TipoParametroIncompativelException.class, AcoesUtil.getTypeName(tipo));
   }
}
