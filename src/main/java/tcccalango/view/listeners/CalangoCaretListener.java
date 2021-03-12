package tcccalango.view.listeners;

import java.awt.*;
import java.text.MessageFormat;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class CalangoCaretListener implements CaretListener {
   private RSyntaxTextArea textArea;
   private JLabel label;

   public CalangoCaretListener(RSyntaxTextArea textArea, JLabel label) {
      this.textArea = textArea;
      this.label = label;
      this.label.setForeground(Color.GRAY);
   }

   public void caretUpdate(CaretEvent ce) {
      this.label.setText(MessageFormat.format("{0} Linhas | Linha {1}, Coluna {2}", this.textArea.getLineCount(), this.textArea.getCaretLineNumber() + 1, this.textArea.getCaretOffsetFromLineStart() + 1));
   }
}
