/*
 * TCCCalangoView.java
 */

package tcccalango.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import tcccalango.TCCCalangoApp;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.view.interfaces.ITCCCalangoSettingsSuject;
import tcccalango.view.interfaces.ITCCCalangoViewObserver;
import tcccalango.view.util.CalangoViewUtil;

/**
 * Esta Frame responsável pelos metódos da janela de opções.
 * 
 * criado por: claeber.felinto
 */
public class TCCCalangoSettings extends FrameView implements ITCCCalangoSettingsSuject {

    /*
     * Carrega as informações inicias para o Frame
     */
    public TCCCalangoSettings(SingleFrameApplication app, CalangoSettings calangoSettings) {
        super(app);
        currentCalangoSettings = calangoSettings;
        initComponents();
        getFrame().setResizable(false);
        getFrame().setPreferredSize(new Dimension(630, 660));
        getFrame().setTitle("Opções");
        
        rstaExemploCores = configuraJPanel(jpExemploCores, calangoSettings, calangoSettings.getTextoInicio(), 10);
        rstaTextoSintese = configuraJPanel(jpTextoSintese, calangoSettings, calangoSettings.getTextoSintese(), 5);
        rstaTextoSessaoComandos = configuraJPanel(jpSecaoComandos, calangoSettings, calangoSettings.getTextoSecaoComandos(), 5);
     }
    
    public RSyntaxTextArea configuraJPanel(javax.swing.JPanel panel, CalangoSettings calangoSettings, String textoInicial, int rows){
        RSyntaxTextArea rSyntaxTextArea = new RSyntaxTextArea();
        rSyntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CALANGO);
        rSyntaxTextArea.setText(textoInicial);
        rSyntaxTextArea.setRows(rows);
        CalangoViewUtil.configuraSyntaxWithSettings(calangoSettings, rSyntaxTextArea);
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.CENTER, new JScrollPane(rSyntaxTextArea));
        rSyntaxTextArea.setVisible(true);
        return  rSyntaxTextArea;
    }
  
    /**
     * Retorna o texto inicial para exibição do exemplo.
     * @return @link{String} Texto inicial para paleta de cores
     */
    public String getTextoInicio(){
        StringBuilder texto = new StringBuilder();
        texto.append("algoritmo semNome;\n");
        texto.append("// Síntese\n");
        texto.append("//  Objetivo:  \n");
        texto.append("//  Entrada :\n");
        texto.append("//  Saída   :\n");
        texto.append("//  Data    : ");
        texto.append(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        texto.append("\n");
        texto.append("\n");
        texto.append("principal\n");
        texto.append("// Seção de Comandos\n");
        texto.append(" inteiro a = 1;\n");
        texto.append(" escreval(\"Teste de Configuração\");\n");
        texto.append("fimPrincipal\n");
        return texto.toString();
    }
    
    /**
     *  Executa a ação de exibir a pela de cores.
     */
    @Action
    public void showColorChoose(){
        Color cor;  
            //TRADUCAO  
            UIManager.put("ColorChooser.sampleText","Texto Exemplo");  
            UIManager.put("ColorChooser.swatchesNameText","Escolha");  
            UIManager.put("ColorChooser.resetText", "Restaurar");  
            UIManager.put("ColorChooser.previewText", "Pré-Visualizar");  
            UIManager.put("ColorChooser.cancelText", "Cancelar");  
            UIManager.put("ColorChooser.swatchesRecentText", "Recentes");  
              
            JColorChooser colorChooser = new JColorChooser();  
            cor = colorChooser.showDialog(null,"Selecione a cor",Color.red);
            //colorChooser.set
            if (cor != null){  
               jpCor.setOpaque(true);  
               jpCor.setBackground(cor);  
            }           
            corSelecionada = cor;
            aplicarOpcoesTextoExemplo();
    }
    
    /**
     * Fecha a Frame da tela de opões e cancela todas as alterações
     */
    @Action
    public void cancelarOpces(){
        TCCCalangoApp.getApplication().hide(this);
    }
    
    /**
     * Salva e aplica as alterações realizadas na tela de opções.
     */
    @Action
    public void aplicarOpcoes(){
        CalangoSettings cs = getOpcoes();
        notificarObservadores(cs);
    }
    
    /**
     * Salva e aplica as alterações realizadas na tela de opções e fecha a janela
     * de configurações
     */
    @Action
    public void aplicarOpcoesCloseSettings(){
        TCCCalangoApp.getApplication().hide(this);
        CalangoSettings cs = getOpcoes();
        notificarObservadores(cs);
    }
    
    /**
     * Aplica as opções de configuração informadas na janela do Menu de 
     * Personalização no texto exemplo contida no mesmo
     */
    @Action
    public void aplicarOpcoesTextoExemplo(){
        CalangoSettings cs = getOpcoes();
        configuraTextoExemploWithSettings(cs);
        if(rstaExemploCores != null)
            rstaExemploCores.repaint();
        jpExemploCores.repaint();
    }
    
    private void configuraTextoExemploWithSettings(CalangoSettings calangoSettings) {
            CalangoViewUtil.configuraSyntaxWithSettings(calangoSettings, rstaExemploCores);
    }
    
    /**
     * Obtém as opções de configuração informadas na janela do Menu de Personalização
     * @return 
     */
    private CalangoSettings getOpcoes(){
        CalangoSettings cs = new CalangoSettings();
        copyCalangoSettings(currentCalangoSettings, cs);
        Object[] elementos = listElementos.getSelectedValues();
        for (Object object : elementos) {
            String value = (String)object;
            if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.COMENTARIO.getDescricao())){
                cs.setCorComentario(corSelecionada);
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.CONSTANTE_TEXTO.getDescricao())){
                cs.setCorConstanteTexto(corSelecionada);
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.CONSTANTE_NUMERICA.getDescricao())){
                cs.setCorConstanteNum(corSelecionada);                
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.FUNDO_EDITOR.getDescricao())){
                cs.setCorFundoEditor(corSelecionada);                
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.PALAVRA_CHAVE.getDescricao())){
                cs.setCorPalavraChave(corSelecionada);                
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.TIPO_DADO.getDescricao())){
                cs.setCorTipoDado(corSelecionada);                
            } else if(value.equalsIgnoreCase(EnumCamposCoresEditaveis.TEXTO_GERAL.getDescricao())){
                cs.setCorTextoGeral(corSelecionada);
            }
        }
        
        cs.setFonte((String) cboxFonte.getSelectedItem());
        cs.setTamanhoFonte((Integer)cboxTamanho.getSelectedItem());
        cs.setSyleUnderline(checkSublinhado.isSelected());
        if(checkItalico.isSelected()){
            if(checkNegrito.isSelected()){
                cs.setEstiloFonte(Font.BOLD+Font.ITALIC);
            }
            else {
                cs.setEstiloFonte(Font.ITALIC);
            }
        }else if(checkNegrito.isSelected()){
            cs.setEstiloFonte(Font.BOLD);
        }else{
            cs.setEstiloFonte(Font.PLAIN);
        }
        
        if(rstaTextoSintese != null)
            cs.setTextoSintese(rstaTextoSintese.getText());
        if(rstaTextoSessaoComandos != null)
            cs.setTextoSecaoComandos(rstaTextoSessaoComandos.getText());
        return cs;
    } 
    
      /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpOpcoes = new javax.swing.JPanel();
        jTbOpcoes = new javax.swing.JTabbedPane();
        jpSettings = new javax.swing.JPanel();
        cboxFonte = new javax.swing.JComboBox();
        cboxTamanho = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new JScrollPane();
        listElementos = new javax.swing.JList();
        jpAtributos = new javax.swing.JPanel();
        checkNegrito = new javax.swing.JCheckBox();
        checkItalico = new javax.swing.JCheckBox();
        checkSublinhado = new javax.swing.JCheckBox();
        btDefinirCor = new javax.swing.JButton();
        jpCor = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpExemploCores = new javax.swing.JPanel();
        jpLayout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jpTextoSintese = new javax.swing.JPanel();
        jpSecaoComandos = new javax.swing.JPanel();
        jpBotoesOpcoes = new javax.swing.JPanel();
        btCancelarOpcoes = new javax.swing.JButton();
        btAplicarOpcoes = new javax.swing.JButton();
        btSalvarOpcoes = new javax.swing.JButton();

        jpOpcoes.setName("jpOpcoes"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoSettings.class);
        jTbOpcoes.setToolTipText(resourceMap.getString("jTbOpcoes.toolTipText")); // NOI18N
        jTbOpcoes.setName("jTbOpcoes"); // NOI18N

        jpSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jpSettings.border.title"))); // NOI18N
        jpSettings.setMaximumSize(new Dimension(700, 600));
        jpSettings.setMinimumSize(new Dimension(700, 600));
        jpSettings.setName("jpSettings"); // NOI18N

        cboxFonte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED }));
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getActionMap(TCCCalangoSettings.class, this);
        cboxFonte.setAction(actionMap.get("aplicarOpcoesTextoExemplo")); // NOI18N
        cboxFonte.setName("cboxFonte"); // NOI18N
        cboxFonte.setSelectedItem(currentCalangoSettings.getFonte());

        cboxTamanho.setModel(new javax.swing.DefaultComboBoxModel(new Integer[] { 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 40, 50 }));
        cboxTamanho.setAction(actionMap.get("aplicarOpcoesTextoExemplo")); // NOI18N
        cboxTamanho.setName("cboxTamanho"); // NOI18N
        cboxTamanho.setSelectedItem(currentCalangoSettings.getTamanhoFonte());

        jLabel1.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listElementos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { EnumCamposCoresEditaveis.COMENTARIO.getDescricao(),
                EnumCamposCoresEditaveis.CONSTANTE_TEXTO.getDescricao(),
                EnumCamposCoresEditaveis.CONSTANTE_NUMERICA.getDescricao(),
                EnumCamposCoresEditaveis.FUNDO_EDITOR.getDescricao(),
                EnumCamposCoresEditaveis.PALAVRA_CHAVE.getDescricao(),
                EnumCamposCoresEditaveis.TIPO_DADO.getDescricao(),
                EnumCamposCoresEditaveis.TEXTO_GERAL.getDescricao()};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listElementos.setName("listElementos"); // NOI18N
        listElementos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listElementosMouseClicked2(evt);
            }
        });
        jScrollPane1.setViewportView(listElementos);

        jpAtributos.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jpAtributos.border.title"))); // NOI18N
        jpAtributos.setName("jpAtributos"); // NOI18N

        checkNegrito.setAction(actionMap.get("aplicarOpcoesTextoExemplo")); // NOI18N
        checkNegrito.setText(resourceMap.getString("checkNegrito.text")); // NOI18N
        checkNegrito.setName("checkNegrito"); // NOI18N
        if(currentCalangoSettings.isStyleBold() || currentCalangoSettings.isStyleBoldItalic()){
            checkNegrito.setSelected(true);
        } else {
            checkNegrito.setSelected(false);
        }

        checkItalico.setAction(actionMap.get("aplicarOpcoesTextoExemplo")); // NOI18N
        checkItalico.setText(resourceMap.getString("checkItalico.text")); // NOI18N
        checkItalico.setName("checkItalico"); // NOI18N
        if(currentCalangoSettings.isStyleItalic() || currentCalangoSettings.isStyleBoldItalic()){
            checkItalico.setSelected(true);
        } else {
            checkItalico.setSelected(false);
        }

        checkSublinhado.setAction(actionMap.get("aplicarOpcoesTextoExemplo")); // NOI18N
        checkSublinhado.setText(resourceMap.getString("checkSublinhado.text")); // NOI18N
        checkSublinhado.setName("checkSublinhado"); // NOI18N
        if(currentCalangoSettings.isSyleUnderline()){
            checkSublinhado.setSelected(true);
        } else {
            checkSublinhado.setSelected(false);
        }

        btDefinirCor.setAction(actionMap.get("showColorChoose")); // NOI18N
        btDefinirCor.setText(resourceMap.getString("btDefinirCor.text")); // NOI18N
        btDefinirCor.setName("btDefinirCor"); // NOI18N

        jpCor.setName("jpCor"); // NOI18N

        javax.swing.GroupLayout jpCorLayout = new javax.swing.GroupLayout(jpCor);
        jpCor.setLayout(jpCorLayout);
        jpCorLayout.setHorizontalGroup(
            jpCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );
        jpCorLayout.setVerticalGroup(
            jpCorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jpAtributosLayout = new javax.swing.GroupLayout(jpAtributos);
        jpAtributos.setLayout(jpAtributosLayout);
        jpAtributosLayout.setHorizontalGroup(
            jpAtributosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAtributosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAtributosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkNegrito)
                    .addComponent(checkItalico)
                    .addComponent(checkSublinhado)
                    .addGroup(jpAtributosLayout.createSequentialGroup()
                        .addComponent(jpCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btDefinirCor))
                    .addComponent(jLabel4))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpAtributosLayout.setVerticalGroup(
            jpAtributosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAtributosLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jpAtributosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDefinirCor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkSublinhado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkItalico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkNegrito)
                .addGap(18, 18, 18))
        );

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jpExemploCores.setName("jpExemploCores"); // NOI18N

        javax.swing.GroupLayout jpExemploCoresLayout = new javax.swing.GroupLayout(jpExemploCores);
        jpExemploCores.setLayout(jpExemploCoresLayout);
        jpExemploCoresLayout.setHorizontalGroup(
            jpExemploCoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );
        jpExemploCoresLayout.setVerticalGroup(
            jpExemploCoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpSettingsLayout = new javax.swing.GroupLayout(jpSettings);
        jpSettings.setLayout(jpSettingsLayout);
        jpSettingsLayout.setHorizontalGroup(
            jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSettingsLayout.createSequentialGroup()
                        .addGroup(jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jpAtributos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(jpExemploCores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jpSettingsLayout.setVerticalGroup(
            jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboxTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSettingsLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                    .addComponent(jpAtributos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jpExemploCores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTbOpcoes.addTab(resourceMap.getString("jpSettings.TabConstraints.tabTitle"), jpSettings); // NOI18N

        jpLayout.setName("jpLayout"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jpTextoSintese.setAutoscrolls(true);
        jpTextoSintese.setMaximumSize(new Dimension(200, 150));
        jpTextoSintese.setName("jpTextoSintese"); // NOI18N

        javax.swing.GroupLayout jpTextoSinteseLayout = new javax.swing.GroupLayout(jpTextoSintese);
        jpTextoSintese.setLayout(jpTextoSinteseLayout);
        jpTextoSinteseLayout.setHorizontalGroup(
            jpTextoSinteseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        jpTextoSinteseLayout.setVerticalGroup(
            jpTextoSinteseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        jpSecaoComandos.setName("jpSecaoComandos"); // NOI18N

        javax.swing.GroupLayout jpSecaoComandosLayout = new javax.swing.GroupLayout(jpSecaoComandos);
        jpSecaoComandos.setLayout(jpSecaoComandosLayout);
        jpSecaoComandosLayout.setHorizontalGroup(
            jpSecaoComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        jpSecaoComandosLayout.setVerticalGroup(
            jpSecaoComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpLayoutLayout = new javax.swing.GroupLayout(jpLayout);
        jpLayout.setLayout(jpLayoutLayout);
        jpLayoutLayout.setHorizontalGroup(
            jpLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLayoutLayout.createSequentialGroup()
                .addGroup(jpLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLayoutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpLayoutLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jpTextoSintese, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpLayoutLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jpLayoutLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jpSecaoComandos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpLayoutLayout.setVerticalGroup(
            jpLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLayoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpTextoSintese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpSecaoComandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTbOpcoes.addTab(resourceMap.getString("jpLayout.TabConstraints.tabTitle"), jpLayout); // NOI18N

        jpBotoesOpcoes.setName("jpBotoesOpcoes"); // NOI18N

        btCancelarOpcoes.setAction(actionMap.get("cancelarOpces")); // NOI18N
        btCancelarOpcoes.setText(resourceMap.getString("btCancelarOpcoes.text")); // NOI18N
        btCancelarOpcoes.setName("btCancelarOpcoes"); // NOI18N

        btAplicarOpcoes.setAction(actionMap.get("aplicarOpcoes")); // NOI18N
        btAplicarOpcoes.setText(resourceMap.getString("btAplicarOpcoes.text")); // NOI18N
        btAplicarOpcoes.setName("btAplicarOpcoes"); // NOI18N

        btSalvarOpcoes.setAction(actionMap.get("aplicarOpcoesCloseSettings")); // NOI18N
        btSalvarOpcoes.setText(resourceMap.getString("btSalvarOpcoes.text")); // NOI18N
        btSalvarOpcoes.setName("btSalvarOpcoes"); // NOI18N

        javax.swing.GroupLayout jpBotoesOpcoesLayout = new javax.swing.GroupLayout(jpBotoesOpcoes);
        jpBotoesOpcoes.setLayout(jpBotoesOpcoesLayout);
        jpBotoesOpcoesLayout.setHorizontalGroup(
            jpBotoesOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBotoesOpcoesLayout.createSequentialGroup()
                .addContainerGap(394, Short.MAX_VALUE)
                .addComponent(btCancelarOpcoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAplicarOpcoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSalvarOpcoes)
                .addGap(25, 25, 25))
        );
        jpBotoesOpcoesLayout.setVerticalGroup(
            jpBotoesOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotoesOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBotoesOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvarOpcoes)
                    .addComponent(btAplicarOpcoes)
                    .addComponent(btCancelarOpcoes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btCancelarOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("btCancelarOpcoes.AccessibleContext.accessibleName")); // NOI18N
        btAplicarOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("btAplicarOpcoes.AccessibleContext.accessibleName")); // NOI18N

        javax.swing.GroupLayout jpOpcoesLayout = new javax.swing.GroupLayout(jpOpcoes);
        jpOpcoes.setLayout(jpOpcoesLayout);
        jpOpcoesLayout.setHorizontalGroup(
            jpOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpOpcoesLayout.createSequentialGroup()
                .addGroup(jpOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpBotoesOpcoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTbOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpOpcoesLayout.setVerticalGroup(
            jpOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcoesLayout.createSequentialGroup()
                .addComponent(jTbOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBotoesOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTbOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("jTbOpcoes.AccessibleContext.accessibleName")); // NOI18N
        jpBotoesOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("jpBotoesOpcoes.AccessibleContext.accessibleName")); // NOI18N

        setComponent(jpOpcoes);
    }// </editor-fold>//GEN-END:initComponents

    private void listElementosMouseClicked2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listElementosMouseClicked2
        // TODO add your handling code here:
    }//GEN-LAST:event_listElementosMouseClicked2

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAplicarOpcoes;
    private javax.swing.JButton btCancelarOpcoes;
    private javax.swing.JButton btDefinirCor;
    private javax.swing.JButton btSalvarOpcoes;
    private javax.swing.JComboBox cboxFonte;
    private javax.swing.JComboBox cboxTamanho;
    private javax.swing.JCheckBox checkItalico;
    private javax.swing.JCheckBox checkNegrito;
    private javax.swing.JCheckBox checkSublinhado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTbOpcoes;
    private javax.swing.JPanel jpAtributos;
    private javax.swing.JPanel jpBotoesOpcoes;
    private javax.swing.JPanel jpCor;
    private javax.swing.JPanel jpExemploCores;
    private javax.swing.JPanel jpLayout;
    private javax.swing.JPanel jpOpcoes;
    private javax.swing.JPanel jpSecaoComandos;
    private javax.swing.JPanel jpSettings;
    private javax.swing.JPanel jpTextoSintese;
    private javax.swing.JList listElementos;
    // End of variables declaration//GEN-END:variables
    private RSyntaxTextArea rstaExemploCores;
    private RSyntaxTextArea rstaTextoSintese;
    private RSyntaxTextArea rstaTextoSessaoComandos;
    private Color corSelecionada;
    private List<ITCCCalangoViewObserver> observadores = new ArrayList<ITCCCalangoViewObserver>();
    private CalangoSettings currentCalangoSettings;

    public void adicionarObservador(TCCCalangoView view) {
        observadores.add(view);
    }

    public void removerObservador(TCCCalangoView view) {
        observadores.remove(view);
    }

    /**
     * Notifica os observadores desta classe que ocorreram alterações nas 
     * configurações
     * @param novaConfiguracao 
     */
    public void notificarObservadores(CalangoSettings novaConfiguracao) {
        Iterator i = observadores.iterator();
        while(i.hasNext()){
                TCCCalangoView view = (TCCCalangoView) i.next();
                view.atualizar(novaConfiguracao);
        }
    }

    /**
     * Copia os dados de um CalangoSettings para outro
     * @param from
     * @param to 
     * @author Jéssica Luanne
     */
    private void copyCalangoSettings(CalangoSettings from, CalangoSettings to) {
        to.setCorComentario(from.getCorComentario());
        to.setCorConstanteNum(from.getCorConstanteNum());
        to.setCorConstanteTexto(from.getCorConstanteTexto());
        to.setCorFundoEditor(from.getCorFundoEditor());
        to.setCorPalavraChave(from.getCorPalavraChave());
        to.setCorTextoGeral(from.getCorTextoGeral());
        to.setCorTipoDado(from.getCorTipoDado());
        to.setDocumentosRecentes(from.getDocumentosRecentes());
        to.setEstiloFonte(from.getEstiloFonte());
        to.setFonte(from.getFonte());
        to.setTamanhoFonte(from.getTamanhoFonte());
        to.setText(from.getText());
    }
    
}
