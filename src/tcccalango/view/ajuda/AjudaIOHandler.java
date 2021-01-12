/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.ajuda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Giovanna
 */
public class AjudaIOHandler {
    private final static String AJUDAR_DIR = "ajuda";
    
    private final static List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
    
    static{
        Map<String, Funcionalidade> fmap = new HashMap<String, Funcionalidade>();
        
        try {
            JAXBContext context = JAXBContext.newInstance(Funcionalidade.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File diretorio = new File(AJUDAR_DIR);
            

            for (File child : diretorio.listFiles()){
                if (child.isFile() && child.getName().endsWith(".xml")){
                    try {
                        Funcionalidade f = (Funcionalidade) unmarshaller.unmarshal(new FileInputStream(child));
                        funcionalidades.add(f); 
                        fmap.put(f.getNome(), f);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JAXBException ex) {
                        Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(AjudaIOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Funcionalidade f : fmap.values()){
            if (fmap.containsKey(f.getParent())){
                fmap.get(f.getParent()).addChild(f);
                funcionalidades.remove(f);
            }
        }
        
        Collections.sort(funcionalidades);
    }
    
    public static void save(Funcionalidade funcionalidade, String fileName){
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
