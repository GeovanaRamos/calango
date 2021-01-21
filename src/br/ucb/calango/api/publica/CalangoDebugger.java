package br.ucb.calango.api.publica;

public interface CalangoDebugger {
   void passo(String var1, Integer var2);

   void pushedEscopo(String var1);

   void poppedEscopo();

   void definedVariable(TipoDado var1, String var2, Object var3, String var4);

   void settedValor(String var1, Object var2);
}
