package tcccalango.view.ajuda;

import java.util.List;

public interface TreeComponent<T extends TreeComponent> extends Comparable<T> {
   void add(T var1);

   List<T> getChilds();

   void setParent(TreeComponent var1);

   TreeComponent getParent();

   void remove(T var1);

   void saveToFile();
}
