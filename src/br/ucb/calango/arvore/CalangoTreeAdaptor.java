package br.ucb.calango.arvore;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class CalangoTreeAdaptor extends CommonTreeAdaptor {
   public Object create(Token payload) {
      return new CalangoTree(payload);
   }
}
