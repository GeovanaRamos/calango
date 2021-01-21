package br.ucb.calango.interpretador;

import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.exceptions.erros.NumeroInvalidoException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloVariavel;
import br.ucb.calango.util.AcoesUtil;
import java.lang.reflect.Array;

public class AcoesDeclaracoesVariaveis {
   protected static void declVariavel(CalangoTree tree) {
      for(int i = 1; i < tree.getChildCount(); ++i) {
         StringBuilder tipoDado = new StringBuilder(tree.getChild(0).getText());
         CalangoTree var = tree.getCalangoChild(i);
         Integer arraySize = null;
         Integer matrixSize = null;
         Class tipo;
         boolean isReferencia;
         if (var.getChild(1) != null) {
            if (var.getChild(2) != null) {
               tipo = AcoesUtil.getTypeFromString(tipoDado.append("[][]").toString());
               isReferencia = true;

               try {
                  arraySize = new Integer(var.getChild(1).toString());
               } catch (NumberFormatException var10) {
                  throw new NumeroInvalidoException(var.getChild(1).toString());
               }

               try {
                  matrixSize = new Integer(var.getChild(2).toString());
               } catch (NumberFormatException var9) {
                  throw new NumeroInvalidoException(var.getChild(2).toString());
               }
            } else {
               tipo = AcoesUtil.getTypeFromString(tipoDado.append("[]").toString());
               isReferencia = true;
               arraySize = new Integer(var.getChild(1).toString());
            }
         } else {
            tipo = AcoesUtil.getTypeFromString(tipoDado.toString());
            isReferencia = false;
         }

         SimboloVariavel variavel = new SimboloVariavel(var.getChild(0).getText(), tipo, isReferencia ? "" : null);
         Algoritmo.define((Simbolo)variavel);
         if (isReferencia) {
            if (matrixSize != null) {
               Algoritmo.setValue(variavel, Array.newInstance(variavel.getTipo().getComponentType().getComponentType(), new int[]{arraySize, matrixSize}));
            } else {
               Algoritmo.setValue(variavel, Array.newInstance(variavel.getTipo().getComponentType(), arraySize));
            }
         }
      }

   }

   protected static SimboloVariavel tipoIdentificador(CalangoTree tree) {
      StringBuilder tipoDado = new StringBuilder(tree.getChild(0).getText());
      if (isMatrix(tree)) {
         tipoDado.append("[][]");
      } else if (isArray(tree)) {
         tipoDado.append("[]");
      }

      Class<?> tipo = AcoesUtil.getTypeFromString(tipoDado.toString());
      String identificador = tree.getChild(1).getText();
      boolean isReferencia = tree.parentHasChild(80) || tree.parentHasChild(75) || tree.parentHasChild(74);
      SimboloVariavel variavel = new SimboloVariavel(identificador, tipo, isReferencia ? "" : null);
      return variavel;
   }

   public static SimboloVariavel parametro(CalangoTree tree) {
      SimboloVariavel param = (SimboloVariavel)Interpretador.exec((CalangoTree)tree.getChild(0));
      if (tree.getFirstChildWithType(74) != null) {
         try {
            param.setTamanhoMatriz(new Integer(tree.getChild(2).getText()));
         } catch (NumberFormatException var3) {
            throw new NumeroInvalidoException(tree.getChild(2).getText());
         }
      }

      return param;
   }

   private static boolean isArray(CalangoTree tree) {
      return tree.getParent().getFirstChildWithType(75) != null;
   }

   private static boolean isMatrix(CalangoTree tree) {
      return tree.getParent().getFirstChildWithType(74) != null;
   }
}
