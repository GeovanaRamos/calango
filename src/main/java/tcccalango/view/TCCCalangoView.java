package tcccalango.view;

import br.ucb.calango.api.publica.CalangoAPI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.EventObject;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar.Separator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.text.StyleConstants;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import tcccalango.TCCCalangoApp;
import tcccalango.util.arquivo.IOUtil;
import tcccalango.util.componentes.CalangoConsole;
import tcccalango.util.componentes.IMonitorFrame;
import tcccalango.util.componentes.JPanelRodape;
import tcccalango.util.componentes.PrintUtilities;
import tcccalango.util.componentes.SimpleMonitorFrame;
import tcccalango.util.componentes.passoapasso.PassoAPassoViewer;
import tcccalango.util.indentacao.IndentadorCalango;
import tcccalango.util.interpretador.CalangoIDEDebug;
import tcccalango.util.interpretador.CalangoIDEIn;
import tcccalango.util.interpretador.CalangoIDEOut;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.util.settings.CalangoSettingsUtil;
import tcccalango.view.ajuda.AjudaFrame;
import tcccalango.view.interfaces.ITCCCalangoViewObserver;
import tcccalango.view.listeners.CalangoCaretListener;
import tcccalango.view.listeners.CarregaArquivoListener;
import tcccalango.view.util.CalangoViewUtil;

public class TCCCalangoView extends FrameView implements ITCCCalangoViewObserver {
   public static final KeyStroke DEBUG_STROKE = KeyStroke.getKeyStroke(115, 0);
   private static final int SAVE = 0;
   private static final int IGNORE = 1;
   private CalangoIDEDebug ideDebug;
   private IMonitorFrame monitor;
   private JComponent[] commands;
   private JMenuItem ImprimirMenuItem;
   private JToolBar barraFerramento;
   private JButton btAumentarLetra;
   private JButton btConsole;
   private JButton btDebug;
   private JButton btDiminuirLetra;
   private JButton btIndentar;
   private JButton btMudarConsole;
   private JButton btNewFile;
   private JButton btOpenFile;
   private JButton btPlay;
   private JButton btPrint;
   private JButton btSaveAs;
   private JButton btSearch;
   private JMenuItem colarMenuItem;
   private JScrollPane debugToolbar;
   private JMenuItem executarPassoAPassoMenuItem;
   private JMenuItem identarMenuItem;
   private JLabel jLabelLinha;
   private JLabel jLabelMensagem;
   private JMenuItem jMenuItem1;
   private JMenuItem jMenuItem2;
   private Separator jSeparadorArquivo;
   private Separator jSeparator2;
   private JSplitPane jSplitHorizontal;
   private JPanel jpAlgoritmo;
   private JPanel jpConsole;
   private JPanel jpEscopo;
   private JPanel jpPanelRodape;
   private JPanel jpPrincipal;
   private JSplitPane jsplitVertical;
   private JMenuBar menu;
   private JMenu menuAlgoritmo;
   private JMenu menuArquivo;
   private JMenu menuDocRecentes;
   private JMenu menuEditar;
   private JMenu menuFerramentas;
   private JMenuItem novoMenuItem;
   private JMenuItem numeroLinhas;
   private JMenuItem recortarMenuItem;
   private JMenuItem salvarComoMenuItem;
   private JMenuItem salvarComoMenuItem1;
   private JMenuItem salvarMenuItem;
   private JScrollPane scrollConsole;
   private CalangoConsole tpConsole;
   private JDialog aboutBox;
   private RSyntaxTextArea jtAlgoritmo;
   private RTextScrollPane scroolAlgoritmo;
   private CalangoSettings calangoSettings;
   private Thread calangoThread;
   private static final int INTERNA = 1;
   private static final int EXTERNA = 2;
   private int consoleASerAberta = 2;
   private boolean isEmExecucao = false;
   private File fileSave;
   private File fileOpen;
   private TCCCalangoSettings tccCalangoSettings;
   private String textoSalvo;
   private IndentadorCalango indentador;
   private AjudaFrame ajudaFrame;
   private FindAndReplaceDialog find;

   public TCCCalangoView(SingleFrameApplication app) {
      super(app);

      try {
         this.getFrame().setIconImage(ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("tcccalango/view/resources/calango-icone-provisorio.png")));
      } catch (IOException var3) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

      this.initComponents();
      this.secondInit();
      this.getFrame().setMinimumSize(new Dimension(800, 600));
      this.getFrame().addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            TCCCalangoView.this.calangoSettings.setWindowBounds(TCCCalangoView.this.getFrame().getBounds());
            TCCCalangoView.this.changeCalangoSettings();
         }

         public void windowOpened(WindowEvent e) {
            TCCCalangoView.this.getFrame().setBounds(TCCCalangoView.this.calangoSettings.getWindowBounds());
         }
      });
      app.addExitListener(new Application.ExitListener() {
         public boolean canExit(EventObject event) {
            if (TCCCalangoView.this.jtAlgoritmo.getText().equals(TCCCalangoView.this.getTextoSalvo())) {
               return true;
            } else {
               String msg = "";
               if (TCCCalangoView.this.getFileOpen() != null && TCCCalangoView.this.getFileSave() != null) {
                  msg = "O arquivo " + TCCCalangoView.this.getFileSave().getName() + " foi modificado. Deseja salvá-lo?";
               } else {
                  msg = "O arquivo ainda não foi salvo. Deseja salvá-lo?";
               }

               int retorno = JOptionPane.showConfirmDialog((Component)null, msg, "Deseja salvar o arquivo?", 1, -1);
               if (retorno <= 1 && TCCCalangoView.this.isEmExecucao) {
                  TCCCalangoView.this.paraExecucao();
               }

               switch(retorno) {
               case -1:
                  return false;
               case 0:
                  TCCCalangoView.this.saveArquivo();
                  return true;
               case 1:
                  return true;
               case 2:
                  return false;
               default:
                  return true;
               }
            }
         }

         public void willExit(EventObject event) {
            System.exit(0);
         }
      });
   }

   @Action
   public void showAboutBox() {
      if (this.aboutBox == null) {
         JFrame mainFrame = TCCCalangoApp.getApplication().getMainFrame();
         this.aboutBox = new TCCCalangoAboutBox(mainFrame);
         this.aboutBox.setLocationRelativeTo(mainFrame);
      }

      TCCCalangoApp.getApplication().show(this.aboutBox);
   }

   @Action
   public void copiar() {
      this.jtAlgoritmo.copy();
   }

   @Action
   public void colar() {
      this.jtAlgoritmo.paste();
   }

   @Action
   public void recortar() {
      this.jtAlgoritmo.cut();
   }

   @Action
   public void showAbrirArquivo() {
      this.openFile();
   }

   private void openFile() {
      FileDialog dialog = new FileDialog(this.getFrame(), "Escolha um arquivo");
      dialog.setMode(0);
      dialog.setModalityType(ModalityType.APPLICATION_MODAL);

      do {
         dialog.setVisible(true);
         if (dialog.getFile() != null) {
            if (dialog.getFile().toLowerCase().endsWith(".clg")) {
               String fileName = dialog.getDirectory() + dialog.getFile();
               this.carregaArquivo(new File(fileName));
            } else {
               JOptionPane.showMessageDialog(this.getFrame(), "O arquivo deve ser .clg", "Impossível abrir arquivo", -1);
            }
         }
      } while(dialog.getFile() != null && !dialog.getFile().toLowerCase().endsWith(".clg"));

   }

   @Action
   public void saveArquivo() {
      if (this.getFileOpen() == null && this.getFileSave() == null) {
         this.saveAs();
      } else {
         if (this.getFileOpen() != null && this.getFileSave() == null) {
            this.setFileSave(this.getFileOpen());
         }

         IOUtil.salvarArquivo(this.getFileSave(), this.jtAlgoritmo.getText());
         this.jLabelMensagem.setText("Arquivo salvo com sucesso!");
         this.setTextoSalvo(this.jtAlgoritmo.getText());
         this.jpPanelRodape.updateUI();
         this.carregaArquivo(this.getFileSave());
      }

   }

   @Action
   public void saveAs() {
      FileDialog dialog = new FileDialog(this.getFrame(), "Escolha um arquivo");
      dialog.setMode(1);
      dialog.setModalityType(ModalityType.APPLICATION_MODAL);
      dialog.setVisible(true);
      if (dialog.getFile() != null) {
         StringBuilder fileName = new StringBuilder(dialog.getDirectory() + dialog.getFile());
         if (!dialog.getFile().toLowerCase().endsWith(".clg")) {
            fileName.append(".clg");
         }

         File file = new File(fileName.toString());
         this.setFileSave(file);
         IOUtil.salvarArquivo(this.getFileSave(), this.jtAlgoritmo.getText());
         this.setTextoSalvo(this.jtAlgoritmo.getText());
         this.jLabelMensagem.setText("Arquivo salvo com sucesso!");
         this.jpPanelRodape.updateUI();
         this.carregaArquivo(this.getFileSave());
      }

   }

   @Action
   public void newFile() {
      this.novoArquivo();
   }

   public void novoArquivo() {
      this.fileOpen = null;
      this.carregaAlgoritmo("Calango - Sem Título.clg", this.calangoSettings.getTextoInicio());
   }

   private void carregaAlgoritmo(String titulo, String algoritmo) {
      switch(this.shouldSave()) {
      case 0:
         this.saveArquivo();
      case 1:
         if (this.isEmExecucao) {
            this.paraExecucao();
         }

         this.tpConsole.clear();
         this.jtAlgoritmo.setText(algoritmo);
         this.jtAlgoritmo.discardAllEdits();
         this.setTextoSalvo(this.jtAlgoritmo.getText());
         this.jLabelMensagem.setText("");
         this.jpPanelRodape.updateUI();
         this.fileSave = null;
         this.getFrame().setTitle(titulo);
         this.recarregaMenuArquivosRecentes();
      default:
      }
   }

   private int shouldSave() {
      if (this.jtAlgoritmo.getText().equals(this.getTextoSalvo())) {
         return 1;
      } else {
         String msg = "";
         if (this.getFileOpen() != null && this.getFileSave() != null) {
            msg = "O arquivo " + this.getFileSave().getName() + " foi modificado. Deseja salvá-lo?";
         } else {
            msg = "O arquivo ainda não foi salvo. Deseja salvá-lo?";
         }

         return JOptionPane.showConfirmDialog((Component)null, msg, "Deseja salvar o arquivo?", 1, -1);
      }
   }

   @Action
   public void showNumeroLinhas() {
      this.scroolAlgoritmo.setLineNumbersEnabled(!this.scroolAlgoritmo.getLineNumbersEnabled());
   }

   @Action
   public void showSettings() {
      this.tccCalangoSettings = new TCCCalangoSettings(this.getFrame(), TCCCalangoApp.getApplication(), this.calangoSettings);
      this.tccCalangoSettings.adicionarObservador(this);
      TCCCalangoApp.getApplication().show(this.tccCalangoSettings);
   }

   @Action
   public void showAjuda() {
      TCCCalangoApp.getApplication().show(this.ajudaFrame);
   }

   @Action
   public void executar() {
      this.isEmExecucao = true;
      this.mudaBotoesExecutando(false);
      this.tpConsole.clear();
      this.tpConsole.requestFocus();
      this.jtAlgoritmo.setEnabled(false);

      try {
         this.calangoThread = new Thread(new Runnable() {
            public void run() {
               CalangoAPI.setIn(new CalangoIDEIn(TCCCalangoView.this.tpConsole));
               CalangoAPI.setOut(new CalangoIDEOut(TCCCalangoView.this.tpConsole, TCCCalangoView.this.jtAlgoritmo));
               CalangoAPI.interpretar(TCCCalangoView.this.jtAlgoritmo.getText());
               if (TCCCalangoView.this.isEmExecucao) {
                  TCCCalangoView.this.paraExecucao();
               }

            }
         });
         this.calangoThread.start();
         this.showConsole(true);
      } catch (RuntimeException var2) {
         var2.printStackTrace();
         this.calangoThread.interrupt();
      }

   }

   @Action
   public void depurar() {
      if (this.isEmExecucao) {
         if (this.ideDebug != null) {
            this.ideDebug.passo();
         }
      } else {
         this.isEmExecucao = true;
         this.mudaBotoesExecutando(true);
         this.tpConsole.clear();
         this.tpConsole.requestFocus();
         this.jtAlgoritmo.setEnabled(false);

         try {
            this.calangoThread = new Thread(new Runnable() {
               public void run() {
                  try {
                     CalangoAPI.setIn(new CalangoIDEIn(TCCCalangoView.this.tpConsole));
                     CalangoAPI.setOut(new CalangoIDEOut(TCCCalangoView.this.tpConsole, TCCCalangoView.this.jtAlgoritmo));
                     CalangoAPI.setDebugger(TCCCalangoView.this.ideDebug);
                     TCCCalangoView.this.showDebug();
                     CalangoAPI.depurar(TCCCalangoView.this.jtAlgoritmo.getText());
                     if (TCCCalangoView.this.isEmExecucao) {
                        TCCCalangoView.this.paraExecucao();
                     }
                  } catch (RuntimeException var2) {
                  }

               }
            });
            this.calangoThread.start();
            this.showConsole(false);
         } catch (RuntimeException var2) {
            var2.printStackTrace();
            this.calangoThread.interrupt();
         }
      }

   }

   @Action
   public void paraExecucao() {
      this.isEmExecucao = false;
      if (!Thread.currentThread().equals(this.calangoThread)) {
         this.calangoThread.interrupt();
      }

      this.ideDebug.passo();
      this.mudaBotoesParando();
      this.tpConsole.append("\n--\nFim de execução!");
      this.jtAlgoritmo.setEnabled(true);
      this.jpPanelRodape.updateUI();
   }

   @Action
   public void showConsole(boolean modal) {
      if (this.isAbrirConsoleExterna()) {
         this.showConsoleExterna(modal);
      } else {
         this.showConsoleInterna();
      }

      this.tpConsole.requestFocus();
      this.tpConsole.updateUI();
   }

   private void showConsoleExterna(boolean modal) {
      this.hideConsoleInterna();
      if (!this.monitor.isConsole(this.jpConsole)) {
         this.monitor.setConsole(this.jpConsole);
      }

      this.monitor.setModal(modal);
      this.monitor.setVisible(true);
      this.monitor.requestFocusInWindow();
   }

   private void hideConsoleExterna() {
      this.monitor.setVisible(false);
      this.monitor.removeConsole();
   }

   private void showConsoleInterna() {
      this.hideConsoleExterna();
      this.jpPanelRodape.updateUI();
      this.jSplitHorizontal.setDividerSize(3);
      if (!this.jSplitHorizontal.isAncestorOf(this.jpConsole)) {
         this.jSplitHorizontal.add(this.jpConsole);
      }

      this.scrollConsole.getVerticalScrollBar().setValue(this.scrollConsole.getVerticalScrollBar().getMaximum());
   }

   private void hideConsoleInterna() {
      this.jSplitHorizontal.setDividerSize(0);
      this.jSplitHorizontal.remove(this.jpConsole);
   }

   private void mudaBotoesParando() {
      JComponent[] arr$ = this.commands;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         JComponent c = arr$[i$];
         c.setEnabled(true);
      }

      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoView.class);
      ActionMap actionMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getActionMap(TCCCalangoView.class, this);
      this.btPlay.setAction(actionMap.get("executar"));
      this.btPlay.setText(resourceMap.getString("btPlay.text"));
      this.btPlay.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btPlay.setFocusable(false);
      this.btPlay.setHorizontalTextPosition(0);
      this.btPlay.setName("btPlay");
      this.btPlay.setVerticalTextPosition(3);
      this.btDebug.setEnabled(true);
      if (CalangoViewUtil.isMacOS()) {
         this.btPlay.setIcon(resourceMap.getIcon("btMacPlay.icon"));
      } else {
         this.btPlay.setIcon(resourceMap.getIcon("btPlay.icon"));
      }

      this.btPlay.updateUI();
   }

   private void mudaBotoesExecutando(boolean depuracao) {
      JComponent[] arr$ = this.commands;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         JComponent c = arr$[i$];
         c.setEnabled(false);
      }

      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoView.class);
      ActionMap actionMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getActionMap(TCCCalangoView.class, this);
      this.btPlay.setAction(actionMap.get("paraExecucao"));
      this.btPlay.setText(resourceMap.getString("btPlay.text"));
      this.btPlay.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btPlay.setFocusable(false);
      this.btPlay.setHorizontalTextPosition(0);
      this.btPlay.setName("btStop");
      this.btPlay.setVerticalTextPosition(3);
      this.btDebug.setEnabled(depuracao);
      if (CalangoViewUtil.isMacOS()) {
         this.btPlay.setIcon(resourceMap.getIcon("btMacStop.icon"));
      } else {
         this.btPlay.setIcon(resourceMap.getIcon("btStop.icon"));
      }

      this.btPlay.updateUI();
   }

   @Action
   public void mudaBotaoConsole() {
      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoView.class);
      if (this.isAbrirConsoleInterna()) {
         if (CalangoViewUtil.isMacOS()) {
            this.btMudarConsole.setIcon(resourceMap.getIcon("btMacConsoleExterno.icon"));
         } else {
            this.btMudarConsole.setIcon(resourceMap.getIcon("btExecutarConsoleExerno.icon"));
         }

         this.consoleASerAberta = 2;
      } else {
         if (CalangoViewUtil.isMacOS()) {
            this.btMudarConsole.setIcon(resourceMap.getIcon("btMacConsole.icon"));
         } else {
            this.btMudarConsole.setIcon(resourceMap.getIcon("btExecutarConsoleInterno.icon"));
         }

         this.consoleASerAberta = 1;
      }

      this.calangoSettings.setConsoleEmbutida(this.isAbrirConsoleInterna());
      this.changeCalangoSettings();
   }

   @Action
   public void identar() {
      this.indenta();
   }

   private void secondInit() {
      this.jpAlgoritmo = new JPanel();
      this.jpAlgoritmo.setAutoscrolls(true);
      this.jpAlgoritmo.setName("jpAlgoritmo");
      this.jpAlgoritmo.setMinimumSize(new Dimension(500, 400));
      this.jpAlgoritmo.setLayout(new BorderLayout());
      AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
      atmf.putMapping("text/calango", "tcccalango.util.rsyntaxarea.CalangoTokenMaker");
      this.jtAlgoritmo = new RSyntaxTextArea();
      this.jtAlgoritmo.setSyntaxEditingStyle("text/calango");
      this.jtAlgoritmo.setTabSize(4);
      this.jtAlgoritmo.setPaintTabLines(true);
      this.jtAlgoritmo.setMargin(new Insets(0, 5, 0, 0));
      this.jtAlgoritmo.addCaretListener(new CalangoCaretListener(this.jtAlgoritmo, this.jLabelLinha));
      this.scroolAlgoritmo = new RTextScrollPane(this.jtAlgoritmo);
      this.scroolAlgoritmo.getGutter().setBackground(new Color(229, 233, 238));
      this.scroolAlgoritmo.setBorder(new MatteBorder(1, 0, 1, 0, new Color(112, 112, 112)));
      this.scroolAlgoritmo.setVerticalScrollBarPolicy(20);
      this.jpAlgoritmo.add("Center", this.scroolAlgoritmo);
      this.jSplitHorizontal.setTopComponent(this.jpAlgoritmo);
      this.jpPrincipal.setLayout(new BorderLayout());
      this.jpPrincipal.add("North", this.barraFerramento);
      this.jpPrincipal.add("Center", this.jsplitVertical);
      this.jpPrincipal.add("South", this.jpPanelRodape);
      this.jSplitHorizontal.remove(this.jpConsole);
      this.btSearch.setVisible(false);
      if (CalangoViewUtil.isMacOS()) {
         this.personalizaOSMac();
      }

      this.iniciaCalangoSettings();
      this.jpEscopo.setVisible(false);
      this.btConsole.setVisible(false);
      this.setTextoSalvo(this.jtAlgoritmo.getText());
      this.tpConsole.addFocusListener(new FocusAdapter() {
         public void focusLost(FocusEvent e) {
            if (!TCCCalangoView.this.isEmExecucao) {
               TCCCalangoView.this.jtAlgoritmo.removeAllLineHighlights();
               if (TCCCalangoView.this.isAbrirConsoleInterna()) {
                  TCCCalangoView.this.hideConsoleInterna();
               } else {
                  TCCCalangoView.this.hideConsoleExterna();
               }

               if (TCCCalangoView.this.debugToolbar.isVisible()) {
                  TCCCalangoView.this.hideDebug();
               }
            }

         }
      });
      StyleConstants.setFontSize(this.tpConsole.getOutput(), 14);
      StyleConstants.setFontSize(this.tpConsole.getInput(), 14);
      this.scrollConsole.getVerticalScrollBar().addAdjustmentListener(this.tpConsole);
      this.monitor = new SimpleMonitorFrame(new SimpleMonitorFrame.CloseListener() {
         public void close() {
            if (!TCCCalangoView.this.isEmExecucao) {
               TCCCalangoView.this.paraExecucao();
               TCCCalangoView.this.hideConsoleExterna();
            }

         }

         public void esc() {
            if (TCCCalangoView.this.isEmExecucao) {
               TCCCalangoView.this.paraExecucao();
            }

         }
      });
      this.indentador = new IndentadorCalango();
      this.jtAlgoritmo.getInputMap(2).put(KeyStroke.getKeyStroke(71, 128), "identar");
      this.jtAlgoritmo.getActionMap().put("identar", new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoView.this.indenta();
         }
      });
      this.ajudaFrame = new AjudaFrame("Ajuda Calango");
      this.commands = new JComponent[]{this.btAumentarLetra, this.btConsole, this.btDiminuirLetra, this.btIndentar, this.btMudarConsole, this.btNewFile, this.btOpenFile, this.btPlay, this.btPrint, this.btSaveAs, this.btSearch, this.menuArquivo, this.menuEditar, this.menuAlgoritmo, this.menuFerramentas};
      this.ideDebug = new CalangoIDEDebug((PassoAPassoViewer)this.debugToolbar, this.jtAlgoritmo);
      this.debugToolbar.setPreferredSize(new Dimension(350, 250));
      AbstractAction debugAction = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
            TCCCalangoView.this.depurar();
         }
      };
      JComponent[] arr$ = new JComponent[]{this.btDebug, this.debugToolbar, this.jtAlgoritmo, this.tpConsole, this.executarPassoAPassoMenuItem};
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         JComponent c = arr$[i$];
         c.getInputMap(2).put(DEBUG_STROKE, "passo-debug");
         c.getActionMap().put("passo-debug", debugAction);
      }

      this.find = new FindAndReplaceDialog(this.getFrame(), this.jtAlgoritmo);
   }

   public void autoComplete() {
      DefaultCompletionProvider provider = new DefaultCompletionProvider();

      try {
         FileReader arq = new FileReader("autocomplete.txt");
         BufferedReader lerArq = new BufferedReader(arq);

         for(String linha = lerArq.readLine(); linha != null; linha = lerArq.readLine()) {
            String[] parametros = linha.split("#");
            String codigo = parametros[1].replaceAll("&", "\n").replaceAll("%", "\t");
            provider.addCompletion(new ShorthandCompletion(provider, parametros[0], codigo, parametros[2]));
         }

         arq.close();
         AutoCompletion ac = new AutoCompletion(provider);
         ac.install(this.jtAlgoritmo);
      } catch (IOException var7) {
         System.err.printf("Erro na abertura do arquivo: %s.\n", var7.getMessage());
      }

   }

   private void hideDebug() {
      this.jpPrincipal.remove(this.debugToolbar);
      this.jpPrincipal.revalidate();
   }

   private void showDebug() {
      ((PassoAPassoViewer)this.debugToolbar).limpaEscopos();
      this.jpPrincipal.add("East", this.debugToolbar);
      this.jpPrincipal.revalidate();
   }

   @Action
   public void find() {
      this.find.open();
   }

   @Action
   public void indenta() {
      this.jtAlgoritmo.beginAtomicEdit();
      int position = this.jtAlgoritmo.getCaretPosition();
      this.jtAlgoritmo.setText(this.indentador.identa(this.jtAlgoritmo.getText()));

      try {
         this.jtAlgoritmo.setCaretPosition(position);
      } catch (IllegalArgumentException var3) {
      }

      this.jtAlgoritmo.endAtomicEdit();
   }

   private void iniciaCalangoSettings() {
      File arquivo = new File("calango.conf");

      try {
         try {
            if (arquivo.exists()) {
               this.calangoSettings = IOUtil.getConfigurationFromConfigurationFile();
            } else {
               this.calangoSettings = CalangoSettingsUtil.obtemConfiguracoesDefault(this.jtAlgoritmo.getBackground(), this.jtAlgoritmo.getForeground(), this.jtAlgoritmo.getSyntaxScheme());
               IOUtil.saveConfigurationFile(this.calangoSettings);
            }
         } catch (InvalidClassException var3) {
            this.calangoSettings = CalangoSettingsUtil.obtemConfiguracoesDefault(this.jtAlgoritmo.getBackground(), this.jtAlgoritmo.getForeground(), this.jtAlgoritmo.getSyntaxScheme());
            IOUtil.saveConfigurationFile(this.calangoSettings);
         }
      } catch (FileNotFoundException var4) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var4);
      } catch (IOException var5) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var5);
      } catch (ClassNotFoundException var6) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      this.configuraCalangoWithSettings(true);
      this.configuraBotoesTamanhoFonte(this.calangoSettings.getTamanhoFonte());
   }

   @Action
   public void imprimir() {
      PrintUtilities.printComponent(this.jtAlgoritmo);
   }

   private void configuraCalangoWithSettings(boolean setTextoInicio) {
      CalangoViewUtil.configuraSyntaxWithSettings(this.calangoSettings, this.jtAlgoritmo);
      CalangoViewUtil.configuraConsoleWithSettings(this.calangoSettings, this.tpConsole);
      if (setTextoInicio) {
         this.jtAlgoritmo.setText(this.calangoSettings.getTextoInicio());
      }

      if (this.calangoSettings.isConsoleEmbutida()) {
         this.consoleASerAberta = 1;
      } else {
         this.consoleASerAberta = 2;
      }

      this.mudaBotaoConsole();
      this.mudaBotaoConsole();
   }

   public void recarregaMenuArquivosRecentes() {
      this.menuDocRecentes.removeAll();
      Iterator i$ = this.calangoSettings.getDocumentosRecentes().iterator();

      while(i$.hasNext()) {
         String file = (String)i$.next();
         JMenuItem item = new JMenuItem(file);
         item.addActionListener(new CarregaArquivoListener(new File(file)) {
            public void actionPerformed(ActionEvent e) {
               TCCCalangoView.this.carregaArquivo(this.getFile());
            }
         });
         this.menuDocRecentes.add(item);
      }

   }

   private void personalizaOSMac() {
      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoView.class);
      this.barraFerramento.setPreferredSize(new Dimension(239, 32));
      this.btNewFile.putClientProperty("JButton.buttonType", "textured");
      this.btOpenFile.putClientProperty("JButton.buttonType", "textured");
      this.btSaveAs.putClientProperty("JButton.buttonType", "textured");
      this.btSearch.putClientProperty("JButton.buttonType", "textured");
      this.btIndentar.putClientProperty("JButton.buttonType", "textured");
      this.btDiminuirLetra.putClientProperty("JButton.buttonType", "textured");
      this.btAumentarLetra.putClientProperty("JButton.buttonType", "textured");
      this.btConsole.putClientProperty("JButton.buttonType", "textured");
      this.btMudarConsole.putClientProperty("JButton.buttonType", "textured");
      this.btPrint.putClientProperty("JButton.buttonType", "textured");
      this.btDebug.putClientProperty("JButton.buttonType", "textured");
      this.btDiminuirLetra.putClientProperty("JButton.buttonType", "segmentedTextured");
      this.btDiminuirLetra.putClientProperty("JButton.segmentPosition", "first");
      this.btAumentarLetra.putClientProperty("JButton.buttonType", "segmentedTextured");
      this.btAumentarLetra.putClientProperty("JButton.segmentPosition", "last");
      this.btPlay.putClientProperty("JButton.buttonType", "textured");
      this.btOpenFile.setIcon(resourceMap.getIcon("btMacOpenFile.icon"));
      this.btSaveAs.setIcon(resourceMap.getIcon("btMacSaveAs.icon"));
      this.btSearch.setIcon(resourceMap.getIcon("btMacSearch.icon"));
      this.btIndentar.setIcon(resourceMap.getIcon("btMacIdentar.icon"));
      this.btDiminuirLetra.setIcon(resourceMap.getIcon("btMacDiminuirLetra.icon"));
      this.btAumentarLetra.setIcon(resourceMap.getIcon("btMacAumentarLetra.icon"));
      this.btMudarConsole.setIcon(resourceMap.getIcon("btMacConsoleExterno.icon"));
      this.btPlay.setIcon(resourceMap.getIcon("btMacPlay.icon"));
      this.btNewFile.setIcon(resourceMap.getIcon("btMacNewFile.icon"));
      this.btDebug.setIcon(resourceMap.getIcon("btMacDebug.icon"));
      this.btPrint.setIcon(resourceMap.getIcon("btMacPrint.icon"));
      this.jtAlgoritmo.setFont(new Font("Monaco", 0, 12));
      this.getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);
   }

   private void initComponents() {
      this.jpPrincipal = new JPanel();
      this.barraFerramento = new JToolBar();
      this.btNewFile = new JButton();
      this.btOpenFile = new JButton();
      this.btSaveAs = new JButton();
      this.btPrint = new JButton();
      this.jSeparadorArquivo = new Separator();
      this.btIndentar = new JButton();
      this.btDiminuirLetra = new JButton();
      this.btAumentarLetra = new JButton();
      this.btMudarConsole = new JButton();
      this.jSeparator2 = new Separator();
      this.btSearch = new JButton();
      this.btDebug = new JButton();
      this.btPlay = new JButton();
      this.jsplitVertical = new JSplitPane();
      this.jpEscopo = new JPanel();
      this.jSplitHorizontal = new JSplitPane();
      this.jpAlgoritmo = new JPanel();
      this.jpConsole = new JPanel();
      this.scrollConsole = new JScrollPane();
      this.tpConsole = new CalangoConsole();
      this.jpPanelRodape = new JPanelRodape();
      this.jLabelLinha = new JLabel();
      this.jLabelMensagem = new JLabel();
      this.btConsole = new JButton();
      this.debugToolbar = new PassoAPassoViewer();
      this.menu = new JMenuBar();
      this.menuArquivo = new JMenu();
      JMenuItem abrirMenuItem = new JMenuItem();
      this.novoMenuItem = new JMenuItem();
      this.salvarMenuItem = new JMenuItem();
      this.salvarComoMenuItem = new JMenuItem();
      this.ImprimirMenuItem = new JMenuItem();
      this.menuDocRecentes = new JMenu();
      this.salvarComoMenuItem1 = new JMenuItem();
      this.menuEditar = new JMenu();
      JMenuItem copiarMenuItem = new JMenuItem();
      this.colarMenuItem = new JMenuItem();
      this.recortarMenuItem = new JMenuItem();
      this.identarMenuItem = new JMenuItem();
      JMenuItem desfazerMenuItem1 = new JMenuItem();
      JMenuItem refazerMenuItem2 = new JMenuItem();
      this.jMenuItem2 = new JMenuItem();
      this.menuAlgoritmo = new JMenu();
      JMenuItem executarMenuItem = new JMenuItem();
      this.executarPassoAPassoMenuItem = new JMenuItem();
      this.menuFerramentas = new JMenu();
      this.numeroLinhas = new JMenuItem();
      JMenuItem exitMenuItem3 = new JMenuItem();
      JMenu menuAjuda = new JMenu();
      this.jMenuItem1 = new JMenuItem();
      JMenuItem aboutMenuItem = new JMenuItem();
      this.jpPrincipal.setName("jpPrincipal");
      this.barraFerramento.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 7));
      this.barraFerramento.setFloatable(false);
      this.barraFerramento.setRollover(true);
      this.barraFerramento.setFocusable(false);
      this.barraFerramento.setName("barraFerramento");
      ActionMap actionMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getActionMap(TCCCalangoView.class, this);
      this.btNewFile.setAction(actionMap.get("newFile"));
      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoView.class);
      this.btNewFile.setIcon(resourceMap.getIcon("btNewFile.icon"));
      this.btNewFile.setText(resourceMap.getString("btNewFile.text"));
      this.btNewFile.setToolTipText(resourceMap.getString("btNewFile.toolTipText"));
      this.btNewFile.setAlignmentY(0.0F);
      this.btNewFile.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btNewFile.setFocusable(false);
      this.btNewFile.setHorizontalTextPosition(0);
      this.btNewFile.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btNewFile.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btNewFile.setName("");
      this.btNewFile.setPreferredSize(new Dimension(28, 28));
      this.btNewFile.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btNewFile);
      this.btOpenFile.setAction(actionMap.get("showAbrirArquivo"));
      this.btOpenFile.setIcon(resourceMap.getIcon("btOpenFile.icon"));
      this.btOpenFile.setText(resourceMap.getString("btOpenFile.text"));
      this.btOpenFile.setToolTipText(resourceMap.getString("btOpenFile.toolTipText"));
      this.btOpenFile.setAlignmentY(0.0F);
      this.btOpenFile.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btOpenFile.setFocusable(false);
      this.btOpenFile.setHorizontalTextPosition(0);
      this.btOpenFile.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btOpenFile.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btOpenFile.setName("");
      this.btOpenFile.setPreferredSize(new Dimension(28, 28));
      this.btOpenFile.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btOpenFile);
      this.btSaveAs.setAction(actionMap.get("saveArquivo"));
      this.btSaveAs.setIcon(resourceMap.getIcon("btSaveAs.icon"));
      this.btSaveAs.setText(resourceMap.getString("btSaveAs.text"));
      this.btSaveAs.setToolTipText(resourceMap.getString("btSaveAs.toolTipText"));
      this.btSaveAs.setAlignmentY(0.0F);
      this.btSaveAs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btSaveAs.setFocusable(false);
      this.btSaveAs.setHorizontalTextPosition(0);
      this.btSaveAs.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btSaveAs.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btSaveAs.setName("btSaveAs");
      this.btSaveAs.setPreferredSize(new Dimension(28, 28));
      this.barraFerramento.add(this.btSaveAs);
      this.btPrint.setAction(actionMap.get("imprimir"));
      this.btPrint.setIcon(resourceMap.getIcon("btPrint.icon"));
      this.btPrint.setText(resourceMap.getString("btPrint.text"));
      this.btPrint.setToolTipText(resourceMap.getString("btPrint.toolTipText"));
      this.btPrint.setAlignmentY(0.0F);
      this.btPrint.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btPrint.setFocusable(false);
      this.btPrint.setHorizontalTextPosition(0);
      this.btPrint.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btPrint.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btPrint.setName("btPrint");
      this.btPrint.setPreferredSize(new Dimension(28, 28));
      this.btPrint.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btPrint);
      this.jSeparadorArquivo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.jSeparadorArquivo.setName("jSeparadorArquivo");
      this.barraFerramento.add(this.jSeparadorArquivo);
      this.btIndentar.setAction(actionMap.get("indenta"));
      this.btIndentar.setIcon(resourceMap.getIcon("btIndentar.icon"));
      this.btIndentar.setToolTipText(resourceMap.getString("btIndentar.toolTipText"));
      this.btIndentar.setAlignmentY(0.0F);
      this.btIndentar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btIndentar.setFocusable(false);
      this.btIndentar.setHorizontalTextPosition(0);
      this.btIndentar.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btIndentar.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btIndentar.setName("btIndentar");
      this.btIndentar.setPreferredSize(new Dimension(28, 28));
      this.btIndentar.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btIndentar);
      this.btDiminuirLetra.setAction(actionMap.get("diminuirFonte"));
      this.btDiminuirLetra.setIcon(resourceMap.getIcon("btDiminuirLetra.icon"));
      this.btDiminuirLetra.setToolTipText(resourceMap.getString("btDiminuirLetra.toolTipText"));
      this.btDiminuirLetra.setAlignmentY(0.0F);
      this.btDiminuirLetra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btDiminuirLetra.setFocusable(false);
      this.btDiminuirLetra.setHorizontalTextPosition(0);
      this.btDiminuirLetra.setLabel(resourceMap.getString("btDiminuirLetra.label"));
      this.btDiminuirLetra.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btDiminuirLetra.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btDiminuirLetra.setName("btDiminuirLetra");
      this.btDiminuirLetra.setPreferredSize(new Dimension(28, 28));
      this.barraFerramento.add(this.btDiminuirLetra);
      this.btAumentarLetra.setAction(actionMap.get("aumentarFonte"));
      this.btAumentarLetra.setIcon(resourceMap.getIcon("btAumentarLetra.icon"));
      this.btAumentarLetra.setToolTipText(resourceMap.getString("btAumentarLetra.toolTipText"));
      this.btAumentarLetra.setAlignmentY(0.0F);
      this.btAumentarLetra.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btAumentarLetra.setFocusable(false);
      this.btAumentarLetra.setHorizontalTextPosition(0);
      this.btAumentarLetra.setLabel(resourceMap.getString("btAumentarLetra.label"));
      this.btAumentarLetra.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btAumentarLetra.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btAumentarLetra.setName("btAumentarLetra");
      this.btAumentarLetra.setPreferredSize(new Dimension(28, 28));
      this.barraFerramento.add(this.btAumentarLetra);
      this.btMudarConsole.setAction(actionMap.get("mudaBotaoConsole"));
      this.btMudarConsole.setIcon(resourceMap.getIcon("btMudarConsole.icon"));
      this.btMudarConsole.setToolTipText(resourceMap.getString("btMudarConsole.toolTipText"));
      this.btMudarConsole.setAlignmentY(0.0F);
      this.btMudarConsole.setFocusable(false);
      this.btMudarConsole.setHorizontalTextPosition(0);
      this.btMudarConsole.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btMudarConsole.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btMudarConsole.setName("btMudarConsole");
      this.btMudarConsole.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btMudarConsole);
      this.jSeparator2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.jSeparator2.setName("jSeparator2");
      this.barraFerramento.add(this.jSeparator2);
      this.btSearch.setIcon(resourceMap.getIcon("btSearch.icon"));
      this.btSearch.setAlignmentY(0.0F);
      this.btSearch.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btSearch.setFocusable(false);
      this.btSearch.setHorizontalTextPosition(0);
      this.btSearch.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btSearch.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btSearch.setName("btSearch");
      this.btSearch.setPreferredSize(new Dimension(28, 28));
      this.barraFerramento.add(this.btSearch);
      this.btDebug.setAction(actionMap.get("depurar"));
      this.btDebug.setIcon(resourceMap.getIcon("btDebug.icon"));
      this.btDebug.setText(resourceMap.getString("btDebug.text"));
      this.btDebug.setToolTipText(resourceMap.getString("btDebug.toolTipText"));
      this.btDebug.setAlignmentY(0.0F);
      this.btDebug.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btDebug.setFocusable(false);
      this.btDebug.setHorizontalTextPosition(0);
      this.btDebug.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btDebug.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btDebug.setName("btDebug");
      this.btDebug.setPreferredSize(new Dimension(28, 28));
      this.btDebug.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btDebug);
      this.btPlay.setAction(actionMap.get("executar"));
      this.btPlay.setIcon(resourceMap.getIcon("btPlay.icon"));
      this.btPlay.setText(resourceMap.getString("btPlay.text"));
      this.btPlay.setToolTipText(resourceMap.getString("btPlay.toolTipText"));
      this.btPlay.setAlignmentY(0.0F);
      this.btPlay.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      this.btPlay.setFocusable(false);
      this.btPlay.setHorizontalTextPosition(4);
      this.btPlay.setMaximumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btPlay.setMinimumSize(CalangoViewUtil.isMacOS() ? null : new Dimension(24, 24));
      this.btPlay.setName("btPlay");
      this.btPlay.setPreferredSize(new Dimension(28, 28));
      this.btPlay.setVerticalTextPosition(3);
      this.barraFerramento.add(this.btPlay);
      this.jsplitVertical.setBorder((Border)null);
      this.jsplitVertical.setDividerLocation(400);
      this.jsplitVertical.setDividerSize(0);
      this.jsplitVertical.setName("jsplitVertical");
      this.jsplitVertical.setPreferredSize(new Dimension(610, 445));
      this.jpEscopo.setMaximumSize(new Dimension(0, 0));
      this.jpEscopo.setMinimumSize(new Dimension(0, 0));
      this.jpEscopo.setName("jpEscopo");
      this.jpEscopo.setPreferredSize(new Dimension(0, 0));
      GroupLayout jpEscopoLayout = new GroupLayout(this.jpEscopo);
      this.jpEscopo.setLayout(jpEscopoLayout);
      jpEscopoLayout.setHorizontalGroup(jpEscopoLayout.createParallelGroup(Alignment.LEADING).addGap(0, 232, 32767));
      jpEscopoLayout.setVerticalGroup(jpEscopoLayout.createParallelGroup(Alignment.LEADING).addGap(0, 445, 32767));
      this.jsplitVertical.setRightComponent(this.jpEscopo);
      this.jSplitHorizontal.setBorder((Border)null);
      this.jSplitHorizontal.setDividerLocation(-10);
      this.jSplitHorizontal.setDividerSize(0);
      this.jSplitHorizontal.setOrientation(0);
      this.jSplitHorizontal.setResizeWeight(1.0D);
      this.jSplitHorizontal.setCursor(new Cursor(0));
      this.jSplitHorizontal.setName("jSplitHorizontal");
      this.jpAlgoritmo.setAlignmentX(2.0F);
      this.jpAlgoritmo.setAutoscrolls(true);
      this.jpAlgoritmo.setFont(resourceMap.getFont("jpAlgoritmo.font"));
      this.jpAlgoritmo.setMinimumSize(new Dimension(0, 320));
      this.jpAlgoritmo.setName("jpAlgoritmo");
      this.jpAlgoritmo.setPreferredSize(new Dimension(255, 150));
      GroupLayout jpAlgoritmoLayout = new GroupLayout(this.jpAlgoritmo);
      this.jpAlgoritmo.setLayout(jpAlgoritmoLayout);
      jpAlgoritmoLayout.setHorizontalGroup(jpAlgoritmoLayout.createParallelGroup(Alignment.LEADING).addGap(0, 400, 32767));
      jpAlgoritmoLayout.setVerticalGroup(jpAlgoritmoLayout.createParallelGroup(Alignment.LEADING).addGap(0, 295, 32767));
      this.jSplitHorizontal.setTopComponent(this.jpAlgoritmo);
      this.jpConsole.setCursor(new Cursor(0));
      this.jpConsole.setMinimumSize(new Dimension(255, 150));
      this.jpConsole.setName("jpConsole");
      this.jpConsole.setPreferredSize(new Dimension(255, 150));
      this.jpConsole.setLayout(new GridLayout(1, 0));
      this.scrollConsole.setBorder((Border)null);
      this.scrollConsole.setName("scrollConsole");
      this.tpConsole.setName("tpConsole");
      this.scrollConsole.setViewportView(this.tpConsole);
      this.jpConsole.add(this.scrollConsole);
      this.jSplitHorizontal.setRightComponent(this.jpConsole);
      this.jsplitVertical.setLeftComponent(this.jSplitHorizontal);
      this.jpPanelRodape.setAlignmentY(0.5F);
      this.jpPanelRodape.setName("jpPanelRodape");
      this.jpPanelRodape.setPreferredSize(new Dimension(853, 23));
      this.jpPanelRodape.setLayout(new BoxLayout(this.jpPanelRodape, 2));
      this.jLabelLinha.setName("jLabelLinha");
      this.jpPanelRodape.add(this.jLabelLinha);
      this.jLabelMensagem.setHorizontalAlignment(4);
      this.jLabelMensagem.setHorizontalTextPosition(4);
      this.jLabelMensagem.setMaximumSize((Dimension)null);
      this.jLabelMensagem.setMinimumSize((Dimension)null);
      this.jLabelMensagem.setName("jLabelMensagem");
      this.jLabelMensagem.setPreferredSize((Dimension)null);
      this.jpPanelRodape.add(this.jLabelMensagem);
      this.btConsole.setAction(actionMap.get("showConsole"));
      this.btConsole.setIcon(resourceMap.getIcon("btConsole.icon"));
      this.btConsole.setText(resourceMap.getString("btConsole.text"));
      this.btConsole.setAlignmentY(0.0F);
      this.btConsole.setFocusable(false);
      this.btConsole.setHorizontalTextPosition(0);
      this.btConsole.setName("btConsole");
      this.btConsole.setVerticalAlignment(1);
      this.btConsole.setVerticalTextPosition(3);
      this.jpPanelRodape.add(this.btConsole);
      this.debugToolbar.setMaximumSize(new Dimension(500, 2000));
      this.debugToolbar.setMinimumSize(new Dimension(250, 250));
      this.debugToolbar.setName("debugToolbar");
      GroupLayout jpPrincipalLayout = new GroupLayout(this.jpPrincipal);
      this.jpPrincipal.setLayout(jpPrincipalLayout);
      jpPrincipalLayout.setHorizontalGroup(jpPrincipalLayout.createParallelGroup(Alignment.LEADING).addGroup(jpPrincipalLayout.createSequentialGroup().addGap(73, 73, 73).addGroup(jpPrincipalLayout.createParallelGroup(Alignment.LEADING).addComponent(this.jpPanelRodape, -1, 654, 32767).addGroup(jpPrincipalLayout.createSequentialGroup().addGap(22, 22, 22).addComponent(this.jsplitVertical, -2, 632, -2)).addComponent(this.barraFerramento, -2, 395, -2)).addGap(185, 185, 185).addComponent(this.debugToolbar, -2, 257, -2).addContainerGap()));
      jpPrincipalLayout.setVerticalGroup(jpPrincipalLayout.createParallelGroup(Alignment.LEADING).addGroup(jpPrincipalLayout.createSequentialGroup().addGroup(jpPrincipalLayout.createParallelGroup(Alignment.LEADING).addGroup(jpPrincipalLayout.createSequentialGroup().addComponent(this.barraFerramento, -2, 39, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jsplitVertical, -2, -1, -2).addGap(11, 11, 11).addComponent(this.jpPanelRodape, -2, 27, -2)).addGroup(Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup().addGap(50, 50, 50).addComponent(this.debugToolbar, -1, 547, 32767))).addContainerGap()));
      this.menu.setName("menu");
      this.menuArquivo.setText(resourceMap.getString("menuArquivo.text"));
      this.menuArquivo.setName("menuArquivo");
      abrirMenuItem.setAction(actionMap.get("showAbrirArquivo"));
      abrirMenuItem.setAccelerator(KeyStroke.getKeyStroke(79, 2));
      abrirMenuItem.setIcon(resourceMap.getIcon("abrirMenuItem.icon"));
      abrirMenuItem.setText(resourceMap.getString("abrirMenuItem.text"));
      abrirMenuItem.setName("abrirMenuItem");
      this.menuArquivo.add(abrirMenuItem);
      this.novoMenuItem.setAction(actionMap.get("newFile"));
      this.novoMenuItem.setAccelerator(KeyStroke.getKeyStroke(78, 2));
      this.novoMenuItem.setIcon(resourceMap.getIcon("novoMenuItem.icon"));
      this.novoMenuItem.setText(resourceMap.getString("novoMenuItem.text"));
      this.novoMenuItem.setName("novoMenuItem");
      this.menuArquivo.add(this.novoMenuItem);
      this.salvarMenuItem.setAction(actionMap.get("saveArquivo"));
      this.salvarMenuItem.setAccelerator(KeyStroke.getKeyStroke(83, 2));
      this.salvarMenuItem.setIcon(resourceMap.getIcon("salvarMenuItem.icon"));
      this.salvarMenuItem.setText(resourceMap.getString("salvarMenuItem.text"));
      this.salvarMenuItem.setName("salvarMenuItem");
      this.menuArquivo.add(this.salvarMenuItem);
      this.salvarComoMenuItem.setAction(actionMap.get("saveAs"));
      this.salvarComoMenuItem.setAccelerator(KeyStroke.getKeyStroke(83, 3));
      this.salvarComoMenuItem.setIcon(resourceMap.getIcon("salvarComoMenuItem.icon"));
      this.salvarComoMenuItem.setText(resourceMap.getString("salvarComoMenuItem.text"));
      this.salvarComoMenuItem.setName("salvarComoMenuItem");
      this.menuArquivo.add(this.salvarComoMenuItem);
      this.ImprimirMenuItem.setAction(actionMap.get("imprimir"));
      this.ImprimirMenuItem.setAccelerator(KeyStroke.getKeyStroke(80, 2));
      this.ImprimirMenuItem.setIcon(resourceMap.getIcon("ImprimirMenuItem.icon"));
      this.ImprimirMenuItem.setText(resourceMap.getString("ImprimirMenuItem.text"));
      this.ImprimirMenuItem.setName("ImprimirMenuItem");
      this.menuArquivo.add(this.ImprimirMenuItem);
      this.menuDocRecentes.setText(resourceMap.getString("menuDocRecentes.text"));
      this.menuDocRecentes.setName("menuDocRecentes");
      this.menuArquivo.add(this.menuDocRecentes);
      this.salvarComoMenuItem1.setAction(actionMap.get("quit"));
      this.salvarComoMenuItem1.setIcon(resourceMap.getIcon("sairMenuItem.icon"));
      this.salvarComoMenuItem1.setText(resourceMap.getString("sairMenuItem.text"));
      this.salvarComoMenuItem1.setName("sairMenuItem");
      this.menuArquivo.add(this.salvarComoMenuItem1);
      this.menu.add(this.menuArquivo);
      this.menuEditar.setText(resourceMap.getString("menuEditar.text"));
      this.menuEditar.setName("menuEditar");
      copiarMenuItem.setAction(actionMap.get("copiar"));
      copiarMenuItem.setAccelerator(KeyStroke.getKeyStroke(67, 2));
      copiarMenuItem.setIcon(resourceMap.getIcon("copiarMenuItem.icon"));
      copiarMenuItem.setText(resourceMap.getString("copiarMenuItem.text"));
      copiarMenuItem.setName("copiarMenuItem");
      this.menuEditar.add(copiarMenuItem);
      this.colarMenuItem.setAction(actionMap.get("colar"));
      this.colarMenuItem.setAccelerator(KeyStroke.getKeyStroke(86, 2));
      this.colarMenuItem.setIcon(resourceMap.getIcon("colarMenuItem.icon"));
      this.colarMenuItem.setText(resourceMap.getString("colarMenuItem.text"));
      this.colarMenuItem.setName("colarMenuItem");
      this.menuEditar.add(this.colarMenuItem);
      this.recortarMenuItem.setAction(actionMap.get("recortar"));
      this.recortarMenuItem.setAccelerator(KeyStroke.getKeyStroke(88, 2));
      this.recortarMenuItem.setIcon(resourceMap.getIcon("recortarMenuItem.icon"));
      this.recortarMenuItem.setText(resourceMap.getString("recortarMenuItem.text"));
      this.recortarMenuItem.setName("recortarMenuItem");
      this.menuEditar.add(this.recortarMenuItem);
      this.identarMenuItem.setAction(actionMap.get("identar"));
      this.identarMenuItem.setAccelerator(KeyStroke.getKeyStroke(71, 2));
      this.identarMenuItem.setIcon(resourceMap.getIcon("identarMenuItem.icon"));
      this.identarMenuItem.setText(resourceMap.getString("identarMenuItem.text"));
      this.identarMenuItem.setToolTipText(resourceMap.getString("identarMenuItem.toolTipText"));
      this.identarMenuItem.setName("identarMenuItem");
      this.menuEditar.add(this.identarMenuItem);
      desfazerMenuItem1.setAction(actionMap.get("desfazer"));
      desfazerMenuItem1.setAccelerator(KeyStroke.getKeyStroke(90, 2));
      desfazerMenuItem1.setIcon(resourceMap.getIcon("desfazerMenuItem1.icon"));
      desfazerMenuItem1.setText(resourceMap.getString("desfazerMenuItem1.text"));
      desfazerMenuItem1.setToolTipText(resourceMap.getString("desfazerMenuItem1.toolTipText"));
      desfazerMenuItem1.setName("desfazerMenuItem1");
      this.menuEditar.add(desfazerMenuItem1);
      refazerMenuItem2.setAction(actionMap.get("fazer"));
      refazerMenuItem2.setAccelerator(KeyStroke.getKeyStroke(89, 2));
      refazerMenuItem2.setIcon(resourceMap.getIcon("copiarMenuItem1.icon"));
      refazerMenuItem2.setText(resourceMap.getString("refazerMenuItem2.text"));
      refazerMenuItem2.setName("refazerMenuItem2");
      this.menuEditar.add(refazerMenuItem2);
      refazerMenuItem2.getAccessibleContext().setAccessibleDescription(resourceMap.getString("refazerMenuItem2.AccessibleContext.accessibleDescription"));
      this.jMenuItem2.setAction(actionMap.get("find"));
      this.jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(70, 2));
      this.jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon"));
      this.jMenuItem2.setText(resourceMap.getString("jMenuItem2.text"));
      this.jMenuItem2.setName("jMenuItem2");
      this.menuEditar.add(this.jMenuItem2);
      this.menu.add(this.menuEditar);
      this.menuAlgoritmo.setText(resourceMap.getString("menuAlgoritmo.text"));
      this.menuAlgoritmo.setName("menuAlgoritmo");
      executarMenuItem.setAction(actionMap.get("executar"));
      executarMenuItem.setAccelerator(KeyStroke.getKeyStroke(120, 0));
      executarMenuItem.setIcon(resourceMap.getIcon("executarMenuItem.icon"));
      executarMenuItem.setText(resourceMap.getString("executarMenuItem.text"));
      executarMenuItem.setName("executarMenuItem");
      this.menuAlgoritmo.add(executarMenuItem);
      this.executarPassoAPassoMenuItem.setAction(actionMap.get("depurar"));
      this.executarPassoAPassoMenuItem.setAccelerator(KeyStroke.getKeyStroke(115, 0));
      this.executarPassoAPassoMenuItem.setIcon(resourceMap.getIcon("executarPassoAPassoMenuItem.icon"));
      this.executarPassoAPassoMenuItem.setText(resourceMap.getString("executarPassoAPassoMenuItem.text"));
      this.executarPassoAPassoMenuItem.setToolTipText(resourceMap.getString("executarPassoAPassoMenuItem.toolTipText"));
      this.executarPassoAPassoMenuItem.setActionCommand(resourceMap.getString("executarPassoAPassoMenuItem.actionCommand"));
      this.executarPassoAPassoMenuItem.setName("executarPassoAPassoMenuItem");
      this.menuAlgoritmo.add(this.executarPassoAPassoMenuItem);
      this.menu.add(this.menuAlgoritmo);
      this.menuFerramentas.setText(resourceMap.getString("menuFerramentas.text"));
      this.menuFerramentas.setName("menuFerramentas");
      this.numeroLinhas.setAction(actionMap.get("showNumeroLinhas"));
      this.numeroLinhas.setAccelerator(KeyStroke.getKeyStroke(76, 2));
      this.numeroLinhas.setIcon(resourceMap.getIcon("numeroLinhas.icon"));
      this.numeroLinhas.setText(resourceMap.getString("numeroLinhas.text"));
      this.numeroLinhas.setName("numeroLinhas");
      this.menuFerramentas.add(this.numeroLinhas);
      exitMenuItem3.setAction(actionMap.get("showSettings"));
      exitMenuItem3.setIcon(resourceMap.getIcon("exitMenuItem3.icon"));
      exitMenuItem3.setText(resourceMap.getString("exitMenuItem3.text"));
      exitMenuItem3.setName("exitMenuItem3");
      this.menuFerramentas.add(exitMenuItem3);
      this.menu.add(this.menuFerramentas);
      menuAjuda.setText(resourceMap.getString("menuAjuda.text"));
      menuAjuda.setName("menuAjuda");
      this.jMenuItem1.setAction(actionMap.get("showAjuda"));
      this.jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(112, 0));
      this.jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon"));
      this.jMenuItem1.setText(resourceMap.getString("jMenuItem1.text"));
      this.jMenuItem1.setName("jMenuItem1");
      menuAjuda.add(this.jMenuItem1);
      aboutMenuItem.setAction(actionMap.get("showAboutBox"));
      aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon"));
      aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text"));
      aboutMenuItem.setName("aboutMenuItem");
      menuAjuda.add(aboutMenuItem);
      this.menu.add(menuAjuda);
      this.setComponent(this.jpPrincipal);
      this.setMenuBar(this.menu);
   }

   public CalangoSettings getCalangoSettings() {
      return this.calangoSettings;
   }

   public void setCalangoSettings(CalangoSettings calangoSettings) {
      this.calangoSettings = calangoSettings;
   }

   public Thread getCalangoThread() {
      return this.calangoThread;
   }

   public void setCalangoThread(Thread calangoThread) {
      this.calangoThread = calangoThread;
   }

   public File getFileOpen() {
      return this.fileOpen;
   }

   public void setFileOpen(File fileOpen) {
      this.fileOpen = fileOpen;
   }

   public File getFileSave() {
      return this.fileSave;
   }

   public void setFileSave(File fileSave) {
      this.fileSave = fileSave;
   }

   public boolean isIsEmExecucao() {
      return this.isEmExecucao;
   }

   public void setIsEmExecucao(boolean isEmExecucao) {
      this.isEmExecucao = isEmExecucao;
   }

   public void atualizar(CalangoSettings novaConfiguracao) {
      CalangoSettingsUtil.copyCalangoSettings(novaConfiguracao, this.calangoSettings);
      this.configuraCalangoWithSettings(false);
      this.configuraBotoesTamanhoFonte(this.calangoSettings.getTamanhoFonte());
      this.changeCalangoSettings();
   }

   private void changeCalangoSettings() {
      try {
         IOUtil.saveConfigurationFile(this.calangoSettings);
      } catch (FileNotFoundException var2) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var2);
      } catch (IOException var3) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

      this.jtAlgoritmo.repaint();
   }

   private boolean isAbrirConsoleInterna() {
      return this.consoleASerAberta == 1;
   }

   private boolean isAbrirConsoleExterna() {
      return this.consoleASerAberta == 2;
   }

   public String getTextoSalvo() {
      return this.textoSalvo;
   }

   public void setTextoSalvo(String textoSalvo) {
      this.textoSalvo = textoSalvo;
   }

   public IndentadorCalango getIdentador() {
      return this.indentador;
   }

   public void setIdentador(IndentadorCalango identador) {
      this.indentador = identador;
   }

   public void carregaArquivo(File file) {
      try {
         this.setFileOpen(file);
         this.calangoSettings.addArquivo(file.getAbsolutePath());
         IOUtil.saveConfigurationFile(this.calangoSettings);
         String arquivo = IOUtil.lerArquivo(file);
         this.carregaAlgoritmo("Calango - " + file.getName(), arquivo);
      } catch (IOException var3) {
      }

   }

   @Action
   public void diminuirFonte() {
      this.alterarTamanhoFonte(CalangoSettings.getPreviousFontSize(this.calangoSettings.getTamanhoFonte()));
   }

   @Action
   public void aumentarFonte() {
      this.alterarTamanhoFonte(CalangoSettings.getLaterFontSize(this.calangoSettings.getTamanhoFonte()));
   }

   public void alterarTamanhoFonte(Integer tamanho) {
      this.configuraBotoesTamanhoFonte(tamanho);
      this.calangoSettings.setTamanhoFonte(tamanho);
      this.configuraCalangoWithSettings(false);
      this.changeCalangoSettings();
   }

   private void configuraBotoesTamanhoFonte(Integer tamanho) {
      if (tamanho.equals(CalangoSettings.getMinimumFontSize())) {
         this.btDiminuirLetra.setEnabled(false);
         this.btAumentarLetra.setEnabled(true);
      } else if (tamanho.equals(CalangoSettings.getMaximumFontSize())) {
         this.btAumentarLetra.setEnabled(false);
         this.btDiminuirLetra.setEnabled(true);
      } else {
         this.btDiminuirLetra.setEnabled(true);
         this.btAumentarLetra.setEnabled(true);
      }

   }
}
