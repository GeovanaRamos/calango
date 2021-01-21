package br.ucb.calango.interpretador;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.escopo.Escopo;
import br.ucb.calango.exceptions.erros.ArgumentoIncompativelEmMatrizException;
import br.ucb.calango.exceptions.erros.ArgumentoIncompativelEmVetorException;
import br.ucb.calango.exceptions.erros.AtribuicaoMetodoException;
import br.ucb.calango.exceptions.erros.AtribuicaoRetornoProcedimentoException;
import br.ucb.calango.exceptions.erros.ContadorIgualValorFinalException;
import br.ucb.calango.exceptions.erros.ExpressaoIncompativelException;
import br.ucb.calango.exceptions.erros.FuncaoNaoRetornouException;
import br.ucb.calango.exceptions.erros.LeiaCaracterException;
import br.ucb.calango.exceptions.erros.LeiaCaracterTipoException;
import br.ucb.calango.exceptions.erros.LeiaLiteralException;
import br.ucb.calango.exceptions.erros.LeiaLogicoException;
import br.ucb.calango.exceptions.erros.LeiaVetorNaoIndexadoException;
import br.ucb.calango.exceptions.erros.LiteralPorReferenciaException;
import br.ucb.calango.exceptions.erros.MetodoInexistenteException;
import br.ucb.calango.exceptions.erros.NumberoDeParametrosIncorreto;
import br.ucb.calango.exceptions.erros.ParametroIncompativelException;
import br.ucb.calango.exceptions.erros.PassoZeroException;
import br.ucb.calango.exceptions.erros.PosicaoInexistenteException;
import br.ucb.calango.exceptions.erros.ProcedimentoRetornandoException;
import br.ucb.calango.exceptions.erros.RetornoTiposIncompativeisException;
import br.ucb.calango.exceptions.erros.TamanhoMatrizIncompativelException;
import br.ucb.calango.exceptions.erros.TiposIncompativeisException;
import br.ucb.calango.exceptions.erros.TiposParametrosIncompativeisException;
import br.ucb.calango.exceptions.erros.VariavelNaoIndexadaException;
import br.ucb.calango.exceptions.erros.VetorNaoMatrizException;
import br.ucb.calango.exceptions.statements.InterrompeStatementException;
import br.ucb.calango.exceptions.statements.RetornaValueException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloLiteral;
import br.ucb.calango.simbolos.SimboloMetodo;
import br.ucb.calango.simbolos.SimboloVariavel;
import br.ucb.calango.util.AcoesUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AcoesStatements {
   public static void statements(CalangoTree tree) {
      List<CalangoTree> children = tree.getChildren();
      Iterator var3 = children.iterator();

      while(var3.hasNext()) {
         CalangoTree statement = (CalangoTree)var3.next();
         Interpretador.exec(statement);
      }

   }

   protected static void escreva(CalangoTree tree) {
      StringBuilder printString = new StringBuilder();
      Iterator var3 = tree.getChildren().iterator();

      while(var3.hasNext()) {
         CalangoTree child = (CalangoTree)var3.next();
         printString.append(Interpretador.exec(child).toString());
      }

      CalangoAPI.print(printString.toString());
   }

   protected static void escreval(CalangoTree tree) {
      escreva(tree);
      CalangoAPI.novaLinha();
   }

   protected static void atribuicao(CalangoTree tree) {
      Integer posicao = 0;
      Integer posicaoMatriz = null;

      SimboloVariavel var;
      try {
         var = (SimboloVariavel)Interpretador.exec((CalangoTree)tree.getChild(0));
      } catch (ClassCastException var8) {
         throw new AtribuicaoMetodoException(tree.getChild(0).getText());
      }

      Simbolo atribuicao = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (AcoesStatements.RetornoProcedimento.class.equals(atribuicao.getTipo())) {
         throw new AtribuicaoRetornoProcedimentoException();
      } else {
         Object valor = atribuicao.getValor();
         if (tree.getChild(2) == null) {
            Algoritmo.setValue(var, valor);
         } else {
            try {
               if (!var.isArray()) {
                  throw new VariavelNaoIndexadaException(var.getIdentificador());
               }

               posicao = (Integer)Interpretador.exec((CalangoTree)tree.getChild(2)).getValor(Integer.class);
               if (tree.getChild(3) != null) {
                  posicaoMatriz = (Integer)Interpretador.exec((CalangoTree)tree.getChild(3)).getValor(Integer.class);
               }

               atribuirValorAVetor(var, posicao, posicaoMatriz, valor);
            } catch (IndexOutOfBoundsException var7) {
               throw new PosicaoInexistenteException(posicao, Array.getLength(var.getValor()) - 1);
            }
         }

      }
   }

   protected static void se(CalangoTree tree) {
      CalangoTree expressao = (CalangoTree)tree.getChild(0);

      Boolean expressaoIsTrue;
      try {
         expressaoIsTrue = (Boolean)Interpretador.exec(expressao).getValor();
      } catch (ClassCastException var5) {
         throw new ExpressaoIncompativelException(Boolean.class);
      }

      if (expressaoIsTrue) {
         Interpretador.exec((CalangoTree)tree.getChild(1));
      } else {
         for(int i = 2; i < tree.getChildCount(); ++i) {
            CalangoTree child = (CalangoTree)tree.getChild(i);
            if (child.isType(85)) {
               expressaoIsTrue = (Boolean)Interpretador.exec((CalangoTree)child.getChild(0)).getValor();
               if (expressaoIsTrue) {
                  Interpretador.exec((CalangoTree)child.getChild(1));
                  break;
               }
            } else {
               Interpretador.exec((CalangoTree)child.getChild(0));
            }
         }
      }

   }

   protected static void escolha(CalangoTree tree) {
      CalangoTree casos = (CalangoTree)tree.getChild(1);

      try {
         Interpretador.exec(casos);
         CalangoTree outrocaso = (CalangoTree)tree.getChild(2);
         if (outrocaso != null) {
            Interpretador.exec(outrocaso);
         }
      } catch (InterrompeStatementException var3) {
      }

   }

   public static void casos(CalangoTree tree) {
      SimboloVariavel valorComparacao = getValorEscolha(tree);
      boolean executarCaso = false;

      for(int i = 0; i < tree.getChildCount(); ++i) {
         if (executarCaso = executarCaso || casoMatches((CalangoTree)tree.getChild(i), valorComparacao)) {
            Interpretador.exec((CalangoTree)tree.getChild(i));
         }
      }

   }

   public static void caso(CalangoTree tree) {
      Interpretador.exec((CalangoTree)tree.getChild(1));
   }

   public static void outrocaso(CalangoTree tree) {
      Interpretador.exec((CalangoTree)tree.getChild(0));
   }

   public static void interrompa(CalangoTree tree) {
      throw new InterrompeStatementException();
   }

   public static void leia(CalangoTree tree) {
      try {
         for(int i = 0; i < tree.getChildCount(); ++i) {
            CalangoTree firstChild = (CalangoTree)tree.getChild(i);
            SimboloVariavel var = (SimboloVariavel)Interpretador.exec(firstChild);
            if (var.getTipo().equals(Character.class)) {
               throw new LeiaCaracterException();
            }

            if (var.getTipo().equals(Boolean.class)) {
               throw new LeiaLogicoException();
            }

            if ((var.isMatrix() || var.isArray()) && firstChild.getType() != 91) {
               throw new LeiaVetorNaoIndexadoException();
            }

            String input = CalangoAPI.read();
            if (firstChild.getType() == 91) {
               var.setValor(AcoesUtil.getTypedValue(input, var.getTipo()));
               SimboloVariavel vetor = (SimboloVariavel)Interpretador.exec((CalangoTree)firstChild.getChild(0));
               Integer posicao = (Integer)Interpretador.exec((CalangoTree)firstChild.getChild(1)).getValor(Integer.class);
               Integer posicaoMatriz = null;
               if (firstChild.getChild(2) != null) {
                  posicaoMatriz = (Integer)Interpretador.exec((CalangoTree)firstChild.getChild(2)).getValor(Integer.class);
               }

               atribuirValorAVetor(vetor, posicao, posicaoMatriz, var.getValor());
            } else {
               Algoritmo.setValue(var, AcoesUtil.getTypedValue(input, var.getTipo()));
            }
         }

      } catch (ClassCastException var8) {
         throw new LeiaLiteralException();
      }
   }

   public static void leiaCaracter(CalangoTree tree) {
      CalangoTree firstChild = (CalangoTree)tree.getChild(0);
      Simbolo var = Interpretador.exec(firstChild);
      if (!var.getTipo().equals(Character.class)) {
         throw new LeiaCaracterTipoException();
      } else {
         Character input = CalangoAPI.readChar();
         if (firstChild.getType() == 91) {
            var.setValor((Object)input);
            SimboloVariavel vetor = (SimboloVariavel)Interpretador.exec((CalangoTree)firstChild.getChild(0));
            Integer posicao = (Integer)Interpretador.exec((CalangoTree)firstChild.getChild(1)).getValor(Integer.class);
            Integer posicaoMatriz = null;
            if (firstChild.getChild(2) != null) {
               posicaoMatriz = (Integer)Interpretador.exec((CalangoTree)firstChild.getChild(2)).getValor(Integer.class);
            }

            atribuirValorAVetor(vetor, posicao, posicaoMatriz, var.getValor());
         } else {
            Algoritmo.setValue((SimboloVariavel)var, input);
         }

      }
   }

   public static void para(CalangoTree tree) {
      SimboloVariavel var = (SimboloVariavel)Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo sfim = Interpretador.exec((CalangoTree)tree.getChild(2));
      if (var.equals(sfim)) {
         throw new ContadorIgualValorFinalException();
      } else {
         Integer inicio = (Integer)Interpretador.exec((CalangoTree)tree.getChild(1)).getValor(Integer.class);
         Integer fim = (Integer)sfim.getValor(Integer.class);
         Integer passo = (Integer)Interpretador.exec((CalangoTree)tree.getChild(3)).getValor(Integer.class);
         int i = inicio;
         if (passo == 0) {
            throw new PassoZeroException();
         } else {
            AcoesStatements.Comparacao condicao;
            if (passo > 0) {
               condicao = new AcoesStatements.Comparacao() {
                  public boolean comparacao(int i, int fim) {
                     return i <= fim;
                  }
               };
            } else {
               condicao = new AcoesStatements.Comparacao() {
                  public boolean comparacao(int i, int fim) {
                     return i >= fim;
                  }
               };
            }

            for(i = inicio; condicao.comparacao(i, fim); i += passo) {
               var.destravar();
               Algoritmo.setValue(var, i);
               var.travar();

               try {
                  Interpretador.exec((CalangoTree)tree.getChild(4));
               } catch (InterrompeStatementException var9) {
                  break;
               }

               CalangoAPI.passo("para", tree.getLine());
            }

            var.destravar();
            Algoritmo.setValue(var, i);
         }
      }
   }

   public static void enquantoStm(CalangoTree tree) {
      CalangoTree condicao = (CalangoTree)tree.getChild(0);

      while((Boolean)Interpretador.exec(condicao).getValor()) {
         try {
            Interpretador.exec((CalangoTree)tree.getChild(1));
         } catch (InterrompeStatementException var3) {
            break;
         }
      }

   }

   public static void faca(CalangoTree tree) {
      List<CalangoTree> children = tree.getChildren();
      boolean interrompe = false;

      do {
         Iterator var4 = children.iterator();

         while(var4.hasNext()) {
            CalangoTree statement = (CalangoTree)var4.next();

            try {
               Interpretador.exec(statement);
            } catch (InterrompeStatementException var6) {
               interrompe = true;
               break;
            }
         }
      } while(!interrompe);

   }

   public static void facaEnquanto(CalangoTree tree) {
      Boolean condicaoValida = (Boolean)Interpretador.exec((CalangoTree)tree.getChild(0)).getValor();
      if (!condicaoValida) {
         throw new InterrompeStatementException();
      }
   }

   public static SimboloLiteral chamadaMetodo(CalangoTree tree) {
      HashMap<String, Simbolo> referencias = new HashMap();
      SimboloMetodo metodo = (SimboloMetodo)Algoritmo.reference(tree.getChild(0).getText());
      if (metodo == null) {
         throw new MetodoInexistenteException(tree.getChild(0).getText());
      } else {
         List<SimboloVariavel> params = adicionaParametrosAosSimbolos((CalangoTree)tree.getChild(1), metodo.getParametros(), referencias);
         Algoritmo.pushEscopo(new Escopo(metodo.getIdentificador()));
         CalangoAPI.passo("INICIO SUBPROGRAMA", metodo.getLineStart());
         Algoritmo.define(params);

         label151: {
            SimboloLiteral var7;
            try {
               RetornaValueException e;
               try {
                  Interpretador.exec(metodo.getStatements());
                  break label151;
               } catch (RetornaValueException var19) {
                  e = var19;
                  if (metodo.isProcedimento()) {
                     throw new ProcedimentoRetornandoException();
                  }
               }

               try {
                  var7 = new SimboloLiteral(metodo.getTipo(), e.getValue().getValor(metodo.getTipo()));
               } catch (TiposIncompativeisException var18) {
                  throw new RetornoTiposIncompativeisException();
               }
            } finally {
               Iterator var9 = referencias.keySet().iterator();

               while(true) {
                  if (!var9.hasNext()) {
                     CalangoAPI.passo("FIM SUBPROGRAMA", metodo.getLineEnd());
                     Algoritmo.popEscopo();
                  } else {
                     String id = (String)var9.next();
                     if (id.contains("-")) {
                        Simbolo original = (Simbolo)referencias.get(id);
                        String[] nameIndex = id.split("-");
                        Integer index = new Integer(nameIndex[1]);
                        Simbolo newValue = Algoritmo.reference(nameIndex[0]);
                        if (nameIndex.length == 3) {
                           Integer indexMatriz = new Integer(nameIndex[2]);
                           Array.set(Array.get(original.getValor(), index), indexMatriz, newValue.getValor());
                        } else {
                           Array.set(original.getValor(), index, newValue.getValor());
                        }
                     } else {
                        SimboloVariavel original = (SimboloVariavel)referencias.get(id);
                        original.setValor(Algoritmo.reference(id).getValorUnchecked());
                     }
                  }
               }
            }

         }

      }
   }

   public static void retorna(CalangoTree tree) {
      Simbolo returnValue = Interpretador.exec((CalangoTree)tree.getChild(0));
      throw new RetornaValueException(returnValue);
   }

   public static void limpatela() {
      CalangoAPI.limpaTela();
   }

   private static List<SimboloVariavel> adicionaParametrosAosSimbolos(CalangoTree parametrosArvore, List<SimboloVariavel> parametrosParam, HashMap<String, Simbolo> referencias) {
      List<SimboloVariavel> parametros = null;
      int quantidadePassada = parametrosArvore == null ? 0 : parametrosArvore.getChildCount();
      int quantidadeReal = parametrosParam.size();
      if (quantidadePassada != quantidadeReal) {
         throw new NumberoDeParametrosIncorreto(quantidadeReal, quantidadePassada);
      } else {
         parametros = new ArrayList();
         Iterator var7 = parametrosParam.iterator();

         while(var7.hasNext()) {
            SimboloVariavel param = (SimboloVariavel)var7.next();
            parametros.add(new SimboloVariavel(param.getIdentificador(), param.getTipo(), param.getReferencia(), param.getTamanhoMatriz()));
         }

         for(int i = 0; i < quantidadeReal; ++i) {
            if (((SimboloVariavel)parametros.get(i)).isReferencia()) {
               try {
                  CalangoTree termoVetor = parametrosArvore.getCalangoChild(i);
                  SimboloVariavel vetor;
                  if (termoVetor.getType() == 91) {
                     vetor = (SimboloVariavel)Interpretador.exec((CalangoTree)termoVetor.getChild(0));
                     String posicao = Interpretador.exec((CalangoTree)termoVetor.getChild(1)).toString();
                     String posicaoMatriz = null;
                     if (termoVetor.getChild(2) != null) {
                        posicaoMatriz = Interpretador.exec((CalangoTree)termoVetor.getChild(2)).toString();
                     }

                     if (posicaoMatriz != null) {
                        referencias.put(((SimboloVariavel)parametros.get(i)).getIdentificador() + "-" + posicao + "-" + posicaoMatriz, vetor);
                     } else {
                        referencias.put(((SimboloVariavel)parametros.get(i)).getIdentificador() + "-" + posicao, vetor);
                     }
                  } else {
                     vetor = (SimboloVariavel)Interpretador.exec((CalangoTree)parametrosArvore.getChild(i));
                     if (vetor.isMatrix()) {
                        Integer tamanhoParametroPassado = Array.getLength(Array.get(vetor.getValor(), 0));
                        Integer tamanhoParametroFormal = ((SimboloVariavel)parametros.get(i)).getTamanhoMatriz();
                        if (!tamanhoParametroPassado.equals(tamanhoParametroFormal)) {
                           throw new TamanhoMatrizIncompativelException(tamanhoParametroFormal, tamanhoParametroPassado);
                        }
                     }

                     referencias.put(((SimboloVariavel)parametros.get(i)).getIdentificador(), vetor);
                  }
               } catch (ClassCastException var11) {
                  throw new LiteralPorReferenciaException();
               }
            }

            Simbolo valor = Interpretador.exec((CalangoTree)parametrosArvore.getChild(i));
            if (((SimboloVariavel)parametros.get(i)).isReferencia()) {
               ((SimboloVariavel)parametros.get(i)).setReferencia(valor.getIdentificador());
            }

            if (!valor.isCompativel((Simbolo)parametros.get(i))) {
               if (((SimboloVariavel)parametros.get(i)).isArray()) {
                  throw new ArgumentoIncompativelEmVetorException();
               }

               if (((SimboloVariavel)parametros.get(i)).isMatrix()) {
                  throw new ArgumentoIncompativelEmMatrizException();
               }

               throw new ParametroIncompativelException();
            }

            try {
               ((SimboloVariavel)parametros.get(i)).setValor(valor.getValorUnchecked());
            } catch (TiposIncompativeisException var12) {
               throw new TiposParametrosIncompativeisException();
            }
         }

         return parametros;
      }
   }

   private static boolean casoMatches(CalangoTree tree, SimboloVariavel valorComparacao) {
      Simbolo value = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (!value.getTipo().equals(valorComparacao.getTipo())) {
         throw new TiposIncompativeisException(valorComparacao.getTipo(), value.getTipo());
      } else {
         return value.getValor().equals(valorComparacao.getValor());
      }
   }

   private static SimboloVariavel getValorEscolha(CalangoTree tree) {
      CalangoTree ancestor = tree.getAncestor(34);
      CalangoTree valorComparacaoTree = (CalangoTree)ancestor.getChild(0);
      return (SimboloVariavel)Interpretador.exec(valorComparacaoTree);
   }

   private static void atribuirValorAVetor(Simbolo vetor, Integer posicao, Integer posicaoMatriz, Object valor) {
      Object vetorAtribuicao = null;
      if (posicaoMatriz != null) {
         Object linha = Array.get(vetor.getValor(), posicao);
         posicao = posicaoMatriz;
         if (linha != null && linha.getClass().isArray()) {
            vetorAtribuicao = linha;
         }
      } else {
         vetorAtribuicao = vetor.getValor();
      }

      if (vetorAtribuicao == null) {
         throw new VetorNaoMatrizException();
      } else {
         Class<?> tipoMatriz = vetorAtribuicao.getClass().getComponentType();
         Class<?> tipoValor = valor.getClass();
         if (tipoMatriz.equals(tipoValor)) {
            Array.set(vetorAtribuicao, posicao, valor);
         } else if (tipoMatriz.equals(Double.class) && tipoValor.equals(Integer.class)) {
            Array.set(vetorAtribuicao, posicao, ((Integer)valor).doubleValue());
         } else {
            if (!tipoMatriz.equals(Integer.class) || !tipoValor.equals(Double.class)) {
               throw new TiposIncompativeisException(tipoMatriz, tipoValor);
            }

            Array.set(vetorAtribuicao, posicao, ((Double)valor).intValue());
         }

         Algoritmo.setValue((SimboloVariavel)vetor, vetor.getValor());
      }
   }

   interface Comparacao {
      boolean comparacao(int var1, int var2);
   }

   static class RetornoProcedimento {
   }
}
