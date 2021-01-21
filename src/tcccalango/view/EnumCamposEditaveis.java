package tcccalango.view;

public enum EnumCamposEditaveis {
   COMENTARIO(1, "Comentário"),
   CONSTANTE_TEXTO(2, "Constante texto"),
   CONSTANTE_NUMERICA(3, "Constante numérica"),
   FUNDO_EDITOR(4, "Fundo do Editor"),
   PALAVRA_CHAVE(5, "Palavra Chave"),
   TIPO_DADO(6, "Tipo de Dado"),
   TEXTO_GERAL(7, "Texto em Geral");

   private String descricao;
   private int id;

   private EnumCamposEditaveis(int id, String descricao) {
      this.descricao = descricao;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public EnumCamposEditaveis getEnumByDescricao(String desc) {
      EnumCamposEditaveis[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumCamposEditaveis campo = arr$[i$];
         if (campo.getDescricao().equalsIgnoreCase(desc)) {
            return campo;
         }
      }

      return null;
   }

   public int getIdEnumByDescricao(String desc) {
      EnumCamposEditaveis[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumCamposEditaveis campo = arr$[i$];
         if (campo.getDescricao().equalsIgnoreCase(desc)) {
            return campo.getId();
         }
      }

      return 0;
   }
}
