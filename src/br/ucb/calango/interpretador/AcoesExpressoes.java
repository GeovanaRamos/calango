package br.ucb.calango.interpretador;

import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.exceptions.erros.ChamaFuncaoInvalidaException;
import br.ucb.calango.exceptions.erros.ComparaTextoTipoException;
import br.ucb.calango.exceptions.erros.ComparacaoTextoException;
import br.ucb.calango.exceptions.erros.ComparacaoTiposIncompativeisException;
import br.ucb.calango.exceptions.erros.DivisaoPorZeroException;
import br.ucb.calango.exceptions.erros.FormatacaoDeTipoNaoRealException;
import br.ucb.calango.exceptions.erros.FormatoInteiroInvalidoException;
import br.ucb.calango.exceptions.erros.FuncaoNecessitaTipoException;
import br.ucb.calango.exceptions.erros.NenhumFormatadorUsadoException;
import br.ucb.calango.exceptions.erros.ParametroAleatorioInvalidoException;
import br.ucb.calango.exceptions.erros.PosicaoInexistenteException;
import br.ucb.calango.exceptions.erros.PrecedenciaNegacaoMatematicaInvalida;
import br.ucb.calango.exceptions.erros.RaizNegativaException;
import br.ucb.calango.exceptions.erros.TamanhoTextoTipoException;
import br.ucb.calango.exceptions.erros.TipoParametroIncompativelException;
import br.ucb.calango.exceptions.erros.TiposIncompativeisEmOperacaoException;
import br.ucb.calango.exceptions.erros.ValorForaDaTabelaAsciiException;
import br.ucb.calango.exceptions.erros.ValoresForaDoLimiteException;
import br.ucb.calango.exceptions.erros.VariavelIndefinidaException;
import br.ucb.calango.exceptions.erros.VariavelNaoIndexadaException;
import br.ucb.calango.exceptions.erros.VetorNaoMatrizException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloLiteral;
import br.ucb.calango.simbolos.SimboloMetodo;
import br.ucb.calango.simbolos.SimboloVariavel;
import java.lang.reflect.Array;
import java.util.Random;

public class AcoesExpressoes {
   protected static SimboloLiteral adicao(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      return execResult1.add(execResult2);
   }

   protected static SimboloLiteral subtracao(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      return execResult1.sub(execResult2);
   }

   protected static SimboloLiteral multiplicacao(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      return execResult1.mult(execResult2);
   }

   protected static SimboloLiteral divisao(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (execResult2.getValor() instanceof Number && ((Number)execResult2.getValor()).doubleValue() == 0.0D) {
         throw new DivisaoPorZeroException();
      } else {
         return execResult1.div(execResult2);
      }
   }

   public static Simbolo divisaoInteiro(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (!execResult1.getTipo().equals(Integer.class)) {
         throw new TiposIncompativeisEmOperacaoException(execResult1.getClass(), Integer.class);
      } else if (!execResult2.getTipo().equals(Integer.class)) {
         throw new TiposIncompativeisEmOperacaoException(execResult1.getClass(), Integer.class);
      } else if ((Integer)execResult2.getValor() == 0) {
         throw new DivisaoPorZeroException();
      } else {
         return execResult1.divInt(execResult2);
      }
   }

   protected static SimboloLiteral mod(CalangoTree tree) {
      Simbolo execResult1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo execResult2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (!execResult1.getTipo().equals(Integer.class)) {
         throw new TiposIncompativeisEmOperacaoException(execResult1.getClass(), Integer.class);
      } else if (!execResult2.getTipo().equals(Integer.class)) {
         throw new TiposIncompativeisEmOperacaoException(execResult1.getClass(), Integer.class);
      } else {
         return execResult1.mod(execResult2);
      }
   }

   protected static SimboloVariavel identificador(CalangoTree tree) {
      Simbolo simbolo = Algoritmo.reference(tree.getText());
      if (simbolo instanceof SimboloVariavel) {
         return (SimboloVariavel)simbolo;
      } else if (simbolo instanceof SimboloMetodo) {
         throw new ChamaFuncaoInvalidaException(tree.getText());
      } else {
         throw new VariavelIndefinidaException(tree.getText());
      }
   }

   public static SimboloVariavel termoVetor(CalangoTree tree) {
      SimboloVariavel var = (SimboloVariavel)Interpretador.exec(tree.getFirstChildWithType(46));
      if (var.isArray()) {
         Simbolo indexResult = Interpretador.exec((CalangoTree)tree.getChild(1));
         Simbolo matrixResult = Interpretador.exec((CalangoTree)tree.getChild(2));
         String identificador = tree.getChild(0).getText();
         Integer index = (Integer)indexResult.getValor(Integer.class);
         SimboloVariavel retorno = null;

         Object valor;
         try {
            valor = Array.get(var.getValor(), index);
         } catch (IndexOutOfBoundsException var12) {
            throw new PosicaoInexistenteException(index, Array.getLength(var.getValor()) - 1);
         }

         if (matrixResult != null) {
            Integer indexMatriz = -1;

            try {
               indexMatriz = (Integer)matrixResult.getValor(Integer.class);
               valor = Array.get(valor, indexMatriz);
            } catch (IndexOutOfBoundsException var10) {
               throw new PosicaoInexistenteException(indexMatriz, Array.getLength(var.getValor()) - 1);
            } catch (IllegalArgumentException var11) {
               throw new VetorNaoMatrizException();
            }

            retorno = new SimboloVariavel(identificador + "[" + index + "][" + indexMatriz + "]", var.getTipo().getComponentType().getComponentType(), var.getReferencia());
            retorno.setValor(valor);
         } else {
            retorno = new SimboloVariavel(identificador + "[" + index + "]", var.getTipo().getComponentType(), var.getReferencia());
            retorno.setValor(valor);
         }

         return retorno;
      } else {
         throw new VariavelNaoIndexadaException(var.getIdentificador());
      }
   }

   public static SimboloLiteral igual(CalangoTree tree) {
      CalangoTree child1 = (CalangoTree)tree.getChild(0);
      CalangoTree child2 = (CalangoTree)tree.getChild(1);
      Simbolo s1 = Interpretador.exec(child1);
      Object exec1;
      if (s1.getTipo().equals(Integer.class)) {
         exec1 = ((Integer)s1.getValor()).doubleValue();
      } else {
         exec1 = s1.getValor();
      }

      Simbolo s2 = Interpretador.exec(child2);
      Object exec2;
      if (s2.getTipo().equals(Integer.class)) {
         exec2 = ((Integer)s2.getValor()).doubleValue();
      } else {
         exec2 = s2.getValor();
      }

      if (!exec1.getClass().equals(String.class) && !exec2.getClass().equals(String.class)) {
         return new SimboloLiteral(Boolean.class, exec1.equals(exec2));
      } else {
         throw new ComparacaoTextoException();
      }
   }

   public static SimboloLiteral diferente(CalangoTree tree) {
      return igual(tree).nao();
   }

   public static SimboloLiteral menor(CalangoTree tree) {
      CalangoTree child1 = (CalangoTree)tree.getChild(0);
      CalangoTree child2 = (CalangoTree)tree.getChild(1);
      Simbolo exec1 = Interpretador.exec(child1);
      Simbolo exec2 = Interpretador.exec(child2);
      boolean comparacao;
      if (exec1.getValor() instanceof Number && exec2.getValor() instanceof Number) {
         comparacao = new Double(exec1.toString()) < new Double(exec2.toString());
      } else {
         if (!exec1.getTipo().equals(Character.class) || !exec2.getTipo().equals(Character.class)) {
            throw new ComparacaoTiposIncompativeisException(new Object[]{exec1.getValor(), exec2.getValor()});
         }

         comparacao = (Character)exec1.getValor() < (Character)exec2.getValor();
      }

      return new SimboloLiteral(Boolean.class, comparacao);
   }

   public static SimboloLiteral menorIgual(CalangoTree tree) {
      CalangoTree child1 = (CalangoTree)tree.getChild(0);
      CalangoTree child2 = (CalangoTree)tree.getChild(1);
      Simbolo exec1 = Interpretador.exec(child1);
      Simbolo exec2 = Interpretador.exec(child2);
      boolean comparacao;
      if (exec1.getValor() instanceof Number && exec2.getValor() instanceof Number) {
         comparacao = new Double(exec1.toString()) <= new Double(exec2.toString());
      } else {
         if (!exec1.getTipo().equals(Character.class) || !exec2.getTipo().equals(Character.class)) {
            throw new ComparacaoTiposIncompativeisException(new Object[]{exec1.getValor(), exec2.getValor()});
         }

         comparacao = (Character)exec1.getValor() <= (Character)exec2.getValor();
      }

      return new SimboloLiteral(Boolean.class, comparacao);
   }

   public static SimboloLiteral maior(CalangoTree tree) {
      return menorIgual(tree).nao();
   }

   public static SimboloLiteral maiorIgual(CalangoTree tree) {
      return menor(tree).nao();
   }

   public static SimboloLiteral ou(CalangoTree tree) {
      CalangoTree child1 = (CalangoTree)tree.getChild(0);
      CalangoTree child2 = (CalangoTree)tree.getChild(1);
      Boolean exec1 = (Boolean)Interpretador.exec(child1).getValor();
      Boolean exec2 = (Boolean)Interpretador.exec(child2).getValor();
      return new SimboloLiteral(Boolean.class, exec1 || exec2);
   }

   public static SimboloLiteral e(CalangoTree tree) {
      CalangoTree child1 = (CalangoTree)tree.getChild(0);
      CalangoTree child2 = (CalangoTree)tree.getChild(1);
      Boolean exec1 = (Boolean)Interpretador.exec(child1).getValor();
      Boolean exec2 = (Boolean)Interpretador.exec(child2).getValor();
      return new SimboloLiteral(Boolean.class, exec1 && exec2);
   }

   public static Simbolo negacaoLogica(CalangoTree tree) {
      return Interpretador.exec((CalangoTree)tree.getChild(0)).nao();
   }

   public static Simbolo negacaoMatematica(CalangoTree tree) {
      if (tree.getChildCount() != 1) {
         throw new PrecedenciaNegacaoMatematicaInvalida();
      } else {
         return Interpretador.exec((CalangoTree)tree.getChild(0)).neg();
      }
   }

   public static Simbolo formataReal(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (!expressao.getTipo().equals(Double.class) && !expressao.getTipo().equals(Integer.class)) {
         throw new FormatacaoDeTipoNaoRealException();
      } else {
         Simbolo formatadorTotal = Interpretador.exec((CalangoTree)tree.getChild(1).getChild(0));
         Simbolo formatadorDecimal = Interpretador.exec((CalangoTree)tree.getChild(2).getChild(0));
         String tamanhoCampo = "";
         if (formatadorTotal != null) {
            tamanhoCampo = ((Integer)formatadorTotal.getValor(Integer.class)).toString();
         }

         String formato = "%" + tamanhoCampo;
         Object valor;
         if (Integer.class.equals(expressao.getTipo())) {
            if (formatadorDecimal != null) {
               throw new FormatoInteiroInvalidoException();
            }

            if (formatadorTotal == null) {
               throw new NenhumFormatadorUsadoException();
            }

            formato = formato + "d";
            valor = expressao.getValor(Integer.class);
         } else {
            if (formatadorDecimal != null) {
               String casasDecimais = ((Integer)formatadorDecimal.getValor(Integer.class)).toString();
               formato = formato + "." + casasDecimais;
            } else if (formatadorTotal == null) {
               throw new NenhumFormatadorUsadoException();
            }

            formato = formato + "f";
            valor = expressao.getValor(Double.class);
         }

         return new SimboloLiteral(String.class, String.format(formato, valor));
      }
   }

   public static SimboloLiteral comparaTexto(CalangoTree tree) {
      Simbolo simbolo1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo simbolo2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (simbolo1.getTipo().equals(String.class) && simbolo2.getTipo().equals(String.class)) {
         String string1 = simbolo1.getValor().toString();
         String string2 = simbolo2.getValor().toString();
         return new SimboloLiteral(Integer.class, string1.compareTo(string2));
      } else {
         throw new ComparaTextoTipoException();
      }
   }

   public static SimboloLiteral tamanhoTexto(CalangoTree tree) {
      Simbolo texto = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (!texto.getTipo().equals(String.class)) {
         throw new TamanhoTextoTipoException();
      } else {
         return new SimboloLiteral(Integer.class, texto.toString().length());
      }
   }

   public static Simbolo absoluto(CalangoTree tree) {
      Simbolo parametro = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (parametro.getTipo().equals(Integer.class)) {
         return new SimboloLiteral(parametro.getTipo(), Math.abs((Integer)parametro.getValor()));
      } else if (parametro.getTipo().equals(Double.class)) {
         return new SimboloLiteral(parametro.getTipo(), Math.abs((Double)parametro.getValor()));
      } else {
         throw new TipoParametroIncompativelException(parametro.getTipo());
      }
   }

   public static Simbolo exponencial(CalangoTree tree) {
      Simbolo parametro1 = Interpretador.exec((CalangoTree)tree.getChild(0));
      Simbolo parametro2 = Interpretador.exec((CalangoTree)tree.getChild(1));
      if (!parametro1.getTipo().equals(String.class) && !parametro1.getTipo().equals(Boolean.class) && !parametro1.getTipo().equals(Character.class)) {
         if (!parametro2.getTipo().equals(String.class) && !parametro2.getTipo().equals(Boolean.class) && !parametro2.getTipo().equals(Character.class)) {
            return new SimboloLiteral(Double.class, Math.pow((Double)parametro1.getValor(Double.class), (Double)parametro2.getValor(Double.class)));
         } else {
            throw new TipoParametroIncompativelException(parametro2.getTipo());
         }
      } else {
         throw new TipoParametroIncompativelException(parametro1.getTipo());
      }
   }

   public static Simbolo pi() {
      return new SimboloLiteral(Double.class, 3.141592653589793D);
   }

   public static Simbolo raizQuadrada(CalangoTree tree) {
      Simbolo parametro = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (!parametro.getTipo().equals(Integer.class) && !parametro.getTipo().equals(Double.class)) {
         throw new TipoParametroIncompativelException(parametro.getTipo());
      } else if ((Double)parametro.getValor(Double.class) < 0.0D) {
         throw new RaizNegativaException();
      } else {
         return new SimboloLiteral(Double.class, Math.sqrt((Double)parametro.getValor(Double.class)));
      }
   }

   public static Simbolo aleatorio(CalangoTree tree) {
      Simbolo parametro = Interpretador.exec((CalangoTree)tree.getChild(0));
      if (!parametro.getTipo().equals(Integer.class)) {
         throw new TipoParametroIncompativelException(parametro.getTipo());
      } else if ((Integer)parametro.getValor(Integer.class) < 1) {
         throw new ParametroAleatorioInvalidoException();
      } else {
         Random rand = new Random();
         return new SimboloLiteral(Integer.class, rand.nextInt((Integer)parametro.getValor(Integer.class)));
      }
   }

   public static Simbolo maiusculo(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(String.class)) {
         throw new FuncaoNecessitaTipoException("maiusculo", String.class);
      } else {
         return new SimboloLiteral(String.class, expressao.getValor().toString().toUpperCase());
      }
   }

   public static Simbolo minusculo(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(String.class)) {
         throw new FuncaoNecessitaTipoException("minusculo", String.class);
      } else {
         return new SimboloLiteral(String.class, expressao.getValor().toString().toLowerCase());
      }
   }

   public static Simbolo copia(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(String.class)) {
         throw new FuncaoNecessitaTipoException("copia", String.class);
      } else {
         String texto = expressao.getValor().toString();
         Integer index1 = (Integer)Interpretador.exec(tree.getCalangoChild(1)).getValor(Integer.class);
         Integer index2 = (Integer)Interpretador.exec(tree.getCalangoChild(2)).getValor(Integer.class);

         try {
            return new SimboloLiteral(String.class, texto.substring(index1, index2 + 1));
         } catch (IndexOutOfBoundsException var6) {
            throw new ValoresForaDoLimiteException(index1, index2, 0, texto.length());
         }
      }
   }

   public static Simbolo maiuscChar(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(Character.class)) {
         throw new FuncaoNecessitaTipoException("maiusculoCaracter", Character.class);
      } else {
         Character caracter = expressao.getValor().toString().charAt(0);
         return new SimboloLiteral(Character.class, Character.toUpperCase(caracter));
      }
   }

   public static Simbolo minuscChar(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(Character.class)) {
         throw new FuncaoNecessitaTipoException("minusculoCaracter", Character.class);
      } else {
         Character caracter = expressao.getValor().toString().charAt(0);
         return new SimboloLiteral(Character.class, Character.toLowerCase(caracter));
      }
   }

   public static Simbolo asciiToChar(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(Integer.class)) {
         throw new FuncaoNecessitaTipoException("asciiCaracter", Integer.class);
      } else {
         int val = (Integer)expressao.getValor();
         if (val >= 0 && val <= 127) {
            Character caracter = (char)val;
            return new SimboloLiteral(Character.class, caracter);
         } else {
            throw new ValorForaDaTabelaAsciiException(val);
         }
      }
   }

   public static Simbolo charToAscii(CalangoTree tree) {
      Simbolo expressao = Interpretador.exec(tree.getCalangoChild(0));
      if (!expressao.getTipo().equals(Character.class)) {
         throw new FuncaoNecessitaTipoException("caracterAscii", Character.class);
      } else {
         int val = (Character)expressao.getValor(Character.class);
         if (val >= 0 && val <= 127) {
            return new SimboloLiteral(Integer.class, Integer.valueOf(val));
         } else {
            throw new ValorForaDaTabelaAsciiException(val);
         }
      }
   }

   public static Simbolo convCharTexto(CalangoTree tree) {
      Simbolo simboloVetor = Interpretador.exec(tree.getCalangoChild(0));
      if (!simboloVetor.getTipo().equals(Character[].class)) {
         throw new FuncaoNecessitaTipoException("convCharTexto", String.class);
      } else {
         Character[] vetor = (Character[])simboloVetor.getValor(Character[].class);
         StringBuilder s = new StringBuilder();
         Character[] var7 = vetor;
         int var6 = vetor.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            Character ch = var7[var5];
            s.append(ch);
         }

         return new SimboloLiteral(Character[].class, s.toString());
      }
   }

   public static Simbolo convTextoChar(CalangoTree tree) {
      Simbolo simboloTexto = Interpretador.exec(tree.getCalangoChild(0));
      if (!simboloTexto.getTipo().equals(String.class)) {
         throw new FuncaoNecessitaTipoException("convTextoChar", String.class);
      } else {
         String texto = (String)simboloTexto.getValor(String.class);
         Character[] cs = new Character[texto.length()];

         for(int i = 0; i < texto.length(); ++i) {
            cs[i] = texto.charAt(i);
         }

         return new SimboloLiteral(Character[].class, cs);
      }
   }

   public static Simbolo charTexto(CalangoTree tree) {
      Simbolo simboloTexto = Interpretador.exec(tree.getCalangoChild(0));
      if (!simboloTexto.getTipo().equals(String.class)) {
         throw new FuncaoNecessitaTipoException("caracterTexto", String.class);
      } else {
         Simbolo simboloPosicao = Interpretador.exec(tree.getCalangoChild(1));
         if (!simboloPosicao.getTipo().equals(Integer.class)) {
            throw new FuncaoNecessitaTipoException("caracterTexto", Integer.class);
         } else {
            String texto = (String)simboloTexto.getValor(String.class);
            Integer posicao = (Integer)simboloPosicao.getValor(Integer.class);
            if (posicao >= 0 && posicao < texto.length()) {
               return new SimboloLiteral(Character.class, texto.charAt(posicao));
            } else {
               throw new PosicaoInexistenteException(posicao, texto.length() - 1);
            }
         }
      }
   }
}
