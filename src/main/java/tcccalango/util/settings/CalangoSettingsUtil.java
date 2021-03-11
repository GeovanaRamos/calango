package tcccalango.util.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;

public class CalangoSettingsUtil {
   public static void copyCalangoSettings(CalangoSettings from, CalangoSettings to) {
      to.setCorFundoEditor(from.getCorFundoEditor());
      to.setDocumentosRecentes(from.getDocumentosRecentes());
      to.setFonte(from.getFonte());
      to.setTamanhoFonte(from.getTamanhoFonte());
      to.setElementoComentario(createElementoTextoCopy(from.getElementoComentario()));
      to.setElementoConstanteNum(createElementoTextoCopy(from.getElementoConstanteNum()));
      to.setElementoConstanteTexto(createElementoTextoCopy(from.getElementoConstanteTexto()));
      to.setElementoPalavraChave(createElementoTextoCopy(from.getElementoPalavraChave()));
      to.setElementoTextoGeral(createElementoTextoCopy(from.getElementoTextoGeral()));
      to.setElementoTipoDado(createElementoTextoCopy(from.getElementoTipoDado()));
      to.setCorFundoConsole(from.getCorFundoConsole());
      to.setCorOutputConsole(from.getCorOutputConsole());
      to.setCorInputConsole(from.getCorInputConsole());
      to.setCorErrorConsole(from.getCorErrorConsole());
      to.setWindowBounds(from.getWindowBounds());
      to.setConsoleEmbutida(from.isConsoleEmbutida());
      to.setTextoSintese(from.getTextoSintese());
      to.setTextoSecaoComandos(from.getTextoSecaoComandos());
   }

   private static ElementoTexto createElementoTextoCopy(ElementoTexto from) {
      ElementoTexto copy = new ElementoTexto();
      copy.setCor(from.getCor());
      copy.setFont(from.getFont());
      copy.setSyleUnderline(from.isSyleUnderline());
      return copy;
   }

   public static CalangoSettings obtemConfiguracoesDefault(Color defaultBackground, Color defaultForeground, SyntaxScheme scheme) {
      CalangoSettings cs = new CalangoSettings();
      cs.setFonte("monospaced");
      cs.setTamanhoFonte(12);
      cs.setElementoTipoDado(criaElementoTexto(scheme.styles[13], cs));
      cs.setElementoComentario(criaElementoTexto(scheme.styles[1], cs));
      cs.setElementoConstanteNum(criaElementoTexto(scheme.styles[7], cs));
      cs.setElementoConstanteTexto(criaElementoTexto(scheme.styles[10], cs));
      cs.setElementoPalavraChave(criaElementoTexto(scheme.styles[4], cs));
      cs.setElementoTextoGeral(new ElementoTexto(defaultForeground, new Font(cs.getFonte(), 0, cs.getTamanhoFonte()), false));
      cs.setCorFundoConsole(new Color(Integer.parseInt("323232", 16)));
      cs.setCorOutputConsole(new Color(Integer.parseInt("ffffff", 16)));
      cs.setCorInputConsole(new Color(Integer.parseInt("6699ff", 16)));
      cs.setCorErrorConsole(new Color(Integer.parseInt("ff9999", 16)));
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      cs.setWindowBounds(new Rectangle(d.width / 2 - 400, d.height / 2 - 300, 800, 600));
      cs.setCorFundoEditor(defaultBackground);
      return cs;
   }

   private static ElementoTexto criaElementoTexto(Style style, CalangoSettings cs) {
      Font font = style.font;
      if (font == null) {
         font = new Font(cs.getFonte(), 0, cs.getTamanhoFonte());
      }

      return new ElementoTexto(style.foreground, font, style.underline);
   }
}
