package br.ucb.calango.symbolchecker;

import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.util.MensagensUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.antlr.runtime.tree.Tree;

public class SymbolChecker {
   private Escopo global = new Escopo();
   private Escopo local = null;
   private List<Erro> erros = new ArrayList();

   public List<Erro> check(CalangoTree tree) {
      int i = 0;

      for(CalangoTree temp = tree.getCalangoChild(i); temp.getType() == 44 || temp.getType() == 78; temp = tree.getCalangoChild(i)) {
         Tree token = temp.getFirstChildWithType(46);
         String ident = token.getText();
         if (!this.define(ident, true)) {
            this.erros.add(new Erro(token.getLine(), MensagensUtil.getMessage("VariavelRepetida", ident)));
         }

         ++i;
      }

      return this.checkRecursively(tree);
   }

   private List<Erro> checkRecursively(CalangoTree tree) {
      if (tree != null) {
         List<CalangoTree> children = tree.getChildren();
         CalangoTree subtree;
         if (children != null) {
            label44:
            for(Iterator var4 = children.iterator(); var4.hasNext(); this.checkRecursively(subtree)) {
               subtree = (CalangoTree)var4.next();
               switch(subtree.getType()) {
               case 26:
                  int i = 1;

                  while(true) {
                     if (i >= subtree.getChildCount()) {
                        continue label44;
                     }

                     Tree token = subtree.getChild(i).getChild(0);
                     String ident = token.getText();
                     if (!this.define(ident, false)) {
                        this.erros.add(new Erro(token.getLine(), MensagensUtil.getMessage("VariavelRepetida", ident)));
                     }

                     ++i;
                  }
               case 44:
               case 78:
                  this.local = new Escopo();
                  break;
               case 46:
                  String ident = subtree.getText();
                  if (!this.isDefined(ident)) {
                     this.erros.add(new Erro(subtree.getLine(), MensagensUtil.getMessage("VariavelIndefinida", ident)));
                  } else if (subtree.getParent().getType() == 13 && !this.global.isDefined(ident)) {
                     this.erros.add(new Erro(subtree.getLine(), MensagensUtil.getMessage("ChamadaVariavel", ident)));
                  }
                  break;
               case 71:
                  Tree token = ((CalangoTree)subtree.getChild(0)).getFirstChildWithType(46);
                  ident = token.getText();
                  if (!this.define(ident, false)) {
                     this.erros.add(new Erro(token.getLine(), MensagensUtil.getMessage("VariavelRepetida", ident)));
                  }
                  break;
               case 77:
                  this.local = new Escopo();
               }
            }
         }
      }

      return this.erros;
   }

   private boolean define(String simbolo, boolean isGlobal) {
      if (this.isDefined(simbolo)) {
         return false;
      } else {
         if (isGlobal) {
            this.global.define(simbolo);
         } else {
            this.local.define(simbolo);
         }

         return true;
      }
   }

   private boolean isDefined(String simbolo) {
      if (this.global.isDefined(simbolo)) {
         return true;
      } else {
         return this.local != null ? this.local.isDefined(simbolo) : false;
      }
   }
}
