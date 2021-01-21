package br.ucb.calango.api.publica;

import br.ucb.calango.api.debugger.CalangoDefaultDebugger;
import br.ucb.calango.api.interpretador.Interpretador;
import br.ucb.calango.api.io.CalangoDefaultIn;
import br.ucb.calango.api.io.CalangoDefaultOut;
import br.ucb.calango.exceptions.statements.CalangoInterruptionException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.util.MensagensUtil;
import java.io.File;
import java.io.IOException;

public final class CalangoAPI {
   private static CalangoOut out = new CalangoDefaultOut();
   private static CalangoIn in = new CalangoDefaultIn();
   private static CalangoDebugger debugger = new CalangoDefaultDebugger();
   private static Interpretador interpretador = new Interpretador();

   static {
      try {
         Class.forName("br.ucb.calango.util.MensagensUtil");
      } catch (ClassNotFoundException var1) {
         out.printErro(-1, MensagensUtil.getMessage("ErroDeInicializacao"));
      }

   }

   public static void print(Object mensagem) {
      out.print(mensagem);
   }

   public static void novaLinha() {
      out.novaLinha();
   }

   public static void printErro(int line, Object mensagem) {
      out.printErro(line, mensagem);
   }

   public static String read() {
      String read = in.read();
      if (read == null) {
         throw new CalangoInterruptionException();
      } else {
         return read;
      }
   }

   public static Character readChar() {
      Character read = in.readChar();
      return read;
   }

   public static void limpaTela() {
      out.limpatela();
   }

   public static void interpretar(String codigo) {
      interpretador.interpretar(codigo);
   }

   public static void interpretar(File file) throws IOException {
      interpretador.interpretar(file);
   }

   public static void depurar(String codigo) {
      interpretador.depurar(codigo);
   }

   public static void depurar(File file) throws IOException {
      interpretador.depurar(file);
   }

   public static void passo(String acao, Integer linha) {
      if (Algoritmo.isDebugging()) {
         debugger.passo(acao, linha);
      }

   }

   public static void pushedEscopo(String nomeEscopo) {
      if (Algoritmo.isDebugging()) {
         debugger.pushedEscopo(nomeEscopo);
      }

   }

   public static void popedEscopo() {
      if (Algoritmo.isDebugging()) {
         debugger.poppedEscopo();
      }

   }

   public static void definedVariable(TipoDado tipo, String identificador, Object valor, String nomeReferencia) {
      if (Algoritmo.isDebugging()) {
         debugger.definedVariable(tipo, identificador, valor, nomeReferencia);
      }

   }

   public static void settedValor(String identificador, Object valor) {
      if (Algoritmo.isDebugging()) {
         debugger.settedValor(identificador, valor);
      }

   }

   public static void setOut(CalangoOut out) {
      CalangoAPI.out = out;
   }

   public static void setIn(CalangoIn in) {
      CalangoAPI.in = in;
   }

   public static void setDebugger(CalangoDebugger debugger) {
      CalangoAPI.debugger = debugger;
   }
}
