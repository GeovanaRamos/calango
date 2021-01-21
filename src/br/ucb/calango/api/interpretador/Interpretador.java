package br.ucb.calango.api.interpretador;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.arvore.CalangoTreeAdaptor;
import br.ucb.calango.exceptions.erros.CalangoRuntimeException;
import br.ucb.calango.exceptions.erros.InterrompaPosicaoInvalidaException;
import br.ucb.calango.exceptions.statements.CalangoInterruptionException;
import br.ucb.calango.exceptions.statements.InterrompeStatementException;
import br.ucb.calango.gramatica.Algoritmo;
import br.ucb.calango.gramatica.CalangoGrammarLexer;
import br.ucb.calango.gramatica.CalangoGrammarParser;
import br.ucb.calango.symbolchecker.Erro;
import br.ucb.calango.symbolchecker.SymbolChecker;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class Interpretador {
   public void interpretar(String codigo) {
      this.interpretarInterno(codigo, false);
   }

   public void interpretar(File file) throws IOException {
      this.interpretarInterno(file, false);
   }

   public void depurar(String codigo) {
      this.interpretarInterno(codigo, true);
   }

   public void depurar(File file) throws IOException {
      this.interpretarInterno(file, true);
   }

   private void interpretarInterno(File file, boolean debugger) throws IOException {
      byte[] buffer = new byte[(int)file.length()];
      BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
      f.read(buffer);
      String codigo = new String(buffer);
      f.close();
      this.interpretarInterno(codigo, debugger);
   }

   private void interpretarInterno(String codigo, boolean debugger) {
      try {
         CalangoTree tree = this.createTree(codigo);
         if (tree != null) {
            SymbolChecker checker = new SymbolChecker();
            List<Erro> errors = checker.check(tree);
            if (errors.isEmpty()) {
               Algoritmo.setDebugging(debugger);
               br.ucb.calango.interpretador.Interpretador.exec(tree);
            } else {
               Collections.sort(errors);
               Iterator var7 = errors.iterator();

               while(var7.hasNext()) {
                  Erro error = (Erro)var7.next();
                  CalangoAPI.printErro(error.getLinha(), error.getMensagem());
               }
            }
         }
      } catch (InterrompeStatementException var14) {
         CalangoAPI.printErro(br.ucb.calango.interpretador.Interpretador.getLinha(), (new InterrompaPosicaoInvalidaException()).getMessage());
      } catch (CalangoRuntimeException var15) {
         CalangoAPI.printErro(br.ucb.calango.interpretador.Interpretador.getLinha(), var15.getMessage());
      } catch (CalangoInterruptionException var16) {
      } catch (Exception var17) {
         CalangoAPI.printErro(br.ucb.calango.interpretador.Interpretador.getLinha(), "Exception");
         var17.printStackTrace();
      } finally {
         br.ucb.calango.interpretador.Interpretador.destroy();
      }

   }

   private CalangoTree createTree(String codigo) throws RecognitionException {
      CharStream charStream = new ANTLRStringStream(codigo);
      CalangoGrammarLexer lexer = new CalangoGrammarLexer(charStream);
      TokenStream tokenStream = new CommonTokenStream(lexer);
      CalangoGrammarParser parser = new CalangoGrammarParser(tokenStream);
      parser.setTreeAdaptor(new CalangoTreeAdaptor());
      CalangoGrammarParser.algoritmo_return algoritmo = parser.algoritmo();
      if (!parser.hasErrors() && !lexer.hasErrors()) {
         CommonTreeNodeStream nodeStream = new CommonTreeNodeStream(algoritmo.getTree());
         return (CalangoTree)nodeStream.nextElement();
      } else {
         return null;
      }
   }
}
