package br.ucb.calango.util;

import br.ucb.calango.gramatica.CalangoGrammarParser;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.UnwantedTokenException;

public class ErrosUtil {
   public static Object[] getMessageParams(RecognitionException e) {
      String[] tokenNames = CalangoGrammarParser.tokenNames;
      if (e instanceof MissingTokenException) {
         MissingTokenException missing = (MissingTokenException)e;
         int expecting = missing.expecting;
         return new Object[]{getTokenName(expecting, tokenNames)};
      } else if (e instanceof NoViableAltException) {
         NoViableAltException noViable = (NoViableAltException)e;
         return noViable.token != null ? new Object[]{noViable.token.getText()} : new Object[]{(char)noViable.c};
      } else if (e instanceof UnwantedTokenException) {
         UnwantedTokenException unwanted = (UnwantedTokenException)e;
         return unwanted.token != null ? new Object[]{getTokenName(unwanted.token.getText())} : new Object[]{(char)unwanted.c};
      } else if (!(e instanceof MismatchedTokenException)) {
         if (e instanceof MismatchedSetException) {
            MismatchedSetException mismatched = (MismatchedSetException)e;
            return new Object[]{mismatched.token.getText()};
         } else {
            return null;
         }
      } else {
         MismatchedTokenException mismatched = (MismatchedTokenException)e;
         Object[] returnObject = (Object[])null;
         if (mismatched.token != null && mismatched.expecting >= 0) {
            int expecting = mismatched.expecting;
            String found = getTokenName(mismatched.token.getText());
            returnObject = new Object[]{found, getTokenName(expecting, tokenNames)};
         } else if (mismatched.expecting >= 0) {
            char found = (char)mismatched.c;
            char expecting = (char)mismatched.expecting;
            returnObject = new Object[]{found, expecting};
         }

         return returnObject;
      }
   }

   private static String getTokenName(int token, String[] tokenNames) {
      return token < 0 ? "FIM DO ARQUIVO" : tokenNames[token];
   }

   private static String getTokenName(String token) {
      return token.equalsIgnoreCase("<EOF>") ? "FIM DO ARQUIVO" : token;
   }
}
