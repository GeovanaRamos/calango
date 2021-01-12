/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.listeners;

import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author Gabriel
 */
public abstract class CarregaArquivoListener implements ActionListener {
    private File file;

    public CarregaArquivoListener(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
