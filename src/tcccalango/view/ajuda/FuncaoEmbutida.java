package tcccalango.view.ajuda;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FuncaoEmbutida extends FuncionalidadeImpl {
   @XmlElement
   private String tipoRetorno;
   @XmlElement
   private String descricaoRetorno;
   @XmlElement
   @XmlElementWrapper
   private List<Parametro> parametros = new ArrayList();

   public FuncaoEmbutida(String nome) {
      super(nome);
   }

   public FuncaoEmbutida() {
   }

   public String getTipoRetorno() {
      return this.tipoRetorno;
   }

   public void setTipoRetorno(String tipoRetorno) {
      this.tipoRetorno = tipoRetorno;
   }

   public String getDescricaoRetorno() {
      return this.descricaoRetorno;
   }

   public void setDescricaoRetorno(String descricaoRetorno) {
      this.descricaoRetorno = descricaoRetorno;
   }

   public List<Parametro> getParametros() {
      return this.parametros;
   }

   public void setParametros(List<Parametro> parametros) {
      this.parametros = parametros;
   }

   public String getDescricaoFinal() {
      return MessageFormat.format(AjudaFilesLoader.getBundleProperty("funcao.embutida.pattern"), this.getNome(), this.getDescricao().replaceAll("\\s*</?(html|head|body)>\\s*", ""), this.getTipoRetorno(), this.getParametrosAssinatura(), this.getParametrosDescricao(), this.getDescricaoRetorno());
   }

   private String getParametrosAssinatura() {
      StringBuilder sb = new StringBuilder();
      boolean first = true;

      for(Iterator i$ = this.getParametros().iterator(); i$.hasNext(); first = false) {
         Parametro p = (Parametro)i$.next();
         if (!first) {
            sb.append(", ");
         }

         sb.append(MessageFormat.format(AjudaFilesLoader.getBundleProperty("funcao.embutida.parametro.assinatura.pattern"), p.getNome(), p.getTipo()));
      }

      return sb.toString();
   }

   private String getParametrosDescricao() {
      StringBuilder sb = new StringBuilder();
      Iterator i$ = this.getParametros().iterator();

      while(i$.hasNext()) {
         Parametro p = (Parametro)i$.next();
         sb.append(MessageFormat.format(AjudaFilesLoader.getBundleProperty("funcao.embutida.parametro.pattern"), p.getNome(), p.getTipo(), p.getDescricao()));
      }

      return sb.toString();
   }
}
