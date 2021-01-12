/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.componentes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import tcccalango.util.componentes.SimpleMonitorFrame.CloseListener;

/**
 *
 * @author Gabriel
 */
public class SimpleMonitorFrame extends JDialog implements IMonitorFrame {

    private CloseListener listener;
    
    private JComponent console;
    
    public SimpleMonitorFrame(CloseListener listener) {
        setTitle("Console");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);
        
        this.listener = listener;
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowListener());
        
        setLayout(new GridLayout(1, 1));
        ((JComponent) getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "esc");
        ((JComponent) getContentPane()).getActionMap().put("esc", new AbstractAction(){
             @Override
             public void actionPerformed(ActionEvent e){
                 SimpleMonitorFrame.this.listener.esc();
             }
        });
    }
    
    public JButton getButton() {
        return new JButton();
    }

    public void setConsole(JComponent console) {
        add(this.console = console);
    }

    public boolean isConsole(JComponent console) {
        return console.equals(this.console);
    }

    public void removeConsole() {
        if (console!=null){
            remove(console);
            console = null;
        }
    }
    
    public interface CloseListener {
        public void esc();
        public void close();
    }

    class WindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            listener.close();
        }

    }
}
