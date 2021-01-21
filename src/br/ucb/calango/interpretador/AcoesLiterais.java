package br.ucb.calango.interpretador;

import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.exceptions.erros.NumeroInvalidoException;
import br.ucb.calango.simbolos.SimboloLiteral;

public class AcoesLiterais {
   protected static SimboloLiteral textoLiteral(CalangoTree tree) {
      return new SimboloLiteral(String.class, tree.getText());
   }

   protected static SimboloLiteral charLiteral(CalangoTree tree) {
      return new SimboloLiteral(Character.class, tree.getText().charAt(0));
   }

   protected static SimboloLiteral inteiroLiteral(CalangoTree tree) {
      try {
         return new SimboloLiteral(Integer.class, new Integer(tree.getText()));
      } catch (NumberFormatException var2) {
         throw new NumeroInvalidoException(tree.getText());
      }
   }

   protected static SimboloLiteral realLiteral(CalangoTree tree) {
      return new SimboloLiteral(Double.class, new Double(tree.getText()));
   }

   protected static SimboloLiteral boolLiteral(CalangoTree tree) {
      return tree.getText().equals("verdadeiro") ? new SimboloLiteral(Boolean.class, true) : new SimboloLiteral(Boolean.class, false);
   }
}
