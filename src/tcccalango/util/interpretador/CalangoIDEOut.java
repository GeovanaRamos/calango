/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.interpretador;

import br.ucb.calango.api.publica.CalangoOut;
import tcccalango.util.componentes.CalangoConsole;

/**
 *
 * @author Claeber
 */
public class CalangoIDEOut implements CalangoOut{
    
    private static final String BARRA_N = "\n";
    
    private CalangoConsole jtConsole;
    public CalangoIDEOut(){
        
    }
    public CalangoIDEOut(CalangoConsole jtConsole){
        this.jtConsole = jtConsole;
    }

    public void print(Object o) {
        jtConsole.append(o.toString());
    }

    public void printErro(Object o) {
        jtConsole.appendError(BARRA_N+o.toString());
    }

    public void novaLinha() {
        jtConsole.append(BARRA_N);
    }

    public void limpatela() {
        jtConsole.clear();
    }
    
}
