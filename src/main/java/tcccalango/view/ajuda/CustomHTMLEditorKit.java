package tcccalango.view.ajuda;

import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.HTMLFactory;

public class CustomHTMLEditorKit extends HTMLEditorKit {
   public ViewFactory getViewFactory() {
      return new HTMLFactoryX();
   }

   public static class HTMLFactoryX extends HTMLFactory implements ViewFactory {
      public View create(Element elem) {
         Object o = elem.getAttributes().getAttribute(StyleConstants.NameAttribute);
         if (o instanceof Tag) {
            Tag kind = (Tag)o;
            if (kind == Tag.IMG) {
               return new MyImageView(elem);
            }
         }

         return super.create(elem);
      }
   }
}
