package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class NegacaoLogicaInvalidaException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public NegacaoLogicaInvalidaException(Class<?> c) {
      super(NegacaoLogicaInvalidaException.class, AcoesUtil.getTypeName(c));
   }
}
