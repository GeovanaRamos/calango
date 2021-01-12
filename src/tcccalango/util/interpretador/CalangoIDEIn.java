/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.interpretador;

import br.ucb.calango.api.publica.CalangoIn;
import tcccalango.util.componentes.CalangoConsole;

/**
 *
 * @author Claeber
 */
public class CalangoIDEIn implements CalangoIn {

    private CalangoConsole tpConsole;
    public CalangoIDEIn(){
    }
    public CalangoIDEIn(CalangoConsole tpConsole){
        this.tpConsole = tpConsole;
    }

    public String read() {
        String texto = tpConsole.nextLine();
        return texto;
    }

    public CalangoConsole getTpConsole() {
        return tpConsole;
    }

    public void setTpConsole(CalangoConsole tpConsole) {
        this.tpConsole = tpConsole;
    }

    public Character readChar() {
        Character character = tpConsole.nextCharacter();
        tpConsole.append("\n");
        return character;
    }
    
}