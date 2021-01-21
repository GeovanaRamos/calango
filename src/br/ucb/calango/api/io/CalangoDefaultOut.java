package br.ucb.calango.api.io;

import br.ucb.calango.api.publica.CalangoOut;

public class CalangoDefaultOut implements CalangoOut {
   public void print(Object message) {
      System.out.print(message == null ? "" : message.toString());
   }

   public void novaLinha() {
      System.out.println("");
   }

   public void printErro(int line, Object message) {
      System.err.println(message == null ? "" : line + " >> " + message.toString());
   }

   public void limpatela() {
      for(int i = 0; i < 1000; ++i) {
         System.out.println();
      }

   }
}
