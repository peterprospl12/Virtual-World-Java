package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUIKeyListener implements KeyListener {
    private int keyCode;
    private JFrame frame;

    public GUIKeyListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public int getKeyCode() {
        return keyCode;
    }
}
