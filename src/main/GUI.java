package main;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import Organisms.OrganismFactory;

import static Organisms.OrganismFactory.NUMBER_OF_ORGANISMS;


public class GUI {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private World world;
    private int screenSizeX;
    private int screenSizeY;
    public GUI(World world) {
        this.world = world;
        this.screenSizeX = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenSizeY = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame = new JFrame("Virtual World  Piotr Sulewski 192594");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        mainMenu();
    }

    private void mainMenu() {

        JButton newGame = new JButton("New game");
        JButton loadGame = new JButton("Load game");
        JButton exit = new JButton("Exit");

        newGame.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.newGameMenu();
        });

        loadGame.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.loadGameMenu();
        });

        exit.addActionListener(e -> {
            frame.dispose();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // Ustawienie siatki 3x1 z odstępami między przyciskami
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Ustawienie marginesów

        // Ustawienie tła przycisków
        newGame.setBackground(new Color(4, 91, 192));
        newGame.setForeground(new Color(255, 255, 255));
        newGame.setFont(new Font("Arial", Font.BOLD, 20));

        loadGame.setBackground(new Color(62, 166, 196));
        loadGame.setForeground(new Color(255, 255, 255));
        loadGame.setFont(new Font("Arial", Font.BOLD, 20));

        exit.setBackground(new Color(4, 129, 129));
        exit.setForeground(new Color(255, 255, 255));
        exit.setFont(new Font("Arial", Font.BOLD, 20));


        newGame.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        loadGame.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        exit.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        panel.add(newGame);
        panel.add(loadGame);
        panel.add(exit);

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

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Ustawienie marginesów

        // Slider do wyboru boardSizeX
        JLabel labelX = new JLabel("Board width ");
        labelX.setFont(new Font("Arial", Font.BOLD, 25));
        JSlider sliderX = new JSlider(5, 50);
        sliderX.setMajorTickSpacing(10);
        sliderX.setMinorTickSpacing(1);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        sliderX.setForeground(Color.BLUE); // zmiana koloru slidera
        sliderX.setLabelTable(sliderX.createStandardLabels(10)); // opisy etykiet na osi X
        sliderX.addChangeListener(e -> {
            world.setBoardSizeX(sliderX.getValue());
        });
        JPanel sliderXPanel = new JPanel(new BorderLayout());
        sliderXPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // dodanie marginesu
        sliderXPanel.add(labelX, BorderLayout.NORTH);
        sliderXPanel.add(sliderX, BorderLayout.CENTER);
        panel.add(sliderXPanel);

        JLabel labelY = new JLabel("Board height ");
        labelY.setFont(new Font("Arial", Font.BOLD, 25));
        JSlider sliderY = new JSlider(5, 50, world.getBoardSizeY());
        sliderY.setMajorTickSpacing(10);
        sliderY.setMinorTickSpacing(1);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);
        sliderY.setForeground(Color.BLUE); // zmiana koloru slidera
        sliderY.setLabelTable(sliderY.createStandardLabels(10)); // opisy etykiet na osi Y
        sliderY.addChangeListener(e -> {
            world.setBoardSizeY(sliderY.getValue());
        });
        JPanel sliderYPanel = new JPanel(new BorderLayout());
        sliderYPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // dodanie marginesu
        sliderYPanel.add(labelY, BorderLayout.NORTH);
        sliderYPanel.add(sliderY, BorderLayout.CENTER);
        panel.add(sliderYPanel);


        start.setBackground(new Color(62, 166, 196));
        start.setForeground(new Color(255, 255, 255));
        start.setFont(new Font("Arial", Font.BOLD, 20));
        start.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(start);

        back.setBackground(new Color(62, 166, 196));
        back.setForeground(new Color(255, 255, 255));
        back.setFont(new Font("Arial", Font.BOLD, 20));
        back.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(back);

        frame.setContentPane(panel); // Ustawienie panelu jako treści ramki
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
    }



    private void fillBoard() {
        int[] buttonNumber = {0};
        OrganismFactory organismFactory = new OrganismFactory();
        JPanel boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        JPanel buttonPanel = new JPanel(new GridLayout(NUMBER_OF_ORGANISMS+2, 1));
        int cell_size = (int) Math.min(
                (frame.getContentPane().getWidth()*0.75) / world.getBoardSizeX(),
                frame.getContentPane().getHeight() / world.getBoardSizeY()
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

                    //cellPanel.setBackground(Color.BLACK);
                }
                else {
                    cellPanel.setBackground(Color.WHITE);
                }
                cellPanel.setPreferredSize(cellPanelPreferredSize);
                cellPanel.setMinimumSize(cellPanelPreferredSize);

                c.gridx = x;
                c.gridy = y;

                boardPanel.add(cellPanel, c);
            }
        }


        for (int i = 0; i < NUMBER_OF_ORGANISMS; i++) {
            JButton button = new JButton(organismFactory.getOrganismName(i));
            buttonPanel.add(button);
            int finalI = i;
            button.addActionListener(e -> {
                buttonNumber[0] = finalI;
                System.out.println(buttonNumber[0]);
            });
        }

        JButton gameStart = new JButton("Start game");
        gameStart.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            this.game();

        });
        buttonPanel.add(gameStart);

        JButton goBack = new JButton("Back");
        goBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.repaint();
            world.clearOrganisms();
            this.newGameMenu();

        });
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
        OrganismFactory organismFactory = new OrganismFactory();
        JPanel boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        int cell_size = (int) Math.min(
                (frame.getContentPane().getWidth()) / world.getBoardSizeX(),
                frame.getContentPane().getHeight() / world.getBoardSizeY()
        );



        Dimension cellPanelPreferredSize = new Dimension(cell_size, cell_size);
            frame.getContentPane().removeAll();
            frame.repaint();
            for (int y = 0; y < world.getBoardSizeY(); y++) {
                for (int x = 0; x < world.getBoardSizeX(); x++) {
                    JPanel cellPanel = new JPanel();
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    if (world.getOrganism(x, y) != null) {
                        ImageIcon icon = new ImageIcon(world.getOrganism(x, y).getImagePath());
                        Image image = icon.getImage();
                        Image newImage = image.getScaledInstance(cell_size, cell_size, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newImage);
                        JLabel label = new JLabel(icon);
                        cellPanel.removeAll();
                        cellPanel.add(label);
                        cellPanel.revalidate();
                        cellPanel.repaint();

                        //cellPanel.setBackground(Color.BLACK);
                    } else {
                        cellPanel.setBackground(Color.WHITE);
                    }
                    cellPanel.setPreferredSize(cellPanelPreferredSize);
                    cellPanel.setMinimumSize(cellPanelPreferredSize);

                    c.gridx = x;
                    c.gridy = y;

                    boardPanel.add(cellPanel, c);
                }
            }


            frame.setContentPane(boardPanel);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);

    }





    private void loadGameMenu() {




    }
}
