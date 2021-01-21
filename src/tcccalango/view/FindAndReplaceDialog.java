package tcccalango.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.SearchEngine;

public class FindAndReplaceDialog extends JDialog {
   private static final long serialVersionUID = 1L;
   private RSyntaxTextArea textArea;
   private JTextField localizar;
   private JTextField substituir;
   private JCheckBox regex;
   private JCheckBox matchCase;
   private JCheckBox backwards;
   private JCheckBox wholeWord;
   private JButton buttonLocalizar;
   private JButton buttonSubstituir;
   private JButton buttonSubstituirTudo;

   public FindAndReplaceDialog(Window parent, RSyntaxTextArea textArea) {
      super(parent);
      this.textArea = textArea;
      this.setSize(600, 170);
      this.setResizable(false);
      this.setAlwaysOnTop(true);
      this.setLayout(new BorderLayout(10, 10));
      ((JComponent)this.getContentPane()).setBorder(new LineBorder(this.getBackground(), 10));
      this.loadCommands();
      this.loadForm();
      this.setTitle("Localizar e Substituir");
   }

   public void open() {
      this.localizar.setText("");
      this.substituir.setText("");
      this.regex.setSelected(false);
      this.matchCase.setSelected(false);
      this.backwards.setSelected(false);
      this.wholeWord.setSelected(false);
      this.buttonLocalizar.setEnabled(false);
      this.buttonSubstituir.setEnabled(false);
      this.buttonSubstituirTudo.setEnabled(false);
      this.setLocationRelativeTo(this.textArea);
      this.setVisible(true);
   }

   private void loadCommands() {
      JPanel commands = new JPanel(new GridLayout(4, 1, 10, 10));
      commands.setPreferredSize(new Dimension(150, 200));
      this.add("East", commands);
      this.buttonLocalizar = new JButton("Localizar");
      this.buttonLocalizar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SearchEngine.find(FindAndReplaceDialog.this.textArea, FindAndReplaceDialog.this.localizar.getText(), !FindAndReplaceDialog.this.backwards.isSelected(), FindAndReplaceDialog.this.matchCase.isSelected(), FindAndReplaceDialog.this.wholeWord.isSelected(), FindAndReplaceDialog.this.regex.isSelected());
         }
      });
      commands.add(this.buttonLocalizar);
      this.buttonSubstituir = new JButton("Substituir");
      this.buttonSubstituir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SearchEngine.replace(FindAndReplaceDialog.this.textArea, FindAndReplaceDialog.this.localizar.getText(), FindAndReplaceDialog.this.substituir.getText(), !FindAndReplaceDialog.this.backwards.isSelected(), FindAndReplaceDialog.this.matchCase.isSelected(), FindAndReplaceDialog.this.wholeWord.isSelected(), FindAndReplaceDialog.this.regex.isSelected());
         }
      });
      commands.add(this.buttonSubstituir);
      this.buttonSubstituirTudo = new JButton("Substituir tudo");
      this.buttonSubstituirTudo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SearchEngine.replaceAll(FindAndReplaceDialog.this.textArea, FindAndReplaceDialog.this.localizar.getText(), FindAndReplaceDialog.this.substituir.getText(), FindAndReplaceDialog.this.matchCase.isSelected(), FindAndReplaceDialog.this.wholeWord.isSelected(), FindAndReplaceDialog.this.regex.isSelected());
         }
      });
      commands.add(this.buttonSubstituirTudo);
      JButton fechar = new JButton("Fechar");
      fechar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FindAndReplaceDialog.this.setVisible(false);
         }
      });
      commands.add(fechar);
   }

   private void loadForm() {
      JPanel form = new JPanel(new BorderLayout(10, 10));
      JPanel labels = new JPanel(new GridLayout(4, 1, 10, 10));
      labels.add(new JLabel("Localizar:"));
      labels.add(new JLabel("Substituir:"));
      form.add("West", labels);
      JPanel campos = new JPanel(new BorderLayout());
      JPanel textos = new JPanel(new GridLayout(2, 1, 10, 10));
      this.localizar = new JTextField();
      this.localizar.getDocument().addDocumentListener(new DocumentListener() {
         public void insertUpdate(DocumentEvent e) {
            FindAndReplaceDialog.this.buttonLocalizar.setEnabled(!FindAndReplaceDialog.this.localizar.getText().isEmpty());
            FindAndReplaceDialog.this.buttonSubstituir.setEnabled(!FindAndReplaceDialog.this.localizar.getText().isEmpty());
            FindAndReplaceDialog.this.buttonSubstituirTudo.setEnabled(!FindAndReplaceDialog.this.localizar.getText().isEmpty());
         }

         public void removeUpdate(DocumentEvent e) {
            this.insertUpdate(e);
         }

         public void changedUpdate(DocumentEvent e) {
            this.insertUpdate(e);
         }
      });
      textos.add(this.localizar);
      this.substituir = new JTextField();
      textos.add(this.substituir);
      campos.add("North", textos);
      JPanel checkboxs = new JPanel(new GridLayout(2, 2, 10, 10));
      this.matchCase = new JCheckBox("Coincidir maiúsculas e minúsculas");
      checkboxs.add(this.matchCase);
      this.regex = new JCheckBox("Expressões regulares");
      checkboxs.add(this.regex);
      this.wholeWord = new JCheckBox("Palavras inteiras");
      checkboxs.add(this.wholeWord);
      this.backwards = new JCheckBox("Pesquisa para trás");
      checkboxs.add(this.backwards);
      campos.add("South", checkboxs);
      form.add("Center", campos);
      this.add("Center", form);
   }
}
