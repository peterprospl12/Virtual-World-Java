package main;

import Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class TurnPerfomer extends SwingWorker<Void, Void> {
    private World currWorld;
    private GUI gui;
    private JPanel boardPanel;
    private JFrame frame;
    private JPanel[][] cellPanels;
    private HashMap <String, ImageIcon> icons = new HashMap<>();
    private JTextPane textArea;

    public TurnPerfomer(World currWorld, GUI gui, JPanel boardPanel, JFrame frame, JPanel[][] cellPanels, JTextPane textArea) {
        this.currWorld = currWorld;
        this.gui = gui;
        this.boardPanel = boardPanel;
        this.frame = frame;
        this.cellPanels = cellPanels;
        this.textArea = textArea;
    }
    @Override
    protected Void doInBackground() throws Exception {
        process(null);
        while (currWorld.isHumanAlive()) {
            currWorld.performTurn();
            publish();

            Thread.sleep(100); // Opóźnienie 100 milisekund
        }
        return null;
    }

    @Override
    protected void process(List<Void> chunks) {
        // Odświeżenie planszy

        for (int y = 0; y < currWorld.getBoardSizeY(); y++) {
            for (int x = 0; x < currWorld.getBoardSizeX(); x++) {
                JPanel cellPanel = cellPanels[y][x];

                if (currWorld.getOrganism(x, y) != null) {


                    JLabel label = new JLabel(getIcon(currWorld.getOrganism(x, y)));
                    cellPanel.removeAll();
                    cellPanel.add(label);

                } else {
                    cellPanel.removeAll();
                    cellPanel.setBackground(Color.WHITE);

                }
                cellPanel.revalidate();
                cellPanel.repaint();
            }
        }
        textArea.setText(currWorld.getInfoStream().toString());
        currWorld.clearInfoStream();

    }

    public ImageIcon getIcon(Organism organism) {
        ImageIcon icon = icons.get(organism.getName());
        if(icon != null) {
            return icon;
        }
        else {
            icon = new ImageIcon(organism.getImagePath());
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(gui.getCellSize(), gui.getCellSize(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            icons.put(organism.getName(), icon);
            return icon;
        }
    }

}

