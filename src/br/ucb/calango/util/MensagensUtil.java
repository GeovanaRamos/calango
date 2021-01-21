package br.ucb.calango.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MensagensUtil {
   private static final ResourceBundle resources = ResourceBundle.getBundle("Mensagens");

   public static String getMessage(String chave, Object... params) {
      String resource = resources.getString(chave);
      return MessageFormat.format(resource, params);
   }

   public static String getMessage(Class<?> c, Object... params) {
      String resource = resources.getString(c.getSimpleName());
      return MessageFormat.format(resource, params);
   }
}
