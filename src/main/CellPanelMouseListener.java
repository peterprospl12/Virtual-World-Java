package main;

import Organisms.OrganismFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.atomic.AtomicInteger;

public class CellPanelMouseListener implements MouseListener {

    private JPanel cellPanel;
    private World world;
    private int[] numberOfOrganism;
    private int x;
    private int y;

    public CellPanelMouseListener(JPanel cellPanel, int x, int y, int[] numberOfOrganism, World world) {
        this.cellPanel = cellPanel;
        this.numberOfOrganism = numberOfOrganism;
        this.world = world;
        this.x = x;
        this.y = y;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(world.getOrganism(x,y) == null) {
            OrganismFactory organismFactory = new OrganismFactory();
            organismFactory.organismsList(x, y, numberOfOrganism[0], world);

            ImageIcon icon = new ImageIcon(world.getOrganism(x, y).getImagePath());
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(cellPanel.getWidth(), cellPanel.getHeight(), Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            JLabel label = new JLabel(icon);

            cellPanel.removeAll();
            cellPanel.add(label);
            cellPanel.revalidate();
            cellPanel.repaint();

        }
        else {
            world.removeOrganism(world.getOrganism(x,y));
            cellPanel.removeAll();
            cellPanel.setBackground(Color.WHITE);
            cellPanel.revalidate();
            cellPanel.repaint();
        }
        world.drawWorld();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
