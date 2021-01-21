package tcccalango.view.ajuda;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(
   name = "funcionalidade"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class FuncionalidadeImpl implements Funcionalidade {
   @XmlElement
   private String nome;
   @XmlElement
   private int index;
   @XmlElement
   private String descricao;
   @XmlAnyElement(FuncionalidadeDomHandler.class)
   private List<Funcionalidade> childs = new FuncionalidadeList(this);
   @XmlTransient
   private TreeComponent parent;

   public FuncionalidadeImpl() {
   }

   public FuncionalidadeImpl(String nome) {
      this.nome = nome;
   }

   public FuncionalidadeImpl(String nome, Integer index, String parent, String descricao) {
      this.nome = nome;
      this.index = index;
      this.descricao = descricao;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public int getIndex() {
      return this.index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

   public TreeComponent getParent() {
      return this.parent;
   }

   public void setParent(TreeComponent parent) {
      this.parent = parent;
   }

   public List<Funcionalidade> getChilds() {
      return this.childs;
   }

   public void setChilds(List<Funcionalidade> childs) {
      this.childs = childs;
   }

   public void add(Funcionalidade funcionalidade) {
      funcionalidade.setParent(this);
      this.childs.add(funcionalidade);
      Collections.sort(this.childs);
   }

   public int compareTo(Funcionalidade o) {
      return this.getIndex() - o.getIndex();
   }

   public String toString() {
      return this.getNome();
   }

   public String getDescricaoFinal() {
      return MessageFormat.format(AjudaFilesLoader.getBundleProperty("funcionalidade.pattern"), this.getNome(), this.getDescricao().replaceAll("\\s*</?(html|head|body)>\\s*", ""));
   }

   public void remove(Funcionalidade tc) {
      this.getChilds().remove(tc);
   }

   public void saveToFile() {
      Iterator i$ = this.getChilds().iterator();

      while(i$.hasNext()) {
         Funcionalidade child = (Funcionalidade)i$.next();
         child.saveToFile();
      }

   }
}
