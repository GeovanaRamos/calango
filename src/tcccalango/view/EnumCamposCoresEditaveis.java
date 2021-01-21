package tcccalango.view;

public enum EnumCamposCoresEditaveis {
   COMENTARIO(1, "Comentário"),
   CONSTANTE_TEXTO(2, "Constante texto"),
   CONSTANTE_NUMERICA(3, "Constante numérica"),
   FUNDO_EDITOR(4, "Fundo do Editor"),
   PALAVRA_CHAVE(5, "Palabra Chave"),
   TIPO_DADO(6, "Tipo de Dado"),
   TEXTO_GERAL(7, "Texto em Geral");

   private String descricao;
   private int id;

   private EnumCamposCoresEditaveis(int id, String descricao) {
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

   public EnumCamposCoresEditaveis getEnumByDescricao(String desc) {
      EnumCamposCoresEditaveis[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumCamposCoresEditaveis campo = arr$[i$];
         if (campo.getDescricao().equalsIgnoreCase(desc)) {
            return campo;
         }
      }

      return null;
   }

   public int getIdEnumByDescricao(String desc) {
      EnumCamposCoresEditaveis[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumCamposCoresEditaveis campo = arr$[i$];
         if (campo.getDescricao().equalsIgnoreCase(desc)) {
            return campo.getId();
         }
      }

      return 0;
   }
}
