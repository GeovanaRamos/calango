/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.util;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import tcccalango.util.settings.CalangoSettings;

/**
 *
 * @author JessicaLuanne
 */
public class CalangoViewUtil {
    
    /**
     * Este método altera o esquema de cores e configurações relacionadas ao 
     * texto do editor de acordo com as configurações passadas atráves do 
     * calangoSettings
     * @param calangoSettings
     * @param syntaxTextArea 
     */
    public static boolean configuraSyntaxWithSettings(CalangoSettings calangoSettings, RSyntaxTextArea syntaxTextArea) {
        if(syntaxTextArea != null){
            SyntaxScheme scheme = syntaxTextArea.getSyntaxScheme();
            scheme.styles[Token.COMMENT_EOL].foreground = calangoSettings.getCorComentario();
            scheme.styles[Token.COMMENT_MULTILINE].foreground = calangoSettings.getCorComentario();
            scheme.styles[Token.DATA_TYPE].foreground = calangoSettings.getCorTipoDado();
            scheme.styles[Token.LITERAL_NUMBER_DECIMAL_INT].foreground = calangoSettings.getCorConstanteNum();
            scheme.styles[Token.LITERAL_STRING_DOUBLE_QUOTE].foreground = calangoSettings.getCorConstanteTexto();
            scheme.styles[Token.RESERVED_WORD].foreground = calangoSettings.getCorPalavraChave();
            syntaxTextArea.setBackground(calangoSettings.getCorFundoEditor());
            syntaxTextArea.setForeground(calangoSettings.getCorTextoGeral());

            Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
            if(calangoSettings.isSyleUnderline())
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            syntaxTextArea.setFont(new Font(calangoSettings.getFonte(), calangoSettings.getEstiloFonte(), calangoSettings.getTamanhoFonte()).deriveFont(fontAttributes));
            //calangoSettings.getText();
            return true;
        }
        return false;
    }
    
    public static boolean isMacOS(){
        return System.getProperty("os.name").toUpperCase().contains("MAC");
    }
}
