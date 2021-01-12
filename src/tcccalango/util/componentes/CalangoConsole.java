package tcccalango.util.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Console Swing para escrita (output) e leitura (input) de dados do usuário
 * 
 * @author GAFS
 */
public class CalangoConsole extends JTextPane implements KeyListener, AdjustmentListener {
	private static final long serialVersionUID = 5307692770882455949L;
        
        private final StringReadingController STRING = new StringReadingController();
        private final CharacterReadingController CHARACTER = new CharacterReadingController();

	// Buffer de inputs do usuário
	private ReadingController controller;

	// AttributeSet's para output e input
	private MutableAttributeSet output;
	private MutableAttributeSet input;
	private MutableAttributeSet error;
        
        private int lineLimit;
        
	public CalangoConsole() {
            addKeyListener(this);
            
            output = new SimpleAttributeSet();
            StyleConstants.setFontFamily(output, Font.MONOSPACED);
            StyleConstants.setFontSize(output, 14);
            setOutputTextColor(Color.black);
            
            input = new SimpleAttributeSet();
            StyleConstants.setFontFamily(input, Font.MONOSPACED);
            StyleConstants.setFontSize(input, 14);
            setInputTextColor(new Color(0, 150, 255));
            
            error = new SimpleAttributeSet();
            StyleConstants.setFontFamily(error, Font.MONOSPACED);
            StyleConstants.setFontSize(error, 14);
            setErrorTextColor(Color.red);
	}
        
        public CalangoConsole(int lineLimit){
            this();
            setLineLimit(lineLimit);
        }
        
        public void setLineLimit(int lineLimit){
            this.lineLimit = lineLimit;
        }
        
        public int getLineLimit(){
            return lineLimit;
        }

	/**
	 * Adiciona o caractere ao fim da console como texto de saída (output)
	 * @param text
	 */
	public void append(char ch){
		append(ch, output);
	}

	private void append(char ch, AttributeSet aset){
		append(new Character(ch).toString(), aset);
	}

	/**
	 * Adiciona o texto ao fim da console como texto de saída (output)
	 * @param text
	 */
	public void append(String text){
		append(text, output);
	}
	/**
	 * Adiciona o texto ao fim da console como texto de saída (output)
	 * @param text
	 */
	public void appendError(String text){
		append(text, error);
	}

	/**
	 * limpa a console
	 */
	public void clear() {
            try {
                getDocument().remove(0, getDocument().getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
	}
        
	private void append(String text, AttributeSet aset){
            try {
                getDocument().insertString(getDocument().getLength(), text, aset);
            } catch (BadLocationException ex) {
                Logger.getLogger(CalangoConsole.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	@Override
	@Deprecated
	public void setText(String t) {
		super.setText(t);
	}

	/**
	 * @return o próximo caracter digitado pelo usuário (independente de qual seja)
	 */
        public Character nextCharacter(){
            return read(CHARACTER).getCharacter();
        }
        
        /**
         * @return a próxima linha digitada pelo usuário (o \n delimita o fim da linha)
         */
	public String nextLine(){
            return read(STRING).getString();
	}

	@Override
	public void keyPressed(KeyEvent event) {
            if (isReading()){
                if (event.getKeyChar()==KeyEvent.VK_BACK_SPACE){
                    if (!controller.canErase() || event.isControlDown()){
                        event.consume();
                    }
                }else if (event.getKeyChar()==KeyEvent.VK_ENTER){
                    event.consume();
                }
            }else if (!event.isControlDown() && 
                    !event.isAltDown() && 
                    !event.isMetaDown() && 
                    !event.isAltGraphDown() &&
                    event.getKeyChar()!=KeyEvent.VK_ESCAPE){
                event.consume();
            }
        }
        
        private <T extends ReadingController> T read(T controller){
            this.controller = controller;
            
            this.controller.reset();
            setCaretPosition(getDocument().getLength());
            
            synchronized(this){
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            
            setCaretPosition(getDocument().getLength());
            this.controller = null;
            
            return controller;
        }

        private boolean isReading(){
            return controller!=null;
        }
        
	@Override
	public void keyReleased(KeyEvent event) {}

	@Override
	public void keyTyped(KeyEvent event) {
            
            if (!event.isActionKey() && 
                    event.getKeyChar()!=KeyEvent.VK_BACK_SPACE &&
                    event.getKeyChar()!=KeyEvent.VK_ESCAPE &&
                    !event.isControlDown() && 
                    !event.isAltDown() && 
                    !event.isMetaDown() && 
                    !event.isAltGraphDown()){
                
                if (!isReading()){
                    if (!event.isControlDown() && !event.isAltDown()){
                        event.consume();
                    }
                    return;
                }
                
                event.consume();
                
                synchronized(this){
                    char read = event.getKeyChar();
                    
                    append(read, input);
                    
                    if (controller.finish(read)){
                        try {
                            controller.read(getDocument().getText(controller.getBegin(), getDocument().getLength()));
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                        notify();
                    }
                }
                
                
            }else if (event.getKeyChar()==KeyEvent.VK_BACK_SPACE && event.isControlDown()){
                event.consume();
            }
        }

	/** 
	 * define a cor do texto de output
	 * @param color
	 */
	public void setOutputTextColor(Color color){
		StyleConstants.setForeground(output, color);
	}

	/**
	 * define a cor do texto de input
	 * @param color
	 */
	public void setInputTextColor(Color color){
		StyleConstants.setForeground(input, color);
	}
	/**
	 * define a cor do texto de input
	 * @param color
	 */
	public void setErrorTextColor(Color color){
		StyleConstants.setForeground(error, color);
	}

	/**
	 * @return o objeto AttributeSet de output
	 */
	public MutableAttributeSet getOutput() {
		return output;
	}

	/**
	 * @return o objeto AttributeSet de erros
	 */
	public MutableAttributeSet getError() {
		return error;
	}

	/**
	 * @return o objeto AttributeSet de input
	 */
	public MutableAttributeSet getInput() {
		return input;
	}

        private int lastMaximum = 0;
        
        public void adjustmentValueChanged(AdjustmentEvent e) {
            if (!e.getValueIsAdjusting()){
                if (e.getValue()+e.getAdjustable().getVisibleAmount()>=lastMaximum){
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
                if (lastMaximum != e.getAdjustable().getMaximum()){
                    lastMaximum = e.getAdjustable().getMaximum();
                }
            }
        }    
    

    class CharacterReadingController extends ReadingController {
        private Character ch;

        public void read(String value) {
            ch = value.charAt(0);
        }
        
        public boolean finish(char ch){
            return true;
        }

        public Character getCharacter(){
            return this.ch;
        }

    }

    class StringReadingController extends ReadingController {
        private String reading;

        public void read(String value) {
            reading = value.substring(0, value.length()-1);
        }
        
        public boolean finish(char ch){
            return ch=='\n';
        }

        public String getString(){
            return this.reading.toString();
        }
    }

    abstract class ReadingController {
        private int begin;

        public final void reset(){
            begin = getDocument().getLength();
        }

        /**
         * @return Se a console deve permitir que o usuário apague o conteúdo
         */
        public final boolean canErase(){
            return getDocument().getLength() > begin;
        }
        /**
         * @param value O valor lido na console (Com o caracter terminador)
         */
        public abstract void read(String value);
        /**
         * Verifica, através do caracter lido, se a leitura deve ser finalizada
         */
        public abstract boolean finish(char ch);

        public int getBegin() {
            return begin;
        }
    }
}
