package br.ucb.calango.exceptions.erros;

import br.ucb.calango.util.AcoesUtil;

public class FuncaoNecessitaTipoException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public FuncaoNecessitaTipoException(String funcao, Class<?> tipo) {
      super(FuncaoNecessitaTipoException.class, funcao, AcoesUtil.getTypeName(tipo));
   }
}
