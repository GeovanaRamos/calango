package tcccalango.util.settings;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

public class ElementoTexto implements Serializable {
   private Color cor;
   private Font font;
   private boolean syleUnderline;

   public ElementoTexto(Color cor, Font font, boolean syleUnderline) {
      this.cor = cor;
      this.font = font;
      this.syleUnderline = syleUnderline;
   }

   public ElementoTexto() {
   }

   public boolean isSyleUnderline() {
      return this.syleUnderline;
   }

   public void setSyleUnderline(boolean syleUnderline) {
      this.syleUnderline = syleUnderline;
   }

   public boolean isStyleItalic() {
      return this.getFont().isItalic() || this.isStyleBoldItalic();
   }

   public boolean isStyleBold() {
      return this.getFont().isBold() || this.isStyleBoldItalic();
   }

   public boolean isStyleBoldItalic() {
      return this.getFont().getStyle() == 3;
   }

   public Color getCor() {
      return this.cor;
   }

   public void setCor(Color cor) {
      this.cor = cor;
   }

   public Font getFont() {
      return this.font;
   }

   public void setFont(Font font) {
      this.font = font;
   }
}
