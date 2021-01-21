package tcccalango.view.ajuda;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AjudaRoot implements TreeComponent<Funcionalidade> {
   private static File rootDir;
   @XmlAnyElement(FuncionalidadeDomHandler.class)
   private List<Funcionalidade> childs = new FuncionalidadeList(this);
   @XmlTransient
   private File file;

   public static void setRootDir(File rootDir) {
      AjudaRoot.rootDir = rootDir;
   }

   public static File getRootDir() {
      return rootDir;
   }

   public AjudaRoot() {
      this.setFile(new File("root.xml"));
   }

   public List<Funcionalidade> getChilds() {
      return this.childs;
   }

   public void add(Funcionalidade t) {
      if (!this.childs.contains(t)) {
         this.childs.add(t);
      }

      t.setParent(this);
   }

   public void setParent(TreeComponent parent) {
   }

   public TreeComponent getParent() {
      return null;
   }

   public void setChilds(List<Funcionalidade> childs) {
      this.childs = childs;
   }

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
      setRootDir(file.getParentFile());
   }

   public int compareTo(Funcionalidade o) {
      return 0;
   }

   public void remove(Funcionalidade tc) {
      this.getChilds().remove(tc);
   }

   public void saveToFile() {
      JAXBAjudaUtil.save((File)this.getFile(), this);
      Iterator i$ = this.getChilds().iterator();

      while(i$.hasNext()) {
         Funcionalidade child = (Funcionalidade)i$.next();
         child.saveToFile();
      }

   }

   public String toString() {
      return "Calango";
   }
}
