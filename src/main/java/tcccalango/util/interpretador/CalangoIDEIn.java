package tcccalango.util.interpretador;

import br.ucb.calango.api.publica.CalangoIn;
import tcccalango.util.componentes.CalangoConsole;

public class CalangoIDEIn implements CalangoIn {
   private CalangoConsole tpConsole;

   public CalangoIDEIn() {
   }

   public CalangoIDEIn(CalangoConsole tpConsole) {
      this.tpConsole = tpConsole;
   }

   public String read() {
      this.tpConsole.requestFocusInWindow();
      this.tpConsole.requestFocus();
      String texto = this.tpConsole.nextLine();
      return texto;
   }

   public CalangoConsole getTpConsole() {
      return this.tpConsole;
   }

   public void setTpConsole(CalangoConsole tpConsole) {
      this.tpConsole = tpConsole;
   }

   public Character readChar() {
      this.tpConsole.requestFocusInWindow();
      this.tpConsole.requestFocus();
      Character character = this.tpConsole.nextCharacter();
      this.tpConsole.append("\n");
      return character;
   }
}
