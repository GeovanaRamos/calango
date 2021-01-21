package br.ucb.calango.executable;

import br.ucb.calango.api.publica.CalangoAPI;
import java.io.File;
import java.io.IOException;

public class Main {
   public static void main(String[] args) {
      try {
         CalangoAPI.interpretar(new File(args[0]));
      } catch (IOException var2) {
         System.err.println("Ocorreu um erro no tratamento na abertura de arquivo.");
      }

   }
}
