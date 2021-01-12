/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.componentes;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;





/**
 *
 * "Monitor" externo para apresentaÃ§Ã£o da console
 * 
 * @author Gabriel
 */
public class CalangoMonitorFrame extends JDialog implements IMonitorFrame {
    private static final BufferedImage img;

    private JPanel imgPanel;
    private JButton button;
    private ButtonObserver observer;
    private JComponent console;
    
    /**
     * Define o componente que serÃ¡ posicionado no lugar da "tela" do "monitor"
     * @param console 
     */
    @Override
    public void setConsole(JComponent console){
        console.setBounds(35, 155, 620, 310);
        imgPanel.add(BorderLayout.CENTER, this.console = console);
    }

    public boolean isConsole(JComponent console) {
        return console.equals(this.console);
    }
    
    static{
        BufferedImage i = null;
        try {
            i = ImageIO.read(CalangoMonitorFrame.class.getResourceAsStream("monitor.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        img = i;
    }

    /**
     * 
     * @param observer observador para aÃ§Ãµes disparadas pelo botÃ£o do monitor
     * @param console
     * @throws HeadlessException 
     */
    public CalangoMonitorFrame(ButtonObserver observer, JComponent console) throws HeadlessException {
        this(observer);
        setConsole(console);
    }

    /**
     * 
     * @param observer observador para aÃ§Ãµes disparadas pelo botÃ£o do monitor
     * @throws HeadlessException 
     */
    public CalangoMonitorFrame(ButtonObserver observer) throws HeadlessException {
        this.observer = observer;
            
        // O tamanho Ã© definido estaticamente para facilitar o posicionamento dos componentes
        setSize(700, 600);
        setLocationRelativeTo(null);
        setModal(true);
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        // Define a janela undecorated (nÃ£o possui a barra padrÃ£o das janelas do sistema operacional)
        setUndecorated(true);
        
        // Cria um panel capaz de usar a imagem como background
        imgPanel = new JPanel(){

            @Override
            public void paint(Graphics g) {
                // Desenha a imagem no lugar do background
                g.drawImage(img, getX(), getY(), getWidth(), getHeight(), this);
                // Desenha os componentes filhos
                paintComponents(g);
            }
            
        };

        // Cria o Listener que move o monitor de acordo com o movimento do mouse
        CustomMouseListener cml = new CustomMouseListener(this);
        imgPanel.setFocusable(true);
        imgPanel.addMouseListener(cml.mouseListener);
        imgPanel.addMouseMotionListener(cml.mouseMotionListener);
        
        // Cria e posiciona o botÃ£o no monitor
        button = new JButton("");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalangoMonitorFrame.this.observer.clicked();
            }
        });
        button.setBounds(605, 479, 30, 22);
        add(button);

        // Define um layout nulo para que os componentes sejam posicionados livremente
        imgPanel.setLayout(null);
        
        add(imgPanel);
        
        // Define a janela como transparente (pode nÃ£o funcionar)
        //AWTUtilities.setWindowOpaque(this, false);

        // Java 11
        this.setOpacity(0);
    }

    public void removeConsole() {
        if (console!=null){
            imgPanel.remove(console);
            console = null;
        }
    }
    
    @Override
    public JButton getButton() {
        return button;
    }

    /** 
     * Interface utilizada como observador de aÃ§Ãµes disparadas pelo botÃ£o do monitor
     */
    public interface ButtonObserver {
        public void clicked();
    }
}

/**
 * Essa classe possui dois listeners:
 *  mouseListener: responsÃ¡vel por identifica o clique do mouse (a seleÃ§Ã£o da janela)
 *  mouseMotionListener: responsÃ¡vel por identificar o movimento do mouse e mover quando o mouse estiver clicado
 * @author Gabriel
 */
class CustomMouseListener{
    
    private CalangoMonitorFrame frame;

    public CustomMouseListener(CalangoMonitorFrame frame) {
        this.frame = frame;
    }
    
    private Point inicialPoint;
    
    /**
     * listener de clique do mouse
     */
    final MouseListener mouseListener = new MouseAdapter(){

        @Override
        public void mousePressed(MouseEvent e) {
            inicialPoint = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            inicialPoint = null;
        }
    };

    /**
     * listener de movimento do mouse
     */
    final MouseMotionListener mouseMotionListener = new MouseMotionAdapter(){
        @Override
        public void mouseDragged(MouseEvent e) {
            if (inicialPoint!=null){
                Point novo = e.getPoint();
                Point ponto = frame.getLocation();
                frame.setLocation(ponto.x + (novo.x-inicialPoint.x), ponto.y + (novo.y-inicialPoint.y));
            }
        }
    };
}
