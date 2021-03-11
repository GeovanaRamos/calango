package tcccalango.util.componentes.passoapasso;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class AnimatedLayoutManager implements LayoutManager {
   private static final double ANIMATION_SPEED = 1.01D;
   private Map<Container, List<Component>> adicionados = new HashMap();
   private Map<Container, List<Component>> removidos = new HashMap();
   private final HashSet<Container> renderizados = new HashSet();
   private Thread t = new Thread(new Runnable() {
      public void run() {
         try {

            try {
               while(true) {
                  synchronized (AnimatedLayoutManager.this.renderizados){
                     AnimatedLayoutManager.this.renderizados.wait();
                  }
                  Iterator i$ = AnimatedLayoutManager.this.renderizados.iterator();

                  while(i$.hasNext()) {
                     Container c = (Container)i$.next();
                     ((JComponent)c).revalidate();
                  }

                  AnimatedLayoutManager.this.renderizados.clear();
               }
            } finally {
               ;
            }
         } catch (InterruptedException var8) {
            Logger.getLogger(AnimatedLayoutManager.class.getName()).log(Level.SEVERE, (String)null, var8);
         }
      }
   });

   public AnimatedLayoutManager() {
      this.t.start();
   }

   public void addLayoutComponent(String string, Component cmpnt) {
   }

   public void removeLayoutComponent(Component cmpnt) {
   }

   public Dimension preferredLayoutSize(Container cntnr) {
      Dimension min = this.minimumLayoutSize(cntnr);
      int w = min.width;

      for(int i = 0; i < cntnr.getComponentCount(); ++i) {
         Component child = cntnr.getComponent(i);
         if (w < child.getPreferredSize().width) {
            w = child.getPreferredSize().width;
         }
      }

      return new Dimension(w, min.height);
   }

   public Dimension minimumLayoutSize(Container cntnr) {
      int w = 0;
      int h = 0;

      for(int i = 0; i < cntnr.getComponentCount(); ++i) {
         Component child = cntnr.getComponent(i);
         if (w < child.getMinimumSize().width) {
            w = child.getMinimumSize().width;
         }

         h += child.getMinimumSize().height;
      }

      return new Dimension(w, h);
   }

   public void layoutContainer(Container cntnr) {
      if (!this.adicionados.containsKey(cntnr)) {
         this.adicionados.put(cntnr, new ArrayList());
         this.removidos.put(cntnr, new ArrayList());
      }

      int width = cntnr.getWidth();
      List<Component> childs = new ArrayList((Collection)this.adicionados.get(cntnr));
      synchronized(this.renderizados) {
         int i = 0;

         int y;
         Component child;
         for(y = 0; i < cntnr.getComponentCount(); ++i) {
            child = cntnr.getComponent(i);
            if (!((List)this.removidos.get(cntnr)).contains(child)) {
               if (!childs.contains(child)) {
                  child.setBounds(0, cntnr.getHeight(), width, child.getPreferredSize().height);
                  this.renderizados.add(cntnr);
                  this.renderizados.notify();
                  ((List)this.adicionados.get(cntnr)).add(child);
               } else if (child.getY() != y) {
                  child.setBounds(0, y + (int)((double)(child.getY() - y) / 1.01D), width, child.getPreferredSize().height);
                  this.renderizados.add(cntnr);
                  this.renderizados.notify();
                  childs.remove(child);
               } else {
                  child.setBounds(0, y, width, child.getPreferredSize().height);
                  childs.remove(child);
               }

               y += child.getPreferredSize().height;
            }
         }

         ((List)this.adicionados.get(cntnr)).removeAll(childs);
         ((List)this.removidos.get(cntnr)).addAll(childs);
         Iterator i$ = childs.iterator();

         while(i$.hasNext()) {
            child = (Component)i$.next();
            cntnr.add(child);
         }

         i = 0;

         for(y = 0; i < cntnr.getComponentCount(); ++i) {
            child = cntnr.getComponent(i);
            if (((List)this.removidos.get(cntnr)).contains(child)) {
               child.setBounds(0, y + (int)((double)(1 + child.getY() - y) * 1.01D), width, child.getPreferredSize().height);
               this.renderizados.add(cntnr);
               this.renderizados.notify();
               if (Math.abs(child.getY()) >= Math.max(y + child.getPreferredSize().height + 50, cntnr.getHeight() - y)) {
                  ((List)this.removidos.get(cntnr)).remove(child);
                  cntnr.remove(child);
               }
            }

            y += child.getPreferredSize().height;
         }

      }
   }
}
