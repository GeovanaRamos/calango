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
      return (new Scanner(this.getClass().getClassLoader().getResourceAsStream("compile-date.txt"))).nextLine();
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
      JLabel homepageLabel = new JLabel();
      JLabel appHomepageLabel = new JLabel();
      JLabel appDescLabel = new JLabel();
      JLabel imageLabel = new JLabel();
      JLabel homepageLabel1 = new JLabel();
      JLabel appHomepageLabel1 = new JLabel();
      JLabel homepageLabel2 = new JLabel();
      JLabel appHomepageLabel2 = new JLabel();
      this.setDefaultCloseOperation(2);
      ResourceMap resourceMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getResourceMap(TCCCalangoAboutBox.class);
      this.setTitle(resourceMap.getString("title"));
      this.setModal(true);
      this.setName("aboutBox");
      this.setResizable(false);
      ActionMap actionMap = ((TCCCalangoApp)Application.getInstance(TCCCalangoApp.class)).getContext().getActionMap(TCCCalangoAboutBox.class, this);
      this.closeButton.setAction(actionMap.get("closeAboutBox"));
      this.closeButton.setName("closeButton");
      appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | 1, (float)(appTitleLabel.getFont().getSize() + 4)));
      appTitleLabel.setText(resourceMap.getString("Application.title"));
      appTitleLabel.setName("appTitleLabel");
      versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | 1));
      versionLabel.setText(resourceMap.getString("versionLabel.text"));
      versionLabel.setName("versionLabel");
      appVersionLabel.setText(resourceMap.getString("Application.version"));
      appVersionLabel.setName("appVersionLabel");
      vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | 1));
      vendorLabel.setText(resourceMap.getString("vendorLabel.text"));
      vendorLabel.setName("vendorLabel");
      appVendorLabel.setText(resourceMap.getString("Application.vendor"));
      appVendorLabel.setName("appVendorLabel");
      homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | 1));
      homepageLabel.setText(resourceMap.getString("homepageLabel.text"));
      homepageLabel.setName("homepageLabel");
      appHomepageLabel.setText(resourceMap.getString("Application.homepage"));
      appHomepageLabel.setName("appHomepageLabel");
      appDescLabel.setText(resourceMap.getString("appDescLabel.text"));
      appDescLabel.setName("appDescLabel");
      imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon"));
      imageLabel.setName("imageLabel");
      homepageLabel1.setFont(homepageLabel1.getFont().deriveFont(homepageLabel1.getFont().getStyle() | 1));
      homepageLabel1.setText(resourceMap.getString("homepageLabel1.text"));
      homepageLabel1.setName("homepageLabel1");
      appHomepageLabel1.setText(resourceMap.getString("appHomepageLabel1.text"));
      appHomepageLabel1.setName("appHomepageLabel1");
      homepageLabel2.setFont(homepageLabel2.getFont().deriveFont(homepageLabel2.getFont().getStyle() | 1));
      homepageLabel2.setText(resourceMap.getString("homepageLabel2.text"));
      homepageLabel2.setName("homepageLabel2");
      appHomepageLabel2.setText(this.getDataCompilacao());
      appHomepageLabel2.setName("appHomepageLabel2");
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(imageLabel, -2, 199, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(appTitleLabel).addComponent(appDescLabel, -1, 337, 32767).addComponent(this.closeButton, Alignment.TRAILING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(versionLabel).addComponent(vendorLabel).addComponent(homepageLabel).addComponent(homepageLabel1).addComponent(homepageLabel2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(appHomepageLabel2).addComponent(appHomepageLabel1).addComponent(appVersionLabel).addComponent(appVendorLabel).addComponent(appHomepageLabel)))).addContainerGap()));
      layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(imageLabel, -1, 206, 32767).addGroup(layout.createSequentialGroup().addComponent(appTitleLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(appDescLabel, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(versionLabel).addComponent(appVersionLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(vendorLabel).addComponent(appVendorLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(homepageLabel).addComponent(appHomepageLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(homepageLabel1).addComponent(appHomepageLabel1)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(homepageLabel2).addComponent(appHomepageLabel2)).addPreferredGap(ComponentPlacement.RELATED, 39, 32767).addComponent(this.closeButton))).addContainerGap()));
      this.pack();
   }
}
