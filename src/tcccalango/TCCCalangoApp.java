/*
 * TCCCalangoApp.java
 */

package tcccalango;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tcccalango.view.TCCCalangoView;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TCCCalangoApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new TCCCalangoView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TCCCalangoApp
     */
    public static TCCCalangoApp getApplication() {
        return Application.getInstance(TCCCalangoApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        try {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Calango");
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException e) {
                System.out.println("ClassNotFoundException: " + e.getMessage());
        }
        catch(InstantiationException e) {
                System.out.println("InstantiationException: " + e.getMessage());
        }
        catch(IllegalAccessException e) {
                System.out.println("IllegalAccessException: " + e.getMessage());
        }
        catch(UnsupportedLookAndFeelException e) {
                System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
        }
        launch(TCCCalangoApp.class, args);
    }
}
