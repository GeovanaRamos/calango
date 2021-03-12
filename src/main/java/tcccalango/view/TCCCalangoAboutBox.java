package tcccalango.view;

import java.awt.Frame;
import java.util.Scanner;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import tcccalango.TCCCalangoApp;

public class TCCCalangoAboutBox extends JDialog {
    private JButton closeButton;

    public TCCCalangoAboutBox(Frame parent) {
        super(parent);
        this.initComponents();
        this.getRootPane().setDefaultButton(this.closeButton);
    }

    public String getDataCompilacao() {
        return (new Scanner(this.getClass().getResourceAsStream("/version.txt"))).nextLine();
    }

    @Action
    public void closeAboutBox() {
        this.dispose();
    }

    private void initComponents() {
        this.closeButton = new JButton();
        JLabel appTitleLabel = new JLabel();
        JLabel versionLabel = new JLabel();
        JLabel appVersionLabel = new JLabel();
        JLabel vendorLabel = new JLabel();
        JLabel appVendorLabel = new JLabel();
        JLabel appDescLabel = new JLabel();
        JLabel imageLabel = new JLabel();
        this.setDefaultCloseOperation(2);
        ResourceMap resourceMap = Application.getInstance(TCCCalangoApp.class).getContext().getResourceMap(TCCCalangoAboutBox.class);
        this.setTitle(resourceMap.getString("title"));
        this.setModal(true);
        this.setName("aboutBox");
        this.setResizable(false);
        ActionMap actionMap = Application.getInstance(TCCCalangoApp.class).getContext().getActionMap(TCCCalangoAboutBox.class, this);
        this.closeButton.setAction(actionMap.get("closeAboutBox"));
        this.closeButton.setName("closeButton");
        appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 1, (float) (appTitleLabel.getFont().getSize() + 4)));
        appTitleLabel.setText(resourceMap.getString("Application.title"));
        appTitleLabel.setName("appTitleLabel");
        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 1));
        versionLabel.setText(resourceMap.getString("versionLabel.text"));
        versionLabel.setName("versionLabel");
        appVersionLabel.setText(this.getDataCompilacao());
        appVersionLabel.setName("appVersionLabel");
        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 1));
        vendorLabel.setText(resourceMap.getString("vendorLabel.text"));
        vendorLabel.setName("vendorLabel");
        appVendorLabel.setText(resourceMap.getString("Application.vendor"));
        appVendorLabel.setName("appVendorLabel");
        appDescLabel.setText(resourceMap.getString("appDescLabel.text"));
        appDescLabel.setName("appDescLabel");
        imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon"));
        imageLabel.setName("imageLabel");
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(imageLabel, -2, 199, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(appTitleLabel).addComponent(appDescLabel, -1, 337, 32767).addComponent(this.closeButton, Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(versionLabel).addComponent(vendorLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(appVersionLabel).addComponent(appVendorLabel)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(imageLabel, -1, 206, 32767).addGroup(layout.createSequentialGroup().addComponent(appTitleLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(appDescLabel, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(versionLabel).addComponent(appVersionLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(vendorLabel).addComponent(appVendorLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE)).addPreferredGap(ComponentPlacement.RELATED, 39, 32767).addComponent(this.closeButton))).addContainerGap()));
        this.pack();
    }
}
