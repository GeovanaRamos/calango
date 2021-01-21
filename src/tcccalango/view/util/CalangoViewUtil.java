package tcccalango.view.util;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import tcccalango.util.componentes.CalangoConsole;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.util.settings.ElementoTexto;

public class CalangoViewUtil {
   public static boolean configuraSyntaxWithSettings(CalangoSettings calangoSettings, RSyntaxTextArea syntaxTextArea) {
      if (syntaxTextArea != null) {
         SyntaxScheme scheme = syntaxTextArea.getSyntaxScheme();
         configuraSyntaxStyleElement(scheme.styles[1], calangoSettings.getElementoComentario(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[1], calangoSettings.getElementoComentario(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[2], calangoSettings.getElementoComentario(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[13], calangoSettings.getElementoTipoDado(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[7], calangoSettings.getElementoConstanteNum(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[10], calangoSettings.getElementoConstanteTexto(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[11], calangoSettings.getElementoConstanteTexto(), calangoSettings);
         configuraSyntaxStyleElement(scheme.styles[4], calangoSettings.getElementoPalavraChave(), calangoSettings);
         syntaxTextArea.setForeground(calangoSettings.getElementoTextoGeral().getCor());
         syntaxTextArea.setBackground(calangoSettings.getCorFundoEditor());
         syntaxTextArea.setFont(calangoSettings.getElementoTextoGeral().getFont());
         Map<TextAttribute, Integer> fontAttributes = new HashMap();
         if (calangoSettings.getElementoTextoGeral().isSyleUnderline()) {
            fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         }

         syntaxTextArea.setFont((new Font(calangoSettings.getFonte(), calangoSettings.getElementoTextoGeral().getFont().getStyle(), calangoSettings.getTamanhoFonte())).deriveFont(fontAttributes));
         return true;
      } else {
         return false;
      }
   }

   public static void configuraConsoleWithSettings(CalangoSettings calangoSettings, CalangoConsole tpConsole) {
      tpConsole.setBackground(calangoSettings.getCorFundoConsole());
      tpConsole.setOutputTextColor(calangoSettings.getCorOutputConsole());
      tpConsole.setInputTextColor(calangoSettings.getCorInputConsole());
      tpConsole.setErrorTextColor(calangoSettings.getCorErrorConsole());
      tpConsole.clear();
   }

   public static boolean isMacOS() {
      return System.getProperty("os.name").toUpperCase().contains("MAC");
   }

   private static void configuraSyntaxStyleElement(Style style, ElementoTexto elemento, CalangoSettings calangoSettings) {
      style.foreground = elemento.getCor();
      style.font = new Font(calangoSettings.getFonte(), elemento.getFont().getStyle(), calangoSettings.getTamanhoFonte());
      style.underline = elemento.isSyleUnderline();
   }
}
