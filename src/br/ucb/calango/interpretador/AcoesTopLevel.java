package br.ucb.calango.interpretador;

import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.escopo.Escopo;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloMetodo;
import br.ucb.calango.simbolos.SimboloVariavel;
import br.ucb.calango.util.AcoesUtil;
import java.util.List;

public class AcoesTopLevel {
   public static void algoritmo(CalangoTree tree) {
      for(int i = 0; i < tree.getChildCount(); ++i) {
         Interpretador.exec((CalangoTree)tree.getChild(i));
      }

   }

   public static void principal(CalangoTree tree) {
      Algoritmo.pushEscopo(new Escopo("_principal"));

      for(int i = 0; i < tree.getChildCount(); ++i) {
         Interpretador.exec((CalangoTree)tree.getChild(i));
      }

   }

   public static void procedimento(CalangoTree tree) {
      int lineStart = tree.getLine();
      int lineEnd = tree.getFirstChildWithType(40).getLine();
      String identificador = tree.getChild(0).getText();
      CalangoTree child1 = (CalangoTree)tree.getChild(1);
      SimboloMetodo metodo;
      if (child1.isType(72)) {
         List<SimboloVariavel> parametros = AcoesUtil.getParameters(child1);
         metodo = new SimboloMetodo(identificador, parametros, (CalangoTree)tree.getChild(2), lineStart, lineEnd);
      } else {
         metodo = new SimboloMetodo(identificador, child1, lineStart, lineEnd);
      }

      Algoritmo.define((Simbolo)metodo);
   }

   public static void funcao(CalangoTree tree) {
      int lineStart = tree.getLine();
      int lineEnd = tree.getFirstChildWithType(39).getLine();
      Class<?> tipo = AcoesUtil.getTypeFromString(tree.getChild(0).getText());
      String identificador = tree.getChild(1).getText();
      CalangoTree child1 = (CalangoTree)tree.getChild(2);
      SimboloMetodo metodo;
      if (child1.isType(72)) {
         List<SimboloVariavel> parametros = AcoesUtil.getParameters(child1);
         metodo = new SimboloMetodo(identificador, tipo, parametros, (CalangoTree)tree.getChild(3), lineStart, lineEnd);
      } else {
         metodo = new SimboloMetodo(identificador, tipo, child1, lineStart, lineEnd);
      }

      Algoritmo.define((Simbolo)metodo);
   }

   public static void corpo(CalangoTree tree) {
      for(int i = 0; i < tree.getChildCount(); ++i) {
         Interpretador.exec((CalangoTree)tree.getChild(i));
      }

   }
}
