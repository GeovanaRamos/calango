/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.ajuda;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Giovanna
 */
public class AjudaIOHandler {
    private final static String AJUDAR_DIR = "ajuda";

    private final static List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();

    static {
        Map<String, Funcionalidade> fmap = new HashMap<String, Funcionalidade>();

        try {
            JAXBContext context = JAXBContext.newInstance(Funcionalidade.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File diretorio = new File(AJUDAR_DIR);

            if (diretorio.listFiles() == null){
                File jarFile = new File(AjudaIOHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath());
                final JarFile jar = new JarFile(jarFile);
                final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
                while(entries.hasMoreElements()) {
                    final String name = entries.nextElement().getName();
                    if (name.startsWith(AJUDAR_DIR) && name.endsWith(".xml")) { //filter according to the path
                        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
                        Funcionalidade f = (Funcionalidade) unmarshaller.unmarshal(is);
                        funcionalidades.add(f);
                        fmap.put(f.getNome(), f);
                    }
                }
                jar.close();
            } else {
                for (File child : diretorio.listFiles()) {
                    if (child.isFile() && child.getName().endsWith(".xml")) {
                        try {
                            Funcionalidade f = (Funcionalidade) unmarshaller.unmarshal(new FileInputStream(child));
                            funcionalidades.add(f);
                            fmap.put(f.getNome(), f);
                        } catch (FileNotFoundException | JAXBException ex) {
                            Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        } catch (JAXBException | IOException ex) {
            Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Funcionalidade f : fmap.values()) {
            if (fmap.containsKey(f.getParent())) {
                fmap.get(f.getParent()).addChild(f);
                funcionalidades.remove(f);
            }
        }

        Collections.sort(funcionalidades);
    }

    public static void save(Funcionalidade funcionalidade, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Funcionalidade.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(funcionalidade, new File(AJUDAR_DIR, fileName));
        } catch (JAXBException ex) {
            Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Funcionalidade> getFuncionalidades() {
        return funcionalidades;
    }


}
