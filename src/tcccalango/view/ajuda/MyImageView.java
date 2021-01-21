package tcccalango.view.ajuda;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.Position.Bias;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;

public class MyImageView extends View implements ImageObserver, MouseListener, MouseMotionListener {
   public static final String TOP = "top";
   public static final String TEXTTOP = "texttop";
   public static final String MIDDLE = "middle";
   public static final String ABSMIDDLE = "absmiddle";
   public static final String CENTER = "center";
   public static final String BOTTOM = "bottom";
   private static boolean sIsInc = true;
   private static int sIncRate = 100;
   private AttributeSet attr;
   private Element fElement;
   private Image fImage;
   private int fHeight;
   private int fWidth;
   private Container fContainer;
   private Rectangle fBounds;
   private Component fComponent;
   private Point fGrowBase;
   private boolean fGrowProportionally;
   private boolean loading;
   private static Icon sPendingImageIcon;
   private static Icon sMissingImageIcon;
   private static final String PENDING_IMAGE_SRC = "icons/image-delayed.gif";
   private static final String MISSING_IMAGE_SRC = "icons/image-failed.gif";
   private static final boolean DEBUG = false;
   static final String IMAGE_CACHE_PROPERTY = "imageCache";
   private static final int DEFAULT_WIDTH = 32;
   private static final int DEFAULT_HEIGHT = 32;
   private static final int DEFAULT_BORDER = 2;

   public MyImageView(Element elem) {
      super(elem);
      this.initialize(elem);
      StyleSheet sheet = this.getStyleSheet();
      this.attr = sheet.getViewAttributes(this);
   }

   private void initialize(Element elem) {
      synchronized(this) {
         this.loading = true;
         this.fWidth = this.fHeight = 0;
      }

      int width = 0;
      int height = 0;
      boolean customWidth = false;
      boolean customHeight = false;
      boolean var20 = false;

      try {
         var20 = true;
         this.fElement = elem;
         AttributeSet attr = elem.getAttributes();
         String src = this.getSource();
         InputStream stream = this.getSourceStream();
         if (stream != null) {
            Dictionary cache = (Dictionary)this.getDocument().getProperty("imageCache");
            if (cache != null) {
               this.fImage = (Image)cache.get(src);
            } else {
               try {
                  this.fImage = ImageIO.read(stream);
               } catch (IOException var21) {
                  Logger.getLogger(MyImageView.class.getName()).log(Level.SEVERE, (String)null, var21);
               }
            }
         }

         height = this.getIntAttr(Attribute.HEIGHT, -1);
         customHeight = height > 0;
         if (!customHeight && this.fImage != null) {
            height = this.fImage.getHeight(this);
         }

         if (height <= 0) {
            height = 32;
         }

         width = this.getIntAttr(Attribute.WIDTH, -1);
         customWidth = width > 0;
         if (!customWidth && this.fImage != null) {
            width = this.fImage.getWidth(this);
         }

         if (width <= 0) {
            width = 32;
         }

         if (this.fImage != null) {
            if (customWidth && customHeight) {
               Toolkit.getDefaultToolkit().prepareImage(this.fImage, height, width, this);
               var20 = false;
            } else {
               Toolkit.getDefaultToolkit().prepareImage(this.fImage, -1, -1, this);
               var20 = false;
            }
         } else {
            var20 = false;
         }
      } finally {
         if (var20) {
            synchronized(this) {
               this.loading = false;
               if (customWidth || this.fWidth == 0) {
                  this.fWidth = width;
               }

               if (customHeight || this.fHeight == 0) {
                  this.fHeight = height;
               }

            }
         }
      }

      synchronized(this) {
         this.loading = false;
         if (customWidth || this.fWidth == 0) {
            this.fWidth = width;
         }

         if (customHeight || this.fHeight == 0) {
            this.fHeight = height;
         }

      }
   }

   private String processSrcPath(String src) {
      String val = src;
      File imageFile = new File(src);
      if (imageFile.isAbsolute()) {
         return src;
      } else {
         String imagePath = System.getProperty("system.image.path.key");
         if (imagePath != null) {
            val = (new File(imagePath, imageFile.getPath())).toString();
         }

         return val;
      }
   }

   private void waitForImage() throws InterruptedException {
      int w = this.fImage.getWidth(this);
      int h = this.fImage.getHeight(this);

      while(true) {
         int flags = Toolkit.getDefaultToolkit().checkImage(this.fImage, w, h, this);
         if ((flags & 64) != 0 || (flags & 128) != 0) {
            throw new InterruptedException();
         }

         if ((flags & 48) != 0) {
            return;
         }

         Thread.sleep(10L);
      }
   }

   public AttributeSet getAttributes() {
      return this.attr;
   }

   boolean isLink() {
      AttributeSet anchorAttr = (AttributeSet)this.fElement.getAttributes().getAttribute(Tag.A);
      return anchorAttr != null ? anchorAttr.isDefined(Attribute.HREF) : false;
   }

   int getBorder() {
      return this.getIntAttr(Attribute.BORDER, this.isLink() ? 2 : 0);
   }

   int getSpace(int axis) {
      return this.getIntAttr(axis == 0 ? Attribute.HSPACE : Attribute.VSPACE, 0);
   }

   Color getBorderColor() {
      StyledDocument doc = (StyledDocument)this.getDocument();
      return doc.getForeground(this.getAttributes());
   }

   float getVerticalAlignment() {
      String align = (String)this.fElement.getAttributes().getAttribute(Attribute.ALIGN);
      if (align != null) {
         align = align.toLowerCase();
         if (align.equals("top") || align.equals("texttop")) {
            return 0.0F;
         }

         if (align.equals("center") || align.equals("middle") || align.equals("absmiddle")) {
            return 0.5F;
         }
      }

      return 1.0F;
   }

   boolean hasPixels(ImageObserver obs) {
      return this.fImage != null && this.fImage.getHeight(obs) > 0 && this.fImage.getWidth(obs) > 0;
   }

   private String getSource() {
      return (String)this.fElement.getAttributes().getAttribute(Attribute.SRC);
   }

   private InputStream getSourceStream() {
      try {
         return this.getClass().getResourceAsStream(this.getSource());
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   private int getIntAttr(Attribute name, int deflt) {
      AttributeSet attr = this.fElement.getAttributes();
      if (attr.isDefined(name)) {
         String val = (String)attr.getAttribute(name);
         int i;
         if (val == null) {
            i = deflt;
         } else {
            try {
               i = Math.max(0, Integer.parseInt(val));
            } catch (NumberFormatException var7) {
               i = deflt;
            }
         }

         return i;
      } else {
         return deflt;
      }
   }

   public void setParent(View parent) {
      super.setParent(parent);
      this.fContainer = parent != null ? this.getContainer() : null;
      if (parent == null && this.fComponent != null) {
         this.fComponent.getParent().remove(this.fComponent);
         this.fComponent = null;
      }

   }

   public void changedUpdate(DocumentEvent e, Shape a, ViewFactory f) {
      super.changedUpdate(e, a, f);
      float align = this.getVerticalAlignment();
      int height = this.fHeight;
      int width = this.fWidth;
      this.initialize(this.getElement());
      boolean hChanged = this.fHeight != height;
      boolean wChanged = this.fWidth != width;
      if (hChanged || wChanged || this.getVerticalAlignment() != align) {
         this.getParent().preferenceChanged(this, hChanged, wChanged);
      }

   }

   public void paint(Graphics g, Shape a) {
      Color oldColor = g.getColor();
      this.fBounds = a.getBounds();
      int border = this.getBorder();
      int x = this.fBounds.x + border + this.getSpace(0);
      int y = this.fBounds.y + border + this.getSpace(1);
      int width = this.fWidth;
      int height = this.fHeight;
      int sel = this.getSelectionState();
      if (!this.hasPixels(this)) {
         g.setColor(Color.lightGray);
         g.drawRect(x, y, width - 1, height - 1);
         g.setColor(oldColor);
         this.loadIcons();
         Icon icon = this.fImage == null ? sMissingImageIcon : sPendingImageIcon;
         if (icon != null) {
            icon.paintIcon(this.getContainer(), g, x, y);
         }
      }

      if (this.fImage != null) {
         g.drawImage(this.fImage, x, y, width, height, this);
      }

      Color bc = this.getBorderColor();
      int i;
      if (sel == 2) {
         i = 2 - border;
         if (i > 0) {
            x += i;
            y += i;
            width -= i << 1;
            height -= i << 1;
            border = 2;
         }

         bc = null;
         g.setColor(Color.black);
         g.fillRect(x + width - 5, y + height - 5, 5, 5);
      }

      if (border > 0) {
         if (bc != null) {
            g.setColor(bc);
         }

         for(i = 1; i <= border; ++i) {
            g.drawRect(x - i, y - i, width - 1 + i + i, height - 1 + i + i);
         }

         g.setColor(oldColor);
      }

   }

   protected void repaint(long delay) {
      if (this.fContainer != null && this.fBounds != null) {
         this.fContainer.repaint(delay, this.fBounds.x, this.fBounds.y, this.fBounds.width, this.fBounds.height);
      }

   }

   protected int getSelectionState() {
      int p0 = this.fElement.getStartOffset();
      int p1 = this.fElement.getEndOffset();
      if (this.fContainer instanceof JTextComponent) {
         JTextComponent textComp = (JTextComponent)this.fContainer;
         int start = textComp.getSelectionStart();
         int end = textComp.getSelectionEnd();
         if (start <= p0 && end >= p1) {
            if (start == p0 && end == p1 && this.isEditable()) {
               return 2;
            }

            return 1;
         }
      }

      return 0;
   }

   protected boolean isEditable() {
      return this.fContainer instanceof JEditorPane && ((JEditorPane)this.fContainer).isEditable();
   }

   protected Color getHighlightColor() {
      JTextComponent textComp = (JTextComponent)this.fContainer;
      return textComp.getSelectionColor();
   }

   public boolean imageUpdate(Image img, int flags, int x, int y, int width, int height) {
      if (this.fImage != null && this.fImage == img) {
         if ((flags & 192) != 0) {
            this.fImage = null;
            this.repaint(0L);
            return false;
         } else {
            short changed = 0;
            if ((flags & 2) != 0 && !this.getElement().getAttributes().isDefined(Attribute.HEIGHT)) {
               changed = (short)(changed | 1);
            }

            if ((flags & 1) != 0 && !this.getElement().getAttributes().isDefined(Attribute.WIDTH)) {
               changed = (short)(changed | 2);
            }

            synchronized(this) {
               if ((changed & 1) == 1) {
                  this.fWidth = width;
               }

               if ((changed & 2) == 2) {
                  this.fHeight = height;
               }

               if (this.loading) {
                  return true;
               }
            }

            if (changed != 0) {
               Document doc = this.getDocument();

               try {
                  if (doc instanceof AbstractDocument) {
                     ((AbstractDocument)doc).readLock();
                  }

                  this.preferenceChanged(this, true, true);
               } finally {
                  if (doc instanceof AbstractDocument) {
                     ((AbstractDocument)doc).readUnlock();
                  }

               }

               return true;
            } else {
               if ((flags & 48) != 0) {
                  this.repaint(0L);
               } else if ((flags & 8) != 0 && sIsInc) {
                  this.repaint((long)sIncRate);
               }

               return (flags & 32) == 0;
            }
         }
      } else {
         return false;
      }
   }

   public float getPreferredSpan(int axis) {
      int extra = 2 * (this.getBorder() + this.getSpace(axis));
      switch(axis) {
      case 0:
         return (float)(this.fWidth + extra);
      case 1:
         return (float)(this.fHeight + extra);
      default:
         throw new IllegalArgumentException("Invalid axis: " + axis);
      }
   }

   public float getAlignment(int axis) {
      switch(axis) {
      case 1:
         return this.getVerticalAlignment();
      default:
         return super.getAlignment(axis);
      }
   }

   public Shape modelToView(int pos, Shape a, Bias b) throws BadLocationException {
      int p0 = this.getStartOffset();
      int p1 = this.getEndOffset();
      if (pos >= p0 && pos <= p1) {
         Rectangle r = a.getBounds();
         if (pos == p1) {
            r.x += r.width;
         }

         r.width = 0;
         return r;
      } else {
         return null;
      }
   }

   public int viewToModel(float x, float y, Shape a, Bias[] bias) {
      Rectangle alloc = (Rectangle)a;
      if (x < (float)(alloc.x + alloc.width)) {
         bias[0] = Bias.Forward;
         return this.getStartOffset();
      } else {
         bias[0] = Bias.Backward;
         return this.getEndOffset();
      }
   }

   public void setSize(float width, float height) {
   }

   protected void resize(int width, int height) {
      if (width != this.fWidth || height != this.fHeight) {
         this.fWidth = width;
         this.fHeight = height;
         MutableAttributeSet attr = new SimpleAttributeSet();
         attr.addAttribute(Attribute.WIDTH, Integer.toString(width));
         attr.addAttribute(Attribute.HEIGHT, Integer.toString(height));
         ((StyledDocument)this.getDocument()).setCharacterAttributes(this.fElement.getStartOffset(), this.fElement.getEndOffset(), attr, false);
      }
   }

   public void mousePressed(MouseEvent e) {
      Dimension size = this.fComponent.getSize();
      if (e.getX() >= size.width - 7 && e.getY() >= size.height - 7 && this.getSelectionState() == 2) {
         Point loc = this.fComponent.getLocationOnScreen();
         this.fGrowBase = new Point(loc.x + e.getX() - this.fWidth, loc.y + e.getY() - this.fHeight);
         this.fGrowProportionally = e.isShiftDown();
      } else {
         this.fGrowBase = null;
         JTextComponent comp = (JTextComponent)this.fContainer;
         int start = this.fElement.getStartOffset();
         int end = this.fElement.getEndOffset();
         int mark = comp.getCaret().getMark();
         int dot = comp.getCaret().getDot();
         if (e.isShiftDown()) {
            if (mark <= start) {
               comp.moveCaretPosition(end);
            } else {
               comp.moveCaretPosition(start);
            }
         } else {
            if (mark != start) {
               comp.setCaretPosition(start);
            }

            if (dot != end) {
               comp.moveCaretPosition(end);
            }
         }
      }

   }

   public void mouseDragged(MouseEvent e) {
      if (this.fGrowBase != null) {
         Point loc = this.fComponent.getLocationOnScreen();
         int width = Math.max(2, loc.x + e.getX() - this.fGrowBase.x);
         int height = Math.max(2, loc.y + e.getY() - this.fGrowBase.y);
         if (e.isShiftDown() && this.fImage != null) {
            float imgWidth = (float)this.fImage.getWidth(this);
            float imgHeight = (float)this.fImage.getHeight(this);
            if (imgWidth > 0.0F && imgHeight > 0.0F) {
               float prop = imgHeight / imgWidth;
               float pwidth = (float)height / prop;
               float pheight = (float)width * prop;
               if (pwidth > (float)width) {
                  width = (int)pwidth;
               } else {
                  height = (int)pheight;
               }
            }
         }

         this.resize(width, height);
      }

   }

   public void mouseReleased(MouseEvent e) {
      this.fGrowBase = null;
   }

   public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
      }

   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseMoved(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   private Icon makeIcon(String gifFile) throws IOException {
      InputStream resource = MyImageView.class.getResourceAsStream(gifFile);
      if (resource == null) {
         return null;
      } else {
         BufferedInputStream in = new BufferedInputStream(resource);
         ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
         byte[] buffer = new byte[1024];

         int n;
         while((n = in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
         }

         in.close();
         out.flush();
         buffer = out.toByteArray();
         if (buffer.length == 0) {
            System.err.println("warning: " + gifFile + " is zero-length");
            return null;
         } else {
            return new ImageIcon(buffer);
         }
      }
   }

   private void loadIcons() {
      try {
         if (sPendingImageIcon == null) {
            sPendingImageIcon = this.makeIcon("icons/image-delayed.gif");
         }

         if (sMissingImageIcon == null) {
            sMissingImageIcon = this.makeIcon("icons/image-failed.gif");
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   protected StyleSheet getStyleSheet() {
      HTMLDocument doc = (HTMLDocument)this.getDocument();
      return doc.getStyleSheet();
   }
}
