package br.ucb.calango.exceptions.erros;

public class TamanhoMatrizIncompativelException extends CalangoRuntimeException {
   private static final long serialVersionUID = 1L;

   public TamanhoMatrizIncompativelException(Integer tamanhoDeclarado, Integer tamanhoPassado) {
      super(TamanhoMatrizIncompativelException.class, tamanhoDeclarado, tamanhoPassado);
   }
}
