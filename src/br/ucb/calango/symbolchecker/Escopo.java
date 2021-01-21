package br.ucb.calango.symbolchecker;

import java.util.HashSet;

class Escopo {
   private HashSet<String> simbolos = new HashSet();

   public Escopo() {
   }

   public void define(String simbolo) {
      this.simbolos.add(simbolo);
   }

   public boolean isDefined(String simbolo) {
      return this.simbolos.contains(simbolo);
   }
}
