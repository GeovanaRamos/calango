package tcccalango.util.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.border.AbstractBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class CalangoConsole extends JTextPane implements KeyListener, AdjustmentListener {
   private static final long serialVersionUID = 5307692770882455949L;
   private final StringReadingController STRING;
   private final CharacterReadingController CHARACTER;
   private ReadingController controller;
   private MutableAttributeSet output;
   private MutableAttributeSet input;
   private MutableAttributeSet error;
   private int lineLimit;
   private int lastMaximum;

   public CalangoConsole() {
      this.STRING = new StringReadingController();
      this.CHARACTER = new CharacterReadingController();
      this.lastMaximum = 0;
      this.addKeyListener(this);
      this.output = new SimpleAttributeSet();
      StyleConstants.setFontFamily(this.output, "Monospaced");
      StyleConstants.setFontSize(this.output, 14);
      this.setOutputTextColor(Color.black);
      this.input = new SimpleAttributeSet();
      StyleConstants.setFontFamily(this.input, "Monospaced");
      StyleConstants.setFontSize(this.input, 14);
      this.setInputTextColor(new Color(0, 150, 255));
      this.error = new SimpleAttributeSet();
      StyleConstants.setFontFamily(this.error, "Monospaced");
      StyleConstants.setFontSize(this.error, 14);
      this.setErrorTextColor(Color.red);
      this.setBorder(new AbstractBorder() {
         public boolean isBorderOpaque() {
            return true;
         }

         public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 1, 0);
         }

         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
         }
      });
   }

   public CalangoConsole(int lineLimit) {
      this();
      this.setLineLimit(lineLimit);
   }

   public void setLineLimit(int lineLimit) {
      this.lineLimit = lineLimit;
   }

   public int getLineLimit() {
      return this.lineLimit;
   }

   public void append(char ch) {
      this.append(ch, this.output);
   }

   private void append(char ch, AttributeSet aset) {
      this.append((new Character(ch)).toString(), aset);
   }

   public void append(String text) {
      this.append(text, this.output);
   }

   public void appendError(String text) {
      this.append(text, this.error);
   }

   public void clear() {
      try {
         this.getDocument().remove(0, this.getDocument().getLength());
      } catch (BadLocationException var2) {
         var2.printStackTrace();
      }

   }

   private void append(String text, AttributeSet aset) {
      try {
         this.getDocument().insertString(this.getDocument().getLength(), text, aset);
      } catch (BadLocationException var4) {
         Logger.getLogger(CalangoConsole.class.getName()).log(Level.SEVERE, (String)null, var4);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setText(String t) {
      super.setText(t);
   }

   public Character nextCharacter() {
      try {
         return ((CharacterReadingController)this.read(this.CHARACTER)).getCharacter();
      } catch (InterruptedException var2) {
         return null;
      }
   }

   public String nextLine() {
      try {
         return ((StringReadingController)this.read(this.STRING)).getString();
      } catch (InterruptedException var2) {
         return null;
      }
   }

   public void keyPressed(KeyEvent event) {
      if (this.isReading()) {
         if (event.getKeyChar() == '\b') {
            if (!this.controller.canErase() || event.isControlDown()) {
               event.consume();
            }
         } else if (event.getKeyChar() == '\n') {
            event.consume();
         }
      } else if (!event.isControlDown() && !event.isAltDown() && !event.isMetaDown() && !event.isAltGraphDown() && !event.isActionKey() && event.getKeyChar() != 27) {
         event.consume();
      }

   }

   private <T extends ReadingController> T read(T controller) throws InterruptedException {
      this.controller = controller;
      this.controller.reset();
      this.setCaretPosition(this.getDocument().getLength());

      try {
         synchronized(this) {
            this.wait();
         }
      } finally {
         this.setCaretPosition(this.getDocument().getLength());
         this.controller = null;
         return controller;
      }
   }

   private boolean isReading() {
      return this.controller != null;
   }

   public void keyReleased(KeyEvent event) {
   }

   public void keyTyped(KeyEvent event) {
      if (!event.isActionKey() && event.getKeyChar() != '\b' && event.getKeyChar() != 27 && !event.isControlDown() && !event.isAltDown() && !event.isMetaDown() && !event.isAltGraphDown()) {
         if (!this.isReading()) {
            if (!event.isControlDown() && !event.isAltDown()) {
               event.consume();
            }

            return;
         }

         event.consume();
         synchronized(this) {
            char read = event.getKeyChar();
            this.append(read, this.input);
            if (this.controller.finish(read)) {
               try {
                  this.controller.read(this.getDocument().getText(this.controller.getBegin(), this.getDocument().getLength() - this.controller.getBegin()));
               } catch (BadLocationException var6) {
                  var6.printStackTrace();
               }

               this.notify();
            }
         }
      } else if (event.getKeyChar() == '\b' && event.isControlDown()) {
         event.consume();
      }

   }

   public void setOutputTextColor(Color color) {
      StyleConstants.setForeground(this.output, color);
      this.setCaretColor(color);
   }

   public void setInputTextColor(Color color) {
      StyleConstants.setForeground(this.input, color);
   }

   public void setErrorTextColor(Color color) {
      StyleConstants.setForeground(this.error, color);
   }

   public MutableAttributeSet getOutput() {
      return this.output;
   }

   public MutableAttributeSet getError() {
      return this.error;
   }

   public MutableAttributeSet getInput() {
      return this.input;
   }

   public void adjustmentValueChanged(AdjustmentEvent e) {
      if (!e.getValueIsAdjusting()) {
         if (e.getValue() + e.getAdjustable().getVisibleAmount() >= this.lastMaximum) {
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
         }

         if (this.lastMaximum != e.getAdjustable().getMaximum()) {
            this.lastMaximum = e.getAdjustable().getMaximum();
         }
      }

   }

   abstract class ReadingController {
      private int begin;

      public void reset() {
         this.begin = CalangoConsole.this.getDocument().getLength();
      }

      public final boolean canErase() {
         return CalangoConsole.this.getDocument().getLength() > this.begin;
      }

      public abstract void read(String var1);

      public abstract boolean finish(char var1);

      public int getBegin() {
         return this.begin;
      }
   }

   class StringReadingController extends ReadingController {
      private String reading;

      StringReadingController() {
         super();
      }

      public void reset() {
         super.reset();
         this.reading = null;
      }

      public void read(String value) {
         this.reading = value.substring(0, value.length() - 1);
      }

      public boolean finish(char ch) {
         return ch == '\n';
      }

      public String getString() {
         return this.reading == null ? null : String.valueOf(this.reading);
      }
   }

   class CharacterReadingController extends ReadingController {
      private Character ch;

      CharacterReadingController() {
         super();
      }

      public void reset() {
         super.reset();
         this.ch = null;
      }

      public void read(String value) {
         if (!value.isEmpty()) {
            this.ch = value.charAt(0);
         }

      }

      public boolean finish(char ch) {
         return true;
      }

      public Character getCharacter() {
         return this.ch;
      }
   }
}
