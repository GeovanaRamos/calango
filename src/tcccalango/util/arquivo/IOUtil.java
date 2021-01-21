package tcccalango.util.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcccalango.util.settings.CalangoSettings;
import tcccalango.view.TCCCalangoView;

public class IOUtil {
   public static final String CONF_FILE_NAME = "calango.conf";

   public static String lerArquivo(File f) throws IOException {
      StringBuilder texto = new StringBuilder();

      try {
         FileInputStream fis = new FileInputStream(f);
         InputStreamReader reader = new InputStreamReader(fis, "UTF8");
         BufferedReader br = new BufferedReader(reader);

         while(br.ready()) {
            texto.append(br.readLine());
            texto.append("\n");
         }

         br.close();
         reader.close();
         fis.close();
      } catch (IOException var5) {
         Logger.getLogger(TCCCalangoView.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      return texto != null && !texto.toString().isEmpty() ? texto.toString() : "";
   }

   public static boolean salvarArquivo(File file, String texto) {
      try {
         FileOutputStream fileOut = new FileOutputStream(file);
         OutputStreamWriter fOutPut = new OutputStreamWriter(fileOut, "UTF8");
         fOutPut.write(texto);
         fOutPut.close();
         fileOut.close();
         return true;
      } catch (IOException var4) {
         return false;
      }
   }

   public static void saveConfigurationFile(CalangoSettings calangoSettings) throws FileNotFoundException, IOException {
      File arquivoSetting = new File("calango.conf");
      FileOutputStream streamOut = new FileOutputStream(arquivoSetting);
      ObjectOutputStream streamOjetos = new ObjectOutputStream(streamOut);
      streamOjetos.writeObject(calangoSettings);
      streamOjetos.close();
      streamOut.close();
   }

   public static CalangoSettings getConfigurationFromConfigurationFile() throws FileNotFoundException, IOException, ClassNotFoundException {
      File arquivo = new File("calango.conf");
      FileInputStream streamIn = new FileInputStream(arquivo);
      ObjectInputStream streamObjetos = new ObjectInputStream(streamIn);
      CalangoSettings calangoSettings = (CalangoSettings)streamObjetos.readObject();
      streamObjetos.close();
      streamIn.close();
      return calangoSettings;
   }
}
