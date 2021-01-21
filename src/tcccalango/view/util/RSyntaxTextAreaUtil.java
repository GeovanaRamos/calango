package tcccalango.view.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import tcccalango.util.interpretador.CalangoIDEDebug;

public class RSyntaxTextAreaUtil {
   public static void highlightAndScroll(RSyntaxTextArea jtAlgoritmo, int line, Color color) {
      jtAlgoritmo.removeAllLineHighlights();

      try {
         jtAlgoritmo.addLineHighlight(line - 1, color);
      } catch (BadLocationException var7) {
         Logger.getLogger(CalangoIDEDebug.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      int linex = jtAlgoritmo.getLineHeight() * (line - 1);
      Rectangle vrect = jtAlgoritmo.getVisibleRect();
      if (linex < vrect.y || linex > vrect.y + vrect.height) {
         try {
            jtAlgoritmo.scrollRectToVisible(new Rectangle(vrect.x, Math.min(linex, (int)((double)jtAlgoritmo.getHeight() - vrect.getHeight())), vrect.width, vrect.height));
         } catch (Exception var6) {
         }
      }

   }
}
