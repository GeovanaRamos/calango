package tcccalango.view.ajuda;

import java.util.ArrayList;

public class FuncionalidadeList extends ArrayList<Funcionalidade> {
   private TreeComponent parent;

   public FuncionalidadeList(TreeComponent parent) {
      this.parent = parent;
   }

   public boolean add(Funcionalidade e) {
      if (!(e instanceof FakeFuncionalidade)) {
         e.setParent(this.parent);
         return super.add(e);
      } else {
         return false;
      }
   }
}
