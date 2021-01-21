package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.MensagensUtil;

public class CalangoRuntimeException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public CalangoRuntimeException(Class<?> c, Object... params) {
      super(MensagensUtil.getMessage(c, params));
   }
}
