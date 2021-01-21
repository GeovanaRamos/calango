package br.ucb.calango.simbolos;

public class SimboloVariavel extends Simbolo {
   private Integer tamanhoMatriz;
   private String referencia;

   public SimboloVariavel(String identificador, Class<?> tipo, String referencia) {
      super(identificador, tipo, (Object)null);
      this.referencia = referencia;
      this.tamanhoMatriz = -1;
   }

   public SimboloVariavel(String identificador, Class<?> tipo, String referencia, Integer tamanhoMatriz) {
      super(identificador, tipo, (Object)null);
      this.referencia = referencia;
      this.tamanhoMatriz = -1;
      this.tamanhoMatriz = tamanhoMatriz;
   }

   public boolean isReferencia() {
      return this.referencia != null;
   }

   public String getReferencia() {
      return this.referencia;
   }

   public void setReferencia(String referencia) {
      this.referencia = referencia;
   }

   public Integer getTamanhoMatriz() {
      return this.tamanhoMatriz;
   }

   public void setTamanhoMatriz(Integer tamanhoMatriz) {
      this.tamanhoMatriz = tamanhoMatriz;
   }
}
