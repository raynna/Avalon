package com.rs.game;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class isKeyPressed {
    private static volatile boolean shiftPressed = false;
    public static boolean isShiftPressed() {
        synchronized (isKeyPressed.class) {
            return shiftPressed;
        }
    }

    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (isKeyPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                    	System.out.print("Key:"  + ke.getKeyCode());
                        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
                        	shiftPressed = true;
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                    	System.out.print("Key:"  + ke.getKeyCode());
                        if (ke.getKeyCode() != KeyEvent.VK_SHIFT) {
                        	shiftPressed = false;
                        }
                        break;
                    }
                    return false;
                }
            }
        });
    }
}
