package tcccalango.view.ajuda;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Node;

public class FuncionalidadeDomHandler implements DomHandler<Funcionalidade, DOMResult> {
   public DOMResult createUnmarshaller(ValidationEventHandler errorHandler) {
      return new DOMResult();
   }

   public Funcionalidade getElement(DOMResult rt) {
      Node node = rt.getNode().getFirstChild();
      if (node.getNodeType() == 1) {
         if (node.getNodeName().equals("include")) {
            IncludeFuncionalidade include = (IncludeFuncionalidade)JAXBAjudaUtil.load(node);
            include.setFuncionalidade((Funcionalidade)JAXBAjudaUtil.load(include.getSrc()));
            return include;
         } else {
            return (Funcionalidade)JAXBAjudaUtil.load(node);
         }
      } else {
         return new FakeFuncionalidade();
      }
   }

   public Source marshal(Funcionalidade n, ValidationEventHandler errorHandler) {
      return null;
   }
}
