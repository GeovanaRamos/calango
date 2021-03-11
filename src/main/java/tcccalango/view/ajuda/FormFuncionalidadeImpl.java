package tcccalango.view.ajuda;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class FormFuncionalidadeImpl extends JPanel implements Form {
   private JTextField nome = new JTextField();
   private SpinnerNumberModel index = new SpinnerNumberModel();
   private JTextField arquivo = new JTextField();
   private JEditorPane descricao = new JEditorPane();
   private IncludeFuncionalidade include;

   public FormFuncionalidadeImpl() {
      super(new BorderLayout());
      this.descricao.setEditable(AjudaFilesLoader.isEditable());
      this.add("Center", new JScrollPane(this.descricao));
      if (AjudaFilesLoader.isEditable()) {
         JPanel form = new JPanel(new GridLayout(3, 2));
         form.add(new JLabel("Nome"));
         this.nome.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
               this.update(e);
            }

            public void removeUpdate(DocumentEvent e) {
               this.update(e);
            }

            private void update(DocumentEvent e) {
               try {
                  FormFuncionalidadeImpl.this.arquivo.setText(e.getDocument().getText(0, e.getDocument().getLength()).replaceAll("[^A-Za-z]", "") + ".xml");
               } catch (BadLocationException var3) {
                  Logger.getLogger(AjudaFrame.class.getName()).log(Level.SEVERE, (String)null, var3);
               }

            }

            public void changedUpdate(DocumentEvent e) {
            }
         });
         form.add(this.nome);
         form.add(new JLabel("Index"));
         form.add(new JSpinner(this.index));
         form.add(new JLabel("Arquivo"));
         form.add(this.arquivo);
         this.add("North", form);
      } else {
         this.descricao.setEditorKit(new CustomHTMLEditorKit());
      }

   }

   public void load(IncludeFuncionalidade include) {
      this.include = include;
      FuncionalidadeImpl funcionalidade = (FuncionalidadeImpl)include.getSelf();
      if (AjudaFilesLoader.isEditable()) {
         this.descricao.setText(funcionalidade.getDescricao());
         this.index.setValue(funcionalidade.getIndex());
         this.nome.setText(funcionalidade.getNome());
         this.arquivo.setText(include.getSrc());
      } else {
         this.descricao.setText(funcionalidade.getDescricaoFinal());
      }

   }

   public void flush() {
      if (this.include != null && AjudaFilesLoader.isEditable()) {
         FuncionalidadeImpl funcionalidade = (FuncionalidadeImpl)this.include.getSelf();
         funcionalidade.setDescricao(this.descricao.getText());
         funcionalidade.setIndex(this.index.getNumber().intValue());
         funcionalidade.setNome(this.nome.getText());
         this.include.setSrc(this.arquivo.getText());
      }

   }
}
