package main;

import Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class TurnPerfomer extends SwingWorker<Void, Void> {
    private final World currWorld;
    private final GUI gui;

    private final JPanel[][] cellPanels;
    private final HashMap <String, ImageIcon> icons = new HashMap<>();
    private final JTextPane textArea;

    public TurnPerfomer(World currWorld, GUI gui, JPanel[][] cellPanels, JTextPane textArea) {
        this.currWorld = currWorld;
        this.gui = gui;
        this.cellPanels = cellPanels;
        this.textArea = textArea;
    }
    @Override
    protected Void doInBackground() throws Exception {
        process(null);
        while (currWorld.isHumanAlive()) {
            currWorld.performTurn();
            publish();

            if(!currWorld.isHumanAlive()) {
                JOptionPane.showMessageDialog(null, "You lost!");
                currWorld.getGui().getFrame().dispose();
            }

            Thread.sleep(100); // Opóźnienie 100 milisekund
        }
        return null;
    }

    @Override
    protected void process(List<Void> chunks) {

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
        if (icon == null) {
            icon = new ImageIcon(organism.getImagePath());
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(gui.getCellSize(), gui.getCellSize(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            icons.put(organism.getName(), icon);
        }
        return icon;
    }

}

