package br.ucb.calango.api.publica;

public enum TipoDado {
   TEXTO("texto", false),
   CARACTER("caracter", false),
   INTEIRO("inteiro", false),
   REAL("real", false),
   LOGICO("logico", false),
   VETOR_TEXTO("texto[]", true),
   VETOR_CARACTER("caracter[]", true),
   VETOR_INTEIRO("inteiro[]", true),
   VETOR_REAL("real[]", true),
   VETOR_LOGICO("logico[]", true),
   MATRIZ_TEXTO("texto[][]", true),
   MATRIZ_CARACTER("caracter[][]", true),
   MATRIZ_INTEIRO("inteiro[][]", true),
   MATRIZ_REAL("real[][]", true),
   MATRIZ_LOGICO("logico[][]", true);

   private final String nomeTipo;
   private final boolean vetor;

   private TipoDado(String nomeTipo, boolean vetor) {
      this.nomeTipo = nomeTipo;
      this.vetor = vetor;
   }

   public String getNomeTipo() {
      return this.nomeTipo;
   }

   public boolean isVetor() {
      return this.vetor;
   }
}
