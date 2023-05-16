package main;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import Organisms.OrganismFactory;

import static Organisms.OrganismFactory.NUMBER_OF_ORGANISMS;


public class GUI {
    private final JFrame frame;

    private JPanel[][] cellPanels;
    private JPanel boardPanel;
    private final World world;
    private int cell_size;
    private TurnPerfomer turnPerfomer;
    public GUI(World world) {
        this.world = world;
        frame = new JFrame("Virtual World  Piotr Sulewski 192594");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        mainMenu();
    }



    private void mainMenu() {
        JLabel title = new JLabel("Virtual World");
        JButton newGame = new JButton("New game");
        JButton loadGame = new JButton("Load game");
        JButton exit = new JButton("Exit");

        // Ustawianie akcji dla przycisków

        newGame.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.newGameMenu();
        });

        loadGame.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            try {
                this.loadGameMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        exit.addActionListener(e -> frame.dispose());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        title.setFont(new Font("Minecraft", Font.BOLD, 200));
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title, gbc);

        gbc.gridy++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        newGame.setBackground(new Color(4, 91, 192));
        newGame.setForeground(new Color(255, 255, 255));
        newGame.setFont(new Font("Minecraft", Font.BOLD, 50));
        newGame.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(newGame, gbc);

        gbc.gridy++;

        loadGame.setBackground(new Color(62, 166, 196));
        loadGame.setForeground(new Color(255, 255, 255));
        loadGame.setFont(new Font("Minecraft", Font.BOLD, 50));
        loadGame.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(loadGame, gbc);

        gbc.gridy++;

        exit.setBackground(new Color(4, 129, 129));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(new Font("Minecraft", Font.BOLD, 50));
        exit.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(exit, gbc);

        frame.setContentPane(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }



    private void newGameMenu() {
        JButton start = new JButton("Start");
        JButton back = new JButton("Back");

        start.addActionListener(e -> {
            world.changeBoardSize();
            frame.getContentPane().removeAll();
            frame.repaint();
            this.fillBoard();
        });

        back.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.mainMenu();
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel labelX = new JLabel("Board width ");
        labelX.setFont(new Font("Arial", Font.BOLD, 25));
        JSlider sliderX = new JSlider(5, 50, world.getBoardSizeX());
        sliderX.setMajorTickSpacing(10);
        sliderX.setMinorTickSpacing(1);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        sliderX.setForeground(Color.BLUE);
        sliderX.setLabelTable(sliderX.createStandardLabels(1));
        sliderX.addChangeListener(e -> world.setBoardSizeX(sliderX.getValue()));
        JPanel sliderXPanel = new JPanel(new BorderLayout());
        sliderXPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sliderXPanel.add(labelX, BorderLayout.NORTH);
        sliderXPanel.add(sliderX, BorderLayout.CENTER);
        panel.add(sliderXPanel, gbc);

        gbc.gridy++;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        JLabel labelY = new JLabel("Board height ");
        labelY.setFont(new Font("Arial", Font.BOLD, 25));
        JSlider sliderY = new JSlider(5, 50, world.getBoardSizeY());
        sliderY.setMajorTickSpacing(10);
        sliderY.setMinorTickSpacing(1);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);
        sliderY.setForeground(Color.BLUE);
        sliderY.setLabelTable(sliderY.createStandardLabels(1));
        sliderY.addChangeListener(e -> world.setBoardSizeY(sliderY.getValue()));
        JPanel sliderYPanel = new JPanel(new BorderLayout());
        sliderYPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        sliderYPanel.add(labelY, BorderLayout.NORTH);
        sliderYPanel.add(sliderY, BorderLayout.CENTER);
        panel.add(sliderYPanel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        designButtons(start, panel, gbc);
        designButtons(back, panel, gbc);

        frame.setContentPane(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void designButtons(JButton start, JPanel panel, GridBagConstraints gbc) {
        start.setBackground(new Color(62, 166, 196));
        start.setForeground(new Color(255, 255, 255));
        start.setFont(new Font("Arial", Font.BOLD, 20));
        start.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(start, gbc);
        gbc.gridy++;
    }


    private void fillBoard() {
        int[] buttonNumber = {0};
        cellPanels = new JPanel[world.getBoardSizeY()][world.getBoardSizeX()];
        OrganismFactory organismFactory = new OrganismFactory();
        boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        JPanel buttonPanel = new JPanel(new GridLayout(NUMBER_OF_ORGANISMS+2, 1));
        cell_size = (int) Math.min(
                (frame.getContentPane().getWidth()*0.75) / world.getBoardSizeX(),
                (double) frame.getContentPane().getHeight() / world.getBoardSizeY()
        );



        Dimension cellPanelPreferredSize = new Dimension(cell_size, cell_size);

        for (int y = 0; y < world.getBoardSizeY(); y++) {
            for (int x = 0; x < world.getBoardSizeX(); x++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                cellPanel.addMouseListener(new CellPanelMouseListener(cellPanel, x, y, buttonNumber, world));
                if(world.getOrganism(x, y) != null) {
                    ImageIcon icon = new ImageIcon(world.getOrganism(x, y).getImagePath());
                    Image image = icon.getImage();
                    Image newImage = image.getScaledInstance(cell_size, cell_size, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImage);
                    JLabel label = new JLabel(icon);
                    cellPanel.removeAll();
                    cellPanel.add(label);
                    cellPanel.revalidate();
                    cellPanel.repaint();
                }
                else {
                    cellPanel.setBackground(Color.WHITE);
                }
                cellPanel.setPreferredSize(cellPanelPreferredSize);
                cellPanel.setMinimumSize(cellPanelPreferredSize);

                c.gridx = x;
                c.gridy = y;

                boardPanel.add(cellPanel, c);
                cellPanels[y][x] = cellPanel;
            }
        }


        for (int i = 0; i < NUMBER_OF_ORGANISMS; i++) {
            JButton button = new JButton(organismFactory.getOrganismName(i));
            button.setFont(new Font("Minecraft", Font.BOLD, 40));
            buttonPanel.add(button);
            int finalI = i;
            button.addActionListener(e -> {
                buttonNumber[0] = finalI;
                System.out.println(buttonNumber[0]);
            });
        }

        JButton gameStart = new JButton("Start game");
        gameStart.setBackground(new Color(62, 166, 196));
        gameStart.setForeground(new Color(255, 255, 255));
        gameStart.setFont(new Font("MINECRAFT", Font.BOLD, 45));

        gameStart.addActionListener(e -> {
            if(world.isHumanAlive() && world.getOrganismsSize() > 1) {
                frame.getContentPane().removeAll();
                frame.repaint();
                this.game();
            }
            else {
                JOptionPane.showMessageDialog(frame, "You have to add human and at least one other organism to start the game");
            }


        });
        buttonPanel.add(gameStart);

        JButton goBack = new JButton("Back");
        goBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            world.clearOrganisms();
            this.newGameMenu();

        });
        goBack.setBackground(new Color(62, 166, 196));
        goBack.setForeground(new Color(255, 255, 255));
        goBack.setFont(new Font("MINECRAFT", Font.BOLD, 45));
        buttonPanel.add(goBack);

        Dimension buttonPanelPreferredSize = new Dimension(cell_size, cell_size);
        buttonPanel.setPreferredSize(buttonPanelPreferredSize);
        buttonPanel.setMinimumSize(buttonPanelPreferredSize);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(boardPanel);
        splitPane.setRightComponent(buttonPanel);
        splitPane.setResizeWeight(0.75);

        frame.setContentPane(splitPane);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    private void game() {
        Dimension cellPanelPreferredSize = new Dimension(cell_size, cell_size);
        boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        createCellPanels(cellPanelPreferredSize, c);
        GUIKeyListener guiKeyListener = new GUIKeyListener(frame);

        JButton save = new JButton("Save");
        save.setBackground(new Color(62, 166, 196));
        save.setForeground(new Color(255, 255, 255));
        save.setFont(new Font("MINECRAFT", Font.BOLD, 45));
        save.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog("Enter file name");
            world.saveWorld(fileName);
            frame.requestFocusInWindow();

        });
        c.gridy = world.getBoardSizeY();
        c.insets = new Insets(50, 0, 0, 0); // Dodaj 10 pikseli wypełnienia na górze przycisku
        boardPanel.add(save);



        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setPreferredSize(new Dimension(500, 500));

        JTextPane textArea = new JTextPane();
        textArea.setEditable(false);
        textArea.setBackground(new Color(62, 166, 196));
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setFont(new Font("MINECRAFT", Font.PLAIN, 20));
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.requestFocusInWindow();
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 600));
        scrollPane.setMinimumSize(new Dimension(300, 600));
        textPanel.add(scrollPane);

        c.gridx = world.getBoardSizeX();
        c.gridy = 0;
        c.gridheight = world.getBoardSizeY();
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 20, 0);
        textArea.setText("Welcome to the game!\n");

        boardPanel.add(textPanel, c);


        frame.addKeyListener(guiKeyListener);
        frame.setContentPane(boardPanel);
        frame.revalidate();
        frame.repaint();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        if (turnPerfomer != null) {
            turnPerfomer.cancel(true);
        }
        turnPerfomer = new TurnPerfomer(world, this, cellPanels, textArea);
        turnPerfomer.execute();
    }

    private void createCellPanels(Dimension cellPanelPreferredSize, GridBagConstraints c) {
        for (int y = 0; y < world.getBoardSizeY(); y++) {
            for (int x = 0; x < world.getBoardSizeX(); x++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellPanel.setPreferredSize(cellPanelPreferredSize);
                cellPanel.setMinimumSize(cellPanelPreferredSize);

                c.gridx = x;
                c.gridy = y;

                boardPanel.add(cellPanel, c);
                cellPanels[y][x] = cellPanel;
            }
        }
    }


    public void refreshBoard() {
        cellPanels = new JPanel[world.getBoardSizeY()][world.getBoardSizeX()];
        boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        cell_size = (int) Math.min(
                (frame.getContentPane().getWidth()*0.75) / world.getBoardSizeX(),
                (double) frame.getContentPane().getHeight() / world.getBoardSizeY()
        );



        Dimension cellPanelPreferredSize = new Dimension(cell_size, cell_size);

        createCellPanels(cellPanelPreferredSize, c);
    }


    public void loadGameMenu() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Choose file to load");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("World files", "world");
        fileChooser.addChoosableFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (turnPerfomer != null){
                turnPerfomer.cancel(true);
            }
            world.loadWorld(selectedFile);
            refreshBoard();

            this.game();
        } else {
            this.newGameMenu();
        }


    }

    public int getCellSize() {
        return cell_size;
    }

    public JFrame getFrame() {
        return frame;
    }

}
