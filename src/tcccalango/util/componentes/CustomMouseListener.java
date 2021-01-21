package tcccalango.util.componentes;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

class CustomMouseListener {
   private CalangoMonitorFrame frame;
   private Point inicialPoint;
   final MouseListener mouseListener = new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
         CustomMouseListener.this.inicialPoint = e.getPoint();
      }

      public void mouseReleased(MouseEvent e) {
         CustomMouseListener.this.inicialPoint = null;
      }
   };
   final MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
         if (CustomMouseListener.this.inicialPoint != null) {
            Point novo = e.getPoint();
            Point ponto = CustomMouseListener.this.frame.getLocation();
            CustomMouseListener.this.frame.setLocation(ponto.x + (novo.x - CustomMouseListener.this.inicialPoint.x), ponto.y + (novo.y - CustomMouseListener.this.inicialPoint.y));
         }

      }
   };

   public CustomMouseListener(CalangoMonitorFrame frame) {
      this.frame = frame;
   }
}
