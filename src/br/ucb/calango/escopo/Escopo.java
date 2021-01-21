package br.ucb.calango.escopo;

import br.ucb.calango.simbolos.Simbolo;
import java.util.HashMap;
import java.util.Map;

public class Escopo {
   private String nome;
   Map<String, Simbolo> simbolos = new HashMap();

   public Escopo(String nome) {
      this.nome = nome;
   }

   public String getNome() {
      return this.nome;
   }

   public Simbolo reference(String identificador) {
      return (Simbolo)this.simbolos.get(identificador);
   }

   public void put(Simbolo s) {
      this.simbolos.put(s.getIdentificador(), s);
   }
}
