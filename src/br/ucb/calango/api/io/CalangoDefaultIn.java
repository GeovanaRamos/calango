package br.ucb.calango.api.io;

import br.ucb.calango.api.publica.CalangoIn;
import java.util.Scanner;

public class CalangoDefaultIn implements CalangoIn {
   public String read() {
      Scanner scan = new Scanner(System.in);
      return scan.nextLine();
   }

   public Character readChar() {
      Scanner scan = new Scanner(System.in);
      String nextLine = scan.nextLine();
      return nextLine == null ? null : nextLine.charAt(0);
   }
}
