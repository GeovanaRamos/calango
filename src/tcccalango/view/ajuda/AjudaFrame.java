/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.ajuda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Giovanna
 */
public class AjudaFrame extends JFrame {

    private AjudaFrame(){}
    
    public static AjudaFrame output(){
        final AjudaFrame frame = new AjudaFrame();
        frame.setTitle("Ajuda Calango");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        final JEditorPane visualizacao = new JEditorPane("text/html", "");
        visualizacao.setEditable(false);
        
        final JTree tree = new JTree();
        tree.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (tree.getSelectionCount()==1){
                    Object selection = ((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent()).getUserObject();
                    if (selection instanceof Funcionalidade){
                        frame.setTitle("Ajuda Calango - "+((Funcionalidade) selection).getNome());
                        visualizacao.setText(((Funcionalidade) selection).getDescricao());
                        visualizacao.setCaretPosition(0);
                    }else{
                        frame.setTitle("Ajuda Calango");
                    }
                }
            }
            
        });
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Calango", true);
        addChilds(tree, root, AjudaIOHandler.getFuncionalidades(), "");
        tree.setModel(new DefaultTreeModel(root));
        
        JPanel leftbar = new JPanel(new BorderLayout());
        leftbar.setMinimumSize(new Dimension(200, 200));
        
        final JTextField filtro = new JTextField();
        filtro.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("Calango", true);
                addChilds(tree, root, AjudaIOHandler.getFuncionalidades(), filtro.getText().toUpperCase());
                tree.setModel(new DefaultTreeModel(root));
            }
            
        });
        
        leftbar.add(BorderLayout.NORTH, filtro);
        leftbar.add(BorderLayout.CENTER, tree);
        
        split.add(new JScrollPane(leftbar));
        split.add(new JScrollPane(visualizacao));
        
        frame.add(split);

        return frame;
    }
    
    public static AjudaFrame input(){
        AjudaFrame frame = new AjudaFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        
        
        frame.setLayout(new BorderLayout());
        
        JPanel form = new JPanel(new GridLayout(2, 2));
        
        form.add(new JLabel("Nome"));
        final JTextField nome = new JTextField();
        form.add(nome);
        
        form.add(new JLabel("Index"));
        final SpinnerNumberModel index = new SpinnerNumberModel();
        form.add(new JSpinner(index));
        
        form.add(new JLabel("Parent"));
        final JTextField parent = new JTextField();
        form.add(parent);
        
        form.add(new JLabel("Arquivo"));
        final JTextField arquivo = new JTextField();
        form.add(arquivo);
        
        frame.add(BorderLayout.NORTH, form);
        
        final JEditorPane descricao = new JEditorPane("text/html", "");
        frame.add(BorderLayout.CENTER, new JScrollPane(descricao));
        
        JButton submeter = new JButton("Salvar");
        frame.add(BorderLayout.SOUTH, submeter);
        submeter.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AjudaIOHandler.save(new Funcionalidade(
                        nome.getText(), index.getNumber().intValue(),
                        parent.getText(), descricao.getText()), 
                        arquivo.getText());
            }
            
        });
        
        return frame;
    }

    private static boolean addChilds(JTree tree, DefaultMutableTreeNode parent, List<Funcionalidade> funcionalidades, String filtro) {
        boolean found = false;
        for (Funcionalidade f : funcionalidades){
            boolean current = false;
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(f, true);
            
            if (f.getNome().toUpperCase().contains(filtro)){
                current = true;
            }
            
            if (addChilds(tree, node, f.getChilds(), filtro)){
                current = true;
            }
            
            if (current){
                parent.add(node);
                tree.expandRow(tree.getRowCount()-1);
            }
            
            found = found || current;
        }
        return found;
    }
}
