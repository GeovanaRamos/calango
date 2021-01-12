/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.listeners;

import java.text.MessageFormat;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 *
 * @author victorgirotto
 */
public class CalangoCaretListener implements CaretListener {

    private RSyntaxTextArea textArea;
    private JLabel label;
    
    public CalangoCaretListener(RSyntaxTextArea textArea, JLabel label) {
        this.textArea = textArea;
        this.label = label;
    }
    
    public void caretUpdate(CaretEvent ce) {
        // TODO put format string in properties file
        label.setText(MessageFormat.format("{0} Linhas | Linha {1}, Coluna {2}", textArea.getLineCount(),
                                                            textArea.getCaretLineNumber() + 1,
                                                            textArea.getCaretOffsetFromLineStart() + 1));
    }
    
}
