package br.ucb.calango.tipos;

public enum Operacoes {
   SOMA("somar"),
   SUBTRACAO("subtrair"),
   MULTIPLICACAO("multiplicar"),
   DIVISAO("dividir"),
   MOD("dividir");

   private String descricao;

   private Operacoes(String descricao) {
      this.descricao = descricao;
   }

   public String getDescricao() {
      return this.descricao;
   }
}
