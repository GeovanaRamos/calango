package br.ucb.calango.api.debugger;

import br.ucb.calango.api.publica.CalangoDebugger;
import br.ucb.calango.api.publica.TipoDado;
import java.text.MessageFormat;
import java.util.Scanner;

public class CalangoDefaultDebugger implements CalangoDebugger {
   public void passo(String acao, Integer linha) {
      System.out.println(linha + " >> Passo: " + acao);
      Scanner scan = new Scanner(System.in);
      scan.nextLine();
   }

   public void pushedEscopo(String nomeEscopo) {
      System.out.println(">> PUSH: " + nomeEscopo);
   }

   public void poppedEscopo() {
      System.out.println(">> POP ESCOPO");
   }

   public void definedVariable(TipoDado tipo, String identificador, Object valor, String nomeReferencia) {
      System.out.println(MessageFormat.format(">> DEFINE: {0} {1} - {2} -> {3}", tipo.getNomeTipo(), identificador, valor, nomeReferencia));
   }

   public void settedValor(String identificador, Object valor) {
      System.out.println(MessageFormat.format(">> SET: {0} valor: {1}", identificador, valor));
   }
}
