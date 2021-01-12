/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.componentes;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author Gabriel
 */
public interface IMonitorFrame {

    JButton getButton();

    /**
     * Define o componente que serÃ¡ posicionado no lugar da "tela" do "monitor"
     * @param console
     */
    void setConsole(JComponent console);

    public void setVisible(boolean b);

    public boolean requestFocusInWindow();

    public void removeConsole();

    public boolean isConsole(JComponent jpConsole);
    
}
