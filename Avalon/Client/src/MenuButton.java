
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;

public class MenuButton extends JMenu {

private boolean startedIn = false;
private ActionListener action;

public MenuButton(String title) {
    super(title);
    removeListeners(this);
    this.addMouseListener(new MenuButtonListener());

}

public MenuButton(ImageIcon icon) {
    super();
    removeListeners(this);
    this.addMouseListener(new MenuButtonListener());
    this.setIcon(icon);
}

public void addActionListener(ActionListener a) {
    action = a;
}
    //we need to remove all the listeners already associated with a JMenu. If we do
//not do this, then it will not behave as expected because some mouseclicks are eaten 
//by these listeners. There is no easy way to do that, the following method is a 
//workaroundprovided in the java bug database. 
static private void removeListeners(Component comp) {
    Method[] methods = comp.getClass().getMethods();
    for (int i = 0; i < methods.length; i++) {
        Method method = methods[i];
        String name = method.getName();
        if (name.startsWith("remove") && name.endsWith("Listener")) {

            Class[] params = method.getParameterTypes();
            if (params.length == 1) {
                EventListener[] listeners = null;
                try {
                    listeners = comp.getListeners(params[0]);
                } catch (Exception e) {
                    // It is possible that someone could create a listener
                    // that doesn't extend from EventListener. If so, ignore
                    // it
                    System.out.println("Listener " + params[0]
                            + " does not extend EventListener");
                    continue;
                }
                for (int j = 0; j < listeners.length; j++) {
                    try {
                        method.invoke(comp, new Object[] { listeners[j] });
                        // System.out.println("removed Listener " + name +
                        // " for comp " + comp + "\n");
                    } catch (Exception e) {
                        System.out
                                .println("Cannot invoke removeListener method "
                                        + e);
                        // Continue on. The reason for removing all
                        // listeners is to
                        // make sure that we don't have a listener holding
                        // on to something
                        // which will keep it from being garbage collected.
                        // We want to
                        // continue freeing listeners to make sure we can
                        // free as much
                        // memory has possible
                    }
                }
            } else {
                // The only Listener method that I know of that has more
                // than
                // one argument is removePropertyChangeListener. If it is
                // something other than that, flag it and move on.
                if (!name.equals("removePropertyChangeListener"))
                    System.out.println("    Wrong number of Args " + name);
            }
        }
    }
}

public class MenuButtonListener extends MouseAdapter {

    boolean within = false;
    boolean pressed = false;


    public void mousePressed(MouseEvent e) {
        MenuButton.this.setSelected(true);
        pressed = true;
        //System.out.println("pressed");
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("released");
        MenuButton.this.setSelected(false);
        if (action != null && within && pressed) {
            action.actionPerformed(new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, null));
            MenuButton.this.setSelected(false);
        }
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        within = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        within = false;
    }
}
}