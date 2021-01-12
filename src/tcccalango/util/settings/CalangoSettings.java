/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.settings;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Claeber
 */
public class CalangoSettings implements Serializable{
    private static final long serialVersionUID = 1234L;
    
    public static final Locale BR = new Locale("pt_BR_", "pt", "PT");
    private Color corComentario;
    private Color corConstanteTexto;
    private Color corConstanteNum;
    private Color corFundoEditor;
    private Color corPalavraChave;
    private Color corTipoDado;
    private Color corTextoGeral;
    
    private String fonte;
    private Integer tamanhoFonte;
    private Integer estiloFonte;
    private boolean syleUnderline;
    
    private List<String> documentosRecentes;
    
    private String text = "";
    private String textoSintese;
    private String textoSecaoComandos;

    public boolean isSyleUnderline() {
        return syleUnderline;
    }

    public void setSyleUnderline(boolean syleUnderline) {
        this.syleUnderline = syleUnderline;
    }
    
    
    public String getTextoInicio(){
        StringBuilder texto = new StringBuilder();
        texto.append("algoritmo semNome;\n");
//        texto.append("// Síntese\n");
//        texto.append("//  Objetivo:  \n");
//        texto.append("//  Entrada :\n");
//        texto.append("//  Saída   :\n");
//        texto.append("//  Data    : ");
        texto.append(getTextoSintese());
        texto.append(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        texto.append("\n");
        texto.append("\n");
        texto.append("principal\n");
//        texto.append("// Seção de Comandos\n");
        texto.append(getTextoSecaoComandos());
        texto.append("\nfimPrincipal\n");
        return texto.toString();
    }
    
    public void addArquivo(String documento){
        if (getDocumentosRecentes().contains(documento)){
            getDocumentosRecentes().remove(documento);
        }
        
        while (getDocumentosRecentes().size() >= 5){
            getDocumentosRecentes().remove(getDocumentosRecentes().size()-1);
        }
        
        getDocumentosRecentes().add(0, documento);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getCorComentario() {
        return corComentario;
    }

    public void setCorComentario(Color corComentario) {
        this.corComentario = corComentario;
    }

    public Color getCorConstanteNum() {
        return corConstanteNum;
    }

    public void setCorConstanteNum(Color corConstanteNum) {
        this.corConstanteNum = corConstanteNum;
    }

    public Color getCorConstanteTexto() {
        return corConstanteTexto;
    }

    public void setCorConstanteTexto(Color corConstanteTexto) {
        this.corConstanteTexto = corConstanteTexto;
    }

    public Color getCorFundoEditor() {
        return corFundoEditor;
    }

    public void setCorFundoEditor(Color corFundoEditor) {
        this.corFundoEditor = corFundoEditor;
    }

    public Color getCorPalavraChave() {
        return corPalavraChave;
    }

    public void setCorPalavraChave(Color corPalavraChave) {
        this.corPalavraChave = corPalavraChave;
    }

    public Color getCorTextoGeral() {
        return corTextoGeral;
    }

    public void setCorTextoGeral(Color corTextoGeral) {
        this.corTextoGeral = corTextoGeral;
    }

    public Color getCorTipoDado() {
        return corTipoDado;
    }

    public void setCorTipoDado(Color corTipoDado) {
        this.corTipoDado = corTipoDado;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Integer getTamanhoFonte() {
        return tamanhoFonte;
    }

    public void setTamanhoFonte(Integer tamanhoFonte) {
        this.tamanhoFonte = tamanhoFonte;
    }

    public List<String> getDocumentosRecentes() {
        if (documentosRecentes==null) {
            documentosRecentes = new ArrayList<String>();
        }
        return documentosRecentes;
    }

    public void setDocumentosRecentes(List<String> documentosRecentes) {
        this.documentosRecentes = documentosRecentes;
    }
    
    public Integer getEstiloFonte() {
        return estiloFonte;
    }

    public void setEstiloFonte(Integer estiloFonte) {
        this.estiloFonte = estiloFonte;
    }
    
    public boolean isStyleItalic(){
        return getEstiloFonte() == Font.ITALIC? true: false; 
    }
    
    public boolean isStyleBold(){
        return getEstiloFonte() == Font.BOLD? true: false;
    }
    
    public boolean isStyleBoldItalic(){
        return getEstiloFonte() == (Font.BOLD + Font.ITALIC)? true: false;
    }

    public String getTextoSecaoComandos() {
        if(textoSecaoComandos == null){
            StringBuilder secaoComando = new StringBuilder();
            secaoComando.append("// Seção de Comandos\n");
            setTextoSecaoComandos(secaoComando.toString());
        }
        return textoSecaoComandos;
    }

    public void setTextoSecaoComandos(String textoSecaoComandos) {
        this.textoSecaoComandos = textoSecaoComandos;
    }

    public String getTextoSintese() {
        if(textoSintese == null){
            StringBuilder sintese = new StringBuilder();
            sintese.append("// Síntese\n");
            sintese.append("//  Objetivo:  \n");
            sintese.append("//  Entrada :\n");
            sintese.append("//  Saída   :\n");
            sintese.append("//  Data    : ");
            setTextoSintese(sintese.toString());
        }
        return textoSintese;
    }

    public void setTextoSintese(String textoSintese) {
        this.textoSintese = textoSintese;
    }
    
     
}
