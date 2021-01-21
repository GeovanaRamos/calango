package br.ucb.calango.exceptions.statements;

import br.ucb.calango.simbolos.Simbolo;

public class RetornaValueException extends RuntimeException {
   private static final long serialVersionUID = 126241238395797945L;
   private Simbolo value;

   public RetornaValueException(Simbolo value) {
      this.value = value;
   }

   public Simbolo getValue() {
      return this.value;
   }
}
