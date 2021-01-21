package tcccalango.view.listeners;

import java.awt.event.ActionListener;
import java.io.File;

public abstract class CarregaArquivoListener implements ActionListener {
   private File file;

   public CarregaArquivoListener(File file) {
      this.file = file;
   }

   public File getFile() {
      return this.file;
   }
}
