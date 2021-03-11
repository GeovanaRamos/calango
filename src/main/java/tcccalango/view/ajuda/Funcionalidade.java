package tcccalango.view.ajuda;

import java.util.List;

public interface Funcionalidade extends TreeComponent<Funcionalidade> {
   String getDescricao();

   String getDescricaoFinal();

   int getIndex();

   String getNome();

   void setChilds(List<Funcionalidade> var1);

   void setDescricao(String var1);

   void setIndex(int var1);

   void setNome(String var1);
}
