package tcccalango.view.ajuda;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AjudaFrame extends JFrame {
   private FormFuncionalidadeImpl formIncludeFuncionalidade = new FormFuncionalidadeImpl();
   private FormFuncaoEmbutida formFuncaoEmbutida = new FormFuncaoEmbutida();
   private JComponent center;
   private String defaultTitle;
   Object selection;
   final JTree tree = new JTree();
   final JTextField filtro = new JTextField();

   public static void main(String[] args) throws Exception {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      JFileChooser chooser = new JFileChooser();
      chooser.setFileSelectionMode(0);
      chooser.setCurrentDirectory(new File(""));
      JOptionPane.showMessageDialog((Component)null, "Escolha o arquivo root.xml a ser editado.");
      if (chooser.showDialog((Component)null, "Abrir") == 0) {
         File root = chooser.getSelectedFile();
         AjudaFilesLoader.load(root);
         AjudaFrame frame = new AjudaFrame("Editor de Ajudas");
         frame.setDefaultCloseOperation(3);
         frame.setVisible(true);
      }

   }

   public AjudaFrame(String title) {
      super(title);
      this.defaultTitle = title;
      this.setMinimumSize(new Dimension(800, 600));
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      this.add("West", this.loadLeftBar());
      if (AjudaFilesLoader.isEditable()) {
         JButton salvar = new JButton("Salvar");
         salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               AjudaFrame.this.atualizaDadosEditor();
               AjudaFilesLoader.save();
               AjudaFrame.this.reloadRoot();
            }
         });
         this.add("South", salvar);
      }

      this.reloadRoot();
      this.carregaDadosEditor();
   }

   private boolean addChilds(JTree tree, DefaultMutableTreeNode parent, List<Funcionalidade> funcionalidades, String filtro) {
      boolean found = false;

      boolean current;
      for(Iterator i$ = funcionalidades.iterator(); i$.hasNext(); found = found || current) {
         Funcionalidade f = (Funcionalidade)i$.next();
         current = false;
         DefaultMutableTreeNode node = new DefaultMutableTreeNode(f, true);
         if (f.getNome().toUpperCase().contains(filtro)) {
            current = true;
         }

         if (this.addChilds(tree, node, f.getChilds(), filtro)) {
            current = true;
         }

         if (current) {
            parent.add(node);
         }
      }

      return found;
   }

   private JPanel loadLeftBar() {
      JPanel leftbar = new JPanel(new BorderLayout());
      leftbar.setMinimumSize(new Dimension(200, 200));
      this.tree.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (AjudaFrame.this.tree.getSelectionCount() == 1) {
               AjudaFrame.this.atualizaDadosEditor();
               AjudaFrame.this.selection = ((DefaultMutableTreeNode)AjudaFrame.this.tree.getSelectionPath().getLastPathComponent()).getUserObject();
               AjudaFrame.this.carregaDadosEditor();
            }

         }
      });
      this.tree.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 10 && AjudaFrame.this.tree.getSelectionCount() == 1) {
               AjudaFrame.this.atualizaDadosEditor();
               AjudaFrame.this.selection = ((DefaultMutableTreeNode)AjudaFrame.this.tree.getSelectionPath().getLastPathComponent()).getUserObject();
               AjudaFrame.this.carregaDadosEditor();
            }

         }
      });
      this.filtro.getDocument().addDocumentListener(new DocumentListener() {
         public void insertUpdate(DocumentEvent e) {
            try {
               AjudaFrame.this.reloadRoot(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException var3) {
               Logger.getLogger(AjudaFrame.class.getName()).log(Level.SEVERE, (String)null, var3);
            }

         }

         public void removeUpdate(DocumentEvent e) {
            this.insertUpdate(e);
         }

         public void changedUpdate(DocumentEvent e) {
         }
      });
      leftbar.add("North", this.filtro);
      leftbar.add("Center", new JScrollPane(this.tree));
      if (AjudaFilesLoader.isEditable()) {
         JPanel options = new JPanel(new GridLayout(1, 3));
         JButton addFuncionalidade = new JButton("+F");
         addFuncionalidade.setToolTipText("Adicionar Funcionalidade");
         addFuncionalidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (AjudaFrame.this.selection instanceof TreeComponent) {
                  TreeComponent tc = (TreeComponent)AjudaFrame.this.selection;
                  tc.add(new IncludeFuncionalidade("Nova Funcionalidade"));
                  AjudaFrame.this.reloadRoot();
               }

            }
         });
         options.add(addFuncionalidade);
         JButton addFuncaoEmbutida = new JButton("+FE");
         addFuncaoEmbutida.setToolTipText("Adicionar Função Embutida");
         addFuncaoEmbutida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (AjudaFrame.this.selection instanceof TreeComponent) {
                  TreeComponent tc = (TreeComponent)AjudaFrame.this.selection;
                  tc.add(new IncludeFuncionalidade(new FuncaoEmbutida("Nova Função Embutida")));
                  AjudaFrame.this.reloadRoot();
               }

            }
         });
         options.add(addFuncaoEmbutida);
         JButton remove = new JButton("-");
         remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (AjudaFrame.this.selection instanceof TreeComponent) {
                  TreeComponent tc = (TreeComponent)AjudaFrame.this.selection;
                  if (JOptionPane.showConfirmDialog((Component)null, "Deseja realmente remover " + tc + "?") == 0) {
                     tc.getParent().remove(tc);
                     AjudaFrame.this.reloadRoot();
                  }
               }

            }
         });
         options.add(remove);
         leftbar.add("South", options);
      }

      return leftbar;
   }

   private void atualizaDadosEditor() {
      if (this.selection instanceof IncludeFuncionalidade) {
         IncludeFuncionalidade include = (IncludeFuncionalidade)this.selection;
         if (include.getSelf() instanceof FuncaoEmbutida) {
            this.formFuncaoEmbutida.flush();
         } else if (include.getSelf() instanceof FuncionalidadeImpl) {
            this.formIncludeFuncionalidade.flush();
         }
      }

   }

   private void carregaDadosEditor() {
      if (this.center != null) {
         this.remove(this.center);
      }

      this.center = null;
      if (this.selection instanceof IncludeFuncionalidade) {
         IncludeFuncionalidade include = (IncludeFuncionalidade)this.selection;
         this.setTitle(this.defaultTitle + " - " + include.getNome());
         if (include.getSelf() instanceof FuncaoEmbutida) {
            this.formFuncaoEmbutida.load(include);
            this.center = this.formFuncaoEmbutida;
         } else if (include.getSelf() instanceof FuncionalidadeImpl) {
            this.formIncludeFuncionalidade.load(include);
            this.center = this.formIncludeFuncionalidade;
         }
      } else {
         this.setTitle(this.defaultTitle);
      }

      if (this.center != null) {
         this.add("Center", this.center);
      }

      ((JComponent)this.getContentPane()).revalidate();
      ((JComponent)this.getContentPane()).repaint();
   }

   private void reloadRoot() {
      this.reloadRoot("");
   }

   private void reloadRoot(String busca) {
      DefaultMutableTreeNode root = new DefaultMutableTreeNode(AjudaFilesLoader.getRoot(), true);
      this.addChilds(this.tree, root, AjudaFilesLoader.getRoot().getChilds(), busca.toUpperCase());
      this.tree.setModel(new DefaultTreeModel(root));

      for(int i = 0; i < this.tree.getRowCount(); ++i) {
         this.tree.expandRow(i);
      }

      this.tree.repaint();
   }
}
