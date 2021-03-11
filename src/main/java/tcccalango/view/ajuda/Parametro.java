package tcccalango.view.ajuda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Parametro {
   @XmlElement
   private String tipo;
   @XmlElement
   private String nome;
   @XmlElement
   private String descricao;

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
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
}
