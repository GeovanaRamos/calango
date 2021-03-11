package tcccalango.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import tcccalango.TCCCalangoApp;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.util.settings.CalangoSettingsUtil;
import tcccalango.util.settings.ElementoTexto;
import tcccalango.view.interfaces.ITCCCalangoSettingsSuject;
import tcccalango.view.interfaces.ITCCCalangoViewObserver;
import tcccalango.view.util.CalangoViewUtil;

public class TCCCalangoSettings extends JDialog implements ITCCCalangoSettingsSuject {
   private JButton btAplicarOpcoes;
   private JButton btCancelarOpcoes;
   private JButton btDefinirCor;
   private JButton btSalvarOpcoes;
   private JComboBox cboxFonte;
   private JComboBox cboxTamanho;
   private JCheckBox checkItalico;
   private JCheckBox checkNegrito;
   private JCheckBox checkSublinhado;
   private JButton jButton1;
   private JButton jButton2;
   private JButton jButton3;
   private JButton jButton4;
   private JLabel jLabel1;
   private JLabel jLabel10;
   private JLabel jLabel11;
   private JLabel jLabel12;
   private JLabel jLabel2;
   private JLabel jLabel3;
   private JLabel jLabel4;
   private JLabel jLabel5;
   private JLabel jLabel6;
   private JLabel jLabel7;
   private JLabel jLabel8;
   private JLabel jLabel9;
   private JPanel jPanel1;
   private JScrollPane jScrollPane1;
   private JTabbedPane jTbOpcoes;
   private JPanel jpAtributos;
   private JPanel jpBotoesOpcoes;
   private JPanel jpCor;
   private JPanel jpCorEntradaConsole;
   private JPanel jpCorErrosConsole;
   private JPanel jpCorFundoConsole;
   private JPanel jpCorSaidaConsole;
   private JPanel jpExemploCores;
   private JPanel jpLayout;
   private JPanel jpOpcoes;
   private JPanel jpSecaoComandos;
   private JPanel jpSettings;
   private JPanel jpTextoSintese;
   private JList listElementos;
   private JLabel visualizacaoEntradaConsole;
   private JLabel visualizacaoErroConsole;
   private JPanel visualizacaoFundoConsole;
   private JLabel visualizacaoSaidaConsole;
   private RSyntaxTextArea rstaExemploCores;
   private RSyntaxTextArea rstaTextoSintese;
   private RSyntaxTextArea rstaTextoSessaoComandos;
   private Color corSelecionada;
   private List<ITCCCalangoViewObserver> observadores = new ArrayList();
   private CalangoSettings currentCalangoSettings;
   private CalangoSettings newCalangoSettings = new CalangoSettings();
   private Frame parentFrame;
   private JColorChooser colorChooser = new JColorChooser();

   public TCCCalangoSettings(Frame parentFrame, SingleFrameApplication app, CalangoSettings calangoSettings) {
      this.currentCalangoSettings = calangoSettings;
      this.initComponents();
      CalangoSettingsUtil.copyCalangoSettings(this.currentCalangoSettings, this.newCalangoSettings);
      this.setResizable(false);
      this.setPreferredSize(new Dimension(630, 660));
      this.setTitle("Opções");
      this.setModal(true);
      this.rstaExemploCores = this.configuraJPanel(this.jpExemploCores, this.newCalangoSettings, this.newCalangoSettings.getTextoInicio(), 10);
      this.rstaTextoSintese = this.configuraJPanel(this.jpTextoSintese, this.newCalangoSettings, this.newCalangoSettings.getTextoSintese(), 5);
      this.rstaTextoSessaoComandos = this.configuraJPanel(this.jpSecaoComandos, this.newCalangoSettings, this.newCalangoSettings.getTextoSecaoComandos(), 5);
      this.parentFrame = parentFrame;
      this.setCorEntradaConsole(this.newCalangoSettings.getCorInputConsole());
      this.setCorSaidaConsole(this.newCalangoSettings.getCorOutputConsole());
      this.setCorErrosConsole(this.newCalangoSettings.getCorErrorConsole());
      this.setCorFundoConsole(this.newCalangoSettings.getCorFundoConsole());
      this.setDefaultCloseOperation(0);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            TCCCalangoSettings.this.closeWindow();
         }
      });
   }

   public RSyntaxTextArea configuraJPanel(JPanel panel, CalangoSettings calangoSettings, String textoInicial, int rows) {
      RSyntaxTextArea rSyntaxTextArea = new RSyntaxTextArea();
      rSyntaxTextArea.setSyntaxEditingStyle("text/calango");
      rSyntaxTextArea.setText(textoInicial);
      rSyntaxTextArea.setRows(rows);
      CalangoViewUtil.configuraSyntaxWithSettings(calangoSettings, rSyntaxTextArea);
      panel.setLayout(new BorderLayout());
      panel.add("Center", new JScrollPane(rSyntaxTextArea));
      rSyntaxTextArea.setVisible(true);
      return rSyntaxTextArea;
   }

   public String getTextoInicio() {
      StringBuilder texto = new StringBuilder();
      texto.append("algoritmo semNome;\n");
      texto.append("// Síntese\n");
      texto.append("//  Objetivo:  \n");
      texto.append("//  Entrada :\n");
      texto.append("//  Saída   :\n");
      texto.append("//  Data    : ");
      texto.append((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
      texto.append("\n");
      texto.append("\n");
      texto.append("principal\n");
      texto.append("// Seção de Comandos\n");
      texto.append(" inteiro a = 1;\n");
      texto.append(" escreval(\"Teste de Configuração\");\n");
      texto.append("fimPrincipal\n");
      return texto.toString();
   }

   @Action
   public void showColorChoose() {
      UIManager.put("ColorChooser.sampleText", "Texto Exemplo");
      UIManager.put("ColorChooser.swatchesNameText", "Escolha");
      UIManager.put("ColorChooser.resetText", "Restaurar");
      UIManager.put("ColorChooser.previewText", "Pré-Visualizar");
      UIManager.put("ColorChooser.cancelText", "Cancelar");
      UIManager.put("ColorChooser.swatchesRecentText", "Recentes");
      Color cor = this.selecionaCor(this.jpCor.getBackground());
      if (cor != null) {
         this.jpCor.setOpaque(true);
         this.jpCor.setBackground(cor);
      }

      this.corSelecionada = cor;
      this.updateElementoSelecionado();
      this.aplicarOpcoesTextoExemplo();
   }

   @Action
   public void cancelarOpcoes() {
      this.closeWindow();
   }

   private void closeWindow() {
      this.setVisible(false);
      this.enableParentFrame();
   }

   public void enableParentFrame() {
   }

   @Action
   public void aplicarOpcoes() {
      this.atualizaOpcoes();
      this.notificarObservadores(this.newCalangoSettings);
   }

   @Action
   public void aplicarOpcoesCloseSettings() {
      this.aplicarOpcoes();
      this.closeWindow();
   }

   @Action
   public void aplicarOpcoesTextoExemplo() {
      this.atualizaOpcoes();
      this.configuraTextoExemploWithSettings(this.newCalangoSettings);
      if (this.rstaExemploCores != null) {
         this.rstaExemploCores.repaint();
      }

      this.jpExemploCores.repaint();
   }

   private void configuraTextoExemploWithSettings(CalangoSettings calangoSettings) {
      CalangoViewUtil.configuraSyntaxWithSettings(calangoSettings, this.rstaExemploCores);
   }

   private void atualizaOpcoes() {
      this.newCalangoSettings.setFonte((String)this.cboxFonte.getSelectedItem());
      this.newCalangoSettings.setTamanhoFonte((Integer)this.cboxTamanho.getSelectedItem());
      if (this.rstaTextoSintese != null) {
         this.newCalangoSettings.setTextoSintese(this.rstaTextoSintese.getText());
      }

      if (this.rstaTextoSessaoComandos != null) {
         this.newCalangoSettings.setTextoSecaoComandos(this.rstaTextoSessaoComandos.getText());
      }

      this.newCalangoSettings.setCorErrorConsole(this.getCorErrosConsole());
      this.newCalangoSettings.setCorOutputConsole(this.getCorSaidaConsole());
      this.newCalangoSettings.setCorInputConsole(this.getCorEntradaConsole());
      this.newCalangoSettings.setCorFundoConsole(this.getCorFundoConsole());
   }

   private void initComponents() {
      this.jpOpcoes = new JPanel();
      this.jTbOpcoes = new JTabbedPane();
      this.jpSettings = new JPanel();
      this.cboxFonte = new JComboBox();
      this.cboxTamanho = new JComboBox();
      this.jLabel1 = new JLabel();
      this.jLabel2 = new JLabel();
      this.jLabel3 = new JLabel();
      this.jScrollPane1 = new JScrollPane();
      this.listElementos = new JList();
      this.jpAtributos = new JPanel();
      this.checkNegrito = new JCheckBox();
      this.checkItalico = new JCheckBox();
      this.checkSublinhado = new JCheckBox();
      this.btDefinirCor = new JButton();
      this.jpCor = new JPanel();
      this.jLabel4 = new JLabel();
      this.jLabel5 = new JLabel();
      this.jpExemploCores = new JPanel();
      this.jpLayout = new JPanel();
      this.jLabel6 = new JLabel();
      this.jLabel7 = new JLabel();
      this.jpTextoSintese = new JPanel();
      this.jpSecaoComandos = new JPanel();
      this.jPanel1 = new JPanel();
      this.jLabel8 = new JLabel();
      this.jLabel9 = new JLabel();
      this.jLabel10 = new JLabel();
      this.jLabel11 = new JLabel();
      this.jpCorFundoConsole = new JPanel();
      this.jButton1 = new JButton();
      this.jpCorSaidaConsole = new JPanel();
      this.jButton2 = new JButton();
      this.jpCorEntradaConsole = new JPanel();
      this.jButton3 = new JButton();
      this.jpCorErrosConsole = new JPanel();
      this.jButton4 = new JButton();
      this.visualizacaoFundoConsole = new JPanel();
      this.visualizacaoSaidaConsole = new JLabel();
      this.visualizacaoEntradaConsole = new JLabel();
      this.visualizacaoErroConsole = new JLabel();
      this.jLabel12 = new JLabel();
      this.jpBotoesOpcoes = new JPanel();
      this.btCancelarOpcoes = new JButton();
      this.btAplicarOpcoes = new JButton();
      this.btSalvarOpcoes = new JButton();
      this.jpOpcoes.setName("jpOpcoes");
      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoSettings.class);
      this.jTbOpcoes.setToolTipText(resourceMap.getString("jTbOpcoes.toolTipText"));
      this.jTbOpcoes.setName("jTbOpcoes");
      this.jpSettings.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jpSettings.border.title")));
      this.jpSettings.setMaximumSize(new Dimension(700, 600));
      this.jpSettings.setMinimumSize(new Dimension(700, 600));
      this.jpSettings.setName("jpSettings");
      this.cboxFonte.setModel(new DefaultComboBoxModel(new String[]{"SansSerif", "Serif", "Monospaced"}));
      this.cboxFonte.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.aplicarOpcoesTextoExemplo();
         }
      });
      this.cboxFonte.setName("cboxFonte");
      this.cboxFonte.setSelectedItem(this.currentCalangoSettings.getFonte());
      this.cboxTamanho.setModel(new DefaultComboBoxModel(CalangoSettings.getPossibleFontsSizesArray()));
      this.cboxTamanho.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.aplicarOpcoesTextoExemplo();
         }
      });
      this.cboxTamanho.setName("cboxTamanho");
      this.cboxTamanho.setSelectedItem(this.currentCalangoSettings.getTamanhoFonte());
      this.jLabel1.setFont(resourceMap.getFont("jLabel2.font"));
      this.jLabel1.setText(resourceMap.getString("jLabel1.text"));
      this.jLabel1.setName("jLabel1");
      this.jLabel2.setFont(resourceMap.getFont("jLabel2.font"));
      this.jLabel2.setText(resourceMap.getString("jLabel2.text"));
      this.jLabel2.setName("jLabel2");
      this.jLabel3.setText(resourceMap.getString("jLabel3.text"));
      this.jLabel3.setName("jLabel3");
      this.jScrollPane1.setName("jScrollPane1");
      this.listElementos.setModel(new AbstractListModel() {
         String[] strings;

         {
            this.strings = new String[]{EnumCamposEditaveis.COMENTARIO.getDescricao(), EnumCamposEditaveis.CONSTANTE_TEXTO.getDescricao(), EnumCamposEditaveis.CONSTANTE_NUMERICA.getDescricao(), EnumCamposEditaveis.FUNDO_EDITOR.getDescricao(), EnumCamposEditaveis.PALAVRA_CHAVE.getDescricao(), EnumCamposEditaveis.TIPO_DADO.getDescricao(), EnumCamposEditaveis.TEXTO_GERAL.getDescricao()};
         }

         public int getSize() {
            return this.strings.length;
         }

         public Object getElementAt(int i) {
            return this.strings[i];
         }
      });
      this.listElementos.setName("listElementos");
      this.listElementos.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent evt) {
            TCCCalangoSettings.this.listElementosMouseClicked2(evt);
         }
      });
      this.listElementos.setSelectionMode(0);
      this.listElementos.setSelectedIndex(0);
      this.elementoSelecionado();
      this.jScrollPane1.setViewportView(this.listElementos);
      this.jpAtributos.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jpAtributos.border.title")));
      this.jpAtributos.setName("jpAtributos");
      this.checkNegrito.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.updateElementoSelecionado();
         }
      });
      this.checkNegrito.setText(resourceMap.getString("checkNegrito.text"));
      this.checkNegrito.setName("checkNegrito");
      this.checkItalico.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.updateElementoSelecionado();
         }
      });
      this.checkItalico.setText(resourceMap.getString("checkItalico.text"));
      this.checkItalico.setName("checkItalico");
      this.checkSublinhado.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.updateElementoSelecionado();
         }
      });
      this.checkSublinhado.setText(resourceMap.getString("checkSublinhado.text"));
      this.checkSublinhado.setName("checkSublinhado");
      this.btDefinirCor.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.showColorChoose();
         }
      });
      this.btDefinirCor.setText(resourceMap.getString("btDefinirCor.text"));
      this.btDefinirCor.setName("btDefinirCor");
      this.jpCor.setName("jpCor");
      GroupLayout jpCorLayout = new GroupLayout(this.jpCor);
      this.jpCor.setLayout(jpCorLayout);
      jpCorLayout.setHorizontalGroup(jpCorLayout.createParallelGroup(Alignment.LEADING).addGap(0, 38, 32767));
      jpCorLayout.setVerticalGroup(jpCorLayout.createParallelGroup(Alignment.LEADING).addGap(0, 34, 32767));
      this.jLabel4.setText(resourceMap.getString("jLabel4.text"));
      this.jLabel4.setName("jLabel4");
      GroupLayout jpAtributosLayout = new GroupLayout(this.jpAtributos);
      this.jpAtributos.setLayout(jpAtributosLayout);
      jpAtributosLayout.setHorizontalGroup(jpAtributosLayout.createParallelGroup(Alignment.LEADING).addGroup(jpAtributosLayout.createSequentialGroup().addContainerGap().addGroup(jpAtributosLayout.createParallelGroup(Alignment.LEADING).addComponent(this.checkNegrito).addComponent(this.checkItalico).addComponent(this.checkSublinhado).addComponent(this.jLabel4).addGroup(jpAtributosLayout.createSequentialGroup().addComponent(this.jpCor, -2, -1, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btDefinirCor))).addContainerGap(51, 32767)));
      jpAtributosLayout.setVerticalGroup(jpAtributosLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jpAtributosLayout.createSequentialGroup().addGroup(jpAtributosLayout.createParallelGroup(Alignment.TRAILING).addGroup(jpAtributosLayout.createSequentialGroup().addComponent(this.jLabel4).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jpCor, -2, -1, -2)).addComponent(this.btDefinirCor)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.checkSublinhado).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.checkItalico).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.checkNegrito).addGap(18, 18, 18)));
      this.jLabel5.setText(resourceMap.getString("jLabel5.text"));
      this.jLabel5.setName("jLabel5");
      this.jpExemploCores.setName("jpExemploCores");
      GroupLayout jpExemploCoresLayout = new GroupLayout(this.jpExemploCores);
      this.jpExemploCores.setLayout(jpExemploCoresLayout);
      jpExemploCoresLayout.setHorizontalGroup(jpExemploCoresLayout.createParallelGroup(Alignment.LEADING).addGap(0, 573, 32767));
      jpExemploCoresLayout.setVerticalGroup(jpExemploCoresLayout.createParallelGroup(Alignment.LEADING).addGap(0, 153, 32767));
      GroupLayout jpSettingsLayout = new GroupLayout(this.jpSettings);
      this.jpSettings.setLayout(jpSettingsLayout);
      jpSettingsLayout.setHorizontalGroup(jpSettingsLayout.createParallelGroup(Alignment.LEADING).addGroup(jpSettingsLayout.createSequentialGroup().addContainerGap().addGroup(jpSettingsLayout.createParallelGroup(Alignment.LEADING).addGroup(jpSettingsLayout.createSequentialGroup().addComponent(this.jLabel1, -2, 43, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.cboxFonte, -2, 172, -2).addGap(18, 18, 18).addComponent(this.jLabel2, -2, 59, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.cboxTamanho, -2, 76, -2)).addGroup(jpSettingsLayout.createSequentialGroup().addGroup(jpSettingsLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel3, -2, 59, -2).addComponent(this.jScrollPane1, -2, 203, -2)).addGap(18, 18, 18).addComponent(this.jpAtributos, -2, -1, -2)).addComponent(this.jLabel5).addComponent(this.jpExemploCores, -1, -1, 32767)).addContainerGap(18, 32767)));
      jpSettingsLayout.setVerticalGroup(jpSettingsLayout.createParallelGroup(Alignment.LEADING).addGroup(jpSettingsLayout.createSequentialGroup().addContainerGap().addGroup(jpSettingsLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1, -2, 25, -2).addComponent(this.cboxFonte, -2, -1, -2).addComponent(this.jLabel2, -2, 25, -2).addComponent(this.cboxTamanho, -2, -1, -2)).addGap(18, 18, 18).addGroup(jpSettingsLayout.createParallelGroup(Alignment.LEADING).addGroup(jpSettingsLayout.createSequentialGroup().addComponent(this.jLabel3).addGap(10, 10, 10).addComponent(this.jScrollPane1, -1, 161, 32767)).addComponent(this.jpAtributos, -1, 185, 32767)).addGap(16, 16, 16).addComponent(this.jLabel5).addGap(18, 18, 18).addComponent(this.jpExemploCores, -2, -1, -2).addGap(81, 81, 81)));
      this.jTbOpcoes.addTab(resourceMap.getString("jpSettings.TabConstraints.tabTitle"), this.jpSettings);
      this.jpLayout.setName("jpLayout");
      this.jLabel6.setText(resourceMap.getString("jLabel6.text"));
      this.jLabel6.setName("jLabel6");
      this.jLabel7.setText(resourceMap.getString("jLabel7.text"));
      this.jLabel7.setName("jLabel7");
      this.jpTextoSintese.setAutoscrolls(true);
      this.jpTextoSintese.setMaximumSize(new Dimension(200, 150));
      this.jpTextoSintese.setName("jpTextoSintese");
      GroupLayout jpTextoSinteseLayout = new GroupLayout(this.jpTextoSintese);
      this.jpTextoSintese.setLayout(jpTextoSinteseLayout);
      jpTextoSinteseLayout.setHorizontalGroup(jpTextoSinteseLayout.createParallelGroup(Alignment.LEADING).addGap(0, 579, 32767));
      jpTextoSinteseLayout.setVerticalGroup(jpTextoSinteseLayout.createParallelGroup(Alignment.LEADING).addGap(0, 174, 32767));
      this.jpSecaoComandos.setName("jpSecaoComandos");
      GroupLayout jpSecaoComandosLayout = new GroupLayout(this.jpSecaoComandos);
      this.jpSecaoComandos.setLayout(jpSecaoComandosLayout);
      jpSecaoComandosLayout.setHorizontalGroup(jpSecaoComandosLayout.createParallelGroup(Alignment.LEADING).addGap(0, 579, 32767));
      jpSecaoComandosLayout.setVerticalGroup(jpSecaoComandosLayout.createParallelGroup(Alignment.LEADING).addGap(0, 177, 32767));
      GroupLayout jpLayoutLayout = new GroupLayout(this.jpLayout);
      this.jpLayout.setLayout(jpLayoutLayout);
      jpLayoutLayout.setHorizontalGroup(jpLayoutLayout.createParallelGroup(Alignment.LEADING).addGroup(jpLayoutLayout.createSequentialGroup().addGroup(jpLayoutLayout.createParallelGroup(Alignment.LEADING).addGroup(jpLayoutLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel6, -2, 43, -2)).addGroup(jpLayoutLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.jpTextoSintese, -1, -1, 32767)).addGroup(jpLayoutLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel7)).addGroup(jpLayoutLayout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.jpSecaoComandos, -1, -1, 32767))).addContainerGap()));
      jpLayoutLayout.setVerticalGroup(jpLayoutLayout.createParallelGroup(Alignment.LEADING).addGroup(jpLayoutLayout.createSequentialGroup().addContainerGap().addComponent(this.jLabel6, -2, 25, -2).addGap(18, 18, 18).addComponent(this.jpTextoSintese, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel7, -2, 25, -2).addGap(18, 18, 18).addComponent(this.jpSecaoComandos, -2, -1, -2).addContainerGap(94, 32767)));
      this.jTbOpcoes.addTab(resourceMap.getString("jpLayout.TabConstraints.tabTitle"), this.jpLayout);
      this.jPanel1.setName("jPanel1");
      this.jLabel8.setText(resourceMap.getString("jLabel8.text"));
      this.jLabel8.setName("jLabel8");
      this.jLabel9.setText(resourceMap.getString("jLabel9.text"));
      this.jLabel9.setName("jLabel9");
      this.jLabel10.setText(resourceMap.getString("jLabel10.text"));
      this.jLabel10.setName("jLabel10");
      this.jLabel11.setText(resourceMap.getString("jLabel11.text"));
      this.jLabel11.setName("jLabel11");
      this.jpCorFundoConsole.setName("jpCorFundoConsole");
      GroupLayout jpCorFundoConsoleLayout = new GroupLayout(this.jpCorFundoConsole);
      this.jpCorFundoConsole.setLayout(jpCorFundoConsoleLayout);
      jpCorFundoConsoleLayout.setHorizontalGroup(jpCorFundoConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 34, 32767));
      jpCorFundoConsoleLayout.setVerticalGroup(jpCorFundoConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 29, 32767));
      this.jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.escolherCorFundoConsole();
         }
      });
      this.jButton1.setText(resourceMap.getString("jButton1.text"));
      this.jButton1.setName("jButton1");
      this.jpCorSaidaConsole.setName("jpCorSaidaConsole");
      GroupLayout jpCorSaidaConsoleLayout = new GroupLayout(this.jpCorSaidaConsole);
      this.jpCorSaidaConsole.setLayout(jpCorSaidaConsoleLayout);
      jpCorSaidaConsoleLayout.setHorizontalGroup(jpCorSaidaConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 34, 32767));
      jpCorSaidaConsoleLayout.setVerticalGroup(jpCorSaidaConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 29, 32767));
      this.jButton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.escolherCorSaidaConsole();
         }
      });
      this.jButton2.setText(resourceMap.getString("jButton2.text"));
      this.jButton2.setName("jButton2");
      this.jpCorEntradaConsole.setName("jpCorEntradaConsole");
      GroupLayout jpCorEntradaConsoleLayout = new GroupLayout(this.jpCorEntradaConsole);
      this.jpCorEntradaConsole.setLayout(jpCorEntradaConsoleLayout);
      jpCorEntradaConsoleLayout.setHorizontalGroup(jpCorEntradaConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 34, 32767));
      jpCorEntradaConsoleLayout.setVerticalGroup(jpCorEntradaConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 29, 32767));
      this.jButton3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.escolherCorEntradaConsole();
         }
      });
      this.jButton3.setText(resourceMap.getString("jButton3.text"));
      this.jButton3.setName("jButton3");
      this.jpCorErrosConsole.setName("jpCorErrosConsole");
      GroupLayout jpCorErrosConsoleLayout = new GroupLayout(this.jpCorErrosConsole);
      this.jpCorErrosConsole.setLayout(jpCorErrosConsoleLayout);
      jpCorErrosConsoleLayout.setHorizontalGroup(jpCorErrosConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 34, 32767));
      jpCorErrosConsoleLayout.setVerticalGroup(jpCorErrosConsoleLayout.createParallelGroup(Alignment.LEADING).addGap(0, 29, 32767));
      this.jButton4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.escolherCorErrosConsole();
         }
      });
      this.jButton4.setText(resourceMap.getString("jButton4.text"));
      this.jButton4.setName("jButton4");
      this.visualizacaoFundoConsole.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
      this.visualizacaoFundoConsole.setName("visualizacaoFundoConsole");
      this.visualizacaoSaidaConsole.setText(resourceMap.getString("visualizacaoSaidaConsole.text"));
      this.visualizacaoSaidaConsole.setName("visualizacaoSaidaConsole");
      this.visualizacaoEntradaConsole.setText(resourceMap.getString("visualizacaoEntradaConsole.text"));
      this.visualizacaoEntradaConsole.setName("visualizacaoEntradaConsole");
      this.visualizacaoErroConsole.setText(resourceMap.getString("visualizacaoErroConsole.text"));
      this.visualizacaoErroConsole.setName("visualizacaoErroConsole");
      GroupLayout visualizacaoFundoConsoleLayout = new GroupLayout(this.visualizacaoFundoConsole);
      this.visualizacaoFundoConsole.setLayout(visualizacaoFundoConsoleLayout);
      visualizacaoFundoConsoleLayout.setHorizontalGroup(visualizacaoFundoConsoleLayout.createParallelGroup(Alignment.LEADING).addGroup(visualizacaoFundoConsoleLayout.createSequentialGroup().addContainerGap().addGroup(visualizacaoFundoConsoleLayout.createParallelGroup(Alignment.LEADING).addGroup(visualizacaoFundoConsoleLayout.createSequentialGroup().addComponent(this.visualizacaoSaidaConsole).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.visualizacaoEntradaConsole)).addComponent(this.visualizacaoErroConsole)).addContainerGap(249, 32767)));
      visualizacaoFundoConsoleLayout.setVerticalGroup(visualizacaoFundoConsoleLayout.createParallelGroup(Alignment.LEADING).addGroup(visualizacaoFundoConsoleLayout.createSequentialGroup().addContainerGap().addGroup(visualizacaoFundoConsoleLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.visualizacaoSaidaConsole).addComponent(this.visualizacaoEntradaConsole)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.visualizacaoErroConsole).addContainerGap(97, 32767)));
      this.jLabel12.setText(resourceMap.getString("jLabel12.text"));
      this.jLabel12.setName("jLabel12");
      GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
      this.jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.visualizacaoFundoConsole, -1, -1, 32767).addComponent(this.jLabel8).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jpCorFundoConsole, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jButton1)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jpCorSaidaConsole, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jButton2)).addComponent(this.jLabel10).addComponent(this.jLabel9).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jpCorEntradaConsole, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jButton3)).addComponent(this.jLabel11).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jpCorErrosConsole, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jButton4)).addComponent(this.jLabel12)).addContainerGap()));
      jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel8).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jpCorFundoConsole, -2, -1, -2).addComponent(this.jButton1)).addGap(18, 18, 18).addComponent(this.jLabel9).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jpCorSaidaConsole, -2, -1, -2).addComponent(this.jButton2)).addGap(18, 18, 18).addComponent(this.jLabel10).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jpCorEntradaConsole, -2, -1, -2).addComponent(this.jButton3)).addGap(18, 18, 18).addComponent(this.jLabel11).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jpCorErrosConsole, -2, -1, -2).addComponent(this.jButton4)).addPreferredGap(ComponentPlacement.RELATED, 107, 32767).addComponent(this.jLabel12).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.visualizacaoFundoConsole, -2, -1, -2).addContainerGap()));
      this.jTbOpcoes.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), this.jPanel1);
      this.jpBotoesOpcoes.setName("jpBotoesOpcoes");
      this.btCancelarOpcoes.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.cancelarOpcoes();
         }
      });
      this.btCancelarOpcoes.setText(resourceMap.getString("btCancelarOpcoes.text"));
      this.btCancelarOpcoes.setName("btCancelarOpcoes");
      this.btAplicarOpcoes.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.aplicarOpcoes();
         }
      });
      this.btAplicarOpcoes.setText(resourceMap.getString("btAplicarOpcoes.text"));
      this.btAplicarOpcoes.setName("btAplicarOpcoes");
      this.btSalvarOpcoes.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoSettings.this.aplicarOpcoesCloseSettings();
         }
      });
      this.btSalvarOpcoes.setText(resourceMap.getString("btSalvarOpcoes.text"));
      this.btSalvarOpcoes.setName("btSalvarOpcoes");
      GroupLayout jpBotoesOpcoesLayout = new GroupLayout(this.jpBotoesOpcoes);
      this.jpBotoesOpcoes.setLayout(jpBotoesOpcoesLayout);
      jpBotoesOpcoesLayout.setHorizontalGroup(jpBotoesOpcoesLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jpBotoesOpcoesLayout.createSequentialGroup().addContainerGap(394, 32767).addComponent(this.btCancelarOpcoes).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btAplicarOpcoes).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btSalvarOpcoes).addGap(25, 25, 25)));
      jpBotoesOpcoesLayout.setVerticalGroup(jpBotoesOpcoesLayout.createParallelGroup(Alignment.LEADING).addGroup(jpBotoesOpcoesLayout.createSequentialGroup().addContainerGap().addGroup(jpBotoesOpcoesLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.btSalvarOpcoes).addComponent(this.btAplicarOpcoes).addComponent(this.btCancelarOpcoes)).addContainerGap(-1, 32767)));
      this.btCancelarOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("btCancelarOpcoes.AccessibleContext.accessibleName"));
      this.btAplicarOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("btAplicarOpcoes.AccessibleContext.accessibleName"));
      GroupLayout jpOpcoesLayout = new GroupLayout(this.jpOpcoes);
      this.jpOpcoes.setLayout(jpOpcoesLayout);
      jpOpcoesLayout.setHorizontalGroup(jpOpcoesLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jpOpcoesLayout.createSequentialGroup().addGroup(jpOpcoesLayout.createParallelGroup(Alignment.TRAILING).addComponent(this.jpBotoesOpcoes, Alignment.LEADING, -1, -1, 32767).addComponent(this.jTbOpcoes, -2, 618, 32767)).addContainerGap()));
      jpOpcoesLayout.setVerticalGroup(jpOpcoesLayout.createParallelGroup(Alignment.LEADING).addGroup(jpOpcoesLayout.createSequentialGroup().addComponent(this.jTbOpcoes, -2, 576, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jpBotoesOpcoes, -2, -1, -2).addContainerGap(22, 32767)));
      this.jTbOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("jTbOpcoes.AccessibleContext.accessibleName"));
      this.jpBotoesOpcoes.getAccessibleContext().setAccessibleName(resourceMap.getString("jpBotoesOpcoes.AccessibleContext.accessibleName"));
      this.add(this.jpOpcoes);
   }

   private void listElementosMouseClicked2(MouseEvent evt) {
      this.elementoSelecionado();
   }

   private void elementoSelecionado() {
      String elementoSelecionadoValue = (String)this.listElementos.getSelectedValue();
      if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.COMENTARIO.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoComentario());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.CONSTANTE_TEXTO.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoConstanteTexto());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.CONSTANTE_NUMERICA.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoConstanteNum());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.FUNDO_EDITOR.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getCorFundoEditor());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.PALAVRA_CHAVE.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoPalavraChave());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.TIPO_DADO.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoTipoDado());
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.TEXTO_GERAL.getDescricao())) {
         this.updateAtributos(this.newCalangoSettings.getElementoTextoGeral());
      }

   }

   public void adicionarObservador(ITCCCalangoViewObserver view) {
      this.observadores.add(view);
   }

   public void removerObservador(ITCCCalangoViewObserver view) {
      this.observadores.remove(view);
   }

   public void notificarObservadores(CalangoSettings novaConfiguracao) {
      Iterator i = this.observadores.iterator();

      while(i.hasNext()) {
         ITCCCalangoViewObserver view = (ITCCCalangoViewObserver)i.next();
         view.atualizar(novaConfiguracao);
      }

   }

   private void updateAtributos(Object elementoSelecionado) {
      if (elementoSelecionado instanceof ElementoTexto) {
         ElementoTexto e = (ElementoTexto)elementoSelecionado;
         this.corSelecionada = e.getCor();
         this.checkItalico.setSelected(e.isStyleItalic());
         this.checkNegrito.setSelected(e.isStyleBold());
         this.checkSublinhado.setSelected(e.isSyleUnderline());
         this.permitirEdicaoAtributosEstiloFonte(true);
      } else {
         this.corSelecionada = (Color)elementoSelecionado;
         this.checkItalico.setSelected(false);
         this.checkNegrito.setSelected(false);
         this.checkSublinhado.setSelected(false);
         this.permitirEdicaoAtributosEstiloFonte(false);
      }

      this.jpCor.setBackground(this.corSelecionada);
      this.jpAtributos.repaint();
   }

   private void permitirEdicaoAtributosEstiloFonte(boolean permitir) {
      this.checkItalico.setEnabled(permitir);
      this.checkNegrito.setEnabled(permitir);
      this.checkSublinhado.setEnabled(permitir);
   }

   @Action
   public void updateElementoSelecionado() {
      String elementoSelecionadoValue = (String)this.listElementos.getSelectedValue();
      ElementoTexto eTexto = this.getElementoTextoSelecionado(elementoSelecionadoValue);
      if (eTexto == null) {
         if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.FUNDO_EDITOR.getDescricao())) {
            this.newCalangoSettings.setCorFundoEditor(this.corSelecionada);
         }
      } else {
         this.updateElementoTexto(eTexto);
      }

      this.aplicarOpcoesTextoExemplo();
   }

   private void updateElementoTexto(ElementoTexto e) {
      e.setCor(this.corSelecionada);
      e.setSyleUnderline(this.checkSublinhado.isSelected());
      byte estiloFonte;
      if (this.checkItalico.isSelected()) {
         if (this.checkNegrito.isSelected()) {
            estiloFonte = 3;
         } else {
            estiloFonte = 2;
         }
      } else if (this.checkNegrito.isSelected()) {
         estiloFonte = 1;
      } else {
         estiloFonte = 0;
      }

      e.setFont(new Font(this.newCalangoSettings.getFonte(), estiloFonte, this.newCalangoSettings.getTamanhoFonte()));
   }

   private ElementoTexto getElementoTextoSelecionado(String elementoSelecionadoValue) {
      if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.COMENTARIO.getDescricao())) {
         return this.newCalangoSettings.getElementoComentario();
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.CONSTANTE_TEXTO.getDescricao())) {
         return this.newCalangoSettings.getElementoConstanteTexto();
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.CONSTANTE_NUMERICA.getDescricao())) {
         return this.newCalangoSettings.getElementoConstanteNum();
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.PALAVRA_CHAVE.getDescricao())) {
         return this.newCalangoSettings.getElementoPalavraChave();
      } else if (elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.TIPO_DADO.getDescricao())) {
         return this.newCalangoSettings.getElementoTipoDado();
      } else {
         return elementoSelecionadoValue.equalsIgnoreCase(EnumCamposEditaveis.TEXTO_GERAL.getDescricao()) ? this.newCalangoSettings.getElementoTextoGeral() : null;
      }
   }

   @Action
   public void escolherCorFundoConsole() {
      Color cor = this.selecionaCor(this.getCorFundoConsole());
      if (cor != null) {
         this.setCorFundoConsole(cor);
      }

   }

   @Action
   public void escolherCorErrosConsole() {
      Color cor = this.selecionaCor(this.getCorErrosConsole());
      if (cor != null) {
         this.setCorErrosConsole(cor);
      }

   }

   @Action
   public void escolherCorEntradaConsole() {
      Color cor = this.selecionaCor(this.getCorEntradaConsole());
      if (cor != null) {
         this.setCorEntradaConsole(cor);
      }

   }

   @Action
   public void escolherCorSaidaConsole() {
      Color cor = this.selecionaCor(this.getCorSaidaConsole());
      if (cor != null) {
         this.setCorSaidaConsole(cor);
      }

   }

   private Color selecionaCor(Color selected) {
      JColorChooser var10000 = this.colorChooser;
      return JColorChooser.showDialog((Component)null, "Selecione a cor", selected);
   }

   private void setCorSaidaConsole(Color cor) {
      this.jpCorSaidaConsole.setBackground(cor);
      this.jpCorSaidaConsole.setOpaque(true);
      this.visualizacaoSaidaConsole.setForeground(cor);
   }

   private void setCorEntradaConsole(Color cor) {
      this.jpCorEntradaConsole.setBackground(cor);
      this.jpCorEntradaConsole.setOpaque(true);
      this.visualizacaoEntradaConsole.setForeground(cor);
   }

   private void setCorErrosConsole(Color cor) {
      this.jpCorErrosConsole.setBackground(cor);
      this.jpCorErrosConsole.setOpaque(true);
      this.visualizacaoErroConsole.setForeground(cor);
   }

   private void setCorFundoConsole(Color cor) {
      this.jpCorFundoConsole.setBackground(cor);
      this.jpCorFundoConsole.setOpaque(true);
      this.visualizacaoFundoConsole.setBackground(cor);
      this.visualizacaoFundoConsole.setOpaque(true);
   }

   private Color getCorSaidaConsole() {
      return this.visualizacaoSaidaConsole.getForeground();
   }

   private Color getCorEntradaConsole() {
      return this.visualizacaoEntradaConsole.getForeground();
   }

   private Color getCorErrosConsole() {
      return this.visualizacaoErroConsole.getForeground();
   }

   private Color getCorFundoConsole() {
      return this.visualizacaoFundoConsole.getBackground();
   }
}
