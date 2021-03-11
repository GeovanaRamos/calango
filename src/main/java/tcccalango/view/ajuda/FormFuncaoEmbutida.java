package tcccalango.view.ajuda;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class FormFuncaoEmbutida extends JPanel implements Form {
   private JTextField nome = new JTextField();
   private JTextField arquivo = new JTextField();
   private SpinnerNumberModel index = new SpinnerNumberModel();
   private JEditorPane descricao = new JEditorPane();
   private JTextField tipoRetorno = new JTextField();
   private JTextField descricaoRetorno = new JTextField();
   private ParametrosTableModel tableModel;
   private JTable parametros;
   private IncludeFuncionalidade include;

   public FormFuncaoEmbutida() {
      super(new BorderLayout());
      this.parametros = new JTable(this.tableModel = new ParametrosTableModel());
      if (AjudaFilesLoader.isEditable()) {
         JPanel formGeral = new JPanel(new GridLayout(5, 2));
         formGeral.add(new JLabel("Nome"));
         formGeral.add(this.nome);
         this.nome.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
               this.update(e);
            }

            public void removeUpdate(DocumentEvent e) {
               this.update(e);
            }

            private void update(DocumentEvent e) {
               try {
                  FormFuncaoEmbutida.this.arquivo.setText(e.getDocument().getText(0, e.getDocument().getLength()).replaceAll("[^A-Za-z]", "") + ".xml");
               } catch (BadLocationException var3) {
                  Logger.getLogger(AjudaFrame.class.getName()).log(Level.SEVERE, (String)null, var3);
               }

            }

            public void changedUpdate(DocumentEvent e) {
            }
         });
         formGeral.add(new JLabel("Index"));
         formGeral.add(new JSpinner(this.index));
         formGeral.add(new JLabel("Arquivo"));
         formGeral.add(this.arquivo);
         formGeral.add(new JLabel("Tipo de Retorno"));
         formGeral.add(this.tipoRetorno);
         formGeral.add(new JLabel("Descriçao do Retorno"));
         formGeral.add(this.descricaoRetorno);
         this.add("North", formGeral);
         JPanel formDetalhes = new JPanel(new GridLayout(2, 1));
         formDetalhes.add(new JScrollPane(this.descricao));
         formDetalhes.add(new JScrollPane(this.parametros));
         this.add("Center", formDetalhes);
         JPanel options = new JPanel(new GridLayout(1, 4));
         JButton adicionar = new JButton("+");
         adicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               FormFuncaoEmbutida.this.tableModel.addRow();
               FormFuncaoEmbutida.this.parametros.revalidate();
            }
         });
         options.add(adicionar);
         JButton remover = new JButton("-");
         remover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               FormFuncaoEmbutida.this.tableModel.removeRow(FormFuncaoEmbutida.this.parametros.getSelectedRow());
               FormFuncaoEmbutida.this.parametros.revalidate();
            }
         });
         options.add(remover);
         JButton subir = new JButton("^");
         subir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               int row = FormFuncaoEmbutida.this.parametros.getSelectedRow();
               if (FormFuncaoEmbutida.this.tableModel.upRow(row)) {
                  FormFuncaoEmbutida.this.parametros.setSelectionMode(row - 1);
                  FormFuncaoEmbutida.this.parametros.repaint();
               }

            }
         });
         options.add(subir);
         JButton descer = new JButton("v");
         descer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
               int row = FormFuncaoEmbutida.this.parametros.getSelectedRow();
               if (FormFuncaoEmbutida.this.tableModel.downRow(row)) {
                  FormFuncaoEmbutida.this.parametros.setSelectionMode(row + 11);
                  FormFuncaoEmbutida.this.parametros.repaint();
               }

            }
         });
         options.add(descer);
         this.add("South", options);
      } else {
         this.descricao.setEditable(false);
         this.descricao.setEditorKit(new CustomHTMLEditorKit());
         this.add("Center", new JScrollPane(this.descricao));
      }

   }

   public void load(IncludeFuncionalidade include) {
      this.include = include;
      FuncaoEmbutida funcao = (FuncaoEmbutida)include.getSelf();
      if (AjudaFilesLoader.isEditable()) {
         this.nome.setText(funcao.getNome());
         this.index.setValue(funcao.getIndex());
         this.arquivo.setText(include.getSrc());
         this.descricao.setText(funcao.getDescricao());
         this.tipoRetorno.setText(funcao.getTipoRetorno());
         this.descricaoRetorno.setText(funcao.getDescricaoRetorno());
         this.tableModel.clear();
         int row = 0;

         for(Iterator i$ = funcao.getParametros().iterator(); i$.hasNext(); ++row) {
            Parametro p = (Parametro)i$.next();
            this.tableModel.addRow();
            this.tableModel.setValueAt(p.getTipo(), row, 0);
            this.tableModel.setValueAt(p.getNome(), row, 1);
            this.tableModel.setValueAt(p.getDescricao(), row, 2);
         }
      } else {
         this.descricao.setText(funcao.getDescricaoFinal());
      }

   }

   public void flush() {
      if (this.include != null && AjudaFilesLoader.isEditable()) {
         FuncaoEmbutida funcao = (FuncaoEmbutida)this.include.getSelf();
         funcao.setNome(this.nome.getText());
         funcao.setIndex(this.index.getNumber().intValue());
         this.include.setSrc(this.arquivo.getText());
         funcao.setDescricao(this.descricao.getText());
         funcao.setTipoRetorno(this.tipoRetorno.getText());
         funcao.setDescricaoRetorno(this.descricaoRetorno.getText());
         funcao.getParametros().clear();

         for(int row = 0; row < this.tableModel.getRowCount(); ++row) {
            Parametro p = new Parametro();
            p.setTipo((String)this.tableModel.getValueAt(row, 0));
            p.setNome((String)this.tableModel.getValueAt(row, 1));
            p.setDescricao((String)this.tableModel.getValueAt(row, 2));
            funcao.getParametros().add(p);
         }
      }

   }
}
