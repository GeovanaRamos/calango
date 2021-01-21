package tcccalango.util.settings;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalangoSettings implements Serializable {
   private static final long serialVersionUID = 20121203L;
   public static final Locale BR = new Locale("pt_BR_", "pt", "PT");
   private ElementoTexto elementoComentario;
   private ElementoTexto elementoConstanteTexto;
   private ElementoTexto elementoConstanteNum;
   private ElementoTexto elementoPalavraChave;
   private ElementoTexto elementoTipoDado;
   private ElementoTexto elementoTextoGeral;
   private String fonte;
   private Integer tamanhoFonte;
   private Color corFundoEditor;
   private boolean consoleEmbutida;
   private Color corFundoConsole;
   private Color corOutputConsole;
   private Color corInputConsole;
   private Color corErrorConsole;
   private int x;
   private int y;
   private int width;
   private int height;
   private List<String> documentosRecentes;
   private String textoSintese;
   private String textoSecaoComandos;

   public String getTextoInicio() {
      StringBuilder texto = new StringBuilder();
      texto.append("algoritmo semNome;\n");
      texto.append(this.getTextoSintese());
      texto.append("\n");
      texto.append("\n");
      texto.append("principal\n");
      texto.append(this.getTextoSecaoComandos());
      texto.append("\nfimPrincipal\n");
      return texto.toString();
   }

   public void addArquivo(String documento) {
      if (this.getDocumentosRecentes().contains(documento)) {
         this.getDocumentosRecentes().remove(documento);
      }

      while(this.getDocumentosRecentes().size() >= 5) {
         this.getDocumentosRecentes().remove(this.getDocumentosRecentes().size() - 1);
      }

      this.getDocumentosRecentes().add(0, documento);
   }

   public Color getCorErrorConsole() {
      return this.corErrorConsole;
   }

   public void setCorErrorConsole(Color corErrorConsole) {
      this.corErrorConsole = corErrorConsole;
   }

   public Color getCorFundoConsole() {
      return this.corFundoConsole;
   }

   public void setCorFundoConsole(Color corFundoConsole) {
      this.corFundoConsole = corFundoConsole;
   }

   public Color getCorInputConsole() {
      return this.corInputConsole;
   }

   public void setCorInputConsole(Color corInputConsole) {
      this.corInputConsole = corInputConsole;
   }

   public Color getCorOutputConsole() {
      return this.corOutputConsole;
   }

   public void setCorOutputConsole(Color corOutputConsole) {
      this.corOutputConsole = corOutputConsole;
   }

   public boolean isConsoleEmbutida() {
      return this.consoleEmbutida;
   }

   public void setConsoleEmbutida(boolean consoleEmbutida) {
      this.consoleEmbutida = consoleEmbutida;
   }

   public ElementoTexto getElementoComentario() {
      return this.elementoComentario;
   }

   public void setElementoComentario(ElementoTexto elementoComentario) {
      this.elementoComentario = elementoComentario;
   }

   public ElementoTexto getElementoConstanteNum() {
      return this.elementoConstanteNum;
   }

   public void setElementoConstanteNum(ElementoTexto elementoConstanteNum) {
      this.elementoConstanteNum = elementoConstanteNum;
   }

   public ElementoTexto getElementoConstanteTexto() {
      return this.elementoConstanteTexto;
   }

   public void setElementoConstanteTexto(ElementoTexto elementoConstanteTexto) {
      this.elementoConstanteTexto = elementoConstanteTexto;
   }

   public Color getCorFundoEditor() {
      return this.corFundoEditor;
   }

   public void setCorFundoEditor(Color corFundoEditor) {
      this.corFundoEditor = corFundoEditor;
   }

   public ElementoTexto getElementoPalavraChave() {
      return this.elementoPalavraChave;
   }

   public void setElementoPalavraChave(ElementoTexto elementoPalavraChave) {
      this.elementoPalavraChave = elementoPalavraChave;
   }

   public ElementoTexto getElementoTextoGeral() {
      return this.elementoTextoGeral;
   }

   public void setElementoTextoGeral(ElementoTexto elementoTextoGeral) {
      this.elementoTextoGeral = elementoTextoGeral;
   }

   public ElementoTexto getElementoTipoDado() {
      return this.elementoTipoDado;
   }

   public void setElementoTipoDado(ElementoTexto elementoTipoDado) {
      this.elementoTipoDado = elementoTipoDado;
   }

   public String getFonte() {
      return this.fonte;
   }

   public void setFonte(String fonte) {
      this.fonte = fonte;
   }

   public Integer getTamanhoFonte() {
      return this.tamanhoFonte;
   }

   public void setTamanhoFonte(Integer tamanhoFonte) {
      this.tamanhoFonte = tamanhoFonte;
   }

   public List<String> getDocumentosRecentes() {
      if (this.documentosRecentes == null) {
         this.documentosRecentes = new ArrayList();
      }

      return this.documentosRecentes;
   }

   public void setDocumentosRecentes(List<String> documentosRecentes) {
      this.documentosRecentes = documentosRecentes;
   }

   public String getTextoSecaoComandos() {
      if (this.textoSecaoComandos == null) {
         StringBuilder secaoComando = new StringBuilder();
         secaoComando.append("\t// Declarações\n\n");
         secaoComando.append("\t// Instruções\n");
         this.setTextoSecaoComandos(secaoComando.toString());
      }

      return this.textoSecaoComandos;
   }

   public void setTextoSecaoComandos(String textoSecaoComandos) {
      this.textoSecaoComandos = textoSecaoComandos;
   }

   public String getTextoSintese() {
      if (this.textoSintese == null) {
         StringBuilder sintese = new StringBuilder();
         sintese.append("// Síntese\n");
         sintese.append("//  Objetivo:  \n");
         sintese.append("//  Entrada :\n");
         sintese.append("//  Saída   :\n");
         this.setTextoSintese(sintese.toString());
      }

      this.textoSintese = this.textoSintese.replace("{data}", "{0}");
      String data = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
      return MessageFormat.format(this.textoSintese, data);
   }

   public void setTextoSintese(String textoSintese) {
      this.textoSintese = textoSintese;
   }

   public static Integer[] getPossibleFontsSizesArray() {
      Integer[] array = new Integer[]{10, 12, 14, 16, 20, 30, 40, 50};
      return array;
   }

   public static List<Integer> getPossibleFontsSizes() {
      Integer[] array = getPossibleFontsSizesArray();
      List<Integer> list = Arrays.asList(array);
      Collections.sort(list);
      return list;
   }

   public static Integer getMinimumFontSize() {
      return (Integer)getPossibleFontsSizes().get(0);
   }

   public static Integer getMaximumFontSize() {
      List<Integer> list = getPossibleFontsSizes();
      return (Integer)list.get(list.size() - 1);
   }

   public static Integer getPreviousFontSize(Integer tamanho) {
      List<Integer> list = getPossibleFontsSizes();

      for(int i = 0; i < list.size(); ++i) {
         if (i != 0 && ((Integer)list.get(i)).equals(tamanho)) {
            return (Integer)list.get(i - 1);
         }
      }

      return null;
   }

   public static Integer getLaterFontSize(Integer tamanho) {
      List<Integer> list = getPossibleFontsSizes();

      for(int i = 0; i < list.size(); ++i) {
         if (i != list.size() - 1 && ((Integer)list.get(i)).equals(tamanho)) {
            return (Integer)list.get(i + 1);
         }
      }

      return null;
   }

   public Rectangle getWindowBounds() {
      return new Rectangle(this.x, this.y, this.width, this.height);
   }

   public void setWindowBounds(Rectangle bounds) {
      this.x = bounds.x;
      this.y = bounds.y;
      this.width = bounds.width;
      this.height = bounds.height;
   }
}
