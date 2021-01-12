/*
 * TCCCalangoView.java
 */

package tcccalango.view;

import br.ucb.calango.api.publica.CalangoAPI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import java.util.EventObject;
import javax.swing.JComponent;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application.ExitListener;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.MatteBorder;

import javax.swing.text.StyleConstants;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;
import tcccalango.TCCCalangoApp;
import tcccalango.util.interpretador.CalangoIDEIn;
import tcccalango.util.interpretador.CalangoIDEOut;
import tcccalango.util.arquivo.IOUtil;
import tcccalango.util.componentes.IMonitorFrame;
import tcccalango.util.componentes.JPanelRodape;
import tcccalango.util.componentes.PrintUtilities;
import tcccalango.util.componentes.SimpleMonitorFrame;
import tcccalango.util.indentacao.IndentadorCalango;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.view.ajuda.AjudaFrame;
import tcccalango.view.interfaces.ITCCCalangoViewObserver;
import tcccalango.view.listeners.CalangoCaretListener;
import tcccalango.view.listeners.CarregaArquivoListener;
import tcccalango.view.util.CalangoViewUtil;

/**
 * The application's main frame.
 */
public class TCCCalangoView extends FrameView implements ITCCCalangoViewObserver{

    public TCCCalangoView(SingleFrameApplication app) {
        super(app);
        getFrame().setMinimumSize(new Dimension(800,600));
        initComponents();
        secondInit();
        app.addExitListener(new ExitListener() {
            public boolean canExit(EventObject event) {
                if(!jtAlgoritmo.getText().equals(getTextoSalvo())){
                    String msg = "";
                    if(getFileOpen() == null || getFileSave() == null){
                        msg = "O arquivo ainda não foi salvo. Deseja salvá-lo?";
                    }else{
                        msg = "O arquivo "+getFileSave().getName()+" foi modificado. Deseja salvá-lo?";
                    }
                    int retorno = JOptionPane.showConfirmDialog(null, msg);
                    if((retorno<=1)){
                        if(isEmExecucao){
                            paraExecucao();
                        }
                    }
                    switch(retorno){
                        case 0:{
                            saveArquivo();
                            return true;
                        }
                        case 1:{
                            return true;
                        }
                        case 2:{
                            return false;
                        }
                        case -1:
                            return false;
                    }
                    return true;
                }else{
                    return true;
                }
            }

            public void willExit(EventObject event) {
                System.exit(0);
            }
        });
        
        novoArquivo();
     }
    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = TCCCalangoApp.getApplication().getMainFrame();
            aboutBox = new TCCCalangoAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        TCCCalangoApp.getApplication().show(aboutBox);
    }
    
    @Action
    public void copiar(){
        jtAlgoritmo.copy();
    }
    @Action
    public void colar(){
        jtAlgoritmo.paste();
    }
    @Action
    public void recortar(){
        jtAlgoritmo.cut();
    }
    @Action
    public void showAbrirArquivo(){
        openFile();
    }        
    
    /**
     * Abre um arquivo obrigatoriamente .clg
     */
    private void openFile(){    
        FileDialog dialog = new FileDialog(getFrame(), "Escolha um arquivo");
        dialog.setMode(FileDialog.LOAD);
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        do{
            dialog.setVisible(true);
            if(dialog.getFile() != null){
                if(dialog.getFile().toLowerCase().endsWith(".clg")){
                    String fileName = dialog.getDirectory() + dialog.getFile();
                    carregaArquivo(new File(fileName));
                } else {
                    JOptionPane.showMessageDialog(getFrame(), "O arquivo deve ser .clg", "Impossível abrir arquivo", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }while(dialog.getFile() != null && !dialog.getFile().toLowerCase().endsWith(".clg"));
    }
    
    /**
     * Salva o algoritmo no arquivo já aberto (caso esteja aberto)
     */
    @Action
    public void saveArquivo(){
        if(getFileOpen()==null && getFileSave()==null){
            saveAs();
        }else{
            if(getFileOpen() != null && getFileSave()==null){
                setFileSave(getFileOpen());
            }

            IOUtil.salvarArquivo(getFileSave(), jtAlgoritmo.getText());
            jLabelMensagem.setText("Arquivo salvo com sucesso!");
            setTextoSalvo(jtAlgoritmo.getText());
            jpPanelRodape.updateUI();
            carregaArquivo(getFileSave());
         }
         
    }
    
    /**
     * Salva o algoritmo em um novo arquivo
     */
    @Action
    public void saveAs(){
            FileDialog dialog = new FileDialog(getFrame(), "Escolha um arquivo");
            dialog.setMode(FileDialog.SAVE);
            dialog.setModalityType(ModalityType.APPLICATION_MODAL);

            dialog.setVisible(true);
            if(dialog.getFile() != null){
                StringBuilder fileName = new StringBuilder(dialog.getDirectory() + dialog.getFile());
                if(!dialog.getFile().toLowerCase().endsWith(".clg"))
                    fileName.append(".clg");
                File file = new File(fileName.toString());
                setFileSave(file);
                IOUtil.salvarArquivo(getFileSave(), jtAlgoritmo.getText());
                setTextoSalvo(jtAlgoritmo.getText());
                jLabelMensagem.setText("Arquivo salvo com sucesso!");
                jpPanelRodape.updateUI();
                
                carregaArquivo(getFileSave());
            }
    }
    
    @Action
    public void newFile(){
        novoArquivo();
    }
        
    /**
     * Cria um novo arquivo
     */
    private void novoArquivo(){    
            //calangoSettings = new CalangoSettings();
        fileOpen = null;
        carregaAlgoritmo("Calango - Sem Título.clg", calangoSettings.getTextoInicio());
    }
    
    /**
     * Verifica se o algoritmo aberto deve ser salvo, e caso o usuário prossiga, carrega um novo algoritmo no editor
     * @param titulo
     * @param algoritmo 
     */
    private static final int SAVE = 0;
    private static final int IGNORE = 1;
    private void carregaAlgoritmo(String titulo, String algoritmo){
        switch(shouldSave()){
            case SAVE:{
                saveArquivo();
            }
                
            case IGNORE:{
                if(isEmExecucao){
                    paraExecucao();
                }
                
                tpConsole.clear();
                jtAlgoritmo.setText(algoritmo);
                setTextoSalvo(jtAlgoritmo.getText());
                jLabelMensagem.setText("");
                jpPanelRodape.updateUI();
                fileSave = null;
                getFrame().setTitle(titulo);

                recarregaMenuArquivosRecentes();
            }
        }
    }
    
    /**
     * Verifica se o algoritmo aberto foi salvo e em caso negativo, verifica se o usuári deseja salvá-lo
     * @return 1 = Opção Não, 2 = Opção Cancelar, 0 = Opção Sim
     */
    private int shouldSave(){
        if(!jtAlgoritmo.getText().equals(getTextoSalvo())){
            String msg = "";
            if(getFileOpen() == null || getFileSave() == null){
                msg = "O arquivo ainda não foi salvo. Deseja salvá-lo?";
            }else{
                msg = "O arquivo "+getFileSave().getName()+" foi modificado. Deseja salvá-lo?";
            }
            return JOptionPane.showConfirmDialog(null, msg);
        }
        return IGNORE;
    }
    
    
    @Action
    public void showNumeroLinhas(){
        scroolAlgoritmo.setLineNumbersEnabled(!scroolAlgoritmo.getLineNumbersEnabled());
    }
    
    @Action
    public void showSettings(){
        tccCalangoSettings = new TCCCalangoSettings(TCCCalangoApp.getApplication(), calangoSettings);
        tccCalangoSettings.adicionarObservador(this);
        TCCCalangoApp.getApplication().show(tccCalangoSettings);
    }
    
    @Action
    public void showAjuda(){
        TCCCalangoApp.getApplication().show(ajudaFrame);
    }
    
    /**
     * Metodo para execuÃ§Ã£o do algoritmo
     */
    @Action
    public void executar(){
        isEmExecucao = true;
        mudaBotaoPlay();
        
        tpConsole.clear();
        tpConsole.requestFocus();
        
        try {
            calangoThread = new Thread(new Runnable(){
                public void run() {
                    CalangoAPI.setIn(new CalangoIDEIn(tpConsole));
                    CalangoAPI.setOut(new CalangoIDEOut(tpConsole));
                    CalangoAPI.interpretar(jtAlgoritmo.getText());
                    
                    if (isEmExecucao){
                        paraExecucao();
                    }
                }
            });

            calangoThread.start();
            showConsole();
        } catch (RuntimeException e) {
            e.printStackTrace();
            calangoThread.interrupt();
        }
        
    }
    
    @Action
    public void paraExecucao(){
        //Intenrompe a execuÃ§Ã£o da thread
        isEmExecucao = false;
        if (!Thread.currentThread().equals(calangoThread)){
            calangoThread.interrupt();
        }
        mudaBotaoStop();
        tpConsole.append("\n--\nFim de execução!");
        jpPanelRodape.updateUI();
    }
    
    private IMonitorFrame monitor;
    
    @Action
    public void showConsole(){
         if (isAbrirConsoleExterna()){
             showConsoleExterna();
        }else{
             showConsoleInterna();
        }
        tpConsole.requestFocus();
        tpConsole.updateUI();
    }
    
    private void showConsoleExterna(){
        hideConsoleInterna();
        
        tpConsole.setBackground(Color.BLACK);
        tpConsole.setCaretColor(Color.WHITE);

        if (!monitor.isConsole(jpConsole)){
            monitor.setConsole(jpConsole);
        }
        monitor.setVisible(true);
        monitor.requestFocusInWindow();
    }
    
    private void hideConsoleExterna(){
        monitor.setVisible(false);
        monitor.removeConsole();
    }
    
    private void showConsoleInterna(){
        hideConsoleExterna();
        
        tpConsole.setBackground(Color.WHITE);
        tpConsole.setCaretColor(Color.BLACK);

        ((JPanelRodape) jpPanelRodape).getBorder().setBorderOpaque(true);
        jSplitHorizontal.setDividerSize(3);
        if (!jSplitHorizontal.isAncestorOf(jpConsole)){
            jSplitHorizontal.add(jpConsole);
        }
        scrollConsole.getVerticalScrollBar().setValue(scrollConsole.getVerticalScrollBar().getMaximum());
    }
    
    private void hideConsoleInterna(){
        ((JPanelRodape) jpPanelRodape).getBorder().setBorderOpaque(false);
        jSplitHorizontal.setDividerSize(0);
        jSplitHorizontal.remove(jpConsole);
    }
        
    
    /**
     * Muda o botÃ£o de stop para play.
     **/
    private void mudaBotaoStop(){
        //Retorna ao botÃ£o de executar
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoView.class);
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getActionMap(TCCCalangoView.class, this);
        btPlay.setAction(actionMap.get("executar")); // NOI18N
        btPlay.setText(resourceMap.getString("btPlay.text")); // NOI18N
        btPlay.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btPlay.setFocusable(false);
        btPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPlay.setName("btPlay"); // NOI18N
        btPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        if(CalangoViewUtil.isMacOS()){
            btPlay.setIcon(resourceMap.getIcon("btMacPlay.icon")); // NOI18N
        }else{
            btPlay.setIcon(resourceMap.getIcon("btPlay.icon")); // NOI18N
        }    
        btPlay.updateUI();
    }
        /**
     * Muda o botÃ£o de play para stop.
     **/
    private void mudaBotaoPlay(){
        //Troca o botÃ£o de play pelo de stop
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoView.class);
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getActionMap(TCCCalangoView.class, this);
        btPlay.setAction(actionMap.get("paraExecucao")); // NOI18N
        btPlay.setText(resourceMap.getString("btPlay.text")); // NOI18N
        btPlay.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btPlay.setFocusable(false);
        btPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPlay.setName("btStop"); // NOI18N
        btPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        if(CalangoViewUtil.isMacOS()){
            btPlay.setIcon(resourceMap.getIcon("btMacStop.icon")); // NOI18N
        }else{
            btPlay.setIcon(resourceMap.getIcon("btStop.icon")); // NOI18N
        }
        btPlay.updateUI();
        
    }
    
    @Action
    public void mudaBotaoConsole(){
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoView.class);

        if(isAbrirConsoleInterna()){
//            abreJanelaConsole = false;
            if(CalangoViewUtil.isMacOS()){
                btMudarConsole.setIcon(resourceMap.getIcon("btMacConsoleExterno.icon"));
            } else {
                btMudarConsole.setIcon(resourceMap.getIcon("btExecutarConsoleExerno.icon"));
            }
            consoleASerAberta = EXTERNA;
//            tpConsole.setBackground(Color.BLACK);
//            tpConsole.setOutputTextColor(Color.WHITE);
//            monitor.setConsole(jpConsole);
//            monitor.requestFocusInWindow();
        }else{
//            abreJanelaConsole = true;
            if(CalangoViewUtil.isMacOS()){
                btMudarConsole.setIcon(resourceMap.getIcon("btMacConsole.icon"));
            } else {
                btMudarConsole.setIcon(resourceMap.getIcon("btExecutarConsoleInterno.icon"));
            }
            consoleASerAberta = INTERNA;
//            tpConsole.setBackground(Color.WHITE);
//            tpConsole.setOutputTextColor(Color.BLACK);
//           jSplitHorizontal.add(jpConsole);
//            monitor.requestFocusInWindow();
            
        }
//        tpConsole.repaint();
//        scrollConsole.getVerticalScrollBar().setValue(scrollConsole.getVerticalScrollBar().getMaximum());
        //showConsole();
        
        
    }
    
    @Action
    public void identar(){
       jtAlgoritmo.setText(indentador.identa(jtAlgoritmo.getText()));
    }
    
    private void secondInit(){
        jpAlgoritmo = new JPanel();
//        jpAlgoritmo.setFont(new Font("monospaced", 0, 12));
        jpAlgoritmo.setAutoscrolls(true);
        jpAlgoritmo.setName("jpAlgoritmo"); // NOI18N
        jpAlgoritmo.setMinimumSize(new Dimension(500,400));
        jpAlgoritmo.setLayout(new BorderLayout());
        jtAlgoritmo = new RSyntaxTextArea();
        //jtAlgoritmo.setFont(new Font("Matura MT Script Capitals", Font.TYPE1_FONT, 12));
        jtAlgoritmo.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CALANGO);
        jtAlgoritmo.setTabSize(4);
        jtAlgoritmo.setPaintTabLines(true);
        jtAlgoritmo.setMargin(new Insets(0, 5, 0, 0));
        jtAlgoritmo.addCaretListener(new CalangoCaretListener(jtAlgoritmo, jLabelLinha));
        scroolAlgoritmo= new RTextScrollPane(jtAlgoritmo);
        scroolAlgoritmo.getGutter().setBackground(new Color(229, 233, 238));
        scroolAlgoritmo.setBorder(new MatteBorder(1, 0, 1, 0, new Color(112, 112, 112)));
        scroolAlgoritmo.setVerticalScrollBarPolicy(ScrollPaneLayout.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpAlgoritmo.add(BorderLayout.CENTER, scroolAlgoritmo);
        //jpEscopo.setPreferredSize(new Dimension(0,0));
        jSplitHorizontal.setTopComponent(jpAlgoritmo);  
        jpPrincipal.setLayout(new BorderLayout());
        jpPrincipal.add(BorderLayout.NORTH, barraFerramento);
        jpPrincipal.add(BorderLayout.CENTER, jsplitVertical);
        jpPrincipal.add(BorderLayout.SOUTH, jpPanelRodape);
        jSplitHorizontal.remove(jpConsole);
        
        // Escondendo botão não implementados
        btSearch.setVisible(false);
        
        if(CalangoViewUtil.isMacOS()){
           personalizaOSMac();
        }
        
        //Alterando cor de um determinado local
//      SyntaxScheme scheme = jtAlgoritmo.getSyntaxScheme();
//      cheme.styles[Token.]
        iniciaCalangoSettings();
        //calangoSettings = new CalangoSettings();
        
        //jSplitHorizontal.remove(jpConsole);
        //FIXME: Remover essa linnha quando implementar a tabela de escopo.
        jpEscopo.setVisible(false);
        btConsole.setVisible(false);
        
        setTextoSalvo(jtAlgoritmo.getText());
       /* try {
            jtAlgoritmo.addLineHighlight(1, Color.BLUE);
        } catch (BadLocationException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        
        tpConsole.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                if (!isEmExecucao){
                    if (isAbrirConsoleInterna()){
                        hideConsoleInterna();
                    }else{
                        hideConsoleExterna();
                    }
                }
            }
            
        });

        tpConsole.setOutputTextColor(new Color(150, 150, 150));
        StyleConstants.setFontSize(tpConsole.getOutput(), 14);
        StyleConstants.setFontSize(tpConsole.getInput(), 14);
        scrollConsole.getVerticalScrollBar().addAdjustmentListener(tpConsole);
        monitor = new SimpleMonitorFrame(new SimpleMonitorFrame.CloseListener() {
            
            @Override
            public void close() {
                if (!isEmExecucao){
                    showConsole();
                    paraExecucao();
                    monitor.setVisible(false);
                }
            }

            @Override
            public void esc() {
                if (isEmExecucao){
                    paraExecucao();
                }
            }
            
        });
//        monitor = new CalangoMonitorFrame(new CalangoMonitorFrame.ButtonObserver() {
//            public void clicked() {
//                showConsole();
//                paraExecucao();
//                monitor.setVisible(false);
//            }
//        }, jpConsole);
        
        
        indentador = new IndentadorCalango();
        jtAlgoritmo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK), "identar");
        jtAlgoritmo.getActionMap().put("identar", new AbstractAction(){

             @Override
             public void actionPerformed(ActionEvent e){
                 indenta();
             }

          });
        
        ajudaFrame = AjudaFrame.output();
    }

    @Action
    public void indenta() {
        int position = jtAlgoritmo.getCaretPosition();
        jtAlgoritmo.setText(indentador.identa(jtAlgoritmo.getText()));
        jtAlgoritmo.setCaretPosition(position);
    }
    
    /**
     * Este método é responsável por recuperar a configuração do Calango e 
     * configurá-lo a partir desta configuração. Sendo que caso este arquivo  
     * não exista, ele será criado com as configurações padrão.
     * @author Jéssica Luanne
     */
    private void iniciaCalangoSettings(){
        File arquivo = new File("calango.conf");
        try {
            if(arquivo.exists()){
                  calangoSettings = IOUtil.getConfigurationFromConfigurationFile();
            }else{
                calangoSettings = obtemConfiguracoesDefault();
                IOUtil.saveConfigurationFile(calangoSettings);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        configuraCalangoWithSettings();
    }
    
    /**
     * Este método obtem a configuração padrão do Calango
     * @return CalangoSettings
     * @author Jéssica Luanne
     */
    private CalangoSettings obtemConfiguracoesDefault(){
        CalangoSettings cs = new CalangoSettings();
        
        SyntaxScheme scheme = jtAlgoritmo.getSyntaxScheme();
        
        cs.setCorTipoDado(scheme.styles[Token.DATA_TYPE].foreground);
        cs.setCorComentario(scheme.styles[Token.COMMENT_EOL].foreground);
        cs.setCorConstanteNum(scheme.styles[Token.LITERAL_NUMBER_DECIMAL_INT].foreground);
        cs.setCorConstanteTexto(scheme.styles[Token.LITERAL_STRING_DOUBLE_QUOTE].foreground);
        cs.setCorPalavraChave(scheme.styles[Token.RESERVED_WORD].foreground);
        cs.setCorFundoEditor(jtAlgoritmo.getBackground());
        cs.setCorTextoGeral(jtAlgoritmo.getForeground());
        
        cs.setFonte("monospaced");
        cs.setTamanhoFonte(12);
        cs.setEstiloFonte(0);
        cs.setText(null);
        
        return cs;
    }
    
    @Action
    public void imprimir(){
        PrintUtilities.printComponent(jtAlgoritmo);
    }
    
    /**
     * Este método atualiza o esquema de configuração do Calango a partir das
     * informações contidas em calangoSettings
     * @author Jéssica Luanne
     */
    private void configuraCalangoWithSettings() {
        SyntaxScheme scheme = jtAlgoritmo.getSyntaxScheme();
        CalangoViewUtil.configuraSyntaxWithSettings(calangoSettings, jtAlgoritmo);
        jtAlgoritmo.setText(calangoSettings.getTextoInicio());
    }
    
    /**
     * Recarrega o menu de arquivos recentes usando os arquivos contidos na lista
     * do arquivo de configurações
     */
    public void recarregaMenuArquivosRecentes(){
        menuDocRecentes.removeAll();
        
        for (String file : calangoSettings.getDocumentosRecentes()) {
            JMenuItem item = new JMenuItem(file);
            item.addActionListener(new CarregaArquivoListener(new File(file)) {
                public void actionPerformed(ActionEvent e) {
                    carregaArquivo(getFile());
                }
            });
            menuDocRecentes.add(item);
        }
    }
    /**
     * 
     */
    private void personalizaOSMac(){
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoView.class);
        
        barraFerramento.setPreferredSize(new Dimension(239, 32));
        
        btNewFile.putClientProperty("JButton.buttonType", "textured");
        btOpenFile.putClientProperty("JButton.buttonType", "textured");
        btSaveAs.putClientProperty("JButton.buttonType", "textured");
        btSearch.putClientProperty("JButton.buttonType", "textured");
        btIndentar.putClientProperty("JButton.buttonType", "textured");
        btDiminuirLetra.putClientProperty("JButton.buttonType", "textured");
        btAumentarLetra.putClientProperty("JButton.buttonType", "textured");
        btConsole.putClientProperty("JButton.buttonType", "textured");
        btMudarConsole.putClientProperty("JButton.buttonType", "textured");
        
        btDiminuirLetra.putClientProperty("JButton.buttonType", "segmentedTextured");
        btDiminuirLetra.putClientProperty("JButton.segmentPosition", "first");
        btAumentarLetra.putClientProperty("JButton.buttonType", "segmentedTextured");
        btAumentarLetra.putClientProperty("JButton.segmentPosition", "last");
        
        btPlay.putClientProperty("JButton.buttonType", "textured");
        btOpenFile.setIcon(resourceMap.getIcon("btMacOpenFile.icon"));
        btSaveAs.setIcon(resourceMap.getIcon("btMacSaveAs.icon")); // NOI18N
        btSearch.setIcon(resourceMap.getIcon("btMacSearch.icon")); // NOI18N
        btIndentar.setIcon(resourceMap.getIcon("btMacIdentar.icon"));
        btDiminuirLetra.setIcon(resourceMap.getIcon("btMacDiminuirLetra.icon"));
        btAumentarLetra.setIcon(resourceMap.getIcon("btMacAumentarLetra.icon"));
        btMudarConsole.setIcon(resourceMap.getIcon("btMacConsoleExterno.icon"));
        
        btPlay.setIcon(resourceMap.getIcon("btMacPlay.icon")); // NOI18N
        btNewFile.setIcon(resourceMap.getIcon("btMacNewFile.icon")); // NOI18N
        
        jtAlgoritmo.setFont(new Font("Monaco", 0, 12));
        
        getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new JPanel();
        barraFerramento = new javax.swing.JToolBar();
        btNewFile = new javax.swing.JButton();
        btOpenFile = new javax.swing.JButton();
        btSaveAs = new javax.swing.JButton();
        btPrint = new javax.swing.JButton();
        jSeparadorArquivo = new javax.swing.JToolBar.Separator();
        btIndentar = new javax.swing.JButton();
        btDiminuirLetra = new javax.swing.JButton();
        btAumentarLetra = new javax.swing.JButton();
        btMudarConsole = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btSearch = new javax.swing.JButton();
        btPlay = new javax.swing.JButton();
        jsplitVertical = new javax.swing.JSplitPane();
        jpEscopo = new JPanel();
        jSplitHorizontal = new javax.swing.JSplitPane();
        jpAlgoritmo = new JPanel();
        jpConsole = new JPanel();
        scrollConsole = new javax.swing.JScrollPane();
        tpConsole = new tcccalango.util.componentes.CalangoConsole();
        jpPanelRodape = new JPanelRodape();
        jLabelLinha = new javax.swing.JLabel();
        jLabelMensagem = new javax.swing.JLabel();
        btConsole = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        javax.swing.JMenu menuArquivo = new javax.swing.JMenu();
        JMenuItem abrirMenuItem = new JMenuItem();
        novoMenuItem = new JMenuItem();
        salvarMenuItem = new JMenuItem();
        salvarComoMenuItem = new JMenuItem();
        ImprimirMenuItem = new JMenuItem();
        menuDocRecentes = new javax.swing.JMenu();
        salvarComoMenuItem1 = new JMenuItem();
        javax.swing.JMenu menuEditar = new javax.swing.JMenu();
        JMenuItem copiarMenuItem = new JMenuItem();
        colarMenuItem = new JMenuItem();
        recortarMenuItem = new JMenuItem();
        identarMenuItem = new JMenuItem();
        javax.swing.JMenu menuAlgoritmo = new javax.swing.JMenu();
        JMenuItem executarMenuItem = new JMenuItem();
        javax.swing.JMenu menuFerramentas = new javax.swing.JMenu();
        numeroLinhas = new JMenuItem();
        JMenuItem exitMenuItem3 = new JMenuItem();
        javax.swing.JMenu menuAjuda = new javax.swing.JMenu();
        jMenuItem1 = new JMenuItem();
        JMenuItem aboutMenuItem = new JMenuItem();

        jpPrincipal.setName("jpPrincipal"); // NOI18N

        barraFerramento.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 7, 0, 7));
        barraFerramento.setFloatable(false);
        barraFerramento.setRollover(true);
        barraFerramento.setFocusable(false);
        barraFerramento.setName("barraFerramento"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getActionMap(TCCCalangoView.class, this);
        btNewFile.setAction(actionMap.get("newFile")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoView.class);
        btNewFile.setIcon(resourceMap.getIcon("btNewFile.icon")); // NOI18N
        btNewFile.setText(resourceMap.getString("btNewFile.text")); // NOI18N
        btNewFile.setToolTipText(resourceMap.getString("btNewFile.toolTipText")); // NOI18N
        btNewFile.setAlignmentY(0.0F);
        btNewFile.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btNewFile.setFocusable(false);
        btNewFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNewFile.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btNewFile.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btNewFile.setName(""); // NOI18N
        btNewFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btNewFile);

        btOpenFile.setAction(actionMap.get("showAbrirArquivo")); // NOI18N
        btOpenFile.setIcon(resourceMap.getIcon("btOpenFile.icon")); // NOI18N
        btOpenFile.setText(resourceMap.getString("btOpenFile.text")); // NOI18N
        btOpenFile.setAlignmentY(0.0F);
        btOpenFile.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btOpenFile.setFocusable(false);
        btOpenFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btOpenFile.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btOpenFile.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btOpenFile.setName(""); // NOI18N
        btOpenFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btOpenFile);

        btSaveAs.setAction(actionMap.get("saveArquivo")); // NOI18N
        btSaveAs.setIcon(resourceMap.getIcon("btSaveAs.icon")); // NOI18N
        btSaveAs.setText(resourceMap.getString("btSaveAs.text")); // NOI18N
        btSaveAs.setAlignmentY(0.0F);
        btSaveAs.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btSaveAs.setFocusable(false);
        btSaveAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveAs.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btSaveAs.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btSaveAs.setName("btSaveAs"); // NOI18N
        barraFerramento.add(btSaveAs);

        btPrint.setAction(actionMap.get("imprimir")); // NOI18N
        btPrint.setIcon(resourceMap.getIcon("btPrint.icon")); // NOI18N
        btPrint.setText(resourceMap.getString("btPrint.text")); // NOI18N
        btPrint.setToolTipText(resourceMap.getString("btPrint.toolTipText")); // NOI18N
        btPrint.setAlignmentY(0.0F);
        btPrint.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btPrint.setFocusable(false);
        btPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPrint.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btPrint.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btPrint.setName("btPrint"); // NOI18N
        btPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btPrint);

        jSeparadorArquivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jSeparadorArquivo.setName("jSeparadorArquivo"); // NOI18N
        barraFerramento.add(jSeparadorArquivo);

        btIndentar.setAction(actionMap.get("indenta")); // NOI18N
        btIndentar.setIcon(resourceMap.getIcon("btIndentar.icon")); // NOI18N
        btIndentar.setAlignmentY(0.0F);
        btIndentar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btIndentar.setFocusable(false);
        btIndentar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btIndentar.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btIndentar.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btIndentar.setName("btIndentar"); // NOI18N
        btIndentar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btIndentar);

        btDiminuirLetra.setIcon(resourceMap.getIcon("btDiminuirLetra.icon")); // NOI18N
        btDiminuirLetra.setAlignmentY(0.0F);
        btDiminuirLetra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btDiminuirLetra.setFocusable(false);
        btDiminuirLetra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDiminuirLetra.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btDiminuirLetra.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btDiminuirLetra.setName("btDiminuirLetra"); // NOI18N
        barraFerramento.add(btDiminuirLetra);

        btAumentarLetra.setIcon(resourceMap.getIcon("btAumentarLetra.icon")); // NOI18N
        btAumentarLetra.setAlignmentY(0.0F);
        btAumentarLetra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btAumentarLetra.setFocusable(false);
        btAumentarLetra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAumentarLetra.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btAumentarLetra.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btAumentarLetra.setName("btAumentarLetra"); // NOI18N
        barraFerramento.add(btAumentarLetra);

        btMudarConsole.setAction(actionMap.get("mudaBotaoConsole")); // NOI18N
        btMudarConsole.setIcon(resourceMap.getIcon("btMudarConsole.icon")); // NOI18N
        btMudarConsole.setAlignmentY(0.0F);
        btMudarConsole.setFocusable(false);
        btMudarConsole.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMudarConsole.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btMudarConsole.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btMudarConsole.setName("btMudarConsole"); // NOI18N
        btMudarConsole.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btMudarConsole);

        jSeparator2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jSeparator2.setName("jSeparator2"); // NOI18N
        barraFerramento.add(jSeparator2);

        btSearch.setIcon(resourceMap.getIcon("btSearch.icon")); // NOI18N
        btSearch.setAlignmentY(0.0F);
        btSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btSearch.setFocusable(false);
        btSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSearch.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btSearch.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btSearch.setName("btSearch"); // NOI18N
        barraFerramento.add(btSearch);

        btPlay.setAction(actionMap.get("executar")); // NOI18N
        btPlay.setIcon(resourceMap.getIcon("btPlay.icon")); // NOI18N
        btPlay.setText(resourceMap.getString("btPlay.text")); // NOI18N
        btPlay.setAlignmentY(0.0F);
        btPlay.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        btPlay.setFocusable(false);
        btPlay.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPlay.setMaximumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btPlay.setMinimumSize(CalangoViewUtil.isMacOS()?null:new Dimension(24, 24));
        btPlay.setName("btPlay"); // NOI18N
        btPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraFerramento.add(btPlay);

        jsplitVertical.setBorder(null);
        jsplitVertical.setDividerLocation(400);
        jsplitVertical.setDividerSize(0);
        jsplitVertical.setName("jsplitVertical"); // NOI18N
        jsplitVertical.setPreferredSize(new Dimension(610, 445));

        jpEscopo.setMaximumSize(new Dimension(0, 0));
        jpEscopo.setMinimumSize(new Dimension(0, 0));
        jpEscopo.setName("jpEscopo"); // NOI18N
        jpEscopo.setPreferredSize(new Dimension(0, 0));

        javax.swing.GroupLayout jpEscopoLayout = new javax.swing.GroupLayout(jpEscopo);
        jpEscopo.setLayout(jpEscopoLayout);
        jpEscopoLayout.setHorizontalGroup(
            jpEscopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jpEscopoLayout.setVerticalGroup(
            jpEscopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        jsplitVertical.setRightComponent(jpEscopo);

        jSplitHorizontal.setBorder(null);
        jSplitHorizontal.setDividerLocation(-10);
        jSplitHorizontal.setDividerSize(0);
        jSplitHorizontal.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitHorizontal.setResizeWeight(1.0);
        jSplitHorizontal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSplitHorizontal.setName("jSplitHorizontal"); // NOI18N

        jpAlgoritmo.setAlignmentX(2.0F);
        jpAlgoritmo.setAutoscrolls(true);
        jpAlgoritmo.setFont(resourceMap.getFont("jpAlgoritmo.font")); // NOI18N
        jpAlgoritmo.setMinimumSize(new Dimension(0, 320));
        jpAlgoritmo.setName("jpAlgoritmo"); // NOI18N
        jpAlgoritmo.setPreferredSize(new Dimension(255, 150));

        javax.swing.GroupLayout jpAlgoritmoLayout = new javax.swing.GroupLayout(jpAlgoritmo);
        jpAlgoritmo.setLayout(jpAlgoritmoLayout);
        jpAlgoritmoLayout.setHorizontalGroup(
            jpAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jpAlgoritmoLayout.setVerticalGroup(
            jpAlgoritmoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        jSplitHorizontal.setTopComponent(jpAlgoritmo);

        jpConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpConsole.setMinimumSize(new Dimension(255, 150));
        jpConsole.setName("jpConsole"); // NOI18N
        jpConsole.setPreferredSize(new Dimension(255, 150));
        jpConsole.setLayout(new java.awt.GridLayout(1, 0));

        scrollConsole.setBorder(null);
        scrollConsole.setName("scrollConsole"); // NOI18N

        tpConsole.setName("tpConsole"); // NOI18N
        scrollConsole.setViewportView(tpConsole);

        jpConsole.add(scrollConsole);

        jSplitHorizontal.setRightComponent(jpConsole);

        jsplitVertical.setLeftComponent(jSplitHorizontal);

        jpPanelRodape.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        jpPanelRodape.setName("jpPanelRodape"); // NOI18N
        jpPanelRodape.setPreferredSize(new Dimension(853, 23));
        jpPanelRodape.setLayout(new javax.swing.BoxLayout(jpPanelRodape, javax.swing.BoxLayout.LINE_AXIS));

        jLabelLinha.setName("jLabelLinha"); // NOI18N
        jpPanelRodape.add(jLabelLinha);

        jLabelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelMensagem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelMensagem.setMaximumSize(null);
        jLabelMensagem.setMinimumSize(null);
        jLabelMensagem.setName("jLabelMensagem"); // NOI18N
        jLabelMensagem.setPreferredSize(null);
        jpPanelRodape.add(jLabelMensagem);

        btConsole.setAction(actionMap.get("showConsole")); // NOI18N
        btConsole.setIcon(resourceMap.getIcon("btConsole.icon")); // NOI18N
        btConsole.setText(resourceMap.getString("btConsole.text")); // NOI18N
        btConsole.setAlignmentY(0.0F);
        btConsole.setFocusable(false);
        btConsole.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btConsole.setName("btConsole"); // NOI18N
        btConsole.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btConsole.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jpPanelRodape.add(btConsole);

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpPanelRodape, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jsplitVertical, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(barraFerramento, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addComponent(barraFerramento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsplitVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jpPanelRodape, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        menu.setName("menu"); // NOI18N

        menuArquivo.setText(resourceMap.getString("menuArquivo.text")); // NOI18N
        menuArquivo.setName("menuArquivo"); // NOI18N

        abrirMenuItem.setAction(actionMap.get("showAbrirArquivo")); // NOI18N
        abrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        abrirMenuItem.setIcon(resourceMap.getIcon("abrirMenuItem.icon")); // NOI18N
        abrirMenuItem.setText(resourceMap.getString("abrirMenuItem.text")); // NOI18N
        abrirMenuItem.setName("abrirMenuItem"); // NOI18N
        menuArquivo.add(abrirMenuItem);

        novoMenuItem.setAction(actionMap.get("newFile")); // NOI18N
        novoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        novoMenuItem.setIcon(resourceMap.getIcon("novoMenuItem.icon")); // NOI18N
        novoMenuItem.setText(resourceMap.getString("novoMenuItem.text")); // NOI18N
        novoMenuItem.setName("novoMenuItem"); // NOI18N
        menuArquivo.add(novoMenuItem);

        salvarMenuItem.setAction(actionMap.get("saveArquivo")); // NOI18N
        salvarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        salvarMenuItem.setIcon(resourceMap.getIcon("salvarMenuItem.icon")); // NOI18N
        salvarMenuItem.setText(resourceMap.getString("salvarMenuItem.text")); // NOI18N
        salvarMenuItem.setName("salvarMenuItem"); // NOI18N
        menuArquivo.add(salvarMenuItem);

        salvarComoMenuItem.setAction(actionMap.get("saveAs")); // NOI18N
        salvarComoMenuItem.setIcon(resourceMap.getIcon("salvarComoMenuItem.icon")); // NOI18N
        salvarComoMenuItem.setText(resourceMap.getString("salvarComoMenuItem.text")); // NOI18N
        salvarComoMenuItem.setName("salvarComoMenuItem"); // NOI18N
        menuArquivo.add(salvarComoMenuItem);

        ImprimirMenuItem.setAction(actionMap.get("imprimir")); // NOI18N
        ImprimirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        ImprimirMenuItem.setIcon(resourceMap.getIcon("ImprimirMenuItem.icon")); // NOI18N
        ImprimirMenuItem.setText(resourceMap.getString("ImprimirMenuItem.text")); // NOI18N
        ImprimirMenuItem.setName("ImprimirMenuItem"); // NOI18N
        menuArquivo.add(ImprimirMenuItem);

        menuDocRecentes.setText(resourceMap.getString("menuDocRecentes.text")); // NOI18N
        menuDocRecentes.setName("menuDocRecentes"); // NOI18N
        menuArquivo.add(menuDocRecentes);

        salvarComoMenuItem1.setAction(actionMap.get("quit")); // NOI18N
        salvarComoMenuItem1.setIcon(resourceMap.getIcon("sairMenuItem.icon")); // NOI18N
        salvarComoMenuItem1.setText(resourceMap.getString("sairMenuItem.text")); // NOI18N
        salvarComoMenuItem1.setName("sairMenuItem"); // NOI18N
        menuArquivo.add(salvarComoMenuItem1);

        menu.add(menuArquivo);

        menuEditar.setText(resourceMap.getString("menuEditar.text")); // NOI18N
        menuEditar.setName("menuEditar"); // NOI18N

        copiarMenuItem.setAction(actionMap.get("copiar")); // NOI18N
        copiarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copiarMenuItem.setIcon(resourceMap.getIcon("copiarMenuItem.icon")); // NOI18N
        copiarMenuItem.setText(resourceMap.getString("copiarMenuItem.text")); // NOI18N
        copiarMenuItem.setName("copiarMenuItem"); // NOI18N
        menuEditar.add(copiarMenuItem);

        colarMenuItem.setAction(actionMap.get("colar")); // NOI18N
        colarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        colarMenuItem.setIcon(resourceMap.getIcon("colarMenuItem.icon")); // NOI18N
        colarMenuItem.setText(resourceMap.getString("colarMenuItem.text")); // NOI18N
        colarMenuItem.setName("colarMenuItem"); // NOI18N
        menuEditar.add(colarMenuItem);

        recortarMenuItem.setAction(actionMap.get("recortar")); // NOI18N
        recortarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        recortarMenuItem.setIcon(resourceMap.getIcon("recortarMenuItem.icon")); // NOI18N
        recortarMenuItem.setText(resourceMap.getString("recortarMenuItem.text")); // NOI18N
        recortarMenuItem.setName("recortarMenuItem"); // NOI18N
        menuEditar.add(recortarMenuItem);

        identarMenuItem.setAction(actionMap.get("identar")); // NOI18N
        identarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
        identarMenuItem.setIcon(resourceMap.getIcon("identarMenuItem.icon")); // NOI18N
        identarMenuItem.setText(resourceMap.getString("identarMenuItem.text")); // NOI18N
        identarMenuItem.setToolTipText(resourceMap.getString("identarMenuItem.toolTipText")); // NOI18N
        identarMenuItem.setName("identarMenuItem"); // NOI18N
        menuEditar.add(identarMenuItem);

        menu.add(menuEditar);

        menuAlgoritmo.setText(resourceMap.getString("menuAlgoritmo.text")); // NOI18N
        menuAlgoritmo.setName("menuAlgoritmo"); // NOI18N

        executarMenuItem.setAction(actionMap.get("executar")); // NOI18N
        executarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
        executarMenuItem.setIcon(resourceMap.getIcon("executarMenuItem.icon")); // NOI18N
        executarMenuItem.setText(resourceMap.getString("executarMenuItem.text")); // NOI18N
        executarMenuItem.setName("executarMenuItem"); // NOI18N
        menuAlgoritmo.add(executarMenuItem);

        menu.add(menuAlgoritmo);

        menuFerramentas.setText(resourceMap.getString("menuFerramentas.text")); // NOI18N
        menuFerramentas.setName("menuFerramentas"); // NOI18N

        numeroLinhas.setAction(actionMap.get("showNumeroLinhas")); // NOI18N
        numeroLinhas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcccalango/view/resources/num-linha-menu.png"))); // NOI18N
        numeroLinhas.setText(resourceMap.getString("numeroLinhas.text")); // NOI18N
        numeroLinhas.setName("numeroLinhas"); // NOI18N
        menuFerramentas.add(numeroLinhas);

        exitMenuItem3.setAction(actionMap.get("showSettings")); // NOI18N
        exitMenuItem3.setIcon(resourceMap.getIcon("exitMenuItem3.icon")); // NOI18N
        exitMenuItem3.setText(resourceMap.getString("exitMenuItem3.text")); // NOI18N
        exitMenuItem3.setName("exitMenuItem3"); // NOI18N
        menuFerramentas.add(exitMenuItem3);

        menu.add(menuFerramentas);

        menuAjuda.setText(resourceMap.getString("menuAjuda.text")); // NOI18N
        menuAjuda.setName("menuAjuda"); // NOI18N

        jMenuItem1.setAction(actionMap.get("showAjuda")); // NOI18N
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        menuAjuda.add(jMenuItem1);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        menuAjuda.add(aboutMenuItem);

        menu.add(menuAjuda);

        setComponent(jpPrincipal);
        setMenuBar(menu);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuItem ImprimirMenuItem;
    private javax.swing.JToolBar barraFerramento;
    private javax.swing.JButton btAumentarLetra;
    private javax.swing.JButton btConsole;
    private javax.swing.JButton btDiminuirLetra;
    private javax.swing.JButton btIndentar;
    private javax.swing.JButton btMudarConsole;
    private javax.swing.JButton btNewFile;
    private javax.swing.JButton btOpenFile;
    private javax.swing.JButton btPlay;
    private javax.swing.JButton btPrint;
    private javax.swing.JButton btSaveAs;
    private javax.swing.JButton btSearch;
    private JMenuItem colarMenuItem;
    private JMenuItem identarMenuItem;
    private javax.swing.JLabel jLabelLinha;
    private javax.swing.JLabel jLabelMensagem;
    private JMenuItem jMenuItem1;
    private javax.swing.JToolBar.Separator jSeparadorArquivo;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitHorizontal;
    private JPanel jpAlgoritmo;
    private JPanel jpConsole;
    private JPanel jpEscopo;
    private JPanel jpPanelRodape;
    private JPanel jpPrincipal;
    private javax.swing.JSplitPane jsplitVertical;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuDocRecentes;
    private JMenuItem novoMenuItem;
    private JMenuItem numeroLinhas;
    private JMenuItem recortarMenuItem;
    private JMenuItem salvarComoMenuItem;
    private JMenuItem salvarComoMenuItem1;
    private JMenuItem salvarMenuItem;
    private javax.swing.JScrollPane scrollConsole;
    private tcccalango.util.componentes.CalangoConsole tpConsole;
    // End of variables declaration//GEN-END:variables

 
    private JDialog aboutBox;
    private RSyntaxTextArea jtAlgoritmo;
    private RTextScrollPane scroolAlgoritmo;
    private CalangoSettings calangoSettings;
    private Thread calangoThread;
    
    
    private final static int INTERNA = 1;
    private final static int EXTERNA = 2;
    private int consoleASerAberta = EXTERNA;
    private boolean isEmExecucao = false;
    
    private File fileSave;
    private File fileOpen;
    
    private TCCCalangoSettings tccCalangoSettings;
    private String textoSalvo;
    
    private IndentadorCalango indentador;
    private AjudaFrame ajudaFrame;
    
    
    public CalangoSettings getCalangoSettings() {
        return calangoSettings;
    }

    public void setCalangoSettings(CalangoSettings calangoSettings) {
        this.calangoSettings = calangoSettings;
    }
    public Thread getCalangoThread() {
        return calangoThread;
    }

    public void setCalangoThread(Thread calangoThread) {
        this.calangoThread = calangoThread;
    }
    public File getFileOpen() {
        return fileOpen;
    }
    public void setFileOpen(File fileOpen) {
        this.fileOpen = fileOpen;
    }
    public File getFileSave() {
        return fileSave;
    }
    public void setFileSave(File fileSave) {
        this.fileSave = fileSave;
    }

    public boolean isIsEmExecucao() {
        return isEmExecucao;
    }

    public void setIsEmExecucao(boolean isEmExecucao) {
        this.isEmExecucao = isEmExecucao;
    }

    /**
     * Atualiza o editor com as novas configurações passadas por parâmetro
     * @param novaConfiguracao 
     * @author Jéssica Luanne
     */
    public void atualizar(CalangoSettings novaConfiguracao) {
        if(novaConfiguracao.getCorTipoDado() != null){
            calangoSettings.setCorTipoDado(novaConfiguracao.getCorTipoDado());
        }
        if(novaConfiguracao.getCorComentario() != null){
            calangoSettings.setCorComentario(novaConfiguracao.getCorComentario());
        }      
        if(novaConfiguracao.getCorConstanteNum() != null){
            calangoSettings.setCorConstanteNum(novaConfiguracao.getCorConstanteNum());
        }        
        if(novaConfiguracao.getCorConstanteTexto() != null){
            calangoSettings.setCorConstanteTexto(novaConfiguracao.getCorConstanteTexto());
        }        
        if(novaConfiguracao.getCorPalavraChave() != null){
            calangoSettings.setCorPalavraChave(novaConfiguracao.getCorPalavraChave());
        }        
        if(novaConfiguracao.getCorFundoEditor() != null){
            calangoSettings.setCorFundoEditor(novaConfiguracao.getCorFundoEditor());
        }
        if(novaConfiguracao.getCorTextoGeral() != null){
            calangoSettings.setCorTextoGeral(novaConfiguracao.getCorTextoGeral());
        }        
        
        calangoSettings.setFonte(novaConfiguracao.getFonte());
        calangoSettings.setTamanhoFonte(novaConfiguracao.getTamanhoFonte());
        if(novaConfiguracao.getEstiloFonte() != null){
            calangoSettings.setEstiloFonte(novaConfiguracao.getEstiloFonte());
        }
        calangoSettings.setSyleUnderline(novaConfiguracao.isSyleUnderline());
        calangoSettings.setTextoSintese(novaConfiguracao.getTextoSintese());
        calangoSettings.setTextoSecaoComandos(novaConfiguracao.getTextoSecaoComandos());
        configuraCalangoWithSettings();
        changeCalangoSettings();
        
    }

    /**
     * Este método atualiza o arquivo de configurações a partir do calangoSettings
     * e atualiza 
     * @author Jéssica Luanne
     */
    private void changeCalangoSettings() {
        try {
            IOUtil.saveConfigurationFile(calangoSettings);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtAlgoritmo.repaint();
    }
    
    private boolean isAbrirConsoleInterna(){
        return consoleASerAberta == INTERNA;
    }
    
    private boolean isAbrirConsoleExterna(){
        return consoleASerAberta == EXTERNA;
    }

    public String getTextoSalvo() {
        return textoSalvo;
    }

    public void setTextoSalvo(String textoSalvo) {
        this.textoSalvo = textoSalvo;
    }

    public IndentadorCalango getIdentador() {
        return indentador;
    }

    public void setIdentador(IndentadorCalango identador) {
        this.indentador = identador;
    }

    /**
     * Carrega o arquivo passdo como parâmetro.<br/>
     * O arquivo é adicionado na lista de arquivos recentes e o algoritmo contido no arquivo é
     * apresentado no editor
     * @param file arquivo a ser carregado
     */
    private void carregaArquivo(File file) {
        try {
            setFileOpen(file);
            calangoSettings.addArquivo(file.getAbsolutePath());
            IOUtil.saveConfigurationFile(calangoSettings);
            String arquivo = IOUtil.lerArquivo(file);
            carregaAlgoritmo("Calango - "+file.getName(), arquivo);

    //                                File arquivoSetting = new File("calango.conf");
    //                                FileOutputStream streamOut;
    //                                streamOut = new FileOutputStream(arquivoSetting);
    //                                ObjectOutputStream streamOjetos = new ObjectOutputStream(streamOut);
    //                                streamOjetos.writeObject(calangoSettings);
    //                                streamOjetos.close();
    //                                streamOut.close();
        } catch (IOException ex) {

            // TODO Tratar exceÃ§Ã£o
        }
    }

}
