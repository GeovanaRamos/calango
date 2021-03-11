package tcccalango.view.ajuda;

import java.io.File;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(
   name = "include"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class IncludeFuncionalidade implements Funcionalidade {
   @XmlAttribute
   private String src;
   @XmlTransient
   private Funcionalidade self = new FuncionalidadeImpl();

   public IncludeFuncionalidade() {
   }

   public IncludeFuncionalidade(String nome) {
      this.self.setNome(nome);
   }

   public IncludeFuncionalidade(Funcionalidade funcionalidade) {
      this.self = funcionalidade;
   }

   public String getSrc() {
      return this.src;
   }

   public void setSrc(String src) {
      this.src = src;
   }

   public void setFuncionalidade(Funcionalidade self) {
      if (self != null) {
         this.self = self;
      }

   }

   public String getDescricao() {
      return this.self.getDescricao();
   }

   public String getDescricaoFinal() {
      return this.self.getDescricaoFinal();
   }

   public int getIndex() {
      return this.self.getIndex();
   }

   public String getNome() {
      return this.self.getNome();
   }

   public void setChilds(List<Funcionalidade> childs) {
      this.self.setChilds(childs);
   }

   public void setDescricao(String descricao) {
      this.self.setDescricao(descricao);
   }

   public void setIndex(int index) {
      this.self.setIndex(index);
   }

   public void setNome(String nome) {
      this.self.setNome(nome);
   }

   public void add(Funcionalidade t) {
      this.self.add(t);
   }

   public List<Funcionalidade> getChilds() {
      return this.self.getChilds();
   }

   public TreeComponent getParent() {
      return this.self.getParent();
   }

   public void remove(Funcionalidade tc) {
      this.self.remove(tc);
   }

   public void saveToFile() {
      JAXBAjudaUtil.save((File)(new File(AjudaRoot.getRootDir(), this.src)), this.self);
      this.self.saveToFile();
   }

   public void setParent(TreeComponent parent) {
      this.self.setParent(parent);
   }

   public int compareTo(Funcionalidade o) {
      return this.self.compareTo(o);
   }

   public String toString() {
      return this.self.toString();
   }

   public Funcionalidade getSelf() {
      return this.self;
   }
}
