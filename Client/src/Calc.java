
import java.awt.Frame;
import java.awt.Toolkit;

public class Calc extends Frame {
    /**
     * Loads frame icon
     */
    private static final long serialVersionUID = 1L;

    public Calc() {
	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("bin/images/ghostBg2.png")));
    }

}