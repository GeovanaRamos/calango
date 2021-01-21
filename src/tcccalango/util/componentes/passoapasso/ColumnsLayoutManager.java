package tcccalango.util.componentes.passoapasso;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ColumnsLayoutManager implements LayoutManager {
   private final Map<Component, Double> cp = new HashMap();
   private final List<Component> sp = new ArrayList();

   public void addLayoutComponent(String string, Component cmpnt) {
      if (string != null && string.matches("[0-9]+(\\.[0-9]+)?")) {
         this.cp.put(cmpnt, Double.parseDouble(string) / 100.0D);
      } else {
         this.sp.add(cmpnt);
      }

   }

   public void removeLayoutComponent(Component cmpnt) {
      this.cp.remove(cmpnt);
      this.sp.remove(cmpnt);
   }

   public Dimension preferredLayoutSize(Container cntnr) {
      return this.minimumLayoutSize(cntnr);
   }

   public Dimension minimumLayoutSize(Container cntnr) {
      int w = 0;
      int h = 0;

      for(int i = 0; i < cntnr.getComponentCount(); ++i) {
         Component child = cntnr.getComponent(i);
         if (h < child.getMinimumSize().height) {
            h = child.getMinimumSize().height;
         }

         w += child.getMinimumSize().width;
      }

      return new Dimension(w, h);
   }

   public void layoutContainer(Container cntnr) {
      int height = cntnr.getSize().height;
      int width = cntnr.getSize().width;
      int totalWidth = 0;

      Iterator i$;
      int w;
      for(i$ = this.cp.entrySet().iterator(); i$.hasNext(); totalWidth += w) {
         Entry<Component, Double> entry = (Entry)i$.next();
         w = (int)((double)width * (Double)entry.getValue());
         ((Component)entry.getKey()).setSize(w, height);
      }

      totalWidth = width - totalWidth;
      i$ = this.sp.iterator();

      while(i$.hasNext()) {
         Component c = (Component)i$.next();
         c.setSize(totalWidth / this.sp.size(), height);
      }

      int x = 0;

      for(int i = 0; i < cntnr.getComponentCount(); ++i) {
         Component child = cntnr.getComponent(i);
         child.setBounds(x, 0, child.getWidth(), child.getHeight());
         x += child.getWidth();
      }

   }
}
