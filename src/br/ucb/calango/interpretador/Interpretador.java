package br.ucb.calango.interpretador;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.exceptions.erros.EstouroDePilhaException;
import br.ucb.calango.exceptions.statements.InterrompeStatementException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.simbolos.Simbolo;

public class Interpretador {
   private static int linha = 0;
   private static int posChar = 0;

   public static int getLinha() {
      return linha;
   }

   public static int getPosChar() {
      return posChar;
   }

   public static Simbolo exec(CalangoTree tree) throws InterrompeStatementException {
      if (tree != null) {
         Interpretador.linha = tree.getLine();
         posChar = tree.getCharPositionInLine();

         try {
            switch(tree.getType()) {
            case 4:
               return AcoesExpressoes.absoluto(tree);
            case 5:
               return AcoesExpressoes.adicao(tree);
            case 6:
               return AcoesExpressoes.aleatorio(tree);
            case 7:
               AcoesTopLevel.algoritmo(tree);
               break;
            case 8:
               return AcoesExpressoes.asciiToChar(tree);
            case 9:
               passo("atribuicao");
               AcoesStatements.atribuicao(tree);
               break;
            case 10:
               return AcoesLiterais.boolLiteral(tree);
            case 11:
               passo("caso");
               AcoesStatements.caso(tree);
               break;
            case 12:
               AcoesStatements.casos(tree);
               break;
            case 13:
               passo("chamada");
               int linha = getLinha();
               Simbolo s = AcoesStatements.chamadaMetodo(tree);
               Interpretador.linha = linha;
               passo("fim chamada");
               return s;
            case 14:
               return AcoesExpressoes.charToAscii(tree);
            case 15:
               return AcoesLiterais.charLiteral(tree);
            case 16:
               return AcoesExpressoes.charTexto(tree);
            case 17:
            case 18:
            case 20:
            case 25:
            case 28:
            case 39:
            case 40:
            case 42:
            case 43:
            case 45:
            case 52:
            case 72:
            case 73:
            case 74:
            case 75:
            case 80:
            case 84:
            case 85:
            case 86:
            case 87:
            case 93:
            default:
               if (tree.getType() != -1) {
                  System.err.println("Nenhuma ação correspondente para " + tree.getToken().getText());
                  System.err.println(tree.toStringTree());
               }
               break;
            case 19:
               return AcoesExpressoes.comparaTexto(tree);
            case 21:
               return AcoesExpressoes.convCharTexto(tree);
            case 22:
               return AcoesExpressoes.convTextoChar(tree);
            case 23:
               return AcoesExpressoes.copia(tree);
            case 24:
               AcoesTopLevel.corpo(tree);
               break;
            case 26:
               passo("declaracao");
               AcoesDeclaracoesVariaveis.declVariavel(tree);
               break;
            case 27:
               return AcoesExpressoes.diferente(tree);
            case 29:
               return AcoesExpressoes.divisao(tree);
            case 30:
               return AcoesExpressoes.divisaoInteiro(tree);
            case 31:
               return AcoesExpressoes.e(tree);
            case 32:
               passo("enquanto");
               AcoesStatements.facaEnquanto(tree);
               break;
            case 33:
               passo("enquantoStm");
               AcoesStatements.enquantoStm(tree);
               break;
            case 34:
               passo("escolha");
               AcoesStatements.escolha(tree);
               break;
            case 35:
               passo("escreva");
               AcoesStatements.escreva(tree);
               break;
            case 36:
               passo("escreval");
               AcoesStatements.escreval(tree);
               break;
            case 37:
               return AcoesExpressoes.exponencial(tree);
            case 38:
               passo("faca");
               AcoesStatements.faca(tree);
               break;
            case 41:
               return AcoesExpressoes.formataReal(tree);
            case 44:
               AcoesTopLevel.funcao(tree);
               break;
            case 46:
               return AcoesExpressoes.identificador(tree);
            case 47:
               return AcoesExpressoes.igual(tree);
            case 48:
               return AcoesLiterais.inteiroLiteral(tree);
            case 49:
               passo("interrompa");
               AcoesStatements.interrompa(tree);
               break;
            case 50:
               passo("leia");
               AcoesStatements.leia(tree);
               break;
            case 51:
               passo("leiaCaracter");
               AcoesStatements.leiaCaracter(tree);
               break;
            case 53:
               passo("limpaTela");
               AcoesStatements.limpatela();
               break;
            case 54:
               return AcoesExpressoes.maior(tree);
            case 55:
               return AcoesExpressoes.maiorIgual(tree);
            case 56:
               return AcoesExpressoes.maiusculo(tree);
            case 57:
               return AcoesExpressoes.maiuscChar(tree);
            case 58:
               return AcoesExpressoes.menor(tree);
            case 59:
               return AcoesExpressoes.menorIgual(tree);
            case 60:
               return AcoesExpressoes.minusculo(tree);
            case 61:
               return AcoesExpressoes.minuscChar(tree);
            case 62:
            case 63:
               return AcoesExpressoes.mod(tree);
            case 64:
               return AcoesExpressoes.multiplicacao(tree);
            case 65:
            case 66:
               return AcoesExpressoes.negacaoLogica(tree);
            case 67:
               return AcoesExpressoes.negacaoMatematica(tree);
            case 68:
               return AcoesExpressoes.ou(tree);
            case 69:
               passo("outrocaso");
               AcoesStatements.outrocaso(tree);
               break;
            case 70:
               passo("para");
               AcoesStatements.para(tree);
               break;
            case 71:
               return AcoesDeclaracoesVariaveis.parametro(tree);
            case 76:
               return AcoesExpressoes.pi();
            case 77:
               AcoesTopLevel.principal(tree);
               break;
            case 78:
               AcoesTopLevel.procedimento(tree);
               break;
            case 79:
               return AcoesLiterais.realLiteral(tree);
            case 81:
               passo("retorna");
               AcoesStatements.retorna(tree);
               break;
            case 82:
               return AcoesExpressoes.raizQuadrada(tree);
            case 83:
               passo("se");
               AcoesStatements.se(tree);
               break;
            case 88:
               AcoesStatements.statements(tree);
               break;
            case 89:
               return AcoesExpressoes.subtracao(tree);
            case 90:
               return AcoesExpressoes.tamanhoTexto(tree);
            case 91:
               return AcoesExpressoes.termoVetor(tree);
            case 92:
               return AcoesLiterais.textoLiteral(tree);
            case 94:
               return AcoesDeclaracoesVariaveis.tipoIdentificador(tree);
            }
         } catch (StackOverflowError var3) {
            throw new EstouroDePilhaException();
         }
      }

      return null;
   }

   public static void destroy() {
      Algoritmo.setDebugging(false);
      Algoritmo.destroy();
   }

   private static void passo(String acao) {
      CalangoAPI.passo(acao, getLinha());
   }
}
